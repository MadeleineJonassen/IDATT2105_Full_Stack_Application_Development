package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.LoginResponseDTO;
import edu.ntnu.idatt2105.exception.UserNotFoundException;
import edu.ntnu.idatt2105.model.Role;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.RoleRepository;
import edu.ntnu.idatt2105.repository.UserRepository;
import edu.ntnu.idatt2105.service.AuthenticationService;
import edu.ntnu.idatt2105.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthenticationService authenticationService;

    private User testUser;
    private Role testRole;

    @BeforeEach
    void setUp() {
        testRole = new Role();
        testRole.setAuthority("USER");

        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setPassword("testPassword");
        testUser.setAuthorities(roles);
    }

    @Test
    void testRegisterUser() {
        when(roleRepository.findByAuthority("USER")).thenReturn(Optional.of(testRole));
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        User registeredUser = authenticationService.registerUser("testUser", "testPassword");

        assertNotNull(registeredUser);
        assertEquals("testUser", registeredUser.getUsername());
        assertEquals(1, registeredUser.getAuthorities().size());
        assertTrue(registeredUser.getAuthorities().contains(testRole));
    }

    @Test
    void testLoginUser_ValidCredentials() {
        Authentication mockAuth = mock(Authentication.class);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(mockAuth);
        when(tokenService.generateJwt(mockAuth)).thenReturn("testToken");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(testUser));

        LoginResponseDTO responseDTO = authenticationService.loginUser("testUser", "testPassword");

        assertNotNull(responseDTO);
        assertEquals("testUser", responseDTO.getUsername()); // Corrected assertion
        assertEquals("testToken", responseDTO.getJwt()); // Assert JWT token
    }


    @Test
    void testLoginUser_InvalidCredentials() {
        when(authenticationManager.authenticate(any(Authentication.class))).thenThrow(BadCredentialsException.class);

        assertThrows(UserNotFoundException.class, () -> authenticationService.loginUser("testUser", "wrongPassword"));
    }

}

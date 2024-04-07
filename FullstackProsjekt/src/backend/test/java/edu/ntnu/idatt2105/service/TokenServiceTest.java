package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.UserDTO;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private JwtDecoder jwtDecoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        tokenService = new TokenService(jwtEncoder, jwtDecoder, userRepository);
    }
/*
   @Test
    void generateJwt_ReturnsValidToken() {
    when(authentication.getName()).thenReturn("testUser");
    when(authentication.getAuthorities()).thenReturn(Collections.singletonList(new SimpleGrantedAuthority(Set<"USER">)));
    Jwt jwt = mock(Jwt.class);
    when(jwt.getTokenValue()).thenReturn("mockJwtToken");
    when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwt);

    // Act
    String token = tokenService.generateJwt(authentication);

    // Assert
    assertNotNull(token);
    assertEquals("mockJwtToken", token);
    }

 */

@Test
void verifyToken_WithValidToken() {
    // Mock the behavior to return a Jwt object for a valid token
    Jwt jwt = mock(Jwt.class);
    when(jwtDecoder.decode("validToken")).thenReturn(jwt);

    // Verify that no exception is thrown
    assertDoesNotThrow(() -> tokenService.verifyToken("validToken"));

}


    @Test
    void verifyToken_WithInvalidToken() {
        // Mock the behavior to throw RuntimeException for an invalid token
        doThrow(new RuntimeException("Invalid token")).when(jwtDecoder).decode("invalidToken");

        // Verify that the expected exception is thrown
        assertThrows(RuntimeException.class, () -> tokenService.verifyToken("invalidToken"));
    }


    @Test
    void getUserDTOFromToken_ReturnsUserDTO() {
        Jwt jwt = mock(Jwt.class);
        when(jwtDecoder.decode("validToken")).thenReturn(jwt);
        when(jwt.getSubject()).thenReturn("testUser");

        User user = new User();
        user.setId(1);
        user.setUsername("testUser");
        user.setPassword("password");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        UserDTO userDTO = tokenService.getUserDTOFromToken("validToken");

        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getPassword(), userDTO.getPassword());
    }

    @Test
    void getUserDTOFromToken_WithInvalidToken_ThrowsException() {
        when(jwtDecoder.decode("invalidToken")).thenThrow(new IllegalArgumentException("Invalid token"));

        assertThrows(IllegalArgumentException.class, () -> tokenService.getUserDTOFromToken("invalidToken"));
    }
}

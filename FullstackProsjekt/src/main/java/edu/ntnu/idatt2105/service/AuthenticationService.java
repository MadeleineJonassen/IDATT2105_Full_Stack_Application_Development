package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.dto.LoginResponseDTO;
import edu.ntnu.idatt2105.exception.UserNotFoundException;
import edu.ntnu.idatt2105.model.Role;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.RoleRepository;
import edu.ntnu.idatt2105.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authManager;
  private final TokenService tokenService;
  private final UserService userService;

  public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
                               PasswordEncoder encoder, AuthenticationManager authManager,
                               TokenService tokenService, UserService userService) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.encoder = encoder;
    this.authManager = authManager;
    this.tokenService = tokenService;
    this.userService = userService;
  }

  public User registerUser(String username, String password) {
    String encodedPassword = encoder.encode(password);
    Role userRole = null;
    if (roleRepository.findByAuthority("USER").isPresent()) userRole = roleRepository.findByAuthority("USER").get();

    Set<Role> authorities = new HashSet<>();
    authorities.add(userRole);

    return userRepository.save(new User(username, encodedPassword, authorities));
  }

  public LoginResponseDTO loginUser(String username, String password) {
    try {
      Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      String token = tokenService.generateJwt(auth);
      System.out.println("first token: " + token);

      Optional<User> optionalUser = userRepository.findByUsername(username);

      if (optionalUser.isPresent()) {
        return new LoginResponseDTO(optionalUser.get(), token);
      } else {
        throw new UsernameNotFoundException("User not found.");
      }

    } catch (AuthenticationException e) {
      throw new UserNotFoundException("Invalid username or password.");
    }
  }

  public String refreshJWT(String existingToken) {
    tokenService.verifyToken(existingToken);

    String username = tokenService.getNameFromToken(existingToken);

    UserDetails userDetails = userService.loadUserByUsername(username);

    Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    return tokenService.generateJwt(auth);
  }
}

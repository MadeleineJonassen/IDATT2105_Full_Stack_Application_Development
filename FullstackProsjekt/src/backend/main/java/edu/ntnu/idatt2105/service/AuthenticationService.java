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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for handling user authentication.
 */
@Service
@Transactional
public class AuthenticationService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authManager;
  private final TokenService tokenService;
  private final UserService userService;

  /**
   * Constructs an AuthenticationService.
   *
   * @param userRepository  The repository for user operations.
   * @param roleRepository  The repository for role operations.
   * @param encoder         The password encoder.
   * @param authManager     The authentication manager.
   * @param tokenService    The service for JWT token operations.
   * @param userService     The service for user operations.
   */
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

  /**
   * Registers a new user.
   *
   * @param username The username of the user.
   * @param password The password of the user.
   * @return The newly registered user.
   */
  public User registerUser(String username, String password) {
    String encodedPassword = encoder.encode(password);
    Role userRole = null;
    if (roleRepository.findByAuthority("USER").isPresent()) userRole = roleRepository.findByAuthority("USER").get();

    Set<Role> authorities = new HashSet<>();
    authorities.add(userRole);

    return userRepository.save(new User(username, encodedPassword, authorities));
  }

  /**
   * Logs in a user.
   *
   * @param username The username of the user.
   * @param password The password of the user.
   * @return A LoginResponseDTO containing the user details and JWT token.
   * @throws UserNotFoundException if the user is not found or the credentials are invalid.
   */
  public LoginResponseDTO loginUser(String username, String password) {
    try {
      Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      String token = tokenService.generateJwt(auth);

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

  /**
   * Refreshes a JWT token.
   *
   * @param existingToken The existing JWT token.
   * @return The refreshed JWT token.
   */
  public String refreshJWT(String existingToken) {
    tokenService.verifyToken(existingToken);

    String username = tokenService.getUsernameFromToken(existingToken);

    UserDetails userDetails = userService.loadUserByUsername(username);

    Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    return tokenService.generateJwt(auth);
  }
}

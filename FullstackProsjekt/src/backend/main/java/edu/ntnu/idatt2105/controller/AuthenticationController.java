package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.LoginResponseDTO;
import edu.ntnu.idatt2105.dto.TokenDTO;
import edu.ntnu.idatt2105.dto.UserRegistrationDTO;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling authentication-related API endpoints.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private final AuthenticationService authService;
  private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

  /**
   * Constructor for AuthenticationController.
   *
   * @param authService An instance of AuthenticationService used for user authentication.
   */
  public AuthenticationController(AuthenticationService authService) {
    this.authService = authService;
  }

  /**
   * Endpoint for user registration.
   *
   * @param registrationDTO The registration data for the new user.
   * @return The newly registered user.
   */
  @PostMapping("/register")
  public User registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
    // TODO: Remove return of password, use DTO
    return authService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());
  }

  /**
   * Endpoint for user login.
   *
   * @param registrationDTO The login data for the user.
   * @return The login response containing authentication token.
   */
  @PostMapping("/login")
  public LoginResponseDTO loginUser(@RequestBody UserRegistrationDTO registrationDTO) {
    logger.info("Logging in with user " + registrationDTO.toString());
    return authService.loginUser(registrationDTO.getUsername(), registrationDTO.getPassword());
  }

  /**
   * Endpoint for refreshing JWT token.
   *
   * @param existingToken The existing JWT token.
   * @return The refreshed JWT token.
   * @throws InterruptedException If interrupted while sleeping.
   */
  @PostMapping("/refresh")
  public TokenDTO refreshJWT(@RequestBody TokenDTO existingToken) throws InterruptedException {
    Thread.sleep(500);
    System.out.println("OLD TOKEN1: " + existingToken);
    String token = authService.refreshJWT(existingToken.getToken());
    System.out.println("OLD TOKEN2: " + existingToken);
    System.out.println("NEW TOKEN MAYBE: " + token);

    return new TokenDTO(token);
  }
}

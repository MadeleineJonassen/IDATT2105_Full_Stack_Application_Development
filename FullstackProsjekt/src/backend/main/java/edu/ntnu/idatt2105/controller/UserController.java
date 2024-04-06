package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.dto.UserDTO;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.service.TokenService;
import edu.ntnu.idatt2105.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling user-related endpoints.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;
  private final TokenService tokenService;

  /**
   * Constructor for UserController.
   *
   * @param userService  An instance of UserService used for managing users.
   * @param tokenService An instance of TokenService used for token-related operations.
   */
  public UserController(UserService userService, TokenService tokenService) {
    this.tokenService = tokenService;
    this.userService = userService;
  }

  /**
   * Endpoint for retrieving all application users.
   *
   * @return A list of all application users.
   */
  @GetMapping("/")
  public List<User> getAppUsers() {
    return userService.findAllAppUsers();
  }

  /**
   * Endpoint for retrieving user ID from a token.
   *
   * @param token The token to extract user ID from.
   * @return The user DTO associated with the token.
   */
  @GetMapping("/getId/{token}")
  public UserDTO getUserIdFromToken(@PathVariable String token) {
    return tokenService.getUserDTOFromToken(token);
  }
}

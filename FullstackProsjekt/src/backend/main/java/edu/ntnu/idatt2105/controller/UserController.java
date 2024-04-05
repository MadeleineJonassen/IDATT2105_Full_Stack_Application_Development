package edu.ntnu.idatt2105.controller;


import edu.ntnu.idatt2105.dto.UserDTO;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.service.TokenService;
import edu.ntnu.idatt2105.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;
  private final TokenService tokenService;

  public UserController(UserService userService, TokenService tokenService) {
    this.tokenService = tokenService;
    this.userService = userService;
  }


  @GetMapping("/")
  public List<User> getAppUsers() {
    return userService.findAllAppUsers();
  }

  @GetMapping("/getId/{token}")
  public UserDTO getUserDTOFromToken(@PathVariable String token) {
    return tokenService.getUserDTOFromToken(token);
  }
}

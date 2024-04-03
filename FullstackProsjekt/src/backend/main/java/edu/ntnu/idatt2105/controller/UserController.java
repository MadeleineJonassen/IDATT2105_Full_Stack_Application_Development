package edu.ntnu.idatt2105.controller;


import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping("/")
  public List<User> getAppUsers() {
    return userService.findAllAppUsers();
  }
}

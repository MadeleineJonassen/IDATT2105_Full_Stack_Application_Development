package idatt2105.group11.idatt2105projectbackend.controller;


import idatt2105.group11.idatt2105projectbackend.model.User;
import idatt2105.group11.idatt2105projectbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
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

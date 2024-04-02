package edu.ntnu.idatt2105.controller;


import edu.ntnu.idatt2105.dto.LoginResponseDTO;
import edu.ntnu.idatt2105.dto.TokenDTO;
import edu.ntnu.idatt2105.dto.UserRegistrationDTO;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

  private final AuthenticationService authService;

  public AuthenticationController(AuthenticationService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public User registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
    //TODO: Remove return of password, use DTO
    return authService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());
  }

  @PostMapping("/login")
  public LoginResponseDTO loginUser(@RequestBody UserRegistrationDTO registrationDTO) {
    return authService.loginUser(registrationDTO.getUsername(), registrationDTO.getPassword());
  }

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

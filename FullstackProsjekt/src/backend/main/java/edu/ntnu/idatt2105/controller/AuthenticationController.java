package edu.ntnu.idatt2105.controller;


import edu.ntnu.idatt2105.dto.LoginResponseDTO;
import edu.ntnu.idatt2105.dto.TokenDTO;
import edu.ntnu.idatt2105.dto.UserRegistrationDTO;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

  private final AuthenticationService authService;
  private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

  public AuthenticationController(AuthenticationService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public User registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
    //TODO: Remove return of password, use DTO
    return authService.registerUser(registrationDTO.getName(), registrationDTO.getPassword());
  }

  @PostMapping("/login")
  public LoginResponseDTO loginUser(@RequestBody UserRegistrationDTO registrationDTO) {
    logger.info("Logging in with user " + registrationDTO.toString());
    return authService.loginUser(registrationDTO.getName(), registrationDTO.getPassword());
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
package idatt2105.group11.idatt2105projectbackend.controller;


import idatt2105.group11.idatt2105projectbackend.dto.LoginResponseDTO;
import idatt2105.group11.idatt2105projectbackend.dto.TokenDTO;
import idatt2105.group11.idatt2105projectbackend.dto.UserRegistrationDTO;
import idatt2105.group11.idatt2105projectbackend.model.User;
import idatt2105.group11.idatt2105projectbackend.service.AuthenticationService;
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
    return authService.registerUser(registrationDTO.getName(), registrationDTO.getPassword());
  }

  @PostMapping("/login")
  public LoginResponseDTO loginUser(@RequestBody UserRegistrationDTO registrationDTO) {
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

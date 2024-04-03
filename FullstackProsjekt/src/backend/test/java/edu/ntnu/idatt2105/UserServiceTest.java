package edu.ntnu.idatt2105;

import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.UserRepository;
import edu.ntnu.idatt2105.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  private UserRepository userRepository;

  private UserService userService;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);
    userService = new UserService(userRepository); // Assuming UserService is the class under test
  }

  @Test
  void testAddAppUser() {
    // Create a user to add
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("password");

    // Call the method to add the user
    userService.addAppUser(user);

    // Verify that userRepository.save() was called with the correct user
    verify(userRepository).save(user);
  }

  @Test
  void testLoadUserByUsername_UserFound() {
    // Mock behavior of userRepository.findByUsername to return a user
    String username = "testUser";
    User user = new User();
    user.setUsername(username);
    user.setPassword("password");
    when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

    // Call the method under test
    UserDetails userDetails = userService.loadUserByUsername(username);

    // Verify that the returned UserDetails object has the correct username
    assertEquals(username, userDetails.getUsername());
  }

  @Test
  void testLoadUserByUsername_UserNotFound() {
    // Mock behavior of userRepository.findByUsername to return an empty Optional
    String username = "nonExistentUser";
    when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

    // Call the method under test and expect UsernameNotFoundException
    assertThrows(UsernameNotFoundException.class, () ->
      userService.loadUserByUsername(username));
  }
}


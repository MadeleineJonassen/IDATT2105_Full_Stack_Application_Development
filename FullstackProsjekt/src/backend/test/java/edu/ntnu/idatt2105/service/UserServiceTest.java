package edu.ntnu.idatt2105.service;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  private UserRepository userRepository;

  private UserService userService;

  @BeforeEach
  void setUp() {
    userRepository = mock(UserRepository.class);
    userService = new UserService(userRepository);
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

  @Test
  void testVerifyAppUser_ExistingUser() {
    // Mocking UserRepository
    UserRepository userRepository = mock(UserRepository.class);

    // Creating a sample user
    User user = new User();
    user.setUsername("existingUser");
    user.setPassword("password123");

    // Mocking UserRepository's behavior to return an existing user when queried by username or password
    when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
    when(userRepository.findByPassword(user.getPassword())).thenReturn(Optional.of(user));

    // Creating an instance of UserService
    UserService userService = new UserService(userRepository);

    // Verifying that the method returns true for an existing user
    assertTrue(userService.verifyAppUser(user));
  }

  @Test
  void testVerifyAppUser_NonExistingUser() {
    // Mocking UserRepository
    UserRepository userRepository = mock(UserRepository.class);

    // Creating a sample user
    User user = new User();
    user.setUsername("nonExistingUser");
    user.setPassword("password123");

    // Mocking UserRepository's behavior to return empty Optional when queried by username or password
    lenient().when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
    lenient().when(userRepository.findByPassword(user.getPassword())).thenReturn(Optional.empty());

    // Creating an instance of UserService
    UserService userService = new UserService(userRepository);

    // Verifying that the method returns false for a non-existing user
    assertFalse(userService.verifyAppUser(user));
  }
}


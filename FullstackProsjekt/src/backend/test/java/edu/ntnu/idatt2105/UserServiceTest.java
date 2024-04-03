package edu.ntnu.idatt2105;

import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.UserRepository;
import edu.ntnu.idatt2105.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
}


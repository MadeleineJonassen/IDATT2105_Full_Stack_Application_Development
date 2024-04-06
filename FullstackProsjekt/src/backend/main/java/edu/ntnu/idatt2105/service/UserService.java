package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing application users.
 */
@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  /**
   * Constructor for UserService.
   *
   * @param userRepository The repository for user data.
   */
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Retrieves all application users.
   *
   * @return The list of all application users.
   */
  public List<User> findAllAppUsers() {
    return userRepository.findAll();
  }

  /**
   * Adds a new application user.
   *
   * @param user The user to add.
   */
  public void addAppUser(User user) {
    userRepository.save(user);
  }

  /**
   * Verifies if an application user exists.
   *
   * @param user The user to verify.
   * @return True if the user exists, otherwise false.
   */
  public Boolean verifyAppUser(User user) {
    return userRepository.findByUsername(user.getUsername()).isPresent() &&
            userRepository.findByPassword(user.getPassword()).isPresent();
  }

  /**
   * Finds an application user by username.
   *
   * @param name The username of the user to find.
   * @return The found user, or null if not found.
   */
  public User findAppUserByName(String name) {
    Optional<User> userOptional = userRepository.findByUsername(name);
    return userOptional.orElse(null);
  }

  /**
   * Loads a user by username.
   *
   * @param name The username of the user to load.
   * @return The UserDetails object representing the user.
   * @throws UsernameNotFoundException If the user is not found.
   */
  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    return userRepository.findByUsername(name)
            .orElseThrow(() -> new UsernameNotFoundException("User '" + name + "' not found."));
  }
}

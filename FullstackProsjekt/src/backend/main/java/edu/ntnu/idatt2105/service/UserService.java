package edu.ntnu.idatt2105.service;

import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAllAppUsers() {
    return userRepository.findAll();
  }

  public void addAppUser(@RequestBody User user) {
    userRepository.save(user);
  }

  public Boolean verifyAppUser(User user) {
    return userRepository.findByName(user.getName()).isPresent() &&
            userRepository.findByPassword(user.getPassword()).isPresent();
  }

  public User findAppUserByName(String name) {
    Optional<User> userOptional = userRepository.findByName(name);

    return userOptional.orElse(null);
  }

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    return userRepository.findByName(name)
            .orElseThrow(() -> new UsernameNotFoundException("User '" + name + "' not found."));
  }
}

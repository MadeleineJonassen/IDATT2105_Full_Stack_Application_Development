package edu.ntnu.idatt2105.repository;


import edu.ntnu.idatt2105.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities in the database.
 * Provides methods for querying and manipulating users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Finds a user by username.
   *
   * @param username The username of the user to find.
   * @return An {@link Optional} containing the {@link User} with the specified username,
   *         or empty if no user is found.
   */
  Optional<User> findByUsername(String username);

  /**
   * Finds a user by ID.
   *
   * @param id The ID of the user to find.
   * @return An {@link Optional} containing the {@link User} with the specified ID,
   *         or empty if no user is found.
   */
  Optional<User> findById(Integer id);

  /**
   * Finds a user by password.
   *
   * @param password The password of the user to find.
   * @return An {@link Optional} containing the {@link User} with the specified password,
   *         or empty if no user is found.
   */
  Optional<User> findByPassword(String password);
}

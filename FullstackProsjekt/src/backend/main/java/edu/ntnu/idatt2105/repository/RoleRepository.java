package edu.ntnu.idatt2105.repository;

import edu.ntnu.idatt2105.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Role} entities in the database.
 * Provides methods for querying and manipulating roles.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  /**
   * Finds a role by its authority.
   *
   * @param authority The authority string of the role.
   * @return An {@link Optional} containing the {@link Role} with the specified authority,
   *         or empty if no role is found.
   */
  Optional<Role> findByAuthority(String authority);
}

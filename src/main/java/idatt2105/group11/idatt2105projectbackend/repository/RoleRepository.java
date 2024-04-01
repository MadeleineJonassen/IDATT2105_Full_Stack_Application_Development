package idatt2105.group11.idatt2105projectbackend.repository;

import idatt2105.group11.idatt2105projectbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByAuthority(String authority);
}

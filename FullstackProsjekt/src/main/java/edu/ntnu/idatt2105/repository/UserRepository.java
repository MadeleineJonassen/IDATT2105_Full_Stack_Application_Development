package edu.ntnu.idatt2105.repository;


import edu.ntnu.idatt2105.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String Name);

  Optional<User> findById(Integer id);

  Optional<User> findByPassword(String password);
}



package idatt2105.group11.idatt2105projectbackend.repository;


import idatt2105.group11.idatt2105projectbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByName(String Name);

  Optional<User> findById(Integer id);

  Optional<User> findByPassword(String password);
}



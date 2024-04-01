package edu.ntnu.idatt2105;

import edu.ntnu.idatt2105.model.Role;
import edu.ntnu.idatt2105.model.User;
import edu.ntnu.idatt2105.repository.RoleRepository;
import edu.ntnu.idatt2105.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:5173")
public class FullstackProsjektApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullstackProsjektApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            // Exit early if DB already contains the admin user
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User(2, "potetmos", encoder.encode("potetmos"), roles);
            userRepository.save(admin);
        };
    }
}
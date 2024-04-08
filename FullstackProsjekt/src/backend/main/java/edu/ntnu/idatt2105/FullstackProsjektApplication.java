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
            Role userRole = roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            User testUser = new User("test", encoder.encode("test"), roles);

            if(userRepository.findByUsername(testUser.getUsername()).isEmpty()) userRepository.save(testUser);

            roles.add(adminRole);
            User admin = new User("admin", encoder.encode("password"), roles);
            if(userRepository.findByUsername(admin.getUsername()).isEmpty()) userRepository.save(admin);
        };
    }
}
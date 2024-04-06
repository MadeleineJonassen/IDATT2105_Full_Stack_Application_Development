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


}
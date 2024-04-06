package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Create a sample user
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        user = new User("testuser", "password", roles);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertNotNull(user.getAuthorities());

        // Test setters
        user.setUsername("newuser");
        assertEquals("newuser", user.getUsername());

        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());

        Set<Role> newRoles = new HashSet<>();
        newRoles.add(new Role("ROLE_ADMIN"));
        user.setAuthorities(newRoles);
        assertEquals(newRoles, user.getAuthorities());
    }
}

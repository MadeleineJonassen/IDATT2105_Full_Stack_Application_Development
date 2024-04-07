package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGetId() {
        // Test getId method
        assertNull(user.getId());
    }

    @Test
    public void testIsAccountNonExpired() {
        // Test isAccountNonExpired method
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        // Test isAccountNonLocked method
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        // Test isCredentialsNonExpired method
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        // Test isEnabled method
        assertTrue(user.isEnabled());
    }

    @Test
    public void testGetAuthorities() {
        // Test getAuthorities method
        assertNotNull(user.getAuthorities());
        assertEquals(1, user.getAuthorities().size());
        assertEquals("ROLE_USER", user.getAuthorities().iterator().next().getAuthority());
    }
}

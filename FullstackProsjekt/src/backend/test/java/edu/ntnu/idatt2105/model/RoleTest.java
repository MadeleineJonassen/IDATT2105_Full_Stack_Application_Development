package edu.ntnu.idatt2105.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RoleTest {

    private Role role;

    @BeforeEach
    public void setUp() {
        // Create a sample role
        role = new Role("ROLE_USER");
        role.setRoleId(1);
    }

    @Test
    public void testGettersAndSetters() {
        // Test getters
        assertEquals(Integer.valueOf(1), role.getRoleId());
        assertEquals("ROLE_USER", role.getAuthority());

        // Test setters
        role.setRoleId(2);
        assertEquals(Integer.valueOf(2), role.getRoleId());

        role.setAuthority("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", role.getAuthority());
    }

    @Test
    public void testToString() {
        // Test toString method
        assertEquals("Role{roleId=1, authority='ROLE_USER'}", role.toString());
    }

    @Test
    public void testGetAuthority() {
        // Test getAuthority method
        assertEquals("ROLE_USER", role.getAuthority());
    }
}

package test;

import model.User;
import model.UserDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    @Test
    public void testDoRetrieveByUsername() throws SQLException {
        // Assuming you have a working UserDAO class
        UserDAO userDAO = new UserDAO();

        // Insert a test user into the database (make sure the user with this username exists)
        User testUser = new User();
        testUser.setUsername("testUser");
        testUser.setNome("Test");
        testUser.setCognome("User");
        testUser.setEmail("testuser@example.com");
        testUser.setTelefono("1234567890");
        testUser.setIndirizzo("Test Address");
        testUser.setPassword("testpassword");
        testUser.setAdmin(false);
        userDAO.doSave(testUser);

        try {
            // Test retrieving the user by username
            User retrievedUser = userDAO.doRetrieveByUsername("testUser");

            // Assert that the retrieved user is not null
            assertNotNull(retrievedUser);

            // Assert that the retrieved user has the expected attributes
            assertEquals("testUser", retrievedUser.getUsername());
            assertEquals("Test", retrievedUser.getNome());
            assertEquals("User", retrievedUser.getCognome());
            assertEquals("testuser@example.com", retrievedUser.getEmail());
            assertEquals("1234567890", retrievedUser.getTelefono());
            assertEquals("Test Address", retrievedUser.getIndirizzo());
            assertEquals("testpassword", retrievedUser.getPassword());
            assertFalse(retrievedUser.isAdmin());  // Assuming the test user is not an admin

        } catch (SQLException e) {
            // If an exception occurs during the test, fail the test
            fail("Exception occurred: " + e.getMessage());
        } finally {
            // Clean up: remove the test user from the database
            userDAO.deleteUser("testUser");
        }
    }

    @Test
    public void testDoRetrieveByUsernameNotFound() throws SQLException {
        // Assuming you have a working UserDAO class
        UserDAO userDAO = new UserDAO();

        try {
            // Test retrieving a non-existing user by username
            User retrievedUser = userDAO.doRetrieveByUsername("nonExistingUser");

            // Assert that the retrieved user is null (not found)
            assertNull(retrievedUser);

        } catch (SQLException e) {
            // If an exception occurs during the test, fail the test
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testSaveDuplicateUsername() throws SQLException {
        // Assuming you have a working UserDAO class
        UserDAO userDAO = new UserDAO();

        // Insert a test user into the database
        User testUser = new User();
        testUser.setUsername("duplicateUser");
        testUser.setNome("Test");
        testUser.setCognome("User");
        testUser.setEmail("testuser@example.com");
        testUser.setTelefono("1234567890");
        testUser.setIndirizzo("Test Address");
        testUser.setPassword("testpassword");
        testUser.setAdmin(false);
        userDAO.doSave(testUser);

        try {
            // Attempt to insert a user with the same username (duplicate)
            User duplicateUser = new User();
            duplicateUser.setUsername("duplicateUser");
            duplicateUser.setNome("Duplicate");
            duplicateUser.setCognome("User");
            duplicateUser.setEmail("duplicateuser@example.com");
            duplicateUser.setTelefono("9876543210");
            duplicateUser.setIndirizzo("Duplicate Address");
            duplicateUser.setPassword("duplicatepassword");
            duplicateUser.setAdmin(true);
            assertThrows(SQLException.class, () -> userDAO.doSave(duplicateUser));

        } finally {
            // Clean up: remove the test user from the database
            userDAO.deleteUser("duplicateUser");
        }
    }
}

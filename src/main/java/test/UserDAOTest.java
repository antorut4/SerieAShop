package test;

import model.User;
import model.UserDAO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    @Test
    public void testDoRetrieveByUsername() throws SQLException {
        UserDAO userDAO = new UserDAO();

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
}
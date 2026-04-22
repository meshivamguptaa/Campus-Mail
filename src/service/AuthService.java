// This class handles user authentication logic, including registration and login. It interacts with the UserDAO to access user data in the database.
package service;

import dao.UserDAO;
import model.User;

public class AuthService {

    private UserDAO userDAO;                // Data Access Object for handling user-related database operations

    public AuthService() {
        userDAO = new UserDAO();               // Initialize the UserDAO to interact with the database for user operations
    }

    // Method to register a new user, which checks if the email is already in use and saves the new user to the database if it's not
    public boolean register(String name, String email, String password) {

    // check if user already exists
    User existingUser = userDAO.getUserByEmail(email);

    if (existingUser != null) {
        return false; // email already used
    }

    // create new user
    User newUser = new User(name, email, password);

    userDAO.saveUser(newUser);

    return true;
    }

    // Method to log in a user, which retrieves the user by email and checks if the provided password matches the stored password
    public User login(String email, String password) {

    User user = userDAO.getUserByEmail(email);

    if (user == null) {
        return null; // no such user
    }

    if (user.getPassword().equals(password)) {
        return user; // success
    }

    return null; // wrong password
    }
}   

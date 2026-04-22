// This class manages the user session, allowing the application to keep track of the currently logged-in user. It provides methods to set, get, and clear the current user session.
package core;

import model.User;

public class SessionManager {

    private static User currentUser;           // Static variable to hold the currently logged-in user, allowing the application to access user information across different parts of the application while the session is active


    // Method to set the current user, which is called after a successful login to store the user's information in the session
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // Method to get the current user, which allows other parts of the application to access the logged-in user's information for personalization and authorization purposes
    public static User getCurrentUser() {
        return currentUser;
    }

    // Method to log out the user, which clears the current user session by setting the currentUser variable to null, effectively ending the user's session and requiring them to log in again to access protected features
    public static void logout() {
        currentUser = null;
    }
}
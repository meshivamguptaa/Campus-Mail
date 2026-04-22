// User model class to represent user data in the mail client application

package model;

public class User {

    // User model class to represent user data
    private int id;
    private String name;
    private String email;
    private String password;

    // Constructor for DB fetch (with id)
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Constructor for register (no id yet)
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;                                  // Return the ID of the user, useful for database operations and linking to messages
    }

    public String getName() {
        return name;                                // Return the name of the user, useful for displaying in the UI and personalizing the user experience
    }

    public String getEmail() {
        return email;                               // Return the email of the user, useful for login and communication purposes in the mail client
    }

    public String getPassword() {
        return password;                             // Return the password of the user, useful for authentication (note: in a real application, passwords should be hashed and not stored in plain text)                                         
    }

    // Setters (important)
    public void setId(int id) {
        this.id = id;                               // Set the ID of the user, useful for assigning a unique identifier when registering a new user or fetching from the database
    }
}
package dao;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet; // Import ResultSet for handling query results


public class UserDAO {

    public UserDAO() {
        // Initialize database connection or any required resources for user data access
        
    }

    public void saveUser(User user) {

    String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());

        stmt.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Error creating account", e);
    }
}
    public User getUserByEmail(String email) {

    String sql = "SELECT * FROM users WHERE email = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password")
            );
        }

        return null;

    } catch (SQLException e) {
        throw new RuntimeException("Error retrieving user", e);
    }
}


    public User getUserById(int id) {

    String sql = "SELECT * FROM users WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password")
            );
        }

        return null;

    } catch (SQLException e) {
        throw new RuntimeException("Error retrieving user", e);
    }
}
            
}
    
    


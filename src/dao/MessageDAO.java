// This class is responsible for managing message data, including storing, retrieving, and updating messages in the mail client.
package dao;

public class MessageDAO {
    MessageDAO() {
        // Initialize message DAO, e.g., connect to database or set up file storage
        public void saveMessage(Message message) {
            // Code to save message to database or file
            // Implementation details would go here
            String sql = "INSERT INTO messages (sender_id, recipient_id, subject, body, timestamp, status) VALUES (?, ?, ?, ?, ?, Now())";
            // Use JDBC to execute the SQL statement and save the message to the database
            try(Connection conn = DBConnection.getConnection();                // Establish a connection to the database
                    PreparedStatement stmt = conn. prepareStatement(sql)){       // Prepare the SQL statement for execution
                stmt.setInt(1, message.getSenderID());                          // Set the senderId parameter in the SQL statement
                stmt.setInt(2, message.getRecipientID());  
                stmt.setString(3, message.getSubject());
                stmt.setString(4, message.getBody());
                stmt.setString(5, message.getStatus().name());
                stmt.executeUpdate(); // Execute the SQL statement to save the message
                System.out.println("Message saved successfully!");
            } catch (SQLException e) {
                e.printStackTrace(); // Handle any SQL exceptions that occur during the save operation
                       }

        }


        
    }
    
}

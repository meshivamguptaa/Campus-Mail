// This class is responsible for managing message data, including storing, retrieving, and updating messages in the mail client.
package dao;
import model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDAO {
    MessageDAO() {
        // Initialize message DAO, e.g., connect to database or set up file storage
        public void saveMessage(Message message) {
            // Code to save message to database or file
            // Implementation details would go here
            String sql = "INSERT INTO messages (sender_id, recipient_id, subject, body, status, timestamp) VALUES (?, ?, ?, ?, ?, Now())";
            // Use JDBC to execute the SQL statement and save the message to the database
            try(Connection conn = DBConnection.getConnection();                // Establish a connection to the database
                    PreparedStatement stmt = conn. prepareStatement(sql)){       // Prepare the SQL statement for execution
                stmt.setInt(1, message.getSenderID());                          // Set the senderId parameter in the SQL statement
                stmt.setInt(2, message.getRecipientID());  
                stmt.setString(3, message.getSubject());
                stmt.setString(4, message.getBody());
                stmt.setString(5, message.getStatus());
                stmt.executeUpdate(); // Execute the SQL statement to save the message
                System.out.println("Message saved successfully!");
            } catch (SQLException e) {
                throw new RuntimeException("Error saving message", e);// Handle any SQL exceptions that occur during the save operation
                       }

        }


        // Additional methods for retrieving, updating, and deleting messages can be added here
        public Message getMessageById(int id) {
            // Code to retrieve a message by its ID from the database or file
            // Implementation details would go here
            String sql = "SELECT * FROM messages WHERE id = ?"; // SQL query to retrieve a message by its ID
            try(Connevtion conn = DBConnection.getConnection();                // Establish a connection to the database
                    PreparedStatement stmt = conn. prepareStatement(sql)){       // Prepare the SQL statement for execution
                stmt.setInt(1, id);                          // Set the ID parameter in the SQL statement
                ResultSet rs = stmt.executeQuery(); // Execute the SQL query to retrieve the message
                if (rs.next()) {
                    // Create a Message object from the result set and return it
                    Message message = new Message(
                        rs.getInt("id"), 
                        rs.getInt("sender_id"), 
                        rs.getInt("recipient_id"), 
                        rs.getString("subject"), 
                        rs.getString("body"), 
                        rs.getStatus("status").equals("DRAFT") ? Message.MessageStatus.DRAFT :
                        rs.getStatus("status").equals("SENT") ? Message.MessageStatus.SENT :
                        rs.getStatus("status").equals("INBOX") ? Message.MessageStatus.INBOX : null,

                        rs.getTimestamp("timestamp").toLocalDateTime()
                    );
                    return message; // Return the retrieved message
                } else {
                    System.out.println("Message not found with ID: " + id); // Handle case where message is not found
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle any SQL exceptions that occur during the retrieval operation
                return null;
            }
        }


        public List<Message> getInboxMessages(int recipientId){
            // Code to retrieve all messages for a specific recipient from the database or file
            // Implementation details would go here
            String sql = "SELECT * FROM messages WHERE recipient_id = ? AND status = 'INBOX'"; // SQL query to retrieve inbox messages for a specific recipient
            List<Message> inboxMessages = new ArrayList<>();
            try(Connection conn = DBConnection.getConnection();                // Establish a connection to the database
                PreparedStatement stmt = conn. prepareStatement(sql)){       // Prepare the SQL statement for execution
                stmt.setInt(1, recipientId);                          // Set the recipientId parameter in the SQL statement
                ResultSet rs = stmt.executeQuery(); // Execute the SQL query to retrieve the inbox messages
                while (rs.next()) {
                    // Create Message objects from the result set and add them to a list or return them as needed
                    Message message = new Message(
                        rs.getInt("id"), 
                        rs.getInt("sender_id"), 
                        rs.getInt("recipient_id"), 
                        rs.getString("subject"), 
                        rs.getString("body"), 
                        rs.getString("status"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                        
                    );
                    inboxMessages.add(message);
                    // Add message to a list or return it as needed
                }
                 // Return the list of inbox messages for the specified recipient
            } catch (SQLException e) {
                e.printStackTrace(); // Handle any SQL exceptions that occur during the retrieval operation
            }
            return inboxMessages;
        }
        
    }
    
}

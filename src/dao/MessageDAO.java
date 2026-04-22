// This class is responsible for managing message data, including storing, retrieving, and updating messages in the mail client.
package dao;
import model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class MessageDAO {
    public MessageDAO() {
        // Initialize message DAO, e.g., connect to database or set up file storage


    }

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

    String sql = "SELECT * FROM messages WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);                    // Set the id parameter in the SQL statement to retrieve the specific message by its unique identifier

        ResultSet rs = stmt.executeQuery();          // Execute the SQL query to retrieve the message from the database based on the provided id

        // If a message with the specified id is found in the database, create a Message object from the result set and return it; otherwise, return null to indicate that no message was found with the given id
        if (rs.next()) {

            Message message = new Message(             // Create a new Message object using the data retrieved from the database result set, including the message's id, senderId, recipientId, subject, body, status, and timestamp (converted to LocalDateTime)
                rs.getInt("id"),
                rs.getInt("sender_id"),
                rs.getInt("recipient_id"), // or receiver_id (be consistent)
                rs.getString("subject"),
                rs.getString("body"),
                rs.getString("status"),
                rs.getTimestamp("timestamp").toLocalDateTime()
            );

            return message;
        }

        return null;

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

public List<Message> getSentMessages(int senderId) {
    String sql = "SELECT * FROM messages WHERE sender_id = ? AND status = 'SENT'";
    List<Message> sentMessages = new ArrayList<>();

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, senderId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Message message = new Message(
                rs.getInt("id"),
                rs.getInt("sender_id"),
                rs.getInt("recipient_id"),
                rs.getString("subject"),
                rs.getString("body"),
                rs.getString("status"),
                rs.getTimestamp("timestamp").toLocalDateTime()
            );
            sentMessages.add(message);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return sentMessages;
}

public List<Message> getDraftMessages(int senderId) {
    String sql = "SELECT * FROM messages WHERE sender_id = ? AND status = 'DRAFT'";
    List<Message> draftMessages = new ArrayList<>();

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, senderId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Message message = new Message(
                rs.getInt("id"),
                rs.getInt("sender_id"),
                rs.getInt("recipient_id"),
                rs.getString("subject"),
                rs.getString("body"),
                rs.getString("status"),
                rs.getTimestamp("timestamp").toLocalDateTime()
            );
            draftMessages.add(message);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return draftMessages;
}

public List<Message> getInboxMessages(int recipientId){
    // Code to retrieve all messages for a specific recipient from the database or file
    // Implementation details would go here
    String sql =  "SELECT m.*, u.email AS sender_email FROM messages m JOIN users u ON m.sender_id = u.id WHERE m.recipient_id = ? AND m.status = 'INBOX'";  
            List<Message> inboxMessages = new ArrayList<>();
            try(Connection conn = DBConnection.getConnection();                // Establish a connection to the database
                PreparedStatement stmt = conn. prepareStatement(sql)){       // Prepare the SQL statement for execution
                stmt.setInt(1, recipientId);                          // Set the recipientId parameter in the SQL statement
                ResultSet rs = stmt.executeQuery(); // Execute the SQL query to retrieve the inbox messages
                while (rs.next()) {

    String senderEmail = rs.getString("sender_email"); //  extra field

    Message message = new Message(
        rs.getInt("id"),
        rs.getInt("sender_id"),
        rs.getInt("recipient_id"),
        rs.getString("subject"),
        rs.getString("body"),
        rs.getString("status"),
        rs.getTimestamp("timestamp").toLocalDateTime()
    );

    //  temporarily attach email using a map OR simple list
    inboxMessages.add(message);

    // ALSO store email somewhere (see next step)
}
                 // Return the list of inbox messages for the specified recipient
            } catch (SQLException e) {
                e.printStackTrace(); // Handle any SQL exceptions that occur during the retrieval operation
            }
            return inboxMessages;
        }
        
    }
    


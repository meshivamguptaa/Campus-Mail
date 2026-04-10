package service;

import dao.MessageDAO;
import model.Message;            
import java.time.LocalDateTime;   // Import LocalDateTime for timestamping messages

public class MessageService {

    private MessageDAO messageDAO;            // Data Access Object for handling message-related database operations

    // Constructor to initialize the MessageService and its dependencies
    public MessageService() {
        messageDAO = new MessageDAO();                          // Initialize the MessageDAO to interact with the database for message operations
    }

    // Method to send a message, which creates a new Message object and saves it to the database
    public void sendMessage(int id, int senderId, int recipientId, String subject, String body, String status, LocalDateTime timestamp) {

        // Create a new Message object for the sender (SENT) and save it to the database
         Message sentMessage = new Message(
        id,
        senderId,
        recipientId,
        subject,
        body,
        "SENT",
        LocalDateTime.now()
    );

    messageDAO.saveMessage(sentMessage);

    // Receiver copy (INBOX)
    Message inboxMessage = new Message(
        id,
        senderId,
        recipientId,
        subject,
        body,
        "INBOX",
        LocalDateTime.now()
    );

    messageDAO.saveMessage(inboxMessage);
}

    }

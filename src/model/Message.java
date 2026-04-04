// This class represents a message in the mail client, containing details like sender, recipient, subject, body, and date.
package model;

import java.time.LocalDateTime;

public class Message {

    // Message properties
    private int id; // Unique identifier for the message, useful for database operations
    private int senderId;    // ID of the sender, can be used to link to a User entity in the database
    private int recipientId; // ID of the recipient, can be used to link to a User entity in the database
    private String subject;     
    private String body;
    private LocalDateTime timestamp; // Timestamp of when the message was sent or received
    private String status; // Status of the message (DRAFT, SENT, INBOX)

    // Constructor to initialize message properties
    public Message(int id, int senderId, int recipientId, String subject, String body, LocalDateTime timestamp) {
        this.id = id;                  // Set ID of the message
        this.senderId = senderId;       // Set senderId of the message
        this.recipientId = recipientId;  // Set recipient of the message
        this.subject = subject;          // Set subject of the message
        this.body = body;               // Set body of the message
        this.timestamp = timestamp;    // Set timestamp of the message
        this.status = "INBOX"; // Default status is INBOX, can be updated to DRAFT or SENT as needed
    }

    // Getter methods for message properties
    public int getId() {
        return id;                          // Return the ID of the message
    }

    public int getSenderID() {
        return senderId;                          // Return the senderId of the message
    }

    public int getRecipientID() {
        return recipientId;                       // Return the recipientId of the message
    }

    public String getSubject() {
        return subject;                 // Return the subject of the message
    }

    public String getBody() {
        return body;                    // Return the body of the message           
    }

    public String getStatus() {
        return status;              // Return the status of the message
    }

    public LocalDateTime getTimestamp() {
        return timestamp;            // Return the timestamp of the message   
    }
    
}

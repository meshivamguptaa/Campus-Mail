// This class represents a message in the mail client, containing details like sender, recipient, subject, body, and date.
package model;

import java.time.LocalDateTime;

public class Message {

    // Message properties
    private int id; // Unique identifier for the message, useful for database operations
    private String senderId;    // ID of the sender, can be used to link to a User entity in the database
    private String recipientId; // ID of the recipient, can be used to link to a User entity in the database
    private String subject;     
    private String body;
    private LocalDateTime timestamp; // Timestamp of when the message was sent or received
    
    public enum MessageStatus {
        DRAFT, SENT, INBOX           // Enum to represent the status of the message, useful for categorizing messages in the UI and database
    }
    private MessageStatus status; // Status of the message (DRAFT, SENT, INBOX)

    // Constructor to initialize message properties
    public Message(int id, String senderId, String recipientId, String subject, String body, LocalDateTime timestamp) {
        this.id = id;                  // Set ID of the message
        this.senderId = senderId;       // Set senderId of the message
        this.recipientId = recipientId;  // Set recipient of the message
        this.subject = subject;          // Set subject of the message
        this.body = body;               // Set body of the message
        this.timestamp = timestamp;    // Set timestamp of the message
        this.status = MessageStatus.INBOX; // Default status is INBOX, can be updated to DRAFT or SENT as needed
    }

    // Getter methods for message properties
    public int getId() {
        return id;                          // Return the ID of the message
    }

    public String getSender() {
        return senderId;                          // Return the senderId of the message
    }

    public String getRecipient() {
        return recipientId;                       // Return the recipientId of the message
    }

    public String getSubject() {
        return subject;                 // Return the subject of the message
    }

    public String getBody() {
        return body;                    // Return the body of the message           
    }

    public LocalDateTime getTimestamp() {
        return timestamp;            // Return the timestamp of the message   
    }
    public MessageStatus getStatus() {
        return status;              // Return the status of the message
    }

}

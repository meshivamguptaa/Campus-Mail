// This class represents a message in the mail client, containing details like sender, recipient, subject, body, and date.
package model;

public class Message {

    // Message properties
    private String sender;
    private String recipient;
    private String subject;
    private String body;
    private String date;

    // Constructor to initialize message properties
    public Message(String sender, String recipient, String subject, String body, String date) {
        this.sender = sender;       // Set sender of the message
        this.recipient = recipient; // Set recipient of the message
        this.subject = subject;     // Set subject of the message
        this.body = body;           // Set body of the message
        this.date = date;           // Set date of the message
    }

    // Getter methods for message properties
    public String getSender() {
        return sender;                          // Return the sender of the message
    }

    public String getRecipient() {
        return recipient;                       // Return the recipient of the message
    }

    public String getSubject() {
        return subject;                 // Return the subject of the message
    }

    public String getBody() {
        return body;                    // Return the body of the message           
    }

    public String getDate() {
        return date;            // Return the date of the message   
    }

}

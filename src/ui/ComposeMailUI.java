package ui;
import javax.swing.*;

import service.MessageService;


import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;  
import core.SessionManager;
    

public class ComposeMailUI extends JPanel {

    // UI components for composing mail
    JTextField toField;
    JTextField subjectField;
    JTextArea bodyArea;
    JButton attachmentBtn;
    JLabel attachmentLabel;
    JButton sendBtn;
    private File attachmentFile;           // Variable to store the selected attachment file reference
    private JPanel content;

    public ComposeMailUI(JPanel content) {
        this.content = content;
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(formPanel, BorderLayout.NORTH);
        // To field
        JLabel toLabel = new JLabel("To:");
        toField = new JTextField();
        formPanel.add(toLabel);
        formPanel.add(toField);

        // Subject field
        JLabel subjectLabel = new JLabel("Subject:");
        subjectField = new JTextField();
        formPanel.add(subjectLabel);
        formPanel.add(subjectField);

        // Body area
       bodyArea = new JTextArea();
       bodyArea.setLineWrap(true);
       bodyArea.setWrapStyleWord(true);

        add(new JScrollPane(bodyArea), BorderLayout.CENTER);

        // Attachment and Send buttons
        JPanel buttonPanel = new JPanel(new BorderLayout());              // Use BorderLayout for button panel to separate attachment and send buttons
        JPanel left= new JPanel(new FlowLayout(FlowLayout.LEFT));         // Left panel for attachment button and label
        attachmentBtn = new JButton("Add Attachment");               // Button to add attachment
        attachmentLabel = new JLabel("No file chosen");              // Label to show chosen file name
        sendBtn = new JButton("Send");
        left.add(attachmentBtn);                                          // Add attachment button to left panel
        left.add(attachmentLabel);                      // Add attachment label to left panel
        buttonPanel.add(left, BorderLayout.WEST);                          // Add left panel to button panel
        buttonPanel.add(sendBtn, BorderLayout.EAST);                        // Add send button to right side of button panel
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons can be added here
        attachmentBtn.addActionListener(e -> {
            // Handle attachment logic (e.g., open file chooser)
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);                                    // Open file chooser dialog
            if (result == JFileChooser.APPROVE_OPTION) {                                // If user selects a file
                attachmentLabel.setText(fileChooser.getSelectedFile().getName());           // Update label to show selected file name

                // store file reference
                attachmentFile = fileChooser.getSelectedFile();                        // Store the selected file reference for later use (e.g., when sending the email)
            }
        });
        sendBtn.addActionListener(e -> {

    try {
        int senderId = SessionManager.getCurrentUser().getId(); // Get the sender's user ID from the session manager to associate the sent message with the currently logged-in user
        int receiverId = Integer.parseInt(toField.getText());

        String subject = subjectField.getText();
        String body = bodyArea.getText();

        // basic validation
        if (subject.isEmpty() || body.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Subject and Body cannot be empty");
            return;
        }

        // call service
        MessageService service = new MessageService();
        service.sendMessage(0, senderId, receiverId, subject, body, "SENT", LocalDateTime.now());
        
        JOptionPane.showMessageDialog(this, "Message Sent!");

        // clear fields
        toField.setText("");
        subjectField.setText("");
        bodyArea.setText("");
        attachmentLabel.setText("No file chosen");
        attachmentFile = null;

        

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid Receiver ID");
    }
    // After sending the message, navigate back to the SentPanel to show the sent message in the list
        content.removeAll();
        content.add(new SentPanel(content), BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
});


    
    }
}
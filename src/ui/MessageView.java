package ui;

import javax.swing.*;
import java.awt.*;
import model.Message;

public class MessageView extends JPanel {

    private JLabel senderLabel;
    private JLabel recipientLabel;
    private JLabel subjectLabel;
    private JLabel timestampLabel;
    private JTextArea bodyTextArea;

    public MessageView() {

        setLayout(new BorderLayout(10, 10));

        senderLabel = new JLabel("From: ");
        recipientLabel = new JLabel("To: ");
        subjectLabel = new JLabel("Subject: ");
        timestampLabel = new JLabel("Received on: ");

        bodyTextArea = new JTextArea();
        bodyTextArea.setEditable(false);
        bodyTextArea.setLineWrap(true);
        bodyTextArea.setWrapStyleWord(true);

        JPanel headerPanel = new JPanel(new GridLayout(4, 1));
        headerPanel.add(senderLabel);
        headerPanel.add(recipientLabel);
        headerPanel.add(subjectLabel);
        headerPanel.add(timestampLabel);

        add(headerPanel, BorderLayout.NORTH);
        add(new JScrollPane(bodyTextArea), BorderLayout.CENTER);
    }

    public void setMessage(Message message) {
        senderLabel.setText("From: " + message.getSenderId());
        recipientLabel.setText("To: " + message.getReceiverId());
        subjectLabel.setText("Subject: " + message.getSubject());
        bodyTextArea.setText(message.getBody());
        timestampLabel.setText("Received on: " + message.getTimestamp().toLocalDate());
    }
}
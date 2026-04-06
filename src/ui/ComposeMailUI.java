package ui;
import javax.swing.*;
import java.awt.*;
import java.io.File;  
    

public class ComposeMailUI extends JPanel {

    // UI components for composing mail
    JTextField toField;
    JTextField subjectField;
    JTextArea bodyArea;
    JButton attachmentBtn;
    JLabel attachmentLabel;
    JButton sendBtn;
    private File attachmentFile;

    public ComposeMailUI() {
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
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel left= new JPanel(new FlowLayout(FlowLayout.LEFT));
        attachmentBtn = new JButton("Add Attachment");
        attachmentLabel = new JLabel("No file chosen");
        sendBtn = new JButton("Send");
        left.add(attachmentBtn);
        left.add(attachmentLabel);
        buttonPanel.add(left, BorderLayout.WEST);
        buttonPanel.add(sendBtn, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons can be added here
        attachmentBtn.addActionListener(e -> {
            // Handle attachment logic (e.g., open file chooser)
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                attachmentLabel.setText(fileChooser.getSelectedFile().getName());

                // store file reference
                attachmentFile = fileChooser.getSelectedFile();
            }
        });
    }
}
package ui;
import javax.swing.*;
import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;       

public class ComposeMailUI extends JPanel {

    public ComposeMailUI() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Compose Mail functionality coming soon!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.CENTER);
    }
}
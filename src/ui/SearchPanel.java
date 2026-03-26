package ui;
import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    public SearchPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Search functionality coming soon!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.CENTER);
    }
}
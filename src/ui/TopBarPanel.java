package ui;

import javax.swing.*;
import java.awt.*;
import ui.DashboardUI;

public class TopBarPanel extends JPanel {

    private JPanel content;
    public TopBarPanel(JPanel content) {
        this.content = content;

        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("CampusMail Client", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(33, 37, 41)); // Set title color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10)); // Add some right padding
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align title to the left
        titleLabel.setVerticalAlignment(SwingConstants.CENTER); // Center title vertically
        titleLabel.setPreferredSize(new Dimension(200, 30)); // Set preferred size for better layout
        add(titleLabel, BorderLayout.WEST);

        // Search field
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 35));

        // Right side panel
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton refreshBtn = new JButton("Refresh");
        JButton profileBtn = new JButton("User");

        refreshBtn.addActionListener(e -> {
            // Implement refresh functionality here
            content.removeAll();
            content.add(new InboxPanel(content), BorderLayout.CENTER);
            content.revalidate();
            content.repaint();
            // For now, just show a message
            JOptionPane.showMessageDialog(this, "Refresh clicked!");
        });
        rightPanel.add(refreshBtn);
        rightPanel.add(profileBtn);

        add(searchField, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}
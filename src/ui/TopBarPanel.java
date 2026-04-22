package ui;

import javax.swing.*;

import core.SessionManager;
import model.User;

import java.awt.*;

public class TopBarPanel extends JPanel {


    private JButton userBtn;    // Made userBtn a class member to access it in the action listener
    private Runnable onLogout; // Callback to notify MainFrame about logout

    public TopBarPanel(Runnable onLogout) {
        this.onLogout = onLogout;

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
        userBtn = new JButton("User");
        userBtn.addActionListener(e -> {
            User user = SessionManager.getCurrentUser();

            JPopupMenu menu = new JPopupMenu();

            // Show current user
            JMenuItem userInfo = new JMenuItem("Logged in as: " + user.getEmailId());
            userInfo.setEnabled(false);

            // Logout option
            JMenuItem logoutItem = new JMenuItem("Logout");

            logoutItem.addActionListener(ev -> {
                if (onLogout != null) {
                    onLogout.run(); // 🔥 THIS triggers Dashboard logic
                }
            });

            menu.add(userInfo);
            menu.addSeparator();
            menu.add(logoutItem);

            menu.show(userBtn, 0, userBtn.getHeight());
        });

        rightPanel.add(refreshBtn);
        rightPanel.add(userBtn);

        add(searchField, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}
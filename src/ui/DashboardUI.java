package ui;
import javax.swing.*;
import java.awt.*;

public class DashboardUI extends JPanel {

    public DashboardUI() {

        setLayout(new BorderLayout()); // Use BorderLayout for main panel

        // Sidebar
        JPanel sidebar = new JPanel(); // Sidebar panel
        sidebar.setPreferredSize(new Dimension(200, 0)); // Set width, height will adjust automatically
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking

        // Logo
        JLabel logo = new JLabel("Campus Mail"); // Placeholder for logo
        logo.setFont(new Font("Arial", Font.BOLD, 18)); // Set font for logo
        logo.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the logo horizontally

        sidebar.add(Box.createVerticalStrut(20)); // Add vertical space at the top
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(30)); // Add space between logo and buttons
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));  // Add padding around sidebar

        // Compose Button
       JButton compose = new JButton("Compose"); // Create compose button

       compose.setAlignmentX(Component.CENTER_ALIGNMENT); //    Center the button horizontally
       compose.setMaximumSize(new Dimension(Integer.MAX_VALUE,40)); // Set maximum size to make it look better
       compose.setBackground(Color.WHITE); // Set background color
       compose.setFocusPainted(false); // Remove focus border
       compose.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Add border for better appearance

        sidebar.add(Box.createVerticalStrut(30)); // Add space between logo and compose button
        sidebar.add(compose); // Add compose button to sidebar
        sidebar.add(Box.createVerticalStrut(20)); // Add space between compose button and mail sections

        // Mail sections
        JButton inbox = new JButton("Inbox");  // Create inbox button

        inbox.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        inbox.setMaximumSize(new Dimension(Integer.MAX_VALUE,40)); // Allow button to expand horizontally
        inbox.setFocusPainted(false);  // Remove focus border
        inbox.setBorderPainted(false); // Remove border for a cleaner look
        inbox.setBackground(new Color(248,249,250));  //    Set background color to match sidebar

        JButton sentBtn = new JButton("Sent");  // Create sent button

        sentBtn.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the button horizontally
        sentBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));  // Allow button to expand horizontally
        sentBtn.setFocusPainted(false);  // Remove focus border
        sentBtn.setBorderPainted(false);  // Remove border for a cleaner look
        sentBtn.setBackground(new Color(248,249,250));  // Set background color to match sidebar

        JButton draftBtn = new JButton("Draft");               // Create draft button
        draftBtn.setAlignmentX(Component.CENTER_ALIGNMENT);         // Center the button horizontally
        draftBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));       // Allow button to expand horizontally
        draftBtn.setFocusPainted(false);                        // Remove focus border
        draftBtn.setBorderPainted(false);                       // Remove border for a cleaner look
        draftBtn.setBackground(new Color(248,249,250));         // Set background color to match sidebar

        sidebar.add(inbox);
        sidebar.add(sentBtn);
        sidebar.add(draftBtn);
        sidebar.add(Box.createVerticalStrut(15));
        //sidebar.setBackground(new Color(248,249,250));

        // Content area
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);

        content.add(new JLabel("Mail Content Area"));

        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);
    }
}
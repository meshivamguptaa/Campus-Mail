package ui;

import javax.swing.*;
import java.awt.*;
import service.AuthService;
import model.User;
import core.SessionManager;

public class LoginUI extends JPanel {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;
    private JPanel contentPanel;

    public LoginUI(JPanel contentPanel) {
        this.contentPanel = contentPanel;

        //  Center everything
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 242, 245));

        //  Card panel
        JPanel card = new JPanel();
        card.setLayout(new GridLayout(5, 1, 10, 10));
        card.setPreferredSize(new Dimension(350, 250));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        card.setBackground(Color.WHITE);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        // Buttons
        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");

        //  Button styling
        loginBtn.setFocusPainted(false);
        registerBtn.setFocusPainted(false);

        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);

        registerBtn.setBackground(Color.WHITE);
        registerBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Add components
        card.add(emailLabel);
        card.add(emailField);
        card.add(passwordLabel);
        card.add(passwordField);

        // Buttons side by side
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(loginBtn);
        btnPanel.add(registerBtn);

        card.add(btnPanel);

        // Add card to center
        add(card);

        //  LOGIN LOGIC (unchanged)
        loginBtn.addActionListener(e -> {
            AuthService service = new AuthService();

            User user = service.login(
                emailField.getText(),
                new String(passwordField.getPassword())
            );

            if (user != null) {
                SessionManager.setCurrentUser(user);

                contentPanel.removeAll();
                contentPanel.add(new DashboardUI(contentPanel), BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();

            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        //  REGISTER NAVIGATION (unchanged)
        registerBtn.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(new RegisterUI(contentPanel), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    }
}
package ui;

import javax.swing.*;

import core.SessionManager;
import model.User;

import java.awt.*;
import service.AuthService;

public class RegisterUI extends JPanel {

    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton registerBtn, backBtn;
    private JPanel contentPanel;

    public RegisterUI(JPanel contentPanel) {
        this.contentPanel = contentPanel;

        //  Center layout
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 242, 245));

        //  Card panel
        JPanel card = new JPanel();
        card.setLayout(new GridLayout(6, 1, 10, 10));
        card.setPreferredSize(new Dimension(350, 300));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        card.setBackground(Color.WHITE);

        // Fields
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        // Buttons
        registerBtn = new JButton("Register");
        backBtn = new JButton("Back");

        //  Styling
        registerBtn.setFocusPainted(false);
        backBtn.setFocusPainted(false);

        registerBtn.setBackground(Color.BLACK);
        registerBtn.setForeground(Color.WHITE);

        backBtn.setBackground(Color.WHITE);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Add components
        card.add(nameLabel);
        card.add(nameField);

        card.add(emailLabel);
        card.add(emailField);

        card.add(passwordLabel);
        card.add(passwordField);

        // Button panel
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(registerBtn);
        btnPanel.add(backBtn);

        card.add(btnPanel);

        // Add card center
        add(card);

        //  REGISTER LOGIC (same)
        registerBtn.addActionListener(e -> {
            AuthService service = new AuthService();

            boolean success = service.register(
                nameField.getText(),
                emailField.getText(),
                new String(passwordField.getPassword())
            );

            if (success) {
                JOptionPane.showMessageDialog(this, "Registered!");

                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                User user = service.login(email, password);
                SessionManager.setCurrentUser(user);

                contentPanel.removeAll();
                contentPanel.add(new DashboardUI(contentPanel), BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();

            } else {
                JOptionPane.showMessageDialog(this, "Email already exists");
            }
        });

        //  BACK BUTTON (same)
        backBtn.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(new LoginUI(contentPanel), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    }
}
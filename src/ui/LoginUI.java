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

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");

        add(loginBtn);
        add(registerBtn);

        // 🔥 Login
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

        // Go to Register
        registerBtn.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(new RegisterUI(contentPanel), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    }
}
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

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        registerBtn = new JButton("Register");
        backBtn = new JButton("Back");

        add(registerBtn);
        add(backBtn);

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

        

                User user = service.login(email, password); // Auto-login after registration
                SessionManager.setCurrentUser(user);

                contentPanel.removeAll();
                contentPanel.add(new DashboardUI(contentPanel));
                contentPanel.revalidate();
                contentPanel.repaint();

            } else {
                JOptionPane.showMessageDialog(this, "Email exists");
            }

            contentPanel.revalidate();
            contentPanel.repaint();
        });

        backBtn.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(new LoginUI(contentPanel), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    }
}
package ui;

import javax.swing.*;
import java.awt.*;
import service.AuthService;
import model.User;
import core.SessionManager;

public class RegisterUI extends JFrame {

    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton registerBtn, backBtn;

    public RegisterUI() {
        setTitle("Register");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        registerBtn = new JButton("Register");
        backBtn = new JButton("Back to Login");

        panel.add(registerBtn);
        panel.add(backBtn);

        add(panel);

        // Register Action
        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            AuthService service = new AuthService();
            boolean success = service.register(name, email, password);

            if (success) {
                JOptionPane.showMessageDialog(this, "Registered Successfully!");

                //  Auto-login after registration
                User user = service.login(email, password);
                SessionManager.setCurrentUser(user);

                new MainFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email already exists");
            }
        });

        // Back to Login
        backBtn.addActionListener(e -> {
            new LoginUI();
            dispose();
        });

        setVisible(true);
    }
}
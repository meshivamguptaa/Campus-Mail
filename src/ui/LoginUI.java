package ui;

import javax.swing.*;
import java.awt.*;
import service.AuthService;
import model.User;
import core.SessionManager;

public class LoginUI extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;

    public LoginUI() {
        setTitle("Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");

        panel.add(loginBtn);
        panel.add(registerBtn);

        add(panel);

        //  Login Action
        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            AuthService service = new AuthService();
            User user = service.login(email, password);

            if (user != null) {
                SessionManager.setCurrentUser(user);

                new MainFrame(); // your dashboard entry
                dispose(); // close login window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password");
            }
        });

        //  Go to Register
        registerBtn.addActionListener(e -> {
            new RegisterUI();
            dispose();
        });

        setVisible(true);
    }
}
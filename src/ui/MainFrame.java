package ui;

import javax.swing.*;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {

    private JPanel contentPanel;

    public MainFrame() {
        setTitle("Mail Client");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel);

        // FIRST SCREEN
        contentPanel.add(new LoginUI(contentPanel), BorderLayout.CENTER);

        setVisible(true);
    }
}
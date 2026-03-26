package ui;

import java.awt.Color;

import javax.swing.*;



public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Campus Mail Client");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new DashboardUI());
        
        setVisible(true);
    }
}
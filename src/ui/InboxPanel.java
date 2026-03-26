package ui;

import javax.swing.*;
import java.awt.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class InboxPanel extends JPanel {

    private JTable emailTable;
    private DefaultTableModel tableModel;

    public InboxPanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(" Inbox", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));   
        //titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding around title
        add(titleLabel, BorderLayout.NORTH);


        // Table model with columns: Sender, Subject, Date
        tableModel = new DefaultTableModel(
             new Object[]{"Sender", "Subject", "Date"}, 0
            ) {
         public boolean isCellEditable(int row, int column) {        // Make table cells non-editable
            return false;
         }
        };
        emailTable = new JTable(tableModel);             // Create table with model
        emailTable.setFillsViewportHeight(true); // Make table fill the panel

        // Customize table header
        JTableHeader header = emailTable.getTableHeader();
        header.setBackground(new Color(250,250,250)); // Set header background color
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBorder(BorderFactory.createEmptyBorder());

        // Add some dummy data for testing
        tableModel.addRow(new Object[]{"David", "Weekly Report", "2024-06-04"});
        tableModel.addRow(new Object[]{"Emma", "Team Sync", "2024-06-05"});
        tableModel.addRow(new Object[]{"Frank", "Code Review", "2024-06-06"});
        tableModel.addRow(new Object[]{"Grace", "Client Meeting", "2024-06-07"});
        tableModel.addRow(new Object[]{"Hannah", "Budget Discussion", "2024-06-08"});
        tableModel.addRow(new Object[]{"Ian", "Design Feedback", "2024-06-09"});
        tableModel.addRow(new Object[]{"Jack", "Sprint Planning", "2024-06-10"});
        tableModel.addRow(new Object[]{"Karen", "HR Update", "2024-06-11"});
        tableModel.addRow(new Object[]{"Leo", "Deployment Notice", "2024-06-12"});
        tableModel.addRow(new Object[]{"Mia", "Training Session", "2024-06-13"});

        tableModel.addRow(new Object[]{"Nina", "Bug Fixes", "2024-06-14"});
        tableModel.addRow(new Object[]{"Oscar", "Performance Review", "2024-06-15"});
        tableModel.addRow(new Object[]{"Paul", "New Feature", "2024-06-16"});
        tableModel.addRow(new Object[]{"Quinn", "System Update", "2024-06-17"});
        tableModel.addRow(new Object[]{"Ryan", "Database Migration", "2024-06-18"});
        tableModel.addRow(new Object[]{"Sophia", "Security Check", "2024-06-19"});
        tableModel.addRow(new Object[]{"Tom", "Integration Testing", "2024-06-20"});
        tableModel.addRow(new Object[]{"Uma", "UI Improvements", "2024-06-21"});
        tableModel.addRow(new Object[]{"Victor", "Backend Refactor", "2024-06-22"});
        tableModel.addRow(new Object[]{"Wendy", "API Review", "2024-06-23"});

        tableModel.addRow(new Object[]{"Xavier", "Documentation Update", "2024-06-24"});
        tableModel.addRow(new Object[]{"Yara", "Release Notes", "2024-06-25"});
        tableModel.addRow(new Object[]{"Zane", "Hotfix Patch", "2024-06-26"});
        tableModel.addRow(new Object[]{"Aarav", "Team Lunch", "2024-06-27"});
        tableModel.addRow(new Object[]{"Isha", "Client Feedback", "2024-06-28"});
        tableModel.addRow(new Object[]{"Rohan", "Project Kickoff", "2024-06-29"});
        tableModel.addRow(new Object[]{"Neha", "Design Review", "2024-06-30"});
        tableModel.addRow(new Object[]{"Arjun", "Bug Triage", "2024-07-01"});
        tableModel.addRow(new Object[]{"Pooja", "Feature Discussion", "2024-07-02"});
        tableModel.addRow(new Object[]{"Karan", "System Testing", "2024-07-03"});

        tableModel.addRow(new Object[]{"Meera", "Weekly Sync", "2024-07-04"});
        tableModel.addRow(new Object[]{"Rahul", "Client Call", "2024-07-05"});
        tableModel.addRow(new Object[]{"Sneha", "UI Fixes", "2024-07-06"});
        tableModel.addRow(new Object[]{"Amit", "Backend Update", "2024-07-07"});
        tableModel.addRow(new Object[]{"Divya", "Team Meeting", "2024-07-08"});
        tableModel.addRow(new Object[]{"Vikram", "Performance Tuning", "2024-07-09"});
        tableModel.addRow(new Object[]{"Anjali", "Design Sprint", "2024-07-10"});
        tableModel.addRow(new Object[]{"Manish", "Security Audit", "2024-07-11"});
        tableModel.addRow(new Object[]{"Kavya", "Project Demo", "2024-07-12"});
        tableModel.addRow(new Object[]{"Suresh", "Code Merge", "2024-07-13"});

        tableModel.addRow(new Object[]{"Ritika", "Bug Report", "2024-07-14"});
        tableModel.addRow(new Object[]{"Deepak", "Feature Testing", "2024-07-15"});
        tableModel.addRow(new Object[]{"Priya", "Release Planning", "2024-07-16"});
        tableModel.addRow(new Object[]{"Nikhil", "Deployment Check", "2024-07-17"});
        tableModel.addRow(new Object[]{"Swati", "User Feedback", "2024-07-18"});
        tableModel.addRow(new Object[]{"Gaurav", "Server Maintenance", "2024-07-19"});
        tableModel.addRow(new Object[]{"Tanya", "Team Discussion", "2024-07-20"});
        tableModel.addRow(new Object[]{"Harsh", "UI Testing", "2024-07-21"});
        tableModel.addRow(new Object[]{"Simran", "API Integration", "2024-07-22"});
        tableModel.addRow(new Object[]{"Varun", "Final Review", "2024-07-23"});

        emailTable.setRowHeight(45); // Set row height for better appearance

        
        

        // Set column widths for better appearance
        emailTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        emailTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        emailTable.getColumnModel().getColumn(2).setPreferredWidth(100);

        // Set selection mode to single selection
        emailTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Remove grid lines for a cleaner look
        emailTable.setShowGrid(false);
        emailTable.setIntercellSpacing(new Dimension(0, 0));

        // Set background color for better appearance
        emailTable.setBackground(Color.WHITE);

        // Set selection colors
        emailTable.setSelectionBackground(new Color(220,230,241));
        emailTable.setSelectionForeground(Color.BLACK);

        // Add padding to cells for better appearance
        emailTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
         @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

            JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

                 label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                return label;
                }
        });

        add(new JScrollPane(emailTable), BorderLayout.CENTER); // Add table with scroll pane
    }
}
package ui;

import javax.swing.*;
import java.awt.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.MessageDAO;

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

        MessageDAO messageDAO = new MessageDAO(); // Create instance of MessageDAO to retrieve messages
        List<Message> inboxMessages = messageDAO.getInboxMessages(1); // Retrieve inbox messages for user with ID 1 (replace with actual user ID)

        for(Message message : inboxMessages) { // Loop through retrieved messages and add them to the table model
            tableModel.addRow(new Object[]{
                message.getSenderID(), // Placeholder for sender name, replace with actual sender name retrieval
                message.getSubject(),
                message.getTimestamp().toLocalDate().toString() // Format timestamp to show only date
            });
        }

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
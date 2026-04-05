package ui;

import javax.swing.*;
import java.awt.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ArrayList;

import model.Message;
import dao.MessageDAO;
import ui.DashboardUI;
import ui.MessageView;

public class InboxPanel extends JPanel {

    private JTable emailTable;
    private DefaultTableModel tableModel;
    private JPanel content;

    public InboxPanel(JPanel content) {
        this.content = content;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(" Inbox", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));   
        //titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding around title
        add(titleLabel, BorderLayout.NORTH);


        // Table model with columns: Sender, Subject, Date
        tableModel = new DefaultTableModel(
             new Object[]{"ID","Sender", "Subject", "Date"}, 0
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
                message.getID(), 
                message.getSenderID(), // Placeholder for sender name, replace with actual sender name retrieval
                message.getSubject(),
                message.getTimestamp().toLocalDate() // Format timestamp to show only date
            });
        }
        emailTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Check for double-click
                    int selectedRow = emailTable.getSelectedRow(); // Get selected row index
                    if (selectedRow != -1) { // Ensure a row is selected
                        int messageId = (int) tableModel.getValueAt(selectedRow, 0); // Get message ID from the first column
                        MessageDAO messageDAO = new MessageDAO(); // Create instance of MessageDAO to retrieve message details
                        Message message = messageDAO.getMessageById(messageId); // Retrieve message details using the message ID

                        // Open a new window or dialog to display the full email content using the message ID
                        if(message != null) {
                            MessageView messageView = new MessageView(); // Create instance of MessageView to display message details
                            messageView.setMessage(message); // Pass the message object to the view to display its details
                            
                            // Clear existing content and show the message view in the main panel
                            content.removeAll(); // Clear existing content in the main panel
                            content.add(messageView, BorderLayout.CENTER); // Add the message view to the main panel
                            content.revalidate(); // Refresh the main panel to show the new content
                            content.repaint(); // Repaint the main panel to ensure the new content is displayed properly
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to retrieve message details.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                      }
                    }
                }
            }
        );
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
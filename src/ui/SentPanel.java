package ui;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import core.SessionManager;

import java.util.List;
import java.util.ArrayList;

import model.Message;
import dao.MessageDAO;
import dao.UserDAO;

public class SentPanel extends JPanel {

    private JTable sentTable;
    private DefaultTableModel tableModel;
    private JPanel content;

    public SentPanel(JPanel content) {
        this.content = content;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(" Sent", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        //titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding around title
        add(titleLabel, BorderLayout.NORTH);

        // Table model with columns: Recipient, Subject, Date
        tableModel = new DefaultTableModel(
             new Object[]{"ID", "Recipient", "Subject", "Date"}, 0
            ) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
        };

        sentTable = new JTable(tableModel);
        sentTable.getColumnModel().getColumn(0).setMinWidth(0);
        sentTable.getColumnModel().getColumn(0).setMaxWidth(0);
        sentTable.getColumnModel().getColumn(0).setWidth(0);
        sentTable.setFillsViewportHeight(true); // Make table fill the panel

        // Customize table header
        JTableHeader header = sentTable.getTableHeader();
        header.setBackground(new Color(250,250,250)); // Set header background color
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBorder(BorderFactory.createEmptyBorder());

        // Retrieve sent messages from database and populate table
        MessageDAO messageDAO = new MessageDAO();
        List<Message> sentMessages = messageDAO.getSentMessages(SessionManager.getCurrentUser().getId());

        UserDAO userDAO = new UserDAO();

for (Message message : sentMessages) {

    String receiverEmail = userDAO
        .getUserById(message.getRecipientID())
        .getEmail();

    tableModel.addRow(new Object[]{
        message.getId(),        // keep ID (hidden)
        receiverEmail,          // show email
        message.getSubject(),
        message.getTimestamp().toLocalDate()
    });
}

        sentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    int selectedRow = sentTable.getSelectedRow();

                        if (selectedRow != -1) {

                             int messageId = (int) tableModel.getValueAt(selectedRow, 0);

                             MessageDAO dao = new MessageDAO();
                             Message message = dao.getMessageById(messageId);

                                 if (message != null) {

                                    MessageView view = new MessageView();
                                    view.setMessage(message);

                                    content.removeAll();
                                    content.add(view, BorderLayout.CENTER);
                                    content.revalidate();
                                    content.repaint();
                                 }
                     }
            }
        }
});

        // Make table non-editable
        
        // Set column widths for better appearance
        sentTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        sentTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        sentTable.getColumnModel().getColumn(2).setPreferredWidth(100);

        // Set selection mode to single selection
        sentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Remove grid lines for a cleaner look
        sentTable.setShowGrid(false);
        sentTable.setIntercellSpacing(new Dimension(0, 0));

        // Set background color for better appearance
        sentTable.setBackground(Color.WHITE);

        // Set selection colors
         sentTable.setSelectionBackground(new Color(220,230,241));
         sentTable.setSelectionForeground(Color.BLACK);

         // Add padding to cells for better appearance
         sentTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
          @Override
         public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
             Component label = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
             setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding to cells
             return label;
         }  
        });
    
        add(new JScrollPane(sentTable), BorderLayout.CENTER); // Add table with scroll panel
    }
}


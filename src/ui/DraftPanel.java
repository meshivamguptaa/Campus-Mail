package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import core.SessionManager;
import dao.MessageDAO;
import model.Message;

public class DraftPanel extends JPanel {

    private JTable draftTable;
    private DefaultTableModel tableModel;
    private JPanel content;

    public DraftPanel(JPanel content) {
        this.content = content;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(" Drafts", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        //titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding around title
        add(titleLabel, BorderLayout.NORTH);

        // Table model with columns: Recipient, Subject, Date
        tableModel = new DefaultTableModel(
             new Object[]{"Recipient", "Subject", "Date"}, 0
            ) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
        };
        draftTable = new JTable(tableModel);
        draftTable.setFillsViewportHeight(true); // Make table fill the panel

        // Customize table header
        JTableHeader header = draftTable.getTableHeader();
        header.setBackground(new Color(250,250,250)); // Set header background color
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBorder(BorderFactory.createEmptyBorder());

        
        //Retrieve draft messages from database and populate table
        MessageDAO messageDAO = new MessageDAO();
        List<Message> draftMessages = messageDAO.getDraftMessages(SessionManager.getCurrentUser().getId()); // Replace with actual user ID
        for (Message message : draftMessages) {
            tableModel.addRow(new Object[]{
            message.getRecipientEmail(),
            message.getSubject(),
            message.getTimestamp().toLocalDate()
        });
        }

        draftTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    int selectedRow = draftTable.getSelectedRow();

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


        draftTable.setRowHeight(45); // Set row height for better appearance

        // Make table non-editable
        

        // Set column widths for better appearance
        draftTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        draftTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        draftTable.getColumnModel().getColumn(2).setPreferredWidth(100);

        // Set selection mode to single selection
        draftTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Remove grid lines for a cleaner look
        draftTable.setShowGrid(false);
        draftTable.setIntercellSpacing(new Dimension(0, 0));

        // Set background color for better appearance
        draftTable.setBackground(Color.WHITE);

        // Set selection colors
         draftTable.setSelectionBackground(new Color(220,230,241));
         draftTable.setSelectionForeground(Color.BLACK);

         // Add padding to cells for better appearance
         draftTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
                    add(new JScrollPane(draftTable), BorderLayout.CENTER); // Add table with scroll panel
                }
            }
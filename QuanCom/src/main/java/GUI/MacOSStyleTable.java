package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;

public class MacOSStyleTable extends JTable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MacOSStyleTable(DefaultTableModel model) {
        super(model);

        // Tạo renderer cho header
        TableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setOpaque(true);
                label.setBackground(new Color(0x007AFF));
                label.setForeground(Color.WHITE);
                label.setFont(label.getFont().deriveFont(Font.BOLD));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return label;
            }
        };

        // Thiết lập renderer cho các header
        for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
            getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        setShowGrid(false);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component comp = super.prepareRenderer(renderer, row, column);

        // Tùy chỉnh giao diện của cell
        if (comp instanceof JComponent) {
            JComponent jc = (JComponent) comp;
            if (!isCellSelected(row, column)) {
                if (row % 2 == 0) {
                    jc.setBackground(Color.WHITE);
                } else {
                    jc.setBackground(new Color(0xF5F5F5));
                }
            }
        }

        return comp;
    }

//    public static void main(String[] args) {
//        // Tạo frame
//        JFrame frame = new JFrame("MacOS Style Table");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Tạo table model và thêm dữ liệu
//        DefaultTableModel tableModel = new DefaultTableModel();
//        tableModel.addColumn("ID");
//        tableModel.addColumn("Name");
//        tableModel.addColumn("Email");
//        tableModel.addRow(new Object[]{"1", "John Doe", "john.doe@example.com"});
//        tableModel.addRow(new Object[]{"2", "Jane Smith", "jane.smith@example.com"});
//        tableModel.addRow(new Object[]{"3", "Bob Johnson", "bob.johnson@example.com"});
//
//        // Tạo bảng với giao diện theme MacOS
//        MacOSStyleTable table = new MacOSStyleTable(tableModel);
//
//        // Tạo scroll pane và đặt table vào scroll pane
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        // Đặt scroll pane vào frame
//        frame.getContentPane().add(scrollPane);
//
//        // Thiết lập kích thước frame và hiển thị
//        frame.setSize(400, 300);
//        frame.setLocationRelativeTo(null); // Hiển thị frame ở giữa màn hình
//        frame.setVisible(true);
//    }
}
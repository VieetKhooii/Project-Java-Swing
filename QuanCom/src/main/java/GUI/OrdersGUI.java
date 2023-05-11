package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.json.ParseException;
import model.DetailOrder;
import model.Orders;
import model.ReceivedNote;
import service.DetailOrderService;
import service.OrderService;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.util.List;
import com.toedter.calendar.JDateChooser;

public class OrdersGUI extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel ordersListPanel;
    private JTable ordersTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane ordersScrollPane;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel searchPanel;
    private JPanel btnField;
    static JButton viewBtn;
    static JButton createBtn;
    static JButton updateBtn;
    static JButton delBtn;
    public static Orders orderIdStatic = new Orders();
    private JTextField searchTxt;
    private JTextField priceFrom;
    private JTextField priceTo;
    private JComboBox<String> sortCbB;
    private JDateChooser dateFrom;
    private JDateChooser dateTo;
    private JComboBox<String> searchCbB;
    OrderService orderService = new OrderService();
    List<Orders> ordersList = orderService.displayOrders();
    /**
     * Create the panel.
     */
    public OrdersGUI() {
        init();
    }
    private void init() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1080, 700));

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        //End


        // Panel table
        contentField = new JPanel(null);

        this.add(contentField, BorderLayout.CENTER);

        //Panel table nhan vien
        ordersListPanel = new JPanel(null);
        ordersListPanel.setBackground(new Color(0, 0, 0, 80));
        ordersListPanel.setBounds(0, 50, 800, 650);

        contentField.add(ordersListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Mã nhân viên", "Ngày tạo", "Tổng tiền"}, 0);
        ordersTable = new MacOSStyleTable(detailTableModel);
        ordersTable.setFont(new Font("Arial", Font.PLAIN, 14));
        ordersTable.setDefaultRenderer(String.class, centerRenderer);
        ordersTable.setRowHeight(30);

        for(int i = 0; i < 4; i++) {
            ordersTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        ListSelectionModel listSelectionModel = ordersTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = ordersTable.getSelectedRow();
                if (row >= 0){
                    viewBtn.setEnabled(true);
                    int id = Integer.parseInt(detailTableModel.getValueAt(row, 0).toString());
                    orderIdStatic.setId(id);

                    GiaoDien.taoDon.idOrderTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    GiaoDien.taoDon.idStaffCreateOrderTxt.setText(detailTableModel.getValueAt(row, 1).toString());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = dateFormat.parse(detailTableModel.getValueAt(row, 2).toString());
                    } catch (ParseException | java.text.ParseException e1) {
                        e1.printStackTrace();
                    }
                    GiaoDien.taoDon.orderDateChooser.setDate(date);

                    GiaoDien.taoDon.totalPriceOrderTxt.setText(detailTableModel.getValueAt(row, 3).toString());
                }
            }
        });

        ordersScrollPane = new CustomScrollPane(ordersTable);
        ordersScrollPane.setBounds(5, 5, 790, 640);
        ordersListPanel.add(ordersScrollPane);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel staffLabel = new JLabel("DANH SÁCH HÓA ĐƠN");
        staffLabel.setForeground(new Color(0x007AFF));
        staffLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(staffLabel);
        staffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staffLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        searchPanel.setBounds(800, 50, 280, 377);
        contentField.add(searchPanel);
        searchPanel.setLayout(null);

        JLabel lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(80, 0, 120, 40);
        searchPanel.add(lblTmKim);

        searchCbB = new JComboBox<String>();
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã hóa đơn", "Mã nhân viên"}));
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setBounds(10, 51, 260, 40);
        searchPanel.add(searchCbB);

        JLabel lblNhp = new JLabel("Nhập mã");
        lblNhp.setFont(new Font("Arial", Font.BOLD, 13));
        lblNhp.setBounds(10, 98, 80, 40);
        searchPanel.add(lblNhp);

        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 16));
        searchTxt.setColumns(10);
        searchTxt.setBounds(100, 98, 170, 40);
        searchPanel.add(searchTxt);

        JLabel lblNewLabel = new JLabel("Tổng giá");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(10, 149, 80, 40);
        searchPanel.add(lblNewLabel);

        priceFrom = new JTextField();
        priceFrom.setFont(new Font("Arial", Font.PLAIN, 13));
        priceFrom.setColumns(10);
        priceFrom.setBounds(100, 149, 80, 40);
        searchPanel.add(priceFrom);

        priceTo = new JTextField();
        priceTo.setFont(new Font("Arial", Font.PLAIN, 13));
        priceTo.setColumns(10);
        priceTo.setBounds(190, 149, 80, 40);
        searchPanel.add(priceTo);

        JLabel dateLabel = new JLabel("Ngày");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        dateLabel.setBounds(10, 199, 80, 40);
        searchPanel.add(dateLabel);

        dateFrom = new JDateChooser();
        dateFrom.setBounds(100, 200, 80, 40);
        searchPanel.add(dateFrom);

        dateTo = new JDateChooser();
        dateTo.setBounds(190, 200, 80, 40);
        searchPanel.add(dateTo);

        JLabel lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(10, 250, 80, 40);
        searchPanel.add(lblSpXp);

        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Mã HĐ giảm dần", "Mã NV tăng dần", "Mã NV giảm dần",
                "Mới nhất", "Cũ nhất", "Tổng giá tăng dần", "Tổng giá giảm dần"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(100, 250, 170, 40);
        searchPanel.add(sortCbB);

        JButton searchButton = new JButton("OK");
        searchButton.setForeground(new Color(255, 255, 255));
        searchButton.setBackground(new Color(0x007AFF));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(priceFrom.getText().equals("") && priceTo.getText().equals("")) {
                    showSearchResult(searchTxt.getText(), searchCbB.getSelectedItem().toString().trim(), sortCbB.getSelectedItem().toString().trim(),
                            priceFrom.getText(), priceTo.getText(), dateFrom.getDate(), dateTo.getDate());
                }
                else {
                    if(!priceFrom.getText().matches("[0-9]{1,9}") && !priceTo.getText().matches("[0-9]{1,9}")) {
                        JOptionPane.showMessageDialog(null, "Sai tham số đầu vào!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        showSearchResult(searchTxt.getText(), searchCbB.getSelectedItem().toString().trim(), sortCbB.getSelectedItem().toString().trim(),
                                priceFrom.getText(), priceTo.getText(), dateFrom.getDate(), dateTo.getDate());
                    }
                }

            }
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(40, 316, 100, 50);
        searchPanel.add(searchButton);

        JButton rmSearchBtn = new JButton("Hủy");
        rmSearchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTxt.setText("");
                priceFrom.setText("");
                priceTo.setText("");
                showBill();
                dateFrom.setDate(null);
                dateTo.setDate(null);
                sortCbB.setSelectedIndex(0);
            }
        });
        rmSearchBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        rmSearchBtn.setBounds(140, 316, 100, 50);
        searchPanel.add(rmSearchBtn);

        btnField = new JPanel();
        btnField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        btnField.setBounds(800, 427, 280, 273);
        contentField.add(btnField);
        btnField.setLayout(null);

        viewBtn = new JButton("Xem chi tiết");
        viewBtn.setBackground(new Color(0x007AFF));
        viewBtn.setForeground(Color.white);
        viewBtn.setEnabled(false);
        viewBtn.setBounds(80, 70, 120, 40);
        viewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GiaoDien.hoaDon.setVisible(false);
                GiaoDien.taoDon.setVisible(true);
                GiaoDien.taoDon.showCart();

            }
        });
        btnField.add(viewBtn);

        createBtn = new JButton("Tạo mới");
        createBtn.setBackground(new Color(0x007AFF));
        createBtn.setForeground(Color.white);
        createBtn.setBounds(80, 130, 120, 40);
        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                orderIdStatic.setId(0);
                GiaoDien.hoaDon.setVisible(false);
                GiaoDien.taoDon.setVisible(true);
                GiaoDien.taoDon.resetComponent();
                GiaoDien.taoDon.showCart();
                GiaoDien.taoDon.idStaffCreateOrderTxt.setText(Login.idStaffLogin);
            }
        });
        btnField.add(createBtn);

//        updateBtn = new JButton("Cập nhật");
//        updateBtn.setBounds(80, 190, 120, 40);
//        btnField.add(updateBtn);

        delBtn = new JButton("Xóa");
        delBtn.setBackground(new Color(0x007AFF));
        delBtn.setForeground(Color.white);
        delBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                //xoa o day
                if(decide == 0) {
                    boolean check1=true;
                    DetailOrderService detailOrderService = new DetailOrderService();
                    List<DetailOrder> detailOrderList = detailOrderService.getAllDetailOrder();
                    for (DetailOrder detailOrder : detailOrderList){
                        if (detailOrder.getOrderId() == orderIdStatic.getId()){
                            check1 = detailOrderService.delDetailOrder(orderIdStatic.getId());
                        }
                        if (!check1){
                            break;
                        }
                    }
                    if (check1){
                        boolean check2 = orderService.delOrder(orderIdStatic.getId());
                        if (check2){
                            JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            showBill();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Xóa đơn không thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Xóa chi tiết đơn không thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        delBtn.setBounds(80, 190, 120, 40);
        btnField.add(delBtn);

        JLabel controllerLabel = new JLabel("Cài đặt");
        controllerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controllerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        controllerLabel.setBounds(80, 0, 120, 40);
        btnField.add(controllerLabel);
        //End
        showBill();


    }
    public void showBill(){
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        ordersList = orderService.displayOrders();
        for (Orders orders : ordersList){
            detailTableModel.addRow(new Object[]{
                    orders.getId(), orders.getStaffId(), orders.getOrderDate(), orders.getTotalPrice()
            });
        }
    }

    private void showSearchResult(String searchTxt, String optSearch, String optSort, String priceFrom, String priceTo
            , java.util.Date dateFrom, java.util.Date dateTo) {
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<Orders> searchResultList = orderService.getAllSearchResult(searchTxt, optSearch, optSort, priceFrom, priceTo, dateFrom, dateTo);
        for (Orders orders : searchResultList){
            detailTableModel.addRow(new Object[]{
                    orders.getId(), orders.getStaffId(), orders.getOrderDate(), orders.getTotalPrice()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
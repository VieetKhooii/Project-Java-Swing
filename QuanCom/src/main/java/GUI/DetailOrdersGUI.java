package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.toedter.calendar.JDateChooser;
import model.*;
import service.*;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.util.List;

public class DetailOrdersGUI extends JPanel implements MouseListener, ActionListener, ItemListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel btnField;
    private JButton preBtn;
    private JButton nextBtn = new JButton("Tiếp");
    private JPanel contentField;
    private JScrollPane itemSelectedScrollPane;
    private JTable ordersSelectedTable;
    private DefaultTableModel model;
    public JTextField idOrderTxt;
    public JTextField idStaffCreateOrderTxt;
    public JTextField totalPriceOrderTxt;
    public JDateChooser orderDateChooser;
    private JPanel switchPanel;
    private JPanel selectItemPanel;
    private JTextField idItemSelectedTxt;
    private JTextField nameItemSelectedTxt;
    private JTextField remainItemSelectedTxt;
    private JTextField priceItemSelectedTxt;
    private JTextField amountInputItemSelectedTxt;
    private CardLayout switchTable;
    private JPanel deltailOrderPanel;
    private JTable detailOrdersTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane detailOrderScrollPane;
    private JTextField idDetailOrderTxt;
    private JTextField nameDetailOrderTxt;
    private JTextField soLuongMuaTxt;
    private JTextField priceDetailOrderTxt;
    private JButton turnToSelectedItemTableBtn;
    private JButton backToDetailOrderTableBtn;
    private DefaultTableCellRenderer centerRenderer;
    private JButton selectProductBtn;
    private JButton unselectedBtn;
    private JButton updateBtn;
    private JButton delBtn;
    Component[] components1;
    Component[] components2;
    Component[] totalComponents;
    ProductService productService = new ProductService();
    DetailOrderService detailOrderService = new DetailOrderService();
    OrderService orderService = new OrderService();
    RecipeService recipeService = new RecipeService();
    MaterialService materialService = new MaterialService();
    List<Recipe> recipeList = recipeService.getAllRecipe();
    List<Material> materialList = materialService.getAllMaterial();
    List<DetailOrder> detailOrderList = detailOrderService.getAllDetailOrder();
    List<Orders> ordersList = orderService.displayOrders();
    List<Product> productList = productService.getAllProduct();
    List<Product> cart = new ArrayList<>();
    Orders orderIdStatic = OrdersGUI.orderIdStatic;
    ProductGUI productGUI = new ProductGUI();
    private JLabel thongtinHDLabel;
    /**
     * Create the panel.
     */
    public DetailOrdersGUI() {
        init();
    }
    private void init() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1080, 700));

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        //End


        // Panel chứa chi tiết và chọn món
        contentField = new JPanel(null);
        contentField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.add(contentField, BorderLayout.CENTER);

        switchTable = new CardLayout(0, 0);
        switchPanel = new JPanel(switchTable);
        switchPanel.setBounds(0, 0, 1080, 460);
        contentField.add(switchPanel);

        //Panel chi tiết hóa đơn
        deltailOrderPanel = new JPanel(null);
        switchPanel.add(deltailOrderPanel, "hello2");

        detailTableModel = new DefaultTableModel(new Object[]{"Mã món", "Tên món", "Số lượng mua", " Giá"}, 0);
        detailOrdersTable = new MacOSStyleTable(detailTableModel);
        detailOrdersTable.setFont(new Font("Arial", Font.PLAIN, 14));
        detailOrdersTable.setDefaultRenderer(String.class, centerRenderer);
        detailOrdersTable.setRowHeight(30);
        for(int i = 0; i < 4; i++) {
            if(i == 1) {
                detailOrdersTable.getColumnModel().getColumn(i).setPreferredWidth(300);
                detailOrdersTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                detailOrdersTable.getColumnModel().getColumn(i).setPreferredWidth(125);
                detailOrdersTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        ListSelectionModel listSelectionModel1 = detailOrdersTable.getSelectionModel();
        listSelectionModel1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel1.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = detailOrdersTable.getSelectedRow();
                if (row >= 0){
                    idDetailOrderTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    nameDetailOrderTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    soLuongMuaTxt.setText(detailTableModel.getValueAt(row, 2).toString());
                    priceDetailOrderTxt.setText(detailTableModel.getValueAt(row, 3).toString());
                }
                updateBtn.setEnabled(true);
                delBtn.setEnabled(true);
            }
        });
        detailOrderScrollPane = new CustomScrollPane(detailOrdersTable);
        detailOrderScrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        detailOrderScrollPane.setBounds(0, 0, 800, 460);
        deltailOrderPanel.add(detailOrderScrollPane);

        turnToSelectedItemTableBtn = new JButton(">>");
        turnToSelectedItemTableBtn.setBackground(new Color(0x007AFF));
        turnToSelectedItemTableBtn.setForeground(Color.white);
        turnToSelectedItemTableBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
        turnToSelectedItemTableBtn.setForeground(new Color(0, 0, 0));
        turnToSelectedItemTableBtn.setBorder(BorderFactory.createLineBorder(Color.black));
        turnToSelectedItemTableBtn.setFocusPainted(false);

        detailOrderScrollPane.setRowHeaderView(turnToSelectedItemTableBtn);
        turnToSelectedItemTableBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchTable.show(switchPanel, "hello1");
            }
        });

        JPanel infoDetailOrderPanel = new JPanel(null);
        infoDetailOrderPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        infoDetailOrderPanel.setBounds(800, 0, 280, 460);
        deltailOrderPanel.add(infoDetailOrderPanel);

        JLabel idDetailOrderLabel = new JLabel("Mã món");
        idDetailOrderLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idDetailOrderLabel.setBounds(10, 90, 73, 30);
        infoDetailOrderPanel.add(idDetailOrderLabel);

        idDetailOrderTxt = new JTextField();
        idDetailOrderTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idDetailOrderTxt.setColumns(10);
        idDetailOrderTxt.setBounds(93, 90, 167, 30);
        idDetailOrderTxt.setEnabled(false);
        infoDetailOrderPanel.add(idDetailOrderTxt);

        JLabel nameDetailOrderLabel = new JLabel("Tên món");
        nameDetailOrderLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameDetailOrderLabel.setBounds(10, 142, 73, 30);
        infoDetailOrderPanel.add(nameDetailOrderLabel);

        nameDetailOrderTxt = new JTextField();
        nameDetailOrderTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameDetailOrderTxt.setColumns(10);
        nameDetailOrderTxt.setBounds(93, 142, 167, 30);
        nameDetailOrderTxt.setEnabled(false);
        infoDetailOrderPanel.add(nameDetailOrderTxt);

        JLabel soLuongMuaLabel = new JLabel("Số lượng mua");
        soLuongMuaLabel.setFont(new Font("Arial", Font.BOLD, 13));
        soLuongMuaLabel.setBounds(10, 198, 140, 30);
        infoDetailOrderPanel.add(soLuongMuaLabel);

        soLuongMuaTxt = new JTextField();
        soLuongMuaTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        soLuongMuaTxt.setColumns(10);
        soLuongMuaTxt.setBounds(210, 198, 50, 30);
        infoDetailOrderPanel.add(soLuongMuaTxt);

        JLabel priceDetailOrderLabel = new JLabel("Giá");
        priceDetailOrderLabel.setFont(new Font("Arial", Font.BOLD, 13));
        priceDetailOrderLabel.setBounds(10, 250, 73, 30);
        infoDetailOrderPanel.add(priceDetailOrderLabel);

        priceDetailOrderTxt = new JTextField();
        priceDetailOrderTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        priceDetailOrderTxt.setColumns(10);
        priceDetailOrderTxt.setBounds(93, 250, 167, 30);
        priceDetailOrderTxt.setEnabled(false);
        infoDetailOrderPanel.add(priceDetailOrderTxt);

        updateBtn = new JButton("Cập nhật");
        updateBtn.setBackground(new Color(0x007AFF));
        updateBtn.setForeground(Color.white);
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean check = true;
                for (Product product : cart){
                    if (product.getId() == Integer.parseInt(idDetailOrderTxt.getText())){
                        // nếu mua thêm sản phẩm
                        if (Integer.parseInt(soLuongMuaTxt.getText()) > product.getAmount()){
                            int amount = Integer.parseInt(soLuongMuaTxt.getText()) - product.getAmount();
                            for (int i=0; i<productList.size(); i++){
                                if (product.getId() == productList.get(i).getId()){
                                    // nếu số lượng mua thêm nhiều hơn số lượng tồn
                                    if (amount > productList.get(i).getAmount()){
                                        soLuongMuaTxt.setText(String.valueOf(product.getAmount()));
                                        JOptionPane.showMessageDialog(null, "Số lượng không đủ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                        check = false;
                                    }
                                    // nếu số lượng hợp lí
                                    else {
                                        FoodCalculation.calculateSameRecipeFood(amount,
                                                productList.get(i).getId(),productList,recipeList,materialList,productService);
                                    }
                                    break;
                                }
                            }
                        }
                        // nếu số lượng sau cập nhật nhỏ hơn hay bằng 0
                        else if(Integer.parseInt(soLuongMuaTxt.getText()) <= 0){
                            soLuongMuaTxt.setText(String.valueOf(product.getAmount()));
                            JOptionPane.showMessageDialog(null, "Số lượng phải nhiều hơn 0!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            check = false;

                        }
                        // nếu giảm sản phẩm trong giỏ
                        else {
                            int amount = product.getAmount() - Integer.parseInt(soLuongMuaTxt.getText());
                            // nếu số lượng sau khi sửa phù hợp
                            for (int i=0; i<productList.size(); i++){
                                if (product.getId() == productList.get(i).getId()){
                                    FoodCalculation.returnMaterial(amount,productList.get(i).getId(),
                                            materialList,productList,recipeList,productService);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
                if (check){
                    for (Product product : productList){
                        for (int i=0; i<productList.size(); i++){
                            if (Integer.parseInt(model.getValueAt(i,0).toString()) == product.getId()){
                                model.setValueAt(product.getAmount(),i,2);
                            }
                            // cập nhật cột số lượng mua của sản phẩm
                            if (Integer.parseInt(idDetailOrderTxt.getText()) == Integer.parseInt(model.getValueAt(i,0).toString())){
                                model.setValueAt(Integer.parseInt(soLuongMuaTxt.getText()),i,4);
                            }
                        }
                    }
                    // Hiển thị số lượng và tổng tiền sau khi cập nhật trong giỏ hàng
                    for (Product product : cart){
                        if (product.getId() == Integer.parseInt(idDetailOrderTxt.getText())){
                            product.setAmount(Integer.parseInt(soLuongMuaTxt.getText()));
                            for (int i=0; i<productList.size(); i++){
                                if (product.getId() == productList.get(i).getId()){
                                    product.setPrice(product.getAmount() * productList.get(i).getPrice());
                                }
                            }
                        }
                    }
                    showCart();
                    JOptionPane.showMessageDialog(null, "Đã cập nhật!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    updateBtn.setEnabled(false);
                }
            }
        });
        updateBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        updateBtn.setBounds(50, 362, 90, 35);
        updateBtn.addActionListener(this);
        updateBtn.setEnabled(false);
        infoDetailOrderPanel.add(updateBtn);

        delBtn = new JButton("Xóa");
        delBtn.setBackground(new Color(0x007AFF));
        delBtn.setForeground(Color.white);
        delBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cập nhật lại số lượng nguyên liệu sau khi xóa món hàng
                for (Product product : productList){
                    if (product.getId() == Integer.parseInt(idDetailOrderTxt.getText())){
                        FoodCalculation.returnMaterial(Integer.parseInt(soLuongMuaTxt.getText()),
                                product.getId(),materialList,productList,recipeList,productService);
                        break;
                    }
                }
                // Cập nhật lại số lượng món ăn có thể bán
                FoodCalculation.productAmountCal(productList,recipeList,materialList,productService,
                        false);

                for (Product product : productList){
                    for (int i=0; i<model.getRowCount(); i++){
                        // hiển thị số lượng món ăn có thể bán sau khi cập nhật
                        if (Integer.parseInt(model.getValueAt(i,0).toString())==product.getId()){
                            model.setValueAt(product.getAmount(),i,2);
                        }
                        // hiển thị số lượng mua của món đã xóa thành 0
                        if (Integer.parseInt(model.getValueAt(i,0).toString()) == Integer.parseInt(idDetailOrderTxt.getText())){
                            model.setValueAt(0,i,4);
                        }
                    }
                }
                // Hiển thị món hàng đã xóa trong giỏ và cập nhật giá tiền tổng lại
                for (Product product : cart){
                    if (product.getId() == Integer.parseInt(idDetailOrderTxt.getText())){
                        totalPriceOrderTxt.setText(String.valueOf(Integer.parseInt(totalPriceOrderTxt.getText())-Integer.parseInt(priceDetailOrderTxt.getText())));
                        cart.remove(product);
                        break;
                    }
                }
                idDetailOrderTxt.setText("");
                nameDetailOrderTxt.setText("");
                priceDetailOrderTxt.setText("");
                soLuongMuaTxt.setText("");
                showCart();
                JOptionPane.showMessageDialog(null, "Đã xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                delBtn.setEnabled(false);
            }
        });
        delBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delBtn.setBounds(140, 362, 90, 35);
        delBtn.addActionListener(this);
        delBtn.setEnabled(false);
        infoDetailOrderPanel.add(delBtn);

        JLabel thongtinHDLabel_1 = new JLabel("THÔNG TIN MÓN");
        thongtinHDLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        thongtinHDLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
        thongtinHDLabel_1.setBounds(64, 11, 150, 40);
        infoDetailOrderPanel.add(thongtinHDLabel_1);
        //End

        //Panel Chọn món
        selectItemPanel = new JPanel(new BorderLayout());
        switchPanel.add(selectItemPanel, "hello1");

        model = new DefaultTableModel(new Object[]{"Mã món", "Tên món", "SL còn lại", "Giá", "SL mua"}, 0);
        ordersSelectedTable = new MacOSStyleTable(model);
        ordersSelectedTable.setFont(new Font("Arial", Font.PLAIN, 14));

        ListSelectionModel listSelectionModel = ordersSelectedTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = ordersSelectedTable.getSelectedRow();

                if (row >= 0){
                    idItemSelectedTxt.setText(model.getValueAt(row, 0).toString());
                    nameItemSelectedTxt.setText(model.getValueAt(row, 1).toString());
                    remainItemSelectedTxt.setText(model.getValueAt(row, 2).toString());
                    priceItemSelectedTxt.setText(model.getValueAt(row, 3).toString());
                    amountInputItemSelectedTxt.setText(model.getValueAt(row, 4).toString());
                }

                if (orderIdStatic.getId() == 0) {
                    selectProductBtn.setEnabled(true);
                }
                else {
                    selectProductBtn.setEnabled(false);
                }

//                if(!amountInputItemSelectedTxt.getText().equals("0")) {
//                    unselectedBtn.setEnabled(true);
//                }
//                else {
//                    unselectedBtn.setEnabled(false);
//                }
            }
        });

        ordersSelectedTable.setDefaultRenderer(String.class, centerRenderer);
        ordersSelectedTable.setRowHeight(30);

        for(int i = 0; i < 5; i++) {
            if(i == 1) {
                ordersSelectedTable.getColumnModel().getColumn(i).setPreferredWidth(300);
                ordersSelectedTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                ordersSelectedTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }



        itemSelectedScrollPane = new CustomScrollPane(ordersSelectedTable);
        itemSelectedScrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        itemSelectedScrollPane.setPreferredSize(new Dimension(800, 460));
        selectItemPanel.add(itemSelectedScrollPane, BorderLayout.WEST);

        backToDetailOrderTableBtn = new JButton("<<");
        backToDetailOrderTableBtn.setBackground(new Color(0x007AFF));
        backToDetailOrderTableBtn.setForeground(Color.white);
        itemSelectedScrollPane.setRowHeaderView(backToDetailOrderTableBtn);
        backToDetailOrderTableBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
        backToDetailOrderTableBtn.setForeground(new Color(0, 0, 0));
        backToDetailOrderTableBtn.setBorder(BorderFactory.createLineBorder(Color.black));
        backToDetailOrderTableBtn.setFocusPainted(false);
        backToDetailOrderTableBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchTable.show(switchPanel, "hello2");
            }
        });

        JPanel infoItemSeclected = new JPanel(null);
        infoItemSeclected.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        infoItemSeclected.setPreferredSize(new Dimension(280, 460));
        selectItemPanel.add(infoItemSeclected, BorderLayout.EAST);

        JLabel idItemSelectedLabel = new JLabel("Mã món");
        idItemSelectedLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idItemSelectedLabel.setBounds(10, 90, 73, 30);
        infoItemSeclected.add(idItemSelectedLabel);

        idItemSelectedTxt = new JTextField();
        idItemSelectedTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idItemSelectedTxt.setColumns(10);
        idItemSelectedTxt.setBounds(93, 90, 167, 30);
        idItemSelectedTxt.setEnabled(false);
        infoItemSeclected.add(idItemSelectedTxt);

        JLabel nameItemSelectedLabel = new JLabel("Tên món");
        nameItemSelectedLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameItemSelectedLabel.setBounds(10, 142, 73, 30);
        infoItemSeclected.add(nameItemSelectedLabel);

        nameItemSelectedTxt = new JTextField();
        nameItemSelectedTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameItemSelectedTxt.setColumns(10);
        nameItemSelectedTxt.setBounds(93, 142, 167, 30);
        nameItemSelectedTxt.setEnabled(false);
        infoItemSeclected.add(nameItemSelectedTxt);

        JLabel remainItemSelectedLabel = new JLabel("Số lượng còn lại");
        remainItemSelectedLabel.setFont(new Font("Arial", Font.BOLD, 13));
        remainItemSelectedLabel.setBounds(10, 194, 140, 30);
        infoItemSeclected.add(remainItemSelectedLabel);

        remainItemSelectedTxt = new JTextField();
        remainItemSelectedTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        remainItemSelectedTxt.setColumns(10);
        remainItemSelectedTxt.setBounds(170, 194, 90, 30);
        remainItemSelectedTxt.setEnabled(false);
        infoItemSeclected.add(remainItemSelectedTxt);

        JLabel amountInputItemSelectedLabel = new JLabel("Số lượng mua");
        amountInputItemSelectedLabel.setFont(new Font("Arial", Font.BOLD, 13));
        amountInputItemSelectedLabel.setBounds(10, 249, 140, 30);
        infoItemSeclected.add(amountInputItemSelectedLabel);

        amountInputItemSelectedTxt = new JTextField();
        amountInputItemSelectedTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        amountInputItemSelectedTxt.setColumns(10);
        amountInputItemSelectedTxt.setBounds(170, 249, 90, 30);
        amountInputItemSelectedTxt.setEnabled(false);
        infoItemSeclected.add(amountInputItemSelectedTxt);

        JLabel priceItemSelectedLabel = new JLabel("Giá");
        priceItemSelectedLabel.setBounds(10, 302, 73, 30);
        infoItemSeclected.add(priceItemSelectedLabel);
        priceItemSelectedLabel.setFont(new Font("Arial", Font.BOLD, 13));

        priceItemSelectedTxt = new JTextField();
        priceItemSelectedTxt.setBounds(93, 302, 167, 30);
        infoItemSeclected.add(priceItemSelectedTxt);
        priceItemSelectedTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        priceItemSelectedTxt.setEnabled(false);
        priceItemSelectedTxt.setColumns(10);

        selectProductBtn = new JButton("Chọn");
        selectProductBtn.setBackground(new Color(0x007AFF));
        selectProductBtn.setForeground(Color.white);
        selectProductBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        selectProductBtn.setBounds(95, 380, 90, 35);
        selectProductBtn.addActionListener(this);
        selectProductBtn.setEnabled(false);
        infoItemSeclected.add(selectProductBtn);

        thongtinHDLabel = new JLabel("THÔNG TIN MÓN");
        thongtinHDLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thongtinHDLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
        thongtinHDLabel.setBounds(70, 11, 150, 40);
        infoItemSeclected.add(thongtinHDLabel);

//        unselectedBtn = new JButton("Bỏ chọn");
//        unselectedBtn.addActionListener(this);
//        unselectedBtn.setEnabled(false);
//        unselectedBtn.setFont(new Font("Arial", Font.PLAIN, 13));
//        unselectedBtn.setBounds(140, 310, 90, 35);
//        infoItemSeclected.add(unselectedBtn);
        //End

        showTableProduct();









        //Tạo hóa đơn
        JLabel orderInfoLabel = new JLabel("THÔNG TIN HÓA ĐƠN");
        orderInfoLabel.setFont(new Font("Arial", Font.BOLD, 15));
        orderInfoLabel.setBounds(10, 470, 200, 20);
        contentField.add(orderInfoLabel);

        JLabel idOrderLabel = new JLabel("Mã hóa đơn");
        idOrderLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idOrderLabel.setBounds(30, 521, 120, 30);
        contentField.add(idOrderLabel);

        idOrderTxt = new JTextField();
        idOrderTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idOrderTxt.setBounds(160, 521, 178, 30);
        idOrderTxt.setEnabled(false);
        contentField.add(idOrderTxt);
        idOrderTxt.setColumns(10);

        JLabel orderDateLabel = new JLabel("Ngày tạo đơn");
        orderDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        orderDateLabel.setBounds(30, 573, 120, 30);
        contentField.add(orderDateLabel);

        orderDateChooser = new JDateChooser();
        orderDateChooser.setBounds(160, 573, 178, 30);
        contentField.add(orderDateChooser);

        JLabel idStaffCreateOrderLabel = new JLabel("Mã nhân viên tạo đơn");
        idStaffCreateOrderLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idStaffCreateOrderLabel.setBounds(370, 573, 150, 30);
        contentField.add(idStaffCreateOrderLabel);

        idStaffCreateOrderTxt = new JTextField();
        idStaffCreateOrderTxt.setEditable(false);
        idStaffCreateOrderTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idStaffCreateOrderTxt.setColumns(10);
        idStaffCreateOrderTxt.setBounds(530, 573, 180, 30);
        contentField.add(idStaffCreateOrderTxt);

        JLabel totalPriceLabel = new JLabel("Tổng tiền");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 13));
        totalPriceLabel.setBounds(370, 521, 150, 30);
        contentField.add(totalPriceLabel);

        totalPriceOrderTxt = new JTextField();
        totalPriceOrderTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        totalPriceOrderTxt.setColumns(10);
        totalPriceOrderTxt.setBounds(530, 521, 180, 30);
        totalPriceOrderTxt.setEnabled(false);
        contentField.add(totalPriceOrderTxt);


        //Nút chuyển tiến trình
        btnField = new JPanel();
        btnField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        btnField.setPreferredSize(new Dimension(1080, 70));
        btnField.setLayout(null);
        this.add(btnField, BorderLayout.SOUTH);

        preBtn = new JButton("Quay lại");

        preBtn.setBackground(new Color(0x007AFF));
        preBtn.setForeground(Color.white);
        preBtn.setFont(new Font("Arial", Font.BOLD, 17));
        preBtn.setBounds(70, 10, 100, 50);
        preBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(!idStaffCreateOrderTxt.getText().equals("") || !cart.isEmpty()) {
                    int decide=0;
                    if (orderIdStatic.getId() == 0) {
                        decide = JOptionPane.showConfirmDialog(null, "Một số dữ liệu vẫn chưa được lưu, bạn có muốn quay trở lại?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    }
                    if(decide==0) {
                        JButton viewBtn = OrdersGUI.viewBtn;
                        viewBtn.setEnabled(false);
                        GiaoDien.hoaDon.setVisible(true);
                        GiaoDien.taoDon.setVisible(false);
                        for (int i=0; i<productList.size(); i++){
                            ordersSelectedTable.setValueAt(0,i,4);
                        }
                        materialList = materialService.getAllMaterial();
                        showTableProduct();
                        cart.clear();
                    }
                }
                else {
                    JButton viewBtn = OrdersGUI.viewBtn;
                    viewBtn.setEnabled(false);
                    GiaoDien.hoaDon.setVisible(true);
                    GiaoDien.taoDon.setVisible(false);
                    for (int i=0; i<productList.size(); i++){
                        ordersSelectedTable.setValueAt(0,i,4);
                    }
                    showTableProduct();
                    materialList = materialService.getAllMaterial();
                    cart.clear();
                }
                showCart();
            }
        });
        btnField.add(preBtn);

        nextBtn = new JButton("Tiếp");
        nextBtn.setBackground(new Color(0x007AFF));
        nextBtn.setForeground(Color.white);
        nextBtn.setFont(new Font("Arial", Font.BOLD, 17));
        nextBtn.setBounds(910, 10, 100, 50);
        nextBtn.addMouseListener(this);
        nextBtn.addActionListener(this);
        btnField.add(nextBtn);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 0, 1080, 1);
        btnField.add(separator);
        //End


        //Mảng lấy các component
        components1 = contentField.getComponents();
        components2 = infoDetailOrderPanel.getComponents();
        totalComponents = Arrays.copyOf(components1, components1.length + components2.length);
        System.arraycopy(components2, 0, totalComponents, components1.length, components2.length);
    }

    public void showTableProduct(){
        while (model.getRowCount() != 0){
            model.removeRow(0);
        }
        materialList = materialService.getAllMaterial();
        productList = productService.getAllProduct();
        FoodCalculation.productAmountCal(productList,recipeList,materialList,productService,false);
        for(Product product : productList) {
            model.addRow(new Object[] {
                    product.getId(), product.getName(), product.getAmount(),
                    product.getPrice(), 0
            });
        }
        showCart();
    }

    public void showCart(){
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        if (orderIdStatic.getId() != 0){
            nextBtn.setEnabled(false);
            for (DetailOrder detailOrder : detailOrderList){
                if (detailOrder.getOrderId() == orderIdStatic.getId()){
                    detailTableModel.addRow(new Object[]{
                            detailOrder.getProductId(), detailOrder.getName(), detailOrder.getAmount(),
                            detailOrder.getPrice()
                    });
                }
            }
        }
        else if (cart.size() > 0){
            nextBtn.setEnabled(true);
            for (Product product : cart){
                detailTableModel.addRow(new Object[]{
                        product.getId(), product.getName(), product.getAmount(), product.getPrice()
                });
            }
            int totalPrice = 0;
            for (Product product : cart){
                totalPrice += product.getPrice();
            }
            totalPriceOrderTxt.setText(String.valueOf(totalPrice));
        }
        else if (cart.size() == 0){
            nextBtn.setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub


    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        int selectedRow = ordersSelectedTable.getSelectedRow(); // Lấy chỉ mục của hàng được chọn
        int modelRow = ordersSelectedTable.convertRowIndexToModel(selectedRow); // Chuyển đổi chỉ mục hàng sang chỉ mục hàng tương ứng trong mô hình dữ liệu
        //////////////////
        if(e.getSource() == nextBtn) {
            if(idStaffCreateOrderTxt.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            else {
                java.sql.Date sqlDate = new Date(orderDateChooser.getDate().getTime());
                boolean check = orderService.addOrder(
                        Integer.parseInt(idStaffCreateOrderTxt.getText()),
                        Integer.parseInt(totalPriceOrderTxt.getText()),
                        sqlDate);
                if (check){
                    ordersList = orderService.displayOrders();
                    productList = productService.getAllProduct();
                    int id = ordersList.get(ordersList.size()-1).getId();
                    FoodCalculation.materialConsumption(id,cart,productList,recipeList,materialList,detailOrderService,materialService);
                    materialList = materialService.getAllMaterial();
                    showTableProduct();
                    int i=0;
                    for (Product product : cart){
                        detailOrderService.addDetailOrder(
                                Integer.parseInt(model.getValueAt(i,0).toString()),
                                product.getId(),
                                product.getName(),
                                product.getAmount(),
                                product.getPrice());
                        i++;
                    }
                    cart.clear();
                    JOptionPane.showMessageDialog(null, "Tạo đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    detailOrderList = detailOrderService.getAllDetailOrder();
                    GiaoDien.material.showTableMaterial();
                    GiaoDien.hoaDon.setVisible(true);
                    GiaoDien.taoDon.setVisible(false);
                    GiaoDien.hoaDon.showBill();
                    showCart();
                }
                else {
                    System.out.println("Error");
                }
            }
        }

        // nút chọn để bỏ vô giỏ hàng
        if(e.getSource() == selectProductBtn) {

            String input="";
            boolean flag;
            boolean check=true;
            do {
                flag=true;
                input = JOptionPane.showInputDialog("Nhập số lượng món");
                if(input.equals("") || input.equals("0")) {
                    flag=false;
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (Integer.parseInt(input) > (Integer) model.getValueAt(modelRow, 2)
                        || productList.get(modelRow).getAmount() < Integer.parseInt(input)){
                    flag = false;
                    JOptionPane.showMessageDialog(null, "Số lượng không đủ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }while(!flag);

            amountInputItemSelectedTxt.setText(input);
            for (Product product : cart){
                if ((Integer) model.getValueAt(modelRow, 0) == product.getId()){
                    product.setAmount(product.getAmount()+Integer.parseInt(input));
                    product.setPrice(product.getAmount() * (Integer) model.getValueAt(modelRow, 3));
                    check = false;
                    break;
                }
            }
            if (check){
                Product product = new Product();
                product.setId((Integer) model.getValueAt(modelRow, 0));
                product.setName((String) model.getValueAt(modelRow, 1));
                product.setPrice((Integer) model.getValueAt(modelRow, 3) * Integer.parseInt(input));
                product.setAmount(Integer.parseInt(input));
                cart.add(product);
            }
            for (int i=0; i<productList.size(); i++){
                if (i==modelRow){
                    model.setValueAt(Integer.parseInt(model.getValueAt(modelRow, 4).toString())+ Integer.parseInt(input),modelRow,4);
                    break;
                }
            }
            FoodCalculation.calculateSameRecipeFood(Integer.parseInt(input),
                    productList.get(modelRow).getId(),productList,recipeList,materialList,productService);
            for (int i=0; i<productList.size(); i++){
                model.setValueAt(productList.get(i).getAmount(),i,2);
            }
            showCart();

        }
        if(e.getSource() == unselectedBtn) {
            amountInputItemSelectedTxt.setText("0");
            ordersSelectedTable.setValueAt("0", modelRow, 4);
        }

    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub


    }
    //Hàm reset dl các component
    void resetComponent() {
        for (Component component : totalComponents) {
            if (component instanceof JTextField) {

                ((JTextField) component).setText(""); // reset giá trị trên JTextField
            }
            // Thêm các trường hợp khác tương tự nếu cần thiết
        }
    }
}
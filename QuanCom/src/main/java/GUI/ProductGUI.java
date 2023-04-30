package GUI;

import enumm.ProductUnit;
import model.Category;
import model.Product;
import model.Roles;
import service.CategoryService;
import service.ProductService;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProductGUI extends JPanel implements MouseListener, ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel productListPanel;
    private JTable productTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane productScrollPane;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel searchPanel;
    private JLabel lblTmKim;
    private JComboBox<String> searchCbB;
    private JComboBox<String> categorySearchCbB;
    private JTextField textField;
    private JLabel lblSpXp;
    private JComboBox<String> sortCbB;
    private JButton searchButton;
    private JTextField idProductTxt;
    private JTextField priceProductTxt;
    private JTextField nameProductTxt;
    private JTextField soLuongProductTxt;
    private JComboBox<String> unitProductCbB;
    private JComboBox<String> categoryProductCbB;
    private JTextField priceFrom;
    private JTextField PriceTo;
    private JButton addProductBtn;
    private JButton fixProductBtn;
    private JButton delProductBtn;
    private JButton browsePhoto;
    private JButton clearInfoBtn;
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    List<Product> productList = productService.getAllProduct();
    List<Category> categoryList = categoryService.getAllCate();
    /**
     * Create the panel.
     */
    public ProductGUI() {
        init();
    }
    private void init() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1080, 670));

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        //End


        // Panel table
        contentField = new JPanel(null);

        this.add(contentField, BorderLayout.CENTER);

        //Panel table nhan vien
        productListPanel = new JPanel(null);
        productListPanel.setBackground(new Color(30, 144, 255));
        productListPanel.setBounds(0, 380, 1080, 290);

        contentField.add(productListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã món", "Tên món", "Đơn vị tính", "Số lượng", "Giá", "Phân loại"}, 0);
        productTable = new JTable(detailTableModel);
        productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        productTable.setDefaultRenderer(String.class, centerRenderer);
        productTable.setRowHeight(30);
        for(int i = 0; i < 6; i++) {
            if(i == 1) {
                productTable.getColumnModel().getColumn(i).setPreferredWidth(250);
                productTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                //staffTable.getColumnModel().getColumn(i).setPreferredWidth(125);
                productTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        ListSelectionModel listSelectionModel = productTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = productTable.getSelectedRow();
                if (row >= 0){
                    idProductTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    nameProductTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    for(int i = 0; i < unitProductCbB.getItemCount(); i++) {
                        if(detailTableModel.getValueAt(row, 2).toString().equals(unitProductCbB.getItemAt(i).toString())) {
                            unitProductCbB.setSelectedIndex(i);
                            break;
                        }
                    }
                    priceProductTxt.setText(detailTableModel.getValueAt(row, 3).toString());
                    soLuongProductTxt.setText(detailTableModel.getValueAt(row, 4).toString());
                    for(int i = 0; i < categoryProductCbB.getItemCount(); i++) {
                        if(detailTableModel.getValueAt(row, 5).toString().equals(categoryProductCbB.getItemAt(i).toString())) {
                            categoryProductCbB.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });

        detailTableModel.addRow(new Object[] {"1", "Coca cola", "Chai", "3", "8000", "Nước"});

        productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(5, 5, 1070, 280);
        productListPanel.add(productScrollPane);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel staffLabel = new JLabel("MÓN ĂN");
        staffLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(staffLabel);
        staffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staffLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        // Tìm kiếm
        searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        searchPanel.setBounds(800, 50, 280, 330);
        contentField.add(searchPanel);
        searchPanel.setLayout(null);

        lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(85, 0, 120, 40);
        searchPanel.add(lblTmKim);

        searchCbB = new JComboBox<String>();
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã món", "Tên món"}));
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setBounds(10, 64, 101, 30);
        searchPanel.add(searchCbB);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        textField.setColumns(10);
        textField.setBounds(121, 64, 149, 30);
        searchPanel.add(textField);

        searchButton = new JButton("OK");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(96, 289, 100, 30);
        searchPanel.add(searchButton);

        priceFrom = new JTextField();
        priceFrom.setFont(new Font("Arial", Font.PLAIN, 13));
        priceFrom.setColumns(10);
        priceFrom.setBounds(121, 105, 149, 30);
        searchPanel.add(priceFrom);

        JLabel priceProduct = new JLabel("Giá ~");
        priceProduct.setFont(new Font("Arial", Font.BOLD, 13));
        priceProduct.setBounds(10, 131, 47, 30);
        searchPanel.add(priceProduct);

        PriceTo = new JTextField();
        PriceTo.setFont(new Font("Arial", Font.PLAIN, 13));
        PriceTo.setColumns(10);
        PriceTo.setBounds(121, 146, 149, 30);
        searchPanel.add(PriceTo);

        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setBounds(10, 238, 80, 30);
        searchPanel.add(lblSpXp);
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));

        sortCbB = new JComboBox<String>();
        sortCbB.setBounds(121, 238, 149, 30);
        searchPanel.add(sortCbB);
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã món", "Tên món", "Số lượng"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));

        JLabel lblPhnLoi_1 = new JLabel("Phân loại");
        lblPhnLoi_1.setFont(new Font("Arial", Font.BOLD, 13));
        lblPhnLoi_1.setBounds(10, 190, 80, 30);
        searchPanel.add(lblPhnLoi_1);

        categorySearchCbB = new JComboBox<String>();
        //categorySearchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã món", "Tên món"}));
        categorySearchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        categorySearchCbB.setBounds(121, 190, 149, 30);
        searchPanel.add(categorySearchCbB);
        // Thông tin món ăn
        JPanel staffInfoPanel = new JPanel();
        staffInfoPanel.setLayout(null);
        staffInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffInfoPanel.setBounds(0, 50, 800, 330);
        contentField.add(staffInfoPanel);

        JLabel staffPhoto = new JLabel("Ảnh");
        staffPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        staffPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffPhoto.setBackground(Color.WHITE);
        staffPhoto.setBounds(41, 70, 140, 130);
        staffInfoPanel.add(staffPhoto);

        JLabel lblThngTinMn = new JLabel("Thông tin món ăn");
        lblThngTinMn.setHorizontalAlignment(SwingConstants.CENTER);
        lblThngTinMn.setFont(new Font("Arial", Font.BOLD, 15));
        lblThngTinMn.setBounds(306, 0, 150, 40);
        staffInfoPanel.add(lblThngTinMn);

        addProductBtn = new JButton("Thêm");
        addProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!idProductTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Không được chọn món ăn đã có sẵn để thêm! Khi thêm id phải để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameProductTxt.getText().equals("") || priceProductTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int id=0;
                    for (Category category : categoryList){
                        if (category.getName().equalsIgnoreCase((String) categoryProductCbB.getSelectedItem())){
                            id = category.getId();
                            break;
                        }
                    }
                    productService.addProduct(nameProductTxt.getText(),
                            0,(String) unitProductCbB.getSelectedItem(),
                            Integer.parseInt(priceProductTxt.getText()),
                            id);
                    showTableProduct();
                    JOptionPane.showMessageDialog(null, "Đã thêm món ăn!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addProductBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addProductBtn.setBounds(270, 280, 90, 35);
        staffInfoPanel.add(addProductBtn);

        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productTable.clearSelection();
                idProductTxt.setText(null);
                nameProductTxt.setText(null);
                priceProduct.setText(null);
                soLuongProductTxt.setText(null);
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        clearInfoBtn.setBounds(534, 280, 90, 35);
        staffInfoPanel.add(clearInfoBtn);

        fixProductBtn = new JButton("Cập nhật");
        fixProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idProductTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 món ăn và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameProductTxt.getText().equals("") || priceProduct.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int id=0;
                    for (Category category : categoryList){
                        if (category.getName().equalsIgnoreCase((String) categoryProductCbB.getSelectedItem())){
                            id = category.getId();
                            break;
                        }
                    }
                    productService.modifyProduct(nameProductTxt.getText(),
                            Integer.parseInt(soLuongProductTxt.getText()),
                            (String) unitProductCbB.getSelectedItem(),Integer.parseInt(priceProductTxt.getText()),
                            id,Integer.parseInt(idProductTxt.getText()));
                    showTableProduct();
                    JOptionPane.showMessageDialog(null, "Đã sửa tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        fixProductBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        fixProductBtn.setBounds(358, 280, 90, 35);
        staffInfoPanel.add(fixProductBtn);

        delProductBtn = new JButton("Xóa");
        delProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idProductTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 món ăn và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    //xoa o day
                    if(decide == 0) {
                        productService.deleteProduct(Integer.parseInt(idProductTxt.getText()));
                        clearInfoBtn.doClick();
                        showTableProduct();
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        delProductBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delProductBtn.setBounds(445, 280, 90, 35);
        staffInfoPanel.add(delProductBtn);

        browsePhoto = new JButton("Chọn");
        browsePhoto.setBounds(71, 220, 80, 30);
        staffInfoPanel.add(browsePhoto);

        idProductTxt = new JTextField();
        idProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idProductTxt.setColumns(10);
        idProductTxt.setBounds(333, 70, 167, 30);
        idProductTxt.setEditable(false);
        staffInfoPanel.add(idProductTxt);

        priceProductTxt = new JTextField();
        priceProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        priceProductTxt.setColumns(10);
        priceProductTxt.setBounds(593, 70, 167, 30);
        staffInfoPanel.add(priceProductTxt);

        nameProductTxt = new JTextField();
        nameProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameProductTxt.setColumns(10);
        nameProductTxt.setBounds(333, 120, 167, 30);
        staffInfoPanel.add(nameProductTxt);

        JLabel lblnVTnh = new JLabel("Đơn vị tính");
        lblnVTnh.setFont(new Font("Arial", Font.BOLD, 13));
        lblnVTnh.setBounds(540, 120, 70, 30);
        staffInfoPanel.add(lblnVTnh);

        unitProductCbB = new JComboBox<String>();
        ProductUnit[] units = ProductUnit.values();
        String[] unitList = new String[units.length];
        for (int i=0; i<unitList.length; i++) {
            unitList[i] = units[i].getUnit();
        }
        unitProductCbB.setModel(new DefaultComboBoxModel<String>(unitList));
        unitProductCbB.setFont(new Font("Arial", Font.BOLD, 13));
        unitProductCbB.setBounds(660, 120, 100, 30);
        staffInfoPanel.add(unitProductCbB);

        JLabel addressSupplierLabel = new JLabel("Giá");
        addressSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        addressSupplierLabel.setBounds(540, 70, 50, 30);
        staffInfoPanel.add(addressSupplierLabel);

        soLuongProductTxt = new JTextField();
        soLuongProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        soLuongProductTxt.setColumns(10);
        soLuongProductTxt.setBounds(333, 170, 167, 30);
        soLuongProductTxt.setEditable(false);
        staffInfoPanel.add(soLuongProductTxt);

        JLabel lblPhnLoi = new JLabel("Phân loại");
        lblPhnLoi.setFont(new Font("Arial", Font.BOLD, 13));
        lblPhnLoi.setBounds(540, 170, 70, 30);
        staffInfoPanel.add(lblPhnLoi);

        categoryProductCbB = new JComboBox<String>();
        for (Category category : categoryList){
            categoryProductCbB.addItem(category.getName());
        }
        categoryProductCbB.setFont(new Font("Arial", Font.BOLD, 13));
        categoryProductCbB.setBounds(660, 170, 100, 30);
        staffInfoPanel.add(categoryProductCbB);

        JLabel idProductLabel = new JLabel("Mã món");
        idProductLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idProductLabel.setBounds(250, 70, 70, 30);
        staffInfoPanel.add(idProductLabel);

        JLabel nameProductLabel = new JLabel("Tên món");
        nameProductLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameProductLabel.setBounds(250, 120, 70, 30);
        staffInfoPanel.add(nameProductLabel);

        JLabel soluongLb = new JLabel("Số lượng");
        soluongLb.setFont(new Font("Arial", Font.BOLD, 13));
        soluongLb.setBounds(250, 170, 70, 30);
        staffInfoPanel.add(soluongLb);
        //End
        showTableProduct();


    }
    public void showTableProduct(){
        String categoryName = "";
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        categoryList = categoryService.getAllCate();
        productList = productService.getAllProduct();
        for(Product product : productList) {
            System.out.println("hi");
            for (Category category : categoryList){
                if (category.getId() == product.getCategoryId()){
                    categoryName = category.getName();
                }
            }
            detailTableModel.addRow(new Object[] {
                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
                    product.getPrice(), categoryName
            });
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
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

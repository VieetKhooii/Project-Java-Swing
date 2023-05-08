package GUI;

import enumm.UnitMaterial;
import model.Material;
import model.Product;
import model.Recipe;
import service.FoodCalculation;
import service.MaterialService;
import service.ProductService;
import service.RecipeService;

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

public class MaterialGUI extends JPanel implements MouseListener, ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel materialListPanel;
    private JTable materialTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane materialScrollPane;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel searchPanel;
    private JLabel lblNewLabel;
    private JTextField idMaterialTxt;
    private JTextField nameMaterialTxt;
    private JTextField priceMaterialTxt;
    private JComboBox<String> unitMaterialCbB;
    private JComboBox<String> categoryMaterialCbB;
    private JPanel materialInfoPanel;
    private JComboBox<String> searchCbB;
    private JTextField searchTxt;
    private JLabel lblSpXp;
    private JComboBox<String> sortCbB;
    private JLabel lblTmKim;
    private JButton searchButton;
    private JTextField soluongMaterialTxt;
    private JButton addMaterialBtn;
    private JButton fixMaterialBtn;
    private JButton delMaterialBtn;
    private JButton clearInfoBtn;
    MaterialService materialService = new MaterialService();
    List<Material> materialList = materialService.getAllMaterial();
    ProductService productService = new ProductService();
    RecipeService recipeService = new RecipeService();
    List<Product> productList = productService.getAllProduct();
    List<Recipe> recipeList = recipeService.getAllRecipe();
    /**
     * Create the panel.
     */
    public MaterialGUI() {
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
        materialListPanel = new JPanel(null);
        materialListPanel.setBackground(new Color(30, 144, 255));
        materialListPanel.setBounds(0, 340, 1080, 360);

        contentField.add(materialListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã nguyên liệu", "Tên nguyên liệu", "Đơn vị", "Số lượng", "Giá"}, 0);
        materialTable = new JTable(detailTableModel);
        materialTable.setFont(new Font("Arial", Font.PLAIN, 14));
        materialTable.setDefaultRenderer(String.class, centerRenderer);
        materialTable.setRowHeight(30);
        for(int i = 0; i < 5; i++) {
            if(i == 1) {
                materialTable.getColumnModel().getColumn(i).setPreferredWidth(250);
                materialTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                materialTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        ListSelectionModel listSelectionModel = materialTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = materialTable.getSelectedRow();
                if (row >= 0){
                    idMaterialTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    nameMaterialTxt.setText(detailTableModel.getValueAt(row, 1).toString());
//                    for(int i = 0; i < unitMaterialCbB.getItemCount(); i++) {
//                        if(detailTableModel.getValueAt(row, 2).toString().equals(unitMaterialCbB.getItemAt(i).toString())) {
//                            unitMaterialCbB.setSelectedIndex(i);
//                            break;
//                        }
//                    }

                    soluongMaterialTxt.setText(detailTableModel.getValueAt(row, 3).toString());
                    priceMaterialTxt.setText(detailTableModel.getValueAt(row, 4).toString());
                }
            }
        });

        materialScrollPane = new JScrollPane(materialTable);
        materialScrollPane.setBounds(5, 5, 1070, 350);
        materialListPanel.add(materialScrollPane);

        materialInfoPanel = new JPanel();
        materialInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        materialInfoPanel.setBounds(0, 50, 600, 290);
        contentField.add(materialInfoPanel);
        materialInfoPanel.setLayout(null);

        lblNewLabel = new JLabel("Thông tin nguyên liệu");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(200, 0, 200, 40);
        materialInfoPanel.add(lblNewLabel);

        JLabel idSupplierLabel = new JLabel("Mã N.liệu");
        idSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idSupplierLabel.setBounds(44, 51, 70, 30);
        materialInfoPanel.add(idSupplierLabel);

        idMaterialTxt = new JTextField();
        idMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idMaterialTxt.setColumns(10);
        idMaterialTxt.setBounds(127, 51, 167, 30);
        idMaterialTxt.setEditable(false);
        materialInfoPanel.add(idMaterialTxt);

        nameMaterialTxt = new JTextField();
        nameMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameMaterialTxt.setColumns(10);
        nameMaterialTxt.setBounds(127, 101, 167, 30);
        materialInfoPanel.add(nameMaterialTxt);

        JLabel nameSupplierLabel = new JLabel("Tên N.liệu");
        nameSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameSupplierLabel.setBounds(44, 101, 70, 30);
        materialInfoPanel.add(nameSupplierLabel);

        JLabel addressSupplierLabel = new JLabel("Giá");
        addressSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        addressSupplierLabel.setBounds(334, 51, 50, 30);
        materialInfoPanel.add(addressSupplierLabel);

        priceMaterialTxt = new JTextField();
        priceMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        priceMaterialTxt.setColumns(10);
        priceMaterialTxt.setBounds(387, 51, 167, 30);
        materialInfoPanel.add(priceMaterialTxt);

        UnitMaterial unitMaterial = new UnitMaterial();
        unitMaterialCbB = new JComboBox<String>();
        unitMaterialCbB.setModel(new DefaultComboBoxModel<String>(unitMaterial.unitArray));
        unitMaterialCbB.setFont(new Font("Arial", Font.BOLD, 13));
        unitMaterialCbB.setBounds(454, 101, 100, 30);
        materialInfoPanel.add(unitMaterialCbB);

        JLabel lblnVTnh = new JLabel("Đơn vị tính");
        lblnVTnh.setFont(new Font("Arial", Font.BOLD, 13));
        lblnVTnh.setBounds(334, 101, 70, 30);
        materialInfoPanel.add(lblnVTnh);

        JLabel soluongLb = new JLabel("Số lượng");
        soluongLb.setFont(new Font("Arial", Font.BOLD, 13));
        soluongLb.setBounds(44, 151, 70, 30);
        materialInfoPanel.add(soluongLb);

        soluongMaterialTxt = new JTextField();
        soluongMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        soluongMaterialTxt.setColumns(10);
        soluongMaterialTxt.setBounds(127, 151, 167, 30);
        materialInfoPanel.add(soluongMaterialTxt);

        addMaterialBtn = new JButton("Thêm");
        addMaterialBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!idMaterialTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Không được chọn nguyên liệu đã có sẵn để thêm! Khi thêm id phải để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameMaterialTxt.getText().equals("") || priceMaterialTxt.getText().equals("") || soluongMaterialTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String unit = "";
                    for (Material material : materialList){
                        if (unitMaterialCbB.getSelectedItem().equals(material.getName())){
                            unit = material.getName();
                            break;
                        }
                    }
                    materialService.addMaterial(nameMaterialTxt.getText(),unit,Integer.parseInt(priceMaterialTxt.getText()),Integer.parseInt(soluongMaterialTxt.getText()));
                    showTableMaterial();
                    JOptionPane.showMessageDialog(null, "Đã thêm nguyên liệu!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addMaterialBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addMaterialBtn.setBounds(162, 244, 90, 35);
        materialInfoPanel.add(addMaterialBtn);

        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                materialTable.clearSelection();
                idMaterialTxt.setText(null);
                nameMaterialTxt.setText(null);
                priceMaterialTxt.setText(null);
                soluongMaterialTxt.setText(null);
                showTableMaterial();
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        clearInfoBtn.setBounds(424, 244, 90, 35);
        materialInfoPanel.add(clearInfoBtn);

        fixMaterialBtn = new JButton("Cập nhật");
        fixMaterialBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idMaterialTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 nguyên liệu và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameMaterialTxt.getText().equals("") || priceMaterialTxt.getText().equals("") || soluongMaterialTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    String unit = "";
                    for (Material material : materialList){
                        if (unitMaterialCbB.getSelectedItem().equals(material.getName())){
                            unit = material.getName();
                            break;
                        }
                    }
                    productList = productService.getAllProduct();
                    materialService.modifyMaterial(nameMaterialTxt.getText(),unit,Integer.parseInt(priceMaterialTxt.getText()),Integer.parseInt(soluongMaterialTxt.getText()), Integer.parseInt(idMaterialTxt.getText()));
                    FoodCalculation.productAmountCal(productList,recipeList,materialList,productService,true);
                    showTableMaterial();
                    GiaoDien.taoDon.showTableProduct();
                    JOptionPane.showMessageDialog(null, "Đã sửa tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        fixMaterialBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        fixMaterialBtn.setBounds(250, 244, 90, 35);
        materialInfoPanel.add(fixMaterialBtn);

        delMaterialBtn = new JButton("Xóa");
        delMaterialBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idMaterialTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 nguyên liệu và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    //xoa o day
                    if(decide == 0) {
                        materialService.deleteMaterial(Integer.parseInt(idMaterialTxt.getText()));
                        showTableMaterial();
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        delMaterialBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delMaterialBtn.setBounds(337, 244, 90, 35);
        materialInfoPanel.add(delMaterialBtn);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel supplierLabel = new JLabel("NGUYÊN LIỆU");
        supplierLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(supplierLabel);
        supplierLabel.setHorizontalAlignment(SwingConstants.CENTER);
        supplierLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        searchPanel.setBounds(600, 50, 480, 290);
        contentField.add(searchPanel);
        searchPanel.setLayout(null);

        searchCbB = new JComboBox<String>();
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã nguyên liệu", "Tên nguyên liệu"}));
        searchCbB.setBounds(130, 75, 101, 40);
        searchPanel.add(searchCbB);

        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        searchTxt.setColumns(10);
        searchTxt.setBounds(241, 75, 149, 40);
        searchPanel.add(searchTxt);

        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(130, 135, 80, 40);
        searchPanel.add(lblSpXp);

        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã nguyên liệu", "Tên nguyên liệu"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(241, 135, 149, 40);
        searchPanel.add(sortCbB);

        lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(185, 11, 120, 40);
        searchPanel.add(lblTmKim);

        searchButton = new JButton("OK");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(192, 229, 100, 50);
        searchPanel.add(searchButton);
        //End
        showTableMaterial();


    }

    public void showTableMaterial(){
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        materialList = materialService.getAllMaterial();
        for(Material material : materialList) {
            detailTableModel.addRow(new Object[] {
                    material.getId(), material.getName(), material.getUnit(), material.getAmount(),
                    material.getPrice(),
            });
        }
    }
//    public void productAmountCal(){
//        int min;
//        int temp=0;
//        for (Product product : productList){
//            min=-1;
//            temp=0;
//            for (Recipe recipe : recipeList){
//                if (product.getId() == recipe.getProductId()){
//                    for (Material material : materialList){
//                        if (material.getId() == recipe.getMaterialId()){
//                            temp = material.getAmount()/recipe.getAmount();
//                            break;
//                        }
//                    }
//                    if (min==-1) min = temp;
//                    else if (temp < min) min = temp;
//                }
//            }
//            if (min==-1){
//                product.setAmount(0);
//            }
//            else {
//                product.setAmount(min);
//            }
//
//            productService.modifyProduct(product.getName(),product.getAmount(),product.getUnit(),
//                    product.getPrice(),product.getCategoryId(),product.getId());
//        }
//    }
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
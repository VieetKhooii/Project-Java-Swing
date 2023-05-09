package GUI;

import model.Staff;
import model.Supplier;
import service.SupplierService;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class SupplierGUI extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel staffListPanel;
    private JTable supplierTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane supplierScrollPane;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel searchPanel;
    private JLabel lblNewLabel;
    private JTextField idSupplierTxt;
    private JTextField nameSupplierTxt;
    private JTextField addressSupplierTxt;
    private JTextField phoneNumbTxt;
    private JPanel staffInfoPanel;
    private JButton addSupplierBtn;
    private JButton fixSupplierBtn;
    private JButton delSupplierBtn;
    private JComboBox<String> searchCbB;
    private JTextField searchTxt;
    private JLabel lblSpXp;
    private JComboBox<String> sortCbB;
    private JLabel lblTmKim;
    private JButton searchButton;
    private JButton clearInfoBtn;
    private JButton rmSearchButton;
    SupplierService supplierService = new SupplierService();
    List<Supplier> supplierList = supplierService.getAllSupplier();
    Color defaultColor = new Color(0, 0, 0, 80);
    /**
     * Create the panel.
     */
    public SupplierGUI() {    	
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
        staffListPanel = new JPanel(null);
        staffListPanel.setBackground(defaultColor);
        staffListPanel.setBounds(0, 340, 1080, 360);

        contentField.add(staffListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại"}, 0);
        supplierTable = new JTable(detailTableModel);
        supplierTable.setFont(new Font("Arial", Font.PLAIN, 14));
        supplierTable.setDefaultRenderer(String.class, centerRenderer);
        supplierTable.setRowHeight(30);
        for(int i = 0; i < 4; i++) {
            if(i == 1 || i == 2) {
                supplierTable.getColumnModel().getColumn(i).setPreferredWidth(250);
                supplierTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                supplierTable.getColumnModel().getColumn(i).setPreferredWidth(125);
                supplierTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        ListSelectionModel listSelectionModel = supplierTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = supplierTable.getSelectedRow();
                if (row >= 0 ){
                    idSupplierTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    nameSupplierTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    addressSupplierTxt.setText(detailTableModel.getValueAt(row, 2).toString());
                    phoneNumbTxt.setText(detailTableModel.getValueAt(row, 3).toString());
                }
            }
        });        

        
        supplierScrollPane = new JScrollPane(supplierTable);
        supplierScrollPane.setBounds(5, 5, 1070, 350);
        staffListPanel.add(supplierScrollPane);

        staffInfoPanel = new JPanel();
        staffInfoPanel.setBackground(defaultColor);
        staffInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffInfoPanel.setBounds(0, 50, 600, 290);
        contentField.add(staffInfoPanel);
        staffInfoPanel.setLayout(null);

        lblNewLabel = new JLabel("Thông tin nhà cung cấp");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(200, 0, 200, 40);
        staffInfoPanel.add(lblNewLabel);

        JLabel idSupplierLabel = new JLabel("Mã NCC");
        idSupplierLabel.setForeground(new Color(255, 255, 255));
        idSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idSupplierLabel.setBounds(60, 50, 70, 30);
        staffInfoPanel.add(idSupplierLabel);

        idSupplierTxt = new JTextField();
        idSupplierTxt.setBackground(new Color(255, 255, 255));
        idSupplierTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idSupplierTxt.setColumns(10);
        idSupplierTxt.setBounds(143, 50, 167, 29);
        idSupplierTxt.setEditable(false);
        staffInfoPanel.add(idSupplierTxt);

        nameSupplierTxt = new JTextField();        
        nameSupplierTxt.setBackground(new Color(255, 255, 255));
        nameSupplierTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameSupplierTxt.setColumns(10);
        nameSupplierTxt.setBounds(143, 110, 167, 30);
        staffInfoPanel.add(nameSupplierTxt);

        JLabel nameSupplierLabel = new JLabel("Tên NCC");
        nameSupplierLabel.setForeground(new Color(255, 255, 255));
        nameSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameSupplierLabel.setBounds(60, 110, 70, 30);
        staffInfoPanel.add(nameSupplierLabel);

        JLabel addressSupplierLabel = new JLabel("Địa chỉ");
        addressSupplierLabel.setForeground(new Color(255, 255, 255));
        addressSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        addressSupplierLabel.setBounds(60, 170, 70, 30);
        staffInfoPanel.add(addressSupplierLabel);

        addressSupplierTxt = new JTextField();        
        addressSupplierTxt.setBackground(new Color(255, 255, 255));
        addressSupplierTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        addressSupplierTxt.setColumns(10);
        addressSupplierTxt.setBounds(143, 170, 167, 30);
        staffInfoPanel.add(addressSupplierTxt);

        JLabel phoneNumbLabel = new JLabel("SĐT");
        phoneNumbLabel.setForeground(new Color(255, 255, 255));
        phoneNumbLabel.setFont(new Font("Arial", Font.BOLD, 13));
        phoneNumbLabel.setBounds(60, 230, 70, 30);
        staffInfoPanel.add(phoneNumbLabel);

        phoneNumbTxt = new JTextField();
        phoneNumbTxt.setBackground(new Color(255, 255, 255));
        phoneNumbTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        phoneNumbTxt.setColumns(10);
        phoneNumbTxt.setBounds(143, 230, 167, 30);
        staffInfoPanel.add(phoneNumbTxt);

        addSupplierBtn = new JButton("Thêm");
        addSupplierBtn.setBorder(null);
        addSupplierBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!idSupplierTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Không được chọn nhà cung cấp đã có sẵn để thêm! Khi thêm id phải để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameSupplierTxt.getText().equals("") || addressSupplierTxt.getText().equals("") || phoneNumbTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else if (!phoneNumbTxt.getText().matches("0[123456789]{1}\\d{8}")) {
                	JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu từ 0 và đủ 10 số!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    supplierService.addSupplier(nameSupplierTxt.getText(),addressSupplierTxt.getText(), phoneNumbTxt.getText());
                    showTableSupplier();
                    JOptionPane.showMessageDialog(null, "Đã thêm nhà cung cấp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addSupplierBtn.setFont(new Font("Arial", Font.BOLD, 13));
        addSupplierBtn.setBounds(400, 50, 90, 35);
        staffInfoPanel.add(addSupplierBtn);

        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.setBorder(null);
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supplierTable.clearSelection();
                idSupplierTxt.setText(null);
                nameSupplierTxt.setText(null);
                addressSupplierTxt.setText(null);
                addressSupplierTxt.setText(null);
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.BOLD, 13));
        clearInfoBtn.setBounds(400, 232, 90, 35);
        staffInfoPanel.add(clearInfoBtn);

        fixSupplierBtn = new JButton("Cập nhật");
        fixSupplierBtn.setBorder(null);
        fixSupplierBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idSupplierTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 nhà cung cấp và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameSupplierTxt.getText().equals("") || addressSupplierTxt.getText().equals("") || phoneNumbTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else if (!phoneNumbTxt.getText().matches("0[123456789]{1}\\d{8}")) {
                	JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu từ 0 và đủ 10 số!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    supplierService.supplierModify(Integer.parseInt(idSupplierTxt.getText()),nameSupplierTxt.getText(),addressSupplierTxt.getText(), phoneNumbTxt.getText());
                    showTableSupplier();
                    clearInfoBtn.doClick();
                    JOptionPane.showMessageDialog(null, "Đã thêm nhà cung cấp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        fixSupplierBtn.setFont(new Font("Arial", Font.BOLD, 13));
        fixSupplierBtn.setBounds(400, 112, 90, 35);
        staffInfoPanel.add(fixSupplierBtn);

        delSupplierBtn = new JButton("Xóa");
        delSupplierBtn.setBorder(null);
        delSupplierBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idSupplierTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 nhà cung cấp và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    //xoa o day
                    if(decide == 0) {
                        supplierService.supplierDetele(Integer.parseInt(idSupplierTxt.getText()));
                        clearInfoBtn.doClick();
                        showTableSupplier();
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        delSupplierBtn.setFont(new Font("Arial", Font.BOLD, 13));
        delSupplierBtn.setBounds(400, 172, 90, 35);
        staffInfoPanel.add(delSupplierBtn);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBackground(defaultColor);
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel supplierLabel = new JLabel("NHÀ CUNG CẤP");
        supplierLabel.setForeground(new Color(255, 255, 255));
        supplierLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(supplierLabel);
        supplierLabel.setHorizontalAlignment(SwingConstants.CENTER);
        supplierLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        searchPanel.setBackground(defaultColor);
        searchPanel.setBounds(600, 50, 480, 290);
        contentField.add(searchPanel);
        searchPanel.setLayout(null);

        searchCbB = new JComboBox<String>();
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã NCC", "Tên NCC", "Số điện thoại"}));
        searchCbB.setBounds(101, 75, 130, 40);
        searchPanel.add(searchCbB);

        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        searchTxt.setColumns(10);
        searchTxt.setBounds(241, 75, 149, 40);
        searchPanel.add(searchTxt);

        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setForeground(new Color(255, 255, 255));
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(101, 145, 130, 40);
        searchPanel.add(lblSpXp);

        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Mã NCC giảm dần", "Tên NCC"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(241, 145, 149, 40);
        searchPanel.add(sortCbB);

        lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setForeground(new Color(255, 255, 255));
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(184, 0, 120, 40);
        searchPanel.add(lblTmKim);

        searchButton = new JButton("OK");
        searchButton.setBorder(null);
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {  
        		showSearchResult(searchTxt.getText(), searchCbB.getSelectedItem().toString().trim(), sortCbB.getSelectedItem().toString().trim());
        	}
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(160, 229, 100, 50);
        searchPanel.add(searchButton);
        
        rmSearchButton = new JButton("Hủy");
        rmSearchButton.setBorder(null);
        rmSearchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchTxt.setText("");
        		showTableSupplier();
        		sortCbB.setSelectedIndex(0);
        	}
        });
        rmSearchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        rmSearchButton.setBounds(259, 229, 100, 50);
        searchPanel.add(rmSearchButton);
        
        JLabel imageLabel = new JLabel("New label");
        imageLabel.setIcon(new ImageIcon("C:\\Users\\Magaki\\Downloads\\Java Downloads\\5374796.png"));
        imageLabel.setBounds(0, 0, 1080, 700);
        contentField.add(imageLabel);
        
        //End
        showTableSupplier();


    }
    private void showTableSupplier(){
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        supplierList = supplierService.getAllSupplier();
        for(Supplier supplier : supplierList) {
            detailTableModel.addRow(new Object[] {
                    supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getPhone()
            });
        }
    }
    
    private void showSearchResult(String searchTxt, String optSearch, String optSort) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<Supplier> searchResultList = supplierService.getAllSearchResult(searchTxt, optSearch, optSort);
        for(Supplier supplier : searchResultList) {
            detailTableModel.addRow(new Object[] {
                    supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getPhone()
            });
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


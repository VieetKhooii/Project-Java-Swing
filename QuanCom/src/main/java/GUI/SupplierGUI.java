package GUI;

import model.Supplier;
import service.SupplierService;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

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
        staffListPanel.setBackground(new Color(30, 144, 255));
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
        staffInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffInfoPanel.setBounds(0, 50, 600, 290);
        contentField.add(staffInfoPanel);
        staffInfoPanel.setLayout(null);

        lblNewLabel = new JLabel("Thông tin nhà cung cấp");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(200, 0, 200, 40);
        staffInfoPanel.add(lblNewLabel);

        JLabel idSupplierLabel = new JLabel("Mã NCC");
        idSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idSupplierLabel.setBounds(60, 50, 70, 30);
        staffInfoPanel.add(idSupplierLabel);

        idSupplierTxt = new JTextField();
        idSupplierTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idSupplierTxt.setColumns(10);
        idSupplierTxt.setBounds(143, 50, 167, 30);
        idSupplierTxt.setEditable(false);
        staffInfoPanel.add(idSupplierTxt);

        nameSupplierTxt = new JTextField();
        nameSupplierTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameSupplierTxt.setColumns(10);
        nameSupplierTxt.setBounds(143, 110, 167, 30);
        staffInfoPanel.add(nameSupplierTxt);

        JLabel nameSupplierLabel = new JLabel("Tên NCC");
        nameSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameSupplierLabel.setBounds(60, 110, 70, 30);
        staffInfoPanel.add(nameSupplierLabel);

        JLabel addressSupplierLabel = new JLabel("Địa chỉ");
        addressSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        addressSupplierLabel.setBounds(60, 170, 70, 30);
        staffInfoPanel.add(addressSupplierLabel);

        addressSupplierTxt = new JTextField();
        addressSupplierTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        addressSupplierTxt.setColumns(10);
        addressSupplierTxt.setBounds(143, 170, 167, 30);
        staffInfoPanel.add(addressSupplierTxt);

        JLabel phoneNumbLabel = new JLabel("SĐT");
        phoneNumbLabel.setFont(new Font("Arial", Font.BOLD, 13));
        phoneNumbLabel.setBounds(60, 230, 70, 30);
        staffInfoPanel.add(phoneNumbLabel);

        phoneNumbTxt = new JTextField();
        phoneNumbTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        phoneNumbTxt.setColumns(10);
        phoneNumbTxt.setBounds(143, 230, 167, 30);
        staffInfoPanel.add(phoneNumbTxt);

        addSupplierBtn = new JButton("Thêm");
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
        addSupplierBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addSupplierBtn.setBounds(400, 50, 90, 35);
        staffInfoPanel.add(addSupplierBtn);

        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supplierTable.clearSelection();
                idSupplierTxt.setText(null);
                nameSupplierTxt.setText(null);
                addressSupplierTxt.setText(null);
                addressSupplierTxt.setText(null);
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        clearInfoBtn.setBounds(400, 232, 90, 35);
        staffInfoPanel.add(clearInfoBtn);

        fixSupplierBtn = new JButton("Cập nhật");
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
        fixSupplierBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        fixSupplierBtn.setBounds(400, 112, 90, 35);
        staffInfoPanel.add(fixSupplierBtn);

        delSupplierBtn = new JButton("Xóa");
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
        delSupplierBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delSupplierBtn.setBounds(400, 172, 90, 35);
        staffInfoPanel.add(delSupplierBtn);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel supplierLabel = new JLabel("NHÀ CUNG CẤP");
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
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã NCC", "Tên NCC", "Số điện thoại"}));
        searchCbB.setBounds(130, 75, 101, 40);
        searchPanel.add(searchCbB);

        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        searchTxt.setColumns(10);
        searchTxt.setBounds(241, 75, 149, 40);
        searchPanel.add(searchTxt);

        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(130, 145, 80, 40);
        searchPanel.add(lblSpXp);

        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Mã NCC giảm dần", "Tên NCC"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(241, 145, 149, 40);
//        sortCbB.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if (sortCbB.getSelectedItem().equals(sortCbB.getItemAt(0))){
//                    supplierService.quickSort(supplierList,"id");
//                }
//                else if (sortCbB.getSelectedItem().equals(sortCbB.getItemAt(1))){
//                    supplierService.quickSort(supplierList,"name");
//                }
//                else {
//                    supplierService.quickSort(supplierList,"address");
//                }
//                //display on screen after sorting
//                while (detailTableModel.getRowCount() != 0){
//                    detailTableModel.removeRow(0);
//                }
//                for(Supplier supplier : supplierList) {
//                    detailTableModel.addRow(new Object[] {
//                            supplier.getId(), supplier.getName(), supplier.getAddress()
//                    });
//                }
//            }
//        });
        searchPanel.add(sortCbB);

        lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(185, 11, 120, 40);
        searchPanel.add(lblTmKim);

        searchButton = new JButton("OK");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {  
        		boolean none = false;
                boolean id = false;
                boolean name = false;
                if(sortCbB.getSelectedItem().toString().equals("None")) {
                	none = true;
                }
                else if(sortCbB.getSelectedItem().toString().equals("Mã NCC giảm dần")) {
                	id = true;
                }
                else if(sortCbB.getSelectedItem().toString().equals("Tên NCC")) {
                	name = true;
                }                
        		if(searchCbB.getSelectedItem().toString().equals("Mã NCC")) {
        			if(!searchTxt.getText().equals("")) {
        				showSearchResultById(searchTxt.getText(), none, name, id);
        			}        			
    			}
    			if(searchCbB.getSelectedItem().toString().equals("Tên NCC")){   				
    				if(!searchTxt.getText().equals("")) {
    					showSearchResultByName(searchTxt.getText(), none, name, id);
    				}
    			}
    			if(searchCbB.getSelectedItem().toString().equals("Số điện thoại")) {
    				if(!searchTxt.getText().equals("")) {
    					showSearchResultByPhoneNumber(searchTxt.getText(), none, name, id);
    				}
    			}
    			if(searchTxt.getText().equals("")) {
    				showSortTable(none, name, id);    				
    			}
        	}
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(160, 229, 100, 50);
        searchPanel.add(searchButton);
        
        rmSearchButton = new JButton("Hủy");
        rmSearchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchTxt.setText("");
        		showSortTable(true, false, false);
        		sortCbB.setSelectedIndex(0);
        	}
        });
        rmSearchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        rmSearchButton.setBounds(259, 229, 100, 50);
        searchPanel.add(rmSearchButton);
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
    
    private List<Supplier> showSortTable(boolean none, boolean name, boolean id) {
    	List<Supplier> sortResultList = null;
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
    	if(none) {
    		sortResultList = supplierService.getAllSupplier();
    		if(searchTxt.getText().equals("")) {
    			showTableSupplier();
    		}
    	}
    	if(name) {
    		sortResultList = supplierService.sortByName(supplierList);
    		if(searchTxt.getText().equals("")) {
    			for(Supplier i : sortResultList) {
    				detailTableModel.addRow(new Object[] {
    	                    i.getId(), i.getName(), i.getAddress(), i.getPhone()
    	            });
                }
    		}
    	}
    	if(id) {
    		sortResultList = supplierService.sortById(supplierList);
    		if(searchTxt.getText().equals("")) {
    			for(Supplier i : sortResultList) {
    				detailTableModel.addRow(new Object[] {
    	                    i.getId(), i.getName(), i.getAddress(), i.getPhone()
    	            });
                }
    		}		
    	}
		return sortResultList;
    }
    
    private void showSearchResultByName(String name, boolean none, boolean name2, boolean id) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<Supplier> searchResultList = supplierService.searchByName(name, showSortTable(none, name2, id));
        for(Supplier i : searchResultList) {
        	detailTableModel.addRow(new Object[] {
                    i.getId(), i.getName(), i.getAddress(), i.getPhone()
            });
        }
    }
    
    private void showSearchResultById(String id, boolean none, boolean name, boolean id2) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<Supplier> searchResultList = supplierService.searchById(id, showSortTable(none, name, id2));
        for(Supplier i : searchResultList) {
        	detailTableModel.addRow(new Object[] {
                    i.getId(), i.getName(), i.getAddress(), i.getPhone()
            });
        }
    }
    
    private void showSearchResultByPhoneNumber(String phoneNumber, boolean none, boolean name, boolean id) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<Supplier> searchResultList = supplierService.searchByPhoneNumber(phoneNumber, showSortTable(none, name, id));
        for(Supplier i : searchResultList) {
        	detailTableModel.addRow(new Object[] {
                    i.getId(), i.getName(), i.getAddress(), i.getPhone()
            });
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
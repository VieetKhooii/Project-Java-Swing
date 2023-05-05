package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import model.Functions;
import model.Role_Func;
import model.Roles;
import model.Staff;
import model.User;
import service.FunctionService;
import service.RoleFuncService;
import service.RoleService;
import service.UserService;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RolesGUI extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel accListPanel;
    private JTable staffTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane accScrollPane;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel searchPanel;
    private JLabel lblNewLabel;
    private JTextField idRoleTxt;
    private JTextField nameRoleTxt;
    private JTextArea descriptionTextArea;
    private JPanel staffInfoPanel;
    private JButton addAccBtn;
    private JButton fixAccBtn;
    private JButton delAccBtn;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JCheckBox createOrderCB;
    private JCheckBox productsCB;
    private JCheckBox accountCB;
    private JCheckBox createReceivingCB;
    private JCheckBox materialCB;
    private JCheckBox supplierCB;
    private JCheckBox staffCB;
    private JButton clearInfoBtn;
    private JTextField searchTxt;
    private JComboBox<String> searchCbB;
    private JComboBox<String> sortCbB;
    private JButton searchButton;
    RoleService roleService = new RoleService();
    FunctionService functionService = new FunctionService();
    RoleFuncService roleFuncService = new RoleFuncService();
    UserService userService = new UserService();
    List<Roles> rolesList = roleService.getAllRoles();
    List<Functions> functionsList = functionService.getAllFunctions();
    List<Functions> role_funcList = new ArrayList<>();
    List<JCheckBox> checkBoxList = new ArrayList<>();
    /**
     * Create the panel.
     */
    public RolesGUI() {
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
        accListPanel = new JPanel(null);
        accListPanel.setBackground(new Color(30, 144, 255));
        accListPanel.setBounds(0, 380, 1080, 290);

        contentField.add(accListPanel);
        /////////////////////////////////////////////////////////////////
        detailTableModel = new DefaultTableModel(new Object[]{"Mã quyền", "Tên quyền", "Mô tả"}, 0);
        staffTable = new JTable(detailTableModel);
        staffTable.setFont(new Font("Arial", Font.PLAIN, 14));
        staffTable.setDefaultRenderer(String.class, centerRenderer);
        staffTable.setRowHeight(30);
        for(int i = 0; i < 3; i++) {
            if(i == 2) {
                staffTable.getColumnModel().getColumn(i).setPreferredWidth(150);
                staffTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                //staffTable.getColumnModel().getColumn(i).setPreferredWidth(125);
                staffTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        ListSelectionModel listSelectionModel = staffTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = staffTable.getSelectedRow();
                if (row >= 0) {
                    for (JCheckBox checkBox : checkBoxList){
                        checkBox.setSelected(false);
                    }
                    idRoleTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    nameRoleTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    descriptionTextArea.setText(detailTableModel.getValueAt(row, 2).toString());
                    for (Functions functions : functionsList){
                        Role_Func roleFunc = new Role_Func();
                        role_funcList = roleFuncService.funcOfRole(Integer.parseInt(idRoleTxt.getText()));
                        for (Functions functions1 : role_funcList){
                            if (functions1.getId() == functions.getId()){
                                for (JCheckBox checkBox : checkBoxList){
                                    if (checkBox.getText().equalsIgnoreCase(functions.getName())){
                                        checkBox.setSelected(true);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        });

        accScrollPane = new JScrollPane(staffTable);
        accScrollPane.setBounds(5, 5, 1070, 280);
        accListPanel.add(accScrollPane);
        ///////////////////////////////////////////////////////////////////
        staffInfoPanel = new JPanel();
        staffInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffInfoPanel.setBounds(0, 50, 800, 330);
        contentField.add(staffInfoPanel);
        staffInfoPanel.setLayout(null);

        lblNewLabel = new JLabel("Thông tin quyền");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(321, 0, 150, 40);
        staffInfoPanel.add(lblNewLabel);

        JLabel idRoleLabel = new JLabel("Mã quyền");
        idRoleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idRoleLabel.setBounds(110, 51, 70, 30);
        staffInfoPanel.add(idRoleLabel);

        idRoleTxt = new JTextField();
        idRoleTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idRoleTxt.setColumns(10);
        idRoleTxt.setBounds(190, 51, 170, 30);
        idRoleTxt.setEditable(false);
        staffInfoPanel.add(idRoleTxt);

        nameRoleTxt = new JTextField();
        nameRoleTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameRoleTxt.setColumns(10);
        nameRoleTxt.setBounds(190, 101, 170, 30);
        staffInfoPanel.add(nameRoleTxt);

        JLabel nameRoleLabel = new JLabel("Tên quyền");
        nameRoleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameRoleLabel.setBounds(110, 101, 70, 30);
        staffInfoPanel.add(nameRoleLabel);

        /////////////////////////////////////////////////////////
        //CheckBox functions

        createOrderCB = new JCheckBox("Đơn hàng");
        createOrderCB.setFont(new Font("Arial", Font.PLAIN, 12));
        createOrderCB.setBounds(445, 80, 110, 25);
        staffInfoPanel.add(createOrderCB);
        checkBoxList.add(createOrderCB);

        createReceivingCB = new JCheckBox("Nhập hàng");
        createReceivingCB.setFont(new Font("Arial", Font.PLAIN, 12));
        createReceivingCB.setBounds(595, 80, 110, 25);
        staffInfoPanel.add(createReceivingCB);
        checkBoxList.add(createReceivingCB);

        productsCB = new JCheckBox("Món ăn");
        productsCB.setFont(new Font("Arial", Font.PLAIN, 12));
        productsCB.setBounds(445, 125, 110, 25);
        staffInfoPanel.add(productsCB);
        checkBoxList.add(productsCB);

        accountCB = new JCheckBox("Tài khoản");
        accountCB.setFont(new Font("Arial", Font.PLAIN, 12));
        accountCB.setBounds(445, 170, 110, 25);
        staffInfoPanel.add(accountCB);
        checkBoxList.add(accountCB);

        materialCB = new JCheckBox("Nguyên liệu");
        materialCB.setFont(new Font("Arial", Font.PLAIN, 12));
        materialCB.setBounds(595, 125, 110, 25);
        staffInfoPanel.add(materialCB);
        checkBoxList.add(materialCB);

        supplierCB = new JCheckBox("Nhà cung cấp");
        supplierCB.setFont(new Font("Arial", Font.PLAIN, 12));
        supplierCB.setBounds(445, 215, 110, 25);
        staffInfoPanel.add(supplierCB);
        checkBoxList.add(supplierCB);

        staffCB = new JCheckBox("Nhân viên");
        staffCB.setFont(new Font("Arial", Font.PLAIN, 12));
        staffCB.setBounds(595, 170, 110, 25);
        staffInfoPanel.add(staffCB);
        checkBoxList.add(staffCB);

        //////////////////////////////////
        addAccBtn = new JButton("Thêm");
        addAccBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!idRoleTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Không được chọn nhân viên đã có sẵn để thêm! Khi thêm id phải để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameRoleTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    if(!createOrderCB.isSelected() && !createReceivingCB.isSelected() && !productsCB.isSelected() && !accountCB.isSelected()
                            && !materialCB.isSelected() && !supplierCB.isSelected() && !staffCB.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Chọn ít nhất 1 chức năng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        roleService.addRole(nameRoleTxt.getText(),descriptionTextArea.getText());
                        for (JCheckBox jCheckBox : checkBoxList){
                            if (jCheckBox.isSelected()){
                                for (Functions functions : functionsList){
                                    if (jCheckBox.getText().equalsIgnoreCase(functions.getName())){
                                        Functions function = new Functions();
                                        function.setId(functions.getId());
                                        rolesList = roleService.getAllRoles();
                                        roleFuncService.addFuncOfRole(function.getId(), rolesList.get(rolesList.size() - 1).getId());
                                    }
                                }
                            }
                        }
                        showTableRoles();
                        JOptionPane.showMessageDialog(null, "Đã thêm quyền!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        AccountAndRoleGUI.accountPanel.positioncbB.addItem(nameRoleTxt.getText());                      
                    }
                }
            }
        });
        addAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addAccBtn.setBounds(222, 284, 90, 35);
        staffInfoPanel.add(addAccBtn);

        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffTable.clearSelection();
                for (JCheckBox checkBox : checkBoxList){
                    checkBox.setSelected(false);
                }
                idRoleTxt.setText(null);
                nameRoleTxt.setText(null);
                descriptionTextArea.setText(null);
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        clearInfoBtn.setBounds(486, 284, 90, 35);
        staffInfoPanel.add(clearInfoBtn);

        fixAccBtn = new JButton("Cập nhật");
        fixAccBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idRoleTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 quyền và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameRoleTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    if(!createOrderCB.isSelected() && !createReceivingCB.isSelected() && !productsCB.isSelected() && !accountCB.isSelected()
                            && !materialCB.isSelected() && !supplierCB.isSelected() && !staffCB.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Chọn ít nhất 1 chức năng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        roleService.roleModify(Integer.parseInt(idRoleTxt.getText()),nameRoleTxt.getText(),descriptionTextArea.getText());
                        for (JCheckBox jCheckBox : checkBoxList){
                            if (jCheckBox.isSelected()){
                                for (Functions functions : functionsList){
                                    if (jCheckBox.getText().equalsIgnoreCase(functions.getName())){
                                        Functions function = new Functions();
                                        function.setId(functions.getId());
                                        roleFuncService.addFuncOfRole(function.getId(), Integer.parseInt(idRoleTxt.getText()));
                                    }
                                }
                            }
                            else {
                                for (Functions functions : functionsList){
                                    if (jCheckBox.getText().equalsIgnoreCase(functions.getName())){
                                        Functions function = new Functions();
                                        function.setId(functions.getId());
                                        roleFuncService.deleteSpecificFuncOfRole(function.getId(), Integer.parseInt(idRoleTxt.getText()));
                                    }
                                }
                            }
                        }
                        showTableRoles();
                        clearInfoBtn.doClick();
                        JOptionPane.showMessageDialog(null, "Đã sửa quyền!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        fixAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        fixAccBtn.setBounds(310, 284, 90, 35);
        staffInfoPanel.add(fixAccBtn);

        delAccBtn = new JButton("Xóa");
        delAccBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idRoleTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 quyền và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (decide == 0) {
                        List<User> userList = userService.getAllUsers();
                        for (User user : userList){
                            if (user.getRoleId() == Integer.parseInt(idRoleTxt.getText())){
                                userService.deleteUser(user.getId());
                            }
                        }
                        roleFuncService.deteleFuncRole(Integer.parseInt(idRoleTxt.getText()));
                        roleService.roleDetele(Integer.parseInt(idRoleTxt.getText()));
                        clearInfoBtn.doClick();
                        showTableRoles();
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        delAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delAccBtn.setBounds(397, 284, 90, 35);
        staffInfoPanel.add(delAccBtn);

        //////////////////////////////////////

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setBounds(190, 151, 170, 80);
        descriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.gray));
        descriptionTextArea.setRows(10);
        staffInfoPanel.add(descriptionTextArea);

        JLabel lblNewLabel_1 = new JLabel("Mô tả");
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel_1.setBounds(110, 151, 70, 30);
        staffInfoPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Chức năng");
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel_2.setBounds(445, 50, 70, 20);
        staffInfoPanel.add(lblNewLabel_2);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel staffLabel = new JLabel("PHÂN QUYỀN TRUY CẬP");
        staffLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(staffLabel);
        staffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staffLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        searchPanel.setBounds(800, 50, 280, 330);
        contentField.add(searchPanel);
        searchPanel.setLayout(null);

        JLabel lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(80, 0, 120, 40);
        searchPanel.add(lblTmKim);

        searchCbB = new JComboBox<String>();
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã quyền", "Tên quyền"}));
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setBounds(10, 70, 101, 40);
        searchPanel.add(searchCbB);

        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        searchTxt.setColumns(10);
        searchTxt.setBounds(121, 70, 149, 40);
        searchPanel.add(searchTxt);

        JLabel lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(10, 140, 80, 40);
        searchPanel.add(lblSpXp);

        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Mã quyền giảm dần", "Tên quyền"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(121, 140, 100, 40);
//        sortCbB.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if (sortCbB.getSelectedItem().equals(sortCbB.getItemAt(0))){
//                    roleService.quickSort(rolesList,true);
//                }
//                else {
//                    roleService.quickSort(rolesList,false);
//                }
//                //display on screen after sorting
//                while (detailTableModel.getRowCount() != 0){
//                    detailTableModel.removeRow(0);
//                }
//                for(Roles roles : rolesList) {
//                    detailTableModel.addRow(new Object[] {
//                            roles.getId(), roles.getName(), roles.getDescription()
//                    });
//                }
//            }
//        });
        searchPanel.add(sortCbB);
        
        searchButton = new JButton("OK");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {  
        		boolean none = false;
                boolean id = false;
                boolean name = false;
                if(sortCbB.getSelectedItem().toString().equals("None")) {
                	none = true;
                }
                else if(sortCbB.getSelectedItem().toString().equals("Mã quyền giảm dần")) {
                	id = true;
                }
                else if(sortCbB.getSelectedItem().toString().equals("Tên quyền")) {
                	name = true;
                }                
        		if(searchCbB.getSelectedItem().toString().equals("Mã quyền")) {
        			if(!searchTxt.getText().equals("")) {
        				showSearchResultById(searchTxt.getText(), none, name, id);
        			}        			
    			}
    			if(searchCbB.getSelectedItem().toString().equals("Tên quyền")){   				
    				if(!searchTxt.getText().equals("")) {
    					showSearchResultByName(searchTxt.getText(), none, name, id);
    				}
    			}
    			if(searchTxt.getText().equals("")) {
    				showSortTable(none, name, id);    				
    			}
        	}
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(45, 269, 100, 50);
        searchPanel.add(searchButton);
        
        JButton rmSearchBtn = new JButton("Hủy");
        rmSearchBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchTxt.setText("");
        		showSortTable(true, false, false);
        		sortCbB.setSelectedIndex(0);
        	}
        });
        rmSearchBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        rmSearchBtn.setBounds(144, 269, 100, 50);
        searchPanel.add(rmSearchBtn);
        //End
        showTableRoles();


    }
    
    private void showTableRoles(){
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        functionsList = functionService.getAllFunctions();
        rolesList = roleService.getAllRoles();
        for(Roles roles : rolesList) {
            detailTableModel.addRow(new Object[] {
                    roles.getId(), roles.getName(), roles.getDescription()
            });
        }
    }
    
    private List<Roles> showSortTable(boolean none, boolean name, boolean id) {
    	List<Roles> sortResultList = null;
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
    	if(none) {
    		sortResultList = roleService.getAllRoles();
    		if(searchTxt.getText().equals("")) {
    			showTableRoles();
    		}
    	}
    	if(name) {
    		sortResultList = roleService.sortByName(rolesList);
    		if(searchTxt.getText().equals("")) {
    			for(Roles i : sortResultList) {
    				detailTableModel.addRow(new Object[] {
    	                    i.getId(), i.getName(), i.getDescription()
    	            });
                }
    		}
    	}
    	if(id) {
    		sortResultList = roleService.sortById(rolesList);
    		if(searchTxt.getText().equals("")) {
    			for(Roles i : sortResultList) {
    				detailTableModel.addRow(new Object[] {
    	                    i.getId(), i.getName(), i.getDescription()
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
        List<Roles> searchResultList = roleService.searchByName(name, showSortTable(none, name2, id));
        for(Roles i : searchResultList) {
			detailTableModel.addRow(new Object[] {
                    i.getId(), i.getName(), i.getDescription()
            });
        }
        
    }
    private void showSearchResultById(String id, boolean none, boolean name, boolean id2) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<Roles> searchResultList = roleService.searchById(id, showSortTable(none, name, id2));
        for(Roles i : searchResultList) {
        	detailTableModel.addRow(new Object[] {
                    i.getId(), i.getName(), i.getDescription()
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
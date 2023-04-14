package main.java.GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class RolesGUI extends JPanel implements MouseListener, ActionListener{

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
	Pair<String, String> createOrderPair;
	Pair<String, String> createReceivingPair;
	Pair<String, String> productsPair;
	Pair<String, String> accountPair;
	Pair<String, String> materialPair;
	Pair<String, String> supplierPair;
	Pair<String, String> staffPair;
	private JTextField textField;
	private JComboBox<String> searchCbB;
	private JComboBox<String> sortCbB;
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
        
        //////////////////////////////////
        addAccBtn = new JButton("Thêm");      
        addAccBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(idRoleTxt.getText().equals("") || nameRoleTxt.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);       			
        		}
        		else {
        			if(!createOrderCB.isSelected() && !createReceivingCB.isSelected() && !productsCB.isSelected() && !accountCB.isSelected() 
        					&& !materialCB.isSelected() && !supplierCB.isSelected() && !staffCB.isSelected()) {
        				JOptionPane.showMessageDialog(null, "Chọn ít nhất 1 chức năng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        			}
        			else {
        				JOptionPane.showMessageDialog(null, "Đã thêm quyền!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        			}
        		}
        	}
        });
        addAccBtn.setFont(new Font("Arial", Font.PLAIN, 13)); 
        addAccBtn.setBounds(270, 280, 90, 35);
        staffInfoPanel.add(addAccBtn);
        
        fixAccBtn = new JButton("Cập nhật");       
        fixAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));       
        fixAccBtn.setBounds(358, 280, 90, 35);
        staffInfoPanel.add(fixAccBtn);
        
        delAccBtn = new JButton("Xóa");     
        delAccBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        		//xoa o day
        		if(decide == 0) {
        			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        delAccBtn.setFont(new Font("Arial", Font.PLAIN, 13));       
        delAccBtn.setBounds(445, 280, 90, 35);
        staffInfoPanel.add(delAccBtn);
        /////////////////////////////////////////////////////////
        //CheckBox functions
        
        createOrderCB = new JCheckBox("Đơn hàng");
        createOrderCB.setFont(new Font("Arial", Font.PLAIN, 12));
        createOrderCB.setBounds(445, 80, 110, 25);
        createOrderPair = new Pair<>("1", "Đơn hàng");
        staffInfoPanel.add(createOrderCB);
        
        createReceivingCB = new JCheckBox("Nhập hàng");
        createReceivingCB.setFont(new Font("Arial", Font.PLAIN, 12));
        createReceivingCB.setBounds(595, 80, 110, 25);
        createReceivingPair = new Pair<>("2", "Nhập hàng");
        staffInfoPanel.add(createReceivingCB); 
        
        productsCB = new JCheckBox("Món ăn");
        productsCB.setFont(new Font("Arial", Font.PLAIN, 12));
        productsCB.setBounds(445, 125, 110, 25);
        productsPair = new Pair<>("3", "Món ăn");
        staffInfoPanel.add(productsCB);
        
        accountCB = new JCheckBox("Tài khoản");
        accountCB.setFont(new Font("Arial", Font.PLAIN, 12));        
        accountCB.setBounds(445, 170, 110, 25);
        accountPair = new Pair<>("5", "Tài khoản");
        staffInfoPanel.add(accountCB);
        
        materialCB = new JCheckBox("Nguyên liệu");
        materialCB.setFont(new Font("Arial", Font.PLAIN, 12));
        materialCB.setBounds(595, 125, 110, 25);
        materialPair = new Pair<>("4", "Nguyên liệu");
        staffInfoPanel.add(materialCB);
        
        supplierCB = new JCheckBox("Nhà cung cấp");
        supplierCB.setFont(new Font("Arial", Font.PLAIN, 12));
        supplierCB.setBounds(445, 215, 110, 25);
        supplierPair = new Pair<>("7", "Nhà cung cấp");
        staffInfoPanel.add(supplierCB);
        
        staffCB = new JCheckBox("Nhân viên");
        staffCB.setFont(new Font("Arial", Font.PLAIN, 12));
        staffCB.setBounds(595, 170, 110, 25);
        staffPair = new Pair<>("6", "Nhân viên");
        staffInfoPanel.add(staffCB);
        
        //////////////////////////////////////
        
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setBounds(190, 151, 170, 80);
        descriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.gray));
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
        
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        textField.setColumns(10);
        textField.setBounds(121, 70, 149, 40);
        searchPanel.add(textField);
        
        JLabel lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(10, 140, 80, 40);
        searchPanel.add(lblSpXp);
        
        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã quyền", "Tên quyền"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(121, 140, 100, 40);
        searchPanel.add(sortCbB);
        
        JButton searchButton = new JButton("OK");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(100, 269, 100, 50);
        searchPanel.add(searchButton);
		//End
		
		
		
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

package main.java.GUI;


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

public class SupplierGUI extends JPanel implements MouseListener, ActionListener{

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
	private JTextField textField;
	private JLabel lblSpXp;
	private JComboBox<String> sortCbB;
	private JLabel lblTmKim;
	private JButton searchButton;
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
            	idSupplierTxt.setText(detailTableModel.getValueAt(row, 0).toString());
            	nameSupplierTxt.setText(detailTableModel.getValueAt(row, 1).toString());
            	addressSupplierTxt.setText(detailTableModel.getValueAt(row, 2).toString());
            	phoneNumbTxt.setText(detailTableModel.getValueAt(row, 3).toString());
            }          
        });
        detailTableModel.addRow(new Object[] {"12", "Cocacola", "273 an duong vuong", "0123456789"});
        
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
        		if(idSupplierTxt.getText().equals("") || nameSupplierTxt.getText().equals("") || addressSupplierTxt.getText().equals("") || phoneNumbTxt.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Đã thêm nhà cung cấp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        addSupplierBtn.setFont(new Font("Arial", Font.PLAIN, 13)); 
        addSupplierBtn.setBounds(400, 67, 90, 35);
        staffInfoPanel.add(addSupplierBtn);
        
        fixSupplierBtn = new JButton("Cập nhật");       
        fixSupplierBtn.setFont(new Font("Arial", Font.PLAIN, 13));       
        fixSupplierBtn.setBounds(400, 129, 90, 35);
        staffInfoPanel.add(fixSupplierBtn);
        
        delSupplierBtn = new JButton("Xóa");     
        delSupplierBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        		//xoa o day
        		if(decide == 0) {
        			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        delSupplierBtn.setFont(new Font("Arial", Font.PLAIN, 13));       
        delSupplierBtn.setBounds(400, 189, 90, 35);
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
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã NCC", "Tên NCC", "Địa chỉ", "Số điện thoại"}));
        searchCbB.setBounds(130, 75, 101, 40);
        searchPanel.add(searchCbB);
        
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        textField.setColumns(10);
        textField.setBounds(241, 75, 149, 40);
        searchPanel.add(textField);
        
        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(130, 145, 80, 40);
        searchPanel.add(lblSpXp);
        
        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã NCC", "Tên NCC", "Địa chỉ",  "Số điện thoại"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(241, 145, 149, 40);
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

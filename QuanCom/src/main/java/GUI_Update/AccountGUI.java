package main.java.GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.formdev.flatlaf.json.ParseException;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AccountGUI extends JPanel implements MouseListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentField;
	private JPanel accListPanel;
	private JTable accTable;
	private DefaultTableModel detailTableModel;
	private JScrollPane accScrollPane;
	private DefaultTableCellRenderer centerRenderer;
	private JPanel searchPanel;
	private JLabel lblNewLabel;
	private JTextField idAccTxt;
	private JTextField nameAccTxt;
	private JTextField passTxt;
	private JPanel staffInfoPanel;
	private JButton addAccBtn;
	private JButton fixAccBtn;
	private JButton delAccBtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton blockedStatusRadioBtn;
	private JRadioButton noneStatusRadioBtn;
	private JDateChooser dateChooser;
	private JLabel lblTmKim;
	private JComboBox<String> searchCbB;
	private JTextField textField;
	private JLabel lblSpXp;
	private JComboBox<String> sortCbB;
	private JButton searchButton;
	private JComboBox<String> positioncbB;
	private JTextField emailTxt;
	/**
	 * Create the panel.
	 */
	public AccountGUI() {
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
        
        detailTableModel = new DefaultTableModel(new Object[]{"Mã tài khoản", "Tên đăng nhập", "Mật khẩu", "Trạng thái", "Ngày tạo", "Email", "Quyền"}, 0);		
        accTable = new JTable(detailTableModel);
        accTable.setFont(new Font("Arial", Font.PLAIN, 14));
        accTable.setDefaultRenderer(String.class, centerRenderer);
	    accTable.setRowHeight(30);
	    for(int i = 0; i < 7; i++) {
	    	if(i == 1 || i == 5) {
	    		accTable.getColumnModel().getColumn(i).setPreferredWidth(150);
	    		accTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    	}
	    	else {
	    		accTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    	}
	    }
	    
	    ListSelectionModel listSelectionModel = accTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){      	
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	int row = accTable.getSelectedRow();        		   		    		        
            	idAccTxt.setText(detailTableModel.getValueAt(row, 0).toString());
            	nameAccTxt.setText(detailTableModel.getValueAt(row, 1).toString());
            	passTxt.setText(detailTableModel.getValueAt(row, 2).toString());
            	if(detailTableModel.getValueAt(row, 3).toString().equals(noneStatusRadioBtn.getText())) {
            		noneStatusRadioBtn.setSelected(true);
            	}
            	else {
            		blockedStatusRadioBtn.setSelected(true);
            	}
            	
            	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            	Date date = null;
            	try {
            	    date = dateFormat.parse(detailTableModel.getValueAt(row, 4).toString());
            	} catch (ParseException | java.text.ParseException e1) {
            	    e1.printStackTrace();
            	}
            	dateChooser.setDate(date);
            	emailTxt.setText(detailTableModel.getValueAt(row, 5).toString());
            	for(int i = 0; i < positioncbB.getItemCount(); i++) {
            		if(detailTableModel.getValueAt(row, 6).toString().equals(positioncbB.getItemAt(i).toString())) {
            			positioncbB.setSelectedIndex(i);
            			break;
            		}
            	}          	
            }          
        });
	    
        detailTableModel.addRow(new Object[] {"1", "huy123", "123456", "Khóa", "4/3/2023", "123@gmail.com", "Nhân viên"});
        
        accScrollPane = new JScrollPane(accTable);
        accScrollPane.setBounds(5, 5, 1070, 280);
        accListPanel.add(accScrollPane);
        
        staffInfoPanel = new JPanel();
        staffInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffInfoPanel.setBounds(0, 50, 800, 330);
        contentField.add(staffInfoPanel);
        staffInfoPanel.setLayout(null);
        
        lblNewLabel = new JLabel("Thông tin tài khoản");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(321, 0, 150, 40);
        staffInfoPanel.add(lblNewLabel);
        
        JLabel idAccLabel = new JLabel("Mã TK");
        idAccLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idAccLabel.setBounds(140, 60, 70, 30);
        staffInfoPanel.add(idAccLabel);
        
        idAccTxt = new JTextField();
        idAccTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idAccTxt.setColumns(10);
        idAccTxt.setBounds(210, 60, 170, 30);
        staffInfoPanel.add(idAccTxt);
        
        nameAccTxt = new JTextField();
        nameAccTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameAccTxt.setColumns(10);
        nameAccTxt.setBounds(470, 60, 170, 30);
        staffInfoPanel.add(nameAccTxt);
        
        JLabel nameAccLabel = new JLabel("Tên TK");
        nameAccLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameAccLabel.setBounds(400, 60, 70, 30);
        staffInfoPanel.add(nameAccLabel);
        
        JLabel passLabel = new JLabel("Mật khẩu");
        passLabel.setFont(new Font("Arial", Font.BOLD, 13));
        passLabel.setBounds(400, 110, 70, 30);
        staffInfoPanel.add(passLabel);
        
        passTxt = new JTextField();
        passTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        passTxt.setColumns(10);
        passTxt.setBounds(470, 110, 170, 30);
        staffInfoPanel.add(passTxt);
        
        addAccBtn = new JButton("Thêm");      
        addAccBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(idAccTxt.getText().equals("") || nameAccTxt.getText().equals("") || passTxt.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Đã thêm tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
        
        JLabel lblGiiTnh = new JLabel("Trạng thái");
        lblGiiTnh.setFont(new Font("Arial", Font.BOLD, 13));
        lblGiiTnh.setBounds(140, 110, 70, 30);
        staffInfoPanel.add(lblGiiTnh);
        
        blockedStatusRadioBtn = new JRadioButton("Khóa");
        buttonGroup.add(blockedStatusRadioBtn);
        blockedStatusRadioBtn.setBounds(210, 110, 60, 30);
        staffInfoPanel.add(blockedStatusRadioBtn);
        
        noneStatusRadioBtn = new JRadioButton("None");
        noneStatusRadioBtn.setSelected(true);
        buttonGroup.add(noneStatusRadioBtn);
        noneStatusRadioBtn.setBounds(270, 110, 60, 30);
        staffInfoPanel.add(noneStatusRadioBtn);
        
        JLabel staffDateLabel = new JLabel("Ngày tạo");
        staffDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        staffDateLabel.setBounds(140, 160, 70, 30);
        staffInfoPanel.add(staffDateLabel);
        
        positioncbB = new JComboBox<>();
        positioncbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Admin", "Quản lí", "Nhân viên"}));
        positioncbB.setBounds(210, 210, 90, 30);
        staffInfoPanel.add(positioncbB);
        
        JLabel lblChcV = new JLabel("Quyền");
        lblChcV.setFont(new Font("Arial", Font.BOLD, 13));
        lblChcV.setBounds(140, 210, 70, 30);
        staffInfoPanel.add(lblChcV);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(210, 160, 170, 30);
        dateChooser.setFont(new Font("Arial", Font.PLAIN, 13));
        staffInfoPanel.add(dateChooser);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 13));
        lblEmail.setBounds(400, 160, 70, 30);
        staffInfoPanel.add(lblEmail);
        
        emailTxt = new JTextField();
        emailTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        emailTxt.setColumns(10);
        emailTxt.setBounds(470, 160, 170, 30);
        staffInfoPanel.add(emailTxt);
        
        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);
        
        JLabel staffLabel = new JLabel("TÀI KHOẢN");
        staffLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(staffLabel);
        staffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staffLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        
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
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã tài khoản", "Tên đăng nhập"}));
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setBounds(10, 64, 101, 40);
        searchPanel.add(searchCbB);
        
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        textField.setColumns(10);
        textField.setBounds(121, 64, 149, 40);
        searchPanel.add(textField);
        
        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(10, 134, 80, 40);
        searchPanel.add(lblSpXp);
        
        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã tài khoản", "Tên đăng nhập"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(121, 134, 149, 40);
        searchPanel.add(sortCbB);
        
        searchButton = new JButton("OK");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(97, 224, 100, 50);
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

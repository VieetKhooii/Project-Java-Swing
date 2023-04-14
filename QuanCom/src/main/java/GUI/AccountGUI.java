package main.java.GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class AccountGUI extends JPanel implements MouseListener, ActionListener{

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
	private JTextField idAccTxt;
	private JTextField nameAccTxt;
	private JTextField passTxt;
	private JTextField textField;
	private JButton searchButton;
	private JPanel staffInfoPanel;
	private JButton addAccBtn;
	private JButton fixAccBtn;
	private JButton delAccBtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton blockedStatusRadioBtn;
	private JRadioButton noneStatusRadioBtn;
	private JDateChooser dateChooser;
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
        
        detailTableModel = new DefaultTableModel(new Object[]{"Mã tài khoản", "Tên đăng nhập", "Trạng thái", "Ngày tạo", "Email", "Quyền"}, 0);		
        staffTable = new JTable(detailTableModel);
        staffTable.setFont(new Font("Arial", Font.PLAIN, 14));
        staffTable.setDefaultRenderer(String.class, centerRenderer);
	    staffTable.setRowHeight(30);
	    for(int i = 0; i < 6; i++) {
	    	if(i == 1 || i == 4) {
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
        idAccLabel.setBounds(141, 90, 70, 30);
        staffInfoPanel.add(idAccLabel);
        
        idAccTxt = new JTextField();
        idAccTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idAccTxt.setColumns(10);
        idAccTxt.setBounds(211, 90, 170, 30);
        staffInfoPanel.add(idAccTxt);
        
        nameAccTxt = new JTextField();
        nameAccTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameAccTxt.setColumns(10);
        nameAccTxt.setBounds(471, 90, 170, 30);
        staffInfoPanel.add(nameAccTxt);
        
        JLabel nameAccLabel = new JLabel("Tên TK");
        nameAccLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameAccLabel.setBounds(401, 90, 70, 30);
        staffInfoPanel.add(nameAccLabel);
        
        JLabel passLabel = new JLabel("Mật khẩu");
        passLabel.setFont(new Font("Arial", Font.BOLD, 13));
        passLabel.setBounds(401, 140, 70, 30);
        staffInfoPanel.add(passLabel);
        
        passTxt = new JTextField();
        passTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        passTxt.setColumns(10);
        passTxt.setBounds(471, 140, 170, 30);
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
        lblGiiTnh.setBounds(141, 140, 70, 30);
        staffInfoPanel.add(lblGiiTnh);
        
        blockedStatusRadioBtn = new JRadioButton("Khóa");
        buttonGroup.add(blockedStatusRadioBtn);
        blockedStatusRadioBtn.setBounds(211, 140, 60, 30);
        staffInfoPanel.add(blockedStatusRadioBtn);
        
        noneStatusRadioBtn = new JRadioButton("None");
        noneStatusRadioBtn.setSelected(true);
        buttonGroup.add(noneStatusRadioBtn);
        noneStatusRadioBtn.setBounds(271, 140, 60, 30);
        staffInfoPanel.add(noneStatusRadioBtn);
        
        JLabel staffDateLabel = new JLabel("Ngày tạo");
        staffDateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        staffDateLabel.setBounds(141, 190, 70, 30);
        staffInfoPanel.add(staffDateLabel);
        
        JComboBox<String> positioncbB = new JComboBox<>();
        positioncbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Admin", "Quản lí", "Nhân viên"}));
        positioncbB.setBounds(471, 190, 90, 30);
        staffInfoPanel.add(positioncbB);
        
        JLabel lblChcV = new JLabel("Quyền");
        lblChcV.setFont(new Font("Arial", Font.BOLD, 13));
        lblChcV.setBounds(401, 190, 70, 30);
        staffInfoPanel.add(lblChcV);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(211, 190, 170, 30);
        staffInfoPanel.add(dateChooser);
        
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
        
        JLabel lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 15));
        lblTmKim.setBounds(80, 80, 120, 40);
        searchPanel.add(lblTmKim);
        
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        textField.setColumns(10);
        textField.setBounds(55, 131, 170, 30);
        searchPanel.add(textField);
        
        searchButton = new JButton("OK");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(90, 180, 100, 30);
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

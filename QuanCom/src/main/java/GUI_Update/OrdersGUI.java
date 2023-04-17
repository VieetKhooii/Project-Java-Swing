package main.java.GUI;


import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.json.ParseException;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class OrdersGUI extends JPanel implements MouseListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentField;
	private JPanel ordersListPanel;
	private JTable ordersTable;
	private DefaultTableModel detailTableModel;
	private JScrollPane ordersScrollPane;
	private DefaultTableCellRenderer centerRenderer;
	private JPanel searchPanel;
	private JTextField searchTxt;
	private JButton searchButton;
	private JPanel btnField;
	static JButton viewBtn;
	static JButton createBtn;
	static JButton updateBtn;
	static JButton delBtn;
	private JLabel lblNewLabel;
	private JTextField priceFrom;
	private JTextField priceTo;
	private JComboBox<String> sortCbB;
	private JLabel lblSpXp;
	private JComboBox<String> searchCbB;
	/**
	 * Create the panel.
	 */
	public OrdersGUI() {
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
        ordersListPanel = new JPanel(null);
        ordersListPanel.setBackground(new Color(30, 144, 255));
        ordersListPanel.setBounds(0, 50, 800, 650);
        
        contentField.add(ordersListPanel);
        
        detailTableModel = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Mã nhân viên", "Ngày tạo", "Tổng tiền"}, 0);		
        ordersTable = new JTable(detailTableModel);
        ordersTable.setFont(new Font("Arial", Font.PLAIN, 14));
        ordersTable.setDefaultRenderer(String.class, centerRenderer);
	    ordersTable.setRowHeight(30);
	    
	    detailTableModel.addRow(new Object[] {"123", "NV123", "12/29/2023", 10000000});
	    detailTableModel.addRow(new Object[] {"124", "NV123", "11/29/2023", 20000000});
	    
	    for(int i = 0; i < 4; i++) {
	    	ordersTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    }
	    
	    ListSelectionModel listSelectionModel = ordersTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){      	
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	int row = ordersTable.getSelectedRow();   
            	viewBtn.setEnabled(true);           		   		    		        
            	viewBtn.addActionListener(new ActionListener() {
                 	public void actionPerformed(ActionEvent e) {
                 		GiaoDien.hoaDon.setVisible(false);
         		        GiaoDien.taoDon.setVisible(true);
                 	}
                });
            	
            	GiaoDien.taoDon.idOrderTxt.setText(detailTableModel.getValueAt(row, 0).toString());
            	GiaoDien.taoDon.idStaffCreateOrderTxt.setText(detailTableModel.getValueAt(row, 1).toString());
            	
            	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            	Date date = null;
            	try {
            	    date = dateFormat.parse(detailTableModel.getValueAt(row, 2).toString());
            	} catch (ParseException | java.text.ParseException e1) {
            	    e1.printStackTrace();
            	}
            	GiaoDien.taoDon.orderDateChooser.setDate(date);
            	
            	GiaoDien.taoDon.totalPriceOrderTxt.setText(detailTableModel.getValueAt(row, 3).toString());
            }          
        });
	    
        ordersScrollPane = new JScrollPane(ordersTable);
        ordersScrollPane.setBounds(5, 5, 790, 640);
        ordersListPanel.add(ordersScrollPane);
        
        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);
        
        JLabel staffLabel = new JLabel("DANH SÁCH HÓA ĐƠN");
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
        
        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        searchTxt.setColumns(10);
        searchTxt.setBounds(100, 70, 170, 40);
        searchPanel.add(searchTxt);
        
        searchButton = new JButton("OK");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(100, 250, 100, 50);
        searchPanel.add(searchButton);
        
        searchCbB = new JComboBox<>();
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã hóa đơn", "Mã nhân viên", "Ngày tạo"}));      
        searchCbB.setBounds(10, 70, 80, 40);
        searchPanel.add(searchCbB);
        
        lblNewLabel = new JLabel("Tổng giá");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(10, 125, 80, 40);
        searchPanel.add(lblNewLabel);
        
        priceFrom = new JTextField();
        priceFrom.setFont(new Font("Arial", Font.PLAIN, 13));
        priceFrom.setColumns(10);
        priceFrom.setBounds(100, 125, 80, 40);
        searchPanel.add(priceFrom);
        
        priceTo = new JTextField();
        priceTo.setFont(new Font("Arial", Font.PLAIN, 13));
        priceTo.setColumns(10);
        priceTo.setBounds(190, 125, 80, 40);
        searchPanel.add(priceTo);
        
        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã hóa đơn", "Mã nhân viên"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(100, 180, 145, 40);
        searchPanel.add(sortCbB);
        
        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(10, 180, 80, 40);
        searchPanel.add(lblSpXp);
        
        btnField = new JPanel();
        btnField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        btnField.setBounds(800, 380, 280, 320);
        contentField.add(btnField);
        btnField.setLayout(null);
        
        viewBtn = new JButton("Xem chi tiết");
        viewBtn.setEnabled(false);
        viewBtn.setBounds(80, 70, 120, 40);
        btnField.add(viewBtn);
        
        createBtn = new JButton("Tạo mới");
        createBtn.setBounds(80, 130, 120, 40);
        createBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GiaoDien.hoaDon.setVisible(false);
		        GiaoDien.taoDon.setVisible(true);
		        GiaoDien.taoDon.resetComponent();
        	}
        });
        btnField.add(createBtn);
        
        updateBtn = new JButton("Cập nhật");
        updateBtn.setBounds(80, 190, 120, 40);
        btnField.add(updateBtn);
        
        delBtn = new JButton("Xóa");
        delBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        		//xoa o day
        		if(decide == 0) {
        			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        delBtn.setBounds(80, 250, 120, 40);
        btnField.add(delBtn);
        
        JLabel controllerLabel = new JLabel("Cài đặt");
        controllerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controllerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        controllerLabel.setBounds(80, 0, 120, 40);
        btnField.add(controllerLabel);
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


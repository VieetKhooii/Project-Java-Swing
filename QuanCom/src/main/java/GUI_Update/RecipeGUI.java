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

public class RecipeGUI extends JPanel implements MouseListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentField;
	private JPanel staffListPanel;
	private JTable recipeTable;
	private DefaultTableModel detailTableModel;
	private JScrollPane recipeScrollPane;
	private DefaultTableCellRenderer centerRenderer;
	private JPanel searchPanel;
	private JLabel lblNewLabel;
	private JTextField idProductTxt;
	private JTextField idMaterialTxt;
	private JTextField soLuongTxt;
	private JPanel recipeInfoPanel;
	private JButton addRecipeBtn;
	private JButton fixRecipeBtn;
	private JButton delRecipeBtn;
	private JComboBox<String> searchCbB;
	private JTextField textField;
	private JLabel lblSpXp;
	private JComboBox<String> sortCbB;
	private JLabel lblTmKim;
	private JButton searchButton;
	private JTextField priceEveryMaterialTxt;
	/**
	 * Create the panel.
	 */
	public RecipeGUI() {
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
        
        detailTableModel = new DefaultTableModel(new Object[]{"Mã món", "Mã nguyên liệu", "Số lượng", "Giá trị"}, 0);		
        recipeTable = new JTable(detailTableModel);
        recipeTable.setFont(new Font("Arial", Font.PLAIN, 14));
        recipeTable.setDefaultRenderer(String.class, centerRenderer);
	    recipeTable.setRowHeight(30);
	    for(int i = 0; i < 4; i++) {
	    	recipeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    }
	    
	    ListSelectionModel listSelectionModel = recipeTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){      	
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	int row = recipeTable.getSelectedRow();        		   		    		        
            	idProductTxt.setText(detailTableModel.getValueAt(row, 0).toString());
            	idMaterialTxt.setText(detailTableModel.getValueAt(row, 1).toString());
            	soLuongTxt.setText(detailTableModel.getValueAt(row, 2).toString());
            	priceEveryMaterialTxt.setText(detailTableModel.getValueAt(row, 3).toString());
            }          
        });
        detailTableModel.addRow(new Object[] {"12", "1", "24", 3000000});
        
        recipeScrollPane = new JScrollPane(recipeTable);
        recipeScrollPane.setBounds(5, 5, 1070, 350);
        staffListPanel.add(recipeScrollPane);
        
        recipeInfoPanel = new JPanel();
        recipeInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        recipeInfoPanel.setBounds(0, 50, 600, 290);
        contentField.add(recipeInfoPanel);
        recipeInfoPanel.setLayout(null);
        
        lblNewLabel = new JLabel("Thông tin nhà cung cấp");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(200, 0, 200, 40);
        recipeInfoPanel.add(lblNewLabel);
        
        JLabel idProductLabel = new JLabel("Mã món");
        idProductLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idProductLabel.setBounds(60, 51, 70, 30);
        recipeInfoPanel.add(idProductLabel);
        
        idProductTxt = new JTextField();
        idProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idProductTxt.setColumns(10);
        idProductTxt.setBounds(143, 51, 167, 30);
        recipeInfoPanel.add(idProductTxt);
        
        idMaterialTxt = new JTextField();
        idMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idMaterialTxt.setColumns(10);
        idMaterialTxt.setBounds(143, 111, 167, 30);
        recipeInfoPanel.add(idMaterialTxt);
        
        JLabel idMaterialLabel = new JLabel("Mã N.liệu");
        idMaterialLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idMaterialLabel.setBounds(60, 111, 70, 30);
        recipeInfoPanel.add(idMaterialLabel);
        
        JLabel soLuongRecipeLb = new JLabel("Số lượng");
        soLuongRecipeLb.setFont(new Font("Arial", Font.BOLD, 13));
        soLuongRecipeLb.setBounds(60, 171, 70, 30);
        recipeInfoPanel.add(soLuongRecipeLb);
        
        soLuongTxt = new JTextField();
        soLuongTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        soLuongTxt.setColumns(10);
        soLuongTxt.setBounds(143, 171, 167, 30);
        recipeInfoPanel.add(soLuongTxt);
        
        addRecipeBtn = new JButton("Thêm");      
        addRecipeBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(idProductTxt.getText().equals("") || idMaterialTxt.getText().equals("") || soLuongTxt.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Đã thêm công thức!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        addRecipeBtn.setFont(new Font("Arial", Font.PLAIN, 13)); 
        addRecipeBtn.setBounds(400, 67, 90, 35);
        recipeInfoPanel.add(addRecipeBtn);
        
        fixRecipeBtn = new JButton("Cập nhật");       
        fixRecipeBtn.setFont(new Font("Arial", Font.PLAIN, 13));       
        fixRecipeBtn.setBounds(400, 129, 90, 35);
        recipeInfoPanel.add(fixRecipeBtn);
        
        delRecipeBtn = new JButton("Xóa");     
        delRecipeBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        		//xoa o day
        		if(decide == 0) {
        			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        delRecipeBtn.setFont(new Font("Arial", Font.PLAIN, 13));       
        delRecipeBtn.setBounds(400, 189, 90, 35);
        recipeInfoPanel.add(delRecipeBtn);
        
        JLabel priceEveryMaterialLb = new JLabel("Giá trị");
        priceEveryMaterialLb.setFont(new Font("Arial", Font.BOLD, 13));
        priceEveryMaterialLb.setBounds(60, 230, 70, 30);
        recipeInfoPanel.add(priceEveryMaterialLb);
        
        priceEveryMaterialTxt = new JTextField();
        priceEveryMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        priceEveryMaterialTxt.setColumns(10);
        priceEveryMaterialTxt.setBounds(143, 230, 167, 30);
        recipeInfoPanel.add(priceEveryMaterialTxt);
        
        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);
        
        JLabel supplierLabel = new JLabel("BẢNG CÔNG THỨC");
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
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã món", "Mã nguyên liệu"}));
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
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã món", "Mã nguyên liệu"}));
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


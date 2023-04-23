package GUI;
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

public class CategoryGUI extends JPanel implements MouseListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentField;
	private JPanel categoryListPanel;
	private JTable categoryTable;
	private DefaultTableModel detailTableModel;
	private JScrollPane categoryScrollPane;
	private DefaultTableCellRenderer centerRenderer;
	private JPanel searchPanel;
	private JLabel lblNewLabel;
	private JTextField idCategoryTxt;
	private JTextField nameCategoryTxt;
	private JTextField descriptionTxt;
	private JPanel categoryInfoPanel;
	private JButton addCategoryBtn;
	private JButton fixCategoryBtn;
	private JButton delCategoryBtn;
	private JComboBox<String> searchCbB;
	private JTextField textField;
	private JLabel lblSpXp;
	private JComboBox<String> sortCbB;
	private JLabel lblTmKim;
	private JButton searchButton;
	/**
	 * Create the panel.
	 */
	public CategoryGUI() {
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
        categoryListPanel = new JPanel(null);
        categoryListPanel.setBackground(new Color(30, 144, 255));
        categoryListPanel.setBounds(0, 340, 1080, 360);
        
        contentField.add(categoryListPanel);
        
        detailTableModel = new DefaultTableModel(new Object[]{"Mã loại", "Tên loại", "Mô tả"}, 0);		
        categoryTable = new JTable(detailTableModel);
        categoryTable.setFont(new Font("Arial", Font.PLAIN, 14));
        categoryTable.setDefaultRenderer(String.class, centerRenderer);
	    categoryTable.setRowHeight(30);
	    for(int i = 0; i < 3; i++) {
	    	if(i == 2) categoryTable.getColumnModel().getColumn(i).setWidth(300);
	    	categoryTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    }
	    
	    ListSelectionModel listSelectionModel = categoryTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){      	
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	int row = categoryTable.getSelectedRow();        		   		    		        
            	idCategoryTxt.setText(detailTableModel.getValueAt(row, 0).toString());
            	nameCategoryTxt.setText(detailTableModel.getValueAt(row, 1).toString());
            	descriptionTxt.setText(detailTableModel.getValueAt(row, 2).toString());
            }          
        });
        detailTableModel.addRow(new Object[] {"12", "1", "24"});
        
        categoryScrollPane = new JScrollPane(categoryTable);
        categoryScrollPane.setBounds(5, 5, 1070, 350);
        categoryListPanel.add(categoryScrollPane);
        
        categoryInfoPanel = new JPanel();
        categoryInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        categoryInfoPanel.setBounds(0, 50, 600, 290);
        contentField.add(categoryInfoPanel);
        categoryInfoPanel.setLayout(null);
        
        lblNewLabel = new JLabel("Thông tin phân loại");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(200, 0, 200, 40);
        categoryInfoPanel.add(lblNewLabel);
        
        JLabel idCategoryLabel = new JLabel("Mã loại");
        idCategoryLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idCategoryLabel.setBounds(55, 67, 70, 30);
        categoryInfoPanel.add(idCategoryLabel);
        
        idCategoryTxt = new JTextField();
        idCategoryTxt.setEditable(false);
        idCategoryTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idCategoryTxt.setColumns(10);
        idCategoryTxt.setBounds(138, 67, 167, 30);
        categoryInfoPanel.add(idCategoryTxt);
        
        nameCategoryTxt = new JTextField();
        nameCategoryTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameCategoryTxt.setColumns(10);
        nameCategoryTxt.setBounds(138, 127, 167, 30);
        categoryInfoPanel.add(nameCategoryTxt);
        
        JLabel nameCategoryLabel = new JLabel("Tên loại");
        nameCategoryLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameCategoryLabel.setBounds(55, 127, 70, 30);
        categoryInfoPanel.add(nameCategoryLabel);
        
        JLabel descriptionLabel = new JLabel("Mô tả");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 13));
        descriptionLabel.setBounds(55, 187, 70, 30);
        categoryInfoPanel.add(descriptionLabel);
        
        descriptionTxt = new JTextField();
        descriptionTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        descriptionTxt.setColumns(10);
        descriptionTxt.setBounds(138, 187, 167, 30);
        categoryInfoPanel.add(descriptionTxt);
        
        addCategoryBtn = new JButton("Thêm");      
        addCategoryBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(nameCategoryTxt.getText().equals("") || descriptionTxt.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Đã thêm phân loại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        addCategoryBtn.setFont(new Font("Arial", Font.PLAIN, 13)); 
        addCategoryBtn.setBounds(400, 67, 90, 35);
        categoryInfoPanel.add(addCategoryBtn);
        
        fixCategoryBtn = new JButton("Cập nhật");       
        fixCategoryBtn.setFont(new Font("Arial", Font.PLAIN, 13));       
        fixCategoryBtn.setBounds(400, 129, 90, 35);
        categoryInfoPanel.add(fixCategoryBtn);
        
        delCategoryBtn = new JButton("Xóa");     
        delCategoryBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        		//xoa o day
        		if(decide == 0) {
        			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        delCategoryBtn.setFont(new Font("Arial", Font.PLAIN, 13));       
        delCategoryBtn.setBounds(400, 189, 90, 35);
        categoryInfoPanel.add(delCategoryBtn);
        
        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);
        
        JLabel supplierLabel = new JLabel("BẢNG PHÂN LOẠI");
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
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã loại", "Tên loại"}));
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
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã loại", "Tên loại"}));
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


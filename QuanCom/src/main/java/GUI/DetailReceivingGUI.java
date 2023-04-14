

package main.java.GUI;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class DetailReceivingGUI extends JPanel implements MouseListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel category;
	private JPanel btnField;
	static JButton preBtn;
	static JButton nextBtn;
	private JPanel contentField;
	private JTextField nameFindText;
	private JTextField priceFrom;
	private JTextField priceTo;
	private JTextField idPNTxt;
	private JTextField idNCCTxt;
	private JTextField idStaffCreatePNTxt;
	private JTextField totalPricePNTxt;
	private JDateChooser datePNChooser;
	private JComboBox<String> sortCbb;
	private JPanel deltailOrderPanel;
	private JTable ctPNTable;
	private DefaultTableModel detailTableModel;
	private JScrollPane ctpnScrollPane;
	private JTextField idMaterialTxt;
	private JTextField nameMaterialTxt;
	private JTextField soLuongNhapTxt;
	private JTextField priceMaterialTxt;
	private JLabel vndSign1;
	private JSeparator separator_3;
	private JButton subFindBtn;
	private JButton rmFindBtn;
	private DefaultTableCellRenderer centerRenderer;
	private JPanel infoDetailOrderPanel;
	private JButton addReceivingBtn;
	private JButton updateReceivingBtn;
	private JButton delReceivingBtn;
	Component[] components1;
	Component[] components2;
	Component[] totalComponents;
	/**
	 * Create the panel.
	 */
	public DetailReceivingGUI() {
		init();
	}
	private void init() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1080, 700));
		
		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		//Tìm kiếm
		category = new JPanel(null);
		category.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		category.setPreferredSize(new Dimension(1080, 100));
		this.add(category, BorderLayout.NORTH);
		
		
		JLabel findLabel = new JLabel("TÌM KIẾM");
		findLabel.setForeground(new Color(255, 255, 255));
		findLabel.setBounds(0, 30, 120, 40);
		category.add(findLabel);
		findLabel.setFont(new Font("Arial", Font.BOLD, 16));
		findLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel findNameProduct = new JLabel("Nhập ");
		findNameProduct.setBounds(160, 10, 73, 30);
		category.add(findNameProduct);
		findNameProduct.setFont(new Font("Arial", Font.BOLD, 13));
		
		nameFindText = new JTextField();
		nameFindText.setBounds(243, 10, 167, 30);
		category.add(nameFindText);
		nameFindText.setFont(new Font("Arial", Font.PLAIN, 13));
		nameFindText.setColumns(10);
		
		JLabel sortProducts = new JLabel("Sắp xếp");
		sortProducts.setBounds(440, 10, 73, 30);
		category.add(sortProducts);
		sortProducts.setFont(new Font("Arial", Font.BOLD, 13));
		
		sortCbb = new JComboBox<String>();
		sortCbb.setBounds(522, 10, 120, 30);
		category.add(sortCbb);
		sortCbb.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JLabel priceProduct = new JLabel("Giá ~");
		priceProduct.setBounds(686, 36, 47, 30);
		category.add(priceProduct);
		priceProduct.setFont(new Font("Arial", Font.BOLD, 13));
		
		priceFrom = new JTextField();
		priceFrom.setBounds(743, 10, 120, 30);
		category.add(priceFrom);
		priceFrom.setFont(new Font("Arial", Font.PLAIN, 13));
		priceFrom.setColumns(10);
		
		priceTo = new JTextField();
		priceTo.setBounds(743, 59, 120, 30);
		category.add(priceTo);
		priceTo.setFont(new Font("Arial", Font.PLAIN, 13));
		priceTo.setColumns(10);
		
		subFindBtn = new JButton("OK");
		subFindBtn.setBounds(950, 9, 90, 35);
		category.add(subFindBtn);
		subFindBtn.setFont(new Font("Arial", Font.BOLD, 13));
		
		rmFindBtn = new JButton("Hủy");
		rmFindBtn.setBounds(950, 54, 90, 35);
		category.add(rmFindBtn);
		rmFindBtn.setFont(new Font("Arial", Font.BOLD, 13));
		
		vndSign1 = new JLabel("(VNĐ)");
		vndSign1.setBounds(873, 36, 37, 30);
		category.add(vndSign1);
		
		JLabel MutualCategoryLabel = new JLabel("Foods/Drinks");
		MutualCategoryLabel.setFont(new Font("Arial", Font.BOLD, 13));
		MutualCategoryLabel.setBounds(160, 59, 100, 30);
		category.add(MutualCategoryLabel);
		
		JComboBox<String> MutualCategoryCbb = new JComboBox<String>();
		MutualCategoryCbb.setFont(new Font("Arial", Font.PLAIN, 13));
		MutualCategoryCbb.setBounds(290, 59, 120, 30);
		category.add(MutualCategoryCbb);
		
		JComboBox<String> categoryCbb = new JComboBox<String>();
		categoryCbb.setFont(new Font("Arial", Font.PLAIN, 13));
		categoryCbb.setBounds(522, 59, 120, 30);
		category.add(categoryCbb);
		
		JLabel categoryLabel = new JLabel("Phân loại");
		categoryLabel.setFont(new Font("Arial", Font.BOLD, 13));
		categoryLabel.setBounds(440, 59, 73, 30);
		category.add(categoryLabel);
		//End
		
		    
		// Panel chứa chi tiết và chọn món
		contentField = new JPanel(null);
		contentField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.add(contentField, BorderLayout.CENTER);
	   
        //Panel chi tiết hóa đơn
        deltailOrderPanel = new JPanel(null);
        deltailOrderPanel.setBounds(0, 0, 1080, 360);
        
        contentField.add(deltailOrderPanel, "hello2");
        
        detailTableModel = new DefaultTableModel(new Object[]{"Mã nguyên liệu", "Tên nguyên liệu", "Số lượng nhập", "Giá"}, 0);		
        ctPNTable = new JTable(detailTableModel);
        ctPNTable.setFont(new Font("Arial", Font.PLAIN, 14));
              
        detailTableModel.addRow(new Object[]{"C1", "Cơm sườn", 10, 30000});
               
        ListSelectionModel listSelectionModel = ctPNTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){      	
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	int row = ctPNTable.getSelectedRow();   
        		delReceivingBtn.setEnabled(true);           		   		    		
        		idMaterialTxt.setText(detailTableModel.getValueAt(row, 0).toString());
        		nameMaterialTxt.setText(detailTableModel.getValueAt(row, 1).toString());
        		soLuongNhapTxt.setText(detailTableModel.getValueAt(row, 2).toString());
        		priceMaterialTxt.setText(detailTableModel.getValueAt(row, 3).toString());      	       		
            }          
        });
        
        
        
        ctPNTable.setDefaultRenderer(String.class, centerRenderer);
	    ctPNTable.setRowHeight(30);
	    for(int i = 0; i < 4; i++) {
	    	if(i == 1) {
	    		ctPNTable.getColumnModel().getColumn(i).setPreferredWidth(300);
	    		ctPNTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    	}
	    	else {
	    		ctPNTable.getColumnModel().getColumn(i).setPreferredWidth(125);
	    		ctPNTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    	}
	    }
	    
	    
        ctpnScrollPane = new JScrollPane(ctPNTable);
        ctpnScrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        ctpnScrollPane.setBounds(0, 0, 800, 360);
        deltailOrderPanel.add(ctpnScrollPane);
        
        infoDetailOrderPanel = new JPanel(null);
        infoDetailOrderPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        infoDetailOrderPanel.setBounds(800, 0, 280, 360);
        deltailOrderPanel.add(infoDetailOrderPanel);
        
        JLabel idCTPNLabel = new JLabel("Mã n.liệu");
        idCTPNLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idCTPNLabel.setBounds(10, 72, 73, 30);
        infoDetailOrderPanel.add(idCTPNLabel);
        
        idMaterialTxt = new JTextField();
        idMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idMaterialTxt.setColumns(10);
        idMaterialTxt.setBounds(93, 72, 167, 30);
        infoDetailOrderPanel.add(idMaterialTxt);
        
        JLabel nameCTPNLabel = new JLabel("Tên n.liệu");
        nameCTPNLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameCTPNLabel.setBounds(10, 124, 73, 30);
        infoDetailOrderPanel.add(nameCTPNLabel);
        
        nameMaterialTxt = new JTextField();
        nameMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameMaterialTxt.setColumns(10);
        nameMaterialTxt.setBounds(93, 124, 167, 30);
        infoDetailOrderPanel.add(nameMaterialTxt);
        
        JLabel soLuongNhapLabel = new JLabel("Số lượng nhập");
        soLuongNhapLabel.setFont(new Font("Arial", Font.BOLD, 13));
        soLuongNhapLabel.setBounds(10, 180, 140, 30);
        infoDetailOrderPanel.add(soLuongNhapLabel);
        
        soLuongNhapTxt = new JTextField();
        soLuongNhapTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        soLuongNhapTxt.setColumns(10);
        soLuongNhapTxt.setBounds(210, 180, 50, 30);
        infoDetailOrderPanel.add(soLuongNhapTxt);
        
        JLabel priceCTPNLabel = new JLabel("Giá nhập");
        priceCTPNLabel.setFont(new Font("Arial", Font.BOLD, 13));
        priceCTPNLabel.setBounds(10, 232, 73, 30);
        infoDetailOrderPanel.add(priceCTPNLabel);
        
        priceMaterialTxt = new JTextField();
        priceMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        priceMaterialTxt.setColumns(10);
        priceMaterialTxt.setBounds(93, 232, 167, 30);
        infoDetailOrderPanel.add(priceMaterialTxt);
        
        JLabel thongtinHDLabel_1 = new JLabel("THÔNG TIN NGUYÊN LIỆU");
        thongtinHDLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        thongtinHDLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        thongtinHDLabel_1.setBounds(53, 11, 176, 30);
        infoDetailOrderPanel.add(thongtinHDLabel_1);
        
        separator_3 = new JSeparator();
        separator_3.setBounds(0, 359, 280, 1);
        infoDetailOrderPanel.add(separator_3);
        
        addReceivingBtn = new JButton("Thêm");
        addReceivingBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(idMaterialTxt.getText().equals("") || nameMaterialTxt.getText().equals("") || 
        				soLuongNhapTxt.getText().equals("") && priceMaterialTxt.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        addReceivingBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addReceivingBtn.setBounds(10, 290, 90, 35);
        infoDetailOrderPanel.add(addReceivingBtn);
        
        updateReceivingBtn = new JButton("Cập nhật");
        updateReceivingBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        updateReceivingBtn.setBounds(98, 290, 90, 35);
        infoDetailOrderPanel.add(updateReceivingBtn);
        
        delReceivingBtn = new JButton("Xóa");
        delReceivingBtn.setEnabled(false);
        delReceivingBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
        		if(decide == 0) {
        			//xu li su kien
        			JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        });
        delReceivingBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delReceivingBtn.setBounds(185, 290, 90, 35);
        infoDetailOrderPanel.add(delReceivingBtn);
   
        //End
        
       
        
        
        
        
        
        
        
        
        //Tạo hóa đơn
		JLabel orderInfoLabel = new JLabel("THÔNG TIN PHIẾU NHẬP");
		orderInfoLabel.setFont(new Font("Arial", Font.BOLD, 15));
		orderInfoLabel.setBounds(10, 370, 200, 20);
		contentField.add(orderInfoLabel);
		
		JLabel idPNLable = new JLabel("Mã phiếu nhập");
		idPNLable.setFont(new Font("Arial", Font.BOLD, 13));
		idPNLable.setBounds(30, 421, 120, 30);
		contentField.add(idPNLable);
		
		idPNTxt = new JTextField();
		idPNTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		idPNTxt.setBounds(160, 421, 178, 30);
		contentField.add(idPNTxt);
		idPNTxt.setColumns(10);
		
		JLabel datePNLabel = new JLabel("Ngày tạo phiếu");
		datePNLabel.setFont(new Font("Arial", Font.BOLD, 13));
		datePNLabel.setBounds(30, 473, 120, 30);
		contentField.add(datePNLabel);
		
		datePNChooser = new JDateChooser();
		datePNChooser.setBounds(160, 473, 178, 30);
		contentField.add(datePNChooser);
		
		JLabel idNCCLabel = new JLabel("Mã nhà cung cấp");
		idNCCLabel.setFont(new Font("Arial", Font.BOLD, 13));
		idNCCLabel.setBounds(370, 421, 120, 30);
		contentField.add(idNCCLabel);
		
		idNCCTxt = new JTextField();
		idNCCTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		idNCCTxt.setColumns(10);
		idNCCTxt.setBounds(532, 421, 178, 30);
		contentField.add(idNCCTxt);
		
		JLabel idStaffCreateOrderLabel = new JLabel("Mã nhân viên tạo phiếu");
		idStaffCreateOrderLabel.setFont(new Font("Arial", Font.BOLD, 13));
		idStaffCreateOrderLabel.setBounds(370, 473, 150, 30);
		contentField.add(idStaffCreateOrderLabel);
		
		idStaffCreatePNTxt = new JTextField();
		idStaffCreatePNTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		idStaffCreatePNTxt.setColumns(10);
		idStaffCreatePNTxt.setBounds(530, 473, 180, 30);
		contentField.add(idStaffCreatePNTxt);
		
		JLabel totalPriceLabel = new JLabel("Tổng tiền");
		totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 13));
		totalPriceLabel.setBounds(758, 421, 102, 30);
		contentField.add(totalPriceLabel);
		
		totalPricePNTxt = new JTextField();
		totalPricePNTxt.setFont(new Font("Arial", Font.PLAIN, 13));
		totalPricePNTxt.setColumns(10);
		totalPricePNTxt.setBounds(870, 421, 178, 30);
		contentField.add(totalPricePNTxt);
        
        
        //Nút chuyển tiến trình
		btnField = new JPanel();
		btnField.setPreferredSize(new Dimension(1080, 70));
		btnField.setLayout(null);
		this.add(btnField, BorderLayout.SOUTH);
		
		preBtn = new JButton("Quay lại");
		preBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!idPNTxt.getText().equals("") || !idNCCTxt.getText().equals("") || !totalPricePNTxt.getText().equals("")
						|| !idStaffCreatePNTxt.getText().equals("")) {
					int decide = JOptionPane.showConfirmDialog(null, "Mot so du lieu van chua duoc luu, ban co muon quay lai?", "Thông báo", JOptionPane.YES_NO_OPTION);						
					if(decide==0) {
						GiaoDien.phieuNhap.setVisible(true);
						GiaoDien.taoPhieu.setVisible(false);
					}
				}
				else {
					GiaoDien.phieuNhap.setVisible(true);
					GiaoDien.taoPhieu.setVisible(false);
				}
			}	
		});
		preBtn.setFont(new Font("Arial", Font.BOLD, 17));
		preBtn.setBounds(70, 10, 100, 50);	
		btnField.add(preBtn);
		
		nextBtn = new JButton("Tiếp");		
		nextBtn.setFont(new Font("Arial", Font.BOLD, 17));
		nextBtn.setBounds(910, 10, 100, 50);
		nextBtn.addActionListener(this);
		btnField.add(nextBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 1080, 1);
		btnField.add(separator);
		//End
		
		//Mảng lấy các component
		components1 = contentField.getComponents();
		components2 = infoDetailOrderPanel.getComponents();
		totalComponents = Arrays.copyOf(components1, components1.length + components2.length);
		System.arraycopy(components2, 0, totalComponents, components1.length, components2.length);
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == nextBtn) {
			if(idPNTxt.getText().equals("") || idNCCTxt.getText().equals("") || totalPricePNTxt.getText().equals("") 
					|| idStaffCreatePNTxt.getText().equals("") || soLuongNhapTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Thong tin chua day du!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Tao phieu nhap thanh cong!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	//Hàm reset dl các component
	void resetComponent() {				
		for (Component component : totalComponents) {
		    if (component instanceof JTextField) {
		    	
		        ((JTextField) component).setText(""); // reset giá trị trên JTextField
		    }
		    // Thêm các trường hợp khác tương tự nếu cần thiết
		}
	}
}

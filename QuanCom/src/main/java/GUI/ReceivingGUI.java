package GUI;

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

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.json.ParseException;
import com.toedter.calendar.JDateChooser;
import model.*;
import service.DetailReceiveService;
import service.MaterialService;
import service.ReceivedNoteService;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.util.List;

public class ReceivingGUI extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel receivingListPanel;
    private JTable receivingTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane receivingScrollPane;
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
    private JComboBox<String> searchCbB;
    private JLabel lblSpXp;
    private JDateChooser dateFrom;
    private JDateChooser dateTo;
    ReceivedNoteService receivedNoteService = new ReceivedNoteService();
    DetailReceiveService detailReceiveService = new DetailReceiveService();
    List<ReceivedNote> receivedNoteList = receivedNoteService.getAllReceiving();
    public static ReceivedNote noteStatic = new ReceivedNote();
//    DetailReceivingGUI detailReceivingGUI = new DetailReceivingGUI();
    /**
     * Create the panel.
     */
    public ReceivingGUI() {
    	
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
        receivingListPanel = new JPanel(null);
        receivingListPanel.setBackground(new Color(30, 144, 255));
        receivingListPanel.setBounds(0, 50, 800, 650);

        contentField.add(receivingListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã phiếu nhập", "Mã nhân viên", "Mã nhà cung cấp", "Ngày tạo", "Tổng tiền"}, 0);
        receivingTable = new JTable(detailTableModel);
        receivingTable.setFont(new Font("Arial", Font.PLAIN, 14));
        receivingTable.setDefaultRenderer(String.class, centerRenderer);
        receivingTable.setRowHeight(40);

        for(int i = 0; i < 5; i++) {
            receivingTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        ListSelectionModel listSelectionModel = receivingTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = receivingTable.getSelectedRow();
                if (row >= 0){
                    viewBtn.setEnabled(true);
                    int id = Integer.parseInt(detailTableModel.getValueAt(row, 0).toString());
                    noteStatic.setId(id);

                    GiaoDien.taoPhieu.idPNTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    GiaoDien.taoPhieu.idStaffCreatePNTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    GiaoDien.taoPhieu.idNCCTxt.setText(detailTableModel.getValueAt(row, 2).toString());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = dateFormat.parse(detailTableModel.getValueAt(row, 3).toString());
                    } catch (ParseException | java.text.ParseException e1) {
                        e1.printStackTrace();
                    }
                    GiaoDien.taoPhieu.datePNChooser.setDate(date);

                    GiaoDien.taoPhieu.totalPricePNTxt.setText(detailTableModel.getValueAt(row, 4).toString());
                }
            }
        });

        receivingScrollPane = new JScrollPane(receivingTable);
        receivingScrollPane.setBounds(5, 5, 790, 640);
        receivingListPanel.add(receivingScrollPane);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBackground(new Color(255, 255, 255));
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel staffLabel = new JLabel("DANH SÁCH PHIẾU NHẬP");
        staffLabel.setBackground(new Color(255, 255, 255));
        staffLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(staffLabel);
        staffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staffLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        searchPanel = new JPanel();
        searchPanel.setBackground(new Color(255, 255, 255));
        searchPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        searchPanel.setBounds(800, 50, 280, 377);
        contentField.add(searchPanel);
        searchPanel.setLayout(null);

        JLabel lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(80, 0, 120, 40);
        searchPanel.add(lblTmKim);

        searchTxt = new JTextField();       
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 16));
        searchTxt.setColumns(10);
        searchTxt.setBounds(100, 98, 170, 40);
        searchPanel.add(searchTxt);

        searchCbB = new JComboBox<>();
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã phiếu nhập", "Mã nhân viên", "Mã nhà cung cấp"}));
        searchCbB.setBounds(10, 51, 260, 40);
        searchPanel.add(searchCbB);

        lblNewLabel = new JLabel("Tổng giá");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(10, 149, 80, 40);
        searchPanel.add(lblNewLabel);

        priceFrom = new JTextField();        
        priceFrom.setFont(new Font("Arial", Font.PLAIN, 13));
        priceFrom.setColumns(10);
        priceFrom.setBounds(100, 149, 80, 40);
        searchPanel.add(priceFrom);

        priceTo = new JTextField();
        priceTo.setFont(new Font("Arial", Font.PLAIN, 13));        
        priceTo.setColumns(10);
        priceTo.setBounds(190, 149, 80, 40);
        searchPanel.add(priceTo);

        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Mã PN giảm dần", "Mã NV tăng dần", "Mã NV giảm dần", "Mã NCC tăng dần"
        		, "Mã NCC giảm dần", "Mới nhất", "Cũ nhất", "Tổng giá tăng dần", "Tổng giá giảm dần"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(100, 250, 170, 40);
        searchPanel.add(sortCbB);

        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(10, 250, 80, 40);
        searchPanel.add(lblSpXp);
        
        JLabel lblNhp = new JLabel("Nhập mã");
        lblNhp.setFont(new Font("Arial", Font.BOLD, 13));
        lblNhp.setBounds(10, 98, 80, 40);
        searchPanel.add(lblNhp);
        
        searchButton = new JButton("OK");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	if(priceFrom.getText().equals("") && priceTo.getText().equals("")) {
            		showSearchResult(searchTxt.getText(), searchCbB.getSelectedItem().toString().trim(), sortCbB.getSelectedItem().toString().trim(),
                			priceFrom.getText(), priceTo.getText(), dateFrom.getDate(), dateTo.getDate());
            	}
            	else {
            		if(!priceFrom.getText().matches("[0-9]{1,9}") && !priceTo.getText().matches("[0-9]{1,9}")) {
                		JOptionPane.showMessageDialog(null, "Sai tham số đầu vào!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                	}
                	else {
                		showSearchResult(searchTxt.getText(), searchCbB.getSelectedItem().toString().trim(), sortCbB.getSelectedItem().toString().trim(),
                    			priceFrom.getText(), priceTo.getText(), dateFrom.getDate(), dateTo.getDate());
                	}  
            	}
            	        	
            }
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(40, 316, 100, 50);
        searchPanel.add(searchButton);
        
        JButton rmSearchBtn = new JButton("Hủy");
        rmSearchBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchTxt.setText("");
        		priceFrom.setText(""); 
        		priceTo.setText("");
        		showTableReceiving();
        		dateFrom.setDate(null);
        		dateTo.setDate(null);
        		sortCbB.setSelectedIndex(0);
        	}
        });
        rmSearchBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        rmSearchBtn.setBounds(140, 316, 100, 50);
        searchPanel.add(rmSearchBtn);
        
        JLabel dateLabel = new JLabel("Ngày");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        dateLabel.setBounds(10, 199, 80, 40);
        searchPanel.add(dateLabel);
        
        dateFrom = new JDateChooser();
        dateFrom.setBounds(100, 200, 80, 40);
        searchPanel.add(dateFrom);
        
        dateTo = new JDateChooser();
        dateTo.setBounds(190, 200, 80, 40);
        searchPanel.add(dateTo);

        btnField = new JPanel();
        btnField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        btnField.setBounds(800, 427, 280, 270);
        contentField.add(btnField);
        btnField.setLayout(null);

        viewBtn = new JButton("Xem chi tiết");
        viewBtn.setEnabled(false);
        viewBtn.setBounds(80, 70, 120, 40);
        viewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GiaoDien.phieuNhap.setVisible(false);
                GiaoDien.taoPhieu.setVisible(true);
                GiaoDien.taoPhieu.showTableReceiving();
                GiaoDien.taoPhieu.showTempMaterial();

            }
        });
        btnField.add(viewBtn);

        createBtn = new JButton("Tạo mới");
        createBtn.setBounds(80, 130, 120, 40);
        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                receivingTable.clearSelection();
                noteStatic.setId(0);
                GiaoDien.phieuNhap.setVisible(false);
                GiaoDien.taoPhieu.setVisible(true);
                GiaoDien.taoPhieu.resetComponent();
                GiaoDien.taoPhieu.showTableReceiving();
                GiaoDien.taoPhieu.showTempMaterial();
            }
        });
        btnField.add(createBtn);

//        updateBtn = new JButton("Cập nhật");
//        updateBtn.setBounds(80, 190, 120, 40);
//        btnField.add(updateBtn);

        delBtn = new JButton("Xóa");
        delBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                //xoa o day
                if(decide == 0) {
                    int row = receivingTable.getSelectedRow();
                    int id = Integer.parseInt(detailTableModel.getValueAt(row, 0).toString());
                    detailReceiveService.deleteDetailNote(id);
                    receivedNoteService.deleteReceivedNote(id);
                    showTableReceiving();
                    JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        delBtn.setBounds(80, 190, 120, 40);
        btnField.add(delBtn);

        JLabel controllerLabel = new JLabel("Cài đặt");
        controllerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controllerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        controllerLabel.setBounds(80, 0, 120, 40);
        btnField.add(controllerLabel);
        //End
        showTableReceiving();


    }

    public void showTableReceiving(){
        int totalPrice = 0;
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        receivedNoteList = receivedNoteService.getAllReceiving();
        for (ReceivedNote note : receivedNoteList){
            note.setTotalPrice(receivedNoteService.getTotalPrice(note.getId()));
            detailTableModel.addRow(new Object[] {
                    note.getId(), note.getStaffId(), note.getSupplierId(), note.getDate(), note.getTotalPrice()
            });
        }
    }

    private void showSearchResult(String searchTxt, String optSearch, String optSort, String priceFrom, String priceTo 
    		, java.util.Date dateFrom, java.util.Date dateTo) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<ReceivedNote> searchResultList = receivedNoteService.getAllSearchResult(searchTxt, optSearch, optSort, priceFrom, priceTo, dateFrom, dateTo);
        for (ReceivedNote note : searchResultList){
            note.setTotalPrice(receivedNoteService.getTotalPrice(note.getId()));
            detailTableModel.addRow(new Object[] {
                    note.getId(), note.getStaffId(), note.getSupplierId(), note.getDate(), note.getTotalPrice()
            });
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
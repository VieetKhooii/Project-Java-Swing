package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.toedter.calendar.JDateChooser;
import enumm.UnitMaterial;
import model.Material;
import model.ReceivedNote;
import model.ReceivedNoteDetail;
import model.User;
import service.DetailReceiveService;
import service.MaterialService;
import service.ReceivedNoteService;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.util.List;

public class DetailReceivingGUI extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel btnField;
    static JButton preBtn;
    static JButton nextBtn;
    private JPanel contentField;
    public JTextField idPNTxt;
    public JTextField idNCCTxt;
    public JTextField idStaffCreatePNTxt;
    public JTextField totalPricePNTxt;
    public JDateChooser datePNChooser;
    private JPanel deltailOrderPanel;
    private JTable ctPNTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane ctpnScrollPane;
    private JTextField idMaterialTxt;
    private JTextField nameMaterialTxt;
    private JTextField soLuongNhapTxt;
    private JTextField priceMaterialTxt;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel infoDetailOrderPanel;
    private JButton addReceivingBtn;
    private JButton updateReceivingBtn;
    private JButton delReceivingBtn;
    private JButton clearInfoBtn;
    private JButton checkBtn;
    private JComboBox<String> unitMaterialCbB = new JComboBox<>();
    Component[] components1;
    Component[] components2;
    Component[] totalComponents;
    ReceivedNoteService receivedNoteService = new ReceivedNoteService();
    DetailReceiveService detailReceiveService = new DetailReceiveService();
    java.util.List<ReceivedNote> receivedNoteList = receivedNoteService.getAllReceiving();
    List<ReceivedNoteDetail> detailList = detailReceiveService.getAll();
    MaterialService materialService = new MaterialService();
    List<Material> materialList = materialService.getAllMaterial();
    List<Material> tempMaterialList = new ArrayList<>();
    ReceivedNote noteStatic = ReceivingGUI.noteStatic;
    ProductGUI productGUI = new ProductGUI();
    int tempId = 0;
    int S;
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
        
        S = materialList.get(0).getId();
        for(Material j : materialList) {
			if(j.getId() > S) {
				S = j.getId();
			}
		}
        //End


        // Panel chứa chi tiết và chọn món
        contentField = new JPanel(null);
        contentField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.add(contentField, BorderLayout.CENTER);

        //Panel chi tiết hóa đơn
        deltailOrderPanel = new JPanel(null);
        deltailOrderPanel.setBounds(0, 0, 1080, 460);

        contentField.add(deltailOrderPanel, "hello2");

        detailTableModel = new DefaultTableModel(new Object[]{"Mã ng.liệu", "Tên nguyên liệu", "Số lượng nhập", "Đơn vị", "Giá"}, 0);
        ctPNTable = new MacOSStyleTable(detailTableModel);
        ctPNTable.setFont(new Font("Arial", Font.PLAIN, 14));

        ListSelectionModel listSelectionModel = ctPNTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = ctPNTable.getSelectedRow();
                if (row >= 0){
                    idMaterialTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    nameMaterialTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    soLuongNhapTxt.setText(detailTableModel.getValueAt(row, 2).toString());
                    for(int j=0; j<unitMaterialCbB.getItemCount(); j++) {
        				if(unitMaterialCbB.getItemAt(j).toString().equals(detailTableModel.getValueAt(row, 3).toString())) {
        					unitMaterialCbB.setSelectedIndex(j);
        				}
        			}
                    priceMaterialTxt.setText(detailTableModel.getValueAt(row, 4).toString());
                }
            }
        });



        ctPNTable.setDefaultRenderer(String.class, centerRenderer);
        ctPNTable.setRowHeight(30);
        for(int i = 0; i < 5; i++) {
            if(i == 1) {
                ctPNTable.getColumnModel().getColumn(i).setPreferredWidth(250);
                ctPNTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                ctPNTable.getColumnModel().getColumn(i).setPreferredWidth(125);
                ctPNTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }


        ctpnScrollPane = new CustomScrollPane(ctPNTable);
        ctpnScrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        ctpnScrollPane.setBounds(0, 0, 750, 460);
        deltailOrderPanel.add(ctpnScrollPane);

        infoDetailOrderPanel = new JPanel(null);
        infoDetailOrderPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        infoDetailOrderPanel.setBounds(750, 0, 330, 460);
        deltailOrderPanel.add(infoDetailOrderPanel);

        JLabel idCTPNLabel = new JLabel("Mã n.liệu");
        idCTPNLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idCTPNLabel.setBounds(10, 103, 73, 30);
        infoDetailOrderPanel.add(idCTPNLabel);

        idMaterialTxt = new JTextField();
        idMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idMaterialTxt.setColumns(10);
        idMaterialTxt.setBounds(119, 103, 201, 30);
        idMaterialTxt.setEditable(false);
        infoDetailOrderPanel.add(idMaterialTxt);

        JLabel nameCTPNLabel = new JLabel("Tên n.liệu");
        nameCTPNLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameCTPNLabel.setBounds(10, 163, 73, 30);
        infoDetailOrderPanel.add(nameCTPNLabel);

        nameMaterialTxt = new JTextField();
        nameMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameMaterialTxt.setColumns(10);
        nameMaterialTxt.setBounds(119, 163, 161, 30);
        infoDetailOrderPanel.add(nameMaterialTxt);

        JLabel soLuongNhapLabel = new JLabel("Số lượng nhập");
        soLuongNhapLabel.setFont(new Font("Arial", Font.BOLD, 13));
        soLuongNhapLabel.setBounds(10, 224, 107, 30);
        infoDetailOrderPanel.add(soLuongNhapLabel);

        soLuongNhapTxt = new JTextField();
        soLuongNhapTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        soLuongNhapTxt.setColumns(10);
        soLuongNhapTxt.setBounds(119, 224, 90, 30);
        infoDetailOrderPanel.add(soLuongNhapTxt);

        UnitMaterial unitMaterial = new UnitMaterial();
        unitMaterialCbB = new JComboBox<String>();
        unitMaterialCbB.setModel(new DefaultComboBoxModel<String>(unitMaterial.unitArray));
        unitMaterialCbB.setFont(new Font("Arial", Font.BOLD, 13));
        unitMaterialCbB.setBounds(230, 224, 90, 30);
        infoDetailOrderPanel.add(unitMaterialCbB);

        JLabel priceCTPNLabel = new JLabel("Đơn giá");
        priceCTPNLabel.setFont(new Font("Arial", Font.BOLD, 13));
        priceCTPNLabel.setBounds(10, 283, 73, 30);
        infoDetailOrderPanel.add(priceCTPNLabel);

        priceMaterialTxt = new JTextField();
        priceMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        priceMaterialTxt.setColumns(10);
        priceMaterialTxt.setBounds(119, 283, 201, 30);
        infoDetailOrderPanel.add(priceMaterialTxt);

        JLabel thongtinHDLabel_1 = new JLabel("THÔNG TIN NGUYÊN LIỆU");
        thongtinHDLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        thongtinHDLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        thongtinHDLabel_1.setBounds(75, 11, 176, 30);
        infoDetailOrderPanel.add(thongtinHDLabel_1);

        addReceivingBtn = new JButton("Thêm");
        addReceivingBtn.setBackground(new Color(0x007AFF));
        addReceivingBtn.setForeground(Color.white);
        addReceivingBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (!idMaterialTxt.getText().equals("")){
//                    JOptionPane.showMessageDialog(null, "Không được chọn nguyên liệu đã có sẵn để thêm! Khi thêm id phải để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                }
                if(nameMaterialTxt.getText().equals("") || priceMaterialTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    Material material = new Material();
                    material.setId(Integer.valueOf(idMaterialTxt.getText()));
                    material.setName(nameMaterialTxt.getText());
                    material.setPrice(Integer.parseInt(priceMaterialTxt.getText()));
                    material.setAmount(Integer.parseInt(soLuongNhapTxt.getText()));
                    material.setUnit((String) unitMaterialCbB.getSelectedItem());
                    tempMaterialList.add(material);
                    //tempId++;
                    showTempMaterial();
                    checkBtn.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Đã thêm nguyên liệu!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    ctPNTable.clearSelection();
                    idMaterialTxt.setText(null);
                    nameMaterialTxt.setText(null);
                    soLuongNhapTxt.setText(null);
                    priceMaterialTxt.setText(null);
                }
            }
        });
        addReceivingBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addReceivingBtn.setBounds(33, 400, 90, 35);
        infoDetailOrderPanel.add(addReceivingBtn);

        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.setBackground(new Color(0x007AFF));
        clearInfoBtn.setForeground(Color.white);
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delReceivingBtn.setEnabled(false);
                ctPNTable.clearSelection();
                idMaterialTxt.setText(null);
                nameMaterialTxt.setText(null);
                soLuongNhapTxt.setText(null);
                priceMaterialTxt.setText(null);
                checkBtn.setEnabled(true);
//                if(tempMaterialList.isEmpty()) {
//                	S = S - 1;
//                }
                S = S - 1;
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        clearInfoBtn.setBounds(121, 400, 90, 35);
        infoDetailOrderPanel.add(clearInfoBtn);

//        updateReceivingBtn = new JButton("Cập nhật");
//        updateReceivingBtn.setFont(new Font("Arial", Font.PLAIN, 13));
//        updateReceivingBtn.setBounds(98, 290, 90, 35);
//        infoDetailOrderPanel.add(updateReceivingBtn);

        delReceivingBtn = new JButton("Xóa");
        delReceivingBtn.setBackground(new Color(0x007AFF));
        delReceivingBtn.setForeground(Color.white);
        delReceivingBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                if(decide == 0) {
                    int row = ctPNTable.getSelectedRow();
                    int delId = Integer.parseInt(detailTableModel.getValueAt(row, 0).toString());
                    for (Material material : tempMaterialList){
                        if (material.getId() == delId){
                            tempMaterialList.remove(material);
                            break;
                        }
                    }
                    showTempMaterial();
                    JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        delReceivingBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delReceivingBtn.setBounds(208, 400, 90, 35);
        infoDetailOrderPanel.add(delReceivingBtn);
        
        checkBtn = new JButton("");
        checkBtn.setIcon(new ImageIcon("C:\\Users\\Magaki\\Downloads\\Java Downloads\\arrow-right-circle-icon-512x512-2p1e2aaw.png"));
        checkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkMaterial(nameMaterialTxt.getText());
			}
        	
        });
        
        //checkMaterial
        checkBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        checkBtn.setBounds(290, 163, 30, 30);
        infoDetailOrderPanel.add(checkBtn);

        //End


        //Tạo hóa đơn
        JLabel orderInfoLabel = new JLabel("THÔNG TIN PHIẾU NHẬP");
        orderInfoLabel.setFont(new Font("Arial", Font.BOLD, 15));
        orderInfoLabel.setBounds(10, 471, 200, 20);
        contentField.add(orderInfoLabel);

        JLabel idPNLable = new JLabel("Mã phiếu nhập");
        idPNLable.setFont(new Font("Arial", Font.BOLD, 13));
        idPNLable.setBounds(30, 522, 120, 30);
        contentField.add(idPNLable);

        idPNTxt = new JTextField();
        idPNTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idPNTxt.setBounds(160, 522, 178, 30);
        idPNTxt.setEditable(false);
        contentField.add(idPNTxt);
        idPNTxt.setColumns(10);

        JLabel datePNLabel = new JLabel("Ngày tạo phiếu");
        datePNLabel.setFont(new Font("Arial", Font.BOLD, 13));
        datePNLabel.setBounds(30, 574, 120, 30);
        contentField.add(datePNLabel);

        datePNChooser = new JDateChooser();
        datePNChooser.setBounds(160, 574, 178, 30);
        contentField.add(datePNChooser);

        JLabel idNCCLabel = new JLabel("Mã nhà cung cấp");
        idNCCLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idNCCLabel.setBounds(370, 522, 120, 30);
        contentField.add(idNCCLabel);

        idNCCTxt = new JTextField();
        idNCCTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idNCCTxt.setColumns(10);
        idNCCTxt.setBounds(532, 522, 178, 30);
        contentField.add(idNCCTxt);

        JLabel idStaffCreateOrderLabel = new JLabel("Mã nhân viên tạo phiếu");
        idStaffCreateOrderLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idStaffCreateOrderLabel.setBounds(370, 574, 150, 30);
        contentField.add(idStaffCreateOrderLabel);

        idStaffCreatePNTxt = new JTextField();
        idStaffCreatePNTxt.setEditable(false);
        idStaffCreatePNTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idStaffCreatePNTxt.setColumns(10);
        idStaffCreatePNTxt.setBounds(530, 574, 180, 30);
        contentField.add(idStaffCreatePNTxt);

        JLabel totalPriceLabel = new JLabel("Tổng tiền");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 13));
        totalPriceLabel.setBounds(758, 522, 102, 30);
        contentField.add(totalPriceLabel);

        totalPricePNTxt = new JTextField();
        totalPricePNTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        totalPricePNTxt.setColumns(10);
        totalPricePNTxt.setBounds(870, 522, 178, 30);
        totalPricePNTxt.setEditable(false);
        contentField.add(totalPricePNTxt);


        //Nút chuyển tiến trình
        btnField = new JPanel();
        btnField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        btnField.setPreferredSize(new Dimension(1080, 70));
        btnField.setLayout(null);
        this.add(btnField, BorderLayout.SOUTH);

        
        preBtn = new JButton("Quay lại");
        preBtn.setBackground(new Color(0x007AFF));
        preBtn.setForeground(Color.white);
        preBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(!tempMaterialList.isEmpty()) {
                    int decide = JOptionPane.showConfirmDialog(null, "Mot so du lieu van chua duoc luu, ban co muon quay lai?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if(decide==0) {
                        //JButton viewBtn = ReceivingGUI.viewBtn;
                        //viewBtn.setEnabled(false);
                        tempMaterialList.clear();
                        GiaoDien.phieuNhap.setVisible(true);
                        GiaoDien.taoPhieu.setVisible(false);
                        tempId = 0;
                    }
                }
                else {
                    JButton viewBtn = ReceivingGUI.viewBtn;
                    viewBtn.setEnabled(false);
                    tempMaterialList.clear();
                    GiaoDien.phieuNhap.setVisible(true);
                    GiaoDien.taoPhieu.setVisible(false);
                    tempId = 0;
                }
            }
        });
        preBtn.setFont(new Font("Arial", Font.BOLD, 17));
        preBtn.setBounds(70, 10, 100, 50);
        btnField.add(preBtn);

        nextBtn = new JButton("Tiếp");
        nextBtn.setBackground(new Color(0x007AFF));
        nextBtn.setForeground(Color.white);
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

    public void showTableReceiving(){
        if (noteStatic.getId() != 0){
            addReceivingBtn.setEnabled(false);
            delReceivingBtn.setEnabled(false);
            nextBtn.setEnabled(false);
        }
        else {
            addReceivingBtn.setEnabled(true);
            delReceivingBtn.setEnabled(true);
            nextBtn.setEnabled(true);
        }

        detailList = detailReceiveService.getAll();
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }

        for (ReceivedNoteDetail detail : detailList){
            if (detail.getReceivedNoteId() == noteStatic.getId()){
                String unit="";
                for (Material material : materialList){
                    if (detail.getMaterialId() == material.getId()){
                        unit = material.getUnit();
                        break;
                    }
                }
                Material material = new Material();
                material.setId(detail.getMaterialId());
                material.setName(detail.getName());
                material.setAmount(detail.getAmount());
                material.setUnit(unit);
                material.setPrice(detail.getPrice());
                tempMaterialList.add(material);
//                detailTableModel.addRow(new Object[] {
//                        detail.getMaterialId(), detail.getName(), detail.getAmount(), unit, detail.getPrice()
//                });
            }
        }
    }

    public void showTempMaterial(){
        int totalPrice=0;
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        for (Material material : tempMaterialList){
            //String id="";
            totalPrice += material.getPrice()*material.getAmount();
            detailTableModel.addRow(new Object[] {
                    material.getId(),material.getName(),material.getAmount(), material.getUnit(), material.getPrice()
            });
        }
        totalPricePNTxt.setText(String.valueOf(totalPrice));
    }
    
    public void checkMaterial(String name) {
    	boolean flag = false;
    	for(Material i : materialList) {
    		if(i.getName().equalsIgnoreCase(name)) {
    			idMaterialTxt.setText(String.valueOf(i.getId()));
    			System.out.println(i.getId());
    			for(int j=0; j<unitMaterialCbB.getItemCount(); j++) {
    				if(unitMaterialCbB.getItemAt(j).toString().equals(i.getUnit())) {
    					unitMaterialCbB.setSelectedIndex(j);
    				}
    			}
    			flag = true;  
    			S = S + 1;	
    			break;
    		}
    		else {
    			idMaterialTxt.setText(null);
    		}
    	}    	
    	if(!flag) {   					
			S = S + 1;			
			System.out.println(S);
			idMaterialTxt.setText(String.valueOf(S));
			checkBtn.setEnabled(flag);			
    	}
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == nextBtn) {
            if(idNCCTxt.getText().equals("") || Integer.parseInt(totalPricePNTxt.getText()) == 0
                    || idStaffCreatePNTxt.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            else {
                java.sql.Date sqlDate = new Date(datePNChooser.getDate().getTime());
                boolean checkIfSuccess = receivedNoteService.addReceiving(Integer.parseInt(idStaffCreatePNTxt.getText()),
                        Integer.parseInt(idNCCTxt.getText()),
                        Integer.parseInt(totalPricePNTxt.getText()),
                        sqlDate);
                if (checkIfSuccess){
                    receivedNoteList = receivedNoteService.getAllReceiving();
                    int id = receivedNoteList.get(receivedNoteList.size()-1).getId();
                    for (Material material : tempMaterialList){
                        boolean check = false;            
                        for (Material storeMaterial : materialList){
                            if (storeMaterial.getName().equalsIgnoreCase(material.getName())){                         	
                                materialService.modifyMaterial(storeMaterial.getName(),
                                        storeMaterial.getUnit(),
                                        material.getPrice(),
                                        storeMaterial.getAmount()+material.getAmount(),
                                        storeMaterial.getId());
                                check = true;
                            }
                            
                        }
                        if (!check){
                            materialService.addMaterial(material.getId(), material.getName(),material.getUnit(),material.getPrice(),material.getAmount());
                        }
                        
                        detailReceiveService.addDetailNote(material.getId(),id,material.getName(),
                                material.getAmount(), material.getPrice());
                    }
                    materialList = materialService.getAllMaterial();
                    GiaoDien.phieuNhap.showTableReceiving();
                    GiaoDien.material.showTableMaterial();
                    productGUI.showTableProduct();
                    nextBtn.setEnabled(false);
                    addReceivingBtn.setEnabled(false);
                    delReceivingBtn.setEnabled(false);
                    tempMaterialList.clear();
                    JOptionPane.showMessageDialog(null, "Tạo phiếu nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    GiaoDien.phieuNhap.setVisible(true);
                    GiaoDien.taoPhieu.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nhà cung cấp hoặc nhân viên không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

//                else {
//                    for (int i=count; i <tempMaterialList.size(); i++){
//                        count++;
//                        materialService.addMaterial(tempMaterialList.get(i).getName(),
//                                tempMaterialList.get(i).getUnit(),
//                                tempMaterialList.get(i).getPrice(),
//                                tempMaterialList.get(i).getAmount());
//                        materialList = materialService.getAllMaterial();
//                        int id = materialService.getAllMaterial().get(materialList.size()-1).getId();
//                        detailReceiveService.addDetailNote(id,Integer.parseInt(idPNTxt.getText()),
//                                tempMaterialList.get(i).getName(),
//                                tempMaterialList.get(i).getAmount(),
//                                tempMaterialList.get(i).getPrice());
//                        tempMaterialList.get(i).setId(id);
//                    }
//                    showTempMaterial();
//                    GiaoDien.phieuNhap.showTableReceiving();
//                    GiaoDien.material.showTableMaterial();
//                    JOptionPane.showMessageDialog(null, "Cập nhật phiếu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                }
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
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import model.Material;
import model.Product;
import model.Staff;
import model.Supplier;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import service.*;

import java.awt.Canvas;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

// import org.apache.poi.hssf.usermodel.HSSFCell;
// import org.apache.poi.hssf.usermodel.HSSFRow;
// import org.apache.poi.hssf.usermodel.HSSFSheet;
// import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class StatisticsGUI extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel labelPanel;
    private JPanel mainPanel;
    private JPanel filterPanel;
    private JScrollPane statisticsSconllPane;
    private JTable statisticTable;
    private DefaultTableModel model;
    private JComboBox<String> objectCbB;
    private JComboBox<String> selectedCbB;
    private JComboBox<String> sortCbB;
    private JButton submtBtn;
    private JLabel filterIcon;
    private JLabel dateLabel;
    private JTextField proceedsTxt;
    private JTextField totalSpendingTxt;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel buttonPane;
    private JButton exportBtnp;
    OrderService orderService = new OrderService();
    StaffService staffService = new StaffService();
    ProductService productService = new ProductService();
    MaterialService materialService = new MaterialService();
    SupplierService supplierService = new SupplierService();
    List<Supplier> supplierList = supplierService.getAllSupplier();
    List<Material> materialList = materialService.getAllMaterial();
    List<Staff> staffList = staffService.getAllStaff();
    List<Product> productList = productService.getAllProduct();


    public StatisticsGUI() {
        init();

    }

    private void init() {
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1080, 700));
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        ///////////////
        labelPanel = new JPanel(new BorderLayout());
        labelPanel.setPreferredSize(new Dimension(1080, 50));
        // labelPanel.setBorder(getBorder());
        this.add(labelPanel, BorderLayout.NORTH);
        JLabel bigName = new JLabel("THỐNG KÊ");
        bigName.setFont(new Font("Tahoma", Font.BOLD, 16));
        bigName.setHorizontalAlignment(SwingConstants.CENTER);
        labelPanel.add(bigName,  BorderLayout.CENTER);
        /////////////////////////////



        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(1080, 650));
        this.add(mainPanel);
        ///////////////////////////////////
        filterPanel = new JPanel(null);
        filterPanel.setPreferredSize(new Dimension(1080, 50));
        mainPanel.add(filterPanel, BorderLayout.NORTH);

        objectCbB = new JComboBox<String>();
        objectCbB.setFont(new Font("Tahoma", Font.BOLD, 13));
        objectCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Nhân viên", "Món ăn", "Nguyên liệu", "Nhà cung cấp"}));
        objectCbB.setBounds(70, 10, 150, 30);
        filterPanel.add(objectCbB);

        selectedCbB = new JComboBox<String>();
        selectedCbB.setFont(new Font("Tahoma", Font.BOLD, 13));
        selectedCbB.setBounds(220, 10, 150, 30);
        filterPanel.add(selectedCbB);

        submtBtn = new JButton("OK");
        submtBtn.setBounds(960, 10, 110, 30);
        filterPanel.add(submtBtn);

        filterIcon = new JLabel("New label");
        filterIcon.setIcon(new ImageIcon("ImagesIcon/filterIcon2.png"));
        filterIcon.setBounds(10, 0, 50, 50);
        filterPanel.add(filterIcon);

        dateLabel = new JLabel("Ngày");
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setBounds(380, 0, 50, 50);
        filterPanel.add(dateLabel);

        JDateChooser dateChooserFrom = new JDateChooser();
        dateChooserFrom.setBounds(440, 10, 110, 30);
        filterPanel.add(dateChooserFrom);

        JDateChooser dateChooserTo = new JDateChooser();
        dateChooserTo.setBounds(560, 10, 110, 30);
        filterPanel.add(dateChooserTo);

        JLabel sortLabel = new JLabel("Sắp xếp");
        sortLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        sortLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sortLabel.setBounds(680, 10, 60, 30);
        filterPanel.add(sortLabel);

        sortCbB = new JComboBox<String>();
        sortCbB.setBounds(750, 10, 150, 30);
        filterPanel.add(sortCbB);

        objectCbB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(objectCbB.getSelectedItem().toString().equals("Nhân viên")) {

                    selectedCbB.removeAllItems();
                    selectedCbB.addItem("Hóa đơn bán");
                    selectedCbB.addItem("Phiếu nhập");

                    sortCbB.removeAllItems();
                    sortCbB.addItem("Tổng tiền tăng");
                    sortCbB.addItem("Tổng tiền giảm");
                }
                if(objectCbB.getSelectedItem().toString().equals("Món ăn")) {
                    selectedCbB.removeAllItems();
                    selectedCbB.addItem("Bán ra");

                    sortCbB.removeAllItems();
                    sortCbB.addItem("Tổng tiền tăng");
                    sortCbB.addItem("Tổng tiền giảm");

                }
                if(objectCbB.getSelectedItem().toString().equals("Nguyên liệu")) {
                    selectedCbB.removeAllItems();
                    selectedCbB.addItem("Nhập vào");

                    sortCbB.removeAllItems();
                    sortCbB.addItem("Tổng tiền tăng");
                    sortCbB.addItem("Tổng tiền giảm");
                }
                if(objectCbB.getSelectedItem().toString().equals("Nhà cung cấp")) {
                    selectedCbB.removeAllItems();
                    selectedCbB.addItem("Ng.liệu nhập vào");

                    sortCbB.removeAllItems();
                    sortCbB.addItem("Tổng tiền tăng");
                    sortCbB.addItem("Tổng tiền giảm");
                }
            }

        });

        //////////////////////////////////////
        model = new DefaultTableModel(new Object [] {"STT", " ", " ", "Số lượng", "Tổng tiền"}, 0);
        statisticTable = new MacOSStyleTable(model);
        for(int i = 0; i < 4; i++) {
            statisticTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        statisticTable.setRowHeight(30);
        statisticTable.setFont(new Font("Arial", Font.PLAIN, 14));
        submtBtn.addActionListener(new ActionListener() {    	
            @Override
            public void actionPerformed(ActionEvent e) {
                if(objectCbB.getSelectedItem().toString().equals("Nhân viên") && selectedCbB.getSelectedItem().toString().equals("Hóa đơn bán")) {
                    statisticTable.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Mã nhân viên");
                    statisticTable.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("Số lượng món bán được");
                    statisticTable.getTableHeader().getColumnModel().getColumn(3).setHeaderValue("Số lượng hóa đơn");
                    statisticTable.getTableHeader().repaint();
                    showBillOfStaff();

                }
                else if(objectCbB.getSelectedItem().toString().equals("Nhân viên") && selectedCbB.getSelectedItem().toString().equals("Phiếu nhập")) {
                    statisticTable.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Mã nhân viên");
                    statisticTable.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("Số lượng ng.liệu nhập");
                    statisticTable.getTableHeader().getColumnModel().getColumn(3).setHeaderValue("Số lượng phiếu nhập");
                    statisticTable.getTableHeader().repaint();
                    showMaterialAmountOfStaff();
                }
                else if(objectCbB.getSelectedItem().toString().equals("Món ăn") && selectedCbB.getSelectedItem().toString().equals("Bán ra")) {
                    statisticTable.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Mã món ăn");
                    statisticTable.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(" ");
                    statisticTable.getTableHeader().getColumnModel().getColumn(3).setHeaderValue("Số lượng món");
                    statisticTable.getTableHeader().repaint();
                    showProductStatistic();
                }
                else if(objectCbB.getSelectedItem().toString().equals("Nguyên liệu") && selectedCbB.getSelectedItem().toString().equals("Nhập vào")) {
                    statisticTable.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Mã nguyên liệu");
                    statisticTable.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(" ");
                    statisticTable.getTableHeader().getColumnModel().getColumn(3).setHeaderValue("Số lượng ng.liệu");
                    statisticTable.getTableHeader().repaint();
                    showMaterialStatistic();
                }
                else if(objectCbB.getSelectedItem().toString().equals("Nhà cung cấp") && selectedCbB.getSelectedItem().toString().equals("Ng.liệu nhập vào")) {
                    statisticTable.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Mã nhà cung cấp");
                    statisticTable.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(" ");
                    statisticTable.getTableHeader().getColumnModel().getColumn(3).setHeaderValue("Số lượng ng.liệu");
                    statisticTable.getTableHeader().repaint();
                    showSupplierStatistic();
                }
            }


        });

        statisticsSconllPane = new CustomScrollPane(statisticTable);
        mainPanel.add(statisticsSconllPane, BorderLayout.CENTER);

        ////////////////////////////////
        buttonPane = new JPanel(null);
        buttonPane.setPreferredSize(new Dimension(1080, 100));
        this.add(buttonPane, BorderLayout.SOUTH);

        JLabel totalRevenue = new JLabel("Tổng doanh thu");
        proceedsTxt = new JTextField();
        int proceeds = 0;
        for (Product product : productList){
            proceeds += productService.totalPriceOfASoldProduct(product.getId());
        }
        totalRevenue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        totalRevenue.setBounds(10, 35, 200, 30);
        proceedsTxt.setBounds(130, 35, 200, 30);
        proceedsTxt.setText(String.valueOf(proceeds));
        proceedsTxt.setEnabled(false);
        buttonPane.add(proceedsTxt);
        buttonPane.add(totalRevenue);

        JLabel totalSpending = new JLabel("Tổng chi tiêu");
        totalSpendingTxt = new JTextField();
        int spending = 0;
        for (Material material : materialList){
            spending += materialService.totalReceivePriceOfAMaterial(material.getId());
        }
        totalSpending.setFont(new Font("Tahoma", Font.PLAIN, 15));
        totalSpending.setBounds(350, 35, 200, 30);
        totalSpendingTxt.setText(String.valueOf(spending));
        totalSpendingTxt.setBounds(470, 35, 200, 30);
        totalSpendingTxt.setEnabled(false);
        buttonPane.add(totalSpendingTxt);
        buttonPane.add(totalSpending);

        JButton excelBtn = new JButton("Xuất Excel");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Statistics");
        excelBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (model.getRowCount() != 0){
                   boolean check = true;
                   int rownum = 0;
                   for (int i = 0; i < model.getRowCount(); i++) {
                       HSSFRow row = sheet.createRow(rownum++);
                       for (int j = 0; j < model.getColumnCount(); j++) {
                           HSSFCell cell = row.createCell(j);
                           cell.setCellValue(model.getValueAt(i, j).toString());
                       }
                   }
                   try {
                       FileOutputStream out = new FileOutputStream(new File("C:\\Users\\VieetKhooii\\Downloads\\Statistics.CSV"));
                       workbook.write(out);
                       out.close();
                   } catch (IOException ex) {
                       check=false;
                       System.out.println("StatisticsGUI: Error importing excel");
                       throw new RuntimeException(ex);
                   }
                   if (check){
                       JOptionPane.showMessageDialog(null, "Xuất Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                   }
               }
               else {
                   JOptionPane.showMessageDialog(null, "Chọn đối tượng thống kê trước khi xuất Excel", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
               }
           }
            });
        excelBtn.setBounds(970, 30, 100, 40);
        buttonPane.add(excelBtn);
    }

    // Staff {
    private void showBillOfStaff(){
        while (model.getRowCount() != 0){
            model.removeRow(0);
        }
        for (int i=0; i<staffList.size(); i++){
            int numberOfBills = orderService.billOfStaff(staffList.get(i).getId());
            model.addRow(new Object[]{
                i+1,staffList.get(i).getId(), orderService.productOfStaff(staffList.get(i).getId()), numberOfBills, orderService.totalPriceOfStaff(staffList.get(i).getId())
            });
        }
    }

    private void showMaterialAmountOfStaff(){
        ReceivedNoteService receivedNoteService = new ReceivedNoteService();
        while (model.getRowCount() != 0){
            model.removeRow(0);
        }
        for (int i=0; i<staffList.size(); i++){
            int staffId = staffList.get(i).getId();
            model.addRow(new Object[]{
                    i+1,staffList.get(i).getId(), receivedNoteService.totalMaterialAmountOfStaff(staffId),
                    receivedNoteService.totalReceiveNoteOfStaff(staffId), receivedNoteService.totalMaterialPriceOfStaff(staffId)
            });
        }
    }
    // Staff }

    // Product {

    private void showProductStatistic(){
        while (model.getRowCount() != 0){
            model.removeRow(0);
        }
        for (int i=0; i<productList.size(); i++){
            int productId = productList.get(i).getId();
            model.addRow(new Object[]{
                    i+1, productId, "", productService.totalProductSoldAmount(productId), productService.totalPriceOfASoldProduct(productId)
            });
        }
    }
    // Product }

    // Material {

    private void showMaterialStatistic(){
        while (model.getRowCount() != 0){
            model.removeRow(0);
        }
        for (int i=0; i<materialList.size(); i++){
            int materialId = materialList.get(i).getId();
            model.addRow(new Object[]{
                    i+1, materialId, "", materialService.totalMaterialReceived(materialId), materialService.totalReceivePriceOfAMaterial(materialId)
            });
        }
    }

    // Material }

    // Supplier {
    private void showSupplierStatistic(){
        while (model.getRowCount() != 0){
            model.removeRow(0);
        }
        for (int i=0; i<supplierList.size(); i++){
            int supplierId = supplierList.get(i).getId();
            model.addRow(new Object[]{
                    i+1, supplierId, "", supplierService.totalMaterialOfASupplier(supplierId),
                    supplierService.totalMaterialPriceOfASupplier(supplierId)
            });
        }
    }
    // Supplier }

//    public static void main(String[] args) {
//        JFrame a = new JFrame();
//        a.setVisible(true);
//        a.setSize(1080,700);
//        a.getContentPane().add(new StatisticsGUI());
//    }
}
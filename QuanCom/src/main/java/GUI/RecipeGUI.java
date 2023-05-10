package GUI;

import model.Recipe;
import model.Roles;
import model.Staff;
import service.RecipeService;

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
import java.util.List;

public class RecipeGUI extends JPanel implements ActionListener{

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
    private JButton clearInfoBtn;
    private JComboBox<String> searchCbB;
    private JTextField searchTxt;
    private JLabel lblSpXp;
    private JComboBox<String> sortCbB;
    private JLabel lblTmKim;
    private JButton searchButton;
    private int oldProductId=0;
    private int oldMaterialId=0;
    RecipeService recipeService = new RecipeService();
    List<Recipe> recipeList = recipeService.getAllRecipe();

    /**
     * Create the panel.
     */
    public RecipeGUI() {
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
        staffListPanel = new JPanel(null);
        staffListPanel.setBackground(new Color(0,0,0,80));
        staffListPanel.setBounds(0, 340, 1080, 360);

        contentField.add(staffListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã món", "Mã nguyên liệu", "Số lượng"}, 0);
        recipeTable = new MacOSStyleTable(detailTableModel);
        recipeTable.setFont(new Font("Arial", Font.PLAIN, 14));
        recipeTable.setDefaultRenderer(String.class, centerRenderer);
        recipeTable.setRowHeight(30);
        for(int i = 0; i < 3; i++) {
            recipeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        ListSelectionModel listSelectionModel = recipeTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = recipeTable.getSelectedRow();
                if (row >= 0){
                    idProductTxt.setEnabled(false);
                    idMaterialTxt.setEnabled(false);
                    delRecipeBtn.setEnabled(true);
                    fixRecipeBtn.setEnabled(true);
                    oldMaterialId = (int) detailTableModel.getValueAt(row, 1);
                    oldProductId =  (int) detailTableModel.getValueAt(row, 0);
                    idProductTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    idMaterialTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    soLuongTxt.setText(detailTableModel.getValueAt(row, 2).toString());
//                    priceEveryMaterialTxt.setText(detailTableModel.getValueAt(row, 3).toString());
                }
            }
        });
        detailTableModel.addRow(new Object[] {"12", "1", "24", 3000000});

        recipeScrollPane = new CustomScrollPane(recipeTable);
        recipeScrollPane.setBounds(5, 5, 1070, 320);
        staffListPanel.add(recipeScrollPane);

        recipeInfoPanel = new JPanel();
        recipeInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        recipeInfoPanel.setBounds(0, 50, 600, 290);
        contentField.add(recipeInfoPanel);
        recipeInfoPanel.setLayout(null);

        lblNewLabel = new JLabel("Thông tin công thức");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(200, 0, 200, 40);
        recipeInfoPanel.add(lblNewLabel);

        JLabel idProductLabel = new JLabel("Mã món ăn");
        idProductLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idProductLabel.setBounds(127, 51, 123, 30);
        recipeInfoPanel.add(idProductLabel);

        idProductTxt = new JTextField();
        idProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idProductTxt.setColumns(10);
        idProductTxt.setBounds(263, 51, 224, 30);
        recipeInfoPanel.add(idProductTxt);

        idMaterialTxt = new JTextField();
        idMaterialTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idMaterialTxt.setColumns(10);
        idMaterialTxt.setBounds(263, 111, 224, 30);
        recipeInfoPanel.add(idMaterialTxt);

        JLabel idMaterialLabel = new JLabel("Mã nguyên liệu");
        idMaterialLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idMaterialLabel.setBounds(127, 111, 123, 30);
        recipeInfoPanel.add(idMaterialLabel);

        JLabel soLuongRecipeLb = new JLabel("Số lượng ng.liệu");
        soLuongRecipeLb.setFont(new Font("Arial", Font.BOLD, 13));
        soLuongRecipeLb.setBounds(127, 171, 123, 30);
        recipeInfoPanel.add(soLuongRecipeLb);

        soLuongTxt = new JTextField();
        soLuongTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        soLuongTxt.setColumns(10);
        soLuongTxt.setBounds(263, 171, 224, 30);
        recipeInfoPanel.add(soLuongTxt);

        addRecipeBtn = new JButton("Thêm");
        addRecipeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(idMaterialTxt.getText().equals("") || idProductTxt.getText().equals("") || soLuongTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    boolean check = recipeService.addRecipe(Integer.parseInt(idProductTxt.getText()),Integer.parseInt(idMaterialTxt.getText()),Integer.parseInt(soLuongTxt.getText()));
                    if (check){
                        showAllRecipe();
                        JOptionPane.showMessageDialog(null, "Đã thêm công thức!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Công thức đã tồn tại hoặc mã nguyên liệu, mã sản phẩm không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        addRecipeBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addRecipeBtn.setBounds(127, 233, 90, 35);
        recipeInfoPanel.add(addRecipeBtn);

        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                idProductTxt.setEnabled(true);
                idMaterialTxt.setEnabled(true);
                fixRecipeBtn.setEnabled(false);
                delRecipeBtn.setEnabled(false);
                recipeTable.clearSelection();
                idProductTxt.setText(null);
                idMaterialTxt.setText(null);
                soLuongTxt.setText(null);
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        clearInfoBtn.setBounds(397, 233, 90, 35);
        recipeInfoPanel.add(clearInfoBtn);

        fixRecipeBtn = new JButton("Cập nhật");
        fixRecipeBtn.setEnabled(false);
        fixRecipeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(idMaterialTxt.getText().equals("") || idProductTxt.getText().equals("") || soLuongTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    System.out.println("pro: "+oldProductId);
                    System.out.println("mate: "+oldMaterialId);
                    boolean check = recipeService.modifyRecipe(Integer.parseInt(idProductTxt.getText()),Integer.parseInt(idMaterialTxt.getText()),Integer.parseInt(soLuongTxt.getText()),oldProductId,oldMaterialId);
                    if (check){
                        showAllRecipe();
                        JOptionPane.showMessageDialog(null, "Đã sửa công thức!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Công thức đã tồn tại hoặc mã nguyên liệu, mã sản phẩm không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        fixRecipeBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        fixRecipeBtn.setBounds(217, 233, 90, 35);
        recipeInfoPanel.add(fixRecipeBtn);

        delRecipeBtn = new JButton("Xóa");
        delRecipeBtn.setEnabled(false);
        delRecipeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idMaterialTxt.getText().equals("") || idProductTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 công thức và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    //xoa o day
                    if(decide == 0) {
                        recipeService.deleteRecipe(Integer.parseInt(idProductTxt.getText()),
                                Integer.parseInt(idMaterialTxt.getText()));
                        clearInfoBtn.doClick();
                        showAllRecipe();
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        delRecipeBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delRecipeBtn.setBounds(307, 233, 90, 35);
        recipeInfoPanel.add(delRecipeBtn);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBackground(new Color(0x007AFF));
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel supplierLabel = new JLabel("BẢNG CÔNG THỨC");
        supplierLabel.setForeground(SystemColor.text);
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
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã món", "Mã ng.liệu"}));
        searchCbB.setBounds(105, 75, 101, 40);
        searchPanel.add(searchCbB);

        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        searchTxt.setColumns(10);
        searchTxt.setBounds(216, 75, 149, 40);
        searchPanel.add(searchTxt);

        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));
        lblSpXp.setBounds(105, 145, 80, 40);
        searchPanel.add(lblSpXp);

        sortCbB = new JComboBox<String>();
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Mã món tăng dần", "Mã món giảm dần"
        		, "Mã ng.liệu tăng dần", "Mã ng.liệu giảm dần"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));
        sortCbB.setBounds(216, 145, 149, 40);
        searchPanel.add(sortCbB);

        lblTmKim = new JLabel("Tìm kiếm");
        lblTmKim.setHorizontalAlignment(SwingConstants.CENTER);
        lblTmKim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTmKim.setBounds(180, 11, 120, 40);
        searchPanel.add(lblTmKim);

        searchButton = new JButton("OK");      
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {  
        		showSearchResult(searchTxt.getText(), searchCbB.getSelectedItem().toString().trim(), sortCbB.getSelectedItem().toString().trim());
        	}
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(141, 229, 100, 50);
        searchPanel.add(searchButton);
        
        JButton rmSearchBtn = new JButton("Hủy");
        rmSearchBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchTxt.setText("");
        		showAllRecipe();
        		sortCbB.setSelectedIndex(0);
        	}
        });
        rmSearchBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        rmSearchBtn.setBounds(240, 229, 100, 50);
        searchPanel.add(rmSearchBtn);
        //End
        showAllRecipe();


    }

    public void showAllRecipe(){
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        recipeList = recipeService.getAllRecipe();
        for (Recipe recipe : recipeList){
            detailTableModel.addRow(new Object[] {
                    recipe.getProductId(), recipe.getMaterialId(), recipe.getAmount()
            });
        }
    }
    
    private void showSearchResult(String searchTxt, String optSearch, String optSort) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        List<Recipe> searchResultList = recipeService.getAllSearchResult(searchTxt, optSearch, optSort);
        for (Recipe recipe : searchResultList){
            detailTableModel.addRow(new Object[] {
                    recipe.getProductId(), recipe.getMaterialId(), recipe.getAmount()
            });
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
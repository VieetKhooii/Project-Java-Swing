package GUI;

import model.*;
import service.CategoryService;
import service.MaterialService;
import service.ProductService;
//import service.RecipeService;
import service.RecipeService;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import enumm.ProductUnit;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProductGUI extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentField;
    private JPanel productListPanel;
    private JTable productTable;
    private DefaultTableModel detailTableModel;
    private JScrollPane productScrollPane;
    private DefaultTableCellRenderer centerRenderer;
    private JPanel searchPanel;
    private JLabel lblTmKim;
    private JComboBox<String> searchCbB;
    private JComboBox<String> categorySearchCbB;
    private JTextField searchTxt;
    private JLabel lblSpXp;
    private JComboBox<String> sortCbB;
    private JTextField idProductTxt;
    private JTextField priceProductTxt;
    private JTextField nameProductTxt;
    private JTextField soLuongProductTxt;
    private JComboBox<String> unitProductCbB;
    private JComboBox<String> categoryProductCbB;
    private JButton addProductBtn;
    private JButton fixProductBtn;
    private JButton delProductBtn;
    private JButton browsePhoto;
    private JButton clearInfoBtn;
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    MaterialService materialService = new MaterialService();
    RecipeService recipeService = new RecipeService();
    List<Product> productList = productService.getAllProduct();
    List<Category> categoryList = categoryService.getAllCate();
    List<Material> materialList = materialService.getAllMaterial();
    List<Recipe> recipeList = recipeService.getAllRecipe();
    /**
     * Create the panel.
     */
    public ProductGUI() {
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
        productListPanel = new JPanel(null);
        productListPanel.setBackground(new Color(30, 144, 255));
        productListPanel.setBounds(0, 380, 1080, 290);

        contentField.add(productListPanel);

        detailTableModel = new DefaultTableModel(new Object[]{"Mã món", "Tên món", "Đơn vị tính", "Số lượng", "Giá", "Phân loại"}, 0);
        productTable = new JTable(detailTableModel);
        productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        productTable.setDefaultRenderer(String.class, centerRenderer);
        productTable.setRowHeight(30);
        for(int i = 0; i < 6; i++) {
            if(i == 1) {
                productTable.getColumnModel().getColumn(i).setPreferredWidth(250);
                productTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            else {
                //staffTable.getColumnModel().getColumn(i).setPreferredWidth(125);
                productTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        ListSelectionModel listSelectionModel = productTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = productTable.getSelectedRow();
                if (row >= 0){
                    idProductTxt.setText(detailTableModel.getValueAt(row, 0).toString());
                    nameProductTxt.setText(detailTableModel.getValueAt(row, 1).toString());
                    for(int i = 0; i < unitProductCbB.getItemCount(); i++) {
                        if(detailTableModel.getValueAt(row, 2).toString().equals(unitProductCbB.getItemAt(i).toString())) {
                            unitProductCbB.setSelectedIndex(i);
                            break;
                        }
                    }
                    priceProductTxt.setText(detailTableModel.getValueAt(row, 4).toString());
                    soLuongProductTxt.setText(detailTableModel.getValueAt(row, 3).toString());
                    for(int i = 0; i < categoryProductCbB.getItemCount(); i++) {
                        if(detailTableModel.getValueAt(row, 5).toString().equals(categoryProductCbB.getItemAt(i).toString())) {
                            categoryProductCbB.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });


        productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(5, 5, 1070, 280);
        productListPanel.add(productScrollPane);

        JPanel bigNamePanel = new JPanel();
        bigNamePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        bigNamePanel.setBounds(0, 0, 1080, 50);
        contentField.add(bigNamePanel);
        bigNamePanel.setLayout(null);

        JLabel staffLabel = new JLabel("MÓN ĂN");
        staffLabel.setBounds(240, 0, 600, 50);
        bigNamePanel.add(staffLabel);
        staffLabel.setHorizontalAlignment(SwingConstants.CENTER);
        staffLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        // Tìm kiếm
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
        searchCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã", "Tên", "Số lượng"}));
        searchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        searchCbB.setBounds(10, 64, 101, 30);
        searchPanel.add(searchCbB);

        searchTxt = new JTextField();
        searchTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        searchTxt.setColumns(10);
        searchTxt.setBounds(121, 64, 149, 30);
        searchPanel.add(searchTxt);

        lblSpXp = new JLabel("Sắp xếp");
        lblSpXp.setBounds(10, 163, 80, 30);
        searchPanel.add(lblSpXp);
        lblSpXp.setFont(new Font("Arial", Font.BOLD, 13));

        sortCbB = new JComboBox<String>();
        sortCbB.setBounds(121, 163, 149, 30);
        searchPanel.add(sortCbB);
        sortCbB.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Mã giảm dần", "Tên", "Giá tăng dần", "Giá giảm dần", "Số lượng tăng dần", "Số lượng giảm dần"}));
        sortCbB.setFont(new Font("Arial", Font.BOLD, 13));

        JLabel lblPhnLoi_1 = new JLabel("Phân loại");
        lblPhnLoi_1.setFont(new Font("Arial", Font.BOLD, 13));
        lblPhnLoi_1.setBounds(10, 115, 80, 30);
        searchPanel.add(lblPhnLoi_1);

        categorySearchCbB = new JComboBox<String>();
        categorySearchCbB.addItem("Tất cả");
        for (Category category : categoryList){
        	categorySearchCbB.addItem(category.getName());
        }
        categorySearchCbB.setFont(new Font("Arial", Font.BOLD, 13));
        categorySearchCbB.setBounds(121, 115, 149, 30);
        searchPanel.add(categorySearchCbB);
        
        JButton searchButton = new JButton("OK");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {  
        		boolean none = false;
                boolean id = false;
                boolean name = false;
                boolean priceAsc = false;
                boolean priceDes = false;
                boolean qualityAsc = false;
                boolean qualityDes = false;
                if(sortCbB.getSelectedItem().toString().equals("None")) {
                	none = true;
                }
                else if(sortCbB.getSelectedItem().toString().equals("Mã giảm dần")) {
                	id = true;
                }
                else if(sortCbB.getSelectedItem().toString().equals("Tên")) {
                	name = true;
                }                
                else if(sortCbB.getSelectedItem().toString().equals("Giá tăng dần")) {
                	priceAsc = true;
                } 
                else if(sortCbB.getSelectedItem().toString().equals("Giá giảm dần")) {
                	priceDes = true;
                } 
                else if(sortCbB.getSelectedItem().toString().equals("Số lượng tăng dần")) {
                	qualityAsc = true;
                } 
                else if(sortCbB.getSelectedItem().toString().equals("Số lượng giảm dần")) {
                	qualityAsc = true;
                } 
        		if(searchCbB.getSelectedItem().toString().equals("Mã")) {
        			if(!searchTxt.getText().equals("")) {
        				showSearchResultByid(searchTxt.getText(), none, name, id, priceAsc,
        		        		priceDes, qualityAsc, qualityDes);
        			}        			
    			}
    			if(searchCbB.getSelectedItem().toString().equals("Tên")){   				
    				if(!searchTxt.getText().equals("")) {
    					showSearchResultByName(searchTxt.getText(), none, name, id, priceAsc,
        		        		priceDes, qualityAsc, qualityDes);
    				}
    			}
    			if(searchCbB.getSelectedItem().toString().equals("Số lượng")){   				
    				if(!searchTxt.getText().equals("")) {
    					showSearchResultByQuality(searchTxt.getText(), none, name, id, priceAsc,
        		        		priceDes, qualityAsc, qualityDes);
    				}
    			}
    			
    			if(searchTxt.getText().equals("")) {
    				if(!categorySearchCbB.getSelectedItem().toString().equals("Tất cả")) {
        				showSearchResultByCategory(categorySearchCbB.getSelectedItem().toString(), none, name, id, priceAsc,
            		        		priceDes, qualityAsc, qualityDes);
    				}
    				else if(categorySearchCbB.getSelectedItem().toString().equals("Tất cả")) {
    					showSortTable(none, name, id, priceAsc,
        		        		priceDes, qualityAsc, qualityDes);   
    				}    				 				
    			}
        	}
        });
        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        searchButton.setBounds(40, 269, 100, 50);
        searchPanel.add(searchButton);
        
        JButton rmSearchBtn = new JButton("Hủy");
        rmSearchBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchTxt.setText("");   		
        		sortCbB.setSelectedIndex(0);
        		categorySearchCbB.setSelectedIndex(0);
        		showSortTable(true, false, false, false, false, false, false);
        	}
        });
        rmSearchBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        rmSearchBtn.setBounds(139, 269, 100, 50);
        searchPanel.add(rmSearchBtn);
        // Thông tin món ăn
        JPanel staffInfoPanel = new JPanel();
        staffInfoPanel.setLayout(null);
        staffInfoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffInfoPanel.setBounds(0, 50, 800, 330);
        contentField.add(staffInfoPanel);

        JLabel staffPhoto = new JLabel("Ảnh");
        staffPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        staffPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        staffPhoto.setBackground(Color.WHITE);
        staffPhoto.setBounds(41, 70, 140, 130);
        staffInfoPanel.add(staffPhoto);

        JLabel lblThngTinMn = new JLabel("Thông tin món ăn");
        lblThngTinMn.setHorizontalAlignment(SwingConstants.CENTER);
        lblThngTinMn.setFont(new Font("Arial", Font.BOLD, 15));
        lblThngTinMn.setBounds(306, 0, 150, 40);
        staffInfoPanel.add(lblThngTinMn);

        addProductBtn = new JButton("Thêm");
        addProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!idProductTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Không được chọn món ăn đã có sẵn để thêm! Khi thêm id phải để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameProductTxt.getText().equals("") || priceProductTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else if(!priceProductTxt.getText().matches("[0-9]{1,9}")) {
                	JOptionPane.showMessageDialog(null, "Sai đầu vào!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int id=0;
                    for (Category category : categoryList){
                        if (category.getName().equalsIgnoreCase((String) categoryProductCbB.getSelectedItem())){
                            id = category.getId();
                            break;
                        }
                    }
                    productService.addProduct(chuanHoa(nameProductTxt.getText()),
                            0,(String) unitProductCbB.getSelectedItem(),
                            Integer.parseInt(priceProductTxt.getText()),
                            id);
                    showTableProduct();
                    JOptionPane.showMessageDialog(null, "Đã thêm món ăn!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addProductBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        addProductBtn.setBounds(228, 284, 90, 35);
        staffInfoPanel.add(addProductBtn);

        //Clear Information
        clearInfoBtn = new JButton("Clear");
        clearInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productTable.clearSelection();
                idProductTxt.setText(null);
                nameProductTxt.setText(null);
                priceProductTxt.setText(null);
                soLuongProductTxt.setText(null);
            }
        });
        clearInfoBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        clearInfoBtn.setBounds(492, 284, 90, 35);
        staffInfoPanel.add(clearInfoBtn);

        fixProductBtn = new JButton("Cập nhật");
        fixProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idProductTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 món ăn và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nameProductTxt.getText().equals("") || priceProductTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Thông tin chưa đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else if(!priceProductTxt.getText().matches("[0-9]{1,9}")) {
                	JOptionPane.showMessageDialog(null, "Sai đầu vào!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    int id=0;
                    for (Category category : categoryList){
                        if (category.getName().equalsIgnoreCase((String) categoryProductCbB.getSelectedItem())){
                            id = category.getId();
                            break;
                        }
                    }
                    productService.modifyProduct(chuanHoa(nameProductTxt.getText()),
                            Integer.parseInt(soLuongProductTxt.getText()),
                            (String) unitProductCbB.getSelectedItem(),Integer.parseInt(priceProductTxt.getText()),
                            id,Integer.parseInt(idProductTxt.getText()));
                    showTableProduct();
                    JOptionPane.showMessageDialog(null, "Đã sửa tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        fixProductBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        fixProductBtn.setBounds(316, 284, 90, 35);
        staffInfoPanel.add(fixProductBtn);

        delProductBtn = new JButton("Xóa");
        delProductBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idProductTxt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn 1 món ăn và đảm bảo ID hiện lên khung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    int decide = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    //xoa o day
                    if(decide == 0) {
                        productService.deleteProduct(Integer.parseInt(idProductTxt.getText()));
                        clearInfoBtn.doClick();
                        showTableProduct();
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        delProductBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        delProductBtn.setBounds(403, 284, 90, 35);
        staffInfoPanel.add(delProductBtn);

        browsePhoto = new JButton("Chọn");
        browsePhoto.setBounds(71, 220, 80, 30);
        staffInfoPanel.add(browsePhoto);

        idProductTxt = new JTextField();
        idProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        idProductTxt.setColumns(10);
        idProductTxt.setBounds(333, 70, 167, 30);
        idProductTxt.setEditable(false);
        staffInfoPanel.add(idProductTxt);

        priceProductTxt = new JTextField();
        priceProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        priceProductTxt.setColumns(10);
        priceProductTxt.setBounds(593, 70, 167, 30);
        staffInfoPanel.add(priceProductTxt);

        nameProductTxt = new JTextField();
        nameProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        nameProductTxt.setColumns(10);
        nameProductTxt.setBounds(333, 120, 167, 30);
        staffInfoPanel.add(nameProductTxt);

        JLabel lblnVTnh = new JLabel("Đơn vị tính");
        lblnVTnh.setFont(new Font("Arial", Font.BOLD, 13));
        lblnVTnh.setBounds(540, 120, 70, 30);
        staffInfoPanel.add(lblnVTnh);

        unitProductCbB = new JComboBox<String>();
        ProductUnit[] units = ProductUnit.values();
        String[] unitList = new String[units.length];
        for (int i=0; i<unitList.length; i++) {
            unitList[i] = units[i].getUnit();
        }
        unitProductCbB.setModel(new DefaultComboBoxModel<String>(unitList));
        unitProductCbB.setFont(new Font("Arial", Font.BOLD, 13));
        unitProductCbB.setBounds(640, 120, 120, 30);
        staffInfoPanel.add(unitProductCbB);

        JLabel addressSupplierLabel = new JLabel("Giá");
        addressSupplierLabel.setFont(new Font("Arial", Font.BOLD, 13));
        addressSupplierLabel.setBounds(540, 70, 50, 30);
        staffInfoPanel.add(addressSupplierLabel);

        soLuongProductTxt = new JTextField();
        soLuongProductTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        soLuongProductTxt.setColumns(10);
        soLuongProductTxt.setBounds(333, 170, 167, 30);
        soLuongProductTxt.setEditable(false);
        staffInfoPanel.add(soLuongProductTxt);

        JLabel lblPhnLoi = new JLabel("Phân loại");
        lblPhnLoi.setFont(new Font("Arial", Font.BOLD, 13));
        lblPhnLoi.setBounds(540, 170, 70, 30);
        staffInfoPanel.add(lblPhnLoi);

        categoryProductCbB = new JComboBox<String>();
        for (Category category : categoryList){
            categoryProductCbB.addItem(category.getName());
        }
        categoryProductCbB.setFont(new Font("Arial", Font.BOLD, 13));
        categoryProductCbB.setBounds(640, 170, 120, 30);
        staffInfoPanel.add(categoryProductCbB);

        JLabel idProductLabel = new JLabel("Mã món");
        idProductLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idProductLabel.setBounds(250, 70, 70, 30);
        staffInfoPanel.add(idProductLabel);

        JLabel nameProductLabel = new JLabel("Tên món");
        nameProductLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameProductLabel.setBounds(250, 120, 70, 30);
        staffInfoPanel.add(nameProductLabel);

        JLabel soluongLb = new JLabel("Số lượng");
        soluongLb.setFont(new Font("Arial", Font.BOLD, 13));
        soluongLb.setBounds(250, 170, 70, 30);
        staffInfoPanel.add(soluongLb);
        //End
        showTableProduct();


    }
    public void showTableProduct(){
        String categoryName = "";
        while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
        categoryList = categoryService.getAllCate();
        productList = productService.getAllProduct();
        productAmountCal();
        for(Product product : productList) {
            for (Category category : categoryList){
                if (category.getId() == product.getCategoryId()){
                    categoryName = category.getName();
                }
            }
            detailTableModel.addRow(new Object[] {
                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
                    product.getPrice(), categoryName
            });
        }
    }

    private void productAmountCal(){
        int min;
        int temp=0;
        for (Product product : productList){
            min=-1;
            temp=0;
            for (Recipe recipe : recipeList){
                if (product.getId() == recipe.getProductId()){
                    for (Material material : materialList){
                        if (material.getId() == recipe.getMaterialId()){
                            temp = material.getAmount()/recipe.getAmount();
                            break;
                        }
                    }
                    if (min==-1) min = temp;
                    else if (temp < min) min = temp;
                }
            }
            if (min==-1){
                product.setAmount(0);
            }
            else {
                product.setAmount(min);
            }

            productService.modifyProduct(product.getName(),product.getAmount(),product.getUnit(),
                    product.getPrice(),product.getCategoryId(),product.getId());
        }
    }

    //Search
    private List<Product> showSortTable(boolean none, boolean name, boolean id, boolean 
    		priceAsc, boolean priceDes, boolean qualityAsc, boolean qualityDes) {
    	List<Product> sortResultList = null;
    	String categoryName = "";
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
    	if(none) {
    		sortResultList = productService.getAllProduct(); 
    		if(searchTxt.getText().equals("") && categorySearchCbB.getSelectedItem().toString().equals("Tất cả")) {
    			showTableProduct();
    		}
    	}
    	if(name) {
    		sortResultList = productService.sortByName(productList);
    		categoryList = categoryService.getAllCate();
    		if(searchTxt.getText().equals("") && categorySearchCbB.getSelectedItem().toString().equals("Tất cả")) {
    			for(Product product : sortResultList) {
    	            for (Category category : categoryList){
    	                if (category.getId() == product.getCategoryId()){
    	                    categoryName = category.getName();
    	                }
    	            }
    	            detailTableModel.addRow(new Object[] {
    	                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
    	                    product.getPrice(), categoryName
    	            });
    	        }
    		}
    	}
    	if(id) {
    		sortResultList = productService.sortById(productList);
    		categoryList = categoryService.getAllCate();
    		if(searchTxt.getText().equals("") && categorySearchCbB.getSelectedItem().toString().equals("Tất cả")) {
    			for(Product product : sortResultList) {
    	            for (Category category : categoryList){
    	                if (category.getId() == product.getCategoryId()){
    	                    categoryName = category.getName();
    	                }
    	            }
    	            detailTableModel.addRow(new Object[] {
    	                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
    	                    product.getPrice(), categoryName
    	            });
    	        }
    		}  		
    	}
    	if(priceAsc) {
    		sortResultList = productService.sortByPriceAsc(productList);
    		categoryList = categoryService.getAllCate();
    		if(searchTxt.getText().equals("") && categorySearchCbB.getSelectedItem().toString().equals("Tất cả")) {
    			for(Product product : sortResultList) {
    	            for (Category category : categoryList){
    	                if (category.getId() == product.getCategoryId()){
    	                    categoryName = category.getName();
    	                }
    	            }
    	            detailTableModel.addRow(new Object[] {
    	                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
    	                    product.getPrice(), categoryName
    	            });
    	        }
    		}  		
    	}
    	if(priceDes) {
    		sortResultList = productService.sortByPriceDes(productList);
    		categoryList = categoryService.getAllCate();
    		if(searchTxt.getText().equals("") && categorySearchCbB.getSelectedItem().toString().equals("Tất cả")) {
    			for(Product product : sortResultList) {
    	            for (Category category : categoryList){
    	                if (category.getId() == product.getCategoryId()){
    	                    categoryName = category.getName();
    	                }
    	            }
    	            detailTableModel.addRow(new Object[] {
    	                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
    	                    product.getPrice(), categoryName
    	            });
    	        }
    		}   
    	}
    	if(qualityAsc) {
    		sortResultList = productService.sortByQualityAsc(productList);
    		categoryList = categoryService.getAllCate();
    		if(searchTxt.getText().equals("") && categorySearchCbB.getSelectedItem().toString().equals("Tất cả")) {
    			for(Product product : sortResultList) {
    	            for (Category category : categoryList){
    	                if (category.getId() == product.getCategoryId()){
    	                    categoryName = category.getName();
    	                }
    	            }
    	            detailTableModel.addRow(new Object[] {
    	                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
    	                    product.getPrice(), categoryName
    	            });
    	        }
    		}   
    	}
    	if(qualityDes) {
    		sortResultList = productService.sortByQualityDes(productList);
    		categoryList = categoryService.getAllCate();
    		if(searchTxt.getText().equals("") && categorySearchCbB.getSelectedItem().toString().equals("Tất cả")) {
    			for(Product product : sortResultList) {
    	            for (Category category : categoryList){
    	                if (category.getId() == product.getCategoryId()){
    	                    categoryName = category.getName();
    	                }
    	            }
    	            detailTableModel.addRow(new Object[] {
    	                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
    	                    product.getPrice(), categoryName
    	            });
    	        }
    		}  
    	}
		return sortResultList;
    }
    
    private void showSearchResultByName(String name, boolean none, boolean name2, boolean id, boolean 
    		priceAsc, boolean priceDes, boolean qualityAsc, boolean qualityDes ) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
    	String categoryName = "";
        List<Product> searchResultList = productService.searchByName(name, showSortTable(none, name2, id, priceAsc,
        		priceDes, qualityAsc, qualityDes));
        categoryList = categoryService.getAllCate();
        for(Product product : searchResultList) {
            for (Category category : categoryList){
                if (category.getId() == product.getCategoryId()){
                    categoryName = category.getName();
                }
            }
            detailTableModel.addRow(new Object[] {
                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
                    product.getPrice(), categoryName
            });
        }        
    }
    
    private void showSearchResultByid(String id, boolean none, boolean name, boolean id2, boolean 
    		priceAsc, boolean priceDes, boolean qualityAsc, boolean qualityDes ) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
    	String categoryName = "";
    	categoryList = categoryService.getAllCate();
        List<Product> searchResultList = productService.searchById(id, showSortTable(none, name, id2, priceAsc,
        		priceDes, qualityAsc, qualityDes));
        for(Product product : searchResultList) {
            for (Category category : categoryList){
                if (category.getId() == product.getCategoryId()){
                    categoryName = category.getName();
                }
            }
            detailTableModel.addRow(new Object[] {
                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
                    product.getPrice(), categoryName
            });
        }
        
    }
    
    private void showSearchResultByQuality(String quality, boolean none, boolean name, boolean id2, boolean 
    		priceAsc, boolean priceDes, boolean qualityAsc, boolean qualityDes ) {   	
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
    	String categoryName = "";
    	categoryList = categoryService.getAllCate();
        List<Product> searchResultList = productService.searchByQuality(quality, showSortTable(none, name, id2, priceAsc,
        		priceDes, qualityAsc, qualityDes));
        for(Product product : searchResultList) {
            for (Category category : categoryList){
                if (category.getId() == product.getCategoryId()){
                    categoryName = category.getName();
                }
            }
            detailTableModel.addRow(new Object[] {
                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
                    product.getPrice(), categoryName
            });
        }
        
    }
    private void showSearchResultByCategory(String categorySearch, boolean none, boolean name, boolean id2, boolean 
    		priceAsc, boolean priceDes, boolean qualityAsc, boolean qualityDes ) {
    	while (detailTableModel.getRowCount() != 0){
            detailTableModel.removeRow(0);
        }
    	String categoryName = "";
    	categoryList = categoryService.getAllCate();
        List<Product> searchResultList = productService.searchByCategory(categorySearch, showSortTable(none, name, id2,
        		priceAsc, priceDes, qualityAsc, qualityDes));
        for(Product product : searchResultList) {
            for (Category category : categoryList){
                if (category.getId() == product.getCategoryId()){
                    categoryName = category.getName();
                    break;
                }
            }
            detailTableModel.addRow(new Object[] {
                    product.getId(), product.getName(), product.getUnit(), product.getAmount(),
                    product.getPrice(), categoryName
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public String chuanHoa(String message) {
    	message = message.toLowerCase();
	    char[] charArray = message.toCharArray();
	    boolean foundSpace = true;
	    for(int i = 0; i < charArray.length; i++) {
	      if(Character.isLetter(charArray[i])) {   
	        if(foundSpace) {	          
	          charArray[i] = Character.toUpperCase(charArray[i]);
	          foundSpace = false;
	        }
	      }
	      else {
	        foundSpace = true;
	      }
	    }
	    message = String.valueOf(charArray);
	    return message;
    }
}
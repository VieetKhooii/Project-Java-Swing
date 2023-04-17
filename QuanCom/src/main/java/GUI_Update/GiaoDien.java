package main.java.GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GiaoDien extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TitlePanel title;
    private JPanel leftMenu;
    private JPanel mainContent;
    static CardLayout cardLayout;
    static JPanel switchPanel;
	static JPanel functionPanel1;
    static JPanel functionPanel2;
    static JPanel functionPanel3;
    static JPanel functionPanel4;
    static JPanel functionPanel5;
    static JPanel functionPanel6;
    static JPanel functionPanel7;
    static OrdersGUI hoaDon;
    static DetailOrdersGUI taoDon;
    static ReceivingGUI phieuNhap;
    static DetailReceivingGUI taoPhieu;
    static SupplierGUI supplier;
    static StaffsGUI staffs;
    static AccountAndRoleGUI accounts;
    static MaterialGUI material;
    static ProductAndRecipeGUI product;
    public GiaoDien() {
    	try {
    		FlatSolarizedLightIJTheme.setup();
    	} catch( Exception ex ) {
    	    System.err.println( "Failed to initialize LaF" );
    	}
        init();
        this.setVisible(true);
    }
    
    public void init() {
        this.setSize(1280, 732);
        this.setLocationRelativeTo(null);
		this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Giao diện bán hàng");

        //Tieu de
		title = new TitlePanel(this);
        title.setBackground(Color.darkGray);
        title.setPreferredSize(new Dimension(1280,32));
        title.setOpaque(true);
        //---------------------------------------------------------------


        //Menu ben trai + logo
        
    
        //Menu ben trai
        leftMenu = new LeftMenu();
        leftMenu.setPreferredSize(new Dimension(200, 700));
        
		//---------------------------------------------------------------
        mainContent = new JPanel(new BorderLayout());
        mainContent.setPreferredSize(new Dimension(1080, 700));
        
        
        cardLayout = new CardLayout();
        switchPanel = new JPanel(cardLayout);
        switchPanel.setPreferredSize(new Dimension(1080, 700));
        mainContent.add(switchPanel);
        //Tạo đơn
        functionPanel1 = new JPanel(null);
        functionPanel1.setPreferredSize(new Dimension(1080, 700));    
        hoaDon = new OrdersGUI();
        hoaDon.setBounds(0, 0, 1080, 700);
        functionPanel1.add(hoaDon);       
        taoDon = new DetailOrdersGUI();
        taoDon.setBounds(0, 0, 1080, 700);
        functionPanel1.add(taoDon);
        taoDon.setVisible(false);
        switchPanel.add(functionPanel1, "func1");
        
        //Tạo phiếu nhập
        functionPanel2 = new JPanel(null);
        functionPanel2.setPreferredSize(new Dimension(1080, 700));
        phieuNhap = new ReceivingGUI();      
        phieuNhap.setBounds(0, 0, 1080, 700);
        functionPanel2.add(phieuNhap);     
        taoPhieu = new DetailReceivingGUI();
        taoPhieu.setBounds(0, 0, 1080, 700);
        functionPanel2.add(GiaoDien.taoPhieu);
        taoPhieu.setVisible(false);   
        switchPanel.add(functionPanel2, "func2");   
        
        
        //món ăn và công thức
        functionPanel3 = new JPanel(null);
        functionPanel3.setPreferredSize(new Dimension(1080, 700));	
        product = new ProductAndRecipeGUI();
        product.setBounds(0, 0, 1080, 700);
        functionPanel3.add(product);
        switchPanel.add(functionPanel3, "func3");
        
        //chức năng 4
        functionPanel4 = new JPanel(null);
        functionPanel4.setPreferredSize(new Dimension(1080, 700));
        material = new MaterialGUI();
        material.setBounds(0, 0, 1080, 700);
        functionPanel4.add(material);
        switchPanel.add(functionPanel4, "func4");
        
        //Tài khoản và phân quyền
        functionPanel5 = new JPanel(null);
        functionPanel5.setPreferredSize(new Dimension(1080, 700));
        accounts = new AccountAndRoleGUI();
        accounts.setBounds(0, 0, 1080, 700);
        functionPanel5.add(accounts);
        switchPanel.add(functionPanel5, "func5");
        
        //Nhân viên
        functionPanel6 = new JPanel(null);
        functionPanel6.setPreferredSize(new Dimension(1080, 700));
        staffs = new StaffsGUI();
        staffs.setBounds(0, 0, 1080, 700);
        functionPanel6.add(staffs);
        switchPanel.add(functionPanel6, "func6");
        
        //Nhà cung cấp
        functionPanel7 = new JPanel(null);
        functionPanel7.setPreferredSize(new Dimension(1080, 700));
        supplier = new SupplierGUI();
        supplier.setBounds(0, 0, 1080, 700);
        functionPanel7.add(supplier);
        switchPanel.add(functionPanel7, "func7");
        
        
        //---------------------------------------------------------------








        this.setLayout(new BorderLayout());
        this.add(title,BorderLayout.NORTH);
        this.add(leftMenu, BorderLayout.WEST);
        this.add(mainContent, BorderLayout.CENTER);
        
        
    }

    
    public static void main(String[] args) {
    	try {
    		FlatMacLightLaf.setup();
    	} catch( Exception ex ) {
    	    System.err.println( "Failed to initialize LaF" );
    	}
		JFrame a = new GiaoDien();		
	}
}

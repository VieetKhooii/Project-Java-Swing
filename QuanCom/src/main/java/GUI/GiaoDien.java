package main.java.GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;



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
	JPanel functionPanel1;
    JPanel functionPanel2;
    JPanel functionPanel3;
    JPanel functionPanel4;
    JPanel functionPanel5;
    JPanel functionPanel6;
    JPanel functionPanel7;
    JPanel functionPanel8;
    
    public GiaoDien() {
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
        //chức năng 1
        functionPanel1 = new JPanel(null);
        functionPanel1.setPreferredSize(new Dimension(1080, 700));
        switchPanel.add(functionPanel1, "func1");
        DetailOrdersGUI taoDon = new DetailOrdersGUI();
        taoDon.setBounds(0, 0, 1080, 700);
        functionPanel1.add(taoDon);
        //Chức năng 2
        functionPanel2 = new JPanel(null);
        functionPanel2.setPreferredSize(new Dimension(1080, 700));
        DetailReceivingGUI taoPhieu = new DetailReceivingGUI();
        taoPhieu.setBounds(0, 0, 1080, 700);
        functionPanel2.add(taoPhieu);
        switchPanel.add(functionPanel2, "func2");   
        //chức năng 3
        functionPanel3 = new JPanel(null);
        functionPanel3.setPreferredSize(new Dimension(1080, 700));
        switchPanel.add(functionPanel3, "func3");
        //chức năng 4
        functionPanel4 = new JPanel(null);
        switchPanel.add(functionPanel4, "fun4");
        //chức năng 5
        functionPanel5 = new JPanel(null);
        switchPanel.add(functionPanel5, "func5");
        //chức năng 6
        functionPanel6 = new JPanel(null);
        switchPanel.add(functionPanel6, "func6");
        //chức năng 7
        functionPanel7 = new JPanel(null);
        functionPanel7.setPreferredSize(new Dimension(1080, 700));
        StaffsGUI staffs = new StaffsGUI();
        staffs.setBounds(0, 0, 1080, 700);
        functionPanel7.add(staffs);
        switchPanel.add(functionPanel7, "func7");
        //chức năng 8
        functionPanel8 = new JPanel(null);
        functionPanel8.setPreferredSize(new Dimension(1080, 700));
        SupplierGUI supplier = new SupplierGUI();
        supplier.setBounds(0, 0, 1080, 700);
        functionPanel8.add(supplier);
        switchPanel.add(functionPanel8, "func8");
        
        
        //---------------------------------------------------------------








        this.setLayout(new BorderLayout());
        this.add(title,BorderLayout.NORTH);
        this.add(leftMenu, BorderLayout.WEST);
        this.add(mainContent, BorderLayout.CENTER);
        
        
    }

    
    public static void main(String[] args) {
    	try {
    		FlatSolarizedLightIJTheme.setup();
    	} catch( Exception ex ) {
    	    System.err.println( "Failed to initialize LaF" );
    	}
		JFrame a = new GiaoDien();		
	}
}

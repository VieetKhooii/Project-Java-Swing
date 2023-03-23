package view;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.mouseListen_titlebar;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;



public class GiaoDien extends JFrame {
    
    private TitlePanel title;

    public GiaoDien() {
        init();
        this.setVisible(true);
    }

    public void init() {
        this.setSize(1280, 800);
        this.setLocationRelativeTo(null);
		this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Giao diện bán hàng");

        //Tieu de
		    title = new TitlePanel(this);
            title.setBackground(Color.darkGray);
            mouseListen_titlebar mouse_title = new mouseListen_titlebar(this);
            title.addMouseListener(mouse_title);

        //---------------------------------------------------------------


        //Menu ben trai + logo
            //logo
            JPanel Logo = new JPanel();
            JLabel jLabel_logo = new JLabel();
            //URL neww = LeftMenu.class.getResource("Logo2.png");
            //Image image = Toolkit.getDefaultToolkit().createImage(neww);
            jLabel_logo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(LeftMenu.class.getResource("Logo1.png"))));
            Logo.add(jLabel_logo);
            Logo.setBounds(0, 0, 220, 200);
        
            //Menu ben trai
            JPanel leftMenu = new LeftMenu();
            leftMenu.add(Logo);
		//---------------------------------------------------------------
       





        //Noi dung
            JPanel content = new JPanel();
            content.setLayout(new BorderLayout());



            //thanh tim kiem va icon user
            JPanel searchPanel = new JPanel();
            searchPanel.setLayout(null);
            JTextField searchField = new JTextField(); 

        //---------------------------------------------------------------








        this.setLayout(new BorderLayout());
        this.add(title,BorderLayout.NORTH);
        this.add(leftMenu, BorderLayout.WEST);
    }


    public void moveFrame (){
        
    }


}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.mouseListen_titlebar;

public class TitlePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	// private int mousePosX;
    // private int mousePosY;
    private JFrame frame;
	public TitlePanel(JFrame frame) {
		
		this.frame = frame;
		init();
	}
	private void init() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1280, 32));
		this.setBackground(Color.LIGHT_GRAY);

        



		//Minimize btn
            JButton minimizeButton = new JButton("");
            minimizeButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TitlePanel.class.getResource("minimizeIcon.png"))));
            minimizeButton.setBounds(1190, 0, 45, 32);
            minimizeButton.setFont(new Font("Arial", Font.BOLD, 13));
            minimizeButton.setFocusPainted(false);
            minimizeButton.setBorder(null);
            minimizeButton.setBackground(Color.LIGHT_GRAY);
            minimizeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setState(JFrame.ICONIFIED); // Thu nhỏ cửa sổ
                }
            });
            this.add(minimizeButton);
        //--------------------------------------------------------------------------


        //Close btn
            JButton closeButton = new JButton("");
            closeButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TitlePanel.class.getResource("closeIcon.png"))));
            closeButton.setBounds(1235, 0, 45, 32);
            closeButton.setFont(new Font("Arial", Font.BOLD, 13));
            closeButton.setFocusPainted(false);
            closeButton.setBorder(null);
            closeButton.setBackground(Color.RED);
            this.add(closeButton);
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0); // Đóng ứng dụng
                }
            });
        //---------------------------------------------------------------------------



		// Title
            JLabel title = new JLabel("Quán cơm online");
            title.setForeground(Color.WHITE);
            title.setBounds(20, 0, 250, 32);
            title.setFont(new Font("Arial", Font.BOLD, 14));
            title.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TitlePanel.class.getResource("Logo.png"))));
            this.add(title);
        //---------------------------------------------------------------------------



        //Di chuyển window bằng chuột sau khi loại bỏ thanh tiêu đề   
            // this.addMouseListener(new MouseAdapter() {
            //     public void mousePressed(MouseEvent e) {
            //         mousePosX = e.getX();
            //         mousePosY = e.getY();
            //     }
            // });
            // this.addMouseMotionListener(new MouseAdapter() {
            //     public void mouseDragged(MouseEvent e) {
            //         int x = e.getXOnScreen() - mousePosX;
            //         int y = e.getYOnScreen() - mousePosY;
            //         frame.setLocation(x, y);      
            //     }
            // });
        //----------------------------------------------------------------------------

        
        
        

	}

}
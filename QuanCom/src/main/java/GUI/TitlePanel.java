package main.java.GUI;

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


public class TitlePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private int mousePosX;
    private int mousePosY;
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
        minimizeButton.setIcon(new ImageIcon("ImagesIcon/minimizeIcon.png"));
        minimizeButton.setBounds(1190, 0, 45, 32);
        minimizeButton.setFocusPainted(false);
        //minimizeButton.setFont(new Font("Arial", Font.BOLD, 13));
        minimizeButton.setBorder(null);
        minimizeButton.setBackground(Color.GRAY);
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
        closeButton.setName("exit");
        closeButton.setIcon(new ImageIcon("ImagesIcon/closeIcon.png"));
        closeButton.setBounds(1235, 0, 45, 32);
        closeButton.setFocusPainted(false);
        //closeButton.setFont(new Font("Arial", Font.BOLD, 13));
        closeButton.setBorder(null);
        closeButton.setBackground(Color.GRAY);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Đóng ứng dụng
            }
        }
        );
        this.add(closeButton);    
        //---------------------------------------------------------------------------

        if(frame.getWidth() < 1280) {
			this.setPreferredSize(new Dimension(400, 32));
			minimizeButton.setBounds(310, 0, 45, 32);
			closeButton.setBounds(355, 0, 45, 32);
		}
		else {
			this.setPreferredSize(new Dimension(1280, 32));
			minimizeButton.setBounds(1190, 0, 45, 32);
			closeButton.setBounds(1235, 0, 45, 32);
		}		

		// Title
        JLabel title = new JLabel("Quán cơm online");
        title.setForeground(Color.WHITE);
        title.setBounds(5, 0, 250, 32);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setIcon(new ImageIcon("ImagesIcon/Logo.png"));
        this.add(title);
        //---------------------------------------------------------------------------



        //Di chuyển window bằng chuột sau khi loại bỏ thanh tiêu đề   
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePosX = e.getX();
                mousePosY = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mousePosX;
                int y = e.getYOnScreen() - mousePosY;
                frame.setLocation(x, y);      
            }
        });    
        //----------------------------------------------------------------------------

        
        
        

	}

}
package com.magaki.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mousePosX;
    private int mousePosY;
    private JFrame frame;
	public TitlePanel(JFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		init();
	}
	private void init() {
		this.setLayout(null);
		
		//Minimize btn
		JButton minimizeButton = new JButton("—");     
        minimizeButton.setFont(new Font("Arial", Font.BOLD, 11));
        minimizeButton.setFocusPainted(false);
        minimizeButton.setBorder(null);
        minimizeButton.setBackground(Color.LIGHT_GRAY);
        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setState(JFrame.ICONIFIED); // Thu nhỏ cửa sổ
            }
        });
        minimizeButton.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		minimizeButton.setBackground(new Color(224, 224, 224));
			}
			public void mouseExited(MouseEvent e) {
				minimizeButton.setBackground(Color.LIGHT_GRAY); 
			}
        });
        this.add(minimizeButton);
        //Close btn
        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Arial", Font.BOLD, 11));
        closeButton.setFocusPainted(false);
        closeButton.setBorder(null);
        closeButton.setBackground(Color.LIGHT_GRAY);
        this.add(closeButton);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Đóng ứng dụng
            }
        });
        closeButton.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		closeButton.setBackground(Color.red);
        		closeButton.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				closeButton.setBackground(Color.LIGHT_GRAY); 
				closeButton.setForeground(null);
			}
        });
        //
        if(frame.getWidth() < 1280) {
			this.setPreferredSize(new Dimension(400, 20));
			minimizeButton.setBounds(310, 0, 45, 20);
			closeButton.setBounds(355, 0, 45, 20);
		}
		else {
			this.setPreferredSize(new Dimension(1280, 20));
			minimizeButton.setBounds(1190, 0, 45, 20);
			closeButton.setBounds(1235, 0, 45, 20);
		}		
		this.setBackground(Color.LIGHT_GRAY);
		// Title
        JLabel title = new JLabel("Quán cơm online");
        title.setBounds(10, 0, 120, 20);
        title.setFont(new Font("Arial", Font.BOLD, 13));
        this.add(title);
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
	}
	public static void main(String[] args) {
		new Login();
	}
}
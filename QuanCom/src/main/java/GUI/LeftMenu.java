package main.java.GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LeftMenu extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 1L;
	public JButton[] funcBtn = new JButton[7];
	public String [] BtnText = {"Đơn hàng", "Nhập hàng", "Món ăn", "Nguyên liệu", "Tài khoản", "Nhân viên", "Nhà cung cấp"};
	int x = 0, y = 245;
	int w = 200, h = 65;
	public LeftMenu() {
		
		init();
	}
	private void init() {
		this.setPreferredSize(new Dimension(200, 700));
		this.setLayout(null);
		
		
		//Logo
		
		//food_action action = new food_action(this);

		for(int i = 0; i < BtnText.length; i++) {
			funcBtn[i] = new JButton(BtnText[i]);
			funcBtn[i].setBounds(x, y, w, h);
			funcBtn[i].setOpaque(true);
			funcBtn[i].setFocusPainted(false);
			funcBtn[i].setBorder(null);
			funcBtn[i].setFont(new Font("Arial", Font.BOLD, 15));
			funcBtn[i].addActionListener(this);
			this.add(funcBtn[i]);
			y = y + h;
		}
				
	}
	public void mouseEntered(){

//		for(int i = 0; i < funcBtn.length; i++){
//			funcBtn[i].setBackground(Color.orange);
//		}
		
	}
	
	public void mouseExited(){
		//for(int i = 0 ; i < funcBtn.length; i++){
			//funcBtn[i].setBackground(Color.DARK_GRAY);
		//}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < funcBtn.length; i++) {
			funcBtn[i].setEnabled(true);
		}
		for(int i = 0; i < funcBtn.length; i++) {
			if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Đơn hàng")) {		
				GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func1");
				funcBtn[i].setEnabled(false);
			}
			else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Nhập hàng")) {		
				GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func2");	
				funcBtn[i].setEnabled(false);
			}
			else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Tài khoản")) {		
				GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func5");	
				funcBtn[i].setEnabled(false);
			}
			else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Nhân viên")) {		
				GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func6");	
				funcBtn[i].setEnabled(false);
			}
			else if(e.getSource() == funcBtn[i] && funcBtn[i].getText().equals("Nhà cung cấp")) {		
				GiaoDien.cardLayout.show(GiaoDien.switchPanel, "func7");	
				funcBtn[i].setEnabled(false);
			}
		}
	}
	

		
		
		

}

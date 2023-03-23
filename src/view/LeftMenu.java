package view;
import javax.swing.*;
import java.awt.*;


public class LeftMenu extends JPanel{

	//private static final long serialVersionUID = 1L;
	JButton[] funcBtn = new JButton[8];
	String [] BtnText = {"Functionality1", "Functionality2", "Functionality3", "Functionality4", "Functionality5"
			, "Functionality6", "Functionality7", "Functionality8"};
	int x = 0, y = 200;
	int w = 220, h = 65;
	public LeftMenu() {
		
		init();
	}
	private void init() {
		this.setPreferredSize(new Dimension(220, 600));
		this.setLayout(null);
		this.setBackground(Color.ORANGE);
		for(int i = 0; i < BtnText.length; i++) {
			funcBtn[i] = new JButton(BtnText[i]);
			funcBtn[i].setBounds(x, y, w, h);
			funcBtn[i].setBackground(new Color(42, 97, 129));
			funcBtn[i].setForeground(Color.white);
			funcBtn[i].setFocusPainted(false);
			funcBtn[i].setBorder(null);
			funcBtn[i].setFont(new Font("Arial", Font.PLAIN, 13));
			this.add(funcBtn[i]);
			y = y + h;
		}
		
	}
}

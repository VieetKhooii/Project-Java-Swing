package main.java.GUI;

import java.awt.*;
import javax.swing.*;

import javax.swing.JPanel;

public class AccountAndRoleGUI extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static AccountGUI accountPanel;
	static RolesGUI rolePanel;	
	/**
	 * Create the panel.
	 */
	public AccountAndRoleGUI() {
		init();		
	}
	public void init() {
		
		setSize(new Dimension(1080, 700));
		//tab tài khoản
		accountPanel = new AccountGUI();
		accountPanel.setPreferredSize(new Dimension(1080, 700));
		this.addTab("Tài khoản", accountPanel);
		
		//tab phân quyền
		rolePanel = new RolesGUI();
		rolePanel.setPreferredSize(new Dimension(1080, 700));
		this.addTab("Phân quyền", rolePanel);
		
	}
}

package main.java.GUI;

import javax.swing.*;
import java.awt.*;

public class ProductAndRecipeGUI extends JTabbedPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ProductGUI product;
	public ProductAndRecipeGUI() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init() {
		setSize(new Dimension(1080, 700));
		
		//tab món ăn
		product = new ProductGUI();
		product.setPreferredSize(new Dimension(1080, 700));
		this.add("Món ăn", product);
		//tab công thức
	}
	
	
}

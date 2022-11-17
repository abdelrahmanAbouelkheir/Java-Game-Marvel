package engine;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class ViewCover extends JFrame {
	public ViewCover(String s){	
		this.setBounds(500, 500, 300, 200);
		this.setLayout(new GridLayout(1,1));
		JTextField t = new JTextField(s);
		t.setFont(new Font("Serif", Font.BOLD,20));
		t.setEditable(false);
		this.add(t);
		this.setVisible(true);
	}
}

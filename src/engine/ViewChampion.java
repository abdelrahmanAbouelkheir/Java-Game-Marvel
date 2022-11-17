package engine;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.world.Champion;
import model.world.Hero;

public class ViewChampion extends JFrame{
	public ViewChampion(String s,ImageIcon icon){
		this.setBounds(0, 0, 1000, 1000);
		setVisible(true);
		setLayout(null);
		JTextArea text= new JTextArea();
		text.setBounds(0, 0,300 , 1000);
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(this.getWidth(),this.getHeight(), java.awt.Image.SCALE_SMOOTH);
	    this.setContentPane(new JLabel(new ImageIcon(newimg)));
		text.setFont(new Font("Serif", Font.BOLD,15));
		text.setText(s);
		text.setEditable(false);
		this.add(text);
		this.revalidate();
		this.repaint();
	}
}

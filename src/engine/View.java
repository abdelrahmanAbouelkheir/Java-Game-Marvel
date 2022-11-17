package engine;
import java.awt.Color;
import java.io.File;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;









import javax.swing.*;
import javax.swing.border.Border;
public class View extends JFrame {
	private JTextField input1= new JTextField();
	private JTextField input2= new JTextField();
	private JPanel panel1 = new JPanel();
	//Music se = new Music();
	public JPanel getPanel1() {
		return panel1;
	}

	public JTextField getInput1() {
		return input1;
	}

	public JTextField getInput2() {
		return input2;
	}

	public JTextField getInput3() {
		return input3;
	}
	private JTextField input3= new JTextField();
	public View(){
		
	//	Border blackline = BorderFactory.createLineBorder(Color.black);
	//	panel1.setBorder(blackline);
		input1.setFont(new Font("Serif", Font.BOLD,20));
		input2.setFont(new Font("Serif", Font.BOLD,20));
		this.setResizable(false);
		setBounds(0,0,1000,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		ImageIcon icon = new ImageIcon("back1.jpg");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(this.getWidth(),this.getHeight(), java.awt.Image.SCALE_SMOOTH);
	    this.setContentPane(new JLabel(new ImageIcon(newimg)));
		
		JPanel panel = new JPanel();
		panel.setBounds(0,0,500,100);
		panel.setLayout(new GridLayout(0,2));
		
		JTextField text1= new JTextField("Enter first player name");
		text1.setEditable(false);
		text1.setFont(new Font("Serif", Font.BOLD,20));
		
		JTextField text2= new JTextField("Enter second player name");
		text2.setFont(new Font("Serif", Font.BOLD,20));
		text2.setEditable(false);
		panel.add(text1);panel.add(input1);
		panel.add(text2);panel.add(input2);
		this.add(panel);
		this.add(panel1);
		this.revalidate();
		this.repaint();
		String bip = "Avengers.mp3";
//		this.getContentPane().addHierarchyBoundsListener(
//	            new ResizeComponentsListener(1000, 1000, true));
        //se.setFile("Avengers theme.mp3");
       // se.play();
	}
	
	public static void main(String[] args){
		new View();
	}
}


package engine;

import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.world.Champion;

public class ChooseLeader extends JFrame{
	private JPanel[] champs = new JPanel[3];
	public JPanel[] getChamps() {
		return champs;
	}
	public ChooseLeader(String s){
	
		JOptionPane.showMessageDialog(this,s +" : please select one Leader");
		this.setBounds(500, 500, 800, 200);
		this.setVisible(true);
		this.setLayout(new GridLayout(0, 3));
		for(int i =0;i<3;i++){
			JPanel p =new JPanel();
			this.add(p);
			champs[i]=p;	
		}
		this.revalidate();
		this.repaint();
		
	}
}

package engine;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanel1 extends JPanel{
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage((new ImageIcon("back1.jpg")).getImage(), 0,0,this.getWidth(), this.getHeight(), null);
	}
}


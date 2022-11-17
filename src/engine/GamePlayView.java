package engine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
public class GamePlayView extends JFrame{
//	private GridBagConstraints gbc ;
	private JPanel playeroneRem;
	public JPanel getPlayertwoRem() {
		return playertwoRem;
	}
	public void setPlayeroneRem(JPanel playeroneRem) {
		this.playeroneRem = playeroneRem;
	}
	public JPanel getPlayeroneRem() {
		return playeroneRem;
	}
	private JButton leader;
	private JPanel playertwoRem;
	private JPanel[][] board = new JPanel[5][5];
	private JButton up;
	private JButton down;
	private JButton right;
	private JButton left;
	private JToggleButton attack;
	private JToggleButton move;
	private JPanel abilities;
//	private JToggleButton ab1;
//	private JToggleButton ab2;
//	private JToggleButton ab3;
	private JButton endturn;
	private JTextArea text;
	private JPanel turn;
	public JPanel getCont() {
		return cont;
	}
	private JPanel cont;
	public JPanel getTurn() {
		return turn;
	}
	public JButton getUp() {
		return up;
	}
	public JButton getDown() {
		return down;
	}
	public JButton getRight() {
		return right;
	}
	public JButton getLeft() {
		return left;
	}
	public JToggleButton getAttack() {
		return attack;
	}
	public JToggleButton getMove() {
		return move;
	}
//	public JToggleButton getAb1() {
//		return ab1;
//	}
//	public JToggleButton getAb2() {
//		return ab2;
//	}
//	public JToggleButton getAb3() {
//		return ab3;
//	}
	public JButton getEndturn() {
		return endturn;
	}
	public JPanel[][] getBoard(){
		return board;
	}
	public JTextArea getText() {
		return text;
	}
	public GamePlayView(String p1n,String p2n){
		this.setLayout(null);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setSize(1500,800);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		this.setLocation(x, y); 	
//		Image img = icon.getImage();
//		Image newimg = img.getScaledInstance(this.getWidth(),this.getHeight(), java.awt.Image.SCALE_SMOOTH);
//	    this.setContentPane(new JLabel(new ImageIcon(newimg)));
//		this.getContentPane().setBackground(new Color(0,0,102));
		turn = new JPanel();
		turn.setBounds(1200, 600, 240, 150);
		turn.setBorder(blackline);
		turn.setLayout(new GridLayout(6,1));
		this.add(turn);
		
		text = new JTextArea();
		text.setEditable(false);
		text.setBounds(0, 0, 250, 800);
		this.add(text);
		
		JPanel direction = new JPanel();
		direction.setBounds(1200,0,200,100);
		
		
		direction.setLayout(new BorderLayout());
		up = new JButton("UP");
		direction.add(up,BorderLayout.NORTH);
		
		down = new JButton("DOWN");
		direction.add(down,BorderLayout.SOUTH);
		
		left = new JButton("LEFT");
		direction.add(left,BorderLayout.WEST);
		
		right = new JButton("RIGHT");
		direction.add(right,BorderLayout.EAST);
//		JTextField fir = new JTextField();
//		fir.setText("Leader Ability Used");
//		fir.setEditable(false);
//		fir.setFont(new Font("Serif", Font.BOLD,20));
//		fir.setBounds(850,600,200,50);
//		JTextField sec = new JTextField();
//		sec.setText("Leader Ability Used");
//		sec.setEditable(false);
//		sec.setFont(new Font("Serif", Font.BOLD,20));
//		sec.setBounds(850, 0, 200, 50);
//		screen.add(fir);
//		screen.add(sec);
		
		this.add(direction);
		attack = new JToggleButton("ATTACK");
		attack.setBounds(1200, 150, 100, 50);
		this.add(attack);
		ImageIcon icon1 = new ImageIcon("attack.jpg");
		Image img1 = icon1.getImage();  
		Image newimg1 = img1.getScaledInstance(attack.getWidth(),attack.getHeight(), java.awt.Image.SCALE_SMOOTH);
		icon1 = new ImageIcon(newimg1);
		attack.setIcon(icon1);
		this.revalidate();
		this.repaint();
		
		move = new JToggleButton("MOVE");
		move.setBounds(1200, 250, 100, 50);
		this.add(move);
		
//		ab1 = new JToggleButton("ab1");
//		ab1.setBounds(800, 350, 100, 50);
//		this.add(ab1);
		leader  = new JButton();
		leader.setBounds(1325,200,150,50);
		leader.setText("Leader Ability");
		this.add(leader);
		 abilities = new JPanel();
		abilities.setLayout(new GridLayout(0,1));
		//abilities.setBorder(blackline);
		abilities.setBounds(1200,350,200,200);
		this.add(abilities);
//		ab2 = new JToggleButton("ab2");
//		ab2.setBounds(800, 450, 100, 50);
//		this.add(ab2);
//		
//		ab3 = new JToggleButton("ab3");
//		ab3.setBounds(800, 550, 100, 50);
//		this.add(ab3);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		endturn = new JButton("END TURN");
		endturn.setBounds(1200, 550, 150, 40);
		this.add(endturn);
		JTextField p1 = new JTextField(p1n+"'s Remaining players are");
		p1.setEditable(false);
		p1.setFont(new Font("Serif", Font.BOLD,20));
		JTextField p2 = new JTextField(p2n+"'s Remaining players are");
		p2.setFont(new Font("Serif", Font.BOLD,20));
		p2.setEditable(false);
		p1.setBounds(250,600,300,50);
		p2.setBounds(250,0,300,50);
		
		 playeroneRem = new JPanel(new GridLayout(3,1));
		playeroneRem.setBounds(550,600,200,100);
		playeroneRem.setBorder(blackline);
		this.add(playeroneRem);
		 playertwoRem = new JPanel(new GridLayout(3,1));
		playertwoRem.setBounds(550, 0, 200, 100);
		playertwoRem.setBorder(blackline);
		this.add(playertwoRem);
		this.add(p1);
		this.add(p2);
		cont = new JPanel();
		cont.setBounds(260,100,940,500);
		cont.setBorder(blackline);
		cont.setLayout(new GridLayout(5,5));
//		ImageIcon icon2 = new ImageIcon("back1.jpg");
//		Image img2 = icon2.getImage();
//		Image newimg2 = img2.getScaledInstance(cont.getWidth(),cont.getHeight(), java.awt.Image.SCALE_SMOOTH);
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				JPanel p  = new JPanel();
				p.setLayout(new GridLayout(1, 1));
				board[i][j]=p;
				cont.add(p);
			}
		}
		this.add(cont);
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		this.setResizable(false);
//		this.getContentPane().addHierarchyBoundsListener(
//	            new ResizeComponentsListener(1000, 800, true));
	}
	public JButton getLeader() {
		return leader;
	}
	public JPanel getAbilities() {
		return abilities;
	}
	public static void main(String[] args){
		new GamePlayView("ahmed","Mohamed");
	}
}

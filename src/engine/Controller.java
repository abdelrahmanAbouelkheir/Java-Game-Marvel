package engine;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

import exceptions.GameActionException;
import model.abilities.*;
import model.effects.Effect;
import model.world.*;
public class Controller implements ActionListener {
	private View view1;
	private Game model;
	private GamePlayView screen;
	
	public Controller(){
		final JFXPanel fxPanel = new JFXPanel();
		Media hit = new Media(new File("Avengers.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		view1 = new View();
		mediaPlayer.play();
		JButton b = new JButton("submit names");
		b.setBounds(0,100,200,50);
		view1.add(b);
		view1.revalidate();
		view1.repaint();
		b.addActionListener(e -> {if(!view1.getInput1().getText().equals("")&&!view1.getInput2().getText().equals("")){
									b.setEnabled(false);									
									initializeGame();
									viewChampions();
									mediaPlayer.play();
								  }
								  else{
									  JOptionPane.showMessageDialog(view1, "Please enter player names");
								  }
								 }
		);
		
		
	}
	public void initializeGame(){
		Player player1 = new Player(view1.getInput1().getText());
		Player player2 = new Player(view1.getInput2().getText());
		model = new Game(player1,player2);
		try{
			model.loadAbilities("Abilities.csv");
			model.loadChampions("Champions.csv");
			}catch(IOException e){
				
			}
	}
	public void viewChampions(){
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 250,900 ,650 );
		panel1.setLayout(new GridLayout(0,3));
		JButton[] list  = new JButton[25];
		JButton[] list2 = new JButton[25];
		int k=0;
		Border blackline = BorderFactory.createLineBorder(Color.black);
		JOptionPane.showMessageDialog(view1, model.getFirstPlayer().getName()+" : please choose three champions");
		for(Champion c : Game.getAvailableChampions()){
			JPanel p = new JPanel();
			p.setBorder(blackline);
			p.setLayout(new GridLayout(0,2));
			JButton button2 = new JButton();
			Border emptyBorder = BorderFactory.createEmptyBorder();
			button2.setBorder(emptyBorder);
			list2[k] = button2;
			button2.addActionListener(y ->{
				new ViewChampion((getString((Champion)c)),getImageByName(((Champion)c).getName()));
			});
			p.add(button2);
			
			JButton button1 = new JButton();
			list[k]=button1;
			k++;
			button1.setText(c.getName());
			button1.setBorder(emptyBorder);
			button1.addActionListener(i ->{ 
				panel1.remove(p);
				view1.revalidate();
				view1.repaint();
				putSelectedChampionInSuitableTeam(c);
				
			});
			p.add(button1);
			panel1.add(p);
		}
		view1.add(panel1);
		view1.revalidate();
		view1.repaint();
		try{
		for(JButton b : list){
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			ImageIcon icon = getImageByName(b.getText());
			Image img = icon.getImage();  
			Image newimg = img.getScaledInstance(b.getWidth(),b.getHeight(), java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			b.setIcon(icon);
		}}catch(NullPointerException fail){
			
		}
		try{
		for(JButton button2 : list2){
			button2.setOpaque(false);
			button2.setContentAreaFilled(false);
			button2.setBorderPainted(false);
			ImageIcon icon4 = new ImageIcon("lll.jpg");
			Image img4 = icon4.getImage();  
			Image newimg4 = img4.getScaledInstance(button2.getWidth(),button2.getHeight(), java.awt.Image.SCALE_SMOOTH);
			icon4 = new ImageIcon(newimg4);
			button2.setIcon(icon4);
			button2.setText("View Details");
		}}catch(NullPointerException fail){
			
		}
		
		view1.revalidate();
		view1.repaint();
		
	}
	public String getString(Champion c){
		String ab="";
		for(Ability a : c.getAbilities()){
			ab+="Ability Name : "+a.getName()+"\n	Base Cooldown : "+a.getBaseCooldown()+"\n	Current Cooldown : "+a.getCurrentCooldown()+"\n	Cast Range : "+a.getCastRange()+"\n	Mana Cost : "
		+a.getManaCost()+"\n	Required Action Points : "+a.getRequiredActionPoints()+"\n	Cast Area : "+a.getCastArea().toString()+"\n	";
			if(a instanceof DamagingAbility){
				ab+="Damage Amount : "+((DamagingAbility)a).getDamageAmount();
			}
			else if(a instanceof HealingAbility){
				ab+="Heal Amount : "+((HealingAbility)a).getHealAmount();
			}
			else{
				ab+="Effect Name : " + ((CrowdControlAbility)a).getEffect().getName()+"\n	Effect Duration : "+((CrowdControlAbility)a).getEffect().getDuration();
			}
			ab+="\n";
		}
		String ef="";
		for(Effect a : c.getAppliedEffects()){
			ef+="Effect Name : "+a.getName()+"\nEffect Duration : "+a.getDuration();
			ef+="\n";
		}
		return "Name : "+c.getName()+"\nType : "+c.getClass().getSimpleName()+"\nCondition : "+c.getCondition().toString()+"\nAttack Damage : "+c.getAttackDamage()+"\nAttack Range : "
				+c.getAttackRange()+"\nMana : "+c.getMana()+"\nCurrent Action Points : "+c.getCurrentActionPoints()+"\nMax Action Points Per Turn : "+c.getMaxActionPointsPerTurn()+"\nCurrent Health Points : "+c.getCurrentHP()+"\nMax Health Points : "+c.getMaxHP()+"\nSpeed : "+
				c.getSpeed()+"\n"+ab+ef;
	}
	
	public void putSelectedChampionInSuitableTeam(Champion c){
		if(model.getFirstPlayer().getTeam().size()<3){
			model.getFirstPlayer().getTeam().add(c);
			if(model.getFirstPlayer().getTeam().size()==3){
			//	JOptionPane.showMessageDialog(view1, model.getFirstPlayer().getName()+" : please select one Leader");
			//	new ChooseLeader(model.getFirstPlayer());
				JOptionPane.showMessageDialog(view1, model.getSecondPlayer().getName()+" : please choose three champions");
			}
		}else if(model.getSecondPlayer().getTeam().size()<3){
			model.getSecondPlayer().getTeam().add(c);
			if(model.getSecondPlayer().getTeam().size()==3){
				view1.setVisible(false);
				chooseFirstLeader();
			}
		}
	}
	public void chooseFirstLeader(){
		ChooseLeader choose1 = new ChooseLeader(model.getFirstPlayer().getName());
		int i=0;
		for(Champion c:model.getFirstPlayer().getTeam()){
			JButton b = new JButton();
			ImageIcon icon = getImageByName((c).getName());
			Image img = icon.getImage();  
			Image newimg = img.getScaledInstance(choose1.getChamps()[i].getWidth(),choose1.getChamps()[i].getHeight(), java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			b.setIcon(icon);
			b.addActionListener(e ->{
				model.getFirstPlayer().setLeader(c);
				choose1.setVisible(false);
				chooseSecondLeader();
				
				
			});
			choose1.getChamps()[i].add(b);
			i++;
		}
	}
	public void chooseSecondLeader(){
		ChooseLeader choose2 = new ChooseLeader(model.getSecondPlayer().getName());
		int i=0;
		for(Champion c:model.getSecondPlayer().getTeam()){
			JButton b = new JButton();
			ImageIcon icon = getImageByName((c).getName());
			Image img = icon.getImage();  
			Image newimg = img.getScaledInstance(choose2.getChamps()[i].getWidth(),choose2.getChamps()[i].getHeight(), java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			b.setIcon(icon);
			b.addActionListener(e ->{
				model.getSecondPlayer().setLeader(c);
				choose2.setVisible(false);
				prepareBoard();
				
			});
			choose2.getChamps()[i].add(b);
			i++;
		}
	}
	public void prepareBoard(){
	//	Border blackline = BorderFactory.createLineBorder(Color.black);
//		Border redline = BorderFactory.createLineBorder(Color.red);
//		Border blueline = BorderFactory.createLineBorder(Color.blue);
		model.placeChampions();
		model.prepareChampionTurns();
		screen = new GamePlayView(model.getFirstPlayer().getName(),model.getSecondPlayer().getName());
		UpdateBoard();
		activateEndTurn();
		activateAttack();
		screen.getUp().addActionListener(this);
		screen.getDown().addActionListener(this);
		screen.getLeft().addActionListener(this);
		screen.getRight().addActionListener(this);
		activateMove();
		activateLeader();
		screen.getText().setText(getString(model.getCurrentChampion()));
		putAbilities();
		fillTurn();
		screen.revalidate();
		screen.repaint();
	}
	public void activateLeader(){
		screen.getLeader().addActionListener(e ->{
			try{
				model.useLeaderAbility();
				if(model.getFirstPlayer().getTeam().contains(model.getCurrentChampion())){
					JTextField fir = new JTextField();
					fir.setText("Leader Ability Used");
					fir.setEditable(false);
					fir.setFont(new Font("Serif", Font.BOLD,20));
					fir.setBounds(850,600,200,50);
					screen.add(fir);
				}
				else{
					JTextField sec = new JTextField();
					sec.setText("Leader Ability Used");
					sec.setEditable(false);
					sec.setFont(new Font("Serif", Font.BOLD,20));
					sec.setBounds(850, 0, 200, 50);
					screen.add(sec);
				}
				updateIfDead();
				screen.revalidate();
				screen.repaint();
			}catch(GameActionException fail){
				JOptionPane.showMessageDialog(screen, fail.getMessage());
			}
		});
	}
	public void actionPerformed(ActionEvent e){
		JButton b = (JButton)e.getSource();
		Direction d;
		switch(b.getText()){
			case "UP" : {d=Direction.UP;break;}
			case "DOWN" : {d=Direction.DOWN;break;}
			case "LEFT" :{d=Direction.LEFT;break;}
			default :{d=Direction.RIGHT;break;}
		}
		if(screen.getMove().isSelected()){
			try{
				int xo = 4 - model.getCurrentChampion().getLocation().x;
				int yo = model.getCurrentChampion().getLocation().y;
				model.move(d);
				int xn = 4 - model.getCurrentChampion().getLocation().x;
				int yn = model.getCurrentChampion().getLocation().y;
				updateAfterMove(xo, yo, xn, yn);
				screen.getText().setText(getString(model.getCurrentChampion()));
			}catch(GameActionException fail){
				JOptionPane.showMessageDialog(screen,fail.getMessage());
			}
		}else if(screen.getAttack().isSelected()){
			try{
				model.attack(d);
				updateIfDead();
				screen.getText().setText(getString(model.getCurrentChampion()));
				endGame();
				screen.revalidate();
				screen.repaint();
			}catch(GameActionException fail){
				JOptionPane.showMessageDialog(screen,fail.getMessage());
			}
		}else {
			boolean found=false;
			JToggleButton selectedAbility = new JToggleButton();
			for(int k=0;k<screen.getAbilities().getComponentCount();k++){
				if(((JToggleButton)screen.getAbilities().getComponent(k)).isSelected()){
					selectedAbility = (JToggleButton)screen.getAbilities().getComponent(k);
					found=true;
					break;
				}
			}
			if(found){
				Ability ab = model.getCurrentChampion().getAbilities().get(screen.getAbilities().getComponentZOrder(selectedAbility));
				try{
					if(ab.getCastArea().equals(AreaOfEffect.DIRECTIONAL)){
						model.castAbility(ab,d);
						updateIfDead();
						selectedAbility.setSelected(false);
						screen.getText().setText(getString(model.getCurrentChampion()));
						endGame();
						screen.revalidate();
						screen.repaint();
						
					}
				}catch(GameActionException fail){
					JOptionPane.showMessageDialog(screen,fail.getMessage());
				}catch(CloneNotSupportedException fail){
					
				}
			}
		}
		
	}
	public void activateMove(){
		screen.getMove().addActionListener(e ->{
			if(screen.getMove().isSelected()){
		//		JOptionPane.showMessageDialog(screen, "Please chooose a direction to move");
				screen.getAttack().setSelected(false);
				screen.getUp().setSelected(false);
				screen.getDown().setSelected(false);
				screen.getLeft().setSelected(false);
				screen.getRight().setSelected(false);
				for(int k=0;k<screen.getAbilities().getComponentCount();k++){
					((JToggleButton)screen.getAbilities().getComponent(k)).setSelected(false);
				}
			}
		});
	}
	public void activateAttack(){
		screen.getAttack().addActionListener(e ->{
			if(screen.getAttack().isSelected()){
		//		JOptionPane.showMessageDialog(screen, "Please chooose a direction to preform attack");
				screen.getMove().setSelected(false);
				screen.getUp().setSelected(false);
				screen.getDown().setSelected(false);
				screen.getLeft().setSelected(false);
				screen.getRight().setSelected(false);
				for(int k=0;k<screen.getAbilities().getComponentCount();k++){
					((JToggleButton)screen.getAbilities().getComponent(k)).setSelected(false);
				}
			}
		});
	}
	public void endGame(){
		Player winner =model.checkGameOver();
		if(winner!=null){
			JOptionPane.showMessageDialog(screen,winner.getName()+" WINS");
			screen.getEndturn().setEnabled(false);
			screen.getAttack().setEnabled(false);
			screen.getMove().setEnabled(false);
			screen.getEndturn().setEnabled(false);
			for(int i=0;i<5;i++){
				for(int j=0;j<5;j++){
					if(screen.getBoard()[i][j].getComponentCount()==1){
						screen.getBoard()[i][j].getComponent(0).setEnabled(false);
					}
				}
			}
			for(int i=0;i<screen.getAbilities().getComponentCount();i++){
				screen.getAbilities().getComponent(i).setEnabled(false);
			}
		}
	}
	public void fillTurn(){
			screen.getTurn().removeAll();
			PriorityQueue temp = new PriorityQueue(6);
			while(!model.getTurnOrder().isEmpty()){
				JButton b = new JButton();
				b.setText(model.getCurrentChampion().getName());
				screen.getTurn().add(b);
				temp.insert(model.getTurnOrder().remove());
			}
			while(!temp.isEmpty()){
				model.getTurnOrder().insert(temp.remove());
			}
			screen.revalidate();
			screen.repaint();
		
	}
	public void activateEndTurn(){
		screen.getEndturn().addActionListener(e ->{
			screen.getAttack().setSelected(false);
			screen.getMove().setSelected(false);
			screen.getUp().setSelected(false);
			screen.getDown().setSelected(false);
			screen.getLeft().setSelected(false);
			screen.getRight().setSelected(false);
			for(int k=0;k<screen.getAbilities().getComponentCount();k++){
				((JToggleButton)screen.getAbilities().getComponent(k)).setSelected(false);
			}
			model.endTurn();
			JButton b=(JButton)screen.getTurn().getComponent(0);
			screen.getTurn().remove(b);
			screen.getText().setText(getString(model.getCurrentChampion()));
			putAbilities();
			screen.revalidate();
			screen.repaint();
			fillTurn();
		});
	}
	public void putAbilities(){
		screen.getAbilities().removeAll();
		for(Ability a : model.getCurrentChampion().getAbilities()){
			JToggleButton b = new JToggleButton();
			b.setText(a.getName());
			b.addActionListener(e ->{
				if(b.isSelected()){
					for(int k=0;k<screen.getAbilities().getComponentCount();k++){
						if(!screen.getAbilities().getComponent(k).equals(b))
						((JToggleButton)screen.getAbilities().getComponent(k)).setSelected(false);
					}
					screen.getAttack().setSelected(false);
					screen.getMove().setSelected(false);
					screen.getUp().setSelected(false);
					screen.getDown().setSelected(false);
					screen.getLeft().setSelected(false);
					screen.getRight().setSelected(false);
					Ability ab = model.getCurrentChampion().getAbilities().get(screen.getAbilities().getComponentZOrder(b));
					if(ab.getCastArea()!=AreaOfEffect.DIRECTIONAL&&ab.getCastArea()!=AreaOfEffect.SINGLETARGET){
						b.setSelected(false);
						try{
							model.castAbility(ab);
							updateIfDead();
							screen.revalidate();
							screen.repaint();
							endGame();
							screen.getText().setText(getString(model.getCurrentChampion()));
							screen.revalidate();
							screen.repaint();
						}catch(GameActionException fail){
							JOptionPane.showMessageDialog(screen,fail.getMessage());
						}catch(CloneNotSupportedException fail){
							
						}
					}
					else if (ab.getCastArea()==AreaOfEffect.DIRECTIONAL){
						JOptionPane.showMessageDialog(screen,"Please choose a direction to cast ability");
					}
					else{
						JOptionPane.showMessageDialog(screen,"Please choose a target to cast ability");
					}
				}
			});
			screen.getAbilities().add(b);
		}
		screen.revalidate();
		screen.repaint();
	}
	public void UpdateBoard(){
		screen.getPlayeroneRem().removeAll();
		screen.getPlayertwoRem().removeAll();
		for(int i=0;i<model.getBoardwidth();i++){
			for(int j=0;j<model.getBoardheight();j++){
				screen.getBoard()[4-i][4-j].removeAll();
			}
		}
		screen.revalidate();
		screen.repaint();
		Border redline = BorderFactory.createLineBorder(Color.red);
		Border blueline = BorderFactory.createLineBorder(Color.blue);
		for(int i=0;i<model.getBoardwidth();i++){
			for(int j=0;j<model.getBoardheight();j++){
				if(model.getBoard()[i][j]!=null){
					Damageable d = (Damageable)model.getBoard()[i][j];
					JButton b= new JButton();
					b.setOpaque(false);
					b.setContentAreaFilled(false);
					b.addActionListener(e -> {
						boolean found=false;
						JToggleButton selectedAbility = new JToggleButton();
						for(int k=0;k<screen.getAbilities().getComponentCount();k++){
							if(((JToggleButton)screen.getAbilities().getComponent(k)).isSelected()){
								selectedAbility = (JToggleButton)screen.getAbilities().getComponent(k);
								found=true;
								break;
							}
						}
						if(found){
							Ability ab = model.getCurrentChampion().getAbilities().get(screen.getAbilities().getComponentZOrder(selectedAbility));
							try{
								if(ab.getCastArea().equals(AreaOfEffect.SINGLETARGET)){
									model.castAbility(ab,d.getLocation().x,d.getLocation().y);
									selectedAbility.setSelected(false);
									UpdateBoard();
									screen.getText().setText(getString(model.getCurrentChampion()));
									screen.revalidate();
									screen.repaint();
									endGame();
								}
							}catch(GameActionException fail){
								JOptionPane.showMessageDialog(screen,fail.getMessage());
							}catch(CloneNotSupportedException fail){
								
							}
						}
						
						else if(d instanceof Champion){
							
							new ViewChampion((getString((Champion)d)),getImageByName(((Champion)d).getName()));
						}
						else{	
							new ViewCover("Cover Health Points : "+ ((Cover)d).getCurrentHP());
						}
					});
					if(d instanceof Champion){
//						b.setIcon(new ImageIcon("captain-america-2-scaled.jpg"));
						ImageIcon icon = getImageByName(((Champion)d).getName());
						Image img = icon.getImage();  
						Image newimg = img.getScaledInstance(screen.getBoard()[4-i][j].getWidth(),screen.getBoard()[4-i][j].getHeight(), java.awt.Image.SCALE_SMOOTH);
						icon = new ImageIcon(newimg);
						b.setIcon(icon);
						if(model.getFirstPlayer().getTeam().contains(d)){
							b.setBorder(blueline);
						}else{
							b.setBorder(redline);
						}
					}
					else{
//						b.setIcon(new ImageIcon("captain-america-2-scaled.jpg"));
						ImageIcon icon = new ImageIcon("obs.jpg");
						Image img = icon.getImage();  
						Image newimg = img.getScaledInstance(screen.getBoard()[4-i][j].getWidth(),screen.getBoard()[4-i][j].getHeight(), java.awt.Image.SCALE_SMOOTH);
						icon = new ImageIcon(newimg);
						b.setIcon(icon);
					}
					if(i==0&&(j==1||j==2||j==3)){
						b.setBorder(blueline);
					}
					else if(i==4&&(j==1||j==2||j==3)){
						b.setBorder(redline);
					}
					screen.getBoard()[4-i][j].add(b);
					screen.revalidate();
					screen.repaint();
				}
				else{
				JButton b = new JButton();
					ImageIcon icon = new ImageIcon("emptycell.jpg");
					Image img = icon.getImage();  
					Image newimg = img.getScaledInstance(screen.getBoard()[4-i][j].getWidth(),screen.getBoard()[4-i][j].getHeight(), java.awt.Image.SCALE_SMOOTH);
					icon = new ImageIcon(newimg);
					b.setIcon(icon);
					screen.getBoard()[4-i][j].add(b);
				
				}
			}
		}
		int i=0;
		for(Champion c : model.getFirstPlayer().getTeam()){
			JButton b = new JButton();
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
//			ImageIcon icon = getImageByName(c.getName());
//			Image img = icon.getImage();  
//			Image newimg = img.getScaledInstance(b.getWidth(),b.getHeight(), java.awt.Image.SCALE_SMOOTH);
//			icon = new ImageIcon(newimg);
//			b.setIcon(icon);
			b.setText(c.getName());
			screen.getPlayeroneRem().add(b);
		}
		i=0;
		for(Champion c : model.getSecondPlayer().getTeam()){
			JButton b = new JButton();
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
//			ImageIcon icon = getImageByName(c.getName());
//			Image img = icon.getImage();  
//			Image newimg = img.getScaledInstance(b.getWidth(),b.getHeight(), java.awt.Image.SCALE_SMOOTH);
//			icon = new ImageIcon(newimg);
//			b.setIcon(icon);
			b.setText(c.getName());
			screen.getPlayertwoRem().add(b);
		}
//		
		screen.revalidate();
		screen.repaint();
	}
	public void updateAfterMove(int xo,int yo,int xn,int yn){
		JButton butEmpty=(JButton)screen.getBoard()[xn][yn].getComponent(0);
		JButton butc=(JButton)screen.getBoard()[xo][yo].getComponent(0);
		screen.getBoard()[xn][yn].removeAll();
		screen.getBoard()[xo][yo].removeAll();
		screen.getBoard()[xo][yo].add(butEmpty);
		screen.getBoard()[xn][yn].add(butc);
		screen.revalidate();
		screen.repaint();
	}
	public void updateIfDead(){
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(model.getBoard()[i][j]==null){
					if(!(screen.getBoard()[4-i][j]==null)){
						screen.getBoard()[4-i][j].removeAll();
						JButton b = new JButton();
						ImageIcon icon = new ImageIcon("emptycell.jpg");
						Image img = icon.getImage();  
						Image newimg = img.getScaledInstance(screen.getBoard()[4-i][j].getWidth(),screen.getBoard()[4-i][j].getHeight(), java.awt.Image.SCALE_SMOOTH);
						icon = new ImageIcon(newimg);
						b.setIcon(icon);
						screen.getBoard()[4-i][j].add(b);

					}
				}
			}
		}
		screen.revalidate();
		screen.repaint();
	}
	public ImageIcon getImageByName(String s){
		switch(s){
			case "Captain America": {return new ImageIcon("captain-america-2-scaled.jpg");}
			case "Deadpool": {return new ImageIcon("deadpool10.jpg");}
			case "Dr Strange": {return new ImageIcon("strange2.jpg");}
			case "Electro": {return new ImageIcon("electro2.jpg");}
			case "Ghost Rider": {return new ImageIcon("ghost2.jpg");}
			case "Hela": {return new ImageIcon("heeela.jpg");}
			case "Hulk": {return new ImageIcon("hulk2.jpg");}
			case "Iceman": {return new ImageIcon("iceman2.png");}
			case "Ironman": {return new ImageIcon("ironnn.jpg");}
			case "Loki": {return new ImageIcon("luki2.jpg");}
			case "Quicksilver": {return new ImageIcon("quickss.jpg");}
			case "Spiderman": {return new ImageIcon("spider.jpg");}
			case "Thor": {return new ImageIcon("thor3.jpg");}
			case "Venom": {return new ImageIcon("venom2.jpg");}
			default : {return new ImageIcon("yellowjacket.jpg");}
			
			
			
		}
	}
	public static void main(String[] args){
		 new Controller();
	}
	
	
	
}

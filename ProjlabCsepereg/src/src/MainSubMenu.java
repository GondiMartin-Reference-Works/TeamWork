package src;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainSubMenu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**lables making the program more user friendly*/
	private JLabel[] Virologists;
	
	/**textfield to name the virologists*/
	private JTextField[] ViroNames;
	
	/**the button to start the game*/
	private JButton GameStartBtn;
	
	/**tha game frame*/
	private GameMenu gameFrame;
	
	/**the player count*/
	private int playerCnt=3;
	
	/**
	 * The start button pressed action calling the main frame of the game
	 * @param - the amount of players
	 */
	public void GameStartBtnPress(int playerCnt) {
		boolean allTextFieldsFull=true;
		
		for(int i=0;i< playerCnt;i++) {
			if(ViroNames[i].getText().equals("")) {
				allTextFieldsFull=false;
			}
		}
		String[] VNames=new String[ViroNames.length];
		for(int i=0;i< playerCnt;i++) {
			VNames[i]=ViroNames[i].getText();
		}
		if(allTextFieldsFull) {
			int w=1120; int h=1020;
			gameFrame=new GameMenu(new Game(playerCnt,VNames));
			gameFrame.setSize(w,h);
			gameFrame.setVisible(true);
			this.dispose();
		}
	}
	
	/**
	 * The constructor of the MainSubMenu
	 * @param - the amount of players 
	 */
	public MainSubMenu(int playerCnt) {
		Virologists=new JLabel[playerCnt];
		ViroNames=new JTextField[playerCnt];
		this.playerCnt=playerCnt;
		GameStartBtn=new JButton("START GAME");
		GameStartBtn.addActionListener(new GameActionListener());
		this.setSize(800,800);
		
		JPanel submainpanel=new JPanel(new GridLayout(playerCnt+1,2,50,50));
		
		Dimension size=new Dimension(200,25);	//this is the default(minimum) size of all the JTextFields and JLabels
		
		this.setIconImage(new ImageIcon(this.getClass().getResource("icon.png")).getImage());
		
		this.setTitle("Main Sub Menu");
		
		for(int i=0;i<playerCnt;i++) {
			Virologists[i]=new JLabel("Virologist "+(i+1));
			Virologists[i].setSize(size);
			ViroNames[i]=new JTextField();
			ViroNames[i].setSize(size);
			submainpanel.add(Virologists[i]);
			submainpanel.add(ViroNames[i]);
			
			Virologists[i].setVisible(true);
			ViroNames[i].setVisible(true);
		}
		submainpanel.add(GameStartBtn);
		this.add(submainpanel);
		
		this.setVisible(true);
	}
	
	/**
	 * The actionListener of the button
	 */
	final class GameActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			GameStartBtnPress(playerCnt);
		}		
	}
}

package src;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * MainFrame
 * The very first frame that the user sees
 */
public class MainMenu extends JFrame{
	
	private static final long serialVersionUID = -7953672467585166161L;
	
	/**the panel with the logo*/
	private JPanel logo;
	
	/**the submenu where you can name the virologist*/
	private MainSubMenu subMenuFrame;
	
	/**the possible number of players*/
	private String[] playerNum;
	
	/**JCombobox where the user can choose the number of players*/
	private JComboBox numChooser;
	
	/**the start button*/
	private JButton startBtn;
	
	/**the label making the program more user friendly*/
	private JLabel plCnt;
	
	/**the basic player count*/
	private int playercount=3;
	
	/**
	 * Constructor for the MainMenu
	 */
	public MainMenu() {
		
		/**The constructor of frame*/
		//this = new JFrame();
		JPanel mainp=new JPanel(new GridBagLayout());//holding the whole panel
		GridBagConstraints c = new GridBagConstraints();
		this.add(mainp);
		
		this.setIconImage(new ImageIcon(this.getClass().getResource("icon.png")).getImage());
		
		this.setTitle("Main Menu");
		
		
		
		Toolkit tk=Toolkit.getDefaultToolkit(); 			//Initializing the Toolkit class.
		Dimension screenSize = tk.getScreenSize(); 			//Get the Screen resolution of our device.
		this.setSize(500,500); 	//Set the width and height of the JFrame.
		
		/**The content of the JCombobox*/
		playerNum=new String[] {"3","4","5"};
		
		/**Close*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**Assigning the button, panel and label to the layout */
		logo=new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		mainp.add(logo, c);
		
		
		plCnt= new JLabel("Number of players:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		//c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		mainp.add(plCnt, c);
		
		startBtn=new JButton("START");
		/**The listener which starts the naming process*/
		startBtn.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e) {
				subMenuFrame=new MainSubMenu(playercount);
				subMenuFrame.setSize(300,400);
		        subMenuFrame.setVisible(true);
		        Dispose();
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		//c.weightx = 0.2;
		c.gridx = 0;
		c.gridy = 2;
		mainp.add(startBtn, c);
		
		JPanel whitespace=new JPanel();
		c.gridwidth = 1;
		//c.weightx = 0.2;
		c.gridx = 1;
		c.gridy = 2;
		mainp.add(whitespace, c);
		
		numChooser=new JComboBox(playerNum);
		numChooser.addActionListener(new ComboBoxListener());
		numChooser.setVisible(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		//c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		mainp.add(numChooser, c);		
	}
	
	/**
	 * The list where the user can choose the number of players going to be playing the game
	 */
	final class ComboBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			 playercount=Integer.parseInt((String)numChooser.getSelectedItem());
		}
	}
	
	/**
	 * Closing the main frame
	 */
	public void Dispose() {
		this.dispose();
	}
}

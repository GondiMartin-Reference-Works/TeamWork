package src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GameMenu
 * This class responsible for the main frame of the game.
 * @author erdei
 */
public class GameMenu extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5419089507810165936L;
	
	/**the button representing the craft interaction*/
	JButton btCraft;
	
	/**the button representing the anoint interaction*/
	JButton btAnoint;
	
	/**the button representing the pickup interaction*/
	JButton btPickUp;
	
	/**the button representing the drop interaction*/
	JButton btDrop;
	
	/**the button representing the steal equipment interaction*/
	JButton btStealEq;
	
	/**the button representing the steal material interaction*/
	JButton btStealMat;
	
	/**the button representing the attack interaction*/
	JButton btAttack;
	
	/**the button representing the pass interaction*/
	JButton btPass;
	
	JLabel currVName;
	
	/**the current player*/
	private Virologist currentPlayer;
	
	/**the game played by the users*/
	Game game;
	
	/**the Thing selected in the select menu*/
	private Thing selectedThing;
	
	/**the panel where the map /field is drawn*/
	Canvas canvas;
	
	/**the panel where the canvas is added*/
	private JPanel mainPanel;
	
	
	private static Object lock = new Object();
	private static JFrame selectMenu = new JFrame();
	
	/**
	 * default constructor
	 */
	GameMenu(Game game) {
		this.game=game;
		currentPlayer = this.game.getCurrentPlayer();
		
		currVName=new JLabel();
		currVName.setText(currentPlayer.getName());
		
		Toolkit tk=Toolkit.getDefaultToolkit(); 			//Initializing the Toolkit class.
		Dimension screenSize = tk.getScreenSize(); 			//Get the Screen resolution of our device.
		//this.setSize(screenSize.width,screenSize.height-800); 	//Set the width and height of the JFrame
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setMinimumSize(new Dimension(screen.width/2, screen.height/2));
		this.setLocation(screen.width/2-this.getSize().width/2, 0);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.game.NewGame();
		
		canvas = new Canvas(currentPlayer.GetField().GetNeighbours().size(),currentPlayer.GetField());
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(canvas, BorderLayout.CENTER);
		canvas.repaint();
		canvas.setVisible(true);
		JPanel pane2 = new JPanel(new FlowLayout());
		
		this.setIconImage(new ImageIcon(this.getClass().getResource("icon.png")).getImage());
		
		this.setTitle("Game");
		
		
		
		 btCraft=new JButton("Craft");						//if the button is pressed, the according function gets called
		 btCraft.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e) {
				/** checks whether the current player has any action points left and know any gencode to craft*/
				if(currentPlayer.GetGenCodeCollection().GetAgents().size()==0 || game.getActionCount()==0)
				{
					return;
				}
				CallCraft();
			}
		 });
		 
		 btAnoint = new JButton("Anoint");					//if the button is pressed, the according function gets called
		 btAnoint.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e) {
					/** checks whether the current player has any action point left and has any available agent to anoint with*/
					if(currentPlayer.GetCraftedACollection().GetAgents().size()==0 || game.getActionCount()==0)
					{
						return;
					}
					int cnt=0;
					ArrayList<Virologist> virologists= new ArrayList<Virologist>();
					for (Thing vir : currentPlayer.field.GetThings()) {
						if(vir.toString().equals("Virologist"))
							virologists.add((Virologist)vir);
							cnt++;
					}
					if(cnt==0)
						return;
					CallAnoint();
				}
			 });
		 
		 btPickUp = new JButton("Pick Up");					//if the button is pressed, the according function gets called
		 btPickUp.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e) {
					/**checks whether the current player has any action points left and has empty space for an equipment*/
					if(game.getActionCount()==0)
					{
						return;
					}
					int cnt=0;
					for(Thing t : currentPlayer.field.GetThings())
					{
						if(t.toString().contains("Axe") || t.toString().contains("Cloak") || t.toString().contains("Sack") || t.toString().contains("Gloves")) {
							cnt++;
							
						}
					}
					if(cnt==0)
						return;
					CallPick();
				}
			 });
		 
		 btDrop = new JButton("Drop");						//if the button is pressed, the according function gets called
		 btDrop.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e) {
					/** checks whether the current player has any action points left and can drop an item*/
					if(currentPlayer.GetEquipmentCollection().GetEquipments().size()==0 || game.getActionCount()==0)
					{
						return;
					}
					CallDrop();
				}
			 });
		 
		 btStealEq = new JButton("Steal Equipment");		//if the button is pressed, the according function gets called
		 btStealEq.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e) {
					/**checks whether the current player has any action points left to use and has space for more equipment*/
					if(game.getActionCount()==0 || currentPlayer.GetEquipmentCollection().GetEquipments().size()>=3)
					{
						return;
					}
					int cnt=0;
					/** checks whether there is an available virologist on the field to rob*/
					ArrayList<Virologist> virologists= new ArrayList<Virologist>();
					for (Thing vir : currentPlayer.field.GetThings()) {
						if(vir.toString().equals("Virologist"))
							virologists.add((Virologist)vir);
							cnt++;
					}
					if(cnt<=1)
						return;
					/**checks whether the current player can store any more equipment*/
					int eq=0;
					for (Virologist vir : virologists) {
						if(vir.GetEquipmentCollection().GetEquipments().size()>0)
							eq++;
					}
					if(eq==0)
						return;
					CallStealEq();
				}
			 });
		 
		 btStealMat = new JButton("Steal Material");		//if the button is pressed, the according function gets called
		 btStealMat.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e) {
					/** checks if there is any actionpoint left and  whether the virologist has any space left for materials*/
					if(game.getActionCount()==0 || (currentPlayer.GetMaterialCollection().GetAmino().GetAmount()>=20 && currentPlayer.GetMaterialCollection().GetNucle().GetAmount()>=20 ))
					{
						return;
					}
					/**checks whether is a virologist to be robbed*/
					int cnt=0;
					ArrayList<Virologist> virologists= new ArrayList<Virologist>();
					for (Thing vir : currentPlayer.field.GetThings()) {
						if(vir.toString().equals("Virologist"))
							virologists.add((Virologist)vir);
							cnt++;
					}
					if(cnt==0)
						return;
					/**checks whether the current player could steal material from victim*/
					int eq=0;
					for (Virologist vir : virologists) {
						if(vir.GetMaterialCollection().GetAmino().GetAmount()>0 || vir.GetMaterialCollection().GetNucle().GetAmount()>0)
							eq++;
					}
					if(eq==0)
						return;
					CallStealMat();
				}
			 });
		 
		 btAttack= new JButton("Attack");					//if the button is pressed, the according function gets called
		 btAttack.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e) {
					
					/**if there are no actionpoint left*/
					if(game.getActionCount()==0)
					{
						return;
					}
					/**checks if there are avaliable virologist to kill*/
					int cnt=0;
					ArrayList<Virologist> virologists= new ArrayList<Virologist>();
					for (Thing vir : currentPlayer.field.GetThings()) {
						if(vir.toString().equals("Virologist"))
							virologists.add((Virologist)vir);
							cnt++;
					}
					if(cnt==0)
						return;
					/**checks if said virologist is bear*/
					int bear=0;
					for (Virologist vir : virologists) {
						if(vir.isBear())
							bear++;
					}
					if(bear==0)
						return;
					/** checks whether the current player has an axe usable*/
					int axe=0;
					for(Equipment eq : currentPlayer.GetEquipmentCollection().GetEquipments()) {
						if(eq.toString().equals("Axe")&& eq.useTime>0)
							axe++;
					}
					if(axe==0)
						return;
					CallAttack();
				}
			 });
		 
		 btPass= new JButton("Pass"); 						//if the button is pressed, the according function gets called
		 btPass.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e) {
					CallPass();
				}
			 });
		 
		 pane2.add(btCraft); //adding the buttons which are responsible for controlling the game to the panel
		 pane2.add(btAnoint);
		 pane2.add(btPickUp);
		 pane2.add(btDrop);
		 pane2.add(btStealEq);
		 pane2.add(btStealMat);
		 pane2.add(btAttack);
		 pane2.add(btPass);
		 pane2.add(currVName);
		 canvas.setVisible(true);
		 pane2.setVisible(true);
		 
		 mainPanel.add(pane2, BorderLayout.SOUTH);
		 //this.add(pane2, BorderLayout.SOUTH);
		 this.add(mainPanel);
		 this.pack();
		 this.setVisible(true);
		 
	}
	
	
	/**
	 * Calls the craft method with the choosen genCode in the parameter
	 */
	public void CallCraft() {
		
		// Selecting the genCode
		selectMenu = new SelectThingsMenu(currentPlayer,"genCode" , this);
		selectMenu.setVisible(true);
		GameMenu frame = this;
		
		Thread t = new Thread() {
			public void run() {
				synchronized (lock) {
					while(selectMenu.isVisible()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// Do Nothing
						}
					}
					// After closing it
					if(frame.GetSelectedItem() != null) {
						game.getCurrentPlayer().Craft((Agent) frame.GetSelectedItem());
						game.decreaseActioncount();
					}
					selectedThing = null;
				}
			}
		};
		t.start();
	}
	
	public Object getLock() { return lock; }

	/**
	 * Calls the anoint method with the choosen virologist and crafted agent in the parameter
	 */
	public void CallAnoint() {
		selectMenu = new SelectThingsMenu(currentPlayer,"Virologists" , this);
		selectMenu.setVisible(true);
		GameMenu frameThis = this;
		
		Thread t1 = new Thread() {
			public void run() {
				synchronized (lock) {
					while(selectMenu.isVisible()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// Do Nothing
						}
					}
					// After closing it
					Virologist localEnemy = (Virologist) frameThis.GetSelectedItem();
					if(localEnemy != null) {
						// Selecting the agent
						selectMenu = new SelectThingsMenu(currentPlayer, "Crafts", frameThis);
						selectMenu.setVisible(true);
						
						Thread t2 = new Thread() {
							public void run() {
								synchronized (lock) {
									while(selectMenu.isVisible()) {
										try {
											lock.wait();
										} catch (InterruptedException e) {
											// Do Nothing
										}
									}
									//After closing it
									Agent virus = (Agent) frameThis.GetSelectedItem();
									if(virus != null) {
										game.getCurrentPlayer().Anoint(localEnemy, virus);
										game.decreaseActioncount();
									}
									selectedThing = null;
								}
							}
						};
						t2.start();
					}
					selectedThing = null;
				}
			}
		};
		t1.start();
	}
	
	/**
	 * Calls the craft pickup with the choosen equipment in the parameter
	 */
	public void CallPick() {
		// Selecting the equipment
		selectMenu = new SelectThingsMenu(currentPlayer, "Equipments from Field" , this);
		selectMenu.setVisible(true);
		GameMenu frameThis = this;
		
		Thread t = new Thread() {
			public void run() {
				synchronized (lock) {
					while(selectMenu.isVisible()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// Do Nothing
						}
					}
					// After closing it
					if(frameThis.GetSelectedItem() != null) {
						game.getCurrentPlayer().PickUpEquipment((Equipment) frameThis.GetSelectedItem());
						game.decreaseActioncount();
					}
					selectedThing = null;
				}
			}
		};
		t.start();
	}
	
	/**
	 * Calls the steal equipment method with the choosen virologist and equipment in the parameter
	 */
	public void CallStealEq() {
		// Selecting enemy
		selectMenu = new SelectThingsMenu(currentPlayer, "Virologists" , this);
		selectMenu.setVisible(true);
		GameMenu frameThis = this;
		
		Thread t1 = new Thread() {
			public void run() {
				synchronized (lock) {
					while(selectMenu.isVisible()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// Do Nothing
						}
					}
					// After closing it
					Virologist localEnemy = (Virologist) frameThis.GetSelectedItem();
					if(localEnemy != null) {
						// Selecting the Equipment
						selectMenu = new SelectThingsMenu(localEnemy, "Equipments from Virologist", frameThis);
						selectMenu.setVisible(true);
						
						Thread t2 = new Thread() {
							public void run() {
								synchronized (lock) {
									while(selectMenu.isVisible()) {
										try {
											lock.wait();
										} catch (InterruptedException e) {
											// Do Nothing
										}
									}
									//After closing it
									Equipment eq = (Equipment) frameThis.GetSelectedItem();
									if(eq != null) {
										game.getCurrentPlayer().StealEquipment(localEnemy, eq);
										game.decreaseActioncount();
									}
									selectedThing = null;
								}
							}
						};
						t2.start();
					}
					selectedThing = null;
				}
			}
		};
		t1.start();
	}
	
	/**
	 * Calls the drop method with the choosen equipment in the parameter
	 */
	public void CallDrop() {
		// Selecting the equipment
		selectMenu = new SelectThingsMenu(currentPlayer, "Equipments from Virologist" , this);
		selectMenu.setVisible(true);
		GameMenu frameThis = this;
		
		Thread t = new Thread() {
			public void run() {
				synchronized (lock) {
					while(selectMenu.isVisible()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// Do Nothing
						}
					}
					// After closing it
					if(frameThis.GetSelectedItem() != null) {
						game.getCurrentPlayer().DropEquipment((Equipment) frameThis.GetSelectedItem());
						game.decreaseActioncount();
					}
					selectedThing = null;
				}
			}
		};
		t.start();
	}
	
	/**
	 * Calls the steal material method with the choosen virologist in the parameter
	 */
	public void CallStealMat() {
		// Selecting the enemy
		selectMenu = new SelectThingsMenu(currentPlayer, "Virologists" , this);
		selectMenu.setVisible(true);
		GameMenu frame = this;
		
		Thread t = new Thread() {
			public void run() {
				synchronized (lock) {
					while(selectMenu.isVisible()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// Do Nothing
						}
					}
					// After closing it
					if(frame.GetSelectedItem() != null) {
						game.getCurrentPlayer().StealMaterial((Virologist) frame.GetSelectedItem());
						game.decreaseActioncount();
					}
					selectedThing = null;
				}
			}
		};
		t.start();
	}
	
	/**
	 * Calls the attack method with the choosen virologist in the parameter
	 */
	public void CallAttack() {
		// Selecting the enemy
		selectMenu = new SelectThingsMenu(currentPlayer, "Virologists" , this);
		selectMenu.setVisible(true);
		GameMenu frame = this;
		
		Thread t = new Thread() {
			public void run() {
				synchronized (lock) {
					while(selectMenu.isVisible()) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// Do Nothing
						}
					}
					// After closing it
					if(frame.GetSelectedItem() != null) {
						game.getCurrentPlayer().Attack((Virologist) frame.GetSelectedItem());
						game.decreaseActioncount();
					}
					selectedThing = null;
				}
			}
		};
		t.start();
	}
	
	/**
	 * Calls the pass method responsible for skipping the round of this specific player
	 */
	public void CallPass() {
		game.NewRound();
		this.currentPlayer=game.getCurrentPlayer();
		currVName.invalidate();
		currVName.setText(currentPlayer.getName());
		canvas.setField(currentPlayer.GetField());
		canvas.repaint();
	}
	
	/**
	 * Adds the Thing in the parameter to the selectedThing
	 */
	public void SetSelectedItem(Thing t) {
		this.selectedThing = t;
	}
	
	/**
	 * Returns the selected thing
	 * @return thing
	 */
	public Thing GetSelectedItem() { return selectedThing; }
	
	/**
	 * Returns the current player
	 * @return current player
	 */
	public Virologist GetCurrentPlayer() {
		return currentPlayer;
	}
	
	public void SetNewPanelField() {
		
	}

}

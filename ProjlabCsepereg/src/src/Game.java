package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Vili
 * Game
 * This class counts the number of rounds, stores the player who is moving now 
 * and in the previous round and stores all the players playing in this game.
 * This is where building of the map starts. Stores the maximum number of
 * genetic codes that can be learnt. 
 */
public class Game implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6862257109428847614L;

	/**
	 * The number of players in current game.
	 */
	private int playerCount;
	
	/**
	 * The number of the current round
	 */
	private int roundCount;
	
	/**
	 * The number of genetic codes that can be learnt.
	 */
	private static final int maxGenCode = 5;
	
	/**
	 * The player who can currently move and interact with other things.
	 */
	private static Virologist currentPlayer;
	
	/**
	 * The player who could currently move and interact with other things in the
	 * previous round.
	 */
	private static Virologist previousPlayer;
	
	/**
	 * The list of current players.
	 */
	private ArrayList<Virologist> players;
	
	/**
	 * It stores the map.
	 */
	private Map map;
	
	/**Stores the game's randomness*/
	private static boolean random;
	
	private int actionCount=3;
	
	/**
	 * Constructor for the game
	 * @param playerCount - the number of the players
	 */
	public Game(int playerCount,String[] vNames) {
		this.playerCount = playerCount;
		this.roundCount = 0;
		this.map = new Map();
		players = new ArrayList<Virologist>();
		for(int i = 0; i<playerCount; ++i)
			players.add(new Virologist(vNames[i]));
		currentPlayer = players.get(0);
		random = true;
		Prototype.logger("The map has been generated.", Prototype.GetLogFile());
	}
	
	/**
	 * Gives the game's players back
	 * @return all the players
	 */
	public ArrayList<Virologist> getPlayers(){
		return players;
	}
	
	/**
	 * Gives the game's map back
	 * @return the map
	 */
	public Map GetMap() {
		return map;
	}
	
	/**
	 * It starts a new game.
	 */
	void NewGame() {
		for(int i = 0; i < playerCount; i++) {
			Random rand = new Random();
			System.out.println(map.GetFields().size());
			int field = rand.nextInt(map.GetFields().size());
			map.GetFields().get(field).Accept(players.get(i));   //Random Field gets a Player
		}
		
		try {
			currentPlayer = players.get(0);
		}
		catch(NullPointerException e) {
			System.out.println("Nincsenek jatekosok!");
		}
		//map.GenerateFields(players);
	}
	
	/**
	 * It ends the game and declares the winner.
	 */
	static void EndGame() {
		
	}
	
	/**
	 * This method is called every time a Virologist learns a genetic code and checks
	 * if it has learnt all of them.
	 * @return Did the virologist learn all the genetic codes.
	 */
	public static boolean CheckWin() {
		if(currentPlayer.GetGenCodeCollection().GetSize()==maxGenCode) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method sets the next player in the next round. Decreases the effect time 
	 * and crafted agents' time for the previous player. All the effects will affect the
	 * current player.
	 */
	public void NewRound() {
		setActionCount(3);
		currentPlayer.CallDecreaseAgentTime();
		++roundCount;
		int indexPrevious = players.indexOf(currentPlayer);
		previousPlayer = currentPlayer;
		
		int indexCurrent = 0;
		
		if(indexPrevious == players.size()-1)
			indexCurrent = 0;
		else
			indexCurrent = indexPrevious + 1;
		
		currentPlayer.CallAffectWithAll();
		currentPlayer = players.get(indexCurrent);
		Prototype.logger("Newround", Prototype.GetLogFile());
	}
	
	public Virologist getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Returns the program's randomness
	 * @return the game's randomness
	 */
	public static boolean isRandom() {
		return random;
	}

	/**
	 * Sets the program's randomness
	 * @param random - the value
	 */
	public void setRandom(boolean random) {
		this.random = random;
	}

	public int getActionCount() {
		return actionCount;
	}

	public void setActionCount(int actionCount) {
		this.actionCount = actionCount;
	}
	
	public void decreaseActioncount() {
		this.actionCount--;
	}
}

package src;

import java.util.ArrayList;
import java.util.Random;

/**
 * <b>Laboratory class</b><br>
 * <i>Inherits from Field.</i><br><br>
 * Stores one genetic code which can be learnt by a virologist.<br>
 * Once a virologist enters a laboratory, it can start learning the foundable genetic code.
 * @author Martin
 */
public class Laboratory extends Field{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5384914385619678612L;

	/**Stores the laboratory's one and only genetic code.*/
	private Agent genCode;
	
	/**Stores the laboratory's one and only bearDanceCode if it has or not.*/
	private Agent bearDanceCode;
	
	/**
	 * Constructor for the Laboratory
	 */
	public Laboratory() {
		super();
		 
		if(Game.isRandom()) {
			Random rand = new Random();
			double bearDanceSpawnChance = rand.nextDouble() * 100;
			if(bearDanceSpawnChance >= 0.85) {
				bearDanceCode = new BearDance();
			}
			int genCodeRandom = rand.nextInt(3);
			switch(genCodeRandom) {
				case 0: genCode = new Amnesia(); break;
				case 1: genCode = new Chorea(); break;
				case 2: genCode = new Paralyze(); break;
				case 3: genCode = new Protect(); break;
			}
		}else {
			genCode = new Protect();
			bearDanceCode = new BearDance();
		}
	}
	
	/**
	 * Allocates and creates a genetic code for the laboratory.
	 */
	public void CreateGenCode() {
		
	}
	
	/**
	 * Setting the genCode manually
	 */
	public void SetGenCode(Agent a) {
		genCode = a;
	}
	
	/**
	 * Stores the given virologist. <br>
	 * Laboratory asks the virologist to learn its genetic code.
	 * @param v - the given virologist who might not know the genetic code
	 */
	@Override
	public void Accept(Virologist v) {
		this.things.add(v);
		v.CloneGenCode(genCode);
		
		if(bearDanceCode != null) {
			v.BearDanceAnoint(v);  //nem kerül le a beardance a mezõrõl, ez jó így?
		}
		v.SetField(this);
	}
	
	/**
	 * Gives the field's type back
	 * @return the type of the field
	 */
	@Override
	public String toString() {
		return "Laboratory";
	}
}

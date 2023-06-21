package src;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author Vili
 * Cloak
 * Inherited from Equipment
 * It implements the equipment "Cloak". This equipment has an effect of protecting the virologist against other agents with a certain amount of chance.
 *
 */
public class Cloak extends Equipment {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8933112089535646504L;

	/**
	 * default constructor
	 */
	public Cloak() {
		this.useTime = 1;
	}
	
	/**
	 * Responsible for the effect this item has on the virologist.
	 * @param v The virologist, who is being affected by an effect.
	 */
	public void Affect(Virologist v) {
		if(Chance()) {
			
		}
	}
	
	/**
	 * Calculates the chance of protecting the Virologist against Touching and BearDance.
	 * @return Whether the Virologist can be interacted with.
	 */
	public boolean Chance() {
		if(Game.isRandom()) {
			Random rand = new Random();
			double chance = rand.nextDouble() * 100;
			return chance >= 82.3;
		}
		return true;
		
	}
	
	/**
	 * Return the name of the effect(item)
	 * @return Returns "Cloak".
	 */
	public String GetEffectName() {
		return "Cloak";
	}
	
	/**
	 * The ovveriden toString method
	 * @return Returns the properties of this Cloak.
	 */
	@Override
	public String toString() {
		return GetEffectName()+this.ID;
	}

	@Override
	public void DecreaseEffectTime(Virologist v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int DecreaseUseTime() {
		//üres
		return 1;
	}
}

package src;

/**
 * 
 * @author Vili
 * Sack
 * Inherited from Equipment.
 * It implements the equipment "Sack". By wearing this equipment the virologist's material inventory will be expanded by a certain amount.
 *
 */
public class Sack extends Equipment {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7020711234895534256L;

	/**
	 * Constructor for Sack.
	 */
	public Sack() {
		useTime = 1;
	}
	
	/**
	 * 
	 * @param v The virologist, who is being affected by an effect.
	 */
	public void Affect(Virologist v) {
	}
	
	/**
	 * 
	 * @return Returns "Sack".
	 */
	public String GetEffectName() {
		return "Sack";
	}
	
	/**
	 * Gives the equipment's properties back.
	 * @return Returns the properties of this Sack.
	 */
	@Override
	public String toString() {
		return GetEffectName()+" " + this.ID;
	}

	/**
	 * Decrease the equipment's effect time
	 */
	@Override
	public void DecreaseEffectTime(Virologist v) {
		//üres
		
	}

	/**
	 * Returns the equipment's reduced use-time.
	 * <br>Reduces the use time by one.
	 * @return the reduces value
	 */
	@Override
	public int DecreaseUseTime() {
		return 1;
	}
}

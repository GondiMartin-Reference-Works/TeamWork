package src;

/**
 * 
 * @author Vili
 * Gloves
 * Inherited from Equipment.
 * It implements the equipment "Gloves". By wearing this equipment the virologist1 
 * is able to anoint the other virologist2 with the agent virologist2 anointed to 
 * virologist1.
 */
public class Gloves extends Equipment {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5223975989698407522L;

	/**
	 * Constructor for Gloves
	 */
	public Gloves() {
		super();
		useTime = 3;
	}
	
	/**
	 * 
	 * @param v The virologist, who is being affected by an effect.
	 */
	public void Affect(Virologist v) {
	}
	
	/**
	 * 
	 * @return Returns "Gloves".
	 */
	public String GetEffectName() {
		return "Gloves";
	}
	
	/**
	 * 
	 * @return Returns the properties of these Gloves.
	 */
	@Override
	public String toString() {
		return GetEffectName()+ " " + this.ID;
	}

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
		return --useTime;
	}
	
	/**
	 * Returns the available uses for the equipment
	 * @return useTime
	 */
	public int GetUseTime() {
		return this.useTime;
	}

}

package src;

/**
 * <b>Effect class</b><br>
 * This interface is implemented in Agent and Equipment classes so that they can behave like an effect.<br>
 * Interacts with the virologist.
 * @author - Martin
 */
public interface Effect {
	
	/**
	 * Affects the given virologist.
	 * @param v - the affected virologist
	 */
	public void Affect(Virologist v);
	
	/**
	 * Gives back the effect's name.
	 * @return the effect's name.
	 */
	public String GetEffectName();
	
	/**
	 * Reduces the effect's effect time, if it's considered to be an Agent.
	 * @param v - the virologist whose agents' effect time gets reduced
	 */
	public void DecreaseEffectTime(Virologist v);
}

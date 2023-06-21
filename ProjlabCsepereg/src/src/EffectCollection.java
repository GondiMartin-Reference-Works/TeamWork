package src;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
/**
 * EffectCollection
 * Stores classes that implement the Effect interface, that are affecting the Virologist in the current round.
 * @author csizm
 *
 */
public class EffectCollection implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7923083142104482278L;
	
	
	/** Stores all of the Effects applied to the Virologist*/
	private List<Effect> effects;
	
	/**
	 * default constructor
	 */
	public EffectCollection() {
		effects = new ArrayList<Effect>();
	}
	
	/**
	 * Adds a new Effect to the collection.
	 * @param craftedAgent the Effect
	 */
	public void Add(Effect e, Virologist v) {
		effects.add(e);
		e.Affect(v);
	}
	
	/**
	 * Removes the Effect given as parameter from the collection.
	 * @param e the Effect we need to remove
	 */
	public void Remove(String e) {
		for(int i=0; i< effects.size(); i++) {
			if(effects.get(i).toString().contains(e)) {
				effects.remove(i);
				return;
			}
		}
	}
	
	/**
	 * Checks whether the Effect given as parameter is in the collection or not.
	 * @param effectName - the name of the Effect we compare to the Effect names in the collection
	 * @return true, if the Effect is found
	 */
	public boolean Contains(String effectName) {
		for(int i = 0; i < effects.size(); i++) {
			if(effects.get(i).toString().contains(effectName)) return true; //If the toString of the Effect contains the effectname return true
		}
		return false;
	}
	
	/**
	 * Affects the Virologist (owner of the collection)with all of the Effects stored.
	 * @param v Virologist, it is given as parameter to the Affect methods
	 */
	void AffectWithAll(Virologist v) {
		for(int i=0;i<effects.size();i++) {
			effects.get(i).Affect(v);
		}
	}
	
	/**
	 * Decreases all of the Agent's EffectTime by one (by calling DecreaseEffectTime() methods).
	 * @param v Virologist, who has the EffectCollection, given as parameter to DecreaseEffectTime()
	 */
	void DecreaseAgentTimeEColl(Virologist v) {
		for(int i=0;i< effects.size();i++) {
			this.effects.get(i).DecreaseEffectTime(v);			
			
		}
	}
	
	/**
	 * Listing every effect a Virologist has
	 * @return the effects
	 */
	public String toString() {
		String effect ="";
		for(Effect e : effects)
		{
			effect+=e.GetEffectName()+ System.lineSeparator();
		}
		return effect;
	}
	
	/**
	 * Returns the Effect Collection of the Virologist
	 * @return effect collection
	 */
	public List<Effect> GetEffects() {
		return effects;
	}
	
	
}

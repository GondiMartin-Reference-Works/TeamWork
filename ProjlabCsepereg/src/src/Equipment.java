package src;
import java.io.Serializable;
/**
 * Equipment
 * Thing - base class
 * Implements Effect interface.
 * The abstract base class from which the the different equipments inherit(e.g. Gloves).
 * It can be picked up or thrown out of a Virologist's EquipmentCollection. When it's in someone's EquipmentCollection, it applies its Effect.
 * @author csizm
 *
 */
public abstract class Equipment extends Thing implements Effect, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5600753556673011253L;
	
	/** The equipment's time of use which tells when will the equipment get destroyed*/
	protected int useTime;
	
	/**
	 * The Equipment applies its effect on the virologist given as parameter. The subclasses override this method to apply their unique effects.
	 * @param v the Virologist who has the equipment
	 */
	public abstract void Affect(Virologist v);
	
	/**
	 * Returns the name of the Effect the Equipment has. 
	 * @return a string containing the name of the Effect
	 */
	public abstract String GetEffectName();
	
	/**
	 * Returns the equipment's reduced use-time.
	 * <br>Reduces the use time by one.
	 * @return the reduces value
	 */
	public abstract int DecreaseUseTime();
	
	/**
	 * Writes the Attributes of the Equipment in a string.
	 * @return string
	 */
	public String toString() {
		return "Equipment";
	}
}

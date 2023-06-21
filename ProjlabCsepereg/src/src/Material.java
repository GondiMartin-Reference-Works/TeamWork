package src;

/**
* Material
* Inharited from Thing
* amount - Amount of the material
* Base class to the AminoAcid and Nucleotid
**/
public abstract class Material extends Thing {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1306440827233104804L;
	
	/**Shows the amount of the Material*/
	protected int amount;
	
	public Material() {
		
		amount = 20;
	}
	
	/**
	* Getter to the amount
	* @return amount
	**/
	public int GetAmount() {
		return amount;
	}
	
	/**
	* Increases the amount with the int in the parameter
	* @param a - adds this amount to the amount
	**/
	public void AddAmount(int a) {
		amount += a;
	}
	
	/**
	* Decreases the amount with the int in the parameter
	* @param a - removes this amount to the amount
	**/
	public void RemoveAmount(int a) {
		amount -= a;
	}
	
	/**
	* Default ToString method for console printout.
	* @return String with the amount
	**/
	public String ToString() {
		return ("Amount of material: " + amount);
	}
}

package src;

/**
* Nucleotid
* Inherited from Material
* amount - from Material
* Represents Nucleotid with an amount
**/
public class Nucleotid extends Material{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6124272991668120521L;

	/**
	 * default constructor
	 */
	public Nucleotid() {
		amount = 20;
	}
	
	/**
	 * constructor with parameter
	 * @param amount: how many nucleotid we are going to have
	 */
	public Nucleotid(int amount) {
		this.amount=amount;
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
		return ("Amount of Nucleotid: " + amount);
	}
}
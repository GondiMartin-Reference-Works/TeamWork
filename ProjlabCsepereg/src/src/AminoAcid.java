package src;


/**
* AminoAcid
* Inherited from Material
* amount - from Material
* Represents AminoAcid with an amount
**/
public class AminoAcid extends Material{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7701297255900816515L;
	/**
	 * default constructor
	 */
	public AminoAcid() {
		amount=20;
	}
	
	/**
	 * constructor with parameter
	 * @param amount: how many aminoacid we are going to have
	 */
	public AminoAcid(int amount) {
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
		return ("Amount of AminoAcid: " + amount);
	}
}

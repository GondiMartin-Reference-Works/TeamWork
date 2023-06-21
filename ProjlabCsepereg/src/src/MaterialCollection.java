package src;
import java.io.Serializable;

/**
* MaterialCollecton
* amino - An AminoAcid with amount
* nucle - An Nucleotid with amount
* It represent a collection of the Materials that a player can find on the map.
**/
public class MaterialCollection implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2837607366196673693L;

	/**The AminoAcid in the collection*/
	private AminoAcid amino;
	
	/**The Nucleotid in the collection*/
	private Nucleotid nucle;
	
	/**
	 * default constructor
	 */
	public MaterialCollection() {

		amino = new AminoAcid();
		nucle = new Nucleotid();
	}

	
	/**
	* Getter to the AminoAcid
	* @return amino
	**/
	public AminoAcid GetAmino(){
		return amino;
	}
	
	/**
	 * sets amino to the variable given as parameter
	 * @param a Aminoacid
	 */
	public void SetAmino(AminoAcid a) {
		amino=a;
	}
	
	/**
	* Getter to the Nucleotid
	* @return nucle
	**/
	public Nucleotid GetNucle() {
		return nucle;
	}
	
	/**
	 * sets nucle to the variable given as parameter
	 * @param a Nucleotid
	 */
	public void SetNucle(Nucleotid n) {
		nucle=n;
	}
	
	/**
	 * Sets the amount of the Materials to zero.
	 */
	public void DestroyMaterials() {
		amino.RemoveAmount(amino.GetAmount());
		nucle.RemoveAmount(nucle.GetAmount());
	}
	
	/**
	 * Returns the aminoacid and nucleotid amount the virologist possess, or the Warehouse stores
	 * @return nucleotid and aminoacid amount
	 */
	public String toString() {
		return nucle.ToString()+System.lineSeparator()+ amino.ToString();
	}
}

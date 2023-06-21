package src;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * EquipmentCollection
 * Stores the different Equipments a Virologist picks up in a heterogenous collection.
 * @author csizm
 *
 */
public class EquipmentCollection implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3488868505635455314L;
	
	/**the Equipments that the Virologist has */
	private ArrayList<Equipment> equipments;
	
	public EquipmentCollection() {
		equipments = new ArrayList<Equipment>();
	}
	
	/**
	 * Adds a new Equipment to the collection.
	 * @param e the Equipment added
	 */
	public void Add(Equipment e) {
		equipments.add(e);
	}
	
	/**
	 * Returns how many items are in the collection.
	 * @return number of equipments
	 */
	public int GetSize() {
		return equipments.size();
	}
	
	/**
	 * Returns the string of all the equipment.
	 * @return equipment
	 */
	public String toString() {
		String equipment="";
		for(Equipment e : equipments) {
			equipment+=e.toString()+System.lineSeparator();
		}
		return equipment;
	}
	
	/**
	 * Removes the equipment given as parameter from the collection.
	 * @param e the name of the item we want to remove
	 */
	public void Remove(String e) {
		for(int i=0; i< equipments.size(); i++) {
			if(equipments.get(i).equals(e)) {
				equipments.remove(i);
				return;
			}
		}
		
	}
	
	/**
	 * Checks whether the given Equipment is part of the Collection.
	 * @param e the Equipment we are checking
	 * @return true, if the equipment was found in the collection
	 */
	public boolean Contains(String s) {
		for(int i=0; i< equipments.size(); ++i) {
			if(equipments.get(i).GetEffectName().equals(s))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the equipments stored at the virologist.
	 * @return The equipments the virologist currently has.
	 */
	public ArrayList<Equipment> GetEquipments(){
		return equipments;
	}
}

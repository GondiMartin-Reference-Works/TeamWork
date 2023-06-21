package src;
import java.util.ArrayList;
import java.util.Random;

/**
 * <b>Shelter class</b><br>
 * <i>Inherits from Field.</i><br><br>
 * Stores equipments like gloves, cloak or sack.<br>
 * @author Martin
 */
public class Shelter extends Field {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**Stores the shelter's equipments.*/
	private ArrayList<Equipment> equipments;
	
	/**
	 * Constructor for the Laboratory
	 */
	public Shelter() {
		super();
		
		equipments = new ArrayList<Equipment>();
		
		if(Game.isRandom()) {
			Random rand = new Random();
			int equipRandom = rand.nextInt(3);
			switch(equipRandom) {
				case 0: equipments.add(new Axe()); break;
				case 1: equipments.add(new Cloak()); break;
				case 2: equipments.add(new Gloves()); break;
				case 3: equipments.add(new Sack()); break;
			}
		}else {
			equipments.add(new Gloves());
		}
	}
	
	/**
	 * Allocates and creates special equipments for the shelter's store.
	 */
	public void CreateEquipment() {
		
	}
	
	/**
	 * Stores the given virologist. <br>
	 * Asks the virologist to check the shelter's equipments.
	 * @param v - the given virologist who might pick up an equipment
	 */
	
	 public void Accept(Virologist v) {
		 v.SetField(this);
		 this.things.add(v);
	 }
	 
	
	/**
	 * Stores the given thing.
	 * @param t - the given thing that will be placed on the field
	 */
	public void Accept(Thing t) {
		this.things.add(t);
		t.SetField(this);
	}
	
	/**
	 * Gives the field's type back
	 * @return the type of the field
	 */
	@Override
	public String toString() {
		return "Shelter";
	}
}

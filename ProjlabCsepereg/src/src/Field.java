package src;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * <b>Field class</b><br>
 * Stores the things - virologists, agents, materials, equipments - which can be found here.<br>
 * Stores its neighbours.<br><br>
 * Its job is to store everyTHING that can be found or put here.<br>
 * Implements a special method - called Accept(Virologist v) - which acts different in other subclasses.
 * @author - Martin
 */
public class Field implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -652830960113928255L;

	/**A counter for the fields. Use this to differ one field from another field.*/
	private static int uniqueID = 0;
	
	/**The ID which is given to toString method.*/
	private int ID;

	/**Stores the field neighbours.*/
	protected ArrayList<Field> neighbours;
	
	/**Stores the things - virologist, agenst, materials, equipments - that can be found on the field.*/
	protected ArrayList<Thing> things;
	
	/**
	 * Constructor for the Field class.<br>
	 * Creates a unique ID for the instance.
	 */
	public Field() {
		++uniqueID;
		this.ID = uniqueID;
		neighbours = new ArrayList<Field>();
		things = new ArrayList<Thing>();
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
	 * Stores the given virologist. <br> Acts different in other subclasses.
	 * @param v - the given virologist who wants to move to the field
	 */
	public void Accept(Virologist v) {
		v.SetField(this);
		
		EffectCollection ef = v.GetEffectCollection();
		boolean contains = ef.Contains("BearDance");
		if(contains) {
			ArrayList<Thing> things = GetThings();
			for(int i = 0; i < things.size(); i++) {
				if(things.get(i).toString().contains("Virologist")) {
					v.BearDanceAnoint((Virologist)things.get(i));
				}
			}
		}
		
		this.things.add(v);
	}
	
	/**
	 * Gives back the things.
	 * @return the things that can be found on the field
	 */
	public ArrayList<Thing> GetThings(){
		return things;
	}
	
	/**
	 * Gives back a selected neighbour that has been picked out.
	 * @return the chosen neigbhour
	 */
	public Field GetNeighbour() {
		return neighbours.get(0);
	}
	
	/**
	 * Gives all the neighbour back that has been picked out.
	 * @return the chosen neigbhour
	 */
	public ArrayList<Field> GetNeighbours() {
		return this.neighbours;
	}
	
	/**
	 * Removes the given thing from its store.
	 * @param t - the removable thing
	 */
	public void Remove(Thing t) {
		this.things.remove(t);
	}
	
	/**
	 * Adds a new Field to the list of the Field's neighbours.
	 * @param f the neighbour we want to add
	 */
	public void AddNeighbours(Field f) {
		neighbours.add(f);
	}
	
	public int getID() { return this.ID; }
	
	/**
	 * Gives the field's type back
	 * @return the type of the field
	 */
	@Override
	public String toString() {
		return "Field";
	}
}

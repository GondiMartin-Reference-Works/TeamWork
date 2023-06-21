package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;


/**
* Virologist class
* Inherited from Thing
* Attributes:
* equipmentCollection
* effectCollection
* craftedAgentCollection
* genCodeCollection
* materialCollection
* 
* Each player has its own Virologist instance that they control during the game. 
**/
public class Virologist extends Thing {
	
	/**the name of the virologist*/
	private String Name;
	
	/**
	 * A boolean that shows if the player is still alive in the game
	 */
	
	private boolean alive;
	
	/**
	 * The number of maximum aminoacid a player can hold at one time.
	 */
	private int maxAmino = 20;
	
	/**
	 * The number of maximum nucleotid a player can hold at one time.
	 */
	private int maxNucle = 20;
	
	
	/**If the Virologist picks up an Equipment it gets into the equipmentCollection.*/
	private EquipmentCollection equipmentCollection;
	
	/**If the Virologist picks up an Equipment or gets an Agents Effect it gets into the effectCollection*/
	private EffectCollection effectCollection;
	
	/**If the Virologist crafts an Agent it gets into the craftedAgentCollection*/
	private AgentCollection craftedAgentCollection;
	
	/**If the Virologist learns a genCode in a Laboratory it gets into the genCodeCollection*/
	private AgentCollection genCodeCollection;
	
	/**The Materials owned by the Virologist are in the materialCollection*/
	private MaterialCollection materialCollection;
	
	/**this boolean determines, whether the virologist is a bear, or not (by default, it's not a bear)*/
	private boolean bear=false;
	
	/**
	 * Constructor of Virologist. By default, it is alive.
	 */
	public Virologist(String name) {
		equipmentCollection = new EquipmentCollection();
		effectCollection = new EffectCollection();
		craftedAgentCollection = new AgentCollection();
		genCodeCollection = new AgentCollection();
		materialCollection = new MaterialCollection();
		alive = true;
		this.Name=name;
	}
	
	/**
	 * this method returns the name of the virologist
	 * @return Name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * returns how much aminoacid fits into the virologist's collection
	 * @return maxAmino
	 */
	public int GetMaxAmino() {
		return maxAmino;
	}
	
	/**
	 * returns how much mucleotid fits into the virologist's collection
	 * @return maxNucle
	 */
	public int GetMaxNucle() {
		return maxNucle;
	}
	
	/**
	* The method stores the genCode in the parameter.
	* @param genCode - The Agent that will be stored.
	**/
	public void CloneGenCode(Agent genCode) {
		
		//Checks if the virologist already has this genCode
		boolean contains = false;

		if(this.genCodeCollection.Contains(genCode.GetEffectName())) contains = true;

		
		//If the genCode is new
		if(!contains) {
			this.genCodeCollection.Add(genCode);
			Game.CheckWin();
			
			boolean gameOver = false;
			//If the virologist won
			if(gameOver) {
				Game.EndGame();
			}
		}
	}
	
	/**
	* Shows the available Fields to the Player -> Moves the Virologist.
	**/
	public void Move(Field field) {
		
		/**Guard for index out of range*/
		if(this.field.GetNeighbours().size() == 0)
			return;
		
		/**The destination field*/
		Field tomoveto = field;
		
		this.field.Remove(this);
		this.field = tomoveto;
		tomoveto.Accept(this);
	}
	
	/**
	* Shows the available Fields to the Player -> Moves the Virologist.
	**/
	public void MoveTo(Field whereToMove) {
		field.Remove(this);
		whereToMove.Accept(this);
	}
	
	
	/**
	* If the Virologist in the parameter is touchable, it shows the interaction menu to the Player
	* @param v - A Virologist on our Field
	**/
	public void Touch(Virologist v) {
		EffectCollection effectCol = v.GetEffectCollection();
		boolean containsProtect = effectCol.Contains("protect");
		boolean containsCloak = effectCol.Contains("cloak");
		
		if(!containsProtect && !containsCloak) {
			
		}
		StealMaterial(v);
	}
	
	/**
	* The method adds the Agent (from our craftedAgentCollection) in the parameters to the victims EffectCollection
	* @param agent - An Agent from the craftedAgentCollection
	* @param victim - the choosen Virologist we wish to anoint
	**/
	public void Anoint(Virologist victim, Agent agent) {
		
		/**Checking for gloves*/
		if(victim.GetEquipmentCollection().Contains("Gloves")) {
			Gloves g=null;
			for(Equipment e: this.GetEquipmentCollection().GetEquipments()) {
				if(e.GetEffectName().equals("Gloves")) {
					g=(Gloves) e;
				}
			}
			
			/**Checking if the gloves are still usable*/
			if(g.GetUseTime()>0)
			{
				effectCollection.Add((Effect)agent,this);
				craftedAgentCollection.Remove(agent.GetEffectName());
				for(Equipment e: equipmentCollection.GetEquipments()) {
					if(victim.GetEquipmentCollection().Contains("Gloves"))
						e.DecreaseUseTime();
				}
			}
		}
		else if(victim.GetEquipmentCollection().Contains("Cloak")){
			/**Checking for cloak*/
			if(victim.GetEquipmentCollection().Contains("Cloak")) {
				Cloak c=null;
				for(Equipment e: victim.GetEquipmentCollection().GetEquipments()) {
					if(e.GetEffectName().equals("Cloak")) {
						c=(Cloak) e;
					}
				}
				//**Initiating the cloaks defense mechanism*/
				if(!c.Chance()){	
					victim.GetEffectCollection().Add((Effect)agent,victim);
					Prototype.logger("Anointed Virologist "+victim+" with "+agent.GetEffectName(), Prototype.GetLogFile());
				}else {
					Prototype.logger("Anoint fault", Prototype.GetLogFile());
				}
			}
			else {
				victim.GetEffectCollection().Add((Effect)agent,victim);
				Prototype.logger("Anointed Virologist "+victim+" with "+agent.GetEffectName(), Prototype.GetLogFile());
			}
			/**Removing the used agent*/
			craftedAgentCollection.Remove(agent.GetEffectName());
		}else {
			victim.GetEffectCollection().Add((Effect) agent, victim);
			Prototype.logger("Anointed Virologist "+victim+" with "+agent.GetEffectName(), Prototype.GetLogFile());
		}
	}
	
	/**
	* Shows a menu to the Player of the genCodeCollection that he can choose from to craft
	**/
	public void Craft(Agent tobecrafted) {
		
		/**Checking whether its possible to craft the agent*/
		if(tobecrafted.GetCostAmino() <= materialCollection.GetAmino().GetAmount() && tobecrafted.GetCostNucle() <= materialCollection.GetNucle().GetAmount()) {
				CreateAgent(tobecrafted);
				Prototype.logger("Crafted " + tobecrafted.GetEffectName(), Prototype.GetLogFile());
		}
	}
	
	/**
	* Puts the Agent in the parameter to the craftedAgentCollection (checks if it is craftable)
	* @param genCode - The Agent we wish to craft
	* @return The crafted Agent
	**/
	public Agent CreateAgent(Agent genCode) {
		
		/**Creating the specific agent*/
		Agent a = null;
		if(genCode.GetEffectName().equals("Chorea"))
			a= new Chorea();
		else if(genCode.GetEffectName().equals("Amnesia"))
			a=new Amnesia();
		else if(genCode.GetEffectName().equals("Paralyze"))
			a=new Paralyze();
		else if(genCode.GetEffectName().equals("Protect")) {
			Protect b = new Protect();
			a= b;
			
			System.out.println("Cost  bent N:" + a.costNucle);
			System.out.println("Cost  bent A:" + a.costAmino);
			System.out.println("Cost  bent N:" + b.GetCostNucle());
			System.out.println("Cost  bent A:" + b.GetCostAmino());
		}
		
		/**Adding it to the collection*/
		this.craftedAgentCollection.Add(a);
		
		/**Removes the amount required crafting the agent*/
		materialCollection.GetAmino().RemoveAmount(genCode.GetCostAmino());
		materialCollection.GetNucle().RemoveAmount(genCode.GetCostNucle());
		
		System.out.println("Cost N:" + a.costNucle);
		System.out.println("Cost A:" + a.costAmino);
		
		System.out.println("Nucle: " + materialCollection.GetNucle().amount);
		System.out.println("Amino: " + materialCollection.GetAmino().amount);
		return a;
	}
	
	/**
	* Removes the selected Equipment from the Virologists (in the parameter) equipmentCollection and adds it to its own (checks if v is paralyzed) 
	* @param victim - the number of a virologist on a certain Field
	* @param eqNum - the number of the equipment that should be stolen
	**/
	public void StealEquipment(Virologist victim, Equipment equipment) {
		
		//**Checking if the virologist is paralyzed*/
		boolean paralyzed=victim.GetEffectCollection().Contains("Paralyze");
		
		//**if true then he can be robbed*/
		if(paralyzed) {
			
			//**Victims equipmentcollection*/
			EquipmentCollection eqVictim=victim.GetEquipmentCollection();
			
			//**Players equipmentcollection*/
			EquipmentCollection eqSelf=this.GetEquipmentCollection();
			
			eqSelf.Add(equipment);
			eqVictim.Remove(equipment.GetEffectName());
			victim.GetEffectCollection().Remove(equipment.GetEffectName());
			effectCollection.Add(equipment,this);
			Prototype.logger("Stole " + equipment + " from Virologist " + victim, Prototype.GetLogFile());
			}
		else
			Prototype.logger("Failed, virologist "+victim+" is not paralyzed ", Prototype.GetLogFile());
	}
	
	/**
	* Removes Materials from the Virologists (in the parameter) MaterialCollection and adds it to its own (checks if v is paralyzed) 
	* @param victim - A Virologist on our Field
	**/
	public void StealMaterial(Virologist victim) {
		
			MaterialCollection materialColl2 = victim.GetMaterialCollection();
			FillMaterials(materialColl2);
			Prototype.logger("Virologist stole material from"+victim, Prototype.GetLogFile());
	}
	
	
	/**
	* Drops the selected Equipment from the equipmentCollection of the Player to the Field that it is standing on
	* @param eqNum the number of the equipment which should be dropped
	**/
	public void DropEquipment(Equipment equipment) {
		List<Equipment> equipments = this.equipmentCollection.GetEquipments();
		
		field.Accept(equipment);
		effectCollection.Remove(equipment.GetEffectName());
		equipmentCollection.Remove(equipment.GetEffectName());
		
		Prototype.logger("Dropped "+ equipment.toString(), Prototype.GetLogFile());
			
	}
	
	/**
	* Picks up the selected Equipment from the Field the Player is standing on
	* @param eqNum the number of the equipment which should be picked up
	**/
	public void PickUpEquipment(Equipment equipment) {
		//if the player's inventory if full, the method returns
		if(this.equipmentCollection.GetEquipments().size()>=3) {
			Prototype.logger("Failed, virologist's item collection is full", Prototype.GetLogFile());
			return;
		}
		//picking up the equipment given as parameter
		else {
			if(equipmentCollection.GetSize() < 3) {
				equipmentCollection.Add(equipment);
				Prototype.logger("Picked up "+equipment.GetEffectName(), Prototype.GetLogFile());
				effectCollection.Add(equipment, this);
				field.Remove(equipment);
			}
		}
	}
	
	/**
	* The method chooses a random neighbour of the Field that the Virologist is standing on.
	* Calls the MoveTo(Field f) method.
	**/
	public void RandomField() {
		List<Field> neighbours = field.GetNeighbours();
		Field field = null;
		//if we have randomness turned on, the virologist will move to a randomly selected neighboring field
		if(Game.isRandom()) {
			Random rand = new Random();
			int numberOfSelectedField = rand.nextInt(neighbours.size());
			field = neighbours.get(numberOfSelectedField);
		}
		//if we have randomness turned off, the player will move to the field's first neighbour
		else {
			field = neighbours.get(0);
		}
		
		
		MoveTo(field);
	}
	
	/**
	* Deletes all the Agents from the genCodeCollection.
	**/
	public void DeleteLearntAgent() {
		genCodeCollection.ClearAll();
	}
	
	/**
	* The method fills the Virologists Material amounts from another MaterialCollection
	* @param m - A Warehouse or another Virologist MaterialCollection
	**/
	public void FillMaterials(MaterialCollection m) {
		//the aminoacid and nucleotid of the victim
		AminoAcid victimAmino = m.GetAmino();
		Nucleotid victimNucle = m.GetNucle();
		int victimAminoAmount = victimAmino.amount;
		int victimNucleAmount = victimNucle.amount;
		
		//the aminoacid and nucleotid of the thief
		AminoAcid Amino = materialCollection.GetAmino();
		Nucleotid Nucle = materialCollection.GetNucle();

		//determines the amount of aminoacid the virologist can pick up, before reaching his limit
		int fillAminoWithAmount = maxAmino - Amino.amount;
		
		//determines the amount of nucleotid the virologist can pick up, before reaching his limit
		int fillNucleWithAmount = maxNucle - Nucle.amount;
		
		//if the victim has less aminoacid, than the thief's carrying capacity, the thief takes everything from him
		if(victimAminoAmount <= fillAminoWithAmount) {
			victimAminoAmount -= victimAminoAmount;
			Amino.amount += victimAminoAmount;
		}
		
		//if the victim has more aminoacid, than the thief's carrying capacity, the thief takes the most he can carry
		else if(victimAminoAmount > fillAminoWithAmount) {
			victimAminoAmount -= fillAminoWithAmount;
			Amino.amount += fillAminoWithAmount;
		}
		
		//if the victim has less nucleotid, than the thief's carrying capacity, the thief takes everything from him
		if(victimNucleAmount <= fillNucleWithAmount) {
			victimNucle.amount -= victimNucleAmount;
			Nucle.amount += victimNucleAmount;
		}
		
		//if the victim has more nucleotid, than the thief's carrying capacity, the thief takes the most he can carry
		else if(victimNucleAmount > fillNucleWithAmount) {
			victimNucleAmount -= fillNucleWithAmount;
			Nucle.amount += fillNucleWithAmount;
		}
		
		//saving the changes in the victim's and the thief's materialcollections
		m.SetAmino(new AminoAcid(victimAminoAmount));
		m.SetNucle(new Nucleotid(victimNucleAmount));
		materialCollection.SetAmino(Amino);
		materialCollection.SetNucle(Nucle);
	}
	
	/**
	 * Anoints the Virologist given in the parameters with bear dance. Gets an instance of a cloak which is being held by the victim
	 * then applies BearDance to the virologist if the conditions are correct.
	 * @param v2 The virologist which will be anointed with bear dance.
	 */
	public void BearDanceAnoint(Virologist v2) {
		//by default, we presume, that the victim we want to anoint, doesn't have a cloak
		Cloak eq = null;
		boolean containsCloak = false;
		
		//iterating through the victim's equipments, if we find a cloak, we change the boolean to true
		for(Equipment item: v2.GetEquipmentCollection().GetEquipments()) {
			if(item.toString().contains("Cloak") && !containsCloak) {
				containsCloak = true;
				eq = (Cloak)item;
			}
		}
		//checking whether the victim is protected, and whether his cloak works, or not
		boolean isVictimProtected = v2.GetEffectCollection().Contains("Protect");
		boolean haveCloak = v2.GetEffectCollection().Contains("Cloak");
		if(haveCloak) {
			boolean protectingCloak = eq.Chance();//checking, if the virologist was lucky
			boolean hasBearDanceAlready = v2.GetEffectCollection().Contains("BearDance");

			//if the victim isn't protected, and his cloak doesn't work, and he isn't a bear
			//we anoint him with beardance
			if(!(isVictimProtected && protectingCloak && hasBearDanceAlready)) {
				Agent bearDance = new BearDance();
				v2.effectCollection.Add(bearDance, v2);
				v2.bear=true;
			}
		}
	}
	
	/**
	* The method is called at the start of each turn and it calls every Effect from the effectCollection
	**/
	public void CallAffectWithAll() {
		this.GetEffectCollection().AffectWithAll(this);
	}
	
	/**
	 * Decreases the time of both the crafted agents the player has, and
	 * the effects that are currently applied to him.
	 */
	public void CallDecreaseAgentTime() {
		craftedAgentCollection.DecreaseAgentTimeAColl(this);
		effectCollection.DecreaseAgentTimeEColl(this);
	}
	
	/**
	 * removes the agent given as parameter from the effectCollection
	 * @param a the agent we want to remove
	 */
	public void RemoveAgentFromAgentColl(Agent a) {
		effectCollection.Remove(a.GetEffectName());
	}
	
	/**
	* Default ToString method for console printout.
	* @return String with every attribute of the Virologist
	**/
	@Override
	public String toString() {
		return "Virologist";//+ID;
	}
	
	/**
	* Default ToString method for console printout.
	* @return String with every attribute of the Virologist
	**/
	public String VirologistStat() {
		String stat="";
		stat+=this.toString();
		stat+=System.lineSeparator();
		stat+=this.GetMaterialCollection().GetAmino().ToString();
		stat+=System.lineSeparator();
		stat+=this.GetMaterialCollection().GetNucle().ToString();
		stat+=System.lineSeparator();
		stat+="Equipments: "+System.lineSeparator();
		stat+=this.GetEquipmentCollection().toString();
		stat+="Effects: "+System.lineSeparator();
		stat+=this.GetEffectCollection().toString();
		stat+="Learnt Gencodes: "+System.lineSeparator();
		stat+=this.GetGenCodeCollection().toString();
		stat+="Crafted Agents: "+System.lineSeparator();
		stat+=this.GetCraftedACollection().toString();
		return stat;
	}
	
	
	/**
	* Getter to the materialCollection
	* @return materialCollection
	**/
	public MaterialCollection GetMaterialCollection() {
		return materialCollection;
	}
	
	/**
	* Getter to the equipmentCollection
	* @return equipmentCollection
	**/
	public EquipmentCollection GetEquipmentCollection() {
		return equipmentCollection;
	}
	
	/**
	* Getter to the effectCollection
	* @return effectCollection
	**/
	public EffectCollection GetEffectCollection() {
		return effectCollection;
	}
	
	/**
	 * getter, returns the gencodecollection of the virologist
	 * @return collection of genetic codes learnt
	 */
	public AgentCollection GetGenCodeCollection() {
		return genCodeCollection;
	}
	
	/**
	 * getter, returns the craftedagentcollection of the virologist
	 * @return craftedAgentCollection
	 */
	public AgentCollection GetCraftedACollection() {
		return craftedAgentCollection;
	}
	
	/**
	 * Setting the alive attribute to false
	 */
	public void setDead() {
		alive = false;
	}
	
	/**
	 * Gives TRUE back if the virologist is alive
	 * @return TRUE if the virologist is alive
	 */
	public boolean IsAlive() {
		return alive;
	}
	
	/**
	 * Returns the id of the virologist
	 * @return ID
	 */
	public int GetId() {
		return ID;
	}
	
	/**
	 * Kills the bear by invoking the setDead() method (setting his alive boolean to false).
	 * @param v - the virologist who's become bear
	 */
	public void KillTheBear(Virologist v) {
		v.setDead();
	}
	
	/**
	 * Kills the victim, if he is alive, and he is anointed with the beardance virus.
	 * @param victim
	 */
	public void Attack(Virologist victim) {
		
		/**Checking for the bear and being alive*/
		if(victim.isBear() && victim.IsAlive())
		{
			/**the collection of axes*/
			ArrayList<Axe> axes=new ArrayList<Axe>();
			for(Equipment e: this.GetEquipmentCollection().GetEquipments()) {
				if(e.GetEffectName().equals("Axe")) {
					axes.add((Axe) e);
				}
			}
			/**The first axe of the collection kills the bear and gets used, then the function returns.*/
			for(Axe a : axes)
			{
				if(a.GetUseTime()>0)
				{
					/**KILL THE BEAR*/
					KillTheBear(victim);
					
					Prototype.logger("Virologist killed", Prototype.GetLogFile());
					
					/**Axe goes to the trash*/
					a.DecreaseUseTime();
					return;
				}
			}
		}
	}

	/**
	 * If the Virologist got the BearDance virus, this function returns true.
	 * @return bear, the boolean that stores if the virologist is a bear
	 */
	public boolean isBear() {
		return bear;
	}
}

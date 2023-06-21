package src;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * AgentCollection
 * Stores Agents which the Virologist crafted, or the genetic Codes (also represented by Agent class) he has learnt.
 * @author csizm
 *
 */
public class AgentCollection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7277215157745347778L;
	
	/** heterogenous collection of the different child classes of Agent*/
	private ArrayList<Agent> agents;
	
	/**
	 * default constructor
	 */
	public AgentCollection() {
		agents = new ArrayList<Agent>();
	}
	
	/**
	 * Adds the Agent given as argument to the collection.
	 * @param a the Agent we are adding to the collection
	 */
	public void Add(Agent a) {
		agents.add(a);
	}
	
	/**
	 * Returns all of the Agents, so the player can choose which one he wants to use.
	 */
	public ArrayList<Agent> ListAll() {
		return agents;
	}
	
	/**
	 * Returns how many Agents are in the collection
	 * @return the number of Agents
	 */
	public int GetSize() {
		return agents.size();
	}
	
	/**
	 * Decreases the expireTime of each of the Agents.
	 * @param v the Virologist, who is the owner of the collection
	 */
	public void DecreaseAgentTimeAColl(Virologist v) {
		for(int i=0;i<agents.size();i++) {
			agents.get(i).DecreaseExpireTime(v);
		}			
	}
	
	/**
	 * Removes the Agent given as parameter from the collection.
	 * @param a the name of the agent which needs to be removed
	 */
	public void Remove(String a) {
		for(int i=0; i< agents.size(); i++) {
			if(agents.get(i).toString().contains(a)) {
				agents.remove(i);
				return;
			}
		}
	}
	
	/**
	 * removes every agent from the collection
	 */
	public void ClearAll() {
		agents.clear();
	}
	
	/**
	 * Checks whether a genetic code is part of the collection or not. If it is, the function returns true.
	 * @param s the name of the Agent we want to check
	 * @return true, if the agent given as parameter is found in the collection
	 */
	public boolean Contains(String s) {
		for(int i=0; i< agents.size(); i++) {
			if(agents.get(i).GetEffectName().equals(s))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns the crated agents a virologist has
	 * @return the list of agents crafted by the virologist (and still usable)
	 */
	public ArrayList<Agent> GetAgents(){
		return agents;
	}
	
	/**
	 * Returns the agents know or has been crafted by the virologist
	 * @return agents
	 */
	public String toString() {
		String agent="";
		for(Agent a : agents) {
			agent=a.toString()+System.lineSeparator();
		}
		return agent;
	}
}


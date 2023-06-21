package src;

import java.io.Serializable;
/**
 * Agent
 * Thing- base class
 * Implements Effect interface.
 * This class represents the basic attributes of Agents (such as Chorea, Protect, etc.).
 * @author csizm
 *
 */
public abstract class Agent extends Thing implements Effect, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2367437871696091145L;

	/**The number of remaining rounds, before the agent expires.	 */
	protected int expireTime;
	
	/**The number of remaining rounds, before the effect of the agent is deleted */
	protected int effectTime;
	
	/**how much aminoacid is required to craft the agent*/
	protected int costAmino;
	
	/**how much nucleotid is required to craft the agent*/
	protected int costNucle;
	
	/**
	 * An abstract method which the child classes redefine to apply an effect on a virologist.
	 * @param v Virologist the agent is affecting
	 */
	public abstract void Affect(Virologist v);
	
	/**
	 * Returns the name of the Agent.
	 * @return name of the Agent (e.g. Chorea)
	 */
	public abstract String GetEffectName();
	
	/**
	 * Lists all the attributes of the agent in a string.
	 */
	@Override
	public String toString() {
		return "";
	}
	
	/**
	 * Decreases the attribute expireTime by one. This function is called in each round. If the remaining time is 0, it removes the Agent from AgentCollection.
	 * @param v Virologist that has the crafted agent
	 */
	public void DecreaseExpireTime(Virologist v) {
		expireTime--;
	}
	
	/**
	 * Decreases the attribute effectTime by one. This function is called in each round. If the remaining time is 0, it removes the Agent from EffectCollection.
	 * @param v Virologist that the effect of the agent has been applied to
	 */
	public void DecreaseEffectTime(Virologist v) {
		expireTime--;
		if(expireTime == 0) {
			
			v.RemoveAgentFromAgentColl(this);
		}
		
	}
	
	/**
	 * Returns how much aminoacid is needed to craft the agent.
	 * @return the amount of aminoacid required
	 */
	public int GetCostAmino() {
		return costAmino;
	}
	
	/**
	 * Returns how much nucleotid is needed to craft the agent.
	 * @return the amount of nucleotid required
	 */
	public int GetCostNucle() {
		return costNucle;
	}
	
	/**
	 * Returns how many rounds need to pass before the agent expires.
	 * @return the number of rounds
	 */
	public int GetExpireTime() {
		return expireTime;
	}
	
}

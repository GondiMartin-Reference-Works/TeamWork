package src;


/**
* Amnesia
* Inherited from Agent
* Stores the required amount of nucleotid and aminoacid to craft the agent in addition the time till this agent affect someone and it expires
* This class represents the 'Amnesia' agent and it's attributes. Amnesia is a type of vaccine the Virologist can use to make the target forget all of their learnt Genetic Codes(reffered to as GenCode from now on).
**/

public class Amnesia extends Agent
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8698849677626179071L;

	public Amnesia() {
		this.expireTime = 0;
		this.effectTime = 2;
		this.costAmino = 1;
		this.costNucle = 0;
	}
	
	/**
	* This method has an effect on the Virologist it is applied, making him forget every GenCode he has learnt so far
	* @param v - v is the Virologist, the agent is affecting
	**/
	public void Affect(Virologist v)
	{
		v.DeleteLearntAgent();
	}
	
	/**
	* Gives back the name of the agent
	* @return name - the name of the agent(here it's "Amnesia")
	**/
	public String GetEffectName()
	{
		return "Amnesia";
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
	* Gives back all the attributes in string
	* @return a string stating the parameters of the class
	**/
	
	@Override
	public String toString()
	{
		return  GetEffectName() + " amino cost:" + costAmino +", nucleo cost:" + costNucle + ", expire time:" + expireTime + ", effect time:" + effectTime;
	}
}

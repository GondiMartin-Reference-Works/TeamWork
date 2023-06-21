package src;


/**
* Protect
* Inherited from Agent
* Stores the required amount of nucleotid and aminoacid to craft the agent in addition the time till this agent affect someone and it expires
* This class represents the 'Protect' agent and it's attributes. Protect is a type of vaccine the virologist can use to build up a resistance against other type of vaccines. It's effect wears of after the expireTime is zero.
**/

public class Protect extends Agent
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8239659463195653254L;

	/**Stating how long the agent can be used*/
	private int expireTime;
	
	/**Stating how long the agent has an effect on the target*/
	private int effectTime;
	
	/**Stating how much aminoacid the agent requires to be crafted*/
	private int costAmino;
	
	/**Stating how much nukleoid the agent requires to be crafted*/
	private int costNucle;
	
	/**
	 * default constructor
	 */
	public Protect() {
		expireTime=6;
		effectTime=6;
		costAmino=5;
		costNucle=4;
	}

	
	/**
	* This method has an effect on the Virologist it is applied, making him move to random fields for a sort period of turns
	* @param v - v is the Virologist, the agent is affecting
	**/
	public void Affect(Virologist v)
	{
		
	}
	
	/**
	* Gives back the name of the agent
	* @return name - the name of the agent(here it's "Protect")
	**/
	public String GetEffectName()
	{
		return "Protect";
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
}

package src;


/**
* Paralyze
* Inherited from Agent
* Stores the required amount of nucleotid and aminoacid to craft the agent in addition the time till this agent affect someone and it expires
* This class represents the 'Paralyze' agent and it's attributes. Paralyze is a type of vaccine the Virologist can use to make the target unable to move and do anything. The effect wears of when the expire time is zero.
**/

public class Paralyze extends Agent
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1174265519484897795L;

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
	public Paralyze() {
		expireTime = 3;
		effectTime = 3;
		costAmino = 2;
		costNucle = 1;
	}
	
	/**
	* This method has an effect on the Virologist it is applied, stopping him from moving
	* @param v - v is the Virologist, the agent is affecting
	**/
	public void Affect(Virologist v)
	{
		
	}
	
	/**
	* Gives back the name of the agent
	* @return name - the name of the agent(here it's "Paralyze")
	**/
	public String GetEffectName()
	{
		return "Paralyze";
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

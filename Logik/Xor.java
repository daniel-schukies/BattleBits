package Logik;

/**
 * Logik des XORs
 * @author Daniel Schukies, Sebasian Junger
 *
 */
public class Xor extends Logikgatter
{

	public Xor()
	{
		super();
	}

		
	/** @return boolean gibt true zurueck, wenn der vorgegebene Ausgang erreicht wurde */
	public boolean pruefeAusgang(boolean eingangszustand1, boolean eingangszustand2)
	{
		if ( (eingangszustand1 ^ eingangszustand2) != this.getAusgang() )
		{
			return false;
		} else
		{
			return true;
		}
	}
	/**
	 * @return String Name des Gatters als String
	 */
	public String toString()
	{
		return "Xor";
	}

	/**
	 * Gibt die ID des Gatters zurueck
	 */

	@Override
	public int getID() 
	{
		return 5;
	}
}

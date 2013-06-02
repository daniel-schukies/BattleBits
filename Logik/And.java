package Logik;

/**
 * Logik des ANDs
 * @author Daniel Schukies, Sebasian Junger
 *
 */
public class And extends Logikgatter implements Legbar
{
	//Eindeutige ID zur identifizierung des Gatters
	private final static int ID = 0;
	
	public And()
	{
		super();
	}

	/** @return boolean gibt true zurueck, wenn der vorgegebene Ausgang erreicht wurde */	
	public boolean pruefeAusgang(boolean eingangszustand1, boolean eingangszustand2)
	{
		if ( (eingangszustand1 & eingangszustand2) != this.getAusgang() )
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
		return "And";
	}

	/**
	 * Gibt die ID des Gatters zurueck
	 */
	@Override
	public int getID() 
	{
		return And.ID;
	}
}

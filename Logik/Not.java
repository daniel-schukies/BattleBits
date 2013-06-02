package Logik;

/**
 * Logik des NOT
 * @author Daniel Schukies, Sebasian Junger
 *
 */
public class Not extends Logikgatter
{
	//Eindeutige ID, um das Gatter wiederzuerkennen
	private final static int ID = 4;
	
	public Not(){
	}
	
	/**
	 * @return String Name des Gatters als String
	 */
	public String toString()
	{
		return "Not";
	}


	@Override
	/**
	 * Gibt die ID des Gatters zurueck
	 */
	public int getID() 
	{
		return Not.ID;
	}
}

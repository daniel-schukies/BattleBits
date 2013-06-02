package Logik;

/**
 * Logik des NANDs
 * @author Daniel Schukies, Sebasian Junger
 *
 */
public class Nand extends Logikgatter
{
	//Eindeutige ID, um das Gatter wiederzuerkennen
	private final static int ID = 1;

	public Nand()
	{
		super();
	}

		
	/** @return boolean gibt true zurueck, wenn der vorgegebene Ausgang erreicht wurde */
	
	public boolean pruefeAusgang(boolean eingangszustand1, boolean eingangszustand2){
		if ( !(eingangszustand1 & eingangszustand2) != this.getAusgang() ){
			return false;
		} else
		{
			return true;
		}
	}
	/**
	 * @return String Name des Gatters als String
	 */
	public String toString(){
		return "Nand";
	}

		/**
		 * Gibt die ID des Gatters zurueck
		 */
		@Override
		public int getID() 
		{
			return Nand.ID;
		}
	
}

package Logik;

public class Nor extends Logikgatter
{
	private final static int ID = 3;

	public Nor()	
	{
		super();
	}

		
	/** @return boolean gibt true zurueck, wenn der vorgegebene Ausgang erreicht wurde */
	public boolean pruefeAusgang(boolean eingangszustand1, boolean eingangszustand2)
	{
		if ( (!(eingangszustand1 | eingangszustand2)) != this.getAusgang() )
		{
			return false;
		} else
		{
			return true;
		}
	}
	
	public String toString()
	{
		return "Nor";
	}


	@Override
	public int getID() 
	{
		return Nor.ID;
	}
}

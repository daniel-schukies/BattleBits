package Logik;

public class And extends Logikgatter implements Legbar
{

	public And()
	{
		super();
	}

	/** @return boolean gibt true zurueck, wenn der vorgegebene Ausgang erreicht wurde */	
	public boolean pruefeAusgang(boolean eingangszustand1, boolean eingangszustand2)
	{
		if ( eingangszustand1 & eingangszustand2 != this.getAusgang() )
		{
			return false;
		} else
		{
			return true;
		}
	}
	
	public String toString()
	{
		return "And";
	}


	@Override
	public int getID() 
	{
		return 0;
	}
}

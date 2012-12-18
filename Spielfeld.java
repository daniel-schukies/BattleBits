import java.util.ArrayList;

public class Spielfeld 
{
	private ArrayList logikgatter;
	
	public Spielfeld()
	{
		
	}
	
	public void setLogikgatter(int reihe, int index, Logikgatter logikgatter)
	{
		if(this.pruefeReiheIndex( reihe, index))
		{
			this.logikgatter.set((reihe+index), logikgatter);
		}
		else
		{
			// needs error report
		}
	}
	
	public Object getLogikgatter(int reihe, int index)
	{
		if(this.pruefeReiheIndex( reihe, index))
		{
			if(this.logikgatter.get(reihe+index) != null)
			{
				return this.logikgatter.get((reihe+index));
			}
			else
			{
				//needs error report
			}
		}
		else
		{
			// needs error report
		}
		return this.logikgatter.get((reihe+index)); // Sollte weg.
	}
	
	
	/*
	 * prüft ob Reihe und Index im Normalbereich sind.
	 * Wenn dies der Fall ist wird true returned.
	 */
	
	public boolean pruefeReiheIndex(int reihe, int index)
	{
		if((reihe == 3 && index == 1) || (reihe == 2 && (index <= 2 && index > 0)) || (reihe == 1 && (index <= 4 && index > 0)) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

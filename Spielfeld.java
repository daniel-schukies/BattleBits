import java.util.ArrayList;

public class Spielfeld 
{
	private ArrayList<Logikgatter> logikgatter;
	
	/**
	 * Legt die ArrayList an für die Logikgatter an.
	 */
	public Spielfeld()
	{
		this.logikgatter = new ArrayList<Logikgatter>();
	}
	
	
	/**
	 * Setzt ein Logikgatter an einen bestimmten Platz und Index.
	 * @param reihe
	 * @param index
	 * @param logikgatter
	 * @return Wenn false zurückgegeben wird wurde kein Logikgatter gesetzt.
	 */
	
	public boolean setLogikgatter(int reihe, int index, Logikgatter logikgatter)
	{
		if(this.pruefeReiheIndex( reihe, index))
		{
			this.logikgatter.set((reihe+index), logikgatter);
			return true; // Wurde gesetzt
		}
		else
		{
			return false; // Wurde nicht gesetzt
		}
	}
	
	
	/**
	 * Gibt ein Logikgatter an einem bestimmten Platz und Index zurück.
	 * @param reihe
	 * @param index
	 * @return Ein Logikgatter
	 */
	
	public Logikgatter getLogikgatter(int reihe, int index)
	{
		if(this.pruefeReiheIndex( reihe, index))
		{
			if(this.logikgatter.get(reihe+index) != null)
			{
				return this.logikgatter.get((reihe+index));
			}
		}

		return null; // Konnte Logikgatter nicht finden.
	}
	
	
	/**
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

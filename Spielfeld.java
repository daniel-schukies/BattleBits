public class Spielfeld 
{
	public Bitgenerator bitfolge; // Speicher der Bitfolge
	private Logikgatter logikgatter[][];
	
	/**
	 * Legt die ArrayList an fuer die Logikgatter an.
	 */
	public Spielfeld()
	{
		this.logikgatter = new Logikgatter[4][5];
		this.bitfolge = new Bitgenerator(5);
		bitfolge.generate();
	}
	
	
	/**
	 * Setzt ein Logikgatter an einen bestimmten Platz und Index.
	 * @param reihe
	 * @param index
	 * @param logikgatter
	 * @return Wenn false zurueckgegeben wird wurde kein Logikgatter gesetzt.
	 */
	
	public boolean setLogikgatter(int reihe, int index, Logikgatter logikgatter)
	{
		if(this.pruefeReiheIndex( reihe, index))
		{
			/** Bei Reihe 1 wird die Bitfolge als Eingang geprueft */
			if(reihe == 1)
			{
				/** pruefe Gatterlogik */
				if(logikgatter.pruefeAusgang(this.bitfolge.getBit(index), this.bitfolge.getBit(index+1)) )
				{
					this.logikgatter[reihe][index] = logikgatter;
					return true; // Wurde gesetzt.
				}
				else
				{
					return false;
				}
			}
			else
			{
				/** 
				 * Pruefe Gatterplazierung: 
				 * pruefe, ob Platz vorhanden ist und Anschlussgatter vorhanden sind.
				 * */
				
				if( (this.logikgatter[reihe][index] == null) && (this.logikgatter[reihe-1][index] != null && this.logikgatter[reihe-1][index+1] != null) )
				{
					/** pruefe, ob Anschluessgatter aktiv sind. */
					if( this.logikgatter[reihe-1][index].getIsAktiv() && this.logikgatter[reihe-1][index+1].getIsAktiv() )
					{
						/** pruefe Gatterlogik */
						if( logikgatter.pruefeAusgang( this.logikgatter[reihe-1][index].getAusgang(), this.logikgatter[reihe-1][index+1].getAusgang() ) )
						{
							this.logikgatter[reihe][index] = logikgatter;
							return true; // Wurde gesetzt.
						}
							
					}
				}
			}
		}

		return false; // Wurde nicht gesetzt

	}
	
	
	/**
	 * Gibt ein Logikgatter an einem bestimmten Platz und Index zurueck.
	 * @param reihe
	 * @param index
	 * @return Ein Logikgatter
	 */
	
	public Logikgatter getLogikgatter(int reihe, int index)
	{
		if(this.pruefeReiheIndex( reihe, index))
		{
			if(this.logikgatter[reihe][index] != null)
			{
				return this.logikgatter[reihe][index];
			}
		}

		return null; // Konnte Logikgatter nicht finden.
	}
	
	
	/**
	 * prueft ob Reihe und Index im Normalbereich sind.
	 * Wenn dies der Fall ist wird true returned.
	 * @return boolean 
	 */
	
	public boolean pruefeReiheIndex(int reihe, int index)
	{
		if((reihe == 4 && index == 0) || (reihe == 3 && (index < 2 && index >= 0)) || (reihe == 2 && (index < 3 && index >= 0)) || (reihe == 1 && (index < 4 && index >= 0)) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

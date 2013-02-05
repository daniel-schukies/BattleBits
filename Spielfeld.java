public class Spielfeld implements Cloneable
{
	
	private Logikgatter logikgatter[][];
	
	/**
	 * Legt die ArrayList an fuer die Logikgatter an.
	 */
	public Spielfeld()
	{
		this.logikgatter = new Logikgatter[4][5];
	}
	
    public Object clone() 
    {
        Object theClone = null;
        try 
        {
          theClone = super.clone();
        }
        catch(CloneNotSupportedException e) 
        {
        
        }
        return theClone;
      }
	
	
	/**
	 * Setzt ein Logikgatter an einen bestimmten Platz und Index.
	 * @param reihe
	 * @param index
	 * @param logikgatter
	 * @return Wenn false zurueckgegeben wird wurde kein Logikgatter gesetzt.
	 */
	
	public boolean setLogikgatter(int reihe, int index, Logikgatter logikgatter, Bitgenerator bitfolge)
	{
		if(this.pruefeReiheIndex( reihe, index))
		{
			/** Bei Reihe 1 wird die Bitfolge als Eingang geprueft */
			if(reihe == 0)
			{
				/** pruefe Gatterlogik */
				if(logikgatter.pruefeAusgang(bitfolge.getBit(index), bitfolge.getBit(index+1)) && !(logikgatter.equals(null)) ) //pruefe Logik und auf null (ref getLogikgatter() )
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
	
	public boolean pruefeGueltigkeit(Bitgenerator bitfolge)
	{
		for(int reihe = 0; reihe < logikgatter.length; reihe++)
		{
			for(int index = 0; index < logikgatter[0].length; index++)
			{
				if( this.logikgatter[reihe][index] != null ) // pruefe ob ein Logikgatter vorhanden ist. 
				{
					if(reihe == 0) // Wenn Bitfolge als Eingang genutzt werden muss.
					{
						if( (this.logikgatter[reihe][index].pruefeAusgang(bitfolge.getBit(index), bitfolge.getBit(index+1))) ) // Gatter aktivieren, wenn Logik stimmt.
						{
							this.logikgatter[reihe][index].aktivieren();
						}
						else
						{
							this.logikgatter[reihe][index].loesche();
						}
					}
					else
					{
						/** Wenn einer der beiden Eingaenge nicht vorhanden ist, wird das Logikgatter deaktiviert */
						if( this.logikgatter[reihe-1][index] == null ^ this.logikgatter[reihe-1][index+1] == null )
						{
							this.logikgatter[reihe][index].deaktivieren();
						} /** Wenn beide Eingaenge nicht vorhanden sind, wird das Logikgatter geloescht */
						else if(this.logikgatter[reihe-1][index] == null && this.logikgatter[reihe-1][index+1] == null)
						{
							this.logikgatter[reihe][index].loesche();
						}
						else /** Beide Eingaenge sind vorhanden */
						{
							/** pruefe ob Logik stimmt */
							if(  this.logikgatter[reihe][index].pruefeAusgang( this.logikgatter[reihe-1][index].getAusgang() , this.logikgatter[reihe-1][index+1].getAusgang()  ) ) // Gatter aktivieren, wenn Logik stimmt.
							{
								this.logikgatter[reihe][index].aktivieren();
							}
							else
							{
								this.logikgatter[reihe][index].loesche();
							}
						}
					}
				}
			}
		}
		
		return (Boolean) null;
	}
	
	
	/**
	 * prueft ob Reihe und Index im Normalbereich sind.
	 * Wenn dies der Fall ist wird true returned.
	 * @return boolean 
	 */
	
	public boolean pruefeReiheIndex(int reihe, int index)
	{
		if((reihe == 3 && index == 0) || (reihe == 2 && (index < 2 && index >= 0)) || (reihe == 1 && (index < 3 && index >= 0)) || (reihe == 0 && (index < 4 && index >= 0)) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

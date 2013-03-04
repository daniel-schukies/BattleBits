package Logik;

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
	
    public Spielfeld clone() 
    {
        Spielfeld theClone = new Spielfeld();
        
        /** Schreibt Inhalt von dem einen Spielfeld in eine Kopie */
		for(int reihe = 0; reihe < logikgatter.length; reihe++)
		{
			for(int index = 0; index < logikgatter[0].length; index++)
			{
				theClone.setLogikgatter(reihe, index, this.getLogikgatter(reihe, index));
			}
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
				/** pruefe Gatterlogik auf Gueltigkeit und pruefe ob Ablegeplatz leer ist (null) */
				if(logikgatter.pruefeAusgang(bitfolge.getBit(index), bitfolge.getBit(index+1)) && ( (!logikgatter.equals(null)) && ( this.logikgatter[reihe][index] == null )) ) //pruefe Logik und auf null (ref getLogikgatter() )
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
				
				if( (this.logikgatter[reihe][index] == null) && ( ( (this.logikgatter[reihe-1][index] != null) && (this.logikgatter[reihe-1][index+1] != null) ) ))
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
	
	public void setLogikgatter(int reihe, int index, Logikgatter logikgatter)
	{
		try
		{
			this.logikgatter[reihe][index] = logikgatter; 
		}
		catch(Exception e)
		{
			
		}
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
	 * Prueft alle Logikgatter auf dem Spielfeld auf Gueltigkeit der Logik
	 * @param bitfolge aktuelle Bitfolge
	 * @return Anzahl der ungueltigen Logikgatter auf dem Spielfeld
	 */
	public int pruefeGueltigkeit(Bitgenerator bitfolge)
	{
		int anzahlUngueltigeLogikgatter = 0;
		
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
							this.logikgatter[reihe][index].loesche(); // Macht Logikgatter ungueltig.
							anzahlUngueltigeLogikgatter++;
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
							anzahlUngueltigeLogikgatter++;
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
								anzahlUngueltigeLogikgatter++;
							}
						}
					}
				}
			}
		}
		
		return anzahlUngueltigeLogikgatter;
	}
	
	public void entferneUngueltigeLogikgatter(Bitgenerator bitfolge)
	{
		this.pruefeGueltigkeit(bitfolge);
		
		for(int reihe = 0; reihe < logikgatter.length; reihe++)
		{
			for(int index = 0; index < logikgatter[0].length; index++)
			{
				if( this.logikgatter[reihe][index] != null ) // pruefe ob ein Logikgatter vorhanden ist. 
				{
					if( !(this.logikgatter[reihe][index].getIsGueltig()) ) // pruefe, ob Gatter ungueltig ist.
					{
						this.logikgatter[reihe][index] = null; // loesche Logikgatter
						System.out.println("Es wurde ein Gatter gel�scht");
					}
				}
			}
		}
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

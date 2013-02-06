
public class Spieler 
{
	private String name;
	private boolean isDran;
	private boolean isKI;
	private Logikgatter[] logikgatter;
	
	public Spieler(String name, boolean isKI)
	{
		this.setName(name);
		this.setIsKI(isKI);
		logikgatter = new Logikgatter[4];
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setIsDran(boolean isDran)
	{
		this.isDran = isDran;
	}
	
	public boolean getIsDran()
	{
		return this.isDran;
	}
	
	public void setIsKI(boolean ki)
	{
		this.isKI = ki;
	}
	
	public boolean getIsKI()
	{
		return this.isKI;
	}
	
	public boolean loescheLogikgatter(int index)
	{
		if(this.logikgatter[index] == null)
		{
			return false;
		}
		else
		{
			this.logikgatter[index] = null;
			return true;
		}
	}
	
	public boolean gebeLogikgatter(Logikgatter logikgatter)
	{
		
		for(int i = 0; i < this.logikgatter.length; i++)
		{
			if(this.logikgatter[i] == null)
			{
				this.logikgatter[i] = logikgatter;
				return true;
			}
		}
		return false;
	}
	
	public Logikgatter getLogikgatter(int index)
	{
		if(index >= 0 && index < this.logikgatter.length)
		{
			return this.logikgatter[index]; // ACHTUNG NULLPOINTER ;)
		}
		
		return null; // Existiert nicht
	}
	
	/**
	 * Teilt an den Spieler seine ersten Gatter aus.
	 * @param spieler
	 */
	public void generiereLogikgatter()
	{
		/** Gebe Spieler die Logikgatter */
		Logikgattergenerator lg = new Logikgattergenerator();
		
		while(this.getLogikgatter(3) == null)
		{
			lg.generate();
			this.gebeLogikgatter(lg.getLogikgatter());
		}
	}
	
	/**
	 * Dem Spieler wird ein altes Logikgatter gegen ein Neues getauscht.
	 * @param gatterIndex Welches Logikgatter getauscht werden soll.
	 */
	public void zieheNeuesLogikgatter(int gatterIndex)
	{
		Logikgattergenerator lg = new Logikgattergenerator();
		
		this.loescheLogikgatter(gatterIndex);
		lg.generate();
		this.gebeLogikgatter(lg.getLogikgatter());
	}
	/**
	 * 
	 * @param eigenesSpielfeld
	 * @param gegnerSpielfeld
	 * @param bitfolge
	 */
	public void spieleAlsKI(Spielfeld eigenesSpielfeld, Spielfeld gegnerSpielfeld, Bitgenerator bitfolge)
	{
		int indexVerwendetesGatter = 404; // Error: 404 Gatter not found ;)
		
		int notSchadenBeimGegner[] = new int[5];
		int notSchadenBeimSpieler[] = new int[5];

		/** Lege Kopien der Spielfelder und des Bitgenerators an */
		Spielfeld kopieEigenesSpielfeld = (Spielfeld)eigenesSpielfeld.clone();
		Spielfeld kopieGegnerSpielfeld = (Spielfeld)gegnerSpielfeld.clone();
		Bitgenerator kopieBitfolge = (Bitgenerator)bitfolge.clone();
		
		
		boolean nutzeNot = false; // Ob Not gesetzt wird.
		int bitMitMeistemSchaden = 0; // Das Bit, welches wenn es invertiert wird, den meisten Schaden anrichtet.
		
		for(int i = 0; i < this.logikgatter.length; i++)
		{
			/** pruefe, ob Spieler ein NOT besitzt */
			if(this.logikgatter[i] instanceof Not )
			{
				
				/**  Durchlaufe alle Bits und schreibe den Schaden derer in das Array  */
				for(int bitCnt = 0; bitCnt < 4; bitCnt++)
				{
					kopieBitfolge.invertBit(bitCnt);
					
					/** Schreibt die Anzahl der ungueltigen Logikgatter in das Array an den Index des invertierten Bits */
					notSchadenBeimSpieler[bitCnt] = kopieEigenesSpielfeld.pruefeGueltigkeit(kopieBitfolge);
					notSchadenBeimGegner[bitCnt] = kopieGegnerSpielfeld.pruefeGueltigkeit(kopieBitfolge);
					
					/** Setze Kopien der Spielfelder durch erneutes invertieren des Bits auf den Ursprungszustand zurueck */
					kopieBitfolge.invertBit(bitCnt);
					kopieEigenesSpielfeld.pruefeGueltigkeit(kopieBitfolge);
					kopieGegnerSpielfeld.pruefeGueltigkeit(kopieBitfolge);
				}
				
				/**  Bestimmt das Bit mit dem meisten Schaden */
				for(int bitCnt = 0; bitCnt < 4; bitCnt++)
				{
					if( notSchadenBeimSpieler[bitCnt] < notSchadenBeimGegner[bitCnt] )
					{
						if(notSchadenBeimGegner[bitMitMeistemSchaden] < notSchadenBeimGegner[bitCnt])
						{
							bitMitMeistemSchaden = bitCnt;
						}
					}
				}
				
				/** Entscheide ob NOT zu nutzen ist */
				if( notSchadenBeimSpieler[bitMitMeistemSchaden] > notSchadenBeimGegner[bitMitMeistemSchaden] )
				{
					nutzeNot = false;
				}
				else
				{
					nutzeNot = true; 
					indexVerwendetesGatter = i;
				}
				
				break; // The End ;)
			}
			
			nutzeNot = false; // Spieler besitzt kein NOT
		}
		
		if(nutzeNot)
		{
			bitfolge.invertBit(bitMitMeistemSchaden);
			this.zieheNeuesLogikgatter(indexVerwendetesGatter);
		}
		else // Anderes Gatter muss gespielt werden.
		{
			
		}

		
		//if(indexVerwendetesGatter != 404) // pruefe, ob ein Gatter gespielt werden soll
	}
	
	

}

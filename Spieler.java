
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
	
	public void spieleAlsKI(Spielfeld eigenesSpielfeld, Spielfeld gegnerSpielfeld, Bitgenerator bitfolge)
	{
		boolean nutzeNot;
		
		int notSchadenBeimGegner[] = new int[5];
		int notSchadenBeimSpieler[] = new int[5];

		Spielfeld kopieGegnerSpielfeld = (Spielfeld)gegnerSpielfeld.clone();

		/** pruefe, ob Spieler ein NOT besitzt */
		for(int i = 0; i < this.logikgatter.length; i++)
		{
			if(this.logikgatter[i] instanceof Not )
			{
				nutzeNot = true;
				break;
			}
		}
	}
	
	

}

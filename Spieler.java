
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
		return this.logikgatter[index]; // ACHTUNG NULLPOINTER ;)
	}
	
	

}

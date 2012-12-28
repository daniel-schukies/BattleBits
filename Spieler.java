import java.util.ArrayList;


public class Spieler 
{
	private String name;
	private boolean isDran;
	private boolean isKI;
	private ArrayList<Logikgatter> logikgatter;
	
	public Spieler(String name, boolean isKI)
	{
		this.setName(name);
		this.setIsKI(isKI);
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
	
	public boolean gebeLogikgatter(Logikgatter logikgatter)
	{
		if(this.logikgatter.size() < 4)
		{
			this.logikgatter.add(logikgatter);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Logikgatter getLogikgatter(int index)
	{
		return this.logikgatter.get(index);
	}
	
	

}

package Logik;

public class Not extends Logikgatter
{
	private final static int ID = 4;
	
	public Not(){
	}
	
	
	public String toString()
	{
		return "Not";
	}


	@Override
	public int getID() 
	{
		return Not.ID;
	}
}

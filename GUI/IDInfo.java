package GUI;

public class IDInfo 
{
	private int ID;
	private boolean isPressed;
	
	public IDInfo(int ID)
	{
		this.ID = ID;
		this.isPressed = true;
	}
	
	public IDInfo()
	{
		this.ID = 0;
		this.isPressed = false;
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public boolean getIsPressed()
	{
		return this.isPressed;
	}
}

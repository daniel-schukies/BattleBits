
public class And extends Logikgatter
{

	public And(int index1, boolean eingangszustand1, int index2,boolean eingangszustand2) 
	{
		super(index1, eingangszustand1, index2, eingangszustand2);
		// TODO Auto-generated constructor stub
	}
	
	public boolean getAusgang()
	{
		return super.eingang[0] & super.eingang[1];
	}
	
}

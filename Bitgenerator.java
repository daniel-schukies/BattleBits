import java.util.Random;

public class Bitgenerator 
{
	private boolean[] bitfolge;
	
	public Bitgenerator(int bitanzahl )
	{
		this.bitfolge = new boolean[bitanzahl];
	}
	
	// Erstellt die Bitfolge
	public void generate()
	{
		Random r = new Random();
		
		for(int i = 0; i < this.bitfolge.length; i++ )
		{
			this.bitfolge[i] = r.nextBoolean();
		}
	}
	
	// Gibt ein Bit zurück
	public boolean getBit(int index)
	{
		if(index >= 0 && index < bitfolge.length)
		{
			return this.bitfolge[index];
		}
		
		return false;
		// Error abfangen!
	}
	
	// Man kann ein Bit setzen
	public boolean setBit(int index, boolean bit)
	{
		if(index >= 0 && index < bitfolge.length)
		{
			this.bitfolge[index] = bit;
			return true;
		}
		return false;
	}
}

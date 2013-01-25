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
		if( pruefeIndex(index) )
		{
			return this.bitfolge[index];
		}
		
		return false;
		// Error abfangen!
	}
	
	// Man kann ein Bit setzen
	public boolean setBit(int index, boolean bit)
	{
		if( pruefeIndex(index) )
		{
			this.bitfolge[index] = bit;
			return true;
		}
		return false;
	}
	
	public boolean invertBit(int index)
	{
		if( pruefeIndex(index) )
		{
			this.bitfolge[index] = !this.bitfolge[index];
			return true;
		}
		return false;
	}
	
	private boolean pruefeIndex(int index)
	{
		if(index >= 0 && index < this.bitfolge.length)
		{
			return true;
		}
		return false;
	}
}

package Logik;
import java.util.Random;

public class Bitgenerator implements Cloneable
{
	private boolean[] bitfolge;
	private static Random random;
	
	public Bitgenerator(int bitanzahl )
	{
		this.bitfolge = new boolean[bitanzahl];
		Bitgenerator.random = new Random(Double.doubleToLongBits(Math.random()));
	}
	
    public Object clone() 
    {
        Object theClone = null;
        try 
        {
          theClone = super.clone();
        }
        catch(CloneNotSupportedException e) 
        {
        
        }
        return theClone;
      }
	
	// Erstellt die Bitfolge
	public void generate()
	{
		//r.setSeed( Bitgenerator.SEED );
		
		for(int i = 0; i < this.bitfolge.length; i++ )
		{
			this.bitfolge[i] = Bitgenerator.random.nextBoolean();
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

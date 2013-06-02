package Logik;
import java.util.Random;

/**
 * Generiert die Bitfolge in der Mitte des Spielfeldes
 * @author Daniel Schukies, Sebastian Junger
 *
 */
public class Bitgenerator implements Cloneable
{
	private boolean[] bitfolge;
	private static Random random;
	
	//Erstellt eine Bitfolge mit der Groesse der uebergebenen Bits
	public Bitgenerator(int bitanzahl )
	{
		this.bitfolge = new boolean[bitanzahl];
		Bitgenerator.random = new Random(Double.doubleToLongBits(Math.random()));
	}
	
	/**
	 * Klont die Bitfolge in ein Object
	 */
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
	
	/**
	 * Erstellt eine zufaellige Bitfolge und schreibt sie in das dafuer vorgesehene Array
	 */
	public void generate()
	{
		
		for(int i = 0; i < this.bitfolge.length; i++ )
		{
			this.bitfolge[i] = Bitgenerator.random.nextBoolean();
		}
	}
	
	/**
	 * Gibt das Bit an einem Index zurueck
	 * @param index Index des Bits
	 * @return Zustand des Bits am Index
	 */
	public boolean getBit(int index)
	{
		if( pruefeIndex(index) )
		{
			return this.bitfolge[index];
		}
		
		return false;
		// Error abfangen!
	}
	
	/**
	 * Setzt ein Bit am uebergebenen Index
	 * @param index Index des zu setzenden Bits
	 * @param bit Zustand, auf den gesetzt werden soll
	 * @return Erfolg des setzens
	 */
	public boolean setBit(int index, boolean bit)
	{
		if( pruefeIndex(index) )
		{
			this.bitfolge[index] = bit;
			return true;
		}
		return false;
	}
	
	/**
	 * Invertiert das Bit am uebergebenen Index
	 * @param index Index des zu invertierenden Bits
	 * @return Erfolg des Invertierens
	 */
	public boolean invertBit(int index)
	{
		if( pruefeIndex(index) )
		{
			this.bitfolge[index] = !this.bitfolge[index];
			return true;
		}
		return false;
	}
	
	/**
	 * Prueft den uebergebenen Index auf gueltigkeit
	 * @param index Index, der geprueft werden soll
	 * @return Gueltigkeit des Indizes
	 */
	private boolean pruefeIndex(int index)
	{
		if(index >= 0 && index < this.bitfolge.length)
		{
			return true;
		}
		return false;
	}
}

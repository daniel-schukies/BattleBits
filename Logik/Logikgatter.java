package Logik;
import java.util.Random;
abstract public class Logikgatter {
	
	private boolean ausgang; 
	private boolean isAktiv;
	private boolean isGueltig;
	public final static int ANZAHL_LOGIKGATTER = 6;
	
	/**
	 * Im Konstruktor wird ein zu erreichender Ausgangswert festgelegt
	 */
	
	public Logikgatter()
	{
		Random r = new Random();
		
		this.ausgang = r.nextBoolean(); // uebersichtlicher ;)
		this.aktivieren();

	}
	
	public abstract int getID();
	
	public boolean getIsAktiv()
	{
		return this.isAktiv;
	}
	
	public boolean getIsGueltig()
	{
		return this.isGueltig;
	}
	
	public boolean pruefeAusgang(boolean eingangszustand1, boolean eingangszustand2)
	{
		return false;
	}
	
	public boolean getAusgang(){
		return this.ausgang;
	}
	
	public void aktivieren(){
		this.isAktiv = true;
		this.isGueltig = true;
	}
	
	public void deaktivieren(){
		this.isAktiv = false;
	}
	
	public void loesche(){
		this.isGueltig = false;
		this.isAktiv  = false;
	}
	
}


package Logik;
import java.util.Random;

/**
 * Abstrakte Klasse fuer die Logikgatter And, Nand, Or, Not, etc.
 * @author Daniel Schukies, Sebastian Junger
 *
 */
abstract public class Logikgatter 
{
	
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
		
		this.ausgang = r.nextBoolean(); //Ausgangswert, der erreicht werden muss
		//Am Anfang ist das Logikgatter auf jeden Fall aktiv
		this.aktivieren();
	}
	
	/**
	 * Gibt die ID des Logikgatters zurueck
	 * @return ID des Logikgatterss
	 */
	public abstract int getID();
	
	/**
	 * Gibt zurueck, ob das Logikgatter aktiv ist
	 * @return Inhalt der isAktiv - Variable
	 */
	public boolean getIsAktiv()
	{
		return this.isAktiv;
	}
	
	/**
	 * Gibt zurueck, ob ein Logikgatter gueltig ist
	 * @return Gueltigkeit des Logikgatters
	 */
	public boolean getIsGueltig()
	{
		return this.isGueltig;
	}
	
	/**
	 * Prueft, ob der zu erreichende Ausgangszustand erreicht wurde (Logik je nach Logikgatter)
	 * @param eingangszustand1 Erster Eingangszustand
	 * @param eingangszustand2 Zweiter Eingangszustand
	 * @return True, bei erreichen des Ausgangszustandes, sonst false
	 */
	public boolean pruefeAusgang(boolean eingangszustand1, boolean eingangszustand2)
	{
		return false;
	}
	
	/**
	 * Gibt den zu erreichenden Ausgangszustand zurueck
	 * @return zu erreichender Ausgangszustand
	 */
	public boolean getAusgang(){
		return this.ausgang;
	}
	
	/**
	 * Aktiviert das Logikgatter
	 */
	public void aktivieren(){
		this.isAktiv = true;
		this.isGueltig = true;
	}
	
	/**
	 * Deaktiviert ein Logikgatter
	 */
	public void deaktivieren(){
		this.isAktiv = false;
	}
	
	/**
	 * Loescht ein Logikgatter
	 */
	public void loesche(){
		this.isGueltig = false;
		this.isAktiv  = false;
	}
	
}


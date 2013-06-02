/**
 * 
 */
package Logik;

/**
 * Interface fuer Objekte, die Legbar sind
 * @author Daniel Schukies, Sebastian Junger
 *
 */
public interface Legbar 
{
	/**
	 * Prueft die Eingangszustaende auf Einhaltung des Ausgangszustandes
	 * @param eingangszustand1 Eingangszustand1
	 * @param eingangszustand2 Eingangszustand2
	 * @return Gueltigkeit der Eingangszustaende
	 */
	public boolean pruefeAusgang(boolean eingangszustand1, boolean eingangszustand2);
}

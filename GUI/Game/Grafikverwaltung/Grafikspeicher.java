package GUI.Game.Grafikverwaltung;



import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Logik.Logikgatter;

/**
 * Klasse zum Speichern eines Bildes in allen Versionen
 * @author Daniel Schukies, Sebastian Junger
 *
 */
public class Grafikspeicher 
{
	private JLabel button;
	private ImageIcon[] grafiken;
	private ImageCreator imageCreator;
	
	
	/**
	 * Instanziert einen Grafikspeicher fuer ein Bild in angegebener Groesse mit
	 * der Anzahl der uebergebenen Versionen. Der Boolean - Wert definiert, ob ein
	 * Bild gespiegelt werden soll
	 * @param size Groesse des Bildes
	 * @param anzahlVersionen Anzahl der Versionen des Bildes
	 * @param spiegeln Definiert, ob das Bild gespiegelt werden soll
	 */
	public Grafikspeicher(Dimension size, int anzahlVersionen,boolean spiegeln)
	{
		this.imageCreator = new ImageCreator(size, anzahlVersionen, spiegeln);
		this.grafiken = new ImageIcon[anzahlVersionen];
		this.button = new JLabel();
	}

	
	/**
	 * Legt das zu speichernde Bild fest und definiert den aufzuzeichnenden Logikgatterstatus
	 * @param logikgatter Typ des zu speichernden Bildes
	 * @param drawLogikgatterStatus Ausgangszustand des Logikgatters
	 */
	public void setImage(Logikgatter logikgatter, boolean drawLogikgatterStatus)
	{	
		/** Die verschiedenen Versionen der Logikgattergrafiken werden in einem Array in Form von ImageIcons gespeichert */
		this.imageCreator.grafikenVorladen();
		this.grafiken = this.imageCreator.getImage(logikgatter, drawLogikgatterStatus);
		this.button.setIcon(this.grafiken[0]);
	}
	
	/**
	 * Speichert Bilder eines Bits
	 * @param bit Bit dessen Bild gespeichert werden soll
	 */
	public void setImage(boolean bit)
	{
		/** Die verschiedenen Versionen der Bitgrafiken wwerden in einem Array in Form von ImageIcons gespeichert */

		this.grafiken = this.imageCreator.getImage(bit);
		this.button.setIcon(this.grafiken[0]);
	}
	
	/**
	 * Speichert das als Uebergabewert uebergebene Bild
	 * @param dateiname Dateiname des zu speichernden Bildes
	 * @return Erfolg des Erstellens
	 */
	public boolean setImage(String dateiname)
	{
		this.grafiken = this.imageCreator.getImage(dateiname);
		this.button.setIcon(this.grafiken[0]);
		
		return true;
	}
	
	/**
	 * Speichert die Bilder im uebergebenen Array, sofern die Laenge gleich ist
	 * @param grafiken zu speichernde Grafiken
	 * @return Erfolg des Speicherns
	 */
	public boolean setImage(ImageIcon[] grafiken)
	{
		//Die Laenge des uebergebenen Arrays muss gleich sein, wie die des lokalen
		if(this.grafiken.length == grafiken.length)
		{
			this.grafiken = grafiken;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Speichert das erste Bild aus dem ImageCreator
	 */
	public void setImage()
	{
		this.imageCreator.grafikenVorladen();
		this.grafiken = this.imageCreator.getImage();
		this.button.setIcon(this.grafiken[0]);
	}
	
	/**
	 * Setzt die Version des Bildes, die gespeichert werden soll
	 * @param versionID Version des Bildes
	 * @return Erfolg des Setzens der Version
	 */
	public boolean setVersion(int versionID)
	{
		try
		{
			//Wenn Version im gueltigen Bereich liegt
			if(this.grafiken.length > versionID )
			{
				this.button.setIcon(this.grafiken[versionID]);
				return true;
			}
			
		}catch(Exception e)
		{
			// False wird zurueckgegeben
		}
		
		return false; // Return erst hier, da IF-Verzweigung beruecksichtigt wird.
	}
	
	/**
	 * Gibt das aktuell gespeicherte Bild als JLabel zurueck
	 * @return aktuell gespeichertes Bild
	 */
	public JLabel getImage()
	{
		return button;
	}
}

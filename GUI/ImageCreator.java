package GUI;
import java.awt.Image;

import Logik.Logikgatter;


import javax.swing.ImageIcon;

public class ImageCreator 
{
	private static String verzeichnis = "./grafiken/";
	private static String dateiendung = ".jpg";
	private int anzahlVersionen;
	private int hoehe;
	private int breite;
	
	public ImageCreator(int hoehe, int breite, int anzahlVersionen)
	{
		this.setAufloesung(hoehe, breite);	
		this.anzahlVersionen = anzahlVersionen;
	}
	
	public ImageCreator(int hoehe, int breite)
	{
		this.setAufloesung(hoehe, breite);	
		this.anzahlVersionen = 6;
	}
	/**
	 * Generiert ein Array mit allen Versionen(Grafiken) des uebergebenen Logikgatters
	 * @param logikgatter Logikgatter von dem man alle Grafikversionen haben will.
	 * @return Array mit Grafiken
	 */
	public ImageIcon[] getImage(Logikgatter logikgatter)
	{
		ImageIcon[] grafiken = new ImageIcon[this.anzahlVersionen];
		
		for(Integer i = 0; i < anzahlVersionen;i++  )
		{
			try
			{
				grafiken[i] = new ImageIcon( ImageCreator.verzeichnis + logikgatter.toString() + i.toString() + ImageCreator.dateiendung ); // Grafik erstellen
				grafiken[i] = new ImageIcon(grafiken[i].getImage().getScaledInstance(this.hoehe, this.breite, Image.SCALE_DEFAULT)); //Skallieren
			}
			catch(InstantiationError e)// Grafik nicht vorhanden
			{
				grafiken[i] = null; 
			}
		}
		
		return grafiken;
	}
	
	
	/**
	 * Generiert ein Array mit allen Versionen(Grafiken) des uebergebenen Bits
	 * @param bit Bit von dem man alle Grafikversionen haben will.
	 * @return Array mit Grafiken
	 */
	public ImageIcon[] getImage(boolean bit)
	{
		Boolean castBit = (Boolean)bit; // Um spaeter die toString() zu nutzen.
		
		ImageIcon[] grafiken = new ImageIcon[this.anzahlVersionen];
		
		for(Integer i = 0; i < anzahlVersionen;i++  )
		{
			// Bild muss noch mit richtiger Aufloesung zurueckgegeben werden
			try
			{
				grafiken[i] = new ImageIcon( ImageCreator.verzeichnis + castBit.toString() + i.toString() + ImageCreator.dateiendung ); // Grafik erstellen
				grafiken[i] = new ImageIcon(grafiken[i].getImage().getScaledInstance(this.hoehe, this.breite, Image.SCALE_DEFAULT)); //Skallieren
			}
			catch(InstantiationError e)// Grafik nicht vorhanden
			{
				grafiken[i] = null; 
			}
		}
		
		return grafiken;
	}
	
	public void setAufloesung(int hoehe, int breite)
	{
		this.hoehe = hoehe;
		this.breite = breite;
	}
	
	
}

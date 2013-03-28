package GUI;
import java.awt.Dimension;
import java.awt.Image;

import Logik.Logikgatter;


import javax.swing.ImageIcon;

public class ImageCreator 
{
	private static String verzeichnis = "./grafiken/";
	private static String dateiendung = ".jpg";
	private int anzahlVersionen;
	private Dimension size;
	private boolean spiegeln;
	
	public ImageCreator(Dimension size, int anzahlVersionen,boolean spiegeln)
	{
		this.setAufloesung(size);	
		this.anzahlVersionen = anzahlVersionen;
		this.spiegeln = spiegeln;
	}
	
	public ImageCreator(Dimension size)
	{
		this.setAufloesung(size);	
		this.anzahlVersionen = 6;
		this.spiegeln = false;
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
				if(this.spiegeln)
				{
					grafiken[i] = new MirrorImageIcon( ImageCreator.verzeichnis + logikgatter.toString() + i.toString() + ImageCreator.dateiendung ); // Grafik erstellen
					grafiken[i] = new MirrorImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_DEFAULT)); //Skallieren
				}
				else
				{
					grafiken[i] = new ImageIcon( ImageCreator.verzeichnis + logikgatter.toString() + i.toString() + ImageCreator.dateiendung ); // Grafik erstellen
					grafiken[i] = new ImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_DEFAULT)); //Skallieren
				}
				
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
				if(this.spiegeln)
				{
					grafiken[i] = new MirrorImageIcon( ImageCreator.verzeichnis + castBit.toString() + i.toString() + ImageCreator.dateiendung ); // Grafik erstellen
					grafiken[i] = new MirrorImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_DEFAULT)); //Skallieren

				}
				else
				{
					grafiken[i] = new ImageIcon( ImageCreator.verzeichnis + castBit.toString() + i.toString() + ImageCreator.dateiendung ); // Grafik erstellen
					grafiken[i] = new ImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_DEFAULT)); //Skallieren

				}		
			}
			catch(InstantiationError e)// Grafik nicht vorhanden
			{
				grafiken[i] = null; 
			}
		}
		
		return grafiken;
	}
	
	public void setAufloesung(Dimension size)
	{
		this.size = size;
	}
	
	
}

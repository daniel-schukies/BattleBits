package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Logik.Logikgatter;
import Logik.Not;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ImageCreator 
{
	private static final String VERZEICHNIS = "bin/GUI/grafiken/";
	private static final String DATEIENDUNG = ".png";
	private static final String PLATZHALTER = "platzhalter";
	private static final String LOGIKGATTERSTATUS = "L";
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
		
		Boolean castBit = (Boolean)logikgatter.getAusgang(); // Um spaeter die toString() zu nutzen.
		
		for(Integer i = 0; i < anzahlVersionen;i++  )
		{
			try
			{
				if(this.spiegeln)
				{
			        BufferedImage im1 = ImageIO.read(new File(ImageCreator.VERZEICHNIS + logikgatter.toString() + i.toString() + ImageCreator.DATEIENDUNG )); 
			        
			        BufferedImage im2 = ImageIO.read(new File(ImageCreator.VERZEICHNIS + ImageCreator.LOGIKGATTERSTATUS + castBit + "1" + ImageCreator.DATEIENDUNG )); 
					
			        BufferedImage im1Mirror = ImageUtil.mirror(im1, 0);
			        
					BufferedImage combined = zeichneLogikgatterStatus(im1Mirror,im2, logikgatter);
					
					grafiken[i] = new ImageIcon(combined.getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_DEFAULT)); //Skallieren
				}
				else
				{
			        BufferedImage im1 = ImageIO.read(new File(ImageCreator.VERZEICHNIS + logikgatter.toString() + i.toString() + ImageCreator.DATEIENDUNG )); 
			        
			        BufferedImage im2 = ImageIO.read(new File(ImageCreator.VERZEICHNIS + ImageCreator.LOGIKGATTERSTATUS + castBit + "1" + ImageCreator.DATEIENDUNG )); 

			        BufferedImage combined = zeichneLogikgatterStatus(im1, im2, logikgatter);
			        
					grafiken[i] = new ImageIcon(combined.getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_DEFAULT)); //Skallieren
				}
				
			}
			catch(IOException e)// Grafik nicht vorhanden
			{
				grafiken[i] = null; 
				System.out.println("Error Versions ID:" + i);
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
		
		ImageIcon[] grafiken = new ImageIcon[3];
		
		for(Integer i = 0; i < 3;i++  )
		{
			// Bild muss noch mit richtiger Aufloesung zurueckgegeben werden
			try
			{
				if(this.spiegeln)
				{
					grafiken[i] = new MirrorImageIcon( ImageCreator.VERZEICHNIS + castBit.toString() + i.toString() + ImageCreator.DATEIENDUNG ); // Grafik erstellen
					grafiken[i] = new MirrorImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_DEFAULT)); //Skallieren

				}
				else
				{
					grafiken[i] = new ImageIcon( ImageCreator.VERZEICHNIS + castBit.toString() + i.toString() + ImageCreator.DATEIENDUNG ); // Grafik erstellen
					grafiken[i] = new ImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_DEFAULT)); //Skallieren
				}		
			}
			catch(InstantiationError e)// Grafik nicht vorhanden
			{
				grafiken[i] = null; 
				System.out.println("Error Versions ID:" + i);
			}
		}

		return grafiken;
	}
	
	public ImageIcon[] getImage()
	{
		ImageIcon[] grafiken = new ImageIcon[this.anzahlVersionen];
		
		for(Integer i = 0; i < 3;i++  )
		{
			// Bild muss noch mit richtiger Aufloesung zurueckgegeben werden
			try
			{
				{
					grafiken[i] = new ImageIcon( ImageCreator.VERZEICHNIS + ImageCreator.PLATZHALTER + i.toString() + ImageCreator.DATEIENDUNG ); // Grafik erstellen
					grafiken[i] = new ImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_DEFAULT)); //Skallieren
				}		
			}
			catch(InstantiationError e)// Grafik nicht vorhanden
			{
				grafiken[i] = null; 
				System.out.println("Error Versions ID:" + i);
			}
		}
		
		return grafiken;
	}
	
	public void setAufloesung(Dimension size)
	{
		this.size = size;
	}
	
	public BufferedImage zeichneLogikgatterStatus(BufferedImage mainImage, BufferedImage overlayImage, Logikgatter logikgatter )
	{
		if(!(logikgatter instanceof Not))
		{			
	        BufferedImage combined = new BufferedImage((int)this.size.getWidth(), (int)this.size.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        
	     // paint both images, preserving the alpha channels
	        Graphics g = combined.getGraphics();
	        g.drawImage(mainImage, 0, 0, (int)this.size.getWidth(), (int)this.size.getHeight(),null);
	        g.drawImage(overlayImage, (int)this.size.getHeight()/4, (int)this.size.getHeight()/4, (int)this.size.getHeight()/2, (int)this.size.getHeight()/2, null);
	        
	        return combined;
		}
		else
		{
			System.out.println("return mainIMG");
			return mainImage;
		}
	}
	
	
}

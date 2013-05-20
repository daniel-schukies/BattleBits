package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Logik.Logikgatter;
import Logik.Not;

/*
import Logik.And;
import Logik.Nand;
import Logik.Nor;
import Logik.Or;
import Logik.Xor;
*/
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ImageCreator 
{
	private static final String VERZEICHNIS = "GUI/grafiken/";
	private static final String DATEIENDUNG = ".png";
	private static final String PLATZHALTER = "platzhalter";
	private static final String LOGIKGATTERSTATUS = "L";
	private static GrafikCache grafikCache;
	private int anzahlVersionen;
	private Dimension size;
	private boolean spiegeln;
	
	
	public ImageCreator(Dimension size, int anzahlVersionen,boolean spiegeln)
	{
		this.setAufloesung(size);	
		this.anzahlVersionen = anzahlVersionen;
		this.spiegeln = spiegeln;
		ImageCreator.grafikCache = new GrafikCache();

		


		
		

		/*
		ArrayList<Object> logikgatterList = new ArrayList<Object>();
		ArrayList<Object> logikgatterGrafiken = new ArrayList<Object>();
		
		for(int i = 0; i < 6; i++)
		{
			switch (i)
			{
				case 0: 
					
				And and = new And();
				logikgatterList.add(and);
				logikgatterGrafiken.add(this.getImage(and));
				
				break;
				case 1:
				
				Nand nand = new Nand();
				logikgatterList.add(nand);
				logikgatterGrafiken.add(this.getImage(nand));
					
				break;
				case 2: 

				Or or = new Or();
				logikgatterList.add(or);
				logikgatterGrafiken.add(this.getImage(or));
					
				break;
				case 3: 

				Nor nor = new Nor();
				logikgatterList.add(nor);
				logikgatterGrafiken.add(this.getImage(nor));
					
				break;
				case 4:

				Not not = new Not();
				logikgatterList.add(not);
				logikgatterGrafiken.add(this.getImage(not));	
				
				break; 
				case 5: 
				
				Xor xor = new Xor();
				logikgatterList.add(xor);
				logikgatterGrafiken.add(this.getImage(xor));
	
				break; 
			}
		}
		
		this.logikgatterGrafikCache.add(logikgatterList);
		this.logikgatterGrafikCache.add(logikgatterGrafiken);
*/
	}
	
	/**
	 * Generiert ein Array mit allen Versionen(Grafiken) des uebergebenen Logikgatters
	 * @param logikgatter Logikgatter von dem man alle Grafikversionen haben will.
	 * @return Array mit Grafiken
	 */
	public ImageIcon[] getImage(Logikgatter logikgatter)
	{
		BufferedImage[] logikgatterCacheImages = ImageCreator.grafikCache.getImages(this.size, logikgatter.toString());
		BufferedImage[] statusCacheImages = ImageCreator.grafikCache.getImages(new Dimension((int)this.size.getWidth()/4, (int)this.size.getHeight()/4), ((Boolean)logikgatter.getAusgang()).toString());
		
		ImageIcon[] grafiken = new ImageIcon[this.anzahlVersionen];
	
		Boolean castBit = (Boolean)logikgatter.getAusgang(); // Um spaeter die toString() zu nutzen.
		
		BufferedImage[] cacheSpeicher = new BufferedImage[anzahlVersionen];
		
		for(Integer i = 0; i < anzahlVersionen;i++  )
		{
			try
			{
				BufferedImage logikgatterGrafik;
				BufferedImage overlay;
				
				BufferedImage newOverlay;
				BufferedImage newLogikgatterGrafik;
				
				if(statusCacheImages != null)
				{
					newOverlay = statusCacheImages[0];
					System.out.println("Status aus Cache");
				}
				else
				{
			        overlay = ImageIO.read(new File(ImageCreator.VERZEICHNIS + ImageCreator.LOGIKGATTERSTATUS + castBit + "1" + ImageCreator.DATEIENDUNG ));
					
			        newOverlay = new BufferedImage((int)this.size.getWidth()/2, (int)this.size.getHeight()/2, BufferedImage.TYPE_INT_ARGB);
			        Graphics g;
			        g = newOverlay.getGraphics();
			        g.drawImage(overlay.getScaledInstance((int)this.size.getWidth()/2,  (int)this.size.getHeight()/2, Image.SCALE_FAST), 0, 0,null);
			        
			        BufferedImage[] images = {newOverlay};
			        
			        ImageCreator.grafikCache.saveImages( ((Boolean)logikgatter.getAusgang()).toString(), images);
				}

				
				if(logikgatterCacheImages != null)
				{
					System.out.println("Grafik aus Cache geladen!!!");
					try
					{
						newLogikgatterGrafik = logikgatterCacheImages[i];
					}
					
					catch(Exception e)
					{
				        newLogikgatterGrafik = ImageIO.read(new File(ImageCreator.VERZEICHNIS + logikgatter.toString() + i.toString() + ImageCreator.DATEIENDUNG )); 
					}	
				}
				else
				{
					logikgatterGrafik = ImageIO.read(new File(ImageCreator.VERZEICHNIS + logikgatter.toString() + i.toString() + ImageCreator.DATEIENDUNG ));
					
			        newLogikgatterGrafik = new BufferedImage((int)this.size.getWidth(), (int)this.size.getHeight(), BufferedImage.TYPE_INT_ARGB);
			        
			        Graphics g = newLogikgatterGrafik.getGraphics();
			        g.drawImage(logikgatterGrafik.getScaledInstance((int)this.size.getWidth(),  (int)this.size.getHeight(), Image.SCALE_FAST), 0, 0,null);
			        
			        cacheSpeicher[i] = newLogikgatterGrafik;
				}
				
				if(this.spiegeln)
				{
			        newLogikgatterGrafik = ImageUtil.mirror(newLogikgatterGrafik, 0);
				}
		        
		        
		        grafiken[i] = zeichneLogikgatterStatus(newLogikgatterGrafik, newOverlay, logikgatter);
				
			}
			catch(IOException e)// Grafik nicht vorhanden
			{
				grafiken[i] = null; 
				System.out.println(e);
				System.out.println("Error Versions ID:" + i);
			}
		}
		
		if(cacheSpeicher[0] != null)
		{
			ImageCreator.grafikCache.saveImages(logikgatter.toString(), cacheSpeicher);
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
					grafiken[i] = new MirrorImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_FAST)); //Skallieren

				}
				else
				{
					grafiken[i] = new ImageIcon( ImageCreator.VERZEICHNIS + castBit.toString() + i.toString() + ImageCreator.DATEIENDUNG ); // Grafik erstellen
					grafiken[i] = new ImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_FAST)); //Skallieren
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
	
	public ImageIcon[] getImage(String dateiname)
	{
		ImageIcon[] grafiken = new ImageIcon[this.anzahlVersionen];
		
		for(Integer i = 0; i < this.anzahlVersionen;i++  )
		{
			// Bild muss noch mit richtiger Aufloesung zurueckgegeben werden
			try
			{
				grafiken[i] = new ImageIcon( ImageCreator.VERZEICHNIS + dateiname + i.toString() + ImageCreator.DATEIENDUNG ); // Grafik erstellen
				grafiken[i] = new ImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_SMOOTH)); //Skallieren
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
					grafiken[i] = new ImageIcon(grafiken[i].getImage().getScaledInstance((int)this.size.getHeight(), (int)this.size.getWidth(), Image.SCALE_FAST)); //Skallieren
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
	
	public ImageIcon zeichneLogikgatterStatus(BufferedImage mainImage, BufferedImage overlayImage, Logikgatter logikgatter )
	{
		if(!(logikgatter instanceof Not))
		{			
	        BufferedImage combined = new BufferedImage((int)this.size.getWidth(), (int)this.size.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        
	     // paint both images, preserving the alpha channels
	        Graphics g = combined.getGraphics();
	        g.drawImage(mainImage, 0, 0,null);
	        g.drawImage(overlayImage, (int)this.size.getHeight()/4, (int)this.size.getHeight()/4, null);
	        
	        return new ImageIcon(combined);
		}
		else
		{
			System.out.println("return mainIMG");
			return new ImageIcon(mainImage.getScaledInstance((int)this.size.getWidth(), (int)this.size.getHeight(), Image.SCALE_FAST));
		}
	}
	
	
}

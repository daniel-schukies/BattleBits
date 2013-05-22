package GUI.Game.Grafikverwaltung;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

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
	private static final String VERZEICHNIS = "/GUI/grafiken/";
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
			        overlay = ImageIO.read(getClass().getResource(ImageCreator.VERZEICHNIS + ImageCreator.LOGIKGATTERSTATUS + castBit + "1" + ImageCreator.DATEIENDUNG ));
					
			        newOverlay = new BufferedImage((int)this.size.getWidth()/2, (int)this.size.getHeight()/2, BufferedImage.TYPE_INT_ARGB);
			        Graphics g;
			        g = newOverlay.getGraphics();
			        g.drawImage(overlay.getScaledInstance((int)this.size.getWidth()/2,  (int)this.size.getHeight()/2, Image.SCALE_FAST), 0, 0,null);
			        
			        BufferedImage[] images = {newOverlay};
			        
			        ImageCreator.grafikCache.saveImages( ((Boolean)logikgatter.getAusgang()).toString(), images);
				}

				
				if(logikgatterCacheImages != null)
				{
					try
					{
						newLogikgatterGrafik = logikgatterCacheImages[i];
						System.out.println("Grafik aus Cache geladen!!!");
					}
					catch(Exception e)
					{
				        newLogikgatterGrafik = ImageIO.read(getClass().getResource(ImageCreator.VERZEICHNIS + logikgatter.toString() + i.toString() + ImageCreator.DATEIENDUNG ));
				        System.out.println("Fehler beim Laden aus Cache :(");
					}	
				}
				else
				{
					logikgatterGrafik = ImageIO.read(getClass().getResource(ImageCreator.VERZEICHNIS + logikgatter.toString() + i.toString() + ImageCreator.DATEIENDUNG ));
					
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
			catch(Exception e)// Grafik nicht vorhanden
			{
				grafiken[i] = null; 
				System.out.println(e);
				System.out.println("Error Versions ID:" + i);
				System.out.println(getClass().getResource(ImageCreator.VERZEICHNIS + logikgatter.toString() + i.toString() + ImageCreator.DATEIENDUNG ).getPath());
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
		
		ImageIcon[] grafiken = this.getImage(castBit.toString());
		
		if(this.spiegeln)
		{
			for(Integer i = 0; i < 3;i++  )
			{
				grafiken[i] = new ImageIcon(ImageUtil.mirror((BufferedImage)grafiken[i].getImage(), 0));
			}
		}

		return grafiken;
	}
	
	public ImageIcon[] getImage(String dateiname)
	{
		BufferedImage[] logikgatterCacheImages = ImageCreator.grafikCache.getImages(this.size, dateiname); // Lade Grafiken aus Cache

		ImageIcon[] grafiken = new ImageIcon[this.anzahlVersionen]; // Array, welcher spaeter zuueckgegeben wird.
	
		BufferedImage[] cacheSpeicher = new BufferedImage[anzahlVersionen]; // Array, welcher spaeter im Grafikcache gespeichert wird.
		
		for(Integer i = 0; i < this.anzahlVersionen;i++  )
		{
			try
			{
				BufferedImage currentGrafik; // Grafik der Datei
				BufferedImage newCurrentGrafik = new BufferedImage((int)this.size.getWidth(), (int)this.size.getHeight(), BufferedImage.TYPE_INT_ARGB); // In dieser wird skalliert
				
				URL url = getClass().getResource(ImageCreator.VERZEICHNIS + dateiname + i.toString() + ImageCreator.DATEIENDUNG);
				
				if(url != null)// Preufe, ob Pfad existiert
				{
					// Abfrage des Grafikcaches
					if((logikgatterCacheImages != null))
					{
						try
						{
							newCurrentGrafik = logikgatterCacheImages[i];
							System.out.println("Grafik aus Cache geladen!!!");
						}
						catch(Exception e)
						{
							currentGrafik = ImageIO.read(getClass().getResource(ImageCreator.VERZEICHNIS + dateiname + i.toString() + ImageCreator.DATEIENDUNG ));
							
						    // Scalliere Grfik
						    Graphics g = newCurrentGrafik.getGraphics();
						    g.drawImage(currentGrafik.getScaledInstance((int)this.size.getWidth(),  (int)this.size.getHeight(), Image.SCALE_SMOOTH), 0, 0,null);
						      
							System.out.println("Fehler beim Laden aus Cache :(");
						}	
					}
					else // Wenn nicht vorhanden, erstelle Grafik
					{
						// Erstelle Grafik
						currentGrafik = ImageIO.read(url);
							
					    // Scalliere Grfik
					    Graphics g = newCurrentGrafik.getGraphics();
					    g.drawImage(currentGrafik.getScaledInstance((int)this.size.getWidth(),  (int)this.size.getHeight(), Image.SCALE_SMOOTH), 0, 0,null);
					        
					    cacheSpeicher[i] = newCurrentGrafik; // speichere Grafik fuer Grafikcache
	
					    System.out.println("Grafik erstellt: " + url);
					}
					
	  				grafiken[i] = new ImageIcon(newCurrentGrafik); // Speichere Grafik um spaeter zurueckzugeben.  
				}
				else
				{
					grafiken[i] = null; 
					System.out.println("Version does not exitst -> Error Versions ID:" + i);
				}
			}
			catch(Exception e)// Grafik nicht vorhanden (konnte nicht erstellt werden)
			{
				grafiken[i] = null; 
				System.out.println(e);
				System.out.println("Error Versions ID:" + i);
				//Thread.getAllStackTraces();
			}
		}
		
		//Wenn Grafik im cacheSpeicher vonhanden wird sie gespeichert
		if(cacheSpeicher[0] != null)
		{
			ImageCreator.grafikCache.saveImages(dateiname, cacheSpeicher);
		}
		
		return grafiken;
	}

	
	public ImageIcon[] getImage()
	{	
		return this.getImage(ImageCreator.PLATZHALTER);
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

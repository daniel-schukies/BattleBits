package GUI.Game.Grafikverwaltung;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import GUI.Game.FileAdmin;
import Logik.And;
import Logik.Logikgatter;
import Logik.Nand;
import Logik.Nor;
import Logik.Not;
import Logik.Or;
import Logik.Xor;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ImageCreator 
{
	/**
	 * Strings für den Dateipfad
	 */
	private static final String VERZEICHNIS = "/GUI/grafiken/";
	private static final String DATEIENDUNG = ".png";
	private static final String PLATZHALTER = "platzhalter";
	private static final String LOGIKGATTERSTATUS = "L";
	
	private static GrafikCache grafikCache;
	private static boolean grafikenVorgeladen = false;
	private static int cntReload = 0;
	private int anzahlVersionen;
	private Dimension size;
	private boolean spiegeln;
	
	/**
	 * Setzt Eigenschaften der Grafiken
	 * @param size Aufloesung der zu erstellenden Grafiken
	 * @param anzahlVersionen Anzahl der Grafikversionen
	 * @param spiegeln Ob Grafiken gespiegelt werden sollen.
	 */
	public ImageCreator(Dimension size, int anzahlVersionen,boolean spiegeln)
	{
		this.setAufloesung(size);	
		this.anzahlVersionen = anzahlVersionen;
		this.spiegeln = spiegeln;
		ImageCreator.grafikCache = new GrafikCache();
		
		if(!(new FileAdmin().getCacheZustand()))
		{
			ImageCreator.grafikenVorgeladen = true;
		}
	}
	
	/**
	 * Generiert ein Array mit allen Versionen(Grafiken) des uebergebenen Logikgatters
	 * @param logikgatter Logikgatter von dem man alle Grafikversionen haben will.
	 * @return Array mit Grafiken
	 */
	public ImageIcon[] getImage(Logikgatter logikgatter, boolean drawLogikgatterStatus)
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
			        g.drawImage(logikgatterGrafik.getScaledInstance((int)this.size.getWidth(),  (int)this.size.getHeight(), Image.SCALE_SMOOTH), 0, 0,null);
			        
			        cacheSpeicher[i] = newLogikgatterGrafik;
				}
				
				if(this.spiegeln)
				{
			        newLogikgatterGrafik = ImageUtil.mirror(newLogikgatterGrafik, 0);
				}
		        
		        if(drawLogikgatterStatus)
		        {
		        	grafiken[i] = zeichneLogikgatterStatus(newLogikgatterGrafik, newOverlay, logikgatter);
		        }
		        else
		        {
		        	grafiken[i] = new ImageIcon(newLogikgatterGrafik);
		        }
		        
				
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
		
		/*if(this.spiegeln)
		{
			for(Integer i = 0; i < 3;i++  )
			{
				grafiken[i] = new ImageIcon(ImageUtil.mirror((BufferedImage)grafiken[i].getImage(), 0));
			}
		}*/

		return grafiken;
	}
	
	/**
	 * Generiert ein Array mit allen Versionen(Grafiken) des uebergebenen Dateinamens
	 * @param dateiname Dateiname von dem man alle Grafikversionen haben will.
	 * @return Array mit Grafiken
	 */
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
					System.out.println("Version does not exitst -> Error Versions ID:" + i + ImageCreator.VERZEICHNIS + dateiname + i.toString() + ImageCreator.DATEIENDUNG  );
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

	/**
	 * Erstellt Platzhalter-Array
	 * @return Array mit Grafiken
	 */
	public ImageIcon[] getImage()
	{	
		return this.getImage(ImageCreator.PLATZHALTER);
	}
	

	/**
	 * Aufloesung der Grafiken kann nachtraeglicht geaendert werden
	 * @param size Aufloesung
	 */
	public void setAufloesung(Dimension size)
	{
		this.size = size;
	}
	
	/**
	 * Zeichnet zwei Grafiken aufeinander (fuer Logikgattergrafiken)
	 * @param mainImage Hauptgrafik
	 * @param overlayImage Overlay-Grafik
	 * @param logikgatter Logiktter von dem die Grafik erstellt wird.
	 * @return Grafik mit beiden Grafiken
	 */
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
			return new ImageIcon(mainImage.getScaledInstance((int)this.size.getWidth(), (int)this.size.getHeight(), Image.SCALE_SMOOTH));
		}
	}
	
	/**
	 * Laedt Logikgattergrafiken vor.
	 */
	public void grafikenVorladen()
	{
		if( (!(ImageCreator.grafikenVorgeladen)) && (ImageCreator.cntReload == 0))
		{
			for(int i = 0; i < 7; i++)
			{
				switch (i)
				{
					case 0: this.getImage(new And(), false);
					break;
					
					case 1:this.getImage(new Nand(), false);
					break;
					
					case 2: this.getImage(new Or(), false);
					break;
					
					case 3: this.getImage(new Nor(), false);
					break;
					
					case 4:this.getImage(new Not(), false);
					break;

					case 5: this.getImage(new Xor(), false);
					break;
					
					case 6: this.getImage();
					break;
				}
			}
			ImageCreator.cntReload++;
			System.out.println("Alle Gatter Vorgeladen--------------------------------" + cntReload);

			ImageCreator.grafikenVorgeladen = true;

		}
	}

}

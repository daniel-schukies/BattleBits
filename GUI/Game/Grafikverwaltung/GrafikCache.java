package GUI.Game.Grafikverwaltung;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * 
 * @author Daniel Schukies, Sebastian Junger
 *
 */
public class GrafikCache 
{
	/**
	 * Speicher der Grafiken und Strings
	 */
	private static ArrayList<ArrayList<?>> grafikCache = new ArrayList<ArrayList<?>>();
	
	/**
	 * Speichert einen Grafiken-Array mit zugehoerigem String
	 * @param grafikBezeichnung Name des Grafik-Arrays
	 * @param images Array mit den Grafiken
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean saveImages(String grafikBezeichnung, BufferedImage[] images)
	{
		//Schauen ob Grafik existiert
		for(int i = 0; i < GrafikCache.grafikCache.size(); i++)
		{
			if( ((String)GrafikCache.grafikCache.get(i).get(0)).equals(grafikBezeichnung))
			{
				if( ((int)(((BufferedImage[])GrafikCache.grafikCache.get(i).get(1))[0].getWidth()) == (int)images[0].getWidth()) && ( (int)(((BufferedImage[])GrafikCache.grafikCache.get(i).get(1))[0].getHeight()) == (int)images[0].getHeight()) )
				{
					return false;
				}
			}
		}

		// Speichere
		ArrayList cache = new ArrayList();
		cache.add(0,grafikBezeichnung);
		cache.add(1,images);
		
		System.out.println(grafikBezeichnung+" Hoehe:"+images[0].getHeight()+"In Grafikcache abgelegt");
		
		GrafikCache.grafikCache.add(cache);
		
		return true;
	}
	
	/**
	 * Liest Grafiken-Array aus dem Cache aus
	 * @param size Aufloesung der Grafiken
	 * @param grafikBezeichnung Name des Grafik-Arrays
	 * @return
	 */
	public BufferedImage[] getImages(Dimension size, String grafikBezeichnung)
	{
		//Schauen ob Grafik existiert
		for(int i = 0; i < GrafikCache.grafikCache.size(); i++)
		{
			if( ((String)GrafikCache.grafikCache.get(i).get(0)).equals(grafikBezeichnung))
			{
				if( ((int)(((BufferedImage[])GrafikCache.grafikCache.get(i).get(1))[0].getWidth()) == (int)size.getWidth()) && ( (int)(((BufferedImage[])GrafikCache.grafikCache.get(i).get(1))[0].getHeight()) == (int)size.getHeight()) )
				{
					return ((BufferedImage[])GrafikCache.grafikCache.get(i).get(1));
				}
			}

		}
		System.out.println(grafikBezeichnung + " konnte nicht aus Grafikcache geladen werden");
		return null;
	}
	
	
	
}

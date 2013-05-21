package GUI.Game.Grafikverwaltung;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GrafikCache 
{
	private ArrayList<ArrayList<?>> grafikCache;
	
	public GrafikCache()
	{
		this.grafikCache = new ArrayList<ArrayList<?>>();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean saveImages(String grafikBezeichnung, BufferedImage[] images)
	{
		//Schauen ob Grafik existiert
		for(int i = 0; i < this.grafikCache.size(); i++)
		{
			if(!this.grafikCache.isEmpty())
			{
				if(!this.grafikCache.get(i).isEmpty())
				{
					if( ((String)this.grafikCache.get(i).get(0)).contains(grafikBezeichnung))
					{
						if( ((BufferedImage[])this.grafikCache.get(i).get(1))[0].getWidth() == images[0].getWidth() && (((BufferedImage[])this.grafikCache.get(i).get(1))[0].getHeight() == images[0].getHeight()) )
						{
							return false;
						}
					}
				}
			}
		}
		
		// Speichere
		ArrayList cache = new ArrayList();
		cache.add(0,grafikBezeichnung);
		cache.add(1,images);
		
		System.out.println(grafikBezeichnung+" Hoehe:"+images[0].getHeight());
		
		this.grafikCache.add(cache);
		
		return true;
	}
	
	
	public BufferedImage[] getImages(Dimension size, String grafikBezeichnung)
	{
		//Schauen ob Grafik existiert
		for(int i = 0; i < this.grafikCache.size(); i++)
		{
			if( ((String)this.grafikCache.get(i).get(0)).contains(grafikBezeichnung))
			{
				if( ((BufferedImage[])this.grafikCache.get(i).get(1))[0].getWidth() == size.getWidth() && (((BufferedImage[])this.grafikCache.get(i).get(1))[0].getHeight() == size.getHeight()) )
				{
					return ((BufferedImage[])this.grafikCache.get(i).get(1));
				}
			}
		}
		
		return null;
	}
	
	
	
}

package GUI;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GrafikCache 
{
	private ArrayList<ArrayList<?>> logikgatterGrafikCache;
	
	public GrafikCache()
	{
		this.logikgatterGrafikCache = new ArrayList<ArrayList<?>>();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean saveImage(String grafikBezeichnung, BufferedImage[] images)
	{
		//Schauen ob Grafik existiert
		for(int i = 0; i < this.logikgatterGrafikCache.size(); i++)
		{
			if(!this.logikgatterGrafikCache.isEmpty())
			{
				if(!this.logikgatterGrafikCache.get(i).isEmpty())
				{
					if( ((String)this.logikgatterGrafikCache.get(i).get(0)).contains(grafikBezeichnung))
					{
						if( ((BufferedImage[])this.logikgatterGrafikCache.get(i).get(1))[0].getWidth() == images[0].getWidth() && (((BufferedImage[])this.logikgatterGrafikCache.get(i).get(1))[0].getHeight() == images[0].getHeight()) )
						{
							return false;
						}
					}
				}
			}
		}
		
		// Speichere
		ArrayList cache = new ArrayList();
		cache.add(grafikBezeichnung);
		cache.add(images);
		
		return true;
	}
}

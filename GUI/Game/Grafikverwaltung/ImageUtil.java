package GUI.Game.Grafikverwaltung;

import java.awt.image.BufferedImage;

public class ImageUtil 
{
	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	
	//Instanzbildung verhindern
	private ImageUtil() {}
	
	/**
	 * Erzeuge gespiegelte kopie
	 */
	public static BufferedImage mirror(BufferedImage org, int axis)
	{
		int width = org.getWidth();
		int height = org.getHeight();
		
		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB );
		
		if(axis == X_AXIS)
		{
			for(int i = 0; i < width; i++)
			{
				for(int j = 0; j < height; j++)
				{
					dest.setRGB(width-1-i, j, org.getRGB(i, j));
				}
			}
		}else if(axis == Y_AXIS)
		{
			for(int i = 0; i < width; i++)
			{
				for(int j = 0; j < height; j++)
				{
					dest.setRGB(i, height-1-j, org.getRGB(i, j));
				}
			}
		}
		
		return dest;
	}
}


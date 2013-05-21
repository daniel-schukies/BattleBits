package GUI.Game.Grafikverwaltung;



import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Logik.Logikgatter;

public class Grafikspeicher 
{
	private JLabel button;
	private ImageIcon[] grafiken;
	private ImageCreator imageCreator;
	
	
	/**
	 * 
	 * @param size
	 * @param anzahlVersionen
	 * @param bit
	 */
	public Grafikspeicher(Dimension size, int anzahlVersionen,boolean spiegeln)
	{
		this.imageCreator = new ImageCreator(size, anzahlVersionen, spiegeln);
		this.grafiken = new ImageIcon[anzahlVersionen];
		this.button = new JLabel();
	}

	
	/**
	 * 
	 * @param logikgatter
	 */
	public void setImage(Logikgatter logikgatter)
	{	
		/** Die verschiedenen Versionen der Logikgattergrafiken werden in einem Array in Form von ImageIcons gespeichert */

		this.grafiken = this.imageCreator.getImage(logikgatter);
		this.button.setIcon(this.grafiken[0]);
	}
	
	/**
	 * 
	 * @param bit
	 */
	public void setImage(boolean bit)
	{
		/** Die verschiedenen Versionen der Bitgrafiken wwerden in einem Array in Form von ImageIcons gespeichert */

		this.grafiken = this.imageCreator.getImage(bit);
		this.button.setIcon(this.grafiken[0]);
	}
	
	public boolean setImage(String dateiname)
	{
		this.grafiken = this.imageCreator.getImage(dateiname);
		this.button.setIcon(this.grafiken[0]);
		
		return true;
	}
	
	public boolean setImage(ImageIcon[] grafiken)
	{
		if(this.grafiken.length == grafiken.length)
		{
			this.grafiken = grafiken;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setImage()
	{
		this.grafiken = this.imageCreator.getImage();
		this.button.setIcon(this.grafiken[0]);
	}
	
	/**
	 * 
	 * @param versionID
	 * @return
	 */
	public boolean setVersion(int versionID)
	{
		try
		{
			//this.button.setIcon(null);
			this.button.setIcon(this.grafiken[versionID]);
			//this.button.revalidate();
			return true;
			
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public JLabel getImage()
	{
		return button;
	}
}

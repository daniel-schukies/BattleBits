package GUI;

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
	public Grafikspeicher(Dimension size, int anzahlVersionen, boolean bit,boolean spiegeln)
	{
		this.setImage(bit);
		this.imageCreator = new ImageCreator(size, anzahlVersionen, spiegeln);
		this.grafiken = new ImageIcon[anzahlVersionen];
		this.button = new JLabel();
	}
	
	/**
	 * 
	 * @param size
	 * @param anzahlVersionen
	 * @param logikgatter
	 */
	public Grafikspeicher(Dimension size, int anzahlVersionen, Logikgatter logikgatter, boolean spiegeln)
	{
		this.setImage(logikgatter);
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
		this.button.setIcon(grafiken[0]);
	}
	
	/**
	 * 
	 * @param bit
	 */
	public void setImage(boolean bit)
	{
		/** Die verschiedenen Versionen der Bitgrafiken wwerden in einem Array in Form von ImageIcons gespeichert */

		this.grafiken = this.imageCreator.getImage(bit);
		this.button.setIcon(grafiken[0]);
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
			this.button.setIcon(this.grafiken[versionID]);
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

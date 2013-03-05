package GUI;

import java.awt.Dimension;

import javax.swing.JLabel;

import Logik.Logikgatter;

public class Grafikspeicher 
{
	private JLabel[][] grafiken;//
	private ImageCreator imageCreator;
	
	public Grafikspeicher(Dimension size, int anzahlVersionen)
	{
		this.imageCreator = new ImageCreator(size, anzahlVersionen);
		this.grafiken = new JLabel[Logikgatter.ANZAHL_LOGIKGATTER+2][anzahlVersionen];
	}
	
	public Grafikspeicher(Dimension size)
	{
		imageCreator = new ImageCreator(size);
		this.grafiken = new JLabel[Logikgatter.ANZAHL_LOGIKGATTER+2][6];
	}
	
	
	public void setImage(Logikgatter logikgatter)
	{	
		/** Die verschiedenen Versionen der Logikgattergrafiken werden in einem Array in Form von JLabels gespeichert */
		for(int versionsCounter = 0; versionsCounter < this.grafiken[0].length; versionsCounter++)
		{
			this.grafiken[logikgatter.getID()][versionsCounter] = new JLabel(this.imageCreator.getImage(logikgatter)[versionsCounter]);
		}
	}
	
	public void setImage(boolean bit)
	{
		/** Die verschiedenen Versionen der Bitgrafiken werden in einem Array in Form von JLabels gespeichert */
		for(int versionsCounter = 0; versionsCounter < this.grafiken[0].length; versionsCounter++)
		{
			if(bit)
			{
				this.grafiken[Logikgatter.ANZAHL_LOGIKGATTER][versionsCounter] = new JLabel(this.imageCreator.getImage(bit)[versionsCounter]);
			}else
			{
				this.grafiken[Logikgatter.ANZAHL_LOGIKGATTER+1][versionsCounter] = new JLabel(this.imageCreator.getImage(bit)[versionsCounter]);
			}
		}
	}
	
	public JLabel getImage(int bildID, int version )
	{
		try
		{
			return this.grafiken[bildID][version];
		}catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}
	

	

}

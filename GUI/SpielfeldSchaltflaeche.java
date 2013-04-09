package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import Logik.Spielfeld;


@SuppressWarnings("serial")
public class SpielfeldSchaltflaeche extends JPanel
{

	private final int  anzahlSchaltflaechen = 4;
	
	private int[] gatterPosition;
	
	private LogikgatterSchaltflaeche[] logikgatterSchaltflaechenArray;
	
	public SpielfeldSchaltflaeche(Spielfeld spielfeld, int breite,boolean spiegeln )
	{
		this.setLayout(null);
		this.setSize(breite, breite);
		this.setPreferredSize(new Dimension(breite,breite));
		
		this.logikgatterSchaltflaechenArray = new LogikgatterSchaltflaeche[4];
		
		this.gatterPosition = new int[2];
		
		this.setBackground(new Color(0,0, 0,255) );
		
		int yPosSchaltflaechen = 0;
		int xPosSchaltflaechen = 0;
		
		
		for( int i=0; i < this.anzahlSchaltflaechen; i++)
		{
			switch(i)
			{
				case 0: yPosSchaltflaechen = 0;
				break;
				case 1: yPosSchaltflaechen = (breite/(this.anzahlSchaltflaechen*2));// /8
				break;
				case 2: yPosSchaltflaechen = (breite/this.anzahlSchaltflaechen);// /4
				break;
				case 3: yPosSchaltflaechen = (int)(breite/(this.anzahlSchaltflaechen)*1.5);// /2
				break;
			}
			
			if(spiegeln)
			{
				xPosSchaltflaechen = (breite-(i*(breite/this.anzahlSchaltflaechen))-breite/this.anzahlSchaltflaechen);
			}
			else
			{
				xPosSchaltflaechen = (i*(breite/this.anzahlSchaltflaechen));
			}
			
			//																		(int xPos,					int yPos		    , int  size							, int anzahlGrafiken   ,Logikgatter[] logikgatter ,MouseListener externerMouseListener,boolean spiegeln, boolean isVertikal )
			this.logikgatterSchaltflaechenArray[i] = new SpielfeldLogikgatterSchaltflaeche( xPosSchaltflaechen, yPosSchaltflaechen, breite/this.anzahlSchaltflaechen, (this.anzahlSchaltflaechen-i),spielfeld.getLogikgatter(i),null,this,spiegeln, true );
			this.add(this.logikgatterSchaltflaechenArray[i]);
		}
		
		
		this.setVisible(true);
	}
	
	
	public void sucheGeklicktenButton()
	{
		for( int j=0; j < this.anzahlSchaltflaechen; j++)
		{
			if(this.logikgatterSchaltflaechenArray[j].getPressedID().getIsPressed())
			{
				this.gatterPosition[0] = j;
				this.gatterPosition[1] = this.logikgatterSchaltflaechenArray[j].getPressedID().getID();
			}
		}
	}
	

	
	public void resetAllImages()
	{
		for( int j=0; j < this.anzahlSchaltflaechen; j++)
		{
			if(this.logikgatterSchaltflaechenArray[j].getPressedID().getIsPressed())
			{
				this.logikgatterSchaltflaechenArray[j].setAllImagesToLogikgatterStatus();
				this.logikgatterSchaltflaechenArray[j].setPressedID(new IDInfo());
			}
		}
	}
}

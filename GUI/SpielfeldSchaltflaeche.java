package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import Logik.Konsole;
import Logik.Spiel;
import Logik.Spieler;



@SuppressWarnings("serial")
public class SpielfeldSchaltflaeche extends JPanel implements Refreshable
{

	private final int  anzahlSchaltflaechen = 4;
	
	private Spiel spiel;
	
	private Spieler spieler;
	
	private IDInfo[] gatterPosition;
	
	private LogikgatterSchaltflaeche schaltflaeche;
	
	private Refreshable[] refreshSchaltflaechen;
	
	private LogikgatterSchaltflaeche[] logikgatterSchaltflaechenArray;
	
	public SpielfeldSchaltflaeche(LogikgatterSchaltflaeche schaltflaeche, Spiel spiel, Spieler spieler, int breite,boolean spiegeln )
	{
		this.setLayout(null);
		this.setSize(breite, breite);
		this.setPreferredSize(new Dimension(breite,breite));
		
		this.logikgatterSchaltflaechenArray = new LogikgatterSchaltflaeche[4];
		
		this.schaltflaeche = schaltflaeche;
				
		this.spiel = spiel;
		this.spieler = spieler;
		
		this.gatterPosition = new IDInfo[2];
		
		this.gatterPosition[0] = new IDInfo();
		this.gatterPosition[1] = new IDInfo();
		
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
			this.logikgatterSchaltflaechenArray[i] = new SpielfeldLogikgatterSchaltflaeche( xPosSchaltflaechen, yPosSchaltflaechen, breite/this.anzahlSchaltflaechen, (this.anzahlSchaltflaechen-i),this.spieler.getSpielfeld().getLogikgatter(i),null,this,spiegeln, true );
			this.logikgatterSchaltflaechenArray[i].setAllImagesToLogikgatterStatus();
			this.add(this.logikgatterSchaltflaechenArray[i]);
		}
		
		
		this.setVisible(true);
	}
	
	public void setRefreshSchaltflaechen(Refreshable[] refrashSchaltflaechen)
	{
		this.refreshSchaltflaechen = refrashSchaltflaechen;
	}
	
	
	public void sucheGeklicktenButton()
	{
		for( int j=0; j < this.anzahlSchaltflaechen; j++)
		{
			if(this.logikgatterSchaltflaechenArray[j].getPressedID().getIsPressed())
			{
				this.gatterPosition[0].setID(j);
				this.gatterPosition[0].setIsPressed(true);
				
				this.gatterPosition[1].setID(this.logikgatterSchaltflaechenArray[j].getPressedID().getID());
				this.gatterPosition[1].setIsPressed(true);
			}
		}
	}
	
	
	public void beendeSpielzug()
	{
		if(this.gatterPosition[0].getIsPressed() && this.schaltflaeche.getPressedID().getIsPressed() && this.spieler.getIsDran() )
		{
			if( this.spiel.legeLogikgatter(this.spieler,	this.schaltflaeche.getPressedID().getID(),	this.gatterPosition[0].getID(), this.gatterPosition[1].getID()) )
			{
				this.resetAllImages();
				this.spiel.nextSpielzug();
				
				if(this.spiel.getAktuellerSpieler().getIsKI() && this.spiel.getAktuellerSpieler().getIsDran())
				{
					this.spiel.nextSpielzug();
				}
				
				this.gatterPosition[0].setIsPressed(false);
				this.gatterPosition[1].setIsPressed(false);
				
				this.schaltflaeche.getPressedID().setIsPressed(false);
				

				
				this.refresh();
				
				this.schaltflaeche.refresh();
				if(this.refreshSchaltflaechen != null)
				{
					for(int i = 0; i < this.refreshSchaltflaechen.length; i++)
					{
						this.refreshSchaltflaechen[i].refresh();
					}
				}
			}
		}
	}
	
	public void refresh()
	{
		for(int i = 0; i < this.anzahlSchaltflaechen; i++)
		{
			this.logikgatterSchaltflaechenArray[i].refresh();
			this.logikgatterSchaltflaechenArray[i].setAllImagesToLogikgatterStatus();
		}
	}
	
	public void resetAllImages()
	{
		for( int j=0; j < this.anzahlSchaltflaechen; j++)
		{
			if(this.logikgatterSchaltflaechenArray[j].getPressedID().getIsPressed())
			{
				this.logikgatterSchaltflaechenArray[j].setPressedID(new IDInfo());
			}
			
			this.logikgatterSchaltflaechenArray[j].setAllImagesToLogikgatterStatus();
		}
	}
}

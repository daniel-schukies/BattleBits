package GUI.Game.Schaltflaechen;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.Game.FileAdmin;
import GUI.Game.GameFrame;
import GUI.Game.IDInfo;
import GUI.Game.Refreshable;
import GUI.Game.SoundAusgabe;
import GUI.Game.Grafikverwaltung.Grafikspeicher;
import Logik.Spiel;
import Logik.Spieler;


/**
 * Klasse dient zur grafischen Darstellung eines Teils des Spielfelds, naemlich dem eines Spielers.
 * Auf diesem Teil des Spielfelds werden die Logikgatter des jeweiligen Spielers dargestellt, die dieser auf das Spielfeld gelegt hat.
 * @author Daniel Schukies und Sebastian Junger
 *
 */
@SuppressWarnings("serial")
public class SpielfeldSchaltflaeche extends JPanel implements Refreshable
{

	private final int  anzahlSchaltflaechen = 4;
	
	private Spiel spiel;
	
	/**
	 * Spieler dem dieser Teil des Spielfelds gehoert
	 */
	private Spieler spieler;
	
	/**
	 * Welches Gatter gewaehlt wurde.
	 */
	private IDInfo[] gatterPosition;
	
	/**
	 * Logikgatter die Spieler zu Auswahl hat(Darstellende Schaltflaeche)
	 */
	private LogikgatterSchaltflaeche schaltflaeche;
	
	/**
	 * Zu aktualisierende Schaltflaechen
	 */
	private Refreshable[] refreshSchaltflaechen;
	
	private FileAdmin filewriter; 
	
	
	private JFrame frame;
	

	/**
	 * Soundausgabe
	 */
	private SoundAusgabe sound;
	
	private LogikgatterSchaltflaeche[] logikgatterSchaltflaechenArray;
	
	/**
	 * 
	 * @param xPos X-Position der Schaltflaeche
	 * @param yPos Y-Position der Schaltflaeche
	 * @param schaltflaeche Logikgatter die Spieler zu Auswahl hat(Darstellende Schaltflaeche)
	 * @param spiel aktuelles SpielS
	 * @param spieler Spieler dem dieser Teil des Spielfelds gehoert
	 * @param breite Eine Seiteenlaenge der Spielfeldschaltflaeche (da Quadratisch)
	 * @param spiegeln Ob Schaltflaecheninhalt gespiegelt sein soll.
	 */
	public SpielfeldSchaltflaeche(int xPos,int yPos,LogikgatterSchaltflaeche schaltflaeche, Spiel spiel, Spieler spieler, int breite,JFrame frame,boolean spiegeln)
	{
		this.setLayout(null);
		this.setBounds(xPos, yPos, breite, breite);
		this.setPreferredSize(new Dimension(breite,breite));
		
		this.frame = frame;
		
		this.logikgatterSchaltflaechenArray = new LogikgatterSchaltflaeche[4];
		
		this.schaltflaeche = schaltflaeche;
		
		this.sound  = new SoundAusgabe();
		this.filewriter = new FileAdmin();
	
		this.spiel = spiel;
		this.spieler = spieler;
		
		this.gatterPosition = new IDInfo[2];
		
		this.gatterPosition[0] = new IDInfo();
		this.gatterPosition[1] = new IDInfo();
		
		this.setBackground(new Color(0,0, 0,255) );
		this.setOpaque(false);
		
		int yPosSchaltflaechen = 0;
		int xPosSchaltflaechen = 0;
		
		/**
		 * Berechne groesse der Schaltlfaeche und Anordnung der SpielfeldSLogikgatterSchaltflaechen
		 */
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
			this.logikgatterSchaltflaechenArray[i] = new SpielfeldLogikgatterSchaltflaeche( xPosSchaltflaechen, yPosSchaltflaechen, breite/this.anzahlSchaltflaechen, (this.anzahlSchaltflaechen-i),this.spieler.getSpielfeld().getLogikgatter(i),null,this,spiegeln, true, !this.filewriter.getHardCoreZustand() );
			this.logikgatterSchaltflaechenArray[i].setAllImagesToLogikgatterStatus();
			this.add(this.logikgatterSchaltflaechenArray[i]);
		}
		
		
		this.setVisible(true);
	}
	
	/**
	 * Setzen der Refreshschaltflaechen
	 * @param refrashSchaltflaechen Schaltflaechen, die aktualisiert werden sollen, wenn noetig
	 */
	public void setRefreshSchaltflaechen(Refreshable[] refrashSchaltflaechen)
	{
		this.refreshSchaltflaechen = refrashSchaltflaechen;
	}
	
	/**
	 * Ueberpruefe, ob Button geklickt wurde und schreibe noetige Eintraege
	 */
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
	
	/**
	 * Beende aktuellen Spielzug und leite naechsten ein.
	 */
	public void beendeSpielzug()
	{		
		if(this.gatterPosition[0].getIsPressed() && this.schaltflaeche.getPressedID().getIsPressed() && this.spieler.getIsDran() )
		{
			if( this.spiel.legeLogikgatter(this.spieler,	this.schaltflaeche.getPressedID().getID(),	this.gatterPosition[0].getID(), this.gatterPosition[1].getID()) )
			{
				this.resetAllImages();
				this.spiel.nextSpielzug();
				

				
				/**
		    	 * Schaue ob KI spielen muss
		    	 */
				if(this.spiel.getAktuellerSpieler().getIsKI() && this.spiel.getAktuellerSpieler().getIsDran())
				{
					SpielfeldSchaltflaeche.this.spiel.nextSpielzug();
				}
				
				/**
				 * Pruefe, ob Spiel zu Ende
				 */
				if(this.spiel.getSpielende())
				{
					if(this.spieler.getIsWinner())
					{
						this.beendeSpiel(true);
					}
					else
					{
						this.beendeSpiel(false);
					}
				}
				
				
				SpielfeldSchaltflaeche.this.gatterPosition[0].setIsPressed(false);
				SpielfeldSchaltflaeche.this.gatterPosition[1].setIsPressed(false);
							
				SpielfeldSchaltflaeche.this.schaltflaeche.getPressedID().setIsPressed(false);

				
				this.refresh();
				
				  SwingUtilities.invokeLater(new Runnable() 
				  {
					    public void run() 
					    {

							SpielfeldSchaltflaeche.this.schaltflaeche.refresh();
							if(SpielfeldSchaltflaeche.this.refreshSchaltflaechen != null)
							{
								for(int i = 0; i < SpielfeldSchaltflaeche.this.refreshSchaltflaechen.length; i++)
								{
									SpielfeldSchaltflaeche.this.refreshSchaltflaechen[i].refresh();
								}
							}
					    }
				  });

			}else
			{

				this.sound.playError();
				System.out.println("fast^^");
				
				if(this.filewriter.getHardCoreZustand())
				{
					System.out.println("Hardcore!");
					this.spiel.nextSpielzug();
					
			    	/**
			    	 * Schaue ob KI spielen muss
			    	 */
					if(SpielfeldSchaltflaeche.this.spiel.getAktuellerSpieler().getIsKI() && SpielfeldSchaltflaeche.this.spiel.getAktuellerSpieler().getIsDran())
					{
						this.spiel.nextSpielzug();
						
						
						if(this.refreshSchaltflaechen != null)
						{
							for(int i = 0; i < this.refreshSchaltflaechen.length; i++)
							{
								this.refreshSchaltflaechen[i].refresh();
							}
						}
						
						SwingUtilities.invokeLater(new Runnable() 
						{
							public void run() 
							{
								SpielfeldSchaltflaeche.this.refresh();
							}
						});
					}
				}
				

			}
		}
	}
	
	public void beendeSpiel(boolean isWinner)
	{
		this.spiel.setSpielende(false);
		Grafikspeicher endGrafik = new Grafikspeicher(this.getSize(),1,false);
		if(isWinner)
		{
			endGrafik.setImage("winner");
		}
		else
		{
			endGrafik.setImage("gameover");
		}
		
		endGrafik.getImage().setBounds(0, 0, (int)this.getWidth(), (int)this.getHeight());

		this.removeAll();
		this.add(endGrafik.getImage());
		this.repaint();

		  SwingUtilities.invokeLater(new Runnable() 
		  {
			    public void run() 
			    {
			    	try 
					{
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			    	SpielfeldSchaltflaeche.this.frame.removeAll();
			    	SpielfeldSchaltflaeche.this.frame.dispose();
			    	new GameFrame();
			    }
		  });		
	}
	
	/**
	 * Aktualisiert diese Schaltflaeche
	 */
	public void refresh()
	{

		for(int i = 0; i < this.anzahlSchaltflaechen; i++)
		{
			this.logikgatterSchaltflaechenArray[i].refresh();
			this.logikgatterSchaltflaechenArray[i].setAllImagesToLogikgatterStatus();
		}

	}
	
	/**
	 * Setzt alle Grafiken auf z.B. den Logikgatterstatus etc. zurueck
	 */
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

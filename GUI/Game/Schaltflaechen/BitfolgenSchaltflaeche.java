package GUI.Game.Schaltflaechen;

import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import GUI.Game.Refreshable;
import GUI.Game.SoundAusgabe;
import Logik.Bitgenerator;
import Logik.Spiel;


@SuppressWarnings("serial")
public class BitfolgenSchaltflaeche extends SkallierbareSchaltflaeche implements Refreshable
{
	private Bitgenerator bitfolge;
	private int anzahlGrafiken;
	private LogikgatterSchaltflaeche[] schaltflaeche;
	private Refreshable[] refreshSchaltflaechen;
	private Spiel spiel;
	private static final int ANZAHLVERSIONEN = 3;
	private SoundAusgabe sound;

	/**
	 * 
	 * @param xPos x - Position der Schaltflaeche
	 * @param yPos y - Position der Schaltflaeche
	 * @param size Groesse der Schaltflaeche
	 * @param anzahlGrafiken Anzahl der enthaltenen Grafiken
	 * @param spiel Spiel, dessen Logik beachtet werden muss
	 * @param bitfolge Bitfolge, deren Logik beachtet werden muss
	 * @param schaltflaeche Logikgatterschaltflaechen - Array
	 * @param spiegeln Spiegeln der Schaltflaeche 
	 * @param isVertikal Vertikale bzw. horizontale Darstellung der Schaltflaeche
	 */
	public BitfolgenSchaltflaeche(int xPos, int yPos, int size,	int anzahlGrafiken,Spiel spiel, Bitgenerator bitfolge, LogikgatterSchaltflaeche[] schaltflaeche, boolean spiegeln, boolean isVertikal) 
	{		
		super(xPos, yPos, size, anzahlGrafiken,BitfolgenSchaltflaeche.ANZAHLVERSIONEN, spiegeln, isVertikal);

		this.anzahlGrafiken = anzahlGrafiken;
		
		this.bitfolge = bitfolge;
		
		this.sound  = new SoundAusgabe();
		
		this.schaltflaeche = schaltflaeche;
		
		this.refreshSchaltflaechen = null;
		
		this.spiel = spiel;
		
		this.refresh();
		
		for(int i = 0; i < this.anzahlGrafiken; i++)
		{
			super.getImage(i).addMouseListener(this);
			this.add(super.getImage(i));
		}		
	}
	
	/**
	 * Fueg den Refreshschaltflaechen weitere Schaltflaechen hinzu
	 * @param refrashSchaltflaechen Schaltflaechen, die den RefreshSchaltflaechen hinzugefuegt werden sollen
	 * */
	public void setRefreshSchaltflaechen(Refreshable[] refrashSchaltflaechen)
	{
		this.refreshSchaltflaechen = refrashSchaltflaechen;
	}
	
	/**
	 * Aktuallisiert alle Schaltflaechen
	 * */
	public void refresh()
	{
		for(int i = 0; i < this.anzahlGrafiken; i++ )
		{
				this.setImage(i,this.bitfolge.getBit(i));
		}
	}
	
	/**
	 * Prueft ob gueltiger Spieler ein gueltiges Logikgatter gelegt hat.
	 * */
	private void beendeSpielzug()
	{	
		System.out.println("GEHHT2");
		//Schaltflaeche muss gedrueckt worden sein
		if(this.getPressedID().getIsPressed())
		{
			System.out.println("GEHHT4");
			//sucht den Spieler, der geklickt hat
			for(int spielerID = 0; spielerID < this.schaltflaeche.length; spielerID++)
			{
				System.out.println("GEHHT3");
				//Der Klick muss von dem Spieler kommen, dem das Spielfeld gehoert und er muss gerade dran sein
				if( ( this.spiel.getAktuellerSpieler().getLogikgatter() == this.schaltflaeche[spielerID].getLogikgatter() ) && this.spiel.getSpieler()[spielerID].getIsDran() )
				{	
					System.out.println("GEHHT5 ID" + spielerID);
					//genauer Index der Schaltflaeche, die gedrueckt wurde
					if(this.schaltflaeche[spielerID].getPressedID().getIsPressed())
					{
						System.out.println("GEHHT6");
						//Logikgatter muss an dieser Stelle legbar sein (Not)
						if( this.spiel.legeLogikgatter(this.spiel.getSpieler()[spielerID], this.schaltflaeche[spielerID].getPressedID().getID(), this.getPressedID().getID() ) )
						{
							System.out.println("Dran:" + this.spiel.getAktuellerSpieler().getIsDran() +"ID:" + spielerID);
							this.spiel.nextSpielzug();
							//nextSpielzug zweimal ausfuehren, falls gegen die KI gespielt wird
							if(this.spiel.getAktuellerSpieler().getIsKI() && this.spiel.getAktuellerSpieler().getIsDran())
							{
								this.spiel.nextSpielzug();
							}
							
							this.refresh();
							this.getPressedID().setIsPressed(false);
							
							SwingUtilities.invokeLater(new Runnable() 
							{
								public void run() 
								{
									if(BitfolgenSchaltflaeche.this.refreshSchaltflaechen != null)
									{
										BitfolgenSchaltflaeche.this.sound.playWarning();
										for(int i = 0; i < BitfolgenSchaltflaeche.this.refreshSchaltflaechen.length; i++)
										{
											BitfolgenSchaltflaeche.this.refreshSchaltflaechen[i].refresh();
										}
								    }
									
								}
							});

							
							System.out.println("GEHHT1");
						}//kein Not gelegt
						else
						{
							System.out.println("Schaltflaeche Index:"+this.schaltflaeche[spielerID].getPressedID().getID() +"Invert Bit:" +this.getPressedID().getID());

							this.sound.playError();
							System.out.println("Bitfolgenschaltflaechenerror");
						}
						
						break;
					}
				}
			}
		}
		
		this.refresh();
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		JLabel button = (JLabel)e.getSource();
		if( this.getPressedID().getIsPressed() )
		{
			if(button == this.getImage(this.getPressedID().getID()))
			{
				this.setAllImagesToVersion(0);
				this.changeVersion(1, button);
				this.getPressedID().setIsPressed(false);
			}
			else
			{
				this.changeVersion(0, this.getImage(this.getPressedID().getID()));
				this.setAllImagesToVersion(0);
				this.changeVersion(1, button);
				this.setPressedID(this.getImageIDInfo( button ));
			}
		}
		else
		{
			this.setAllImagesToVersion(0);
			this.changeVersion(1, button);
			this.setPressedID(this.getImageIDInfo( button ));
		}
		
		this.beendeSpielzug();
	}
}

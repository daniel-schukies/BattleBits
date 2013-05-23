package GUI.Game.Schaltflaechen;

import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import GUI.Game.GameFrame;
import GUI.Game.GamePanel;
import GUI.Game.Refreshable;
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

	public BitfolgenSchaltflaeche(int xPos, int yPos, int size,	int anzahlGrafiken,Spiel spiel, Bitgenerator bitfolge, LogikgatterSchaltflaeche[] schaltflaeche, boolean spiegeln, boolean isVertikal) 
	{		
		super(xPos, yPos, size, anzahlGrafiken,BitfolgenSchaltflaeche.ANZAHLVERSIONEN, spiegeln, isVertikal);

		this.anzahlGrafiken = anzahlGrafiken;
		
		this.bitfolge = bitfolge;
		
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
	
	public void setRefreshSchaltflaechen(Refreshable[] refrashSchaltflaechen)
	{
		this.refreshSchaltflaechen = refrashSchaltflaechen;
	}
	
	public void refresh()
	{
		for(int i = 0; i < this.anzahlGrafiken; i++ )
		{
				this.setImage(i,this.bitfolge.getBit(i));
		}
	}
	
	private void beendeSpielzug()
	{
		System.out.println("GEHHT2");
		if(this.getPressedID().getIsPressed())
		{
			System.out.println("GEHHT4");
			for(int spielerID = 0; spielerID < this.schaltflaeche.length; spielerID++)
			{
				System.out.println("GEHHT3");
				if( ( this.spiel.getAktuellerSpieler().getLogikgatter() == this.schaltflaeche[spielerID].getLogikgatter() ) && this.spiel.getSpieler()[spielerID].getIsDran() )
				{
					System.out.println("GEHHT5 ID" + spielerID);
					if(this.schaltflaeche[spielerID].getPressedID().getIsPressed())
					{
						System.out.println("GEHHT6");
						if( this.spiel.legeLogikgatter(this.spiel.getSpieler()[spielerID], this.schaltflaeche[spielerID].getPressedID().getID(), this.getPressedID().getID() ) )
						{
							System.out.println("Dran:" + this.spiel.getAktuellerSpieler().getIsDran() +"ID:" + spielerID);
							this.spiel.nextSpielzug();
							
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
										for(int i = 0; i < BitfolgenSchaltflaeche.this.refreshSchaltflaechen.length; i++)
										{
											BitfolgenSchaltflaeche.this.refreshSchaltflaechen[i].refresh();
										}
								    }
								}
							});

							
							
							System.out.println("GEHHT1");
						}
						else
						{
							System.out.println("Schaltflaeche Index:"+this.schaltflaeche[spielerID].getPressedID().getID() +"Invert Bit:" +this.getPressedID().getID()); 
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

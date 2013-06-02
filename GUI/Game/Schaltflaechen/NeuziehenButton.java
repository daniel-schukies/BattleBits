package GUI.Game.Schaltflaechen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import GUI.Game.Refreshable;
import GUI.Game.SoundAusgabe;
import GUI.Game.Grafikverwaltung.Grafikspeicher;
import Logik.Spiel;

/**
 * Stellt den Button, um ein neues Gatter zu ziehen dar und verwaltet ihn
 * @author Daniel Schukies, Sebastian Junger
 *
 */
@SuppressWarnings("serial")
public class NeuziehenButton extends JPanel implements MouseListener
{
	Grafikspeicher grafik;
	Spiel spiel;
	LogikgatterSchaltflaeche[] schaltflaeche;
	Refreshable[] refreshSchaltflaechen;
	private SoundAusgabe sound;
	
	/**
	 * Erstellt den Button mit den uebergebenen Angaben
	 * @param xPos Position des Buttons auf der X - Achse
	 * @param yPos Position des Buttons auf der Y - Achse
	 * @param size Groesse des Buttons
	 * @param spiel Spiel, dessen Logik beachtet werden muss
	 * @param logikgatterschaltflaeche Logikgatterschaltflaeche, auf der zum Ziehen ein Gatter ausgewaehlt sein mutt
	 */
	public NeuziehenButton(int xPos, int yPos, int size, Spiel spiel,LogikgatterSchaltflaeche[] logikgatterschaltflaeche )
	{
		this.setLayout(null);
		this.setBounds(xPos, yPos, size, size);
		this.setPreferredSize(new Dimension(size, size));
		this.setBackground(new Color(0,0, 0,255) ); // Alpha-Channal nachlesen!
		
		this.spiel = spiel;
		this.sound  = new SoundAusgabe();
		
		this.schaltflaeche = logikgatterschaltflaeche;
		
		this.grafik = new Grafikspeicher(new Dimension(size,size), 3, false);
		
		this.grafik.setImage("New");
		
		this.grafik.getImage().addMouseListener(this);
		
		this.grafik.getImage().setBounds(0, 0, size, size);
		
		this.add(this.grafik.getImage());
		
		this.setOpaque(false);
	}
	
	/**
	 * Setzt die Schaltflaeche, die mit aktualisiert werden muessen, wenn ein neues Gatter
	 * gezogen wird
	 * @param refreshSchaltflaechen Array von Schaltflaechen, die aktualisierbar sein muessen
	 */
	public void setRefreshSchaltflaechen(Refreshable[] refreshSchaltflaechen)
	{
		this.refreshSchaltflaechen = refreshSchaltflaechen;
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		System.out.println("GEHHT4");
		//sucht den Spieler, der geklickt hat
		for(int spielerID = 0; spielerID < this.schaltflaeche.length; spielerID++)
		{
			System.out.println("GEHHT3");
			if( ( this.spiel.getAktuellerSpieler().getLogikgatter() == this.schaltflaeche[spielerID].getLogikgatter() ) && this.spiel.getSpieler()[spielerID].getIsDran() )
			{
				System.out.println("GEHHT5 ID" + spielerID);
				//sucht das Gatter, das ausgewaehlt wurde
				if(this.schaltflaeche[spielerID].getPressedID().getIsPressed())
				{
					this.spiel.getAktuellerSpieler().zieheNeuesLogikgatter(this.schaltflaeche[spielerID].getPressedID().getID());

					this.sound.playNeuZiehen();
					
					this.spiel.nextSpielzug();
					
					if(this.spiel.getAktuellerSpieler().getIsKI())
					{
						this.spiel.nextSpielzug();
					}
					
					this.schaltflaeche[spielerID].refresh();

					this.grafik.setVersion(0);
					 SwingUtilities.invokeLater(new Runnable() 
					 {
					    public void run() 
						   {
					    	//aktuallisiert die anderen Schaltflaechen mit
								if(NeuziehenButton.this.refreshSchaltflaechen != null)
								{
									for(int i = 0; i < NeuziehenButton.this.refreshSchaltflaechen.length; i++)
									{
										NeuziehenButton.this.refreshSchaltflaechen[i].refresh();
									}
								}
						   }
					 });
					break;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		this.grafik.setVersion(2);
		this.repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		this.grafik.setVersion(0);
		this.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		this.grafik.setVersion(1);
		this.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		this.grafik.setVersion(0);
		this.repaint();
		
	}
	
}

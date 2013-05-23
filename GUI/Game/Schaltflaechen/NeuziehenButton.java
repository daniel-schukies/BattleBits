package GUI.Game.Schaltflaechen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.Game.Refreshable;
import GUI.Game.Grafikverwaltung.Grafikspeicher;
import Logik.Spiel;

@SuppressWarnings("serial")
public class NeuziehenButton extends JPanel implements MouseListener
{
	Grafikspeicher grafik;
	Spiel spiel;
	LogikgatterSchaltflaeche[] schaltflaeche;
	Refreshable[] refreshSchaltflaechen;
	
	public NeuziehenButton(int xPos, int yPos, int size, Spiel spiel,LogikgatterSchaltflaeche[] logikgatterSchaltflaeche )
	{
		this.setLayout(null);
		this.setBounds(xPos, yPos, size, size);
		this.setPreferredSize(new Dimension(size, size));
		this.setBackground(new Color(0,0, 0,255) ); // Alpha-Channal nachlesen!
		
		this.spiel = spiel;
		
		this.schaltflaeche = logikgatterSchaltflaeche;
		
		this.grafik = new Grafikspeicher(new Dimension(size,size), 3, false);
		
		this.grafik.setImage("New");
		
		this.grafik.getImage().addMouseListener(this);
		
		//this.grafik.getImage()
		
		this.grafik.getImage().setBounds(0, 0, size, size);
		
		this.add(this.grafik.getImage());
		
		this.setOpaque(false);
	}
	
	public void setRefreshSchaltflaechen(Refreshable[] refreshSchaltflaechen)
	{
		this.refreshSchaltflaechen = refreshSchaltflaechen;
	}

	@Override
	public void mouseClicked(MouseEvent e) 
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
					this.spiel.getAktuellerSpieler().zieheNeuesLogikgatter(this.schaltflaeche[spielerID].getPressedID().getID());
					
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

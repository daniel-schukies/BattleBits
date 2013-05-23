package GUI.Game.Schaltflaechen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JPanel;

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
		
		this.grafik.getImage().setBounds(0, 0, size, size);
		
		this.add(this.grafik.getImage());
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
					try {
					    File neuziehen;
					    AudioInputStream stream;
					    AudioFormat format;
					    DataLine.Info info;
					    Clip clip;
					    
					    neuziehen = new File( "/home/sebi/battlebits/neuziehen.wav" );
					    stream = AudioSystem.getAudioInputStream(neuziehen);
					    format = stream.getFormat();
					    info = new DataLine.Info(Clip.class, format);
					    clip = (Clip) AudioSystem.getLine(info);
					    clip.open(stream);
					    clip.start();
					}
					catch (Exception ex) {
					    System.out.println( "Sound fehler ");
					}
					this.spiel.nextSpielzug();
					
					if(this.spiel.getAktuellerSpieler().getIsKI())
					{
						this.spiel.nextSpielzug();
					}
					
					this.grafik.setVersion(0);
					
					if(this.refreshSchaltflaechen != null)
					{
						for(int i = 0; i < this.refreshSchaltflaechen.length; i++)
						{
							this.refreshSchaltflaechen[i].refresh();
						}
					}
			
					break;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		this.grafik.setVersion(2);
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		this.grafik.setVersion(0);
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		this.grafik.setVersion(1);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		this.grafik.setVersion(0);
		
	}
	
}

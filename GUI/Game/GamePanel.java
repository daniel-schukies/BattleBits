package GUI.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import GUI.Game.Grafikverwaltung.ImageCreator;
import GUI.Game.Schaltflaechen.BitfolgenSchaltflaeche;
import GUI.Game.Schaltflaechen.LogikgatterSchaltflaeche;
import GUI.Game.Schaltflaechen.NeuziehenButton;
import GUI.Game.Schaltflaechen.SpielfeldSchaltflaeche;
import Logik.Spiel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	private Spiel spiel;
	private SpielfeldSchaltflaeche spielfeldSchaltflaeche1;
	private SpielfeldSchaltflaeche spielfeldSchaltflaeche2;
	
	private LogikgatterSchaltflaeche logikgatterSchaltflaeche1;
	private LogikgatterSchaltflaeche logikgatterSchaltflaeche2;
	
	private SpielerInfo spielerInfo1;
	private SpielerInfo spielerInfo2;
	
	private BitfolgenSchaltflaeche bitfolgenSchaltflaeche;
	
	private NeuziehenButton neuziehenButton;
	
	private int logikgatterLaenge;
	private int xCenter;
	private int yCenter;
	private Image background;
	
	
	
	public GamePanel(Dimension size)
	{
		this.setLayout(null);
		this.setPreferredSize(size);
		this.setBackground(new Color(0,0,0,255));
		setOpaque(false);
		
		if(size.getHeight() < size.getWidth())
		{
			this.logikgatterLaenge = (int)size.getHeight() / 6;
			this.xCenter = ((int)size.getWidth()-(this.logikgatterLaenge*9))/2;
			this.yCenter = 0;
		}
		else
		{
			this.logikgatterLaenge = (int)size.getWidth() / 9;
			this.yCenter = ((int)size.getHeight()-(this.logikgatterLaenge*6))/2;
			this.xCenter= 0;
		}
		
		this.background = new ImageCreator(size, 1, false).getImage("background1")[0].getImage();
		//this.background.setImage("background1");
		//this.background.getImage().setBounds(0,0, (int)size.getWidth(), (int)size.getHeight());

		
		
		
		this.spiel = new Spiel(true);
		
		FileAdmin filewriter = new FileAdmin();
		
		this.spiel.getSpieler()[0].setIsKI(filewriter.getKiZustand());
		this.spiel.getSpieler()[1].setIsKI(false);
		
		
		
		this.spiel.getSpieler()[0].setName(filewriter.getPlayer1Name());
		this.spiel.getSpieler()[1].setName(filewriter.getPlayer2Name());
		
		this.spielerInfo1 = new SpielerInfo(this.xCenter, this.yCenter, this.spiel.getSpieler()[0], new Dimension(this.logikgatterLaenge,this.logikgatterLaenge/2));
		this.spielerInfo2 = new SpielerInfo(this.xCenter+this.logikgatterLaenge*8, this.yCenter, this.spiel.getSpieler()[1], new Dimension(this.logikgatterLaenge,this.logikgatterLaenge/2));
		
		this.logikgatterSchaltflaeche1 = new  LogikgatterSchaltflaeche((this.logikgatterLaenge*5)+this.xCenter, (this.logikgatterLaenge*5)+this.yCenter, this.logikgatterLaenge, 4, this.spiel.getSpieler()[0].getLogikgatter(), false, false);
		this.logikgatterSchaltflaeche2 = new  LogikgatterSchaltflaeche(0+this.xCenter, 	(this.logikgatterLaenge*5)+this.yCenter, this.logikgatterLaenge, 4, this.spiel.getSpieler()[1].getLogikgatter(), true, false);

		
		this.spielfeldSchaltflaeche1 = new SpielfeldSchaltflaeche((this.logikgatterLaenge*5)+this.xCenter, (this.logikgatterLaenge/2)+this.yCenter,this.logikgatterSchaltflaeche1, this.spiel, this.spiel.getSpieler()[0], this.logikgatterLaenge*4, false);
		this.spielfeldSchaltflaeche2 = new SpielfeldSchaltflaeche(this.xCenter, (this.logikgatterLaenge/2)+this.yCenter, this.logikgatterSchaltflaeche2, this.spiel, this.spiel.getSpieler()[1], this.logikgatterLaenge*4, true);
		
		
		
		LogikgatterSchaltflaeche[] logikgatterSchaltflaechen = {this.logikgatterSchaltflaeche1, this.logikgatterSchaltflaeche2};
		this.neuziehenButton = new NeuziehenButton((this.logikgatterLaenge*4)+this.xCenter, (this.logikgatterLaenge*5)+this.yCenter, this.logikgatterLaenge, spiel, logikgatterSchaltflaechen);
	
		
		this.bitfolgenSchaltflaeche = new BitfolgenSchaltflaeche((this.logikgatterLaenge*4)+this.xCenter, this.yCenter, this.logikgatterLaenge, 5, this.spiel, this.spiel.getBitfolge(), logikgatterSchaltflaechen, false, true);
		
		// Erstelle RefreshSchaltflaechen-Array

		Refreshable[] reSchaltflaechen = {this.logikgatterSchaltflaeche1,this.logikgatterSchaltflaeche2, this.spielfeldSchaltflaeche1, this.spielfeldSchaltflaeche2};
		Refreshable[] reSchaltflaechen1 = {this.spielfeldSchaltflaeche1, this.bitfolgenSchaltflaeche};
		Refreshable[] reSchaltflaechen2 = {this.spielfeldSchaltflaeche2, this.bitfolgenSchaltflaeche};
		
		// Setze die RefreshSchaltflaechen
		
		this.neuziehenButton.setRefreshSchaltflaechen(reSchaltflaechen);
		this.bitfolgenSchaltflaeche.setRefreshSchaltflaechen(reSchaltflaechen);
		this.spielfeldSchaltflaeche1.setRefreshSchaltflaechen(reSchaltflaechen2);
		this.spielfeldSchaltflaeche2.setRefreshSchaltflaechen(reSchaltflaechen1);
		
		//this.add(this.spielerInfo1);
		
		this.add(this.spielerInfo1);
		this.add(this.spielerInfo2);
		
		this.add(this.spielfeldSchaltflaeche2);
		this.add(this.bitfolgenSchaltflaeche);
		this.add(this.spielfeldSchaltflaeche1);
		
		this.add(this.logikgatterSchaltflaeche2);
		this.add(this.neuziehenButton);
		this.add(this.logikgatterSchaltflaeche1);
		
		
		
		/*
		Grafikspeicher gr = new Grafikspeicher(new Dimension(80,80), 6, false);
		
		gr.setImage(new And());
		
		gr.getImage();
		
		JLabel jl = gr.getImage();
		this.add(jl);
		
	
		ImageCreator im = new ImageCreator(new Dimension(80,80), 6,false);
		
		JLabel jl = new JLabel( im.getImage(new And())[0] );
		this.add(jl);
		*/		
		
		
		

		
		
		//System.out.println(this.spiel.getSpieler()[0].getLogikgatter(0));
		/*
		
		//wichtig: Skallierung der Bilder nochmal anschauen!
		LogikgatterSchaltflaeche logikgatter = new LogikgatterSchaltflaeche(0, 0,  190, 4, this.spiel.getSpieler()[0].getLogikgatter(),null, true, false );
		
		
		for(int i =0; i < 20; i++)
		{
				this.spiel.getSpieler()[0].spieleAlsKI(this.spiel.getSpieler()[0].getSpielfeld(), this.spiel.getSpieler()[1].getSpielfeld(), this.spiel.getBitfolge());
		}
		
		SpielfeldSchaltflaeche ss = new SpielfeldSchaltflaeche(LogikgatterSchaltflaeche schaltflaeche, SpielfeldSchaltflaeche gegnerSpielfeldSchaltflaeche,Spiel spiel, Spieler spieler, int breite,boolean spiegeln );
		
		this.add(ss);
		
		//this.add(logikgatter.getDebugJLabel())
		
		this.add(logikgatter);
		
		
		
		
		*/
		new SoundAusgabe().playSound();
		this.setVisible(true);
	}
	
    public void paintComponent(Graphics g) 
    {
        g.drawImage(this.background, 0, 0, null);
    }
}

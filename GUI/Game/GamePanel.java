package GUI.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import GUI.Game.Grafikverwaltung.ImageCreator;
import GUI.Game.Schaltflaechen.BitfolgenSchaltflaeche;
import GUI.Game.Schaltflaechen.LogikgatterSchaltflaeche;
import GUI.Game.Schaltflaechen.NeuziehenButton;
import GUI.Game.Schaltflaechen.SpielfeldSchaltflaeche;
import Logik.Spiel;

/**
 * Die Klasse GamePanel ist dafür zuständig, die GUI aufzubauen, indem sie
 * alle Schaltﬂächen instanziert und einstellt.
 * 
 * @author Daniel Schukies, Sebastian Junger
 *
 */
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
	private int xCenter;//Mitte des Spielfeldes auf der x-Achse
	private int yCenter;//Mitte des Spielfeldes auf der y-Achse
	private Image background;
	
	
	
	/**
	 * Konfiguration der Schaltflaechen und Panels auf dem Spielfeld
	 * @param size Aufloesung des Spiels
	 * @param frame JFrame, indem sich das GamePanel befindet
	 */
	public GamePanel(Dimension size, JFrame frame)
	{
		this.setLayout(null);
		this.setPreferredSize(size);
		this.setBackground(new Color(0,0,0,255));
		setOpaque(false);// Muss gesetzt sein, dass anderen Panels auch transparent sein koennen.
		
		/**
		 * Berechne Aufloesung für eine Logikgatterlaenge.
		 * Spielfeld besteht aus 9 Logikgatterbreiten und 6 Logikgatterhoehen
		 * d.h. Aufloesung ist 9 zu 6.
		 */
		if((size.getHeight()/6) < (size.getWidth()/9))
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
		
		
		/**
		 * Erstelle Hintergrundgrafik
		 */
		this.background = new ImageCreator(size, 1, false).getImage("background1")[0].getImage();
		
		
		/**
		 * Instanziere Spiel-Logik
		 */
		this.spiel = new Spiel(true);
		
		FileAdmin filewriter = new FileAdmin();
		
		/**
		 * Setze einen Spieler als KI, wenn es eingestellt wurde.
		 */
		this.spiel.getSpieler()[0].setIsKI(filewriter.getKiZustand());
		this.spiel.getSpieler()[1].setIsKI(false);
		
		
		/**
		 * Setzt Namen der Spieler, die eingestellt wurden.
		 */
		this.spiel.getSpieler()[0].setName(filewriter.getPlayer2Name());
		this.spiel.getSpieler()[1].setName(filewriter.getPlayer1Name());
		
		/**
		 * Instanzierung der Schaltflaechen
		 * Aufloesung fuer jede Schaltflaeche wird aus der errechneten Logikgatterlaenge generiert.
		 */
		this.spielerInfo1 = new SpielerInfo(this.xCenter+this.logikgatterLaenge, this.yCenter, this.spiel.getSpieler()[1], new Dimension(this.logikgatterLaenge*2,(int)(this.logikgatterLaenge/1.5)));
		this.spielerInfo2 = new SpielerInfo(this.xCenter+this.logikgatterLaenge*7, this.yCenter, this.spiel.getSpieler()[0], new Dimension(this.logikgatterLaenge*2,(int)(this.logikgatterLaenge/1.5)));
		
		this.logikgatterSchaltflaeche1 = new  LogikgatterSchaltflaeche((this.logikgatterLaenge*5)+this.xCenter, (this.logikgatterLaenge*5)+this.yCenter, this.logikgatterLaenge, 4, this.spiel.getSpieler()[0].getLogikgatter(), false, false, true);
		this.logikgatterSchaltflaeche2 = new  LogikgatterSchaltflaeche(0+this.xCenter, 	(this.logikgatterLaenge*5)+this.yCenter, this.logikgatterLaenge, 4, this.spiel.getSpieler()[1].getLogikgatter(), true, false, true);

		this.spielfeldSchaltflaeche1 = new SpielfeldSchaltflaeche((this.logikgatterLaenge*5)+this.xCenter, (this.logikgatterLaenge/2)+this.yCenter,this.logikgatterSchaltflaeche1, this.spiel, this.spiel.getSpieler()[0], this.logikgatterLaenge*4, frame,false);
		this.spielfeldSchaltflaeche2 = new SpielfeldSchaltflaeche(this.xCenter, (this.logikgatterLaenge/2)+this.yCenter, this.logikgatterSchaltflaeche2, this.spiel, this.spiel.getSpieler()[1], this.logikgatterLaenge*4,frame, true);
		
		LogikgatterSchaltflaeche[] logikgatterSchaltflaechen = {this.logikgatterSchaltflaeche1, this.logikgatterSchaltflaeche2};
		this.neuziehenButton = new NeuziehenButton((this.logikgatterLaenge*4)+this.xCenter, (this.logikgatterLaenge*5)+this.yCenter, this.logikgatterLaenge, spiel, logikgatterSchaltflaechen);
	
		this.bitfolgenSchaltflaeche = new BitfolgenSchaltflaeche((this.logikgatterLaenge*4)+this.xCenter, this.yCenter, this.logikgatterLaenge, 5, this.spiel, this.spiel.getBitfolge(), logikgatterSchaltflaechen, false, true);
		
		
		/**
		 * Erstellen der Refresh-Schaltflaechen-Arrays
		 */
		Refreshable[] reSchaltflaechen = {this.logikgatterSchaltflaeche1,this.logikgatterSchaltflaeche2, this.spielfeldSchaltflaeche1, this.spielfeldSchaltflaeche2, this.spielerInfo1, this.spielerInfo2};
		Refreshable[] reSchaltflaechen1 = {this.spielfeldSchaltflaeche1, this.bitfolgenSchaltflaeche, this.spielerInfo1, this.spielerInfo2};
		Refreshable[] reSchaltflaechen2 = {this.spielfeldSchaltflaeche2, this.bitfolgenSchaltflaeche,  this.spielerInfo1, this.spielerInfo2};
		

		/**
		 * Setzt die Refresh-Schaltflaechen den jeweiligen Schaltlfaechen
		 */
		this.neuziehenButton.setRefreshSchaltflaechen(reSchaltflaechen);
		this.bitfolgenSchaltflaeche.setRefreshSchaltflaechen(reSchaltflaechen);
		this.spielfeldSchaltflaeche1.setRefreshSchaltflaechen(reSchaltflaechen2);
		this.spielfeldSchaltflaeche2.setRefreshSchaltflaechen(reSchaltflaechen1);
		
		
		/**
		 * adde die Schaltflaechen dem Panel
		 */
		this.add(this.spielerInfo1);
		this.add(this.spielerInfo2);
		
		this.add(this.spielfeldSchaltflaeche2);
		this.add(this.bitfolgenSchaltflaeche);
		this.add(this.spielfeldSchaltflaeche1);
		
		this.add(this.logikgatterSchaltflaeche2);
		this.add(this.neuziehenButton);
		this.add(this.logikgatterSchaltflaeche1);
		
		/**
		 * Starte Hintergrundmusik
		 */
		new SoundAusgabe().playSound();
		this.setVisible(true);
	}
	
	/**
	 * Zeichnet Hintergrundgrafik
	 */
    public void paintComponent(Graphics g) 
    {
        g.drawImage(this.background, 0, 0, null);
    }
}

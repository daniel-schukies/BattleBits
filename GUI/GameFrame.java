package GUI;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import Logik.Spiel;



public class GameFrame extends JFrame 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3975895020911336926L;
	/**
	 * 
	 */

	private Spiel spiel;
	private SpielfeldSchaltflaeche spielfeldSchaltflaeche1;
	private SpielfeldSchaltflaeche spielfeldSchaltflaeche2;
	
	private LogikgatterSchaltflaeche logikgatterSchaltflaeche1;
	private LogikgatterSchaltflaeche logikgatterSchaltflaeche2;
	
	private BitfolgenSchaltflaeche bitfolgenSchaltflaeche;
	
	private NeuziehenButton neuziehenButton;
	
	
	public GameFrame()
	{
		this.setLayout(new FlowLayout());
		this.setTitle("Battlebits");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 400);
		this.getContentPane().setBackground(Color.BLACK);
		
		this.spiel = new Spiel(true);
		
		this.spiel.getSpieler()[0].setIsKI(true);
		this.spiel.getSpieler()[1].setIsKI(false);
		
		
		this.logikgatterSchaltflaeche1 = new  LogikgatterSchaltflaeche(0, 0, 180, 4, this.spiel.getSpieler()[0].getLogikgatter(), false, false);
		this.logikgatterSchaltflaeche2 = new  LogikgatterSchaltflaeche(0, 0, 180, 4, this.spiel.getSpieler()[1].getLogikgatter(), true, false);

		
		this.spielfeldSchaltflaeche1 = new SpielfeldSchaltflaeche(this.logikgatterSchaltflaeche1, this.spiel, this.spiel.getSpieler()[0], 500, false);
		this.spielfeldSchaltflaeche2 = new SpielfeldSchaltflaeche(this.logikgatterSchaltflaeche2, this.spiel, this.spiel.getSpieler()[1], 500, true);
		
		LogikgatterSchaltflaeche[] logikgatterSchaltflaechen = {this.logikgatterSchaltflaeche1, this.logikgatterSchaltflaeche2};
		Refreshable[] reSchaltflaechen = {this.logikgatterSchaltflaeche1,this.logikgatterSchaltflaeche2, this.spielfeldSchaltflaeche1, this.spielfeldSchaltflaeche2};

		this.neuziehenButton = new NeuziehenButton(0, 0, 180, spiel, logikgatterSchaltflaechen, reSchaltflaechen);
	
		this.bitfolgenSchaltflaeche = new BitfolgenSchaltflaeche(0, 0, 110, 5, this.spiel, this.spiel.getBitfolge(), logikgatterSchaltflaechen, false, true);
		
		this.bitfolgenSchaltflaeche.setRefreshSchaltflaechen(reSchaltflaechen);
		
		Refreshable[] reSchaltflaechen1 = {this.spielfeldSchaltflaeche1, this.bitfolgenSchaltflaeche};
		Refreshable[] reSchaltflaechen2 = {this.spielfeldSchaltflaeche2, this.bitfolgenSchaltflaeche};
		
		this.spielfeldSchaltflaeche1.setRefreshSchaltflaechen(reSchaltflaechen2);
		this.spielfeldSchaltflaeche2.setRefreshSchaltflaechen(reSchaltflaechen1);
		
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
		this.setVisible(true);
	}
}

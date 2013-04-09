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
	
	public GameFrame()
	{
		this.setLayout(new FlowLayout());
		this.setTitle("Battlebits");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 400);
		this.getContentPane().setBackground(Color.BLACK);
		
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
		
		
		
		this.spiel = new Spiel(true);
		
		this.spiel.nextSpielzug();
		
		
		//System.out.println(this.spiel.getSpieler()[0].getLogikgatter(0));
		
		
		//wichtig: Skallierung der Bilder nochmal anschauen!
		LogikgatterSchaltflaeche logikgatter = new LogikgatterSchaltflaeche(0, 0,  190, 4, this.spiel.getSpieler()[0].getLogikgatter(),null, true, false );
		
		
		for(int i =0; i < 20; i++)
		{
				this.spiel.getSpieler()[0].spieleAlsKI(this.spiel.getSpieler()[0].getSpielfeld(), this.spiel.getSpieler()[1].getSpielfeld(), this.spiel.getBitfolge());
		}
		
		SpielfeldSchaltflaeche ss = new SpielfeldSchaltflaeche(this.spiel.getSpieler()[0].getSpielfeld(), 700,false);
		
		this.add(ss);
		
		//this.add(logikgatter.getDebugJLabel())
		
		this.add(logikgatter);
		
		
		
		
		
		this.setVisible(true);
	}
}

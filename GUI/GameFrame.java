package GUI;


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
		SpielerlogikgatterSchaltflaeche logikgatter = new SpielerlogikgatterSchaltflaeche(0, 0,  200, 4,  false, this.spiel.getSpieler()[0] );
		
		
		//this.add(logikgatter.getDebugJLabel());
		
		this.add(logikgatter);
		
		
		
		
		
		this.setVisible(true);
	}
}

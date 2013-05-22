package GUI.Game;


import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import GUI.Game.Grafikverwaltung.Grafikspeicher;
import GUI.Game.Grafikverwaltung.ImageCreator;
import GUI.Menue.Menue;



public class GameFrame extends JFrame 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3975895020911336926L;
	/**
	 * 
	 */
	private Menue menue;

	
	
	
	public GameFrame()
	{
		this.setLayout(null);
		//this.setResizable(false);
		this.setTitle("Battlebits");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocation(50,50);
		//this.getContentPane().setBackground(Color.BLACK);
		
		//GamePanel gp = new GamePanel(new Dimension(1700,900)); 
		//this.add(gp);
		

		//this.add(new MainMenue(0,0,new Dimension(800,600),new Menue()));
		
		this.menue = new Menue(this);
		this.add(menue);
		
		
		//this.add( new SpielereinstellungenMenue( 0,0,new Dimension(800,600), new Menue()) );
		
		//this.add( new OptionsMenue( 0 , 0 , new Dimension(800,600), new Menue()) );

		
		
		this.setVisible(true);
		
		
		//this.startGame(new Dimension(1200,700));
	}
	
	public void closeGameFrame()
	{
		this.removeAll();
		System.exit(0);
	}
	
	public void startGame(final Dimension aufloesung)
	{
		this.setSize((int)aufloesung.getWidth()+15,(int)aufloesung.getHeight()+40);
		this.remove(this.menue);

		this.getContentPane().setBackground(Color.BLACK);
		
		final Grafikspeicher loadingScreen;
		
		if(aufloesung.getWidth() < aufloesung.getHeight())
		{
			loadingScreen = new Grafikspeicher(new Dimension((int)aufloesung.getWidth(),(int)aufloesung.getWidth()), 1, false);
			loadingScreen.setImage("loading");
			loadingScreen.getImage().setBounds(0,(int)((aufloesung.getHeight()/2)-(aufloesung.getWidth()/2)), (int)aufloesung.getWidth(), (int)aufloesung.getHeight());
		}
		else
		{
			loadingScreen = new Grafikspeicher(new Dimension((int)aufloesung.getHeight(),(int)aufloesung.getHeight()), 1, false);
			loadingScreen.setImage("loading");
			loadingScreen.getImage().setBounds((int)((aufloesung.getWidth()/2)-(aufloesung.getHeight()/2)),0, (int)aufloesung.getWidth(), (int)aufloesung.getHeight());
		}
		
		
		this.add(loadingScreen.getImage());
		
		this.invalidate();
		this.validate();
		this.repaint();
		
		  SwingUtilities.invokeLater(new Runnable() 
		  {
			    public void run() 
			    {
			    	//ImageCreator.grafikenVorladen = true;
					GamePanel game = new GamePanel(aufloesung);
					game.setBounds(0, 0, (int)aufloesung.getWidth(), (int)aufloesung.getHeight());
					GameFrame.this.remove(loadingScreen.getImage());
					GameFrame.this.getContentPane().add(game);
					//ImageCreator.grafikenVorladen = false;
					System.out.println("-------------Spiel geladen---------------");
					GameFrame.this.repaint();
					GameFrame.this.invalidate();
					GameFrame.this.validate();
					GameFrame.this.repaint();
			    }
		  });
		

		
		
		//game.setBounds(0, 0, (int)aufloesung.getWidth(), (int)aufloesung.getHeight());
		
		//this.remove(loadingScreen.getImage());
	}
}

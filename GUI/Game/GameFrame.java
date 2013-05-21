package GUI.Game;


import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

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
		
		this.getContentPane().add(new Menue());
		
		//this.add( new SpielereinstellungenMenue( 0,0,new Dimension(800,600), new Menue()) );
		
		//this.add( new OptionsMenue( 0 , 0 , new Dimension(800,600), new Menue()) );

		
		this.setVisible(true);
		
	}
}

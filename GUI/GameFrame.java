package GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;



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
		this.setLayout(new FlowLayout());
		this.setTitle("Battlebits");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1900, 1000);
		this.getContentPane().setBackground(Color.BLACK);
		
		GamePanel gp = new GamePanel(new Dimension(1700,900)); 
		this.add(gp);
		
		this.setVisible(true);
		
	}
}

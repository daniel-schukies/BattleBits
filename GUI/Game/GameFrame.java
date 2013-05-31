package GUI.Game;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import GUI.Game.Grafikverwaltung.Grafikspeicher;
import GUI.Menue.Menue;



@SuppressWarnings("serial")
public class GameFrame extends JFrame 
{
	private Menue menue;
	
	public GameFrame()
	{
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Battlebits");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocation(50,50);
		
		this.menue = new Menue(this);
		this.add(menue);
		
		this.setVisible(true);
	}
	
	public void closeGameFrame()
	{
		this.removeAll();
		System.exit(0);
	}
	
	public void startGame()
	{
		FileAdmin filewriter = new FileAdmin();
		final Dimension aufloesung = new Dimension(filewriter.getWidth(), filewriter.getHeight());
		
		this.remove(this.menue);
		
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
		

		
		if(filewriter.getFullscreenZustand())
		{
			final JFrame fullscreenFrame = new JFrame(this.getName());
			
			fullscreenFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			fullscreenFrame.setSize(aufloesung);
			fullscreenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			fullscreenFrame.setUndecorated(true);
			
			fullscreenFrame.getContentPane().setBackground(Color.BLACK);
			
			fullscreenFrame.add(loadingScreen.getImage());
			
			this.invalidate();
			this.validate();
			this.repaint();
			
			fullscreenFrame.setVisible(true);
			
			 SwingUtilities.invokeLater(new Runnable() 
			 {
			    public void run() 
			    {
					GamePanel game = new GamePanel(aufloesung);
					game.setBounds(0, 0, (int)aufloesung.getWidth(), (int)aufloesung.getHeight());
					fullscreenFrame.remove(loadingScreen.getImage());

					fullscreenFrame.getContentPane().add(game);

					System.out.println("-------------Spiel geladen---------------");
					fullscreenFrame.repaint();
					fullscreenFrame.invalidate();
					fullscreenFrame.validate();
					fullscreenFrame.repaint();
					
					GameFrame.this.dispose();
				 }
			});
		}
		else
		{
			this.setSize((int)aufloesung.getWidth()+15,(int)aufloesung.getHeight()+40);
		
			this.getContentPane().setBackground(Color.BLACK);
			
			this.add(loadingScreen.getImage());
		
			this.invalidate();
			this.validate();
			this.repaint();
				
			 SwingUtilities.invokeLater(new Runnable() 
			 {
			    public void run() 
			    {
					GamePanel game = new GamePanel(aufloesung);
					game.setBounds(0, 0, (int)aufloesung.getWidth(), (int)aufloesung.getHeight());
					GameFrame.this.remove(loadingScreen.getImage());
					GameFrame.this.getContentPane().add(game);

					System.out.println("-------------Spiel geladen---------------");
					GameFrame.this.repaint();
					GameFrame.this.invalidate();
					GameFrame.this.validate();
					GameFrame.this.repaint();
				 }
			});
		}
		
	}
}

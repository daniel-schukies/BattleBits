package GUI.Menue;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import GUI.Game.GameFrame;

@SuppressWarnings("serial")
public class Menue extends JPanel
{
	private MainMenue mainMenue;
	private OptionsMenue optionsMenue;
	private SpielereinstellungenMenue playerMenue;
	
	private GameFrame gameFrame;

	public static final String START = "start";
	public static final String OPTIONS = "options";
	public static final String CLOSE = "close";
	public static final String SPIELER_EINSTELLUNGEN = "spielerEinstellungen";
	public static final String MAIN_MENUE = "main";
	
	public Menue(GameFrame gameFrame)
	{
		this.setBounds(0, 0, 800, 600);
		this.setPreferredSize(new Dimension(800,600));
		this.setLayout(new CardLayout());
		
		this.gameFrame = gameFrame;
		
		this.mainMenue = new MainMenue(0,0,new Dimension(800,600), this);
		this.optionsMenue = new OptionsMenue(0, 0, new Dimension(800,600) , this);
		this.playerMenue = new SpielereinstellungenMenue(0, 0, new Dimension(800, 600), this);
		

		this.add(this.mainMenue, Menue.MAIN_MENUE);
		this.add( this.optionsMenue,Menue.OPTIONS );
		this.add(this.playerMenue, Menue.SPIELER_EINSTELLUNGEN);

		((CardLayout)this.getLayout()).first(this);
		
		this.setVisible(true);
	}
	
	public void changeMenueCardTo(String menueName)
	{
		((CardLayout)this.getLayout()).show(this, menueName);
	}
	
	public void closeGame()
	{
		this.gameFrame.closeGameFrame();
	}
	
	
	public void startGame(Dimension aufloesung)
	{
		this.gameFrame.startGame();
	}
}

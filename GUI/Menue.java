package GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menue extends JPanel
{
	MainMenue mainMenue;
	JPanel p2;
	JPanel p3;

	public static final String START = "start";
	public static final String OPTIONS = "options";
	public static final String CLOSE = "close";
	public static final String SPIELER_EINSTELLUNGEN = "spielerEinstellungen";
	public static final String MAIN_MENUE = "main";
	
	public Menue()
	{
		this.setLayout(new CardLayout());
		
		this.mainMenue = new MainMenue(0,0,new Dimension(800,600), new Menue());
		this.p2 = new JPanel();
		this.p3 = new JPanel();
		
		this.add(this.mainMenue, Menue.START);
		this.p2.add(new JButton("P22222"));
		this.p3.add(new JButton("P3333333"));
	}
	
	public void changeMenueCardTo(String menueName)
	{
		((CardLayout)this.getLayout()).show(this, menueName);
	}
}

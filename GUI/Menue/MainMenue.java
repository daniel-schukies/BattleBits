package GUI.Menue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Game.Grafikverwaltung.Grafikspeicher;
import GUI.Game.Grafikverwaltung.ImageCreator;

/**
 * Die Klasse MainMenue erbt von JPanel
 * und wird genutzt um das Hauptmenue des
 * Spiels darzustellen.
 * @author Daniel Schukies, Sebastian Junger
 *
 */
@SuppressWarnings("serial")
public class MainMenue extends JPanel implements MouseListener
{
	private Grafikspeicher startButton;
	private Grafikspeicher optionsButton;
	private Grafikspeicher closeButton;
	
	private Image background;
	
	private static final Dimension buttonSize = new Dimension(355,134);//Button groesse
	
	private Menue menue;

	ImageCreator grafik;
	
	/**
	 * Ordnet Buttons fuer die Darstellung der Hauptmenues an.
	 * @param xPos X-Position des Panels
	 * @param yPos Y-Position des Panels
	 * @param size Aufloesung des Panels
	 * @param menue Referenz vom Menue, welches das CardLayout besitzt.
	 */
	public MainMenue(int xPos,int yPos, Dimension size, Menue menue) 
	{
		this.setLayout(null);
		this.setBackground(new Color(0,0,255));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		
		this.menue = menue;
		
		/**
		 * Instanzierung der Grafikspeicher
		 */
		this.startButton = new Grafikspeicher(new Dimension(355,134), 3, false);
		this.optionsButton = new Grafikspeicher(new Dimension(355,134), 3, false);
		this.closeButton = new Grafikspeicher(new Dimension(355,134), 3, false);
		this.background = new ImageCreator(new Dimension(800,600), 1, false).getImage("menue")[0].getImage();
		
		/**
		 * Erstellen der Grafiken
		 */
		this.startButton.setImage("start");
		this.optionsButton.setImage("options");
		this.closeButton.setImage("close");

		/**
		 * Groesse der Labels festlegen
		 */
		this.startButton.getImage().setBounds(225,140, (int)MainMenue.buttonSize.getWidth(),(int)MainMenue.buttonSize.getHeight());
		this.optionsButton.getImage().setBounds(225, 270, (int)MainMenue.buttonSize.getWidth(), (int)MainMenue.buttonSize.getHeight());
		this.closeButton.getImage().setBounds(225, 400, (int)MainMenue.buttonSize.getWidth(), (int)MainMenue.buttonSize.getHeight());
		
		/**
		 * Adden der Mouslistener
		 */
		this.startButton.getImage().addMouseListener(this);
		this.optionsButton.getImage().addMouseListener(this);
		this.closeButton.getImage().addMouseListener(this);
		
		/**
		 * Buttons dem Panels adden
		 */
		this.add(this.startButton.getImage());
		this.add(this.optionsButton.getImage());
		this.add(this.closeButton.getImage());
		
		this.repaint();
		
		this.setVisible(true);
		
		
	}
	
	/**
	 * Zeichnet den Hintergrund
	 */
    public void paintComponent(Graphics g) 
    {
        g.drawImage(this.background, 0, 0, null);
    }


	@Override
	public void mouseClicked(MouseEvent e) 
	{
	    /**
	     * Wechsel der Menues
	     */
		
		JLabel button = (JLabel)e.getSource();
		if(button == this.startButton.getImage())
		{
			this.menue.changeMenueCardTo(Menue.SPIELER_EINSTELLUNGEN);
		}
		else if(button == this.optionsButton.getImage())
		{
			this.menue.changeMenueCardTo(Menue.OPTIONS);
		}
		else if(button == this.closeButton.getImage())
		{
			this.menue.closeGame();
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) 
	{
		/**
		 * Mouseover Effekte
		 */
		JLabel button = (JLabel)e.getSource();
		if(button == this.startButton.getImage())
		{
			this.startButton.setVersion(2);
		}
		else if(button == this.optionsButton.getImage())
		{
			this.optionsButton.setVersion(2);
		}
		else if(button == this.closeButton.getImage())
		{
			this.closeButton.setVersion(2);
		}
	}


	@Override
	public void mouseExited(MouseEvent e) 
	{
		/**
		 * Mouseover Effekte
		 */
		
		JLabel button = (JLabel)e.getSource();
		if(button == this.startButton.getImage())
		{
			this.startButton.setVersion(0);
		}
		else if(button == this.optionsButton.getImage())
		{
			this.optionsButton.setVersion(0);
		}
		else if(button == this.closeButton.getImage())
		{
			this.closeButton.setVersion(0);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) 
	{
		/**
		 * Angeklickt Effekt
		 */
		JLabel button = (JLabel)e.getSource();
		if(button == this.startButton.getImage())
		{
			this.startButton.setVersion(1);
		}
		else if(button == this.optionsButton.getImage())
		{
			this.optionsButton.setVersion(1);
		}
		else if(button == this.closeButton.getImage())
		{
			this.closeButton.setVersion(1);
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) 
	{
		/**
		 * Angeklickt Effekt
		 */	
		JLabel button = (JLabel)e.getSource();
		if(button == this.startButton.getImage())
		{
			this.startButton.setVersion(0);
		}
		else if(button == this.optionsButton.getImage())
		{
			this.optionsButton.setVersion(0);
		}
		else if(button == this.closeButton.getImage())
		{
			this.closeButton.setVersion(0);
		}
		
	}
}

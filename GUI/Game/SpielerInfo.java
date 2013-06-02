package GUI.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import Logik.Spieler;
/**
 * Klasse zum Auslesen und Darstellen des Spielernamens
 * @author Daniel Schukies, Sebastian Junger
 *
 */
@SuppressWarnings("serial")
public class SpielerInfo extends JPanel implements Refreshable
{
	private Spieler spieler;
	private JLabel spielerName;
	/**
	 * Liest den Spielernamen aus dem uebergebenen Spieler aus und stellt diesen in einem
	 * JLabel mit der uebergebenen Groesse an der ebenfalls uebergebenen Position dar
	 * @param xPos Position des JLabels auf der X - Achse
	 * @param yPos Position des JLabels auf der Y - Achse
	 * @param spieler Spieler, aus dem der Name ausgelesen werden soll
	 * @param size Groesse des JLabels
	 */
	public SpielerInfo(int xPos, int yPos,Spieler spieler, Dimension size)
	{
		this.spieler = spieler;
		this.spielerName = new JLabel();
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		this.setBackground(new Color(0,0,0,0));
		//Macht das JLabel durchsichtig
		this.setOpaque(false);
		
		this.spielerName.setBackground(new Color(0,0,0,255));
		this.spielerName.setPreferredSize(size);
		
		this.spielerName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));

		//Farbe unseres Blaus
		this.spielerName.setForeground(new Color(105,210,231,100));
		this.spielerName.setText(this.spieler.getName());
		
		this.add(this.spielerName);
		
		this.refresh();
	}
	
	/**
	 * Aktualisiert, welcher Spieler dran ist und verringert dessen Transparenz
	 */
	@Override
	public void refresh() 
	{
		if(this.spieler.getIsDran())
		{
			this.spielerName.setForeground(new Color(105,210,231,180));
		}
		else
		{
			this.spielerName.setForeground(new Color(105,210,231,80));
		}
		
		this.repaint();
		
	}
}

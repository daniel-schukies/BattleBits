package GUI.Game.Schaltflaechen;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import GUI.Game.Refreshable;
import Logik.Logikgatter;

@SuppressWarnings("serial")
public class SpielfeldLogikgatterSchaltflaeche extends LogikgatterSchaltflaeche implements Refreshable
{
	private SpielfeldSchaltflaeche spielfeldSchaltflaeche;
	/**
	 * Erstellt eine SpielfeldLogikgatterSchaltflaeche
	 * @param xPos Position der Schaltflaeche auf der x - Achse
	 * @param yPos Position der Schaltflaeche auf der y - Achse
	 * @param size Groesse der Schaltflaeche
	 * @param anzahlGrafiken Anzahl der enthaltenen Logikgatter
	 * @param logikgatter enthaltene Logikgatter
	 * @param externerMouseListener MouseListener, um MouseEvents zu bearbeiten
	 * @param spielfeldschaltflaeche SpfielfeldSchaltflaeche, auf der das objekt dargestellt werden soll
	 * @param spiegeln spiegeln der Schaltflaeche
	 * @param isVertikal horizontale bzw. vertikale Anzeigee
	 */
	public SpielfeldLogikgatterSchaltflaeche(int xPos,int yPos, int  size, int anzahlGrafiken,Logikgatter[] logikgatter,MouseListener externerMouseListener,SpielfeldSchaltflaeche spielfeldschaltflaeche,boolean spiegeln, boolean isVertikal )
	{
		super(xPos,yPos,size,anzahlGrafiken,logikgatter,spiegeln,isVertikal );
		this.setBackground(new Color(0,0, 0,255) );
		this.setOpaque(false);
		this.spielfeldSchaltflaeche = spielfeldschaltflaeche;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
		JLabel button = (JLabel)e.getSource();
		if( this.getPressedID().getIsPressed() )
		{
			if(button == this.getImage(this.getPressedID().getID()))
			{
				this.spielfeldSchaltflaeche.resetAllImages();
				this.changeVersion(1, button);
				this.getPressedID().setIsPressed(false);
			}
			else
			{
				this.changeVersion(0, this.getImage(this.getPressedID().getID()));
				this.spielfeldSchaltflaeche.resetAllImages();
				this.changeVersion(1, button);
				this.setPressedID(this.getImageIDInfo( button ));
			}
		}
		else
		{
			this.spielfeldSchaltflaeche.resetAllImages();
			this.changeVersion(1, button);
			this.setPressedID(this.getImageIDInfo( button ));
		}
		
		this.spielfeldSchaltflaeche.sucheGeklicktenButton();
		this.spielfeldSchaltflaeche.beendeSpielzug();
	}
}

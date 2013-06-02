package GUI.Game.Schaltflaechen;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import GUI.Game.Refreshable;
import Logik.Logikgatter;

/**
 * Die SpielfeldLogikgatterSchaltﬂaeche ist eine LogikgatterSchaltﬂaeche, 
 * die speziell für die Nutzung innerhalb der SpielfeldSchaltﬂaeche angepasst wurde.
 * Sie besitzt alle Eigenschaften einer LogikgatterSchaltﬂaeche und zuseatzlich
 * eine Referenz auf die SpielfeldSchaltﬂaeche, in der sie verwendet wird.
 * 
 * @author Daniel Schukies, Sebastian Junger
 *
 */
@SuppressWarnings("serial")
public class SpielfeldLogikgatterSchaltflaeche extends LogikgatterSchaltflaeche implements Refreshable
{
	private SpielfeldSchaltflaeche spielfeldSchaltflaeche;
	
	public SpielfeldLogikgatterSchaltflaeche(int xPos,int yPos, int  size, int anzahlGrafiken,Logikgatter[] logikgatter,MouseListener externerMouseListener,SpielfeldSchaltflaeche spielfeldSchaltflaeche,boolean spiegeln, boolean isVertikal, boolean drawLogikgatterStatus )
	{
		super(xPos,yPos,size,anzahlGrafiken,logikgatter,spiegeln,isVertikal, drawLogikgatterStatus );
		this.setBackground(new Color(0,0, 0,255) );
		this.setOpaque(false);
		this.spielfeldSchaltflaeche = spielfeldSchaltflaeche;
	}
	

	@Override
	public void mouseClicked(MouseEvent e)
	{
		/**
		 * Aendert Grafkversion der Grafiken und ruft Methoden der Spielfeldschaltflaeche auf.
		 */
		JLabel button = (JLabel)e.getSource();
		if( this.getPressedID().getIsPressed() ) //Wenn Button geklickt
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

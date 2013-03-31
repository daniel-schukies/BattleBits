package GUI;

import java.awt.event.MouseEvent;
import Logik.Spieler;

public class SpielerlogikgatterSchaltflaeche extends SkallierbareSchaltflaeche
{
	
	private Spieler spieler;
	private int anzahlGrafiken;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8457018625255576914L;

	public SpielerlogikgatterSchaltflaeche(int xPos,int yPos, int  size, int anzahlGrafiken, boolean isVertikal, Spieler spieler)
	{
		super(xPos, yPos,  size,anzahlGrafiken,  isVertikal);
		
		this.spieler = spieler;
		
		this.anzahlGrafiken = anzahlGrafiken;
		
		this.refresh();
		
		for(int i = 0; i < anzahlGrafiken; i++)
		{
			this.add(super.getImage(i));
		}
	}
	
	
	public void refresh()
	{
		for(int i = 0; i < this.anzahlGrafiken; i++ )
		{
			super.setImage(i, this.spieler.getLogikgatter(0));
		}
	}
	/*
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		this.changeVersion(2, (JLabel)e.getSource());
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		this.changeVersion(0, (JLabel)e.getSource());
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		this.changeVersion(1, (JLabel)e.getSource());
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		this.changeVersion(0, (JLabel)e.getSource());
	}*/

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
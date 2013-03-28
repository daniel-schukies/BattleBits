package GUI;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.Position;

@SuppressWarnings("serial")
public abstract class SkallierbareSchaltflaeche extends JPanel implements MouseListener
{
	private Dimension size;
	private Position position;
	
	
	private Grafikspeicher[] grafikSpeicher;
	
	public SkallierbareSchaltflaeche(Dimension size, int anzahlGrafiken)
	{
		this.size = size;
	}
	
	private boolean changeVersion(int verionID, JLabel button)
	{		
		for(int i = 0;i < this.grafikSpeicher.length;i++)
		{
			if(button == this.grafikSpeicher[i].getImage())
			{
				return this.grafikSpeicher[i].setVersion(1);
			}
		}
		
		return false;
	}
	

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
	}
	
	/**
	 * getIsKilcked
	 * 
	 * 
	 * 
	 * 
	 */
}

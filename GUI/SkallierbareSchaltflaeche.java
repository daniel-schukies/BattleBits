package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Logik.Logikgatter;

@SuppressWarnings("serial")
public abstract class SkallierbareSchaltflaeche extends JPanel implements MouseListener
{
	private Dimension size;
	private Dimension buttonSize;

	
	private Grafikspeicher[] grafikSpeicher;
	
	public SkallierbareSchaltflaeche(int xPos,int yPos, int  size, int anzahlGrafiken, boolean isVertikal)
	{
		this.grafikSpeicher = new Grafikspeicher[anzahlGrafiken];
		
		FlowLayout layout = new FlowLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		
		if(isVertikal)
		{
			this.size = new Dimension(size, size*anzahlGrafiken);
			this.buttonSize = new Dimension((int)this.size.getWidth(), (int)this.size.getHeight()/anzahlGrafiken);
		}else
		{
			this.size = new Dimension( size*anzahlGrafiken, size );
			this.buttonSize = new Dimension( (int)this.size.getWidth()/anzahlGrafiken, (int)this.size.getHeight());
		}
		
		for(int i = 0; i < anzahlGrafiken; i++)
		{
			this.grafikSpeicher[i] = new Grafikspeicher(this.buttonSize, 6, false);
		}
		
		
		this.setPreferredSize(this.size);
		this.setBounds(xPos, yPos, (int)this.size.getWidth(), (int)this.size.getHeight());
		this.setLayout(layout);
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
	
	public IDInfo checkPressed(JLabel button) 
	{
		for(int i = 0; i < this.grafikSpeicher.length; i++)
		{
			if(button == this.grafikSpeicher[i].getImage())
			{
				return new IDInfo(i);
			}
		}
		
		return new IDInfo();
	}
		
	public void setImage(int index, Logikgatter logikgatter)
	{
		this.grafikSpeicher[index].setImage(logikgatter);
	}
	
	public void setImage(int index, boolean bit)
	{
		this.grafikSpeicher[index].setImage(bit);
	}
	
	public void setImage(int index)
	{
		this.grafikSpeicher[index].setImage();
	}
	
	public JLabel getImage(int index)
	{
		return this.grafikSpeicher[index].getImage();
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

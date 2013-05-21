package GUI.Game.Schaltflaechen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Game.IDInfo;
import GUI.Game.Grafikverwaltung.Grafikspeicher;
import Logik.Logikgatter;

@SuppressWarnings("serial")
public abstract class SkallierbareSchaltflaeche extends JPanel implements MouseListener
{
	private Dimension size;
	private Dimension buttonSize;
	private IDInfo pressedID;

	
	private Grafikspeicher[] grafikSpeicher;
	
	public SkallierbareSchaltflaeche(int xPos,int yPos, int  size, int anzahlGrafiken, int anzahlVersionen,boolean spiegeln, boolean isVertikal)
	{
		this.grafikSpeicher = new Grafikspeicher[anzahlGrafiken];
		
		FlowLayout layout = new FlowLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		
		this.pressedID = new IDInfo();
		
		this.setBackground(new Color(0,0, 0,255) ); // Alpha-Channal nachlesen!
		
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
			this.grafikSpeicher[i] = new Grafikspeicher(this.buttonSize, anzahlVersionen, spiegeln);
		}
		
		
		this.setPreferredSize(this.size);
		this.setBounds(xPos, yPos, (int)this.size.getWidth(), (int)this.size.getHeight());
		this.setLayout(layout);
	}
	
	protected boolean changeVersion(int versionID, JLabel button)
	{		
		for(int i = 0;i < this.grafikSpeicher.length;i++)
		{
			if(button == this.grafikSpeicher[i].getImage())
			{
				return this.grafikSpeicher[i].setVersion(versionID);
			}
		}
		return false;
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
	
	public void setAllImagesToVersion(int versionID)
	{
		for(int i = 0;i < this.grafikSpeicher.length;i++)
		{
			{
				this.grafikSpeicher[i].setVersion(versionID);
			}
		}
	}
	
	public IDInfo getPressedID()
	{
		return this.pressedID;
	}
	
	public void setPressedID(IDInfo pressedID)
	{
		this.pressedID = pressedID;
	}
	
	public JLabel getImage(int index)
	{
		return this.grafikSpeicher[index].getImage();
	}
	
	public IDInfo getImageIDInfo(JLabel button) 
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

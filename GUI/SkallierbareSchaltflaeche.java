package GUI;

import java.awt.Color;
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
	
	

	/**
	 * Setzt Logikgatter-Grafik an bestimmten Index im Grafikspeicher	
	 * @param index Index im Grafikspeicher
	 * @param logikgatter Logikgatter, das dargestellt werden soll.
	 */
	public void setImage(int index, Logikgatter logikgatter)
	{
		this.grafikSpeicher[index].setImage(logikgatter);
	}
	
	/**
	 * Setzt Bit-Grafiken an einen Index im Grafikspeicher
	 * @param index Index im Grafikspeicher
	 * @param bit Bit, welches dargestellt werden soll.
	 */
	public void setImage(int index, boolean bit)
	{
		this.grafikSpeicher[index].setImage(bit);
	}
	
	/**
	 * Setzt Platzhaltergrafik an uebergebenen Index
	 * @param index Index des Grafikspeichers
	 */
	public void setImage(int index)
	{
		this.grafikSpeicher[index].setImage();
	}
	
	/**
	 * Setzt alle Grafiken in der Schaltflaeche auf eine bestimmte Bildversion
	 * @param versionID Bildversions ID
	 */
	public void setAllImagesToVersion(int versionID)
	{
		for(int i = 0;i < this.grafikSpeicher.length;i++)
		{
			{
				this.grafikSpeicher[i].setVersion(versionID);
			}
		}
	}
	
	
	/**
	 * Gibt Referenz der aktuellen pressedID zurueck.
	 * @return Referenz des aktuellen pressedID Objekts
	 */
	public IDInfo getPressedID()
	{
		return this.pressedID;
	}
	
	/**
	 * Setzt die pressedID Info
	 * @param pressedID Info ueber Buttonstatus
	 */
	public void setPressedID(IDInfo pressedID)
	{
		this.pressedID = pressedID;
	}
	
	/**
	 * Gibt Image(JLabel) aus einem Index des Grafikspeichers zurueck
	 * @param index Ein Index des Grafikspeichers
	 * @return JLabel am Index
	 */
	public JLabel getImage(int index)
	{
		return this.grafikSpeicher[index].getImage();
	}
	
	
	/**
	 * Vergleicht uebergebenes Label mit denen im Grafikspeicher-Array
	 * @param button Label, welches geprueft werden soll
	 * @return IDInfo ob Label enthalten und, welche ID es besitzt.
	 */
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
	
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		/*
		 * Aendert die Version (auf eine andere Grafik) des angeklickten Buttons.
		 */
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
}

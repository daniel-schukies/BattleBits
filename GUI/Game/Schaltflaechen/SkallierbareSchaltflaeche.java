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

	/**
	 * Hier werden die Grafiken in Form von Labels gespeichert
	 */
	private Grafikspeicher[] grafikSpeicher;
	
	
	/**
	 * Hier wird Position und groesse der Schaltflaeche eingestellt
	 * @param xPos X-Position der Schaltflaeche
	 * @param yPos Y-Position der Schaltflaeche
	 * @param size Gibt die kuerzeste Seite der Quadratischen Schaltflaeche an
	 * @param anzahlGrafiken Anzahl der in der Schaltlfaeche enthaltenen Grafiken
	 * @param anzahlVersionen Anzahl der Verschiedenen Bildversionen, welche in der Schaltlfaeche verwendet werden
	 * @param spiegeln Ob die Inhalte der Schaltflaeche gespielt dargestellt werden sollen.
	 * @param isVertikal Ob die Rechteckige Schaltflaeche vertikal oder horizontal ausgerichtet sein soll.
	 */
	public SkallierbareSchaltflaeche(int xPos,int yPos, int  size, int anzahlGrafiken, int anzahlVersionen,boolean spiegeln, boolean isVertikal)
	{
		this.grafikSpeicher = new Grafikspeicher[anzahlGrafiken];
		
		/*
		 * Breite des Layout wird auf 0 reduziert.
		 */
		FlowLayout layout = new FlowLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		
		this.pressedID = new IDInfo();
		
		this.setOpaque(false);
		
		
		//this.getViewport().setOpaque(false);
		
		/**
		 * Aufloesung wird berechnet
		 */
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
		
		/**
		 * Relationen werden festgelegt.
		 */
		this.setPreferredSize(this.size);
		this.setBounds(xPos, yPos, (int)this.size.getWidth(), (int)this.size.getHeight());
		this.setLayout(layout);
		this.setBackground(new Color(0,0, 0,255) ); // Alpha-Channal nachlesen!

	}
	
	/**
	 * Aengert die Version des Uebergebenen Labels, wenn dieses im Grafikspeicher ist.
	 * @param versionID Version auf die, die Grafik geaengert werden soll.
	 * @param button JLabel, welches geaengert werden soll
	 * @return Ob Versionswechseln funktioniert hat
	 */
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
	 * Setzt die Grafik eines Logikgatters an einen bestimmten Index.	
	 * @param index Index, an welchen die Grfik gesetzt werden soll.
	 * @param logikgatter Logikgatter fuer welches die Grafik erstellt werden soll.
	 */
	public void setImage(int index, Logikgatter logikgatter, boolean drawLogikgatterStatus)
	{
		this.grafikSpeicher[index].setImage(logikgatter, drawLogikgatterStatus);
	}
	
	/**
	 * Setzt die Grafik eines Bits an einen bestimmten Index.	
	 * @param index Index, an welchen die Grfik gesetzt werden soll.
	 * @param bit Bit fuer welches die Grafik erstellt werden soll.
	 */
	public void setImage(int index, boolean bit)
	{
		this.grafikSpeicher[index].setImage(bit);
	}
	
	/**
	 * Setzt die Grafik eines Platzhalters an einen bestimmten Index.	
	 * @param index Index, an welchen die Grfik gesetzt werden soll.
	 */
	public void setImage(int index)
	{
		this.grafikSpeicher[index].setImage();
	}
	
	/**
	 * Setzt alle Grafiken im Grafikspeicher-Array auf eine Version.
	 * @param versionID Versions-ID auf welche die Grafiken gesetzt werden sollen.
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
	 * Gibt die PressedID zurueck
	 * @return PressedID Infos zum Button, welcher gedrueckt wurde.
	 */
	public IDInfo getPressedID()
	{
		return this.pressedID;
	}
	
	
	/**
	 * Setzt die PressedID.
	 * @param PressedID Infos zum Button, welcher gedrueckt wurde.
	 */
	public void setPressedID(IDInfo pressedID)
	{
		this.pressedID = pressedID;
	}
	
	
	/**
	 * GIbt JLabel mit Grafik von einem bestimmten Index zurueck
	 * @param index Index vom Grafikspeicher-Array
	 * @return JLabel mit Grafik.
	 */
	public JLabel getImage(int index)
	{
		return this.grafikSpeicher[index].getImage();
	}
	
	
	/**
	 * Gibt ImageID des uebergebenen Buttons zurueck.
	 * @param button JLabel aus Grafikspeicher-Array
	 * @return Infos zur ID der Grafik
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
	
	/**
	 * Aendere die Version der Grafiken, je nach Mausinteraktion
	 */
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

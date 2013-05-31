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
<<<<<<< HEAD:GUI/SkallierbareSchaltflaeche.java
	 * Setzt Logikgatter-Grafik an bestimmten Index im Grafikspeicher	
	 * @param index Index im Grafikspeicher
	 * @param logikgatter Logikgatter, das dargestellt werden soll.
	 */
	public void setImage(int index, Logikgatter logikgatter)
=======
	 * Setzt die Grafik eines Logikgatters an einen bestimmten Index.	
	 * @param index Index, an welchen die Grfik gesetzt werden soll.
	 * @param logikgatter Logikgatter fuer welches die Grafik erstellt werden soll.
	 */
	public void setImage(int index, Logikgatter logikgatter, boolean drawLogikgatterStatus)
>>>>>>> origin/Menue:GUI/Game/Schaltflaechen/SkallierbareSchaltflaeche.java
	{
		this.grafikSpeicher[index].setImage(logikgatter, drawLogikgatterStatus);
	}
	
	/**
<<<<<<< HEAD:GUI/SkallierbareSchaltflaeche.java
	 * Setzt Bit-Grafiken an einen Index im Grafikspeicher
	 * @param index Index im Grafikspeicher
	 * @param bit Bit, welches dargestellt werden soll.
=======
	 * Setzt die Grafik eines Bits an einen bestimmten Index.	
	 * @param index Index, an welchen die Grfik gesetzt werden soll.
	 * @param bit Bit fuer welches die Grafik erstellt werden soll.
>>>>>>> origin/Menue:GUI/Game/Schaltflaechen/SkallierbareSchaltflaeche.java
	 */
	public void setImage(int index, boolean bit)
	{
		this.grafikSpeicher[index].setImage(bit);
	}
	
	/**
<<<<<<< HEAD:GUI/SkallierbareSchaltflaeche.java
	 * Setzt Platzhaltergrafik an uebergebenen Index
	 * @param index Index des Grafikspeichers
=======
	 * Setzt die Grafik eines Platzhalters an einen bestimmten Index.	
	 * @param index Index, an welchen die Grfik gesetzt werden soll.
>>>>>>> origin/Menue:GUI/Game/Schaltflaechen/SkallierbareSchaltflaeche.java
	 */
	public void setImage(int index)
	{
		this.grafikSpeicher[index].setImage();
	}
	
	/**
<<<<<<< HEAD:GUI/SkallierbareSchaltflaeche.java
	 * Setzt alle Grafiken in der Schaltflaeche auf eine bestimmte Bildversion
	 * @param versionID Bildversions ID
=======
	 * Setzt alle Grafiken im Grafikspeicher-Array auf eine Version.
	 * @param versionID Versions-ID auf welche die Grafiken gesetzt werden sollen.
>>>>>>> origin/Menue:GUI/Game/Schaltflaechen/SkallierbareSchaltflaeche.java
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
	
<<<<<<< HEAD:GUI/SkallierbareSchaltflaeche.java
	
	/**
	 * Gibt Referenz der aktuellen pressedID zurueck.
	 * @return Referenz des aktuellen pressedID Objekts
=======
	/**
	 * Gibt die PressedID zurueck
	 * @return PressedID Infos zum Button, welcher gedrueckt wurde.
>>>>>>> origin/Menue:GUI/Game/Schaltflaechen/SkallierbareSchaltflaeche.java
	 */
	public IDInfo getPressedID()
	{
		return this.pressedID;
	}
	
<<<<<<< HEAD:GUI/SkallierbareSchaltflaeche.java
	/**
	 * Setzt die pressedID Info
	 * @param pressedID Info ueber Buttonstatus
=======
	
	/**
	 * Setzt die PressedID.
	 * @param PressedID Infos zum Button, welcher gedrueckt wurde.
>>>>>>> origin/Menue:GUI/Game/Schaltflaechen/SkallierbareSchaltflaeche.java
	 */
	public void setPressedID(IDInfo pressedID)
	{
		this.pressedID = pressedID;
	}
	
<<<<<<< HEAD:GUI/SkallierbareSchaltflaeche.java
	/**
	 * Gibt Image(JLabel) aus einem Index des Grafikspeichers zurueck
	 * @param index Ein Index des Grafikspeichers
	 * @return JLabel am Index
=======
	
	/**
	 * GIbt JLabel mit Grafik von einem bestimmten Index zurueck
	 * @param index Index vom Grafikspeicher-Array
	 * @return JLabel mit Grafik.
>>>>>>> origin/Menue:GUI/Game/Schaltflaechen/SkallierbareSchaltflaeche.java
	 */
	public JLabel getImage(int index)
	{
		return this.grafikSpeicher[index].getImage();
	}
	
	
	/**
<<<<<<< HEAD:GUI/SkallierbareSchaltflaeche.java
	 * Vergleicht uebergebenes Label mit denen im Grafikspeicher-Array
	 * @param button Label, welches geprueft werden soll
	 * @return IDInfo ob Label enthalten und, welche ID es besitzt.
=======
	 * Gibt ImageID des uebergebenen Buttons zurueck.
	 * @param button JLabel aus Grafikspeicher-Array
	 * @return Infos zur ID der Grafik
>>>>>>> origin/Menue:GUI/Game/Schaltflaechen/SkallierbareSchaltflaeche.java
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
	
<<<<<<< HEAD:GUI/SkallierbareSchaltflaeche.java
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
=======
	/**
	 * Aendere die Version der Grafiken, je nach Mausinteraktion
>>>>>>> origin/Menue:GUI/Game/Schaltflaechen/SkallierbareSchaltflaeche.java
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

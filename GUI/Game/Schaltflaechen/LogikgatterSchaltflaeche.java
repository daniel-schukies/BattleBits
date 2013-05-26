package GUI.Game.Schaltflaechen;

import java.awt.event.MouseEvent;


import javax.swing.JLabel;

import GUI.Game.IDInfo;
import GUI.Game.Refreshable;
import Logik.Logikgatter;

public class LogikgatterSchaltflaeche extends SkallierbareSchaltflaeche implements Refreshable
{
	/**
	 * Logikgatter, welche dargestellt werden.
	 */
	private Logikgatter[] logikgatter;

	private int anzahlGrafiken;
	private static final int ANZAHLVERSIONEN = 4;
	private Logikgatter[] logikgatterCache;
	private int refreshCounter;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8457018625255576914L;

	/**
	 * Legt Position und Größe fest. Zudem wird der MouseListener den Grafiken hinzugefügt und die Logikgatter gespeichert.
	 * @param xPos X-Position der Schaltflaeche
	 * @param yPos Y-Position der Schaltflaeche
	 * @param size Gibt die kuerzeste Seite der Quadratischen Schaltflaeche an
	 * @param anzahlGrafiken Anzahl der in der Schaltlfaeche enthaltenen Grafiken
	 * @param anzahlVersionen Anzahl der Verschiedenen Bildversionen, welche in der Schaltlfaeche verwendet werden
	 * @param spiegeln Ob die Inhalte der Schaltflaeche gespielt dargestellt werden sollen.
	 * @param isVertikal Ob die Rechteckige Schaltflaeche vertikal oder horizontal ausgerichtet sein soll.
	 */
	public LogikgatterSchaltflaeche(int xPos,int yPos, int  size, int anzahlGrafiken,Logikgatter[] logikgatter,boolean spiegeln, boolean isVertikal )
	{
		super(xPos, yPos,  size,anzahlGrafiken,LogikgatterSchaltflaeche.ANZAHLVERSIONEN, spiegeln, isVertikal);
		
		this.refreshCounter = 0;
		
		this.anzahlGrafiken = anzahlGrafiken;
		
		this.logikgatter = logikgatter;
		
		this.logikgatterCache = new Logikgatter[logikgatter.length];
		
		this.refresh();
		
		for(int i = 0; i < anzahlGrafiken; i++)
		{
			super.getImage(i).addMouseListener(this);
			this.add(super.getImage(i));
		}		
	}
	
	
	/**
	 * Refresh der Logikgattergrafiken
	 */
	public void refresh()
	{
		for(int i = 0; i < this.anzahlGrafiken; i++ )
		{
			if( (this.logikgatterCache[i] != this.logikgatter[i]) || this.refreshCounter == 0 )
			{
				if(this.logikgatter[i] == null)
				{
					this.setImage(i);
				}
				else
				{
					this.setImage(i,this.logikgatter[i]);
					this.setImageToLogikgatterStatus(logikgatter[i], this.getImage(i));
				}
				
				System.out.println("Refresh OK");
			}
			else
			{
				System.out.println("Refresh FAIL");
			}
		}
		
		this.refreshCounter++;
		
		for(int i = 0; i < this.logikgatter.length; i++)
		{
			this.logikgatterCache[i] = this.logikgatter[i];
		}
		
		this.setPressedID(new IDInfo());
	}
	
	/**
	 * Setzt Logikgattergrafik auf den entsprechenden Status.
	 * @param logikgatter Logikgatter das beachtet werden soll.
	 * @param button Hier wird die Grafik geändert.
	 */
	public void setImageToLogikgatterStatus(Logikgatter logikgatter, JLabel button)
	{
		if(logikgatter != null)
		{
			if(logikgatter.getIsAktiv())
			{
				this.changeVersion(0, button);
			}
			else
			{
				this.changeVersion(3, button);
			}
		}
		else
		{
			this.changeVersion(0, button);
		}
	}
	
	/**
	 * Alle Logikgattergrafiken werden den richtien Status gesetzt.
	 */
	public void setAllImagesToLogikgatterStatus()
	{
		for(int i = 0; i < this.anzahlGrafiken; i++)
		{
			this.setImageToLogikgatterStatus(this.logikgatter[i],this.getImage(i));
		}
	}
	
	/**
	 * Gibts Logikgatterarray zuruck
	 * @return Array mit Logikgattern, welche dargestellt werden.
	 */
	public Logikgatter[] getLogikgatter()
	{
		return this.logikgatter;
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		JLabel button = (JLabel)e.getSource();
		if(this.getPressedID().getIsPressed())
		{
			if( button != this.getImage(this.getPressedID().getID()) )
			{
				super.changeVersion(2, (JLabel)e.getSource()); // Mouseover
			}
		}
		else
		{
			super.changeVersion(2, (JLabel)e.getSource()); //Mouseover
		}
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		JLabel button = (JLabel)e.getSource();
		if(this.getPressedID().getIsPressed())
		{
			if( button != this.getImage(this.getPressedID().getID()) )
			{
				this.changeVersion(0, (JLabel)e.getSource()); //Normal
			}
		}
		else
		{
			super.changeVersion(0, (JLabel)e.getSource());//Normal
		}
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		//this.changeVersion(1, (JLabel)e.getSource());
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		//this.changeVersion(0, (JLabel)e.getSource());
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
			
		JLabel button = (JLabel)e.getSource();
		if( this.getPressedID().getIsPressed() )
		{
			if(button == this.getImage(this.getPressedID().getID()))
			{
				this.changeVersion(0, button);
				this.getPressedID().setIsPressed(false);//Speichere Zustand
			}
			else
			{
				this.changeVersion(0, this.getImage(this.getPressedID().getID()));
				this.changeVersion(1, button);// angeklickt
				this.setPressedID(this.getImageIDInfo( button ));
			}
		}
		else
		{
			this.changeVersion(1, button);
			this.setPressedID(this.getImageIDInfo( button ));
		}

		
	}
}

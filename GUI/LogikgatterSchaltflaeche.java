package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import Logik.Logikgatter;

public class LogikgatterSchaltflaeche extends SkallierbareSchaltflaeche
{
	
	private Logikgatter[] logikgatter;
	private IDInfo pressedID;
	private int anzahlGrafiken;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8457018625255576914L;

	public LogikgatterSchaltflaeche(int xPos,int yPos, int  size, int anzahlGrafiken,Logikgatter[] logikgatter,MouseListener externerMouseListener,boolean spiegeln, boolean isVertikal )
	{
		super(xPos, yPos,  size,anzahlGrafiken, externerMouseListener, spiegeln,  isVertikal);
		
		this.anzahlGrafiken = anzahlGrafiken;
		
		this.logikgatter = logikgatter;
		
		this.pressedID = new IDInfo();
		
		this.refresh();
		
		for(int i = 0; i < anzahlGrafiken; i++)
		{
			super.getImage(i).addMouseListener(this);
			this.add(super.getImage(i));
		}		
	}
	
	
	
	public void refresh()
	{
		for(int i = 0; i < this.anzahlGrafiken; i++ )
		{
			if(this.logikgatter[i] == null)
			{
				this.setImage(i);
			}
			else
			{
				this.setImage(i,this.logikgatter[i]);
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
	

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		JLabel button = (JLabel)e.getSource();
		if(this.pressedID.getIsPressed())
		{
			if( button != this.getImage(this.pressedID.getID()) )
			{
				super.changeVersion(2, (JLabel)e.getSource());
			}
		}
		else
		{
			super.changeVersion(2, (JLabel)e.getSource());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		JLabel button = (JLabel)e.getSource();
		if(this.pressedID.getIsPressed())
		{
			if( button != this.getImage(this.pressedID.getID()) )
			{
				this.changeVersion(0, (JLabel)e.getSource());
			}
		}
		else
		{
			super.changeVersion(0, (JLabel)e.getSource());
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
		if( this.pressedID.getIsPressed() )
		{
			if(button == this.getImage(this.pressedID.getID()))
			{
				this.changeVersion(0, button);
				this.pressedID.setIsPressed(false);
			}
			else
			{
				this.changeVersion(0, this.getImage(this.pressedID.getID()));
				this.changeVersion(1, button);
				this.pressedID = this.getImageIDInfo( button );
			}
		}
		else
		{
			this.changeVersion(1, button);
			this.pressedID = this.getImageIDInfo( button );
		}

		
	}
}

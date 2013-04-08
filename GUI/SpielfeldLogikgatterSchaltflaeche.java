package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import Logik.Logikgatter;

@SuppressWarnings("serial")
public class SpielfeldLogikgatterSchaltflaeche extends LogikgatterSchaltflaeche 
{
	private SpielfeldSchaltflaeche spielfeldSchaltflaeche;
	
	public SpielfeldLogikgatterSchaltflaeche(int xPos,int yPos, int  size, int anzahlGrafiken,Logikgatter[] logikgatter,MouseListener externerMouseListener,SpielfeldSchaltflaeche spielfeldSchaltflaeche,boolean spiegeln, boolean isVertikal )
	{
		super(xPos,yPos,size,anzahlGrafiken,logikgatter,externerMouseListener,spiegeln,isVertikal );

		this.spielfeldSchaltflaeche = spielfeldSchaltflaeche;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
		JLabel button = (JLabel)e.getSource();
		if( this.getPressedID().getIsPressed() )
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
	}
}

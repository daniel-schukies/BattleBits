package GUI.Menue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JTextField;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import GUI.Game.FileAdmin;
//import GUI.Game.FileAdmin;
//import GUI.Game.FileAdmin;
import GUI.Game.Grafikverwaltung.Grafikspeicher;
import GUI.Game.Grafikverwaltung.ImageCreator;
import java.awt.event.*;

/**
 * Stellt das Optionsmenue dar und verwaltet es
 * @author Daniel Schukies, Sebastian Junger
 *
 */
@SuppressWarnings("serial")
public class OptionsMenue extends JPanel implements MouseListener {

	
	private JTextField width;
	private JTextField height;
	
	private Image image;
	private ImageCreator imagecreator;
	
	private Grafikspeicher grafikcacheButton;
	private Grafikspeicher backButton;
	private Grafikspeicher hardCoreButton;
	private Grafikspeicher musicButton;
	private Grafikspeicher soundButton;
	private Grafikspeicher fullscreenButton;
	private boolean grafikCacheButtonKlicked , backButtonKlicked, hardcoreButtonKlicked, musicButtonKlicked, soundButtonKlicked , fullscreenButtonKlicked;
	private FileAdmin fileadmin;
	private Menue menue;

	
	/**
	 * Stellt das Optionsmenue dar
	 * @param xPos Position des Menues auf der X - Achse
	 * @param yPos Position des Menues auf der Y - Achse
	 * @param size Groesse des Menues
	 * @param menue Menue, welches alle anderen Menues enthaelt
	 */
	public OptionsMenue( int xPos,int yPos, Dimension size,Menue menue )
	{
		//Erstellen und Darstellen der ganzen Buttons
		this.setLayout( null );
		this.setBackground(new Color(20,60,20));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		this.imagecreator = new ImageCreator(new Dimension(800,600),1, false);
		this.image = this.imagecreator.getImage("MenuOptionsBack")[0].getImage();
		
		this.menue = menue;
		this.fileadmin = new FileAdmin();

		this.grafikCacheButtonKlicked = false;
		this.backButtonKlicked = false;
		this.hardcoreButtonKlicked = false;
		this.musicButtonKlicked = false;
		this.soundButtonKlicked = false;
		this.fullscreenButtonKlicked = false;
		
		this.height = new JTextField( String.valueOf( fileadmin.getHeight() ), 10 );
		this.height.setOpaque( false );
		this.width = new JTextField( String.valueOf( fileadmin.getWidth() ) , 10 );
		this.width.setOpaque( false );
		

		this.grafikcacheButton = new Grafikspeicher(new Dimension(133,47),3, false);
		this.grafikcacheButton.setImage( "on" );
		
		this.backButton = new Grafikspeicher(new Dimension(133,47),3,false);
		this.backButton.setImage( "back" );
		
		this.hardCoreButton = new Grafikspeicher(new Dimension( 133,47 ), 3, false);
		this.hardCoreButton.setImage( "on" );
		
		this.musicButton = new Grafikspeicher(new Dimension( 133,47 ), 3, false);
		this.musicButton.setImage( "on" );

		this.soundButton = new Grafikspeicher(new Dimension( 133,47 ), 3, false);
		this.soundButton.setImage( "on" );
		
		this.fullscreenButton = new Grafikspeicher(new Dimension( 133, 47 ), 3, false);
		this.fullscreenButton.setImage( "on" );	
		
		//Rahmen von textfields durchsichtig machen
		width.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		height.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.width.setBackground( new Color(0,0,0,255) );
		this.height.setBackground( new Color(0,0,0,255) );
		
		this.width.setForeground( new Color(40,40,40) );
		this.height.setForeground( new Color(40,40,40) );

		
		this.width.setFont( new Font(Font.SANS_SERIF,Font.BOLD,40) );
		this.height.setFont( new Font(Font.SANS_SERIF,Font.BOLD,40) );
		
		
		//hoehe 45 für das Textfield wird benötigt, weil die Buchstaben sonst abgeschnitten werden
		 this.width.setBounds( 430, 480, 120, 55 );
		 this.height.setBounds( 583, 480, 100, 55 );

		this.add( this.width );
	    this.add( this.height);
	    
	    this.grafikcacheButton.getImage().setBounds( 485,210,133,47 );
	    this.backButton.getImage().setBounds( 0,0,133,47 );
	    this.hardCoreButton.getImage().setBounds(485, 370, 133, 47);
	    this.musicButton.getImage().setBounds( 485, 263, 133, 47 );
	    this.soundButton.getImage().setBounds( 485, 318, 133, 47 );	    
	    this.fullscreenButton.getImage().setBounds( 485, 423, 133, 47 );
	    
	    this.grafikcacheButton.getImage().addMouseListener( this );
	    this.backButton.getImage().addMouseListener( this );
	    this.hardCoreButton.getImage().addMouseListener( this );
	    this.musicButton.getImage().addMouseListener( this );
	    this.soundButton.getImage().addMouseListener( this );
	    this.fullscreenButton.getImage().addMouseListener( this );
	    
	    this.add( this.grafikcacheButton.getImage() );
	    this.add( this.backButton.getImage() );
	    this.add( this.hardCoreButton.getImage() );
	    this.add( this.musicButton.getImage() );
	    this.add( this.soundButton.getImage() );
	    this.add( this.fullscreenButton.getImage() );
	    
	    //
	    //Zustand der Buttons dem Inhalt der Datei anpassen
	    //
	    if( this.fileadmin.getCacheZustand() )
	    {
	    	this.grafikcacheButton.setVersion( 1 );
	    	this.grafikCacheButtonKlicked=true;
	    }
	    
	    if( this.fileadmin.getHardCoreZustand() )
	    {
	    	this.hardCoreButton.setVersion( 1 );
	    	this.hardcoreButtonKlicked=true;
	    }
	    
	    if( this.fileadmin.getMusicZustand() )
	    {
	    	this.musicButton.setVersion( 1 );
	    	this.musicButtonKlicked = true;
	    }
	    
	    if( this.fileadmin.getSoundZustand() )
	    {
	    	this.soundButton.setVersion( 1 );
	    	this.soundButtonKlicked = true;
	    }
		
	    if( this.fileadmin.getFullscreenZustand() )
	    {
	    	this.fullscreenButton.setVersion( 1 );
	    	this.fullscreenButtonKlicked = true;
	    }
	    
		this.setVisible(true);

		
		
		
	}
	/**
	 * Zeichnet den Hintergrund	
	 */
	public void paintComponent(Graphics g) 
	{
	    g.drawImage(this.image, 0, 0, null);
	}

	    
	    @Override
	public void mouseExited(MouseEvent e) 
	{	//wenn vorher nicht angeklickt
		if( (JLabel)e.getSource() == this.backButton.getImage() && !( this.backButtonKlicked ) )
		{
			this.backButton.setVersion( 0 );
		}else
		{	//wenn vorher angeklickt
			if( (JLabel)e.getSource() == this.backButton.getImage() && this.backButtonKlicked )
			{
				this.backButton.setVersion( 1 );
			}
		}
		
		if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() && !( this.grafikCacheButtonKlicked ) )
		{
			this.grafikcacheButton.setVersion( 0 );
			this.repaint();
		}else
		{
			if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() && this.grafikCacheButtonKlicked  )
			{
				this.grafikcacheButton.setVersion( 1 );
				this.repaint();
			}
		}
		
		if( (JLabel)e.getSource() == this.hardCoreButton.getImage() && !( this.hardcoreButtonKlicked ) )
		{
			this.hardCoreButton.setVersion( 0 );
			this.repaint();
		}else
		{
			if( (JLabel)e.getSource() == this.hardCoreButton.getImage() && this.hardcoreButtonKlicked  )
			{
				this.hardCoreButton.setVersion( 1 );
				this.repaint();
			}
		}
		
		if( (JLabel)e.getSource() == this.musicButton.getImage() && !( this.musicButtonKlicked ) )
		{
			this.musicButton.setVersion( 0 );
			this.repaint();
		}else
		{
			if( (JLabel)e.getSource() == this.musicButton.getImage() && this.musicButtonKlicked  )
			{
				this.musicButton.setVersion( 1 );
				this.repaint();
			}
		}
		
		if( (JLabel)e.getSource() == this.soundButton.getImage() && !( this.soundButtonKlicked ) )
		{
			this.soundButton.setVersion( 0 );
			this.repaint();
		}else
		{
			if( (JLabel)e.getSource() == this.soundButton.getImage() && this.soundButtonKlicked  )
			{
				this.soundButton.setVersion( 1 );
				this.repaint();
			}
		}
		
		if( (JLabel)e.getSource() == this.fullscreenButton.getImage() && !( this.fullscreenButtonKlicked ) )
		{
			this.fullscreenButton.setVersion( 0 );
			this.repaint();
		}else
		{
			if( (JLabel)e.getSource() == this.fullscreenButton.getImage() && this.fullscreenButtonKlicked  )
			{
				this.fullscreenButton.setVersion( 1 );
				this.repaint();
			}
		}
				
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		//wird immer auf Version 1 gesetzt. w
		//Wenn bereits angeklickt, sieht man keinen unterschied
		if( (JLabel)e.getSource() == this.backButton.getImage() )
		{
			this.backButton.setVersion( 1 );
		}
		
		
		if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() )
		{
			this.grafikcacheButton.setVersion( 1 );
		}
		
		if( (JLabel)e.getSource() == this.hardCoreButton.getImage() )
		{
			this.hardCoreButton.setVersion( 1 );
		}
		
		if( (JLabel)e.getSource() == this.musicButton.getImage() )
		{
			this.musicButton.setVersion( 1 );
		}
		
		if( (JLabel)e.getSource() == this.soundButton.getImage() )
		{
			this.soundButton.setVersion( 1 );
		}
		
		if( (JLabel)e.getSource() == this.fullscreenButton.getImage() )
		{
			this.fullscreenButton.setVersion( 1 );
		}


	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//backgrafikspeicher und grafikspeicher brauchen kein zuruecksetzen auf Version 0, da beim anklicken in ein anderes
		//Fenster gwechselt wird
		if((JLabel)e.getSource() == this.backButton.getImage())
		{	//gegen Buchstaben in der Eingabe
			try{
				//speichern der Aufloesung
				if ( this.fileadmin.setHeight( Integer.parseInt( this.height.getText() ) ) && this.fileadmin.setWidth( Integer.parseInt( this.width.getText())))
				{
					this.fileadmin.setCache( this.grafikCacheButtonKlicked );
					this.fileadmin.setHardCoreMode( this.hardcoreButtonKlicked );
					this.fileadmin.setMusic( this.musicButtonKlicked );
					this.fileadmin.setSound( this.soundButtonKlicked );
					this.fileadmin.setFullscreen( this.fullscreenButtonKlicked );
					this.menue.changeMenueCardTo(Menue.MAIN_MENUE);
					this.backButton.setVersion(0);
				}else
				{
					//rote Schrift, fals ungueltiger Wert
					this.width.setForeground( Color.RED );
					this.height.setForeground( Color.RED );
				}
			}catch(NumberFormatException x)
			{
				//rote Schrift, falls Buchstaben oder Leerzeichen in der Aufloesung
				this.width.setForeground( Color.RED );
				this.height.setForeground( Color.RED );
			}
		}

		//Wenn vorher angeklickt war -> nicht angeklickt setzen
		if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() &&  this.grafikCacheButtonKlicked  )
		{
			this.grafikcacheButton.setVersion( 0 );
			this.repaint();
			this.grafikCacheButtonKlicked = false;
		}else
		{
			//vorher war nicht angeklickt -> auf angeklickt setzen
			if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() && !( this.grafikCacheButtonKlicked ) ) 	
			{
				this.grafikcacheButton.setVersion( 1 );
				this.repaint();
				this.grafikCacheButtonKlicked = true;
			}
		}
		
		if( (JLabel)e.getSource() == this.hardCoreButton.getImage() &&  this.hardcoreButtonKlicked  )
		{
			this.hardCoreButton.setVersion( 0 );
			this.repaint();
			this.hardcoreButtonKlicked = false;
		}else
		{
			if( (JLabel)e.getSource() == this.hardCoreButton.getImage() && !( this.hardcoreButtonKlicked ) )
			{
				this.hardCoreButton.setVersion( 1 );
				this.repaint();
				this.hardcoreButtonKlicked = true;
			}
		}
		
		if( (JLabel)e.getSource() == this.musicButton.getImage() &&  this.musicButtonKlicked  )
		{
			this.musicButton.setVersion( 0 );
			this.repaint();
			this.musicButtonKlicked = false;
		}else
		{
			if( (JLabel)e.getSource() == this.musicButton.getImage() && !( this.musicButtonKlicked ) )
			{
				this.musicButton.setVersion( 1 );
				this.repaint();
				this.musicButtonKlicked = true;
			}
		}
		
		if( (JLabel)e.getSource() == this.soundButton.getImage() &&  this.soundButtonKlicked  )
		{
			this.soundButton.setVersion( 0 );
			this.repaint();
			this.soundButtonKlicked = false;
		}else
		{
			if( (JLabel)e.getSource() == this.soundButton.getImage() && !( this.soundButtonKlicked ) )
			{
				this.soundButton.setVersion( 1 );
				this.repaint();
				this.soundButtonKlicked = true;
			}
		}
		
		if( (JLabel)e.getSource() == this.fullscreenButton.getImage() &&  this.fullscreenButtonKlicked  )
		{
			this.fullscreenButton.setVersion( 0 );
			this.repaint();
			this.fullscreenButtonKlicked = false;
		}else
		{
			if( (JLabel)e.getSource() == this.fullscreenButton.getImage() && !( this.fullscreenButtonKlicked ) )
			{
				this.fullscreenButton.setVersion( 1 );
				this.repaint();
				this.fullscreenButtonKlicked = true;
			}
		}
	}

	
}


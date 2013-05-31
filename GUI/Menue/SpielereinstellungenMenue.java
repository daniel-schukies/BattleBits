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
import javax.swing.ImageIcon;

import GUI.Game.FileAdmin;
import GUI.Game.Grafikverwaltung.Grafikspeicher;
import GUI.Game.Grafikverwaltung.ImageCreator;

import java.awt.event.*;



@SuppressWarnings("serial")
public class SpielereinstellungenMenue extends JPanel implements MouseListener {
	
	private JTextField sname1;
	private JTextField sname2;
	private JLabel bild;
	private Image image;
	private Grafikspeicher finalstartButton;
	private Grafikspeicher backButton;
	private Grafikspeicher pcButton;
	private boolean pcButtonKlicked , backButtonKlicked , playButtonKlicked;
	private FileAdmin fileadmin;
	
	private Menue menue;

	
	
	public SpielereinstellungenMenue( int xPos,int yPos, Dimension size,Menue menue )
	{
		this.setLayout( null );
		this.setBackground(new Color(20,60,20));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		this.image = new ImageCreator(new Dimension(800,600),1, false).getImage("SpielereinstellungenMenue")[0].getImage();
		
		this.menue = menue;
		this.fileadmin = new FileAdmin();

		this.finalstartButton = new Grafikspeicher(new Dimension(302,101),3, false);
		this.finalstartButton.setImage( "finalstart" );
		
		this.pcButtonKlicked = false;
		this.backButtonKlicked = false;
		this.playButtonKlicked = false;
		
		this.backButton = new Grafikspeicher(new Dimension(133,47),3,false);
		this.backButton.setImage( "back" );
		
		this.pcButton = new Grafikspeicher(new Dimension(133,47),3, false);
		this.pcButton.setImage( "pc" );
		
		this.bild = new JLabel( new ImageIcon(new ImageCreator(new Dimension(800,600),3, false).getImage("SpielereinstellungenMenue")[0].getImage()));
		this.bild.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		
		this.sname1 = new JTextField( this.fileadmin.getPlayer1Name() , 10 );
		this.sname1.setOpaque( false );
		this.sname2 = new JTextField( this.fileadmin.getPlayer2Name() , 10 );
		this.sname2.setOpaque( false );
		
		//Rahmen von textfields durchsichtig machen
		sname1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		sname2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		

		this.sname1.setBackground( new Color(0,0,0,255) );
		this.sname2.setBackground( new Color(0,0,0,255) );
		
		this.sname1.setForeground( new Color(40,40,40) );
		this.sname2.setForeground( new Color(40,40,40) );
		
		this.sname1.setFont( new Font(Font.SANS_SERIF,Font.BOLD,40) );
		this.sname2.setFont( new Font(Font.SANS_SERIF,Font.BOLD,40) );
		
		//hoehe 45 für das Textfield wird benötigt, weil die Buchstaben sonst abgeschnitten werden
		this.sname1.setBounds( 385, 224, 250, 55 );
		this.sname2.setBounds( 385, 282, 250, 55 );
		 
		this.backButton.getImage().setBounds( 5,5,133,47 );
		 
		this.finalstartButton.getImage().setBounds( 245,400,302,101 );
		 
		this.pcButton.getImage().setBounds( 638,286,133,47 );

		this.add( this.sname1 );
		
	    this.add( this.sname2);
	    
	    if( this.fileadmin.getKiZustand() )
	    {
	    	this.pcButton.setVersion( 1 );
	    	this.pcButtonKlicked = true;
	    }
		
		this.setVisible(true);
		

		this.backButton.getImage().addMouseListener( this );
		this.finalstartButton.getImage().addMouseListener( this );
		this.pcButton.getImage().addMouseListener( this );
		
		this.add( this.finalstartButton.getImage() );
		this.add( this.backButton.getImage() );
		this.add( this.pcButton.getImage() );

		
		
	}  
	

	public void paintComponent(Graphics g) {
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
		
		if( (JLabel)e.getSource() == this.finalstartButton.getImage() && !( this.playButtonKlicked ) )
		{
			this.finalstartButton.setVersion( 0 );
		}else
		{
			if( (JLabel)e.getSource() == this.finalstartButton.getImage() && this.playButtonKlicked  )
			{
				this.finalstartButton.setVersion( 1 );
			}
		}
		
		if((JLabel)e.getSource() == this.pcButton.getImage())
		{
			if(this.pcButtonKlicked)
			{
				this.pcButton.setVersion(1);
			}
			else
			{
				this.pcButton.setVersion(0);
			}
		}		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		if( (JLabel)e.getSource() == this.backButton.getImage() )
		{
			this.backButton.setVersion( 2 );
		}
		
		
		if( (JLabel)e.getSource() == this.finalstartButton.getImage() )
		{
			this.finalstartButton.setVersion( 2 );
		}
		
		if ( (JLabel)e.getSource() == this.pcButton.getImage() )
		{
			this.pcButton.setVersion( 1 );
			//this.pcButtonKlicked = false;
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
		if((JLabel)e.getSource() == this.backButton.getImage())
		{
			if( this.fileadmin.setPlayer1Name( this.sname1.getText()) && this.fileadmin.setPlayer2Name(  this.sname2.getText()) )
			{
				this.fileadmin.setKI( this.pcButtonKlicked );
			} else
			{
				this.sname1.setForeground( Color.RED );
				this.sname2.setForeground( Color.RED );
				
			}
			this.menue.changeMenueCardTo(Menue.MAIN_MENUE);
			this.backButton.setVersion(0);
		}
		//backgrafikspeicher und grafikspeicher brauchen kein zuruecksetzen auf Version 0, da beim anklicken in ein anderes
		//Fenster gwechselt wird /// DOCH :D

		
		if( (JLabel)e.getSource() == this.finalstartButton.getImage()  )
		{
			if( this.fileadmin.setPlayer1Name( this.sname1.getText()) && this.fileadmin.setPlayer2Name(  this.sname2.getText()) )
			{
				this.fileadmin.setKI( this.pcButtonKlicked );
			} else
			{
				this.sname1.setForeground( Color.RED );
				this.sname2.setForeground( Color.RED );
				
			}
			if(this.playButtonKlicked )
			{
				this.finalstartButton.setVersion( 0 );
				this.repaint();
				this.playButtonKlicked = false;
			}
			else
			{
				this.finalstartButton.setVersion( 1 );
				this.repaint();
				this.playButtonKlicked = true;
			}
			
			this.menue.startGame(new Dimension(1200,720));
		}
		
		if ( (JLabel)e.getSource() == this.pcButton.getImage() &&  this.pcButtonKlicked  )
		{
			this.pcButton.setVersion( 0 );
			this.repaint();
			this.pcButtonKlicked = false;
			
		}else
		{
		
			if ( (JLabel)e.getSource() == this.pcButton.getImage() && !( this.pcButtonKlicked ) )
			{
				this.pcButton.setVersion( 1 );
				this.repaint();
				this.pcButtonKlicked = true;
			}
		}

	}

}

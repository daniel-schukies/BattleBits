package GUI;

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
import java.awt.event.*;



@SuppressWarnings("serial")
public class SpielereinstellungenMenue extends JPanel implements MouseListener {
	
	private JTextField sname1;
	private JTextField sname2;
	private JLabel bild;
	private Image image;
	private ImageCreator imagecreator;
	private Grafikspeicher grafikspeicher;
	private Grafikspeicher backgrafikspeicher;
	private Grafikspeicher kigrafikspeicher;
	private boolean pcButtonKlicked , backButtonKlicked , playButtonKlicked;

	
	
	public SpielereinstellungenMenue( int xPos,int yPos, Dimension size,Menue menue )
	{
		this.setLayout( null );
		this.setBackground(new Color(20,60,20));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		this.imagecreator = new ImageCreator(new Dimension(600,800),1, false);
		this.image = this.imagecreator.getImage("SpielereinstellungenMenue")[0].getImage();

		this.grafikspeicher = new Grafikspeicher(new Dimension(101,302),3, false);
		this.grafikspeicher.setImage( "finalstart" );
		
		this.pcButtonKlicked = false;
		this.backButtonKlicked = false;
		this.playButtonKlicked = false;
		
		this.backgrafikspeicher = new Grafikspeicher(new Dimension(47,133),3,false);
		this.backgrafikspeicher.setImage( "back" );
		
		this.kigrafikspeicher = new Grafikspeicher(new Dimension(47,133),3, false);
		this.kigrafikspeicher.setImage( "pc" );
		
		this.bild = new JLabel( new ImageIcon(new ImageCreator(new Dimension(600,800),3, false).getImage("SpielereinstellungenMenue")[0].getImage()));
		this.bild.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		
		this.sname1 = new JTextField( "Player1" , 10 );
		this.sname1.setOpaque( false );
		this.sname2 = new JTextField( "Player2" , 10 );
		this.sname2.setOpaque( false );
		
		//Rahmen von textfields durchsichtig machen
		sname1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		sname2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		

		this.sname1.setBackground( new Color(0,0,0,255) );
		this.sname2.setBackground( new Color(0,0,0,255) );
		
		this.sname1.setForeground( new Color(0,0,255) );
		this.sname2.setForeground( new Color(0,0,255) );
		

		
		//this.lname1 = new JLabel( "Spieler 1:" );
		//this.lname2= new JLabel( "Spieler 2:" );
		//this.lname1.setFont( new Font(Font.MONOSPACED,Font.PLAIN,16) );
		//this.lname2.setFont( new Font(Font.MONOSPACED,Font.PLAIN,16) );
		
	
		//this.lname1.setForeground( new Color(0,0,255,255) );
		//this.lname2.setForeground( new Color(0,0,255,255) );
		
		//this.lname1.setBounds(225,140, 100, 30);
		//this.lname2.setBounds(225, 220, 100, 30);
		
		this.sname1.setFont( new Font(Font.MONOSPACED,Font.BOLD,30) );
		this.sname2.setFont( new Font(Font.MONOSPACED,Font.BOLD,30) );
		
		//hoehe 45 für das Textfield wird benötigt, weil die Buchstaben sonst abgeschnitten werden
		 this.sname1.setBounds( 385,230, 250, 45 );
		 this.sname2.setBounds( 385	, 288, 250, 45 );
		 
		 this.backgrafikspeicher.getImage().setBounds( 0,0,133,47 );
		 
		 this.grafikspeicher.getImage().setBounds( 280,370,302,101 );
		 
		 this.kigrafikspeicher.getImage().setBounds( 648,286,133,47 );

		//this.add( this.lname1 );
		this.add( this.sname1 );
		
		//this.add( this.lname2 );
	    this.add( this.sname2);
		
		this.setVisible(true);
		

		this.backgrafikspeicher.getImage().addMouseListener( this );
		this.grafikspeicher.getImage().addMouseListener( this );
		this.kigrafikspeicher.getImage().addMouseListener( this );
		
		this.add( this.grafikspeicher.getImage() );
		this.add( this.backgrafikspeicher.getImage() );
		this.add( this.kigrafikspeicher.getImage() );

		
		
	}  
	

	public void paintComponent(Graphics g) {
		g.drawImage(this.image, 0, 0, null);
	}
	    
	@Override
	public void mouseExited(MouseEvent e) 
	{	//wenn vorher nicht angeklickt
		if( (JLabel)e.getSource() == this.backgrafikspeicher.getImage() && !( this.backButtonKlicked ) )
		{
			this.backgrafikspeicher.setVersion( 0 );
			this.repaint();
		}else
		{	//wenn vorher angeklickt
			if( (JLabel)e.getSource() == this.backgrafikspeicher.getImage() && this.backButtonKlicked )
			{
				this.backgrafikspeicher.setVersion( 1 );
				this.repaint();
			}
		}
		
		if( (JLabel)e.getSource() == this.grafikspeicher.getImage() && !( this.playButtonKlicked ) )
		{
			this.grafikspeicher.setVersion( 0 );
			this.repaint();
		}else
		{
			if( (JLabel)e.getSource() == this.grafikspeicher.getImage() && this.playButtonKlicked  )
			{
				this.grafikspeicher.setVersion( 1 );
				this.repaint();
			}
		}
				
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		if( (JLabel)e.getSource() == this.backgrafikspeicher.getImage() )
		{
			this.backgrafikspeicher.setVersion( 2 );
			this.repaint();
		}
		
		
		if( (JLabel)e.getSource() == this.grafikspeicher.getImage() )
		{
			this.grafikspeicher.setVersion( 2 );
			this.repaint();
		}
		
		if ( (JLabel)e.getSource() == this.kigrafikspeicher.getImage() &&  this.pcButtonKlicked  )
		{
			this.kigrafikspeicher.setVersion( 2 );
			this.repaint();
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
		//backgrafikspeicher und grafikspeicher brauchen kein zuruecksetzen auf Version 0, da beim anklicken in ein anderes
		//Fenster gwechselt wird
		if( (JLabel)e.getSource() == this.backgrafikspeicher.getImage() && this.backButtonKlicked)
		{
			this.backgrafikspeicher.setVersion( 0 );
			this.repaint();
			this.backButtonKlicked = false;
		}else
		{
			if( (JLabel)e.getSource() == this.backgrafikspeicher.getImage() && !( this.backButtonKlicked ) )
			{
				this.backgrafikspeicher.setVersion( 1 );
				this.repaint();
				this.backButtonKlicked = true;
			}
		}
		
		if( (JLabel)e.getSource() == this.grafikspeicher.getImage() &&  this.playButtonKlicked  )
		{
			this.grafikspeicher.setVersion( 0 );
			this.repaint();
			this.playButtonKlicked = false;
		}else
		{
			if( (JLabel)e.getSource() == this.grafikspeicher.getImage() && !( this.playButtonKlicked ) ) 	
			{
				this.grafikspeicher.setVersion( 1 );
				this.repaint();
				this.playButtonKlicked = true;
			}
		}
		
		if ( (JLabel)e.getSource() == this.kigrafikspeicher.getImage() &&  this.pcButtonKlicked  )
		{
			this.kigrafikspeicher.setVersion( 0 );
			this.repaint();
			this.pcButtonKlicked = false;
			
		
		}else
		{
		
			if ( (JLabel)e.getSource() == this.kigrafikspeicher.getImage() && !( this.pcButtonKlicked ) )
			{
				this.kigrafikspeicher.setVersion( 1 );
				this.repaint();
				this.pcButtonKlicked = true;
			}
		}

	}

}

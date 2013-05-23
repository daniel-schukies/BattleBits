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

import java.awt.Toolkit;
import java.awt.event.*;


@SuppressWarnings("serial")
public class OptionsMenue extends JPanel implements MouseListener {

	
	private JTextField width;
	private JTextField height;
	
	private Image image;
	private ImageCreator imagecreator;
	private Dimension aufloesung;
	
	//private JCheckBox grafikenLaden;
	//private JButton speichern;
	private Grafikspeicher grafikcacheButton;
	private Grafikspeicher backButton;
	private boolean kiButtonKlicked , backButtonKlicked;
	
	private FileAdmin fileadmin;
	
	private Menue menue;

	
	
	public OptionsMenue( int xPos,int yPos, Dimension size,Menue menue )
	{
		this.setLayout( null );
		this.setBackground(new Color(20,60,20));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		this.imagecreator = new ImageCreator(new Dimension(800,600),1, false);
		this.image = this.imagecreator.getImage("MenuOptionsBack")[0].getImage();
		
		this.menue = menue;
		
		this.fileadmin = new FileAdmin();
		
		//this.grafikenLaden = new JCheckBox( "Grafiken vorladen" , false );
		//this.grafikenLaden.setOpaque( false );
		//this.grafikenLaden.setFont( new Font(Font.MONOSPACED,Font.BOLD,28) );
		//this.grafikenLaden.setForeground( new Color(0,0,255) );
		//this.grafikenLaden.setBackground( new Color(0,0,0,255) );
		//this.grafikenLaden.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		//this.speichern = new JButton( "Speichern" );
		//this.speichern.setBounds( 235, 435, 320, 45 );
		
		//this.bild = new JLabel( new ImageIcon(new ImageCreator(new Dimension(600,800),1, false).getImage("MenuOptionsBack")[0].getImage()));
		//this.bild.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		

		this.kiButtonKlicked = false;
		this.backButtonKlicked = false;
		
		this.aufloesung = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.height = new JTextField( "" + (int)this.aufloesung.getHeight() , 10 );
		this.height.setOpaque( false );
		this.width = new JTextField( "" + (int)this.aufloesung.getWidth() , 10 );
		this.width.setOpaque( false );
		

		this.grafikcacheButton = new Grafikspeicher(new Dimension(133,47),3, false);
		this.grafikcacheButton.setImage( "on" );
		this.backButton = new Grafikspeicher(new Dimension(133,47),3,false);

		this.backButton.setImage( "back" );
		//this.onofButton.setBounds( 0, 0, 133, this.onofButton.getHeight() );
		//System.out.println( this.onofButton.getHeight() );
		
		
		//Rahmen von textfields durchsichtig machen
		width.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		height.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.width.setBackground( new Color(0,0,0,255) );
		this.height.setBackground( new Color(0,0,0,255) );
		
		this.width.setForeground( new Color(40,40,40) );
		this.height.setForeground( new Color(40,40,40) );
		
		//this.laufloesung = new JLabel( "Auflösung:" );
		//this.laufloesung.setFont( new Font(Font.MONOSPACED,Font.BOLD,22) );
		//this.laufloesung.setForeground( new Color(0,0,255,255) );
		//this.laufloesung.setBounds(235, 235, 300, 45);

		
		this.width.setFont( new Font(Font.SANS_SERIF,Font.BOLD,40) );
		this.height.setFont( new Font(Font.SANS_SERIF,Font.BOLD,40) );
		
		//this.add( this.bild );
		
		//hoehe 45 für das Textfield wird benötigt, weil die Buchstaben sonst abgeschnitten werden
		 this.width.setBounds( 425, 362, 100, 55 );
		 this.height.setBounds( 575, 362, 100, 55 );
		 
		 //this.grafikenLaden.setBounds( 235, 335, 410, 45 );
		

		//this.add( this.laufloesung );
		this.add( this.width );
	    this.add( this.height);
	    
	    this.grafikcacheButton.getImage().setBounds( 485,250,133,47 );
	    this.backButton.getImage().setBounds( 0,0,133,47 );
	    

	    this.grafikcacheButton.getImage().addMouseListener( this );
	    this.backButton.getImage().addMouseListener( this );
	    

	    
	    this.add( this.grafikcacheButton.getImage() );
	    this.add( this.backButton.getImage() );
	    
	   // this.add( this.grafikenLaden );
	    
	   // this.add( this.speichern );
		
		this.setVisible(true);

		
		
		
	}
		
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
		
		if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() && !( this.kiButtonKlicked ) )
		{
			this.grafikcacheButton.setVersion( 0 );
			this.repaint();
		}else
		{
			if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() && this.kiButtonKlicked  )
			{
				this.grafikcacheButton.setVersion( 1 );
				this.repaint();
			}
		}
				
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		if( (JLabel)e.getSource() == this.backButton.getImage() )
		{
			this.backButton.setVersion( 1 );
		}
		
		
		if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() )
		{
			this.grafikcacheButton.setVersion( 1 );
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
					this.fileadmin.setCache(kiButtonKlicked);
					this.menue.changeMenueCardTo(Menue.MAIN_MENUE);
					this.backButton.setVersion(0);
				}else
				{
					this.width.setForeground( new Color(255,255,255).RED );
					this.height.setForeground( new Color(255,255,255).RED );
				}
			}catch(NumberFormatException x)
			{
				this.width.setForeground( new Color(255,255,255).RED );
				this.height.setForeground( new Color(255,255,255).RED );
			}

		}

		
		if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() &&  this.kiButtonKlicked  )
		{
			this.grafikcacheButton.setVersion( 0 );
			this.repaint();
			this.kiButtonKlicked = false;
		}else
		{
			if( (JLabel)e.getSource() == this.grafikcacheButton.getImage() && !( this.kiButtonKlicked ) ) 	
			{
				this.grafikcacheButton.setVersion( 1 );
				this.repaint();
				this.kiButtonKlicked = true;
			}
		}
		

	}

	
}


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
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.JButton;


public class OptionsMenue extends JPanel {
	
	private JTextField width;
	private JTextField height;
	private JLabel laufloesung;
	private JLabel bild;
	private Image image;
	private ImageCreator imagecreator;
	private Dimension aufloesung;
	private JCheckBox grafikenLaden;
	private JButton speichern;
	
	
	public OptionsMenue( int xPos,int yPos, Dimension size,Menue menue )
	{
		this.setLayout( null );
		this.setBackground(new Color(20,60,20));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		this.imagecreator = new ImageCreator(new Dimension(600,800),1, false);
		this.image = this.imagecreator.getImage("menue")[0].getImage();
		
		this.grafikenLaden = new JCheckBox( "Grafiken vorladen" , false );
		this.grafikenLaden.setOpaque( false );
		this.grafikenLaden.setFont( new Font(Font.MONOSPACED,Font.BOLD,28) );
		this.grafikenLaden.setForeground( new Color(0,0,255) );
		this.grafikenLaden.setBackground( new Color(0,0,0,255) );
		//this.grafikenLaden.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		this.speichern = new JButton( "Speichern" );
		this.speichern.setBounds( 235, 435, 320, 45 );
		
		this.bild = new JLabel( new ImageIcon(new ImageCreator(new Dimension(600,800),1, false).getImage("menue")[0].getImage()));
		this.bild.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		
		this.aufloesung = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.height = new JTextField( "" + (int)this.aufloesung.getHeight() , 10 );
		this.height.setOpaque( false );
		this.width = new JTextField( "" + (int)this.aufloesung.getWidth() , 10 );
		this.width.setOpaque( false );
		
		//Rahmen von textfields durchsichtig machen
		width.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		height.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.width.setBackground( new Color(0,0,0,255) );
		this.height.setBackground( new Color(0,0,0,255) );
		
		this.width.setForeground( new Color(0,0,255) );
		this.height.setForeground( new Color(0,0,255) );
		
		this.laufloesung = new JLabel( "Auflösung:" );
		this.laufloesung.setFont( new Font(Font.MONOSPACED,Font.BOLD,22) );
		this.laufloesung.setForeground( new Color(0,0,255,255) );
		this.laufloesung.setBounds(235, 235, 300, 45);

		
		this.width.setFont( new Font(Font.MONOSPACED,Font.BOLD,30) );
		this.height.setFont( new Font(Font.MONOSPACED,Font.BOLD,30) );
		
		//this.add( this.bild );
		
		//hoehe 45 für das Textfield wird benötigt, weil die Buchstaben sonst abgeschnitten werden
		 this.width.setBounds( 365, 235, 300, 45 );
		 this.height.setBounds( 465, 235, 300, 45 );
		 
		 this.grafikenLaden.setBounds( 235, 335, 410, 45 );
		

		this.add( this.laufloesung );
		this.add( this.width );
	    this.add( this.height);
	    
	    this.add( this.grafikenLaden );
	    
	    this.add( this.speichern );
		
		this.setVisible(true);

		
		
		
	}
		
		public void paintComponent(Graphics g) {
	        g.drawImage(this.image, 0, 0, null);
	    }
	
}


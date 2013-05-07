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


@SuppressWarnings("serial")
public class SpielereinstellungenMenue extends JPanel {
	
	private JTextField sname1;
	private JTextField sname2;
	private JLabel lname1;
	private JLabel lname2;
	private JLabel bild;
	private Image image;
	private ImageCreator imagecreator;
	
	
	public SpielereinstellungenMenue( int xPos,int yPos, Dimension size,Menue menue )
	{
		this.setLayout( null );
		this.setBackground(new Color(20,60,20));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		this.imagecreator = new ImageCreator(new Dimension(600,800),1, false);
		this.image = this.imagecreator.getImage("menue")[0].getImage();
		
		this.bild = new JLabel( new ImageIcon(new ImageCreator(new Dimension(600,800),1, false).getImage("menue")[0].getImage()));
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
		
		this.lname1 = new JLabel( "Spieler 1:" );
		this.lname2= new JLabel( "Spieler 2:" );
		this.lname1.setFont( new Font(Font.MONOSPACED,Font.PLAIN,16) );
		this.lname2.setFont( new Font(Font.MONOSPACED,Font.PLAIN,16) );
		
	
		this.lname1.setForeground( new Color(0,0,255,255) );
		this.lname2.setForeground( new Color(0,0,255,255) );
		
		this.lname1.setBounds(225,140, 100, 30);
		this.lname2.setBounds(225, 220, 100, 30);
		
		this.sname1.setFont( new Font(Font.MONOSPACED,Font.BOLD,30) );
		this.sname2.setFont( new Font(Font.MONOSPACED,Font.BOLD,30) );
		
		//hoehe 45 für das Textfield wird benötigt, weil die Buchstaben sonst abgeschnitten werden
		 this.sname1.setBounds( 335,135, 300, 45 );
		 this.sname2.setBounds( 335, 215, 300, 45 );
		

		this.add( this.lname1 );
		this.add( this.sname1 );
		
		this.add( this.lname2 );
	    this.add( this.sname2);
		
		this.setVisible(true);


		
		
	}  
	
		public void paintComponent(Graphics g) {
	        g.drawImage(this.image, 0, 0, null);
	    }
}

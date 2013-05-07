package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class SpielereinstellungenMenue extends JPanel {
	
	private JTextArea sname1;
	private JTextArea sname2;
	private JLabel lname1;
	private JLabel lname2;
	private JPanel flowlayoutpanel;
	
	
	public SpielereinstellungenMenue( int xPos,int yPos, Dimension size,Menue menue )
	{
		this.setLayout( null );
		this.setBackground(new Color(0,0,0,255));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		
		this.sname1 = new JTextArea( "Player1"  );
		this.sname2 = new JTextArea( "Player2"  );
		
		this.flowlayoutpanel = new JPanel();
		this.flowlayoutpanel.setLayout( new FlowLayout() );
		this.flowlayoutpanel.add( this.sname1 );
		this.flowlayoutpanel.setPreferredSize( new Dimension( 10, 1 ));
		
		
		
		//sname1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//sname2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.sname1.setBackground( new Color(0,0,0,0) );
		this.sname2.setBackground( new Color(0,0,0,0) );
		
		this.sname1.setForeground( new Color(0,0,255,255) );
		this.sname2.setForeground( new Color(0,0,255,255) );
		
		this.lname1 = new JLabel( "Spieler 1" );
		this.lname2= new JLabel( "Spieler 2" );
	
		this.lname1.setForeground( new Color(0,0,255,255) );
		this.lname2.setForeground( new Color(0,0,255,255) );
		
		this.lname1.setBounds(225,140, 100, 30);
		this.lname2.setBounds(225, 270, 100, 30);
		
		this.sname1.setFont( new Font("SANS_SERIF",3,30) );
		this.sname2.setFont( new Font("SANS_SERIF",3,30) );
		
		//this.sname1.setBounds( 290,140, 300, 30 );
		//this.sname2.setBounds( 225, 270, 220, 120 );
		
		this.add( lname1 );
	//	this.add( sname1 );
		this.flowlayoutpanel.setBounds( 290,140, 300, 30 );
		this.add( this.flowlayoutpanel );
		
		this.add( lname2 );
	 //  this.add( sname2);
		
		this.setVisible(true);
		
		this.repaint();

		
		
	}  
	
		public void paintComponent(Graphics g) {
	        g.drawImage(new ImageCreator(new Dimension(600,800),1, false).getImage("menue")[0].getImage(), 0, 0, null);
	    }
}

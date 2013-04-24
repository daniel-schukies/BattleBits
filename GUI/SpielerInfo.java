package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Logik.Spieler;

@SuppressWarnings("serial")
public class SpielerInfo extends JPanel 
{
	private Spieler spieler;
	private JLabel spielerName;
	
	public SpielerInfo(int xPos, int yPos,Spieler spieler, Dimension size)
	{
		this.spieler = spieler;
		this.spielerName = new JLabel();
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		this.setBackground(new Color(0,0,0,255));
		
		this.spielerName.setBackground(new Color(0,0,0,255));
		this.spielerName.setPreferredSize(size);
		
		this.spielerName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		this.spielerName.setForeground(Color.GREEN);
		this.spielerName.setText(this.spieler.getName());
		/*
		BufferedImage im = (BufferedImage)((ImageIcon)this.spielerName.getIcon()).getImage();
		float[] matrix = new float[400];
		for (int i = 0; i < 400; i++)
			matrix[i] = 1.0f/400.0f;

	    BufferedImageOp op = new ConvolveOp( new Kernel(20, 20, matrix), ConvolveOp.EDGE_NO_OP, null );
		BufferedImage blurredImage = op.filter(im,im);
		
		this.spielerName.setIcon(new ImageIcon(blurredImage));
		*/
		this.add(this.spielerName);
	}
}

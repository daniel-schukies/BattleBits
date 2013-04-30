package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenue extends JPanel implements MouseListener
{
	private JLabel startButton;
	private JLabel optionsButton;
	private JLabel closeButton;
	
	private static final Dimension buttonSize = new Dimension(355,134);
	
	public static final String start = "start";
	public static final String options = "options";
	public static final String close = "close";
	
	ImageCreator imageCreator;
	
	public MainMenue(int xPos,int yPos, Dimension size,Menue menue) 
	{
		this.setLayout(null);
		this.setBackground(new Color(0,0,255));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		
		this.imageCreator = new ImageCreator(new Dimension(134,355), 3, false);
		
		this.startButton = new JLabel(this.imageCreator.getImage("options")[0]);
		this.optionsButton = new JLabel(this.imageCreator.getImage("options")[0]);
		this.closeButton = new JLabel(this.imageCreator.getImage("options")[0]);
		
		
		this.startButton.setBounds(225,140, (int)MainMenue.buttonSize.getWidth(),(int)MainMenue.buttonSize.getHeight());
		this.optionsButton.setBounds(225, 270, (int)MainMenue.buttonSize.getWidth(), (int)MainMenue.buttonSize.getHeight());
		this.closeButton.setBounds(225, 400, (int)MainMenue.buttonSize.getWidth(), (int)MainMenue.buttonSize.getHeight());
		
		this.add(this.startButton);
		this.add(this.optionsButton);
		this.add(this.closeButton);
		
		this.repaint();
		
		this.setVisible(true);
		
		
	}
	
    public void paintComponent(Graphics g) {
        g.drawImage(new ImageCreator(new Dimension(600,800),1, false).getImage("menue")[0].getImage(), 0, 0, null);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

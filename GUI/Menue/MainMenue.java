package GUI.Menue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Game.Grafikverwaltung.Grafikspeicher;
import GUI.Game.Grafikverwaltung.ImageCreator;

@SuppressWarnings("serial")
public class MainMenue extends JPanel implements MouseListener
{
	private Grafikspeicher startButton;
	private Grafikspeicher optionsButton;
	private Grafikspeicher closeButton;
	
	private static final Dimension buttonSize = new Dimension(355,134);
	
	private Menue menue;

	ImageCreator grafik;
	
	public MainMenue(int xPos,int yPos, Dimension size, Menue menue) 
	{
		this.setLayout(null);
		this.setBackground(new Color(0,0,255));
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		
		this.menue = menue;
		
		//this.grafik = new ImageCreator(new Dimension(134,355), 3, false);
		
		this.startButton = new Grafikspeicher(new Dimension(134,355), 1, false);
		this.optionsButton = new Grafikspeicher(new Dimension(134,355), 1, false);
		this.closeButton = new Grafikspeicher(new Dimension(134,355), 1, false);
		
		this.startButton.setImage("start");
		this.optionsButton.setImage("options");
		this.closeButton.setImage("close");
		
		/*this.startButton = new JLabel(this.grafik.getImage("options")[0]);
		this.optionsButton = new JLabel(this.grafik.getImage("options")[0]);
		this.closeButton = new JLabel(this.grafik.getImage("options")[0]);*/
		
		
		this.startButton.getImage().setBounds(225,140, (int)MainMenue.buttonSize.getWidth(),(int)MainMenue.buttonSize.getHeight());
		this.optionsButton.getImage().setBounds(225, 270, (int)MainMenue.buttonSize.getWidth(), (int)MainMenue.buttonSize.getHeight());
		this.closeButton.getImage().setBounds(225, 400, (int)MainMenue.buttonSize.getWidth(), (int)MainMenue.buttonSize.getHeight());
		
		this.startButton.getImage().addMouseListener(this);
		this.optionsButton.getImage().addMouseListener(this);
		this.closeButton.getImage().addMouseListener(this);
		
		this.add(this.startButton.getImage());
		this.add(this.optionsButton.getImage());
		this.add(this.closeButton.getImage());
		
		this.repaint();
		
		this.setVisible(true);
		
		
	}
	
    public void paintComponent(Graphics g) {
        g.drawImage(new ImageCreator(new Dimension(600,800),1, false).getImage("menue")[0].getImage(), 0, 0, null);
    }

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		JLabel button = (JLabel)e.getSource();
		if(button == this.startButton.getImage())
		{
			
			this.menue.changeMenueCardTo(Menue.START);
		}
		else if(button == this.optionsButton.getImage())
		{
			this.menue.changeMenueCardTo(Menue.OPTIONS);
		}
		else if(button == this.closeButton.getImage())
		{
			this.menue.changeMenueCardTo(Menue.CLOSE);
		}
		
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

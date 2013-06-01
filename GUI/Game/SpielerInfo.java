package GUI.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import Logik.Spieler;

@SuppressWarnings("serial")
public class SpielerInfo extends JPanel implements Refreshable
{
	private Spieler spieler;
	private JLabel spielerName;
	
	public SpielerInfo(int xPos, int yPos,Spieler spieler, Dimension size)
	{
		this.spieler = spieler;
		this.spielerName = new JLabel();
		this.setBounds(xPos, yPos, (int)size.getWidth(), (int)size.getHeight());
		this.setPreferredSize(size);
		this.setBackground(new Color(0,0,0,0));
		this.setOpaque(false);
		
		this.spielerName.setBackground(new Color(0,0,0,255));
		this.spielerName.setPreferredSize(size);
		
		this.spielerName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		this.spielerName.setForeground(new Color(105,210,231,100));
		this.spielerName.setText(this.spieler.getName());
		
		this.add(this.spielerName);
		
		this.refresh();
	}

	@Override
	public void refresh() 
	{
		if(this.spieler.getIsDran())
		{
			this.spielerName.setForeground(new Color(105,210,231,180));
		}
		else
		{
			this.spielerName.setForeground(new Color(105,210,231,80));
		}
		
		this.repaint();
		
	}
}

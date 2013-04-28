package GUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menue extends JPanel implements ActionListener
{
	JPanel p1;
	JPanel p2;
	JPanel p3;

	
	public Menue()
	{
		this.setLayout(new CardLayout());
		
		this.p1 = new JPanel();
		this.p2 = new JPanel();
		this.p3 = new JPanel();
		
		JButton b1 = new 
		
		
		this.p1.add(new JButton("P11111111"));
		this.p2.add(new JButton("P22222"));
		this.p3.add(new JButton("P3333333"));
		
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

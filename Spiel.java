import java.util.Scanner;

public class Spiel 
{
	private int rundenzahl;
	private Spieler spieler1;
	private Spieler spieler2;
	private Bitgenerator bitfolge;
	private Spielfeld spielfeld;
	
	public Spiel()
	{
		this.rundenzahl = 0;
	}
	
	public void starten()
	{
		/** Eingabe der Benutzerdaten */
		
		Scanner s = new Scanner(System.in);
		boolean isEingabeOK = false;
		
		String name1 = "";
		String name2 = "";
		
		String eingabe = "";
		

		System.out.print("Name Spieler1: ");
		name1 = s.next();
			
		System.out.print("Name Spieler2: ");
		name2 = s.next();
		
		
		
		do
		{
			System.out.println("Wollen Sie gegen den PC oder gegen eine Person spielen?");
			System.out.println("Geben Sie \"PC\" oder \"PR\" ein.");
			
			eingabe = s.next();
			
			if(eingabe.equals("PC"))
			{
				this.spieler1 = new Spieler(name1, false);
				this.spieler2 = new Spieler(name2, true);
				
				isEingabeOK = true;
			}
			else if( (eingabe.equals("PR")) )
			{
				this.spieler1 = new Spieler(name1, false);
				this.spieler2 = new Spieler(name2, false);
				
				isEingabeOK = true;
			}
			else
			{
				isEingabeOK = false;
			}
			
			
			
		}while( !(isEingabeOK) );
		
		s.close();
		
		/**-------------------------------------------------------------*/
		
		/** Starten der Spielschleife nur mit P vs. P */
		
		this.spieler1.setIsDran(true);
		
		boolean isSpielende = false;
		
		
		while( !(isSpielende) )
		{
			this.rundenzahl++;
			
			if( this.spieler1.getIsDran() )
			{
				this.spieler2.setIsDran(true);
				this.spieler1.setIsDran(false);
				
				this.spielzug(spieler2);
			}
			else
			{
				this.spieler2.setIsDran(false);
				this.spieler1.setIsDran(true);
				
				this.spielzug(spieler1);
			}
			
			
			
		}
	}
	
	
	public void spielzug(Spieler s)
	{
		
		/** Gebe Spieler die Logikgatter */
		
		Logikgattergenerator lg = new Logikgattergenerator();
		

		while(s.getLogikgatter(3) == null)
		{
			lg.generate();
			s.gebeLogikgatter(lg.getLogikgatter());
		}
		
		
		/** Konsolenausgabe der Logikgatter */
		
		for(int i = 0; i < 4; i++)
		{
			System.out.println(s.getLogikgatter(i));
		}
		
		System.out.println("---------------------");
		
	}
	
	
	public void neustarten()
	{
		
	}
	
	public void ZugBeenden()
	{
		
	}
	


		


		
}

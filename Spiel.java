import java.util.Scanner;

public class Spiel 
{
	private int rundenzahl;
	private Spieler spieler1;
	private Spieler spieler2;
	//private Bitgenerator bitfolge;
	private Spielfeld spielfeld;

	private Scanner sc = new Scanner(System.in);

	
	public Spiel()
	{
		this.rundenzahl = 0;
		this.spielfeld = new Spielfeld();
	}
	
	public void starten()
	{	
		this.eingabeBenutzerdaten();
		this.starteSpielschleife();
	}
	
	
	public void spielzug(Spieler s)
	{
		
		this.gebeSpielerGatter(s);
		
		this.ausgabeSpielerlogikgatter(s);

		/** Frage Logikgatter ab */

		int[] logikgatterVerwendung = this.frageLogikgatterAb(); // Options Abfrage
		int[] ablegeplatz;
		
		if(logikgatterVerwendung[1] == 1) // 1 == Option: Neues Logikgatter ziehen.
		{
			this.zieheNeuesLogikgatter(s, logikgatterVerwendung[0] );
			this.ausgabeSpielerlogikgatter(s);
		}
		else // Option: Logikgatter auf Spielfeld legen.
		{
			do
			{
			
				do{
					ablegeplatz = this.ablegeplatzAbfrage(); // Ablegeplatz abfrage
				}while(!(this.spielfeld.setLogikgatter(ablegeplatz[0], ablegeplatz[1], s.getLogikgatter(ablegeplatz[1]))));// Pruefe und setze Logikgatter auf Spielfeld
				
			} while( s.getLogikgatter(ablegeplatz[1]).equals(null) ); //index und reihe auf gueltigkeit pruefen
				
		}
		
		System.out.println("----------------Spielende!-----------------");
		
	}
	
	private void eingabeBenutzerdaten()
	{
		/** Eingabe der Benutzerdaten */
		
		boolean isEingabeOK = false;
		
		String name1 = "";
		String name2 = "";
		
		String eingabe = "";
		

		System.out.print("Name Spieler1: ");
		name1 = sc.next();
			
		System.out.print("Name Spieler2: ");
		name2 = sc.next();
		
		
		
		do
		{
			System.out.println("Wollen Sie gegen den PC oder gegen eine Person spielen?");
			System.out.println("Geben Sie \"PC\" oder \"PR\" ein.");
			
			eingabe = sc.next();
			
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
	}
	
	
	
	private void starteSpielschleife()
	{
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
	
	private void gebeSpielerGatter(Spieler s)
	{
		/** Gebe Spieler die Logikgatter */
		
		Logikgattergenerator lg = new Logikgattergenerator();
		

		while(s.getLogikgatter(3) == null)
		{
			lg.generate();
			s.gebeLogikgatter(lg.getLogikgatter());
		}
	}
	
	private void ausgabeSpielerlogikgatter(Spieler s)
	{
		/** Konsolenausgabe der Logikgatter */
		
		for(int i = 0; i < 4; i++)
		{
			System.out.println(s.getLogikgatter(i));
		}
		
		System.out.println("---------------------");
	}
	
	private int[] frageLogikgatterAb()
	{
		/** Frage Logikgatter ab */
		int[] logikgatterVerwendung = new int[2];
		
		System.out.print("Welches Logikgatter wollen Sie benutzen (Index angeben): ");
		logikgatterVerwendung[0] = sc.nextInt();

		System.out.println("Geben Sie 1 für neues Gatter ziehen oder 2 für Gatter legen: ");
		logikgatterVerwendung[1] = sc.nextInt();
		
		return logikgatterVerwendung;
		
		
		
	}
	
	
	private void zieheNeuesLogikgatter(Spieler s, int gatterIndex)
	{
		Logikgattergenerator lg = new Logikgattergenerator();
		
		s.loescheLogikgatter(gatterIndex);
		lg.generate();
		s.gebeLogikgatter(lg.getLogikgatter());
	}
	
	private int[] ablegeplatzAbfrage()
	{
		int[] ablegeplatz = new int[2]; // Index 0 == Reihe | Index 1 == Index
		
		System.out.println("Wo wollen Sie das Gatter ablegen?");
		
		System.out.print("Reihe: ");
		ablegeplatz[0] = sc.nextInt();
		
		System.out.print("Index: ");
		ablegeplatz[1] = sc.nextInt();
		
		return ablegeplatz;
	}
	
	
	public void neustarten()
	{
		
	}
	
	public void ZugBeenden()
	{
		
	}
	


		


		
}

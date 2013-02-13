import java.util.Scanner;

public class Spiel 
{
	private int rundenzahl;
	private Spieler spieler1;
	private Spieler spieler2;
	private Spielfeld spielfeld1;
	private Spielfeld spielfeld2;
	private Bitgenerator bitfolge; // Speicher der Bitfolge


	private Scanner sc = new Scanner(System.in);

	
	public Spiel()
	{
		this.rundenzahl = 0;
		this.spielfeld1 = new Spielfeld();
		this.spielfeld2 = new Spielfeld();
		
		this.bitfolge = new Bitgenerator(5);
		this.bitfolge.generate();
	}
	
	/** Startet das Spiel */
	public void starten()
	{	
		this.conEingabeBenutzerdaten();
		this.starteSpielschleife();
	}
	
	/** Startet und steuert die Spielschleife */
	private void starteSpielschleife()
	{
		/** Starten der Spielschleife nur mit P vs. P */
		
		this.spieler1.setIsDran(true);
		
		boolean isSpielende = false;
		
		/** Spielschleife */
		while( !(isSpielende) )
		{
			this.rundenzahl++; // Zaehlt die Runden
			
			/**
			 * Steuert, welcher Spieler am Zug ist und startet seinen Spielzug 
			 */
			if( this.spieler1.getIsDran() )
			{
				this.spieler2.setIsDran(true);
				this.spieler1.setIsDran(false);
				
				this.nextSpielzug(spieler2, this.spielfeld2);
			}
			else
			{
				this.spieler2.setIsDran(false);
				this.spieler1.setIsDran(true);
				
				this.nextSpielzug(spieler1, this.spielfeld1);
			}
		}
	}
	
	
	/**
	 * fuehrt einen Spielzug des uebergebenen Spielers durch.
	 * @param spieler ist der Spieler, welcher gerade am Zug ist.
	 * @param spielfeld des uebergebenen Spielers
	 */
	private void nextSpielzug(Spieler spieler, Spielfeld spielfeld)
	{
		spieler.generiereLogikgatter();
		
		if(spieler.getIsKI())
		{
			this.conAusgabeSpielerlogikgatter(spieler);
			
			spieler.spieleAlsKI(spielfeld2, spielfeld1, bitfolge);
			this.conAusgabeRundenzahl();
			
			this.conAusgabeSpielerInfo(spieler);
			
			this.conAusgabeSpielerlogikgatter(spieler);
			
			this.conSpielfeldAusgabe(spielfeld);
		}
		else
		{
			this.conAusgabeRundenzahl();
			
			this.conAusgabeSpielerInfo(spieler);
			
			this.conAusgabeSpielerlogikgatter(spieler);
			
			this.conSpielfeldAusgabe(spielfeld);
	
			/** Frage Logikgatter ab */
			int[] logikgatterVerwendung = this.conFrageLogikgatterAb(); // Options Abfrage
			
			if(logikgatterVerwendung[1] == 1) // 1 == Option: Neues Logikgatter ziehen.
			{
				spieler.zieheNeuesLogikgatter(logikgatterVerwendung[0] );
				this.conAusgabeSpielerlogikgatter(spieler); // Gebe Logikgatter des Spielers aus
			}
			else // Option: Logikgatter auf Spielfeld legen.
			{
				boolean isGatterSet;
				
				do
				{
					if( this.conLegeLogikgatterAufSpielfeld(spielfeld, spieler, logikgatterVerwendung[0]) )
					{
						isGatterSet  = true;
					}
					else
					{
						isGatterSet = false;
						
						logikgatterVerwendung = this.conFrageLogikgatterAb();  // Options Abfrage
						
						if(logikgatterVerwendung[1] == 1) // 1 == Option: Neues Logikgatter ziehen.
						{
							spieler.zieheNeuesLogikgatter(logikgatterVerwendung[0] );
							this.conAusgabeSpielerlogikgatter(spieler); // Gebe Logikgatter des Spielers aus
						}
					}
				}while( !(isGatterSet) );
			}
			
			this.conSpielfeldAusgabe(spielfeld);
			
			
		
		}
		System.out.println("----------------Ende des Spielzugs!-----------------");
	}
		

	
	
	/**
	 * Legt ein Logikgatter eines Spielers auf seinen Teil des Spielfelds
	 * @param spielfeld (des Spielers, der am Zug ist)
	 * @param s	Spieler der am Zug ist
	 * @param ablegeplatz (des Logikgatters auf dem Spielfeld)
	 * @param logikgatterIndex ist der Index des Logikgatters des Spielers
	 * @return boolean (Ob Legen des Logikgatters erfolgreich war)
	 */
	private boolean conLegeLogikgatterAufSpielfeld(Spielfeld spielfeld, Spieler s, int logikgatterIndex)
	{
		if(s.getLogikgatter(logikgatterIndex) instanceof Not) // pruefe auf NOT Gatter
		{
			int zuInvertierendesBit;

			do
			{
				zuInvertierendesBit = conAblegeplatzAbfrageNOT();// Abfrage des zu invertierenden Bits	
			}while(zuInvertierendesBit < 0 && zuInvertierendesBit > 5 ); // Wiederhole bei falschem Index
			
			this.bitfolge.invertBit(zuInvertierendesBit); // invertiere das Bit	
		}
		else // Wenn kein NOT vorhanden.
		{
					int[] ablegeplatz = this.conAblegeplatzAbfrage(); // Ablegeplatz abfrage
					
					/** Pruefe und setze Logikgatter auf Spielfeld  */
					if((spielfeld.setLogikgatter(ablegeplatz[0], ablegeplatz[1], s.getLogikgatter(logikgatterIndex), this.bitfolge)))
					{
						s.zieheNeuesLogikgatter(logikgatterIndex); // Loesche und ziehe neues Logikgatter des Spielers
						return true; // Logikgatter wurde auf Spielfeld gelegt
					}
		}	
		
		return false; // Logikgatter konnte nicht platziert werden!
	}
	
	
	
	/**
	 * Fragt die Ablegeposition des Logikgatters auf dem Spielfeld in der Konsole ab.
	 * @return Int Array (Index 0 = Reihe, Index 1 = Index) --> Logikgatterposition
	 */
	private int[] conAblegeplatzAbfrage()
	{
		int[] ablegeplatz = new int[2]; // Index 0 == Reihe | Index 1 == Index
		
		System.out.println("Wo wollen Sie das Gatter ablegen?");
		
		System.out.print("Reihe: ");
		ablegeplatz[0] = sc.nextInt();
		
		System.out.print("Index: ");
		ablegeplatz[1] = sc.nextInt();
		
		return ablegeplatz;
	}
	
	
	
	/** NOT or not NOT - Das ist hier die Frage.
	 * 
	 * @param s String mit "NOT" für ein NOT
	 * @return ablegeplatz auf Bitfolge
	 */
	private int conAblegeplatzAbfrageNOT()
	{
		int ablegeplatz; // 0-5 Ablegeplatz des NOT auf Bit
		
		System.out.println("Welches Bit wollen Sie invertieren?");
		
		System.out.print("Index: ");
		ablegeplatz = sc.nextInt();
		
		return ablegeplatz;
	}
	
	
	
	/**
	 * Fragt in der Konsole das Logikgatter ab und //nicht fertig kommentiert
	 * @return Array (Index 0 = Logikgatter, Index 1 = Option) 
	 */
	private int[] conFrageLogikgatterAb()
	{
		/** Frage Logikgatter ab */
		int[] logikgatterVerwendung = new int[2];
		
		System.out.print("Welches Logikgatter wollen Sie benutzen (Index angeben): ");
		logikgatterVerwendung[0] = sc.nextInt();

		System.out.println("Geben Sie 1 für neues Gatter ziehen oder 2 für Gatter legen: ");
		logikgatterVerwendung[1] = sc.nextInt();
		
		return logikgatterVerwendung;
	}
	
	
	
	/**
	 * Fragt die Benutzerdaten in der Konsole ab und traegt sie in das Spieler-Objekt ein
	 */
	private void conEingabeBenutzerdaten()
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
	
	
	
	/**
	 * Stellt ein Spielfeld in den Konsole dar.
	 * @param s Spielfeld, welches dargestellt werden soll
	 */
	private void conSpielfeldAusgabe(Spielfeld s)
	{
		int i;
		
		System.out.println("---------- Spielfeld ----------");
		
		for(i = 0; i < 5;i++ )
		{
			System.out.print(" | " + this.bitfolge.getBit(i)); // Ausgabe der Bitfolge
		}
		
		System.out.println(""); // Abstand ...
		
		int y = 0; // Anzahl der Spielfeldreihen
		
		for(int x = 4; x >= 0; x--)
		{
			
			for(int z = y; z >= 0; z--)
			{
				System.out.print("    ");
			}
			
			for(i = 0; i < x; i++)
			{
				System.out.print("  " + s.getLogikgatter(y, i) + "  ");
			}
			
			System.out.println("");
			

			y++;
		}			
	}
	
	

	/**
	 * Stellt die Logikgatter eines Spielers in den Konsole dar.
	 * @param s Spieler der gerade am Zug ist
	 */
	private void conAusgabeSpielerlogikgatter(Spieler s)
	{
		/** Konsolenausgabe der Logikgatter */
		
		for(int i = 0; i < 4; i++)
		{
			System.out.println("" + s.getLogikgatter(i) + " Ausgang: " + s.getLogikgatter(i).getAusgang());
		}
		
		System.out.println("-------------------------------");
	}
	
	
	/** Gibt die Rundenzahl in der Konsole aus */
	private void conAusgabeRundenzahl()
	{
		System.out.println("Runde:" + this.rundenzahl);
	}
	
	
	/**
	 * Gibt Informationen ueber einen Spieler in der Konsole aus
	 * @param s Spieler, dessen Infos ausgegeben werden sollen.
	 */
	private void conAusgabeSpielerInfo(Spieler s)
	{
		System.out.print("Spieler " + s.getName() + " ist am Zug");
		
		if(!(s.getIsDran()))
		{
			System.out.println("nich ;)");
		}
		else
		{
			System.out.println("");
		}
	}
	
	
	
	public void neustarten()
	{
		
	}
	
	public void ZugBeenden()
	{
		
	}

}

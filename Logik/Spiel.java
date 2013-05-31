package Logik;

import java.util.Scanner;

import Logik.Bitgenerator;
import Logik.Konsole;
import Logik.Not;
import Logik.Spieler;


public class Spiel 
{
	private boolean gui;
	private boolean isSpielende;
	private int rundenzahl;
	private Spieler spieler1;
	private Spieler spieler2;
	private Bitgenerator bitfolge; // Speicher der Bitfolge

	
	public Spiel(boolean gui)
	{
		this.rundenzahl = 0;
		
		this.isSpielende = false;
		
		this.bitfolge = new Bitgenerator(5);
		this.bitfolge.generate();
		
		this.gui = gui;
		
		if(this.gui)
		{
			this.starteGUIModus();
		}
		else
		{
			this.starteKonsolenModus();
		}
	}
	
	public void nextSpielzug()
	{
		/**
		* Steuert, welcher Spieler am Zug ist und startet seinen Spielzug 
		*/
		if( this.spieler1.getIsDran() )
		{
			this.spieler2.setIsDran(true);
			this.spieler1.setIsDran(false);
			this.spielen(this.spieler2, this.spieler1);
			System.out.println("Spieler1 is dran!");
		}
		else
		{
			this.spieler2.setIsDran(false);
			this.spieler1.setIsDran(true);
			this.spielen(this.spieler1, this.spieler2);
			System.out.println("Spieler0 is dran!");
		}
		
		Konsole con = new Konsole(this.getBitfolge());
		con.conAusgabeSpielerlogikgatter(this.getAktuellerSpieler());
		con.conSpielfeldAusgabe(this.getAktuellerSpieler().getSpielfeld());
	}

	public boolean legeLogikgatter(Spieler spieler, int logikgatterIndex, int ablegeReihe, int ablegeIndex)
	{
		if(!(spieler.getLogikgatter(logikgatterIndex) instanceof Not)) // Wenn kein NOT vorhanden.
		{		
			/** Pruefe und setze Logikgatter auf Spielfeld  */
			if((spieler.getSpielfeld().setLogikgatter(ablegeReihe, ablegeIndex, spieler.getLogikgatter(logikgatterIndex), this.bitfolge)))
			{
				spieler.zieheNeuesLogikgatter(logikgatterIndex); // Loesche und ziehe neues Logikgatter des Spielers
				return true; // Logikgatter wurde auf Spielfeld gelegt
			}
		}	
		
		return false; // Logikgatter konnte nicht platziert werden!
	}
	
	
	public boolean legeLogikgatter(Spieler spieler, int logikgatterIndex, int zuInvertierendesBit)
	{
		if(spieler.getLogikgatter(logikgatterIndex) instanceof Not) // pruefe auf NOT Gatter
		{
			if(!(zuInvertierendesBit < 0 && zuInvertierendesBit > 5) )// Wiederhole bei falschem Index
			{
				this.bitfolge.invertBit(zuInvertierendesBit); // invertiere das Bit	
				spieler.zieheNeuesLogikgatter(logikgatterIndex); // Loesche und ziehe neues Logikgatter des Spielers
				System.out.println("invertiert");
				return true; // Bit wurde invertiert (Not gelegt)
			}
			else
			{
				System.out.println("Falsche Zahl");
			}
		}
		else
		{
			System.out.println("Kein Not");
		}
		
		return false; // Logikgatter konnte nicht platziert werden!
	}
	
	public int getRundenzahl()
	{
		return this.rundenzahl;
	}
	
	public Spieler getAktuellerSpieler()
	{
		if( this.spieler1.getIsDran() )
		{
			return this.spieler1;
		}
		else
		{
			return this.spieler2;
		}
	}
	
	
	public Spieler[] getSpieler()
	{
		Spieler[] spieler = new Spieler[2];
		
		spieler[0] = this.spieler1;
		spieler[1] = this.spieler2;
		
		
		return spieler;
	}
	
	
	public Bitgenerator getBitfolge()
	{
		return this.bitfolge;
	}
	
	public boolean getSpielende()
	{
		return this.isSpielende;
	}
	
	public void setSpielende(boolean isSpielende)
	{
		this.isSpielende = isSpielende;
	}
	
	
	
	private void spielen(Spieler spieler, Spieler gegner)
	{
		this.rundenzahl++; // Zaehlt die Runden
			
		spieler.getSpielfeld().entferneUngueltigeLogikgatter(this.bitfolge);
		
		spieler.generiereLogikgatter();
		
		if(spieler.getIsKI())
		{			
			spieler.spieleAlsKI(gegner.getSpielfeld(), this.bitfolge);
		}

		
		if(spieler.getSpielfeld().getLogikgatter(3, 0) != null)
		{
			this.isSpielende = true; //Spiel ist zu Ende.
			spieler.setIsWinner();
		}
	}
	
	
	private void starteGUIModus()
	{
		this.spieler1 = new Spieler();
		this.spieler2 = new Spieler();
		this.spieler1.setIsKI(true);
		this.spieler2.setIsKI(true);
		this.spieler1.setIsDran(false);
		this.spieler2.setIsDran(true);
		
		this.spieler1.generiereLogikgatter();
		this.spieler2.generiereLogikgatter();
	}
	
	
	/** 
	 *  Startet das Spiel 
	 *  Startet und steuert die Spielschleife 
	 * */
	private void starteKonsolenModus()
	{
		/**
		 * Fraegt die Benutzerdaten in der Konsole ab und traegt sie in das Spieler-Objekt ein
		 */
		/** Eingabe der Benutzerdaten */
		
		Scanner sc = new Scanner(System.in);
		
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
		
		Konsole con = new Konsole(this.bitfolge);

		/** Starten der Spielschleife nur mit P vs. P */
		
		this.spieler1.setIsDran(true);
		
		this.isSpielende = false;
		
		/** Spielschleife */
		while( !(this.isSpielende) )
		{
			this.rundenzahl++; // Zaehlt die Runden
			
			/**
			 * Steuert, welcher Spieler am Zug ist und startet seinen Spielzug 
			 */
			if( this.spieler1.getIsDran() )
			{
				this.spieler2.setIsDran(true);
				this.spieler1.setIsDran(false);
				
				this.isSpielende = con.conNextSpielzug(spieler2, this.spieler2.getSpielfeld(), this.spieler1.getSpielfeld(), this.rundenzahl);
			}
			else
			{
				this.spieler2.setIsDran(false);
				this.spieler1.setIsDran(true);
				
				this.isSpielende = con.conNextSpielzug(spieler1, this.spieler1.getSpielfeld(), this.spieler2.getSpielfeld(), this.rundenzahl);
			}
		}
		
		sc.close();
	}
}

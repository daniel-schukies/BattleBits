package Logik;

import java.util.Random;
import javax.swing.SwingUtilities;
import GUI.Game.SoundAusgabe;


/**
 * Representiert einen Spieler und seine Funktionen
 * @author Daniel Schukies, Sebastian Junger
 *
 */
public class Spieler 
{
	private String name;
	private boolean isDran;
	private boolean isKI;
	private boolean isWinner;
	private Logikgatter[] logikgatter;
	private Spielfeld spielfeld;
	private SoundAusgabe sa;

	/**
	 * Erstellt einen Spieler und definiert, seinen Namen und Spielmodus
	 * @param name Name des Spielers
	 * @param isKI Art des Spielers
	 */
	public Spieler(String name, boolean isKI)
	{
		this.spielfeld = new Spielfeld();
		this.setName(name);
		this.setIsKI(isKI);
		logikgatter = new Logikgatter[4];
	}
	
	/**
	 * Erstellt einen Spieler mit leerem Namen, der nicht als KI spielt
	 */
	public Spieler()
	{
		this.spielfeld = new Spielfeld();
		this.setName("");
		this.setIsKI(false);
		logikgatter = new Logikgatter[4];
		this.sa = new SoundAusgabe();
	}
	
	/**
	 * Setzt den Namen des Spielers
	 * @param name Name d. Spielers
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Setzt die variable isDran auf Wert
	 * @param isDran Wert der Variable
	 */
	public void setIsDran(boolean isDran)
	{
		this.isDran = isDran;
	}
	
	/**
	 * Setzt Wert der Variable isKI
	 * @param ki wert der Variable
	 */
	public void setIsKI(boolean ki)
	{
		this.isKI = ki;
	}
	
	/**
	 * Setzt Spieler als Gewinner
	 */
	public void setIsWinner()
	{
		this.isWinner = true;
	}
	
	/**
	 * Gibt den Namen des Spielers zurueck
	 * @return Name des Spielers
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * gibt Spielfeld zurueck
	 * @return Spielfeld d. Spielers
	 */
	public Spielfeld getSpielfeld()
	{
		return this.spielfeld;
	}
	
	/**
	 * Gibt bestimmtes Logikgatter zurueck
	 * @param index Index des Logikgatters
	 * @return Logikgatter an index
	 */
	public Logikgatter getLogikgatter(int index)
	{
		if(index >= 0 && index < this.logikgatter.length)
		{
			return this.logikgatter[index]; // ACHTUNG NULLPOINTER ;)
		}
		
		return null; // Existiert nicht
	}
	
	/**
	 * Gibt die Logikgatter des Spielers zurueck
	 * @return Logikgatter des Spielers
	 */
	public Logikgatter[] getLogikgatter()
	{
		return this.logikgatter;
	}
	
	/**
	 * Inhalt der Variable isKI abfragen
	 * @return Inhalt der Variable isKI
	 */
	public boolean getIsKI()
	{
		return this.isKI;
	}
	
	/**
	 * Gibt den inhalt der Variable isDran zurueck
	 * @return Inhalt der Variable isDran
	 */
	public boolean getIsDran()
	{
		return this.isDran;
	}
	
	/**
	 * Zeigt, ob Spieler gewonnen hat
	 * @return Inhalt von isWinner
	 */
	public boolean getIsWinner()
	{
		return this.isWinner;
	}
	

	/**
	 * loescht ein bestimmtes Logikgatter
	 * @param index Index des zu loeschenden Logikgatters
	 * @return Loeschen erfolgreich
	 */
	public boolean loescheLogikgatter(int index)
	{
		if(this.logikgatter[index] == null)
		{
			return false;
		}
		else
		{
			this.logikgatter[index] = null;
			return true;
		}
	}
	
	/**
	 * Gibt dem Spieler ein Logikgatter
	 * @param logikgatter auszuteilendes Logikgatter
	 * @return Vergabe erfolgreich
	 */
	public boolean gebeLogikgatter(Logikgatter logikgatter)
	{
		
		for(int i = 0; i < this.logikgatter.length; i++)
		{
			if(this.logikgatter[i] == null)
			{
				this.logikgatter[i] = logikgatter;
				return true;
			}
		}
		return false;
	}
	

	
	/**
	 * Teilt an den Spieler seine ersten Gatter aus.
	 * @param spieler
	 */
	public void generiereLogikgatter()
	{
		/** Gebe Spieler die Logikgatter */
		Logikgattergenerator lg = new Logikgattergenerator();
		
		while(this.getLogikgatter(3) == null)
		{
			lg.generate();
			this.gebeLogikgatter(lg.getLogikgatter());
		}
	}
	
	/**
	 * Dem Spieler wird ein altes Logikgatter gegen ein Neues getauscht.
	 * @param gatterIndex Welches Logikgatter getauscht werden soll.
	 */
	public void zieheNeuesLogikgatter(int gatterIndex)
	{
		Logikgattergenerator lg = new Logikgattergenerator();
		
		this.loescheLogikgatter(gatterIndex);
		lg.generate();
		this.gebeLogikgatter(lg.getLogikgatter());
	}
	
	/**
	 * Spieler spielt als KI
	 * @param eigenesSpielfeld
	 * @param gegnerSpielfeld
	 * @param bitfolge
	 */
	public void spieleAlsKI(Spielfeld gegnerSpielfeld, Bitgenerator bitfolge)
	{
		System.out.println("KI Hat gespielt");
		
		int indexVerwendetesGatter = 404; // Error: 404 Gatter not found ;)
		
		int notSchadenBeimGegner[] = new int[5];
		int notSchadenBeimSpieler[] = new int[5];

		/** Lege Kopien der Spielfelder und des Bitgenerators an */
		Spielfeld kopieEigenesSpielfeld = this.spielfeld.clone();
		Spielfeld kopieGegnerSpielfeld = gegnerSpielfeld.clone();
		Bitgenerator kopieBitfolge = (Bitgenerator)bitfolge.clone();
		
		
		boolean nutzeNot = false; // Ob Not gesetzt wird.
		int bitMitMeistemSchaden = 0; // Das Bit, welches wenn es invertiert wird, den meisten Schaden anrichtet.
		
		for(int i = 0; i < this.logikgatter.length; i++)
		{
			/** pruefe, ob Spieler ein NOT besitzt */
			if(this.logikgatter[i] instanceof Not )
			{
				/**  Durchlaufe alle Bits und schreibe den Schaden derer in das Array  */
				for(int bitCnt = 0; bitCnt < 4; bitCnt++)
				{
					kopieBitfolge.invertBit(bitCnt);
					
					/** Schreibt die Anzahl der ungueltigen Logikgatter in das Array an den Index des invertierten Bits */
					notSchadenBeimSpieler[bitCnt] = kopieEigenesSpielfeld.pruefeGueltigkeit(kopieBitfolge);
					notSchadenBeimGegner[bitCnt] = kopieGegnerSpielfeld.pruefeGueltigkeit(kopieBitfolge);
					
					
					/** Setze Kopien der Spielfelder durch erneutes invertieren des Bits auf den Ursprungszustand zurueck */
					kopieBitfolge.invertBit(bitCnt);
					kopieEigenesSpielfeld.pruefeGueltigkeit(kopieBitfolge);
					kopieGegnerSpielfeld.pruefeGueltigkeit(kopieBitfolge);
				}
				
				/**  Bestimmt das Bit mit dem meisten Schaden */
				for(int bitCnt = 0; bitCnt < 4; bitCnt++)
				{
					if( notSchadenBeimSpieler[bitCnt] < notSchadenBeimGegner[bitCnt] )
					{
						if(notSchadenBeimGegner[bitMitMeistemSchaden] < notSchadenBeimGegner[bitCnt])
						{
							bitMitMeistemSchaden = bitCnt;
						}
					}
				}
				
				/** Entscheide ob NOT zu nutzen ist */
				if( notSchadenBeimSpieler[bitMitMeistemSchaden] >= notSchadenBeimGegner[bitMitMeistemSchaden] )
				{
					nutzeNot = false;
				}
				else
				{
					nutzeNot = true; 
					indexVerwendetesGatter = i;
				}
				
				break; // The End ;)
			}
			
			nutzeNot = false; // Spieler besitzt kein NOT
		}
		
		if(nutzeNot)
		{
			
			this.zieheNeuesLogikgatter(indexVerwendetesGatter);
			bitfolge.invertBit(bitMitMeistemSchaden);

			SwingUtilities.invokeLater(new Runnable() 
			{
				public void run() 
			    {
					Spieler.this.sa.playWarning();
			    }
			});


		}
		else // Anderes Gatter muss gespielt werden.
		{
			/** 
			 * Indizes der ersten Dimension des Arrays stehen fuer die Gatter.
			 * In der zweiten Dimsension wird Reihe,Index und Prioritaet des Ablegeplatzes gespeichert.
			 *   0       1        2
			 * [reihe][index][prioritaet] x4 --> gatterPrioritaet[][steht hier]
			 *  */
			int[][] gatterPrioritaet = new int[4][3];
			
			/** 
			 * 	Durchlaeuft das Spielfeld und schreibt in das Array Reihe, Index und die Anzahl der moeglichen nachfolgenden Logikgatter, 
			 *  welche legbar sind. 
			 *  */
			
			for(int spielergatterCounter = 0; spielergatterCounter < 4; spielergatterCounter++ )
			{
				gatterPrioritaet[spielergatterCounter][2] = 0 ; // Setze Prioritaet auf 0
				
				for(int reihe = 0; reihe < 4; reihe++ )
				{
					for(int index = 0; index < 4; index++) // ungueltige Indizes werden abgefangen.
					{
						if( !(this.logikgatter[spielergatterCounter] instanceof Not) ) // pruefe, ob Spielergatter kein Not ist.
						{
							/** Lege Logikgatter auf Spielfeld, wenn es gesetzt werden kann und pruefe, ob weitere Gatter gelegt werden koennen */
							if( kopieEigenesSpielfeld.setLogikgatter(reihe, index, this.getLogikgatter(spielergatterCounter), kopieBitfolge) )
							{
								gatterPrioritaet[spielergatterCounter][0] = reihe; //speichere Reihe
								gatterPrioritaet[spielergatterCounter][1] = index; //Speichere Index
								gatterPrioritaet[spielergatterCounter][2]++; // erhoehe Prioritaet.
								
								/** Versuche die verbliebenen Gatter zu legen */
								for(int probeSpielergatterCounter = 0; probeSpielergatterCounter < 4; probeSpielergatterCounter++)
								{
									/** Versucht weitere Gatter zu legen, um die Prioritaet des gelegten Gatters zu bestimmen */
									for(int probeReihe = 0; probeReihe < 4; probeReihe++ )
									{
										for(int probeIndex = 0; probeIndex < 4; probeIndex++) // ungueltige Indizes werden abgefangen.
										{
											/** pruefe, ob das Probegatter nicht Not ist und nicht mit dem testweise gelegtem Gatter uebereinstimmt */
											if( !(this.logikgatter[probeSpielergatterCounter] instanceof Not) && probeSpielergatterCounter == spielergatterCounter)
											{
												if( kopieEigenesSpielfeld.setLogikgatter(probeReihe, probeIndex, this.getLogikgatter(probeSpielergatterCounter), kopieBitfolge) )
												{
													gatterPrioritaet[spielergatterCounter][0] = reihe;
													gatterPrioritaet[spielergatterCounter][1] = index;
													gatterPrioritaet[spielergatterCounter][2]++;
												}
											}
										}
									}
								}
								
								kopieEigenesSpielfeld = this.spielfeld.clone(); // Setze kopie des Spielfelds zurueck.
							}
						}
					}
				}
			}

			
			
			/** Sucht Gatter mit hoechster prioritaet */
			int priorisiertesGatter = 0;
			
			for(int i = 0; i < 4;i++)
			{
				if(gatterPrioritaet[i][2] > gatterPrioritaet[priorisiertesGatter][2] )
				{
					priorisiertesGatter = i;
				}
			}
			
			/** Entscheide, ob Logikgatter auf Spielfeld gelegt werden soll oder ein Neues gezogen werden soll */
			if(gatterPrioritaet[priorisiertesGatter][2] > 0)
			{
				if( !(this.spielfeld.setLogikgatter(gatterPrioritaet[priorisiertesGatter][0], gatterPrioritaet[priorisiertesGatter][1], this.logikgatter[priorisiertesGatter], bitfolge)) )
				{
					System.out.println("--------- KI Entscheidungsfehler !!! ---------");
					System.out.println("Gatter welches gespielt werden sollte: " + priorisiertesGatter);
					System.out.println("Gatter Prioritaet: " + gatterPrioritaet[priorisiertesGatter][2] );
					System.out.println("Gatter Reihe: " + gatterPrioritaet[priorisiertesGatter][0] );
					System.out.println("Gatter Index: " + gatterPrioritaet[priorisiertesGatter][1] );
				}
				this.zieheNeuesLogikgatter(priorisiertesGatter);
			}
			else // Ziehe Neues
			{
				this.zieheNeuesLogikgatter(new Random().nextInt(4));
			}
		}

	}
	

}

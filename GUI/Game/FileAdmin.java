package GUI.Game;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



import GUI.Game.FileAdmin;



public class FileAdmin {

	private FileWriter writer;
	private static File file;
	private FileReader fr;
	private int[] trennzeichen;
	private static String player1Name;
	private static String player2Name;
	private static Boolean ki;
	private static Boolean cache;
	private static Boolean hardcoreMode;
	private static int height;
	private static int width;
	private static Boolean sound;
	private static Boolean music;
	private static Boolean fullscreen;
	private String tmp;
	
	/**
	 * Erstellt einen FileAdmin und liest die Werte aus der Datei infos.ini aus
	 */
	public FileAdmin()
	{
		FileAdmin.file = new File( "infos.ini" );
		this.trennzeichen = new int[9];
		FileAdmin.player1Name = "Player1";
		FileAdmin.player2Name = "Player2";
		FileAdmin.ki = false;
		FileAdmin.cache = false;
		FileAdmin.hardcoreMode = false;
		FileAdmin.height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		FileAdmin.width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		FileAdmin.sound = true;
		FileAdmin.music = true;
		FileAdmin.fullscreen = false;
		
		try
		{
			this.tmp = this.readOfFile();
			
		}catch(Exception e)
		{
			System.out.println( "infos.ini wurde angelegt" );
		}


		if(!(file.exists()))
		{
			try{
			writer = new FileWriter(file);
			
			}catch(IOException e)
			{
				System.out.println("Konfig erstellt");
			}
			
			
		}
		//System.out.println("Datei gibts nicht");
		
		if( this.readOfFile().length() == 0 )
		{
			System.out.println("Dummywerte");
			this.updateFile();
	
		}else
		{
			int counter=0;
			for( int i=0; i < this.tmp.length(); i++)
			{
				if( this.tmp.charAt( i ) == ';' )
				{
					this.trennzeichen[ counter ] = i;
					counter++;
				}
			}
		
			StringBuffer temporaer = new StringBuffer();
			
			for(int i=0; i< trennzeichen[0]; i++)
			{
				temporaer.append( tmp.charAt(i) );
			}
			
		
			this.setHeight(Integer.parseInt(temporaer.toString()) );
			
			temporaer.delete(0, temporaer.length());
			
			//System.out.println( Gruetze.height );
			
			for( int i=trennzeichen[0]+1; i < trennzeichen[1]; i++)
			{
				temporaer.append( tmp.charAt(i) );
			}
			
			this.setWidth( Integer.parseInt(temporaer.toString()) );
			temporaer.delete(0, temporaer.length());
			
			for( int i=trennzeichen[1]+1; i < trennzeichen[2]; i++ )
			{
				temporaer.append( tmp.charAt(i) );
			}
			//System.out.println(temporaer);
			this.setPlayer1Name( temporaer.toString() );
			temporaer.delete(0, temporaer.length());
			
			for( int i=trennzeichen[2]+1; i < trennzeichen[3]; i++)
			{
				temporaer.append( tmp.charAt(i) );
			}
			
			this.setPlayer2Name( temporaer.toString() );
			temporaer.delete(0, temporaer.length());
			
			for(int i=trennzeichen[3]+1; i < trennzeichen[4]; i++)
			{
				temporaer.append( tmp.charAt(i) );
			}
			if ( temporaer.toString().matches("false") )
			{
				this.setKI( false );
			}else
			{
				this.setKI( true );
			}
			temporaer.delete(0, temporaer.length());
			
			for(int i=trennzeichen[4]+1; i < trennzeichen[5]; i++)
			{
				temporaer.append( tmp.charAt(i) );
			}
			
			if ( temporaer.toString().matches("false") )
			{
				this.setCache( false );
			}else
			{
				this.setCache( true );
			}
			
			temporaer.delete(0, temporaer.length());
			
		//	System.out.println(trennzeichen[5]);
			for(int i=trennzeichen[5]+1; i < trennzeichen[6]; i++)
			{
				temporaer.append( tmp.charAt(i) );
			}
			
		//	System.out.println(temporaer);
			
			if ( temporaer.toString().matches("false") )
			{
				this.setHardCoreMode( false );
			}else
			{
				this.setHardCoreMode( true );
			}

			temporaer.delete(0, temporaer.length());
			
			for(int i=trennzeichen[6]+1; i < trennzeichen[7]; i++)
			{
				temporaer.append( tmp.charAt(i) );
			}
			
			if ( temporaer.toString().matches("false") )
			{
				this.setSound( false );
			}else
			{
				this.setSound( true );
			}
			temporaer.delete(0, temporaer.length());
			
			for(int i=trennzeichen[7]+1; i < trennzeichen[8]; i++)
			{
				temporaer.append( tmp.charAt(i) );
			}
			
			if ( temporaer.toString().matches("false") )
			{
				this.setMusic( false );
			}else
			{
				this.setMusic( true );
			}
			temporaer.delete(0, temporaer.length());

			for(int i=trennzeichen[8]+1; i < tmp.length(); i++)
			{
				temporaer.append( tmp.charAt(i) );
			}
			
			if ( temporaer.toString().matches("false") )
			{
				this.setFullscreen( false );
			}else
			{
				this.setFullscreen( true );
			}
			temporaer.delete(0, temporaer.length());
		}
		
	}
	/**
	 * Aktualisiert den Inhalt der Datei mit den aktuellen Daten
	 */
	public void updateFile()
	{
		try{
			 writer = new FileWriter(file); 
			// System.out.println( "-----------------schreibt:" + FileAdmin.music ); 
			 writer.write( "" + FileAdmin.height + ";" + FileAdmin.width + ";" + FileAdmin.player1Name + ";" + FileAdmin.player2Name + ";" + FileAdmin.ki + ";" + FileAdmin.cache + ";" + FileAdmin.hardcoreMode + ";" + FileAdmin.sound + ";" + FileAdmin.music + ";" + FileAdmin.fullscreen);
			 writer.flush(); //leert den  Stream
			 
			 writer.close(); //schließt den Stream
			 
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Setzt die Hoehe in der Datei infos.ini
	 * @param height zu setzende hoehe
	 * @return gueltigkeit des Wertes
	 */
	public boolean setHeight( int height )
	{
		try
		{
			if( height > 0 )
			{
				FileAdmin.height = height;
				this.updateFile();
				return true;
			}//Falls so ein Witzbold Buchstaben eingibt
		}catch(NumberFormatException e)
		{
				return false;
		}
		return false;
	}
	/**
	 * Setzt die Breite in der Datei infos.ini
	 * @param width zu setzende Breite
	 * @return gueltigkeit des Wertes
	 */
	public boolean setWidth( int width )
	{
		try
		{
			if( width > 0 )
			{
				FileAdmin.width = width;
				this.updateFile();
				return true;
			}
		} catch(NumberFormatException x)
			{
				return false;
			}
		return false;
	}
	/**
	 * Setzt, ob gegen die KI gespielt werden soll
	 * @param ki Wert fuer die KI
	 * @return Erfolg des Setzens
	 */
	public boolean setKI( boolean ki )
	{
		FileAdmin.ki = ki;
		this.updateFile();
		return true;
	}
	/**
	 * Setzt, ob Fullscreen verwendet werden soll
	 * @param fullscreen Wert, ob Fullscreen verwendet werden soll
	 * @return Erfolg des Setzens
	 */
	public boolean setFullscreen( boolean fullscreen )
	{
		FileAdmin.fullscreen = fullscreen;
		this.updateFile();
		return true;
	}
	
	/**
	 * Setzt, ob im HardCoreMode gespielt werden soll
	 * @param hardcore Zustand des HardcoreModes
	 * @return Erfolg des Setzens
	 */
	public boolean setHardCoreMode( boolean hardcore )
	{
		FileAdmin.hardcoreMode = hardcore;
		this.updateFile();
		return true;
	}
	/**
	 * Setzt, ob Musik gespielt werden soll
	 * @param music Zustand der Musik
	 * @return Erfolg des Setzens
	 */
	public boolean setMusic( boolean music )
	{
		FileAdmin.music = music;
		this.updateFile();
		return true;
	}
	/**
	 * Setzt, ob Sound wiedergegeben werden soll
	 * @param sound Zustand des Sounds
	 * @return Erfolg des Setzens
	 */
	public boolean setSound( boolean sound )
	{
		FileAdmin.sound = sound;
		this.updateFile();
		return true;
	}
	/**
	 * Setzt, ob der Grafikcache an oder aus sein soll
	 * @param cache Zustand des Grafikcaches
	 * @return Erfolg des Setzens
	 */
	public boolean setCache( boolean cache )
	{
		FileAdmin.cache = cache;
		this.updateFile();
		return true;
	}
	/**
	 * Setzt den Namen von Spieler 2
	 * @param name2 Name von Spieler 2
	 * @return Erfolg des Setzens
	 */
	public boolean setPlayer2Name( String name2 )
	{
	//	if( name2.length() > 0 )
	//	{
			FileAdmin.player2Name = name2;
			this.updateFile();
			return true;
	//	}
	//	return false;
	}
	/**
	 * Setzt den Namen von Spieler 1
	 * @param name1 Name von Spieler 1
	 * @return Erfolg des Setzens
	 */
	public boolean setPlayer1Name( String name1 )
	{
		//if( name1.length() > 0 )
		//{
			FileAdmin.player1Name = name1;
			this.updateFile();
			return true;
//		}
	//	return false;
	}
	/**
	 * Gibt die gesetzte Hoehe zurueck
	 * @return Gesetzte Hoehe
	 */
	public int getHeight()
	{
		return FileAdmin.height;
	}
	/**
	 * Gibt zurueck, ob Fullscreen verwendet wird
	 * @return Wert, ob Fullscreen verwendet wird
	 */
	public boolean getFullscreenZustand()
	{
		return FileAdmin.fullscreen;
	}
	/**
	 * Gibt die gesetzte Breite zurueck
	 * @width Gesetzte Breite
	 */
	public int getWidth()
	{
		return FileAdmin.width;
	}
	/**
	 * Gibt den Namen von Spieler 1 zurueck
	 * @return Name von Spieler 1
	 */
	public String getPlayer1Name()
	{
		//System.out.println(Gruetze.player1Name.toString());
		return FileAdmin.player1Name.toString();
		
	}
	/**
	 * Gibt den Zustand des Caches zurueck
	 * @return Zustand des Caches
	 */
	public boolean getCacheZustand()
	{
		return FileAdmin.cache;
	}
	/**
	 * Gibt den Namen von Spieler 2 zurueck
	 * @return Name von Spieler 2
	 */
	public String getPlayer2Name()
	{
		return FileAdmin.player2Name.toString();
	}
	/**
	 * Gibt den Zustand des HardcoreModes zurueck
	 * @return Zustand des HardcoreModes
	 */
	public boolean getHardCoreZustand()
	{
		return FileAdmin.hardcoreMode;
	}
	/**
	 * Gibt den Zustand des KI - Modus zurueck
	 * @return Zustand des KI - Mode
	 */
	public boolean getKiZustand()
	{
		return FileAdmin.ki;
	}
	/**
	 * Gibt den Zustand des Musik - Modus zurueck
	 * @return Zustand des Musik - Modus
	 */
	public boolean getMusicZustand()
	{
		return FileAdmin.music;
	}
	/**
	 * Gibt den Zustand des Soun - Modus zurueck
	 * @return Zustand des Sound Modus
	 */
	public boolean getSoundZustand()
	{
		return FileAdmin.sound;
	}
	
	/**
	 * Liest alle Werte aus der Datei in einen String ein
	 * @return String, der alle Werte der Datei infos.ini enthält
	 */
	public String readOfFile(){
		
	    this.fr = null;
	    int c;
	    StringBuffer buff = new StringBuffer();
	    try {
	    	try{
	    		fr = new FileReader(file);
		     }catch(FileNotFoundException e)
		     {
		    	 System.out.println( "infos.ini nicht gefunden!" );
		     }
	        while ((c = fr.read()) != -1) {
	            buff.append((char) c);
	        }
	        fr.close();

	    } catch (IOException e) {
	        System.out.println();
	    } 
			return buff.toString();
		}

/*	public static void main( String[] arg )
	{
		FileAdmin fa = new FileAdmin();
		System.out.println( fa.readOfFile() );
		System.out.println( fa.getFullscreenZustand() );
		fa.setFullscreen(false);
		System.out.println( fa.getFullscreenZustand() );
		
		FileAdmin la = new FileAdmin();
		System.out.println( la.getFullscreenZustand() );
	}*/
}




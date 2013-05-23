package GUI.Game;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileAdmin {
	
	//private static String inhalt="";
	private static Dimension resolution;
	private StringBuffer player1Name;
	private StringBuffer player2Name;
	//private static boolean cache;
	private FileWriter writer;
	private File file;
	private FileReader fr;
	private static StringBuffer zustand;
	private static Boolean cache;
	
	public FileAdmin(){
		
		FileAdmin.resolution = Toolkit.getDefaultToolkit().getScreenSize();
		this.player1Name = new StringBuffer( "Player1" );
		this.player2Name = new StringBuffer( "Player2" );
		FileAdmin.zustand = new StringBuffer( this.cache.toString() );
		

				file = new File("gameinfo.txt");
		
		try{
			 writer = new FileWriter(file); //datei aufloesung.txt muss existieren
			 								//, wird jedoch ueberschrieben
			 writer.write("" + (int)FileAdmin.resolution.getHeight() + "x" + (int)FileAdmin.resolution.getWidth() + ";" + this.player1Name + ";" + this.player2Name + ";" + FileAdmin.zustand); //Werte werden durch Komma getrennt

			 writer.flush(); //leert den  Stream
			 
			 writer.close(); //schließt den Stream
			 
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	
	public void writeInFile(){
		
		try{
			 writer = new FileWriter(file); //datei aufloesung.txt muss existieren
			 								//, wird jedoch ueberschrieben
			 writer.write("" + (int)FileAdmin.resolution.getHeight() + "x" + (int)FileAdmin.resolution.getWidth() + ";" + this.player1Name + ";" + this.player2Name + ";" + FileAdmin.zustand); //Werte werden durch Komma getrennt

			 writer.flush(); //leert den  Stream
			 
			 writer.close(); //schließt den Stream
			 
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String readOfFile(){
	
	//return "900x1440;Player1;Player2;";	
	
    this.fr = null;
    int c;
    StringBuffer buff = new StringBuffer();
    try {
        fr = new FileReader(file);
        while ((c = fr.read()) != -1) {
            buff.append((char) c);
        }
        fr.close();

    } catch (IOException e) {
        e.printStackTrace();
    } 
		return buff.toString();
	}

	public Dimension getAufloesung(){
		return FileAdmin.resolution;
	}
	
	
	public void setCache( boolean cache )
	{
		FileAdmin.cache = cache;
	}
	
	public boolean setPlayer1Name( StringBuffer name )
	{
		this.player1Name = name;
		this.writeInFile();
		return true;
	}
	
	public boolean setPlayer2Name( StringBuffer name )
	{
		this.player2Name = name;
		this.writeInFile();
		return true;
	}
	
	public boolean setAufloesung( Dimension resolution )
	{
		if( resolution.getHeight() > 0 && resolution.getWidth() > 0 )
		{
			FileAdmin.resolution = resolution;
			return true;
		}
		return false;
	}
	
	//public 
	
	public String getPlayer1Name()
	{
	
		String tmp = this.readOfFile();
		int counter=0;
		//SpielerName (zwischen ersten zwei ';') auslesen
		for( int i=tmp.indexOf(';')+1; i < tmp.indexOf(';', tmp.indexOf(';')+2 );i++)
		{
			//this.player1Name = "";
			this.player1Name.setCharAt(counter,tmp.charAt(i));		
		//	System.out.println(tmp.length());
		//h	System.out.println( this.player1Name + "" + (char)tmp.charAt(i));
			counter++;
		}
		return this.player1Name.toString();
	}
	
	public String getPlayer2Name()
	{
		String tmp = this.readOfFile();
		int counter=0;
		//SpielerName (zwischen ersten zwei ';') auslesen
		for( int i=tmp.indexOf(';' , tmp.indexOf(';')+1) ; i < tmp.indexOf(';', tmp.indexOf(';')+2 );i++)
		{
			//this.player1Name = "";
			this.player2Name.setCharAt(counter,tmp.charAt(i));		
			//System.out.println(tmp.charAt(i));
		//h	System.out.println( this.player1Name + "" + (char)tmp.charAt(i));
			counter++;
		}
		return this.player2Name.toString();
	}
	
	public boolean getGrafikCache()
	{
		String tmp = this.readOfFile();
		int counter=0;
		//Zustand (zwischen ';') auslesen
		for( int i=tmp.lastIndexOf(';')+1; i < tmp.length();i++)
		{
			//this.player1Name = "";
			zustand.setCharAt(counter,tmp.charAt(i));		
			
		//	System.out.println( tmp.charAt(tmp.indexOf(';' , tmp.indexOf(';' , tmp.indexOf(';')+1)+1)+2) );
			counter++;
		}
		System.out.println(zustand.toString());
		if ( zustand.toString().matches("false") )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	

}
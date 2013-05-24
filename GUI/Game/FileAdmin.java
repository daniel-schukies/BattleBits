package GUI.Game;

import java.awt.Toolkit;
import java.io.File;
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
	private String tmp;
	
	
	public FileAdmin()
	{
		FileAdmin.file = new File( "infos.ini" );
		this.trennzeichen = new int[7];
		FileAdmin.player1Name = "Player1";
		FileAdmin.player2Name = "Player2";
		FileAdmin.ki = false;
		FileAdmin.cache = false;
		FileAdmin.hardcoreMode = false;
		FileAdmin.height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		FileAdmin.width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		this.tmp = this.readOfFile();


		if(!(file.exists()))
		{
			try{
			writer = new FileWriter(file);
			
			}catch(IOException e)
			{
				System.out.println("Konfig erstellt");
			}
		}

		
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
			for(int i=trennzeichen[5]+1; i < tmp.length(); i++)
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
			
		}
		
	}
	
	public void updateFile()
	{
		try{
			 writer = new FileWriter(file); 
			 writer.write( "" + FileAdmin.height + ";" + FileAdmin.width + ";" + FileAdmin.player1Name + ";" + FileAdmin.player2Name + ";" + FileAdmin.ki + ";" + FileAdmin.cache + ";" + FileAdmin.hardcoreMode);
			 writer.flush(); //leert den  Stream
			 
			 writer.close(); //schließt den Stream
			 
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
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
	
	public boolean setKI( boolean ki )
	{
		FileAdmin.ki = ki;
		this.updateFile();
		return true;
	}
	
	public boolean setHardCoreMode( boolean hardcore )
	{
		FileAdmin.hardcoreMode = hardcore;
		return true;
	}
	
	public boolean setCache( boolean cache )
	{
		FileAdmin.cache = cache;
		this.updateFile();
		return true;
	}
	
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
	
	public int getHeight()
	{
		return FileAdmin.height;
	}
	
	public int getWidth()
	{
		return FileAdmin.width;
	}
	
	public String getPlayer1Name()
	{
		//System.out.println(Gruetze.player1Name.toString());
		return FileAdmin.player1Name.toString();
		
	}
	
	public boolean getCacheZustand()
	{
		return FileAdmin.cache;
	}
	
	public String getPlayer2Name()
	{
		return FileAdmin.player2Name.toString();
	}
	
	public boolean getHardCoreZustand()
	{
		return FileAdmin.hardcoreMode;
	}
	
	public boolean getKiZustand()
	{
		return FileAdmin.ki;
	}
	
	public String readOfFile(){
		
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


}




package GUI.Game;


import java.net.URL;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
/**
 * Klasse zur Wiedergabe der einzelnen Sounds / der Musik
 * @author Daniel Schukies, Sebastian Junger
 *
 */
public class SoundAusgabe 
{
	private FileAdmin fa = new FileAdmin();

	private URL  audioFileURL;
	
	private static int soundCnt = 0;
	

	/**
	 * Gibt die Hintergrundmusik wieder
	 */
	public void playSound()
	{
		if( fa.getMusicZustand() )
		{
			if(SoundAusgabe.soundCnt == 0)
			{
				try 
			  	 { 
			  		 this.audioFileURL = getClass().getResource("/loading1.wav");
			  		 InputStream inStream = audioFileURL.openStream(); 
			  		 BufferedInputStream bufStream = new BufferedInputStream(inStream);  
			  		 //Bildet einen AudioInputStream mit dem Informat
			  		 AudioInputStream audioInputStream = AudioSystem .getAudioInputStream(bufStream); 
			  		 //fragt das Format der Datei ab
			  		 AudioFormat af = audioInputStream.getFormat();
			  		 //berechnet die Buffergroesse
			  		 int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
			  		 byte[] audio = new byte[size];
			  		 DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			  		 audioInputStream.read(audio, 0, size);
			  		 //Initialisiert einen Clip mit den Informationen des AudioSystems
			  		 Clip clip = (Clip) AudioSystem.getLine(info);
			  		 clip.open(af, audio, 0, size); 
			  		 //Hintergrundmusik soll endlos laufen
			  		 clip.loop(Clip.LOOP_CONTINUOUSLY);
			  		 //Start der Wiedergabe
			  		 clip.start(); 
			  		 
			  		 SoundAusgabe.soundCnt++;
			  		 
			  	}
				catch(Exception e)
			  	{
			  		System.out.println("Fail");
			  	}
			}
			else
			{
				System.out.println("Sound laeuft bereits");
			}
		}
	}
	
	/**
	 * Gibt den Sound bei einer Invertierung wieder
	 */
	public void playWarning()
	{
		if( fa.getSoundZustand() )
		{
		  	 try 
		  	 { 
		  		 this.audioFileURL = getClass().getResource("/warning.wav");
		  		 System.out.println("Playing: " + audioFileURL); 
		  		 InputStream inStream = audioFileURL.openStream(); 
		  		 BufferedInputStream bufStream = new BufferedInputStream(inStream);  
		  		 AudioInputStream audioInputStream = AudioSystem .getAudioInputStream(bufStream); 
		  		 AudioFormat af = audioInputStream.getFormat(); 
		  		 int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
		  		 byte[] audio = new byte[size];
		  		 DataLine.Info info = new DataLine.Info(Clip.class, af, size);
		  		 audioInputStream.read(audio, 0, size);
		  		 Clip clip = (Clip) AudioSystem.getLine(info);
		  		 clip.open(af, audio, 0, size); 
		  		 clip.start(); 
		  	}
		  	catch(Exception e)
		  	{
		  		System.out.println("Fail");
		  	}
		}

	}
	
	/**
	 * Gibt den Sound bei einem fehlerhaften Zug wieder
	 */
	public void playError()
	{
		if( fa.getSoundZustand() )
		{
		  	 try 
		  	 { 
		  		 this.audioFileURL = getClass().getResource("/error.wav");
		  		 System.out.println("Playing: " + audioFileURL); 
		  		 InputStream inStream = audioFileURL.openStream(); 
		  		 BufferedInputStream bufStream = new BufferedInputStream(inStream);  
		  		 AudioInputStream audioInputStream = AudioSystem .getAudioInputStream(bufStream); 
		  		 AudioFormat af = audioInputStream.getFormat(); 
		  		 int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
		  		 byte[] audio = new byte[size];
		  		 DataLine.Info info = new DataLine.Info(Clip.class, af, size);
		  		 audioInputStream.read(audio, 0, size);
		  		 Clip clip = (Clip) AudioSystem.getLine(info);
		  		 clip.open(af, audio, 0, size); 
		  		 clip.start(); 
		  	}
		  	catch(Exception e)
		  	{
		  		System.out.println("Fail");
		  	}
		}


	}
	
	/**
	 * Gibt den Sound beim Ziehen eines neuen Gatters wieder
	 */
	public void playNeuZiehen()
	{
		if( fa.getSoundZustand() )
		{
		  	 try 
		  	 { 
		  		 this.audioFileURL = getClass().getResource("/neuziehen.wav");
		  		 System.out.println("Playing: " + audioFileURL); 
		  		 InputStream inStream = audioFileURL.openStream(); 
		  		 BufferedInputStream bufStream = new BufferedInputStream(inStream);  
		  		 AudioInputStream audioInputStream = AudioSystem .getAudioInputStream(bufStream); 
		  		 AudioFormat af = audioInputStream.getFormat(); 
		  		 int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
		  		 byte[] audio = new byte[size];
		  		 DataLine.Info info = new DataLine.Info(Clip.class, af, size);
		  		 audioInputStream.read(audio, 0, size);
		  		 Clip clip = (Clip) AudioSystem.getLine(info);
		  		 clip.open(af, audio, 0, size); 
		  		 clip.start();
			}
		  	catch(Exception e)
		  	{
		  		System.out.println("Fail");
		  	}
		}
	}
	
}

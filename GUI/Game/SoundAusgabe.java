package GUI.Game;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundAusgabe {
	
	private AudioInputStream stream;
	private AudioFormat format;
	private  DataLine.Info info;
	private  Clip clip;
	private URL url;
	
	public SoundAusgabe()
	{
		
	}

	public void play( URL url)
	{
		try {
		   // File neuziehen;
		    this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		}
		catch (Exception ex) {
		    System.out.println( "Sound fehler ");
		}
	}
	
	public void playWarning()
	{
		try{
		
			this.url = getClass().getResource("/warning.wav");
			this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		
		}catch (Exception ex) {
		    System.out.println( "Sound fehler ");
		}

	}
	
	public void playError()
	{
		try{
			
			this.url = getClass().getResource("/error.wav");
			this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		
		}catch (Exception ex) {
		    System.out.println( "Sound fehler ");
		}
	}
	
	public void playNeuZiehen()
	{
		try{
			
			this.url = getClass().getResource("/neuziehen.wav");
			this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		
		}catch (Exception ex) {
		    System.out.println( "Sound fehler ");
		}
	}
	
}

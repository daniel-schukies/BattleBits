package GUI.Game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
	private List<AudioClip> audioliste = new ArrayList<AudioClip>();
	
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
		/*try{
		
			this.url = getClass().getResource("/warning.wav");
			this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		
		}catch (Exception ex) {
		    System.out.println( "Sound fehler ");
		}*/
		
		URL sound_url = getClass().getResource("/warning.wav");
        AudioClip audio = (AudioClip)Applet.newAudioClip(sound_url);
        audioliste.add(audio);
        audioliste.get(0).play();
	}
	
	public void playError()
	{
		/*try{
			
			this.url = getClass().getResource("/error.wav");
			this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		
		}catch (Exception ex) {
		    System.out.println( "Sound fehler ");
		}*/
		URL sound_url = getClass().getResource("/error.wav");
		//System.out.println( "error:" + sound_url );
        AudioClip audio = (AudioClip)Applet.newAudioClip(sound_url);
        audioliste.add(audio);
        audioliste.get(0).play();
	}
	
	public void playNeuZiehen()
	{
		
		URL sound_url = getClass().getResource("/neuziehen.wav");
        AudioClip audio = (AudioClip)Applet.newAudioClip(sound_url);
        audioliste.add(audio);
        audioliste.get(0).play();
		
	/*	try{
			
			this.url = getClass().getResource("/neuziehen.wav");
			this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		
		}catch (Exception ex) {
		    System.out.println( "Sound fehler ");
		}*/
	}
	
}

package GUI.Game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.BufferedInputStream;
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
	private URL  audioFileURL;;
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
		/*  InputStream path = getClass().getResourceAsStream("/warning.wav");
		  	try{
		  	    clip = AudioSystem.getClip();
		  	    clip.open(AudioSystem.getAudioInputStream(path));
		  	    clip.start();
		  	}catch(Exception e)
		  	{
		  		System.out.println("Fehler");
		  	}*/
	  	 try { 
	  		 this.audioFileURL = getClass().getResource("/warning.wav");
	  		 System.out.println("Playing: " + audioFileURL); 
	  		 InputStream inStream = audioFileURL.openStream(); 
	  		 BufferedInputStream bufStream = new BufferedInputStream(inStream); 
	  		 System.out.println("InputStream Type: " + inStream.getClass().getName()); 
	  		 System.out.println("BufferedInputStream Type: " + bufStream.getClass().getName()); 
	  		 AudioInputStream audioInputStream = AudioSystem .getAudioInputStream(bufStream); 
	  		 AudioFormat af = audioInputStream.getFormat(); 
	  		 System.out.println("AudioFormat: " + af); 
	  		 System.out.println("AudioFormatFrameSize: " + af.getFrameSize()); 
	  		 System.out.println("AudioInpuStreamFrameLength: " + audioInputStream.getFrameLength());
	  		 int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
	  		 byte[] audio = new byte[size];
	  		 DataLine.Info info = new DataLine.Info(Clip.class, af, size);
	  		 audioInputStream.read(audio, 0, size);
	  		 Clip clip = (Clip) AudioSystem.getLine(info);
	  		 clip.open(af, audio, 0, size); clip.start(); // Das musste ich auskommentieren, sonst spielt er bei mir gar nichts. // while (clip.isActive()) { // } // clip.stop(); } catch (Exception e) { e.printStackTrace(); .. mehr auf http://w-w-w.ms/a33t56
	  	 }catch(Exception e)
	  	 {
	  		 System.out.println("Fail");
	  	 }
		/*try
		{
			URL url = getClass().getResource("/warning.wav");
			this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		
		}catch(Exception e)
		{
			System.out.println("Sound Fehler");
		}*/
		
		//URL sound_url = getClass().getResource("/warning.wav");
        //AudioClip audio = (AudioClip)Applet.newAudioClip(sound_url);
      //  audioliste.add(audio);
       // audio.play();
	}
	
	public void playError()
	{
	/*	  InputStream path = getClass().getResourceAsStream("/error.wav");
		  	try{
		  	    clip = AudioSystem.getClip();
		  	    clip.open(AudioSystem.getAudioInputStream(path));
		  	    clip.start();
		  	}catch(Exception e)
		  	{
		  		System.out.println("Fehler");
		  	}*/
	  	 try { 
	  		 this.audioFileURL = getClass().getResource("/error.wav");
	  		 System.out.println("Playing: " + audioFileURL); 
	  		 InputStream inStream = audioFileURL.openStream(); 
	  		 BufferedInputStream bufStream = new BufferedInputStream(inStream); 
	  		 System.out.println("InputStream Type: " + inStream.getClass().getName()); 
	  		 System.out.println("BufferedInputStream Type: " + bufStream.getClass().getName()); 
	  		 AudioInputStream audioInputStream = AudioSystem .getAudioInputStream(bufStream); 
	  		 AudioFormat af = audioInputStream.getFormat(); 
	  		 System.out.println("AudioFormat: " + af); 
	  		 System.out.println("AudioFormatFrameSize: " + af.getFrameSize()); 
	  		 System.out.println("AudioInpuStreamFrameLength: " + audioInputStream.getFrameLength());
	  		 int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
	  		 byte[] audio = new byte[size];
	  		 DataLine.Info info = new DataLine.Info(Clip.class, af, size);
	  		 audioInputStream.read(audio, 0, size);
	  		 Clip clip = (Clip) AudioSystem.getLine(info);
	  		 clip.open(af, audio, 0, size); clip.start(); // Das musste ich auskommentieren, sonst spielt er bei mir gar nichts. // while (clip.isActive()) { // } // clip.stop(); } catch (Exception e) { e.printStackTrace(); .. mehr auf http://w-w-w.ms/a33t56
	  	 }catch(Exception e)
	  	 {
	  		 System.out.println("Fail");
	  	 }
	/*	try
		{
			URL url = getClass().getResource("/error.wav");
			this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		
		}catch(Exception e)
		{
			System.out.println("Sound Fehler");
		}*/
	//	URL sound_url = getClass().getResource("/error.wav");
     //   AudioClip audio = (AudioClip)Applet.newAudioClip(sound_url);
      //  audioliste.add(audio);
     //   audio.play();
	}
	
	public void playNeuZiehen()
	{
		/*  InputStream path = getClass().getResource("/neuziehen.wav");
		  	try{
		  	    clip = AudioSystem.getClip();
		  	    clip.open(AudioSystem.getAudioInputStream(path));
		  	    clip.start();
		  	}catch(Exception e)
		  	{
		  		System.out.println("Fehler");
		  	}
		  	
		  	InputStream is= getClass().getResourceAsStream("/images/ads/WindowsNavigationStart.wav");
		  	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);*/
		  	
		  	 try { 
		  		 this.audioFileURL = getClass().getResource("/neuziehen.wav");
		  		 System.out.println("Playing: " + audioFileURL); 
		  		 InputStream inStream = audioFileURL.openStream(); 
		  		 BufferedInputStream bufStream = new BufferedInputStream(inStream); 
		  		 System.out.println("InputStream Type: " + inStream.getClass().getName()); 
		  		 System.out.println("BufferedInputStream Type: " + bufStream.getClass().getName()); 
		  		 AudioInputStream audioInputStream = AudioSystem .getAudioInputStream(bufStream); 
		  		 AudioFormat af = audioInputStream.getFormat(); 
		  		 System.out.println("AudioFormat: " + af); 
		  		 System.out.println("AudioFormatFrameSize: " + af.getFrameSize()); 
		  		 System.out.println("AudioInpuStreamFrameLength: " + audioInputStream.getFrameLength());
		  		 int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
		  		 byte[] audio = new byte[size];
		  		 DataLine.Info info = new DataLine.Info(Clip.class, af, size);
		  		 audioInputStream.read(audio, 0, size);
		  		 Clip clip = (Clip) AudioSystem.getLine(info);
		  		 clip.open(af, audio, 0, size); clip.start(); // Das musste ich auskommentieren, sonst spielt er bei mir gar nichts. // while (clip.isActive()) { // } // clip.stop(); } catch (Exception e) { e.printStackTrace(); .. mehr auf http://w-w-w.ms/a33t56
		  	 }catch(Exception e)
		  	 {
		  		 System.out.println("Fail");
		  	 }
		  	
		/*try
		{
			URL url = getClass().getResource("/neuziehen.wav");
			this.stream = AudioSystem.getAudioInputStream(url);
		    this.format = stream.getFormat();
		    this.info = new DataLine.Info(Clip.class, format);
		    this.clip = (Clip) AudioSystem.getLine(info);
		    this.clip.open(stream);
		    this.clip.start();
		
		}catch(Exception e)
		{
			System.out.println("Sound Fehler");
		}*/
		
	//	URL sound_url = getClass().getResource("/neuziehen.wav");
    //    AudioClip audio = (AudioClip)Applet.newAudioClip(sound_url);
        //audioliste.add(audio);
    //    audio.play();
		
	}
	
}

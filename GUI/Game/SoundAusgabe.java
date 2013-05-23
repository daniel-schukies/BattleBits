package GUI.Game;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundAusgabe {
	
	public SoundAusgabe()
	{
		
	}

public void play( URL url)
{
	try {
	   // File neuziehen;
	    AudioInputStream stream;
	    AudioFormat format;
	    DataLine.Info info;
	    Clip clip;
	    stream = AudioSystem.getAudioInputStream(url);
	    format = stream.getFormat();
	    info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}
	catch (Exception ex) {
	    System.out.println( "Sound fehler ");
	}
}
}

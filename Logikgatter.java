import java.util.Random;
public class Logikgatter {
	
	private boolean ausgang; 
	private boolean isAktiv;
	private boolean isGueltig;
	
	//Im Konstruktor wird ein zu erreichender Ausgangswert festgelegt
	public Logikgatter(){
		Random r = new Random();
		int zufall=r.nextInt(2); //Zufallswert: 0 oder 1
		switch (zufall){
			case 0: ausgang=false;
			break;
			case 1: ausgang=true;
			break;
		}
		
	}
	public boolean getAusgang(){
		return ausgang;
	}
	public void aktivieren(){
		isAktiv=true;
	}
	public void deaktivieren(){
		isAktiv=false;
	}
	public void loesche(){
		isGueltig=false;
	}
	
}


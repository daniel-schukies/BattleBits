import java.util.Random;

public class Nand extends Logikgatter
{
	Random r = new Random();
	public Nand(){
		this.genAusgang();
	}
	//generiert einen Defaultausgang fuer das Gatter
	private boolean genAusgang(){
		return true;
		}
		
	//gibt true zurueck, wenn der vorgegebene Ausgang erreicht wurde
	public boolean pruefeAusgang(boolean eingangszustand1, boolean eingangszustand2){
		if ( eingangszustand1 & eingangszustand2 != super.getAusgang() ){
			return false;
		} else
		{
			return true;
		}
	}
	
}

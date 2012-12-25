import java.util.Random;

public class Or extends Logikgatter
{
	Random r = new Random();
	public Or(){
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
	public String toString(){
		return "Or";
	}
}

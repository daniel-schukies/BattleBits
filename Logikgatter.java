
public class Logikgatter {
	
	private boolean ausgang; 
	private boolean isAktiv;
	private boolean isGueltig;
	
	public Logikgatter(){
		
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


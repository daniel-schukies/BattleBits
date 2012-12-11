
public class Logikgatter {
	
	//Array, um die Eingaenge des Logikgatters zu speichern
	boolean[] eingang = new boolean[2];
	
		//superclass für nand/and nor/or
	public Logikgatter (int index1, boolean eingangszustand1, int index2, boolean eingangszustand2){
		
		setEingang(index1,eingangszustand1);
		setEingang(index2,eingangszustand2);
		
	}
	
	
	//setzt einen Eingang auf true oder false, um ein Logikgatter zu erstellen
	public boolean setEingang(int index, boolean eingangszustand){
		
		if ( index == 0 || index == 1 ){
		
		eingang[index] = eingangszustand;
		return true;
		} else 
		{
			return false;
		}
	}
}


import java.util.Random;

public class Logikgattergenerator {
	
	private Logikgatter generiertesLogikgatter;   //variable zum Speichern der generierten Logikgatter
	
	public Logikgattergenerator(){
		this.generate();
	}
	
	//generiert ein Logikgatter und speicher es in der Variable generiertesLogikgatter
	public void generate(){
		Random r = new Random();
		int zufall=r.nextInt(4); //generiert eine Zufallszahl zwischen 0 und 4
		System.out.println (zufall);
		switch (zufall){
			case 0: this.generiertesLogikgatter = new And (1,false,2,false);
			break;
			case 1: this.generiertesLogikgatter = new Nand (1,false,2,false);
			break;
			case 2: this.generiertesLogikgatter = new Or (1,false,2,false);
			break;
			case 3: this.generiertesLogikgatter = new Nor (1,false,2,false);
			break;
		}
	}
	//Logikgatter kann hier nicht eindutig identifiziert werden
	//evtl. toString(): name:string in die einzelnen Gatter einbauen!
	public Logikgatter getLogikgatter(){
			return generiertesLogikgatter;
	}
	
}


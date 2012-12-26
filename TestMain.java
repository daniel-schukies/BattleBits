public class TestMain {
	
	public static void main (String args[]) {
		Logikgatter l = new Logikgatter(1,false,2,false);
		Logikgattergenerator g = new Logikgattergenerator();
		l=g.getLogikgatter();
		l.setEingang(1,true);
		l.setEingang(2,true);
		boolean ausgang;
		ausgang=l.getAusgang();
	}
}


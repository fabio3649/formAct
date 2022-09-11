package controller.control;

public class prova {
	public static void main(String[] args) {
//		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String pos = System.getProperty("user.dir").toString();
		pos += "/src/main/webapp/view/pianoformativo/punteggiGenerazioni.txt";
		System.out.println(pos);
		String posizione = "";
		System.out.println(pos.length());
		for (int i = 0; i < pos.length(); i++) {
			Character c = posizione.charAt(i);
			if(c.equals('\\')) {
				c = '/';
			}
			posizione += c;
		}
		System.out.println(posizione);
	}
}

package gpx;

import java.io.IOException;

public class Trilha {

	public static void main(String[] args) {

		if (args.length != 3) {
			System.err.println("Par�metros inv�lidos");
			System.exit(0);
		}
		Segmento trilha = new Segmento(args[0]);
		try {
			trilha.RemovePontos(args[1], args[2]);
		} catch (IOException e) {
			System.err.println("N�o foi poss�vel localizar o arquivo.");
			e.printStackTrace();
		}
	}
}

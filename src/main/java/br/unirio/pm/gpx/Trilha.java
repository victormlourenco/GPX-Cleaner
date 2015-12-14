package br.unirio.pm.gpx;

public class Trilha {

	public static void main(String[] args) {

/*		if (args.length != 3) {
			System.err.println("Par�metros inv�lidos");
			System.exit(0);
		}*/

		Segmento trilha = new Segmento("foxboro.gpx");
		trilha.removePontos("0.1", "foxboro-.gpx");
	}

}

package gpx;

public class Trilha {

	public static void main(String[] args) {

		if (args.length != 3) {
			System.err.println("Parâmetros inválidos");
			System.exit(0);
		}

		Segmento trilha = new Segmento(args[1]);
		trilha.removerPontos(args[0], args[2]);
	}

}

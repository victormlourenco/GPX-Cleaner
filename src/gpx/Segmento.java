package gpx;

import java.util.List;

import ferramentas.Haversine;
import ferramentas.Serializador;

public class Segmento {

	private static List<Ponto> pontos;

	// Recebe um array de objetos do tipo Ponto do Serializador
	public Segmento(String ArquivoDeEntrada) {
		pontos = Serializador.deserializarPontos(ArquivoDeEntrada);
	}

	// Remove pontos de uma array de Objetos do tipo Ponto e salva com um nome
	// passado como parâmetro.
	public void removerPontos(String quantidade, String saida) {
		if (quantidade.endsWith("%")) {
			reduzirPercentual(Integer.parseInt(quantidade.replace("%", "")));
		} else {
			reduzirDistancias(Double.parseDouble(quantidade));
		}
		Serializador.escreverPontos(saida, pontos);
	}

	// Método responsável por remover Pontos através de um percentual.
	private void reduzirPercentual(int value) {
		value = (value * pontos.size()) / 100;
		for (int i = 0; i < value; i++) {
			recalcularDistancias();
			pontos.remove(calcularMenorDistancia());
		}
	}

	// Retorna a posição do Ponto que possui a menor distância
	private int calcularMenorDistancia() {
		double menor = pontos.get(0).getDistancia();
		int posicaomenor = 0;
		for (int i = 1; i < pontos.size() - 1; i++) {
			if (pontos.get(i).getDistancia() < menor) {
				posicaomenor = i;
				menor = pontos.get(i).getDistancia();
			}
		}
		return posicaomenor;
	}

	// Reduz pontos com distancias menores a uma distancia passada como
	// parâmetro
	private void reduzirDistancias(double distancia) {
		for (int i = 1; i < pontos.size() - 1; i++) {
			if (pontos.get(i).getDistancia() <= distancia) {
				recalcularDistancias();
				// System.out.println(pontos.get(i).getDistancia());
				pontos.remove(i);
			}
		}
	}

	// Recalcula as distancias
	private void recalcularDistancias() {
		for (int i = 1; i < pontos.size() - 1; i++) {
			calcularDistancia(pontos.get(i - 1), pontos.get(i), pontos.get(i + 1));
		}
	}

	// Calcula a distância d entre pontos
	private void calcularDistancia(Ponto pontoA, Ponto pontoB, Ponto pontoC) {
		double x1 = pontoA.getLongitude();
		double y1 = pontoA.getLatitude();
		double x2 = pontoB.getLongitude();
		double y2 = pontoB.getLatitude();
		double x3 = pontoC.getLongitude();
		double y3 = pontoC.getLatitude();
		double a = (y1 - y3) / (x1 - x3);
		double b = y3 - a * x3;
		double b_ = y2 + (x2 / a);
		double x4 = (b_ - b) / (a + (1 / a));
		double y4 = ((a * (b_ - b)) / (a + (1 / a))) + b;
		Ponto PontoD = new Ponto(y4, x4);
		pontoB.setDistancia(Haversine.haversine(PontoD.getLatitude(), PontoD.getLongitude(), pontoB.getLatitude(),
				pontoB.getLongitude()));
	}

}

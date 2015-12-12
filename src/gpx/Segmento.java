package gpx;

import java.util.List;

import ferramentas.*;

public class Segmento {
	
	private static List<Ponto> pontos;

	// Recebe um array de objetos do tipo Ponto do Serializador
	public Segmento(String ArquivoDeEntrada) {
		pontos = Serializador.deserializarPontos(ArquivoDeEntrada);
	}

	// Remove pontos de uma array de Objetos do tipo Ponto e salva com um nome
	// passado como parâmetro.
	public void RemoverPontos(String quantidade, String saida) {
		if (quantidade.endsWith("%")) {
			ReduzirPercentual(quantidade);
		} else {
			ReduzirDistancia(quantidade);
		}
		Serializador.escreverPontos(saida, pontos);
	}

	// Método responsável por remover Pontos através de um percentual.
	private void ReduzirPercentual(String percentual) {
		int value = Integer.parseInt(percentual.replace("%", ""));
		value = (value * pontos.size()) / 100;
		for (int i = 1; i < pontos.size() - 1; i++) {
			CalcularDistancia(pontos.get(i - 1), pontos.get(i), pontos.get(i + 1));
		}
		for (int i = 0; i < value; i++) {
			pontos.remove(CalcularMenorDistancia());
		}
	}

	// Método responsável por remover Pontos através de uma distância d dada
	private void ReduzirDistancia(String distancia) {
		double value = Double.parseDouble(distancia);
		value = (value * pontos.size()) / 100;
		for (int i = 1; i < pontos.size() - 1; i++) {
			CalcularDistancia(pontos.get(i - 1), pontos.get(i), pontos.get(i + 1));
			if (pontos.get(i).getDistancia() <= value / 100) {
				pontos.remove(i);
			}
		}
	}

	// Retorna a posição do Ponto que possui a menor distância
	private int CalcularMenorDistancia() {
		double smallest = pontos.get(0).getDistancia();
		int indexmenor = 0;
		for (int i = 1; i < pontos.size() - 1; i++) {
			if (pontos.get(i).getDistancia() < smallest) {
				indexmenor = i;
				smallest = pontos.get(i).getDistancia();
			}
		}
		return indexmenor;
	}

	// Calcula a distância d entre pontos
	private void CalcularDistancia(Ponto pontoA, Ponto pontoB, Ponto pontoC) {
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

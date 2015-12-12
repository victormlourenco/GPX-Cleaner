package gpx;

import java.util.List;

import ferramentas.*;

public class Segmento {
	
	private static List<Ponto> Pontos;

	// Recebe um array de objetos do tipo Ponto do Serializador
	public Segmento(String ArquivoDeEntrada) {
		Pontos = Serializer.SerializePoints(ArquivoDeEntrada);
	}

	// Remove pontos de uma array de Objetos do tipo Ponto e salva com um nome
	// passado como parâmetro.
	public void RemovePontos(String quantidade, String saida) {
		if (quantidade.endsWith("%")) {
			ReduzPercentual(quantidade);
		} else {
			ReduzDistancia(quantidade);
		}
		Serializer.WritePoints(saida, Pontos);
	}

	// Método responsável por remover Pontos através de um percentual.
	private void ReduzPercentual(String percentual) {
		int value = Integer.parseInt(percentual.replace("%", ""));
		value = (value * Pontos.size()) / 100;
		for (int i = 1; i < Pontos.size() - 1; i++) {
			CalculaDistancia(Pontos.get(i - 1), Pontos.get(i), Pontos.get(i + 1));
		}
		for (int i = 0; i < value; i++) {
			Pontos.remove(MenorDistancia());
		}
	}

	// Método responsável por remover Pontos através de uma distância d dada
	private void ReduzDistancia(String distancia) {
		double value = Double.parseDouble(distancia);
		value = (value * Pontos.size()) / 100;
		for (int i = 1; i < Pontos.size() - 1; i++) {
			CalculaDistancia(Pontos.get(i - 1), Pontos.get(i), Pontos.get(i + 1));
			if (Pontos.get(i).getDistancia() <= value / 100) {
				Pontos.remove(i);
			}
		}
	}

	// Retorna a posição do Ponto que possui a menor distância
	private int MenorDistancia() {
		double smallest = Pontos.get(0).getDistancia();
		int indexmenor = 0;
		for (int i = 1; i < Pontos.size() - 1; i++) {
			if (Pontos.get(i).getDistancia() < smallest) {
				indexmenor = i;
				smallest = Pontos.get(i).getDistancia();
			}
		}
		return indexmenor;
	}

	// Calcula a distância d entre pontos
	private void CalculaDistancia(Ponto PontoA, Ponto PontoB, Ponto PontoC) {
		double x1 = PontoA.getLongitude();
		double y1 = PontoA.getLatitude();
		double x2 = PontoB.getLongitude();
		double y2 = PontoB.getLatitude();
		double x3 = PontoC.getLongitude();
		double y3 = PontoC.getLatitude();
		double a = (y1 - y3) / (x1 - x3);
		double b = y3 - a * x3;
		double b_ = y2 + (x2 / a);
		double x4 = (b_ - b) / (a + (1 / a));
		double y4 = ((a * (b_ - b)) / (a + (1 / a))) + b;
		Ponto PontoD = new Ponto(y4, x4);
		PontoB.setDistancia(Haversine.haversine(PontoD.getLatitude(), PontoD.getLongitude(), PontoB.getLatitude(),
				PontoB.getLongitude()));
	}
	
}

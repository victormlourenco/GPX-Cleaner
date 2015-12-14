package gpx;

import java.util.List;
import ferramentas.Operacoes;
import ferramentas.Serializador;

public class Segmento {

	private static List<Ponto> pontos;

	// Recebe um array de objetos do tipo Ponto do Serializador
	public Segmento(String ArquivoDeEntrada) {
		pontos = Serializador.deserializaPontos(ArquivoDeEntrada);
	}

	// Remove pontos de uma array de Objetos do tipo Ponto e salva com um nome
	// passado como par�metro.
	public void removePontos(String quantidade, String saida) {
		if (quantidade.endsWith("%")) {
			reduzPercentual(Integer.parseInt(quantidade.replace("%", "")));
		} else {
			reduzDistancias(Double.parseDouble(quantidade));
		}
		Serializador.escrevePontos(saida, pontos);
	}

	// M�todo respons�vel por remover Pontos atrav�s de um percentual.
	private void reduzPercentual(int value) {
		value = (value * pontos.size()) / 100;
		for (int i = 0; i < value; i++) {
			recalculaDistancias();
			pontos.remove(calculaMenorDistancia());
		}
	}

	// Retorna a posi��o do Ponto que possui a menor dist�ncia
	private int calculaMenorDistancia() {
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
	// par�metro
	private void reduzDistancias(double distancia) {
		for (int i = 1; i < pontos.size() - 1; i++) {
			if (Operacoes.calculaHaversine(pontos.get(i), pontos.get(i + 1)) <= distancia
					|| pontos.get(i + 1).getDistancia() <= distancia) {
				recalculaDistancias();
				pontos.remove(i + 1);
			}
		}
	}

	// Recalcula as distancias
	private void recalculaDistancias() {
		for (int i = 1; i < pontos.size() - 1; i++) {
			Operacoes.calculaDistancia(pontos.get(i - 1), pontos.get(i), pontos.get(i + 1));
		}
	}
	
}

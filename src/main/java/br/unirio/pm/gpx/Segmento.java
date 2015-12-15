package br.unirio.pm.gpx;

import java.util.List;

import br.unirio.pm.ferramentas.Operacoes;
import br.unirio.pm.ferramentas.Serializador;

public class Segmento {

	private List<Ponto> pontos;

	// Recebe um array de objetos do tipo Ponto do Serializador
	public Segmento(String ArquivoDeEntrada) {
		pontos = Serializador.deserializaPontos(ArquivoDeEntrada);
		recalculaDistancias();
	}

	// Remove pontos de uma array de Objetos do tipo Ponto e salva com um nome
	// passado como parâmetro.
	public void removePontos(String quantidade, String saida) {
		if (quantidade.endsWith("%")) {
			reduzPercentual(Integer.parseInt(quantidade.replace("%", "")));
		} else {
			reduzDistancias(Double.parseDouble(quantidade));
		}
		Serializador.escrevePontos(saida, pontos);
	}

	// Método responsável por remover Pontos atravï¿½s de um percentual.
	public void reduzPercentual(int value) {
		value = (value * pontos.size()) / 100;
		for (int i = 0; i < value; i++) {
			recalculaDistancias();
			pontos.remove(calculaMenorDistancia());
		}
	}

	// Retorna a Posição do Ponto que possui a menor distância
	private int calculaMenorDistancia() {
		double menor = pontos.get(0).getDistancia();
		int posicaomenor = 0;
		for (int i = 0; i < pontos.size(); i++) {
			if (pontos.get(i).getDistancia() < menor) {
				posicaomenor = i;
				menor = pontos.get(i).getDistancia();
			}
		}
		return posicaomenor;
	}

	// Reduz pontos com distancias menores a uma distancia passada como
	// parâmetro
	public void reduzDistancias(double distancia) {
		for (int i = 0; i < pontos.size() - 1; i++) {
			for (int j = i + 1; j < pontos.size() - 1; ++j) {
				if (Math.abs(pontos.get(i).getDistanciaprox() + pontos.get(j).getDistanciaprox()) <= 2 * distancia) {
					pontos.remove(j);
				}
			}
		}
	}

	// Recalcula as distâncias
	private void recalculaDistancias() {
		for (int i = 1; i < pontos.size() - 1; i++) {
			pontos.get(i).setDistancia(Operacoes.calculaDistancia(pontos.get(i - 1), pontos.get(i), pontos.get(i + 1)));
			pontos.get(i).setDistanciaprox(Operacoes.calculaHaversine(pontos.get(i - 1), pontos.get(i)));
		}
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

}

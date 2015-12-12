package gpx;

import java.io.IOException;
import java.util.List;
import Ferramentas.*;

public class Segmento {
	private static List<Ponto> Pontos;

	public void RemovePontos(Ponto[] pontos, int tam) {
	}

	public Segmento(String ArquivoDeEntrada) {
		Pontos = Serializer.SerializePoints(ArquivoDeEntrada);
	}

	public void RemovePontos(String Quant, String ArquivoDeSaida) throws IOException {
		if (Quant.endsWith("%")) {
			ReduzPercentual(Quant);
		} else {
			ReduzDistancia(Quant);
		}

		Serializer.WritePoints(ArquivoDeSaida, Pontos);
	}

	private void ReduzPercentual(String Perc) {
		int value = Integer.parseInt(Perc.replace("%", ""));
		value = (value * Pontos.size()) / 100;
		for (int i = 1; i < Pontos.size() - 1; i++) {
			CalculaD(Pontos.get(i - 1), Pontos.get(i), Pontos.get(i + 1));
		}
		for (int i = 0; i < value; i++) {
			Pontos.remove(RemoveMenorD());
		}
	}
	
	private void ReduzDistancia(String Distancia) {
		double value = Double.parseDouble(Distancia);
		value = (value * Pontos.size()) / 100;
		for (int i = 1; i < Pontos.size() - 1; i++) {
			CalculaD(Pontos.get(i - 1), Pontos.get(i), Pontos.get(i + 1));
			
			if (Math.round(Pontos.get(i).getDistancia()) <= value) {
				System.out.println(Pontos.get(i).getDistancia());
				Pontos.remove(i);
			}
		}
	}

	private int RemoveMenorD() {
		double smallest = Pontos.get(0).getDistancia();
		int indexmenor = 0;

		for (int i = 1; i < Pontos.size() - 1; i++) {
			if (Pontos.get(i).getDistancia() < smallest) {
				indexmenor = i;
				smallest = Pontos.get(i).getDistancia();
			}
		}

		// System.out.println(Pontos.get(indexmenor).getFrame() + " " +
		// Pontos.get(indexmenor).getDistancia() + " " + Pontos.size() + " " +
		// indexmenor);
		// System.out.printf("Value: %.9f\n",
		// Pontos.get(indexmenor).getDistancia());
		return indexmenor;
	}

	private void CalculaD(Ponto PontoA, Ponto PontoB, Ponto PontoC) {
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

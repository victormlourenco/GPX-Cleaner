package br.unirio.pm.ferramentas;

import br.unirio.pm.gpx.Ponto;

public class Operacoes {
	public static final double R = 6372.8;

	public static double calculaHaversine(Ponto ponto1, Ponto ponto2) {
		double dLat = Math.toRadians(ponto2.getLatitude() - ponto1.getLatitude());
		double dLon = Math.toRadians(ponto2.getLongitude() - ponto1.getLongitude());
		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2)
				* Math.cos(Math.toRadians(ponto1.getLatitude())) * Math.cos(ponto1.getLatitude());
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}

	// Calcula a distância d entre pontos
	public static double calculaDistancia(Ponto pontoA, Ponto pontoB, Ponto pontoC) {
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
		Ponto pontoD = new Ponto(y4, x4);
		return calculaHaversine(pontoD, pontoB);
	}

}

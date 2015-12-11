package Ferramentas;

public class Triangulo {

	private static double getArea(double a, double b, double c) {
		double s = (a + b + c) / 2.0d;
		double x = (s * (s - a) * (s - b) * (s - c));
		return Math.sqrt(x);
	}
	
	public static double getAltura(double a, double b, double c) {
		return (2 * getArea(a, b, c)/c);
	}

}

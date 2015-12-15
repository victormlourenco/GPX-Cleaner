package br.unirio.pm.gpx;

import br.unirio.pm.ferramentas.Serializador;

public class Ponto {
	private double latitude;
	private double longitude;
	private Object frame;
	private double distancia;
	private double distanciaprox;

	// Constr�i um objeto do tipo Ponto atrav�s de um objeto do tipo frame
	public Ponto(Object frame) {
		super();
		this.latitude = Serializador.getLat(frame);
		this.longitude = Serializador.getLong(frame);
		this.frame = frame;
		this.distancia = Double.MAX_VALUE;
		this.distanciaprox = Double.MAX_VALUE;
	}

	// Construtor default
	public Ponto() {
		super();
		this.latitude = 0;
		this.longitude = 0;
		this.frame = null;
		this.distancia = Double.MAX_VALUE;
		this.distanciaprox = Double.MAX_VALUE;
	}

	// Constr�i um objeto do tipo ponto a partir de um y,x
	public Ponto(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.frame = null;
		this.distancia = Double.MAX_VALUE;
		this.distanciaprox = Double.MAX_VALUE;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public Object getFrame() {
		return frame;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public double getDistanciaprox() {
		return distanciaprox;
	}

	public void setDistanciaprox(double distanciaprox) {
		this.distanciaprox = distanciaprox;
	}

}

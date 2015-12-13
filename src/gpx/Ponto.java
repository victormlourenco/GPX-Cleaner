package gpx;

import ferramentas.Serializador;

public class Ponto {
	private double latitude;
	private double longitude;
	private Object frame;
	private double distancia;
	private double distanciaProx;

	public double getDistanciaProx() {
		return distanciaProx;
	}

	public void setDistanciaProx(double distanciaProx) {
		this.distanciaProx = distanciaProx;
	}

	// Constrói um objeto do tipo Ponto através de um objeto do tipo frame
	public Ponto(Object frame) {
		super();
		this.latitude = Serializador.getLat(frame);
		this.longitude = Serializador.getLong(frame);
		this.frame = frame;
	}

	// Construtor default
	public Ponto() {
		super();
		this.latitude = 0;
		this.longitude = 0;
		this.frame = null;
		this.distancia = Double.MAX_VALUE;
		this.distanciaProx = Double.MAX_VALUE;
	}

	// Constrói um a partir de um y,x
	public Ponto(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.frame = null;
		this.distancia = Double.MAX_VALUE;
		this.distanciaProx = Double.MAX_VALUE;
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

}

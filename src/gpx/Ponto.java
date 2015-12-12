package gpx;

import ferramentas.Serializer;

public class Ponto {
	private double Latitude;
	private double Longitude;
	private Object Frame;
	private double Distancia;
	
	//Constrói um objeto do tipo Ponto através de um objeto do tipo frame
	public Ponto(Object frame) {
		super();
		Latitude = Serializer.GetLat(frame);
		Longitude = Serializer.GetLong(frame);
		Frame = frame;
	}
	
	//Construtor default
	public Ponto() {
		super();
		Latitude = 0;
		Longitude = 0;
		Frame = null;
		Distancia = Double.MAX_VALUE;
	}
	
	//Constrói um a partir de um y,x
	public Ponto(double latitude, double longitude) {
		super();
		Latitude = latitude;
		Longitude = longitude;
		Frame = null;
		Distancia = Double.MAX_VALUE;
	}
	
	public double getLatitude() {
		return Latitude;
	}
	
	public double getLongitude() {
		return Longitude;
	}

	public Object getFrame() {
		return Frame;
	}
	
	public double getDistancia() {
		return Distancia;
	}

	public void setDistancia(double distancia) {
		Distancia = distancia;
	}
	
	
}

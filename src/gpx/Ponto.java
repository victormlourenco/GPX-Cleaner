package gpx;

import Ferramentas.Serializer;

public class Ponto {
	private double Latitude;
	private double Longitude;
	private String Time;
	private Object Frame;
	private double Distancia;
	
	public Ponto(Object frame) {
		super();
		Latitude = Serializer.GetLat(frame);
		Longitude = Serializer.GetLong(frame);
		Time = Serializer.GetTime(frame);
		Frame = frame;
	}
	
	public Ponto() {
		super();
		Latitude = 0;
		Longitude = 0;
		Time = null;
		Frame = null;
		Distancia = Double.MAX_VALUE;
	}
	
	public Ponto(double latitude, double longitude) {
		super();
		Latitude = latitude;
		Longitude = longitude;
		Time = null;
		Frame = null;
		Distancia = Double.MAX_VALUE;
	}
	
	public double getLatitude() {
		return Latitude;
	}
	
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public Object getFrame() {
		return Frame;
	}
	public void setFrame(String frame) {
		Frame = frame;
	}
	
	public double getDistancia() {
		return Distancia;
	}

	public void setDistancia(double distancia) {
		Distancia = distancia;
	}
	
	
}

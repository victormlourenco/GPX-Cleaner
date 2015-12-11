package gpx;

import Ferramentas.Serializer;

public class TrackPoint {
	private double Latitude;
	private double Longitude;
	private String Time;
	private Object Frame;
	
	public TrackPoint(Object frame) {
		super();
		Latitude = Serializer.GetLat(frame);
		Longitude = Serializer.GetLong(frame);
		Time = Serializer.GetTime(frame);
		Frame = Serializer.GetTrack(Latitude, Longitude, Time);
	}
	
	public double getLatitude() {
		return Latitude;
	}
	
	public void setLatitude(double latitude) {
		Latitude = latitude;
		AtualizarFrame();
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
		AtualizarFrame();
	}
	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
		AtualizarFrame();
	}

	public Object getFrame() {
		return Frame;
	}
	public void setFrame(Object frame) {
		Frame = frame;
	}
	
	public void AtualizarFrame () {	
		Frame = Serializer.GetTrack(Latitude, Longitude, Time); 
	}
	
	
}

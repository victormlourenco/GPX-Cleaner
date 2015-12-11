package Ferramentas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.fmt.gps.data.GpxFileDataAccess;
import com.fmt.gps.track.*;

public class Serializer {

	public static final String SerializePoints = null;

	public static List<TrackPoint> SerializePoints(String filename) {
		return GpxFileDataAccess.getPoints(new File(filename));
	}

	public static void WritePoints(String filename, List<TrackPoint> points) {
		final Trip trip = Trip.makeTrip(-1, new TrackSegment(points, TrackSegment.caminarType.walk));
		PrintWriter out = null;
		try {
			out = new PrintWriter(filename);
		} catch (FileNotFoundException e1) {
			System.out.print("Erro: Não foi possível gravar o arquivo.");
		}
		out.print(GpxFileDataAccess.makeGpxXml(trip, false));
	}
	
	public static double GetLat(Object obj) {
		return Double.parseDouble(obj.toString().substring(obj.toString().indexOf(" "), obj.toString().indexOf(" lon:")).replace(',', '.'));
	}
	
	public static double GetLong(Object obj) {
		return Double.parseDouble(obj.toString().substring(obj.toString().lastIndexOf("lon: "), obj.toString().indexOf(" time")).replace(',', '.').replace("lon: ", ""));
	}
	
	public static String GetTime(Object obj) {
		return obj.toString().substring(obj.toString().lastIndexOf(": "), obj.toString().length()).replace(": ", "");
	}
	
	public static Object GetTrack(double lat, double lon, String time) {
		String out = "";
		out = out.concat("lat: " + lat + " lon: " + lon + " time: "+ time).replace('.', ',');
		return out;
	}
	
	

}

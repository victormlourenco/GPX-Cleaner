package Ferramentas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fmt.gps.data.GpxFileDataAccess;
import com.fmt.gps.track.*;

import gpx.Ponto;

public class Serializer {

	public static final String SerializePoints = null;

	public static List<Ponto> SerializePoints(String filename) {
		List<TrackPoint> frames = (GpxFileDataAccess.getPoints(new File(filename)));
		List<Ponto> Points = new ArrayList<Ponto>();
		for (int i = 0; i < frames.size(); i++) {
			Points.add(new Ponto(frames.get(i)));
		}
		return Points;
	}

	public static void WritePoints(String filename, List<Ponto> points) throws IOException {
		List<TrackPoint> frames = new ArrayList<TrackPoint>();
		for (int i = 0; i < points.size(); i++) {
			frames.add((TrackPoint) points.get(i).getFrame());
			//System.out.println(points.get(i).getFrame());
		}
		Trip trip = Trip.makeTrip(-1, new TrackSegment(frames, TrackSegment.caminarType.speedChange));
		PrintWriter out = null;
		try {
			out = new PrintWriter(filename);
		} catch (FileNotFoundException e1) {
			System.out.print("Erro: N�o foi poss�vel gravar o arquivo.");
		}
		String output = GpxFileDataAccess.makeGpxXml(trip, false);
        BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(filename)));
        bwr.write(output);
        bwr.flush();
        bwr.close();
	}

	public static double GetLat(Object obj) {
		return Double.parseDouble(obj.toString().substring(obj.toString().indexOf(" "), obj.toString().indexOf(" lon:"))
				.replace(',', '.'));
	}

	public static double GetLong(Object obj) {
		return Double.parseDouble(
				obj.toString().substring(obj.toString().lastIndexOf("lon: "), obj.toString().indexOf(" time"))
						.replace(',', '.').replace("lon: ", ""));
	}

	public static String GetTime(Object obj) {
		return obj.toString().substring(obj.toString().lastIndexOf(": "), obj.toString().length()).replace(": ", "");
	}
}

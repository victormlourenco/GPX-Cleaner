package br.unirio.pm.ferramentas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fmt.gps.data.GpxFileDataAccess;
import com.fmt.gps.track.TrackPoint;
import com.fmt.gps.track.TrackSegment;
import com.fmt.gps.track.Trip;

import br.unirio.pm.gpx.Ponto;

public class Serializador {

	// Recupera pontos de um arquivo .gpx
	public static List<Ponto> deserializaPontos(String filename) {
		List<Ponto> Points = new ArrayList<Ponto>();
		List<TrackPoint> frames = (GpxFileDataAccess.getPoints(new File(filename)));
		for (int i = 0; i < frames.size(); i++) {
			Points.add(new Ponto(frames.get(i)));
		}
		return Points;
	}

	// Escreve uma lista de Pontos num arquivo .gpx
	public static void escrevePontos(String filename, List<Ponto> points) {
		List<TrackPoint> frames = new ArrayList<TrackPoint>();
		for (int i = 0; i < points.size(); i++) {
			frames.add((TrackPoint) points.get(i).getFrame());
		}
		Trip trip = Trip.makeTrip(-1, new TrackSegment(frames, TrackSegment.caminarType.speedChange));
		String output = GpxFileDataAccess.makeGpxXml(trip, false);
		try {
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(filename)));
			bwr.write(output);
			bwr.flush();
			bwr.close();
		} catch (IOException e) {
			System.err.println("Não foi possível salvar o arquivo.");
			System.exit(0);
		}
	}

	// Recupera a latiditude de um frame
	public static double getLat(Object obj) {
		return Double.parseDouble(obj.toString().substring(obj.toString().indexOf(" "), obj.toString().indexOf(" lon:"))
				.replace(',', '.'));
	}

	// Recupera a longitude de um frame
	public static double getLong(Object obj) {
		return Double.parseDouble(
				obj.toString().substring(obj.toString().lastIndexOf("lon: "), obj.toString().indexOf(" time"))
						.replace(',', '.').replace("lon: ", ""));
	}

}

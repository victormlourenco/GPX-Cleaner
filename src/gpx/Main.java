package gpx;

import java.util.List;
import com.fmt.gps.track.TrackPoint;

import Ferramentas.Haversine;
import Ferramentas.Serializer;

public class Main {
	
	public static void main(String[] args) {
		
		if(args.length != 3) {
			System.err.println("Parâmetros inválidos.");
		}
		
		gpx.TrackPoint[] pontos = Serializer.SerializePoints(args[2]);
		
		if (args[0].endsWith("%")) {
			int percentual = Integer.parseInt(args[0].substring(0, args[0].length()-1));
			//ReduzPercentual(percentual, args[2], pontos)
		} else {
			double distancia = Double.parseDouble(args[0]);
			//ReduzDistancia(distancia, args[2], pontos)
		}
		
	}

}

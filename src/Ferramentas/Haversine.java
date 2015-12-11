package Ferramentas;

import gpx.TrackPoint;

public class Haversine {
	public static Double calcularDistancia(TrackPoint Point1, TrackPoint Point2) {
		
		Double latitudeOne = Point1.getLatitude();
		Double longitudeOne = Point1.getLongitude(); 
		Double latitudeTwo = Point2.getLatitude(); 
		Double longitudeTwo = Point2.getLongitude();
		
        if (latitudeOne == null || latitudeTwo == null || longitudeOne == null || longitudeTwo == null) {
            return null;
        }

        Double earthRadius = 6371.0;
        Double diffBetweenLatitudeRadians = Math.toRadians(latitudeTwo - latitudeOne);
        Double diffBetweenLongitudeRadians = Math.toRadians(longitudeTwo - longitudeOne);
        Double latitudeOneInRadians = Math.toRadians(latitudeOne);
        Double latitudeTwoInRadians = Math.toRadians(latitudeTwo);
        Double a = Math.sin(diffBetweenLatitudeRadians / 2) * Math.sin(diffBetweenLatitudeRadians / 2) + Math.cos(latitudeOneInRadians) * Math.cos(latitudeTwoInRadians) * Math.sin(diffBetweenLongitudeRadians / 2)
                * Math.sin(diffBetweenLongitudeRadians / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (earthRadius * c);
    }
}

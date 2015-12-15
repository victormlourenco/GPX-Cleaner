package test.br.unirio.pm.gpx;

import java.util.List;

import org.junit.Test;

import br.unirio.pm.gpx.Ponto;
import br.unirio.pm.gpx.Segmento;
import junit.framework.TestCase;

public class TestGpxCleaner extends TestCase {

	@Test
	public void testPercentual50p() {
		Segmento segmento50p = new Segmento("Century-2007-02-18.gpx");
		segmento50p.reduzPercentual(50);
		assertEquals(segmento50p.getPontos().size(), 1121);
	}

	@Test
	public void testPercentual60p() {
		Segmento segmento60p = new Segmento("Century-2007-02-18.gpx");
		segmento60p.reduzPercentual(60);
		assertEquals(897, segmento60p.getPontos().size());
	}

	@Test
	public void testPercentual90p() {
		Segmento segmento90p = new Segmento("Century-2007-02-18.gpx");
		segmento90p.reduzPercentual(90);
		assertEquals(225, segmento90p.getPontos().size());
	}

	@Test
	public void testPercentual95p() {
		Segmento segmento95p = new Segmento("Century-2007-02-18.gpx");
		segmento95p.reduzPercentual(95);
		assertEquals(113, segmento95p.getPontos().size());
	}

	@Test
	public void testPercentual100p() {
		Segmento segmento100p = new Segmento("Century-2007-02-18.gpx");
		segmento100p.reduzPercentual(100);
		assertEquals(0, segmento100p.getPontos().size());
	}

	@Test
	public void testDistancia100m() {
		Segmento segmento = new Segmento("foxboro.gpx");
		double distancia = 0.1;
		segmento.reduzDistancias(distancia);
		List<Ponto> pontos = segmento.getPontos();
		for (int i = 0; i < pontos.size() - 1; i++) {
			assertFalse(pontos.get(i).getDistanciaprox() + pontos.get(i + 1).getDistanciaprox() <= 2 * distancia);
		}
	}

	@Test
	public void testDistancia10m() {
		Segmento segmento = new Segmento("foxboro.gpx");
		double distancia = 0.01;
		segmento.reduzDistancias(distancia);
		List<Ponto> pontos = segmento.getPontos();
		for (int i = 0; i < pontos.size() - 1; i++) {
			assertFalse(pontos.get(i).getDistanciaprox() + pontos.get(i + 1).getDistanciaprox() <= 2 * distancia);
		}
	}

	@Test
	public void testDistancia1m() {
		Segmento segmento = new Segmento("foxboro.gpx");
		double distancia = 0.001;
		segmento.reduzDistancias(distancia);
		List<Ponto> pontos = segmento.getPontos();
		for (int i = 0; i < pontos.size() - 1; i++) {
			assertFalse(pontos.get(i).getDistanciaprox() + pontos.get(i + 1).getDistanciaprox() <= 2 * distancia);
		}
	}

}

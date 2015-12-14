package test.br.unirio.pm.gpx;

import junit.framework.TestCase;

import org.junit.Test;

import br.unirio.pm.gpx.Segmento;

public class TestGpxCleaner extends TestCase {
	
	@Test
	public void testPercentual50p()
	{
		Segmento segmento50p = new Segmento("century.gpx");
		segmento50p.reduzPercentual(50);
		assertEquals(Segmento.getPontos().size(), 1121);
	}
	
	@Test
	public void testPercentual60p()
	{
		Segmento segmento60p = new Segmento("century.gpx");
		segmento60p.reduzPercentual(60);
		assertEquals(897, Segmento.getPontos().size());
	}
	
	@Test
	public void testPercentual90p()
	{
		Segmento segmento90p = new Segmento("century.gpx");
		segmento90p.reduzPercentual(90);
		assertEquals(225, Segmento.getPontos().size());
	}
	
	@Test
	public void testPercentual95p()
	{
		Segmento segmento95p = new Segmento("century.gpx");
		segmento95p.reduzPercentual(95);
		assertEquals(113, Segmento.getPontos().size());
	}
	
	@Test
	public void testPercentual100p()
	{
		Segmento segmento100p = new Segmento("century.gpx");
		segmento100p.reduzPercentual(100);
		assertEquals(0, Segmento.getPontos().size());
	}

}

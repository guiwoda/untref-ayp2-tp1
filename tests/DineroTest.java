import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DineroTest extends TrabajoPracticoTest<Dinero> {
	private Dinero pesos;
	private Dinero dolares;
	
	@Before
	public void setUp() throws Exception {
		pesos = new Dinero(Moneda.PESO, 10);
		dolares = new Dinero(Moneda.DOLAR, 5);
	}
	
	@Test(expected=Exception.class)
	public void necesitaUnaMoneda() throws Exception {
		new Dinero(null);
	}
	
	@Test
	public void puedoSumarloConOtroDinero() throws Exception {
		assertEquals(new Dinero(Moneda.PESO, 20), pesos.sumar(pesos));
	}
	
	@Test(expected=Exception.class)
	public void noPuedoSumarloConOtroDineroDeDiferenteMoneda() throws Exception {
		pesos.sumar(dolares);
	}
	
	@Test
	public void puedoRestarloConOtroDinero() throws Exception {
		assertEquals(new Dinero(Moneda.PESO, 5), pesos.restar(new Dinero(Moneda.PESO, 5)));
	}
	
	@Test(expected=Exception.class)
	public void noPuedoRestarloConOtroDineroDeDiferenteMoneda() throws Exception {
		pesos.restar(dolares);
	}

	@Test
	public void puedoSumarleUnaCantidadEntera() throws Exception {
		assertEquals(new Dinero(Moneda.PESO, 21), pesos.sumar(11));
	}
	
	@Test
	public void puedoRestarleUnaCantidadEntera() throws Exception {
		assertEquals(new Dinero(Moneda.PESO, 9), pesos.restar(1));
	}
	
	@Test
	public void puedoSumarleUnaFraccion() throws Exception {
		assertEquals(1050, pesos.sumar(0.5).getCentavos());
	}
	
	@Test
	public void puedoRestarleUnaFraccion() throws Exception {
		assertEquals(950, pesos.restar(0.5).getCentavos());
	}
	
	@Test
	public void puedoInvertirSuValor() throws Exception {
		assertEquals(-1000, pesos.invertir().getCentavos());
	}
	
	@Test
	public void puedeDecirmeSiEsPositivo() throws Exception {
		assertTrue(pesos.isPositivo());
		assertFalse(pesos.invertir().isPositivo());
	}
	
	@Test
	public void puedeDecirmeSiEsNegativo() throws Exception {
		assertFalse(pesos.isNegativo());
		assertTrue(pesos.invertir().isNegativo());
	}

	@Test
	public void puedeDistribuirseEnPartesSinPerderCentavos() throws Exception {
		int[] partes = { 50, 50 };

		Dinero[] resultado = pesos.distribuir(partes);

		assertEquals(pesos, resultado[0].sumar(resultado[1]));
	}
	
	@Test
	public void puedeDividirse() throws Exception {
		Dinero result = pesos.dividir(2);
		
		assertEquals(500, result.getCentavos());
	}
	
	@Test
	public void puedeMultiplicarse() throws Exception {
		Dinero result = pesos.multiplicar(2);
		
		assertEquals(2000, result.getCentavos());
	}
	
	@Test
	public void puedeConvertirseAOtraMoneda() throws Exception {
		Dinero dinero = new Dinero(Moneda.DOLAR, 100);
		Dinero pesos = dinero.convertir(Moneda.PESO, 14.5);
		
		assertEquals(new Dinero(Moneda.PESO, 1450), pesos);
	}
	
	@Test
	public void implementaComparable() throws Exception {
		Dinero dinero = new Dinero(Moneda.DOLAR, 100);
		Dinero menor = new Dinero(Moneda.DOLAR, 50);
		Dinero mayor = new Dinero(Moneda.DOLAR, 150);
		Dinero igual = new Dinero(Moneda.DOLAR, 100);
		
		assertTrue(dinero.compareTo(menor) > 0);
		assertTrue(dinero.compareTo(mayor) < 0);
		assertTrue(dinero.compareTo(igual) == 0);
	}
	
	@Test
	public void puedoSaberEnQueMonedaEsta() throws Exception {
		assertEquals(Moneda.PESO, pesos.getMoneda());
		assertEquals(Moneda.DOLAR, dolares.getMoneda());
	}

	@Override
	protected Dinero getObject() throws Exception {
		return new Dinero(Moneda.PESO, 10);
	}
}

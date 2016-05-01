import static org.junit.Assert.*;

import org.junit.Test;

public class DineroTest extends TrabajoPracticoTest<Dinero> {

	@Test
	public void puedoSumarloConOtroDinero() throws Exception {
		Dinero dinero = new Dinero(new Peso(), 10);

		assertEquals(new Dinero(new Peso(), 20), dinero.sumar(dinero));
	}

	@Test
	public void puedoSumarleUnaCantidadEntera() throws Exception {
		Dinero dinero = new Dinero(new Peso(), 10);

		assertEquals(new Dinero(new Peso(), 21), dinero.sumar(11));
	}

	@Test
	public void puedeDistribuirseEnPartesSinPerderCentavos() throws Exception {
		Dinero dinero = new Dinero(new Peso(), 9.99f);

		int[] partes = { 50, 50 };

		Dinero[] resultado = dinero.distribuir(partes);

		assertEquals(dinero, resultado[0].sumar(resultado[1]));
	}
	
	@Test
	public void puedeDividirse() throws Exception {
		Dinero dinero = new Dinero(new Peso(), 100);
		Dinero result = dinero.dividir(2);
		
		assertEquals(5000, result.getCentavos());
	}
	
	@Test
	public void puedeMultiplicarse() throws Exception {
		Dinero dinero = new Dinero(new Peso(), 50);
		Dinero result = dinero.multiplicar(2);
		
		assertEquals(10000, result.getCentavos());
	}
	
	@Test
	public void puedeConvertirseAOtraMoneda() throws Exception {
		Dinero dinero = new Dinero(Moneda.DOLAR, 100);
		Dinero pesos = dinero.convertir(Moneda.PESO, 14.5);
		
		assertEquals(new Dinero(Moneda.PESO, 1450), pesos);
	}
	
	@Test
	public void implementaComparable() throws Exception {
		Dinero dinero = new Dinero(new Dolar(), 100);
		Dinero menor = new Dinero(new Dolar(), 50);
		Dinero mayor = new Dinero(new Dolar(), 150);
		Dinero igual = new Dinero(new Dolar(), 100);
		
		assertTrue(dinero.compareTo(menor) > 0);
		assertTrue(dinero.compareTo(mayor) < 0);
		assertTrue(dinero.compareTo(igual) == 0);
	}

	@Override
	protected Dinero getObject() throws Exception {
		return new Dinero(Moneda.PESO, 10);
	}
}

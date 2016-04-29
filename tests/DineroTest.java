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

	@Override
	protected Dinero getObject() throws Exception {
		return new Dinero(Moneda.PESO, 10);
	}
}

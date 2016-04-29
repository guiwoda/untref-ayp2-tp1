import static org.junit.Assert.*;

import org.junit.Test;

public class DineroTest extends TrabajoPracticoTest<Dinero> {

	@Test
	public void puedoSumarloConOtroDinero() throws Exception {
		Dinero<Peso> dinero = new Dinero<Peso>(new Peso(), 10);

		assertEquals(new Dinero<Peso>(new Peso(), 20), dinero.sumar(dinero));
	}

	@Test
	public void puedoSumarleUnaCantidadEntera() throws Exception {
		Dinero<Peso> dinero = new Dinero<Peso>(new Peso(), 10);

		assertEquals(new Dinero<Peso>(new Peso(), 21), dinero.sumar(11));
	}

	@Test
	public void puedeDistribuirseEnPartesSinPerderCentavos() throws Exception {
		Dinero<Peso> dinero = new Dinero<Peso>(new Peso(), 9.99f);

		int[] partes = { 50, 50 };

		Dinero<Peso>[] resultado = dinero.distribuir(partes);

		assertEquals(dinero, resultado[0].sumar(resultado[1]));
	}

	@Override
	protected Dinero getObject() throws Exception {
		return new Dinero<Peso>(Moneda.PESO, 10);
	}
}

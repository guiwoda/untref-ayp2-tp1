import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TransaccionTest extends TrabajoPracticoTest<Transaccion<Peso>> {

	@Override
	protected Transaccion<Peso> getObject() throws Exception {
		return new Transaccion<Peso>(new Dinero<Peso>(Moneda.PESO, 100));
	}
	
	@Test
	public void tieneDatosObligatoriosAlRegistrarse() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeSerRegistradaAdemasConObservaciones() {
		fail("Not yet implemented");
	}

	@Test
	public void tieneUnaFechaYHora() {
		fail("Not yet implemented");
	}

	@Test
	public void puedeTenerCreditoComoTipoDeMovimiento() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeTenerDebitoComoTipoDeMovimiento() {
		fail("Not yet implemented");
	}

	@Test
	public void tieneUnMonto() {
		fail("Not yet implemented");
	}

	@Test
	public void tieneUnMotivo() {
		fail("Not yet implemented");
	}

	@Test
	public void puedeTenerObservaciones() {
		fail("Not yet implemented");
	}

	@Test
	public void puedeNoTenerObservaciones() {
		fail("Not yet implemented");
	}
}

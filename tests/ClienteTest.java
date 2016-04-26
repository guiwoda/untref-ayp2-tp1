import static org.junit.Assert.*;

import org.junit.Test;

abstract class ClienteTest extends TrabajoPracticoTest {

	abstract protected Cliente getCliente();
	
	@Override
	protected Object getObject() {
		return getCliente();
	}
	
	@Test
	public void necesitaDatosMinimosParaRegistrarse() {
		fail("Not yet implemented");
	}

	@Test
	public void tieneUnaRazonSocial() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnCUIT() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnDomicilio() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnTelefono() {
		fail("Not yet implemented");
	}
}

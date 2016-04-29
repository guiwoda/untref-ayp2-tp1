import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

abstract public class ClienteTest<C extends Cliente> extends TrabajoPracticoTest<C> {

	protected C cliente;
	protected String razonSocial;
	protected String cuit;
	protected Domicilio domicilio;
	protected String telefono;
	
	abstract protected C getCliente();
	
	@Override
	public C getObject() {
		return getCliente();
	}
	
	@Test
	public void necesitaDatosMinimosParaRegistrarse() {
		assertNotNull(cliente);
	}

	@Test
	public void tieneUnaRazonSocial() {
		assertEquals(razonSocial, cliente.getRazonSocial());
	}
	
	@Test
	public void tieneUnCUIT() {
		assertEquals(cuit, cliente.getCuit());
	}
	
	@Test
	public void tieneUnDomicilio() {
		assertEquals(domicilio, cliente.getDomicilio());
	}
	
	@Test
	public void tieneUnTelefono() {
		assertEquals(telefono, cliente.getTelefono());
	}
	
	@Before
	public void setUp() throws Exception {
		razonSocial = "ACME Co.";
		cuit = "20-12345678-9";
		domicilio = new Domicilio();
		telefono = "5555-4444";
		
		cliente = getCliente();
	}
}

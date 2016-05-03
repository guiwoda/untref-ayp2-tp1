import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DomicilioTest {
	private String	direccion	= "Av. Siempreviva 742";
	private int codigoPostal = 1234;
	private String localidad = "Springfield";
	private String provincia = "Wyoming";
	
	private Domicilio domicilio;
	
	@Before
	public void setUp() {
		domicilio = new Domicilio(direccion, codigoPostal, localidad, provincia);
	}
	
	@Test
	public void tieneUnaDireccion() {
		assertEquals(direccion, domicilio.getDireccion());
	}

	@Test
	public void tieneUnCodigoPostal() {
		assertEquals(codigoPostal, domicilio.getCodigoPostal());
	}
	
	@Test
	public void tieneUnaLocalidad() {
		assertEquals(localidad, domicilio.getLocalidad());
	}
	
	@Test
	public void tieneUnaProvincia() {
		assertEquals(provincia, domicilio.getProvincia());
	}
}

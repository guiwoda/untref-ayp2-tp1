import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestionDeCuentasTest extends TrabajoPracticoTest {

	private GestionDeCuentas cuentas;
	
	@Before
	@Override
	protected Object getObject() {
		cuentas = new GestionDeCuentas();
		
		return cuentas;
	}
	@Test
	public void puedeAbrirUnaCajaDeAhorro() {
		fail("Not yet implemented");
	}

	@Test
	public void puedeAbrirUnaCuentaCorriente() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeInhabilitarUnaCuenta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeHabilitarUnaCuenta() {
		fail("Not yet implemented");
	}
}

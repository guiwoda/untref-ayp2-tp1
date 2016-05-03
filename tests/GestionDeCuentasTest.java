import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class GestionDeCuentasTest {
	private GestionDeCuentas gestion;
	private Map<Integer, CuentaDeCliente<?>> cuentas;
	
	@Before
	public void setUp() {
		cuentas = new HashMap<Integer, CuentaDeCliente<?>>();
		gestion = new GestionDeCuentas(cuentas);
	}
	
	@Test
	public void puedeAbrirUnaCajaDeAhorro() throws Exception {
		CajaDeAhorro caja = gestion.abrirCajaDeAhorro(
			new Dinero(Moneda.PESO, 100),
			ClientesFixture.personasFisicas(2),
			new Dinero(Moneda.PESO, 5)
		);
		
		assertTrue(cuentas.containsValue(caja));
		assertTrue(cuentas.containsKey(caja.getCBU()));
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

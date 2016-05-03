import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class GestionDeCuentasTest {
	private GestionDeCuentas					gestion;
	private CajaDeAhorro						unaCaja;
	private Map<Integer, CuentaDeCliente<?>>	cuentas;

	@Before
	public void setUp() throws Exception {
		cuentas = new HashMap<Integer, CuentaDeCliente<?>>();
		unaCaja = CuentasFixture.cajaDeAhorroPesos();
		cuentas.put(unaCaja.getCBU(), unaCaja);
		gestion = new GestionDeCuentas(cuentas);
	}

	@Test
	public void puedeAbrirUnaCajaDeAhorro() throws Exception {
		CajaDeAhorro caja = gestion.abrirCajaDeAhorro(new Dinero(Moneda.PESO, 100), ClientesFixture.personasFisicas(2), new Dinero(Moneda.PESO, 5));

		assertTrue(cuentas.containsValue(caja));
		assertTrue(cuentas.containsKey(caja.getCBU()));
	}

	@Test
	public void puedeAbrirUnaCuentaCorriente() throws Exception {
		CuentaCorriente cc = gestion.abrirCuentaCorriente(new Dinero(Moneda.PESO, 200), ClientesFixture.clientes(), new Dinero(Moneda.PESO, 2000));

		assertTrue(cuentas.containsValue(cc));
		assertTrue(cuentas.containsKey(cc.getCBU()));
	}

	@Test
	public void puedeInhabilitarUnaCuenta() {
		int cbu = unaCaja.getCBU();

		gestion.inhabilitar(cbu);

		assertFalse(cuentas.get(cbu).isActiva());
	}

	@Test
	public void puedeHabilitarUnaCuenta() {
		int cbu = unaCaja.getCBU();
		unaCaja.inactivar();

		gestion.habilitar(cbu);

		assertTrue(cuentas.get(cbu).isActiva());
	}
}

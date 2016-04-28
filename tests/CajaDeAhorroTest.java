import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CajaDeAhorroTest extends CuentaTest {

	private static final int	COSTO			= 7;
	private static final int	SALDO			= 10;
	private static final int    INTERES         = 4;

	private Moneda				denominacion	= new Dolar();
	private Dinero<Moneda>		mantenimiento	= new Dinero<Moneda>(denominacion, COSTO);
	private Dinero<Moneda>		saldo			= new Dinero<Moneda>(denominacion, SALDO);
	private Dinero<Moneda>		interes			= new Dinero<Moneda>(denominacion, INTERES);
	private Set<PersonaFisica>	clientes		= new HashSet<PersonaFisica>();

	@Test
	public void tieneAlMenosUnaPersonaFisicaAsociadaComoTitular() {
		assertFalse(cuenta().getTitulares().isEmpty());
	}

	@Test
	public void puedeTenerMasDeUnaPersonaFisicaAsociadaComoTitular() {
		clientes.clear();
		clientes.add(new PersonaFisica());

		assertEquals(2, createCuenta().getTitulares().size());
	}

	@Test(expected = Exception.class)
	public void noPuedeNoTenerAlMenosUnaPersonaFisicaAsociadaComoTitular() throws Exception {
		clientes.clear();

		createCuenta(saldo, CBU, clientes, interes);
	}

	@Test(expected = Exception.class)
	public void noPuedeTenerSaldoNegativo() throws Exception {
		cuenta().extraer(new Dinero<>(denominacion, 9999));
	}

	@Test
	public void puedeEstarNominadaEnDolares() {
		assertNotNull(createCuenta());
	}

	@Test
	public void puedeEstarNominadaEnPesos() {
		denominacion = new Peso();

		assertNotNull(createCuenta());
	}

	@Test
	public void tieneUnCostoDeMantenimientoFijoEnDolares() {
		CajaDeAhorro<Moneda> cuenta = cuenta();

		assertEquals(denominacion.getMantenimientoCajaDeAhorro(), cuenta.getCostoDeMantenimiento());
	}

	@Test
	public void tieneUnCostoDeMantenimientoFijoEnPesos() throws Exception {
		denominacion = new Peso();

		CajaDeAhorro<Moneda> cuenta = createCuenta(denominacion);

		assertEquals(denominacion.getMantenimientoCajaDeAhorro(), cuenta.getCostoDeMantenimiento());
	}

	@Test
	public void seDebeDepositarUnMontoAlAbrir() {
		assertTrue(cuenta().getSaldo().isPositivo());
	}

	@Test
	public void sePuedeDepositarDinero() throws Exception {
		Dinero<Moneda> dinero = new Dinero<Moneda>(denominacion, 40);
		cuenta().depositar(dinero);

		assertEquals(saldo.sumar(dinero), cuenta().getSaldo());
	}

	@Test
	public void sePuedeExtraerDinero() throws Exception {
		Dinero<Moneda> dinero = new Dinero<Moneda>(denominacion, 2);
		cuenta().extraer(dinero);

		assertEquals(saldo.restar(dinero), cuenta().getSaldo());
	}

	@Test
	public void sePuedeTransferirDineroAOtraCuenta() {
		Cuenta<Moneda> otra     = createCuenta();
		Dinero<Moneda> monto    = new Dinero<>(denominacion, 5);
		Dinero<Moneda> expected = otra.getSaldo().sumar(monto);

		cuenta().transferir(otra, monto);

		assertEquals(saldo.restar(monto), cuenta().getSaldo());
		assertEquals(expected, otra.getSaldo());
	}

	@Test
	public void tieneUnaTasaDeInteresPactadaAlAbrir() {
		assertEquals(interes, cuenta().getInteres());
	}

	@Override
	public Moneda createMoneda() {
		return denominacion;
	}

	@Override
	public CajaDeAhorro<Moneda> createCuenta() {
		return createCuenta(CBU);
	}
	
	public CajaDeAhorro<Moneda> createCuenta(Moneda denominacion) throws Exception {
		clientes.add(new PersonaFisica());
		return createCuenta(new Dinero<Moneda>(denominacion, SALDO), CBU, clientes, interes);
	}

	@Override
	public CajaDeAhorro<Moneda> createCuenta(int CBU) {
		try {
			clientes.add(new PersonaFisica());
			
			return createCuenta(saldo, CBU, clientes, interes);
		} catch (Exception e) {
			fail("Something went wrong: " + e.getMessage());
		}

		return null;
	}

	public CajaDeAhorro<Moneda> createCuenta(Dinero<Moneda> saldo, int CBU, Set<PersonaFisica> clientes, Dinero<Moneda> interes) throws Exception {
		return new CajaDeAhorro<Moneda>(CBU, saldo, clientes, interes);
	}

	@SuppressWarnings("all")
	public CajaDeAhorro<Moneda> cuenta() {
		return (CajaDeAhorro<Moneda>) this.cuenta;
	}

	@After
	public void cleanUp() {
		clientes.clear();
	}
}

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CajaDeAhorroTest extends CuentaTest<CajaDeAhorro<Moneda>, Moneda, PersonaFisica> {

	private static final int	COSTO			= 7;
	private static final int    INTERES         = 4;

	private Dinero<Moneda>		mantenimiento;
	private Dinero<Moneda>		interes;

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
	public void tieneUnaTasaDeInteresPactadaAlAbrir() {
		assertEquals(interes, cuenta().getInteres());
	}
	
	@Override
	public Set<PersonaFisica> createClientes() {
		return new HashSet<PersonaFisica>();
	}

	public CajaDeAhorro<Moneda> createCuenta(Moneda denominacion) throws Exception {
		clientes.add(new PersonaFisica());
		return createCuenta(new Dinero<Moneda>(denominacion, SALDO), CBU, clientes, interes);
	}

	@Override
	public CajaDeAhorro<Moneda> createCuenta(int CBU) {
		try {
			clientes.add(createCliente());
			
			return createCuenta(saldo, CBU, clientes, interes);
		} catch (Exception e) {
			fail();
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

	@Override
	public CajaDeAhorro createCuenta(Set clientes) throws Exception {
		return createCuenta(saldo, CBU, clientes, interes);
	}

	@Override
	public PersonaFisica createCliente() {
		return new PersonaFisica();
	}

	@Override
	protected Moneda createDenominacion() {
		return new Dolar();
	}
	
	@Override
	public void setUp() throws Exception {
		Moneda denominacion = createDenominacion();
		
		mantenimiento 	= new Dinero<Moneda>(denominacion, COSTO);
		interes 		= new Dinero<Moneda>(denominacion, INTERES);
		
		super.setUp();
	}
}

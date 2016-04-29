import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Test;

public class CajaDeAhorroTest extends CuentaDeClienteTest<CajaDeAhorro, PersonaFisica> {

	private static final int INTERES = 4;

	private Dinero interes;

	@Test(expected = Exception.class)
	public void noPuedeTenerSaldoNegativo() throws Exception {
		cuenta.extraer(new Dinero(denominacion, 9999));
	}

	@Test
	public void puedeEstarNominadaEnDolares() throws Exception {
		assertNotNull(createCuenta());
	}

	@Test
	public void puedeEstarNominadaEnPesos() throws Exception {
		denominacion = Moneda.PESO;

		assertNotNull(createCuenta());
	}

	@Test
	public void tieneUnCostoDeMantenimientoFijoEnDolares() throws Exception {
		assertEquals(denominacion.getMantenimientoCajaDeAhorro(), cuenta.getCostoDeMantenimiento());
	}

	@Test
	public void tieneUnCostoDeMantenimientoFijoEnPesos() throws Exception {
		denominacion = Moneda.PESO;

		CajaDeAhorro cuenta = createCuenta(denominacion);

		assertEquals(denominacion.getMantenimientoCajaDeAhorro(), cuenta.getCostoDeMantenimiento());
	}

	@Test
	public void seDebeDepositarUnMontoAlAbrir() throws Exception {
		assertTrue(cuenta.getSaldo().isPositivo());
	}

	@Test
	public void sePuedeDepositarDinero() throws Exception {
		Dinero dinero = new Dinero(denominacion, 40);
		cuenta.depositar(dinero);

		assertEquals(saldo.sumar(dinero), cuenta.getSaldo());
	}

	@Test
	public void sePuedeExtraerDinero() throws Exception {
		Dinero dinero = new Dinero(denominacion, 2);
		cuenta.extraer(dinero);

		assertEquals(saldo.restar(dinero), cuenta.getSaldo());
	}

	@Test
	public void tieneUnaTasaDeInteresPactadaAlAbrir() throws Exception {
		assertEquals(interes, cuenta.getInteres());
	}

	@Override
	public Set<PersonaFisica> createClientes() throws Exception {
		return new HashSet<PersonaFisica>();
	}

	public CajaDeAhorro createCuenta(Moneda denominacion) throws Exception {
		clientes.add(createCliente("20-12332112-3"));
		return createCuenta(new Dinero(denominacion, SALDO), CBU, clientes, interes);
	}

	@Override
	public CajaDeAhorro createCuenta(int CBU) throws Exception {
		clientes.add(createCliente("20-12345678-9"));

		return createCuenta(saldo, CBU, clientes, interes);
	}

	public CajaDeAhorro createCuenta(Dinero saldo, int CBU, Set<PersonaFisica> clientes, Dinero interes) throws Exception {
		return new CajaDeAhorro(CBU, saldo, clientes, interes);
	}

	@After
	public void cleanUp() throws Exception {
		clientes.clear();
	}

	@Override
	public CajaDeAhorro createCuenta(Set<PersonaFisica> clientes) throws Exception {
		return createCuenta(saldo, CBU, clientes, interes);
	}

	@Override
	public PersonaFisica createCliente(String cuit) throws Exception {
		return new PersonaFisica("Acme Co.", cuit, new Domicilio(), "5555-4444", Documento.dni(32000555), EstadoCivil.SOLTERO, "Programador", "Maria Angélica de los Laureles del Monte Diaz Lacarra");
	}

	@Override
	protected Moneda createDenominacion() throws Exception {
		return Moneda.DOLAR;
	}

	@Override
	public void setUp() throws Exception {
		Moneda denominacion = createDenominacion();

		interes = new Dinero(denominacion, INTERES);

		super.setUp();
	}
}

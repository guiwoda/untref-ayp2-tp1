import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;

public class CuentaCorrienteTest extends CuentaDeClienteTest<CuentaCorriente, Peso, Cliente> {
	private Dinero<Peso> sobregiro;
	
	@Test
	public void tieneUnAcuerdoDeSobregiroPactadoAlAbrir() throws Exception {
		assertEquals(sobregiro, cuenta.getSobregiro());
	}

	@Test
	public void puedeTenerSaldoNegativo() throws Exception {
		cuenta.extraer(cuenta.getSaldo());
		cuenta.extraer(sobregiro.restar(100));
		
		assertTrue(cuenta.getSaldo().isNegativo());
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTenerMenorSaldoQueElMontoDeSobregiro() throws Exception {
		cuenta.extraer(cuenta.getSaldo().sumar(sobregiro).sumar(100));
	}

	@Test
	public void soloPuedeEstarNominadaEnPesos() throws Exception {
		assertThat(cuenta.getDenominacion(), new IsInstanceOf(Peso.class));
	}

	@Test
	public void seCobraUnaComisionPorDepositoQueDebeSerDepositadaEnUnaCuentaEspecial() throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
		
		Dinero<Peso> monto = new Dinero<Peso>(Moneda.PESO, 40000);
		Dinero<Peso> expected = cuenta.getSaldo().sumar(40000 - 400 * porcentajeComision);
		
		assertEquals(expected, cuenta.depositar(monto));
	}
	
	@Test
	public void seCobraUnaComisionPorExtraccionQueDebeSerDepositadaEnUnaCuentaEspecial() throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
	}
	
	@Test
	public void seCobraUnaComisionPorTransferenciaQueDebeSerDepositadaEnUnaCuentaEspecial() throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
	}

	@Test
	public void noTieneCostoDeMantenimiento() throws Exception {
		assertEquals(new Dinero<Peso>(denominacion, 0), cuenta.getCostoDeMantenimiento());
	}

	@Override
	public CuentaCorriente createCuenta() throws Exception {
		return createCuenta(CBU);
	}

	@Override
	public CuentaCorriente createCuenta(int CBU) throws Exception {
		clientes.add(createCliente("20-23455432-1"));
		
		return createCuenta(CBU, new Dinero<Peso>(denominacion, 10), clientes, sobregiro);
	}

	public CuentaCorriente createCuenta(int CBU, Dinero<Peso> saldo, Set<Cliente> clientes, Dinero<Peso> sobregiro) throws Exception {
		return new CuentaCorriente(CBU, saldo, clientes, sobregiro);
	}

	@Override
	public CuentaCorriente createCuenta(Set<Cliente> clientes) throws Exception {
		return createCuenta(CBU, new Dinero<Peso>(Moneda.PESO, 10), clientes, sobregiro);
	}

	@Override
	public Set<Cliente> createClientes() throws Exception {
		return new HashSet<Cliente>();
	}

	@Override
	public Cliente createCliente(String cuit) throws Exception {
		return new PersonaJuridica("Acme Co.", cuit, new Domicilio(), "5555-4444", new Date(1445385600));
	}

	@Override
	protected Peso createDenominacion() throws Exception {
		return Moneda.PESO;
	}
	
	@Override
	public void setUp() throws Exception {
		sobregiro = new Dinero<Peso>(Moneda.PESO, 500);
		
		super.setUp();
	}
}

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;

public class CuentaCorrienteTest extends CuentaDeClienteTest<CuentaCorriente, Peso, Cliente> {
	@Test
	public void tieneUnAcuerdoDeSobregiroPactadoAlAbrir() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void puedeTenerSaldoNegativoHastaElMontoDeSobregiro() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void seNecesitaUnMinimoDeDineroParaAbrir() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void sePuedeDepositarDinero() throws Exception {
		fail("Not yet implemented");
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
		clientes.add(createCliente());
		
		return createCuenta(CBU, new Dinero<Peso>(denominacion, 10), clientes);
	}

	public CuentaCorriente createCuenta(int CBU, Dinero<Peso> saldo, Set<Cliente> clientes) throws Exception {
		return new CuentaCorriente(CBU, saldo, clientes);
	}

	@Override
	public CuentaCorriente createCuenta(Set<Cliente> clientes) throws Exception {
		return createCuenta(CBU, new Dinero<Peso>(Moneda.PESO, 10), clientes);
	}

	@Override
	public Set<Cliente> createClientes() throws Exception {
		return new HashSet<Cliente>();
	}

	@Override
	public Cliente createCliente() throws Exception {
		return new PersonaJuridica();
	}

	@Override
	protected Peso createDenominacion() throws Exception {
		return Moneda.PESO;
	}
}

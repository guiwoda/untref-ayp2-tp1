import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;

public class CuentaCorrienteTest extends CuentaTest<CuentaCorriente, Peso, Cliente> {
	@Test
	public void tieneUnAcuerdoDeSobregiroPactadoAlAbrir() {
		fail("Not yet implemented");
	}

	@Test
	public void puedeTenerSaldoNegativoHastaElMontoDeSobregiro() {
		fail("Not yet implemented");
	}

	@Test
	public void seNecesitaUnMinimoDeDineroParaAbrir() {
		fail("Not yet implemented");
	}

	@Test
	public void sePuedeDepositarDinero() {
		fail("Not yet implemented");
	}

	@Test
	public void soloPuedeEstarNominadaEnPesos() {
		assertThat(cuenta.getDenominacion(), new IsInstanceOf(Peso.class));
	}

	@Test
	public void seCobraUnaComisionPorCadaMovimientoQueDebeSerDepositadaEnUnaCuentaEspecial() {
		fail("Not yet implemented");
	}

	@Test
	public void noTieneCostoDeMantenimiento() throws Exception {
		assertEquals(new Dinero<Peso>(denominacion, 0), cuenta.getCostoDeMantenimiento());
	}

	@Override
	public CuentaCorriente createCuenta() {
		return createCuenta(CBU);
	}

	@Override
	public CuentaCorriente createCuenta(int CBU) {
		try {
			clientes.add((Cliente) new PersonaJuridica());

			return createCuenta(CBU, new Dinero<Peso>(new Peso(), 10), clientes);
		} catch (Exception e) {
			fail();
		}

		return null;
	}

	public CuentaCorriente createCuenta(int CBU, Dinero<Peso> saldo, Set<Cliente> clientes) throws Exception {
		return new CuentaCorriente(CBU, saldo, clientes);
	}

	@Override
	public CuentaCorriente createCuenta(Set<Cliente> clientes) throws Exception {
		return createCuenta(CBU, new Dinero<Peso>(new Peso(), 10), clientes);
	}

	@Override
	public Set<Cliente> createClientes() {
		return new HashSet<Cliente>();
	}

	@Override
	public Cliente createCliente() {
		return new PersonaJuridica();
	}

	@Override
	protected Peso createDenominacion() {
		return new Peso();
	}
}

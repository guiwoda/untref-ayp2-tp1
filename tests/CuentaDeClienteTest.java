import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

abstract public class CuentaDeClienteTest<C extends CuentaDeCliente<T>, T extends Cliente> extends CuentaTest<C> {

	protected Set<T> clientes;
	
	abstract public C createCuenta(Set<T> clientes) throws Exception;

	abstract public Set<T> createClientes() throws Exception;

	abstract public T createCliente(String cuit) throws Exception;
	
	@Test
	public void tieneAlMenosUnCliente() throws Exception {
		assertFalse(cuenta.getTitulares().isEmpty());
	}

	@Test
	public void puedeTenerMasDeUnCliente() throws Exception {
		clientes.clear();
		clientes.add(createCliente("20-87654321-0"));

		assertEquals(2, createCuenta().getTitulares().size());
	}

	@Test(expected = Exception.class)
	public void noPuedeNoTenerAlMenosUnCliente() throws Exception {
		clientes.clear();

		createCuenta(clientes);
	}

	@Test
	public void unaCuentaActivaPuedeAcreditarDinero() throws Exception {
		Dinero saldo = cuenta.getSaldo();
		Dinero deposito = new Dinero(denominacion, 10);

		cuenta.depositar(deposito);

		assertTrue(cuenta.getSaldo().compareTo(saldo) > 0);
	}

	@Test
	public void unaCuentaActivaPuedeDebitarDinero() throws Exception {
		Dinero saldo = cuenta.getSaldo();
		Dinero extraccion = new Dinero(denominacion, 6);
		cuenta.extraer(extraccion);

		assertTrue(cuenta.getSaldo().compareTo(saldo) < 0);
	}

	@Test(expected = Exception.class)
	public void unaCuentaInactivaNoPuedeAcreditarDinero() throws Exception {
		cuenta.inactivar();

		cuenta.depositar(new Dinero(denominacion, 10));
	}

	@Test(expected = Exception.class)
	public void unaCuentaInactivaNoPuedeDebitarDinero() throws Exception {
		cuenta.inactivar();

		cuenta.extraer(new Dinero(denominacion, 10));
	}

	@Test
	public void sePuedeTransferirDineroAOtraCuenta() throws Exception {
		Cuenta otra = createCuenta();
		Dinero monto = new Dinero(denominacion, 5);
		Dinero expected = otra.getSaldo();

		cuenta.transferir(otra, monto);

		assertTrue(cuenta.getSaldo().compareTo(saldo) < 0);
		assertTrue(otra.getSaldo().compareTo(expected) > 0);
	}

	@Override
	public void setUp() throws Exception {
		clientes = this.createClientes();
		
		super.setUp();
	}
}

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

abstract public class CuentaDeClienteTest<C extends CuentaDeCliente<M, T>, M extends Moneda, T extends Cliente> extends CuentaTest<C, M> {

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
	@SuppressWarnings("all")
	public void unaCuentaActivaPuedeAcreditarDinero() throws Exception {
		Dinero<M> saldo = cuenta.getSaldo();
		Dinero<M> deposito = new Dinero<M>(denominacion, 10);

		cuenta.depositar(deposito);

		assertTrue(cuenta.getSaldo().compareTo(saldo) > 0);
	}

	@Test
	@SuppressWarnings("all")
	public void unaCuentaActivaPuedeDebitarDinero() throws Exception {
		Dinero<M> saldo = cuenta.getSaldo();
		Dinero<M> extraccion = new Dinero<M>(denominacion, 6);
		cuenta.extraer(extraccion);

		assertEquals(saldo.restar(extraccion), cuenta.getSaldo());
	}

	@Test(expected = Exception.class)
	@SuppressWarnings("all")
	public void unaCuentaInactivaNoPuedeAcreditarDinero() throws Exception {
		cuenta.inactivar();

		cuenta.depositar(new Dinero<M>(denominacion, 10));
	}

	@Test(expected = Exception.class)
	@SuppressWarnings("all")
	public void unaCuentaInactivaNoPuedeDebitarDinero() throws Exception {
		cuenta.inactivar();

		cuenta.extraer(new Dinero<M>(denominacion, 10));
	}

	@Test
	@SuppressWarnings("all")
	public void sePuedeTransferirDineroAOtraCuenta() throws Exception {
		Cuenta<M> otra = createCuenta();
		Dinero<M> monto = new Dinero<M>(denominacion, 5);
		Dinero<M> expected = otra.getSaldo();

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

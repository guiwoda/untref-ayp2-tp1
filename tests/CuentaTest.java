import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("all")
abstract public class CuentaTest<C extends Cuenta<M,T>, M extends Moneda, T extends Cliente> extends TrabajoPracticoTest {

	protected static final int	CBU		= 1337;
	protected static final int	SALDO	= 10;

	protected M					denominacion;
	protected Dinero<M>			saldo;
	protected C					cuenta;
	protected Set<T>			clientes;

	@Test
	public void tieneAlMenosUnCliente() {
		assertFalse(cuenta.getTitulares().isEmpty());
	}

	@Test
	public void puedeTenerMasDeUnCliente() {
		clientes.clear();
		clientes.add(createCliente());

		assertEquals(2, createCuenta().getTitulares().size());
	}

	@Test(expected = Exception.class)
	public void noPuedeNoTenerAlMenosUnCliente() throws Exception {
		clientes.clear();

		createCuenta(clientes);
	}

	@Test
	public void tieneUnCBU() {
		assertEquals(CBU, cuenta.getCBU());
	}

	@Test
	public void tieneUnSaldo() {
		assertNotNull(cuenta.getSaldo());
	}

	@Test
	public void tieneUnHistoricoDeTransacciones() {
		assertNotNull(cuenta.getTransacciones());
	}

	@Test
	@SuppressWarnings("all")
	public void unaCuentaActivaPuedeAcreditarDinero() throws Exception {
		Dinero<M> saldo = cuenta.getSaldo();
		Dinero<M> deposito = new Dinero<M>(denominacion, 10);

		cuenta.depositar(deposito);

		assertEquals(saldo.sumar(deposito), cuenta.getSaldo());
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
	public void elSaldoDebeSerIgualQueLaSumaDeSusMovimientos() throws Exception {
		cuenta.depositar(new Dinero<M>(denominacion, 15));
		cuenta.depositar(new Dinero<M>(denominacion, 10));
		cuenta.extraer(new Dinero<M>(denominacion, 5));

		Dinero<M> inicial = new Dinero<M>(denominacion, 0);

		Set<Transaccion<M>> transacciones = cuenta.getTransacciones();
		for (Transaccion current : transacciones) {
			inicial = inicial.sumar(current.getMonto().getCentavos());
		}

		assertEquals(inicial, cuenta.getSaldo());
	}

	@Test
	@SuppressWarnings("all")
	public void sePuedeTransferirDineroAOtraCuenta() throws Exception {
		Cuenta<M, ?> otra = createCuenta();
		Dinero<M> monto = new Dinero<M>(denominacion, 5);
		Dinero<M> expected = otra.getSaldo().sumar(monto);

		cuenta.transferir(otra, monto);

		assertEquals(saldo.restar(monto), cuenta.getSaldo());
		assertEquals(expected, otra.getSaldo());
	}

	abstract public C createCuenta(int CBU);

	abstract public C createCuenta(Set<T> clientes) throws Exception;

	abstract public Set<T> createClientes();

	abstract public T createCliente();

	@SuppressWarnings("all")
	public C createCuenta() {
		return createCuenta(CBU);
	}

	@Before
	public void setUp() throws Exception {
		clientes 		= this.createClientes();
		denominacion 	= this.createDenominacion();
		saldo 			= new Dinero<M>(denominacion, SALDO);
		
		cuenta = this.<M> createCuenta(CBU);
	}

	abstract protected M createDenominacion();

	@Override
	public Object getObject() {
		return this.<M> createCuenta(CBU);
	}
}

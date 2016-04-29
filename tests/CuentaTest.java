import static org.junit.Assert.*;

import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;

abstract public class CuentaTest<C extends Cuenta> extends TrabajoPracticoTest<C> {

	protected static final int	CBU		= 1337;
	protected static final int	SALDO	= 10;

	protected Moneda	denominacion;
	protected Dinero	saldo;
	protected C			cuenta;

	abstract public C createCuenta(int CBU) throws Exception;

	abstract protected Moneda createDenominacion() throws Exception;

	@Before
	public void setUp() throws Exception {
		denominacion = createDenominacion();
		saldo = new Dinero(denominacion, SALDO);

		cuenta = this.createCuenta(CBU);
	}

	@Test
	public void tieneUnCBU() throws Exception {
		assertEquals(CBU, cuenta.getCBU());
	}

	@Test
	public void tieneUnSaldo() throws Exception {
		assertNotNull(cuenta.getSaldo());
	}

	@Test
	public void tieneUnHistoricoDeTransacciones() throws Exception {
		assertNotNull(cuenta.getTransacciones());
	}

	@Test
	public void elSaldoDebeSerIgualQueLaSumaDeSusMovimientos() throws Exception {
		cuenta.depositar(new Dinero(denominacion, 15));
		cuenta.depositar(new Dinero(denominacion, 10));

		Dinero inicial = new Dinero(denominacion, 0);

		for (Transaccion current : cuenta.getTransacciones()) {
			inicial = current.aplicar(inicial);
		}

		assertEquals(inicial, cuenta.getSaldo());
	}

	@Test
	public void implementaToString() throws Exception {
		assertThat(cuenta.toString(), new StringContains(String.valueOf(CBU)));
	}

	public C createCuenta() throws Exception {
		return createCuenta(CBU);
	}

	@Override
	public C getObject() throws Exception {
		return createCuenta(CBU);
	}
}

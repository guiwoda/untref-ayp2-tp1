import static org.junit.Assert.*;

import java.util.Set;

import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("all")
abstract public class CuentaTest<C extends Cuenta<M>, M extends Moneda> extends TrabajoPracticoTest<C> {

	protected static final int	CBU		= 1337;
	protected static final int	SALDO	= 10;

	protected M					denominacion;
	protected Dinero<M>			saldo;
	protected C					cuenta;
	
	abstract public C createCuenta(int CBU) throws Exception;
	
	abstract protected M createDenominacion() throws Exception;
	
	@Before
	public void setUp() throws Exception {
		denominacion 	= createDenominacion();
		saldo 			= new Dinero<M>(denominacion, SALDO);
		
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
	@SuppressWarnings("all")
	public void elSaldoDebeSerIgualQueLaSumaDeSusMovimientos() throws Exception {
		cuenta.depositar(new Dinero<M>(denominacion, 15));
		cuenta.depositar(new Dinero<M>(denominacion, 10));

		Dinero<M> inicial = new Dinero<M>(denominacion, 0);

		Set<Transaccion<M>> transacciones = cuenta.getTransacciones();
		for (Transaccion current : transacciones) {
			inicial = inicial.sumar(current.getMonto());
		}

		assertEquals(inicial, cuenta.getSaldo());
	}
	
	@Test
	public void implementaToString() throws Exception {
		assertThat(cuenta.toString(), new StringContains(String.valueOf(CBU)));
	}

	@SuppressWarnings("all")
	public C createCuenta() throws Exception {
		return createCuenta(CBU);
	}

	@Override
	public C getObject() throws Exception {
		return createCuenta(CBU);
	}
}

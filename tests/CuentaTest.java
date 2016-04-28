import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

abstract public class CuentaTest<C extends Cuenta, M extends Moneda> extends TrabajoPracticoTest {

	protected static final int CBU = 1337;
	
	protected C cuenta;
	
	protected M moneda;
	
	abstract public <M> C createCuenta(int CBU);
	
	public <M> C createCuenta() {
		return createCuenta(CBU);
	}
	
	abstract public M createMoneda();
	
	@Before
	public void setUp() {
		this.cuenta = this.<M>createCuenta(CBU);
		this.moneda = this.createMoneda();
	}
	
	
	@Override
	public Object getObject() {
		return this.<M>createCuenta(CBU);
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
	public void unaCuentaActivaPuedeAcreditarDinero() throws Exception {
		Dinero<M> saldo = cuenta.getSaldo();
		Dinero<M> deposito = new Dinero<M>(moneda, 10);
		
		cuenta.depositar(deposito);
		
		assertEquals(saldo.sumar(deposito), cuenta.getSaldo());
	}

	@Test
	public void unaCuentaActivaPuedeDebitarDinero() throws Exception {
		Dinero<M> saldo = cuenta.getSaldo();
		Dinero<M> extraccion = new Dinero<M>(moneda, 6);
		cuenta.extraer(extraccion);
		
		assertEquals(saldo.restar(extraccion), cuenta.getSaldo());
	}
	
	@Test(expected=Exception.class)
	public void unaCuentaInactivaNoPuedeAcreditarDinero() throws Exception {
		cuenta.inactivar();
		
		cuenta.depositar(new Dinero<M>(moneda, 10));
	}

	@Test(expected=Exception.class)
	public void unaCuentaInactivaNoPuedeDebitarDinero() throws Exception {
		cuenta.inactivar();
		
		cuenta.extraer(new Dinero<M>(moneda, 10));
	}
	
	@Test
	@SuppressWarnings("all")
	public void elSaldoDebeSerIgualQueLaSumaDeSusMovimientos() throws Exception {
		cuenta.depositar(new Dinero<M>(moneda, 15));
		cuenta.depositar(new Dinero<M>(moneda, 10));
		cuenta.extraer(new Dinero<M>(moneda, 5));
		
		Dinero<M> inicial = new Dinero<M>(moneda, 0);
		
		Set<Transaccion> transacciones = cuenta.getTransacciones();
		for (Transaccion current : transacciones) {
			inicial = inicial.sumar(current.getMonto().getCentavos());
		}
		
		assertEquals(inicial, cuenta.getSaldo());
	}
}

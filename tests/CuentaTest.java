import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

abstract public class CuentaTest<C extends Cuenta, M extends Moneda> extends TrabajoPracticoTest {

	protected C cuenta;
	
	protected M moneda;
	
	protected int CBU = 45;
	
	abstract public <M> C createCuenta(int CBU);
	
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
	public void unaCuentaActivaPuedeAcreditarDinero() {
		Dinero<M> dinero = new Dinero<M>(moneda, 10);
		
		cuenta.depositar(dinero);
		
		assertTrue(cuenta.getSaldo().equals(dinero));
	}

	@Test
	public void unaCuentaActivaPuedeDebitarDinero() {
		fail("Not yet implemented");
	}
	
	@Test
	public void unaCuentaInactivaNoPuedeAcreditarDinero() {
		fail("Not yet implemented");
	}

	@Test
	public void unaCuentaInactivaNoPuedeDebitarDinero() {
		fail("Not yet implemented");
	}
	
	@Test
	public void elSaldoDebeSerIgualQueLaSumaDeSusMovimientos() {
		fail("Not yet implemented");
	}
}

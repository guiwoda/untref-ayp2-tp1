import static org.junit.Assert.*;

import org.junit.Test;

abstract class CuentaTest extends TrabajoPracticoTest {

	abstract protected Cuenta getCuenta();
	
	@Override
	protected Object getObject() {
		return getCuenta();
	}
	
	@Test
	public void tieneUnCBU() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnSaldo() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnHistoricoDeTransacciones() {
		fail("Not yet implemented");
	}
	
	@Test
	public void unaCuentaActivaPuedeAcreditarDinero() {
		fail("Not yet implemented");
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

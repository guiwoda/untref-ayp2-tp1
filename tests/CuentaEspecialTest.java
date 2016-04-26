import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CuentaEspecialTest extends CuentaTest {
	
	private CuentaEspecial cuentaEspecial;
	
	@Before
	@Override
	protected Cuenta createCuenta() {
		cuentaEspecial = new CuentaEspecial();
		
		return cuentaEspecial;
	}

	@Test
	public void puedenCobrarComisionesACuentasDeClientes() {
		fail("Not yet implemented");
	}
}

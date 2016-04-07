import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CajaDeAhorroTest extends CuentaTest {
	
	private CajaDeAhorro cajaDeAhorro;
	
	@Before
	@Override
	protected Cuenta getCuenta() {
		cajaDeAhorro = new CajaDeAhorro();
		
		return cajaDeAhorro;
	}

	@Test
	public void tieneAlMenosUnaPersonaFisicaAsociadaComoTitular() {
		fail("Not yet implemented");
	}

	@Test
	public void noPuedeTenerSaldoNegativo() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeEstarNominadaEnPesos() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeEstarNominadaEnDolares() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnCostoDeMantenimientoFijoEnLaMonedaEnQueEstaNominada() {
		fail("Not yet implemented");
	}
	
	@Test
	public void seDebeDepositarUnMontoAlAbrir() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sePuedeDepositarDinero() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sePuedeExtraerDinero() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sePuedeTransferirDineroAOtraCuenta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnaTasaDeInteresPactadaAlAbrir() {
		fail("Not yet implemented");
	}
}

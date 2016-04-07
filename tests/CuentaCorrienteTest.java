import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CuentaCorrienteTest extends CuentaTest {

	private CuentaCorriente cuentaCorriente;
	
	@Before
	@Override
	protected Cuenta getCuenta() {
		cuentaCorriente = new CuentaCorriente();
		
		return cuentaCorriente;
	}

	@Test
	public void tieneAlMenosUnaPersonaAsociadaComoTitular() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnAcuerdoDeSobregiroPactadoAlAbrir() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeTenerSaldoNegativoHastaElMontoDeSobregiro() {
		fail("Not yet implemented");
	}
	
	@Test
	public void seNecesitaUnMinimoDeDineroParaAbrir() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sePuedeDepositarDinero() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sePuedeTransferirDineroAOtraCuenta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void soloPuedeEstarNominadaEnPesos() {
		fail("Not yet implemented");
	}
	
	@Test
	public void seCobraUnaComisionPorCadaMovimientoQueDebeSerDepositadaEnUnaCuentaEspecial() {
		fail("Not yet implemented");
	}
	
	@Test
	public void noTieneCostoDeMantenimiento() {
		fail("Not yet implemented");
	}
}

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VentanillaTest extends TrabajoPracticoTest {

	private Ventanilla ventanilla;
	
	@Before
	@Override
	protected Object getObject() {
		ventanilla = new Ventanilla();
		
		return ventanilla;
	}

	@Test
	public void puedeDepositarDineroDeLaMonedaCorrespondienteEnUnaCuentaHabilitada() {
		fail("Not yet implemented");
	}
	
	@Test
	public void noPuedeDepositarEnCuentasInactivas() {
		fail("Not yet implemented");
	}

	@Test
	public void noPuedeDepositarDineroDeMonedaDiferenteAComoEstaNominadaLaCuenta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeExtraerEfectivoDeUnaCajaDeAhorroHabilitada() {
		fail("Not yet implemented");
	}
	
	@Test
	public void noPuedeExtraerEfectivoDeUnaCajaDeAhorroInactiva() {
		fail("Not yet implemented");
	}
	
	@Test
	public void noPuedeExtraerMasDelSaldoDisponibleDeUnaCajaDeAhorro() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeTransferirDineroEntreDosCuentasHabilitadas() {
		fail("Not yet implemented");
	}
	
	@Test
	public void noPuedeTransferirDineroDesdeCuentasInactivas() {
		fail("Not yet implemented");
	}
	
	@Test
	public void noPuedeTransferirDineroHaciaCuentasInactivas() {
		fail("Not yet implemented");
	}
	
	@Test
	public void noPuedeTransferirMasDineroDelSaldoQueTengaLaCuentaDeOrigen() {
		fail("Not yet implemented");
	}
	
	@Test
	public void noPuedeTransferirDineroSiElSaldoNoAlcanzaAdemasParaCobrarLosImpuestosCorrespondientes() {
		fail("Not yet implemented");
	}
	
	@Test
	public void debeConvertirLaMonedaAlTipoDeDestinoUsandoElCambioVigente() {
		fail("Not yet implemented");
	}
	
	@Test
	public void debeRegistrarLaMonedaElMontoYElTipoDeCambioEnElCampoDeObservacionesSiHuboUnaConversion() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeListarTodosLosMovimientosDeUnaCuenta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeListarLosUltimosNMovimientosDeUnaCuenta() {
		fail("Not yet implemented");
	}
}

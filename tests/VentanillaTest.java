import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class VentanillaTest extends TrabajoPracticoTest<Ventanilla> {
	private Map<Integer, CuentaDeCliente<?>> cuentas;
	
	@Before
	public void setUp() throws Exception {
		cuentas = new HashMap<>();
		
		for (int i = 0; i < 20; i++) {
			CuentaDeCliente<?> cuenta = CuentasFixture.random();
			
			cuentas.put(cuenta.getCBU(), cuenta);
		}
	}
	
	@Override
	public Ventanilla getObject() {
		return new Ventanilla(cuentas);
	}

	@Test
	public void puedeDepositarDineroDeLaMonedaCorrespondienteEnUnaCuentaHabilitada() throws Exception {
		int CBU = cuentas.keySet().iterator().next();
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);
		Dinero saldo = cuenta.getSaldo();
		
		CuentaDeCliente<?> result = getObject().depositar(CBU, new Dinero(cuenta.getDenominacion(), 1000));
		
		assertEquals(cuenta, result);
		assertTrue(saldo.compareTo(result.getSaldo()) < 0);
		
	}
	
	@Test(expected=Exception.class)
	public void noPuedeDepositarEnCuentasInactivas() throws Exception {
		int CBU = cuentas.keySet().iterator().next();
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);

		cuenta.inactivar();
		
		getObject().depositar(CBU, new Dinero(cuenta.getDenominacion(), 1000));
	}

	@Test(expected=Exception.class)
	public void noPuedeDepositarDineroDeMonedaDiferenteAComoEstaNominadaLaCuenta() throws Exception {
		int CBU = cuentas.keySet().iterator().next();
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);

		Moneda denominacion;
		
		if (cuenta.getDenominacion() instanceof Peso) {
			denominacion = Moneda.DOLAR;
		} else {
			denominacion = Moneda.PESO;
		}
		
		getObject().depositar(CBU, new Dinero(denominacion, 1000));
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

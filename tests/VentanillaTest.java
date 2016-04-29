import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
		
		cuentas.put(1, CuentasFixture.cajaDeAhorroPesos());
		cuentas.put(2, CuentasFixture.cuentaCorriente());
	}
	
	@Override
	public Ventanilla getObject() throws Exception {
		return new Ventanilla(cuentas);
	}

	@Test
	public void puedeDepositarDineroDeLaMonedaCorrespondienteEnUnaCuentaHabilitada() throws Exception {
		int CBU = cuentas.keySet().iterator().next();
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);
		Dinero saldo = cuenta.getSaldo();
		
		CuentaDeCliente<?> result = getObject().depositar(CBU, new Dinero(cuenta.getMoneda(), 1000));
		
		assertEquals(cuenta, result);
		assertTrue(saldo.compareTo(result.getSaldo()) < 0);
		
	}
	
	@Test(expected=Exception.class)
	public void noPuedeDepositarEnCuentasInactivas() throws Exception {
		int CBU = cuentas.keySet().iterator().next();
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);

		cuenta.inactivar();
		
		getObject().depositar(CBU, new Dinero(cuenta.getMoneda(), 1000));
	}

	@Test(expected=Exception.class)
	public void noPuedeDepositarDineroDeMonedaDiferenteAComoEstaNominadaLaCuenta() throws Exception {
		int CBU = cuentas.keySet().iterator().next();
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);

		Moneda denominacion;
		
		if (cuenta.getMoneda() instanceof Peso) {
			denominacion = Moneda.DOLAR;
		} else {
			denominacion = Moneda.PESO;
		}
		
		getObject().depositar(CBU, new Dinero(denominacion, 1000));
	}
	
	@Test
	public void puedeExtraerEfectivoDeUnaCajaDeAhorroHabilitada() throws Exception {
		int CBU = 1;
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);
		Dinero saldo = cuenta.getSaldo();

		CajaDeAhorro result = getObject().extraer(CBU, new Dinero(cuenta.getMoneda(), 10));
		
		assertEquals(cuenta, result);
		assertTrue(result.getSaldo().compareTo(saldo) < 0);
	}
	
	@Test(expected=Exception.class)
	public void noPuedeExtraerEfectivoDeUnaCajaDeAhorroInactiva() throws Exception {
		int CBU = 1;
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);

		cuenta.inactivar();
		
		getObject().depositar(CBU, new Dinero(cuenta.getMoneda(), 1000));
	}
	
	@Test(expected=Exception.class)
	public void noPuedeExtraerMasDelSaldoDisponibleDeUnaCajaDeAhorro() throws Exception {
		int CBU = 1;
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);
		Dinero saldo = cuenta.getSaldo();

		getObject().extraer(CBU, saldo.sumar(10));
	}
	
	@Test
	public void puedeTransferirDineroEntreDosCuentasHabilitadas() throws Exception {
		Iterator<Integer> it = cuentas.keySet().iterator();
		
		int origen = it.next();
		int destino = it.next();
		
		CuentaDeCliente<?> cuentaOrigen = cuentas.get(origen);
		CuentaDeCliente<?> cuentaDestino = cuentas.get(destino);
		
		Dinero saldoOrigen = cuentaOrigen.getSaldo();
		Dinero saldoDestino = cuentaDestino.getSaldo();
		
		getObject().transferir(origen, destino, cuentaOrigen.getSaldo());
		
		assertTrue(cuentaOrigen.getSaldo().compareTo(saldoOrigen) < 0);
		assertTrue(cuentaDestino.getSaldo().compareTo(saldoDestino) > 0);
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTransferirDineroDesdeCuentasInactivas() throws Exception {
		Iterator<Integer> it = cuentas.keySet().iterator();
		
		int origen = it.next();
		int destino = it.next();
		
		CuentaDeCliente<?> cuentaOrigen = cuentas.get(origen);
		cuentaOrigen.inactivar();
		
		getObject().transferir(origen, destino, new Dinero(Moneda.PESO, 5));
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTransferirDineroHaciaCuentasInactivas() throws Exception {
		Iterator<Integer> it = cuentas.keySet().iterator();
		
		int origen = it.next();
		int destino = it.next();
		
		CuentaDeCliente<?> cuentaDestino = cuentas.get(destino);
		cuentaDestino.inactivar();
		
		getObject().transferir(origen, destino, new Dinero(Moneda.PESO, 5));
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTransferirMasDineroDelSaldoQueTengaLaCuentaDeOrigen() throws Exception {
		Iterator<Integer> it = cuentas.keySet().iterator();
		
		int origen = it.next();
		int destino = it.next();
		
		CuentaDeCliente<?> cuentaOrigen = cuentas.get(origen);
		
		getObject().transferir(origen, destino, cuentaOrigen.getSaldo().sumar(1));
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTransferirDineroSiElSaldoNoAlcanzaAdemasParaCobrarLosImpuestosCorrespondientes() throws Exception {
		Iterator<Integer> it = cuentas.keySet().iterator();
		
		int origen = 2;
		int destino = it.next();
		
		CuentaCorriente cuentaOrigen = (CuentaCorriente) cuentas.get(origen);
		
		getObject().transferir(origen, destino, cuentaOrigen.getSaldo().sumar(cuentaOrigen.getSobregiro()));
	}
	
	@Test
	public void debeConvertirLaMonedaAlTipoDeDestinoUsandoElCambioVigente() throws Exception {
		fail("Not yet implemented");
	}
	
	@Test
	public void debeRegistrarLaMonedaElMontoYElTipoDeCambioEnElCampoDeObservacionesSiHuboUnaConversion() throws Exception {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeListarTodosLosMovimientosDeUnaCuenta() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cuentas.keySet().iterator().next());
		
		assertEquals(
			cuenta.getTransacciones(), 
			getObject().movimientos(cuenta.getCBU())
		);
	}
	
	@Test
	public void puedeListarLosUltimosNMovimientosDeUnaCuenta() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cuentas.keySet().iterator().next());
		cuenta.depositar(new Dinero(cuenta.getMoneda(), 3));
		cuenta.depositar(new Dinero(cuenta.getMoneda(), 3));
		cuenta.depositar(new Dinero(cuenta.getMoneda(), 3));
		cuenta.depositar(new Dinero(cuenta.getMoneda(), 3));
		
		Set<Transaccion> expected = new HashSet<>();
		
		int i = 0;
		
		Iterator<Transaccion> it = cuenta.getTransacciones().iterator();
		
		while (i < 3 && it.hasNext()) {
			expected.add(it.next());
			i++;
		}
			
		assertEquals(
			expected,
			getObject().movimientos(cuenta.getCBU(), 3)
		);
	}
}

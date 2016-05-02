import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;

public class VentanillaTest extends TrabajoPracticoTest<Ventanilla> {
	private Map<Integer, CuentaDeCliente<?>> cuentas;
	
	private int cbuCajaDeAhorroPesos;
	private int cbuCajaDeAhorroDolares;
	private int cbuCuentaCorriente;
	
	@Before
	public void setUp() throws Exception {
		cuentas = new HashMap<>();
		
		CajaDeAhorro cajaDeAhorroPesos = CuentasFixture.cajaDeAhorroPesos();
		CajaDeAhorro cajaDeAhorroDolares = CuentasFixture.cajaDeAhorroDolares();
		CuentaCorriente cuentaCorriente = CuentasFixture.cuentaCorriente();
		
		cbuCajaDeAhorroPesos = cajaDeAhorroPesos.getCBU();
		cbuCajaDeAhorroDolares = cajaDeAhorroDolares.getCBU();
		cbuCuentaCorriente = cuentaCorriente.getCBU();
		
		cuentas.put(cbuCajaDeAhorroPesos, cajaDeAhorroPesos);
		cuentas.put(cbuCajaDeAhorroDolares, cajaDeAhorroDolares);
		cuentas.put(cbuCuentaCorriente, cuentaCorriente);
	}
	
	@Override
	public Ventanilla getObject() throws Exception {
		return new Ventanilla(cuentas);
	}

	@Test
	public void puedeDepositarDineroDeLaMonedaCorrespondienteEnUnaCuentaHabilitada() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cbuCajaDeAhorroPesos);
		Dinero saldo = cuenta.getSaldo();
		
		getObject().depositar(cbuCajaDeAhorroPesos, new Dinero(cuenta.getMoneda(), 1000));
		
		assertEquals(saldo.toString() + " dice ser mayor que " + cuenta.getSaldo().toString(),
			-1, saldo.compareTo(cuenta.getSaldo()));
		
	}
	
	@Test(expected=Exception.class)
	public void noPuedeDepositarEnCuentasInactivas() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cbuCajaDeAhorroPesos);

		cuenta.inactivar();
		
		getObject().depositar(cbuCajaDeAhorroPesos, new Dinero(cuenta.getMoneda(), 1000));
	}

	@Test(expected=Exception.class)
	public void noPuedeDepositarDineroDeMonedaDiferenteAComoEstaNominadaLaCuenta() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cbuCajaDeAhorroPesos);

		Moneda denominacion;
		
		if (cuenta.getMoneda() instanceof Peso) {
			denominacion = Moneda.DOLAR;
		} else {
			denominacion = Moneda.PESO;
		}
		
		getObject().depositar(cbuCajaDeAhorroPesos, new Dinero(denominacion, 1000));
	}
	
	@Test
	public void puedeExtraerEfectivoDeUnaCajaDeAhorroHabilitada() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cbuCajaDeAhorroPesos);
		Dinero saldo = cuenta.getSaldo();

		getObject().extraer(cbuCajaDeAhorroPesos, new Dinero(cuenta.getMoneda(), 1));
		
		assertTrue(cuenta.getSaldo().compareTo(saldo) < 0);
	}
	
	@Test(expected=Exception.class)
	public void noPuedeExtraerEfectivoDeUnaCajaDeAhorroInactiva() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cbuCajaDeAhorroPesos);

		cuenta.inactivar();
		
		getObject().depositar(cbuCajaDeAhorroPesos, new Dinero(cuenta.getMoneda(), 1000));
	}
	
	@Test(expected=Exception.class)
	public void noPuedeExtraerMasDelSaldoDisponibleDeUnaCajaDeAhorro() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cbuCajaDeAhorroPesos);
		Dinero saldo = cuenta.getSaldo();

		getObject().extraer(cbuCajaDeAhorroPesos, saldo.sumar(10));
	}
	
	@Test
	public void puedeTransferirDineroEntreDosCuentasHabilitadas() throws Exception {
		CuentaDeCliente<?> cuentaOrigen = cuentas.get(cbuCajaDeAhorroPesos);
		CuentaDeCliente<?> cuentaDestino = cuentas.get(cbuCuentaCorriente);
		
		Dinero saldoOrigen = cuentaOrigen.getSaldo();
		Dinero saldoDestino = cuentaDestino.getSaldo();
		
		getObject().transferir(cbuCajaDeAhorroPesos, cbuCuentaCorriente, cuentaOrigen.getSaldo());
		
		assertTrue(cuentaOrigen.getSaldo().compareTo(saldoOrigen) < 0);
		assertTrue(cuentaDestino.getSaldo().compareTo(saldoDestino) > 0);
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTransferirDineroDesdeCuentasInactivas() throws Exception {
		CuentaDeCliente<?> cuentaOrigen = cuentas.get(cbuCajaDeAhorroPesos);
		cuentaOrigen.inactivar();
		
		getObject().transferir(cbuCajaDeAhorroPesos, cbuCuentaCorriente, new Dinero(Moneda.PESO, 5));
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTransferirDineroHaciaCuentasInactivas() throws Exception {
		CuentaDeCliente<?> cuentaDestino = cuentas.get(cbuCuentaCorriente);
		cuentaDestino.inactivar();
		
		getObject().transferir(cbuCajaDeAhorroPesos, cbuCuentaCorriente, new Dinero(Moneda.PESO, 5));
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTransferirMasDineroDelSaldoQueTengaLaCuentaDeOrigen() throws Exception {
		CuentaDeCliente<?> cuentaOrigen = cuentas.get(cbuCajaDeAhorroPesos);
		
		getObject().transferir(cbuCajaDeAhorroPesos, cbuCuentaCorriente, cuentaOrigen.getSaldo().sumar(1));
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTransferirDineroSiElSaldoNoAlcanzaAdemasParaCobrarLosImpuestosCorrespondientes() throws Exception {
		int origen = cbuCuentaCorriente;
		int destino = cbuCajaDeAhorroPesos;
		
		CuentaCorriente cuentaOrigen = (CuentaCorriente) cuentas.get(origen);
		
		getObject().transferir(origen, destino, cuentaOrigen.getSaldo().sumar(cuentaOrigen.getSobregiro()));
	}
	
	@Test
	public void debeConvertirLaMonedaAlTipoDeDestinoUsandoElCambioVigente() throws Exception {
		CuentaDeCliente<?> cuentaPesos = cuentas.get(cbuCajaDeAhorroPesos);
		Dinero saldoPesos = cuentaPesos.getSaldo();
		
		CuentaDeCliente<?> cuentaDolares = cuentas.get(cbuCajaDeAhorroDolares);
		Dinero saldoDolares = cuentaDolares.getSaldo();
		
		Dinero monto = new Dinero(Moneda.PESO, 100);
		
		getObject().transferir(cbuCajaDeAhorroPesos, cbuCajaDeAhorroDolares, monto);
		
		assertTrue(cuentaPesos.getSaldo().compareTo(saldoPesos) < 0);
		assertTrue(cuentaDolares.getSaldo().compareTo(saldoDolares) > 0);
	}
	
	@Test
	public void debeRegistrarLaMonedaElMontoYElTipoDeCambioEnElCampoDeObservacionesSiHuboUnaConversion() throws Exception {
		CuentaDeCliente<?> cuentaPesos = cuentas.get(cbuCajaDeAhorroPesos);
		
		Dinero monto = new Dinero(Moneda.PESO, 100);
		
		getObject().transferir(cbuCajaDeAhorroPesos, cbuCajaDeAhorroDolares, monto);
		
		Transaccion debito = cuentaPesos.getTransacciones().firstElement();
		assertThat(debito.getObservaciones(), new StringContains("Conversión de moneda"));
		assertThat(
			debito.getObservaciones(), 
			new StringContains("Cambio vigente: " + String.valueOf(Banco.instance().getCambioVigente()))
		);
		assertThat(
			debito.getObservaciones(),
			new StringContains(monto.toString())
		);
	}
	
	@Test
	public void puedeListarTodosLosMovimientosDeUnaCuenta() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cbuCajaDeAhorroPesos);
		
		assertEquals(
			cuenta.getTransacciones(), 
			getObject().movimientos(cuenta.getCBU())
		);
	}
	
	@Test
	public void puedeListarLosUltimosNMovimientosDeUnaCuenta() throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(cbuCajaDeAhorroPesos);
		cuenta.depositar(new Dinero(cuenta.getMoneda(), 3));
		cuenta.depositar(new Dinero(cuenta.getMoneda(), 3));
		cuenta.depositar(new Dinero(cuenta.getMoneda(), 3));
		cuenta.depositar(new Dinero(cuenta.getMoneda(), 3));
		
		Stack<Transaccion> expected = new Stack<>();
		
		int i = 0;
		
		Iterator<Transaccion> it = cuenta.getTransacciones().iterator();
		
		while (i < 3 && it.hasNext()) {
			expected.add(it.next());
			i++;
		}
			
		assertEquals(
			expected,
			getObject().movimientos(cbuCajaDeAhorroPesos, 3)
		);
	}
}

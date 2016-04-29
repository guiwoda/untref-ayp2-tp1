import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Ventanilla {
	private Map<Integer, CuentaDeCliente<?>> cuentas;
	
	public Ventanilla(Map<Integer, CuentaDeCliente<?>> cuentas) {
		this.cuentas = cuentas;
	}
	
	public CuentaDeCliente<?> depositar(int CBU, Dinero monto) throws Exception {
		CuentaDeCliente<?> cuenta = get(CBU);
		
		cuenta.depositar(monto);
		
		return cuenta;
	}

	public CajaDeAhorro extraer(int CBU, Dinero monto) throws Exception {
		CuentaDeCliente<?> cuenta = get(CBU);
		
		if (! (cuenta instanceof CajaDeAhorro)) {
			throw new Exception("Solo se puede extraer de una caja de ahorro.");
		}
		
		cuenta.extraer(monto);
		
		return (CajaDeAhorro) cuenta;
	}
	
	public void transferir(int origen, int destino, Dinero monto) throws Exception {
		CuentaDeCliente<?> cuentaOrigen = get(origen);
		CuentaDeCliente<?> cuentaDestino = get(destino);
		
		cuentaOrigen.transferir(cuentaDestino, monto);
	}
	
	private CuentaDeCliente<?> get(int CBU) throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);
		
		if (cuenta == null) {
			throw new Exception("No se encontró la cuenta con el CBU: " + String.valueOf(CBU));
		}
		
		return cuenta;
	}

	public Set<Transaccion> movimientos(int cbu) throws Exception {
		return get(cbu).getTransacciones();
	}

	public Set<Transaccion> movimientos(int cbu, int cantidad) throws Exception {
		Set<Transaccion> result = new HashSet<>();
		Iterator<Transaccion> it = get(cbu).getTransacciones().iterator();
		
		int i = 0;
		while (i < cantidad && it.hasNext()) {
			result.add(it.next());
			i++;
		}
		
		return result;
	}
}

import java.util.Map;

public class Ventanilla {
	private Map<Integer, CuentaDeCliente<?>> cuentas;
	
	public Ventanilla(Map<Integer, CuentaDeCliente<?>> cuentas) {
		this.cuentas = cuentas;
	}
	
	public CuentaDeCliente<?> depositar(int CBU, Dinero monto) throws Exception {
		CuentaDeCliente<?> cuenta = cuentas.get(CBU);
		
		if (cuenta == null) {
			throw new Exception("No se encontró la cuenta con el CBU: " + String.valueOf(CBU));
		}
		
		cuenta.depositar(monto);
		
		return cuenta;
	}
}

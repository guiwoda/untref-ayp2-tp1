import java.util.Date;
import java.util.Set;

public class CuentaDeCliente<C extends Cliente> extends Cuenta {

	protected boolean			activa	= true;
	protected final Dinero	mantenimiento;
	protected final Set<C>		titulares;

	public CuentaDeCliente(int CBU, Dinero deposito, Dinero mantenimiento, Set<C> titulares) throws Exception {
		super(CBU, deposito.getMoneda());

		if (deposito.isNegativo()) {
			throw new Exception("Una cuenta no puede iniciar con un depósito negativo.");
		}

		if (titulares.isEmpty()) {
			throw new Exception("Una cuenta requiere al menos un cliente asociado.");
		}
		
		for (C titular : titulares) {
			titular.addCuenta(this);
		}

		this.mantenimiento = mantenimiento;
		this.titulares = titulares;

		depositar(deposito);
	}
	
	@Override
	public Dinero depositar(Dinero dinero, String observaciones) throws Exception {
		if (!activa) {
			throw new Exception("Una cuenta inactiva no puede acreditar dinero.");
		}
		
		return super.depositar(dinero, observaciones);
	}
	
	public Dinero extraer(Dinero dinero) throws Exception {
		return extraer(dinero, "");
	}
	
	public Dinero extraer(Dinero dinero, String observaciones) throws Exception {
		if (!activa) {
			throw new Exception("Una cuenta inactiva no puede extraer dinero.");
		}
		
		transacciones.add(Transaccion.debito(new Date(), dinero, "", observaciones));
		
		saldo = calcularSaldo();

		return saldo;
	}
	
	public void inactivar() {
		activa = false;
	}
	
	public Dinero getCostoDeMantenimiento() {
		return mantenimiento;
	}
	
	public Set<C> getTitulares() {
		return titulares;
	}
	
	public void transferir(Cuenta otra, Dinero monto) throws Exception {
		Dinero transferencia = monto;
		String observaciones = "";
		
		if (!otra.getMoneda().equals(getMoneda())) {
			double cambioVigente = Banco.instance().getCambioVigente();
			
			if (otra.getMoneda().equals(Moneda.PESO)) {
				transferencia = monto.convertir(Moneda.PESO, cambioVigente);
			} else {
				transferencia = monto.convertir(Moneda.DOLAR, 1 / cambioVigente);
			}
			
			observaciones = new StringBuilder()
				.append("Conversión de moneda").append("\n")
				.append(monto.toString()).append("\n")
				.append("Cambio vigente: ").append(Banco.instance().getCambioVigente())
				.toString();
		}
		
		extraer(monto, observaciones);
		otra.depositar(transferencia);
	}
}

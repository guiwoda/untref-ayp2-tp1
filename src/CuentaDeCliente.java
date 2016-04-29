import java.util.Date;
import java.util.Set;

public class CuentaDeCliente<M extends Moneda, C extends Cliente> extends Cuenta<M> {

	protected boolean			activa	= true;
	protected final Dinero<M>	mantenimiento;
	protected final Set<C>		titulares;

	public CuentaDeCliente(int CBU, Dinero<M> deposito, Dinero<M> mantenimiento, Set<C> titulares) throws Exception {
		super(CBU, deposito.getMoneda());

		if (deposito.isNegativo()) {
			throw new Exception("Una cuenta no puede iniciar con un depósito negativo.");
		}

		if (titulares.isEmpty()) {
			throw new Exception("Una cuenta requiere al menos un cliente asociado.");
		}

		this.mantenimiento = mantenimiento;
		this.titulares = titulares;

		depositar(deposito);
	}
	
	@Override
	public Dinero<M> depositar(Dinero<M> dinero) throws Exception {
		if (!activa) {
			throw new Exception("Una cuenta inactiva no puede acreditar dinero.");
		}
		
		return super.depositar(dinero);
	}
	
	@SuppressWarnings("unchecked")
	public Dinero<M> extraer(Dinero<M> dinero) throws Exception {
		if (!activa) {
			throw new Exception("Una cuenta inactiva no puede extraer dinero.");
		}
		
		transacciones.add(Transaccion.debito(new Date(), (Dinero<Moneda>) dinero, ""));
		
		saldo = calcularSaldo();

		return saldo;
	}
	
	public void inactivar() {
		activa = false;
	}
	
	public Dinero<M> getCostoDeMantenimiento() {
		return mantenimiento;
	}
	
	public Set<C> getTitulares() {
		return titulares;
	}
	
	public void transferir(Cuenta<M> otra, Dinero<M> monto) throws Exception {
		extraer(monto);
		otra.depositar(monto);
	}
}

import java.util.HashSet;
import java.util.Set;

abstract class Cuenta<M extends Moneda, C extends Cliente> {

	protected int					CBU;
	protected Dinero<M>				saldo;
	protected Set<Transaccion<M>>	transacciones;
	protected boolean				activa	= true;
	protected final Dinero<M>		mantenimiento;
	protected final Set<C>			titulares;

	public Cuenta(int CBU, Dinero<M> deposito, Dinero<M> mantenimiento, Set<C> titulares) throws Exception {
		if (deposito.isNegativo()) {
			throw new Exception("Una cuenta no puede iniciar con un depósito negativo.");
		}

		if (titulares.isEmpty()) {
			throw new Exception("Una caja de ahorro requiere al menos un cliente asociado.");
		}

		this.CBU = CBU;
		this.transacciones = new HashSet<>();
		this.saldo = new Dinero<M>(deposito.getMoneda(), 0);
		this.mantenimiento = mantenimiento;
		this.titulares = titulares;

		depositar(deposito);
	}

	public Dinero<M> depositar(Dinero<M> dinero) throws Exception {
		if (!activa) {
			throw new Exception("Una cuenta inactiva no puede acreditar dinero.");
		}

		saldo = saldo.sumar(dinero);
		transacciones.add(new Transaccion<M>(dinero));

		return saldo;
	}

	public Dinero<M> extraer(Dinero<M> dinero) throws Exception {
		if (!activa) {
			throw new Exception("Una cuenta inactiva no puede extraer dinero.");
		}

		saldo = saldo.restar(dinero);
		transacciones.add(new Transaccion<M>(dinero.invertir()));

		return saldo;
	}

	public int getCBU() {
		return CBU;
	}

	public Dinero<M> getSaldo() {
		return saldo;
	}

	public Set<Transaccion<M>> getTransacciones() {
		return transacciones;
	}

	public void inactivar() {
		activa = false;
	}

	public M getDenominacion() {
		return saldo.getMoneda();
	}

	public Dinero<M> getCostoDeMantenimiento() {
		return mantenimiento;
	}
	
	public Set<C> getTitulares() {
		return titulares;
	}
	
	public void transferir(Cuenta<M, ?> otra, Dinero<M> monto) throws Exception {
		saldo = saldo.restar(monto);
		otra.saldo = otra.saldo.sumar(monto);
	}
}

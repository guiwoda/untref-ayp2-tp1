import java.util.HashSet;
import java.util.Set;

abstract class Cuenta<T extends Moneda> {

	protected int					CBU;
	protected Dinero<T>				saldo;
	protected Set<Transaccion<T>>	transacciones;
	protected boolean				estado = true;

	public Cuenta(int CBU, Dinero<T> saldo) throws Exception {
		if (saldo.isNegativo()) {
			throw new Exception("Una cuenta no puede iniciar con saldo negativo.");
		}

		this.CBU = CBU;
		this.transacciones = new HashSet<>();
		this.saldo = new Dinero<T>(saldo.getMoneda(), 0);
		
		depositar(saldo);
	}

	public Dinero<T> depositar(Dinero<T> dinero) throws Exception {
		if (!estado) {
			throw new Exception("Una cuenta inactiva no puede acreditar dinero.");
		}

		saldo = saldo.sumar(dinero);
		transacciones.add(new Transaccion<T>(dinero));

		return saldo;
	}

	public Dinero<T> extraer(Dinero<T> dinero) throws Exception {
		if (!estado) {
			throw new Exception("Una cuenta inactiva no puede extraer dinero.");
		}

		saldo = saldo.restar(dinero);
		transacciones.add(new Transaccion<T>(dinero.invertir()));

		return saldo;
	}

	public int getCBU() {
		return CBU;
	}

	public Dinero<T> getSaldo() {
		return saldo;
	}

	public Set<Transaccion<T>> getTransacciones() {
		return transacciones;
	}

	public void inactivar() {
		estado = false;
	}
}

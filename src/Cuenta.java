import java.util.HashSet;
import java.util.Set;

abstract class Cuenta<M extends Moneda> {

	protected int					CBU;
	protected Dinero<M>				saldo;
	protected Set<Transaccion<M>>	transacciones;

	public Cuenta(int CBU, M moneda) throws Exception {
		this.CBU = CBU;
		this.transacciones = new HashSet<>();
		this.saldo = new Dinero<M>(moneda, 0);
	}

	public Dinero<M> depositar(Dinero<M> dinero) throws Exception {
		saldo = saldo.sumar(dinero);
		transacciones.add(new Transaccion<M>(dinero));

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

	public M getDenominacion() {
		return saldo.getMoneda();
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("CBU: ")
			.append(CBU)
			.append("\n")
			.append("Saldo: ")
			.append(this.saldo.toString())
			.toString();
	}
}

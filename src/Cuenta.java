import java.util.Date;
import java.util.HashSet;
import java.util.Set;

abstract class Cuenta<M extends Moneda> implements Comparable<Cuenta> {

	protected int				CBU;
	protected Dinero<M>			saldo;
	protected Set<Transaccion>	transacciones;

	public Cuenta(int CBU, M moneda) throws Exception {
		this.CBU = CBU;
		this.transacciones = new HashSet<Transaccion>();
		this.saldo = new Dinero<M>(moneda, 0);
	}

	@SuppressWarnings("unchecked")
	public Dinero<M> depositar(Dinero<M> dinero) throws Exception {
		transacciones.add(Transaccion.credito(new Date(), (Dinero<Moneda>) dinero, ""));
		
		saldo = calcularSaldo();
		
		return saldo;
	}

	@SuppressWarnings("unchecked")
	protected Dinero<M> calcularSaldo() throws Exception {
		Dinero<M> resultado = new Dinero<M>(saldo.getMoneda(), 0);
		
		for (Transaccion transaccion : transacciones) {
			resultado = (Dinero<M>) transaccion.aplicar((Dinero<Moneda>) resultado);
		}
		
		return resultado;
	}

	public int getCBU() {
		return CBU;
	}

	public Dinero<M> getSaldo() {
		return saldo;
	}

	public Set<Transaccion> getTransacciones() {
		return transacciones;
	}

	public M getDenominacion() {
		return saldo.getMoneda();
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("CBU: ").append(CBU).append("\n")
			.append("Saldo: ").append(saldo.toString()).append("\n")
			.toString();
	}


	@Override
	public int compareTo(Cuenta o) {
		if (CBU == o.CBU) {
			return 0;
		}

		return CBU < o.CBU ? -1 : 1;
	}
}

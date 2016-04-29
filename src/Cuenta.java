import java.util.Date;
import java.util.HashSet;
import java.util.Set;

abstract class Cuenta implements Comparable<Cuenta> {

	protected int				CBU;
	protected Dinero			saldo;
	protected Set<Transaccion>	transacciones;

	public Cuenta(int CBU, Moneda moneda) throws Exception {
		this.CBU = CBU;
		this.transacciones = new HashSet<Transaccion>();
		this.saldo = new Dinero(moneda, 0);
	}

	public Dinero depositar(Dinero dinero) throws Exception {
		if (! dinero.getMoneda().equals(saldo.getMoneda())) {
			throw new Exception("No se puede depositar en una moneda diferente a la configurada en la cuenta.");
		}
		
		transacciones.add(Transaccion.credito(new Date(), dinero, ""));
		
		saldo = calcularSaldo();
		
		return saldo;
	}

	protected Dinero calcularSaldo() throws Exception {
		Dinero resultado = new Dinero(saldo.getMoneda(), 0);
		
		for (Transaccion transaccion : transacciones) {
			resultado = (Dinero) transaccion.aplicar(resultado);
		}
		
		return resultado;
	}

	public int getCBU() {
		return CBU;
	}

	public Dinero getSaldo() {
		return saldo;
	}

	public Set<Transaccion> getTransacciones() {
		return transacciones;
	}

	public Moneda getDenominacion() {
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

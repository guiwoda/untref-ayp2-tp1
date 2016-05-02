import java.util.Date;
import java.util.Stack;

abstract class Cuenta implements Comparable<Cuenta> {

	protected int					CBU;
	protected Dinero				saldo;
	protected Stack<Transaccion>	transacciones;

	public Cuenta(int CBU, Moneda moneda) throws Exception {
		this.CBU = CBU;
		this.transacciones = new Stack<Transaccion>();
		this.saldo = new Dinero(moneda, 0);
	}

	public Dinero depositar(Dinero dinero) throws Exception {
		return depositar(dinero, "");
	}
	
	public Dinero depositar(Dinero dinero, String observaciones) throws Exception {
		if (!dinero.getMoneda().equals(saldo.getMoneda())) {
			throw new Exception("No se puede depositar en una moneda diferente a la configurada en la cuenta.");
		}

		transacciones.add(Transaccion.credito(new Date(), dinero, "", observaciones));

		saldo = calcularSaldo();

		return saldo;
	}

	protected Dinero calcularSaldo() throws Exception {
		Dinero resultado = new Dinero(saldo.getMoneda(), 0);

		for (Transaccion transaccion : transacciones) {
			resultado = transaccion.aplicar(resultado);
		}

		return resultado;
	}

	public int getCBU() {
		return CBU;
	}

	public Dinero getSaldo() {
		return saldo;
	}

	public Stack<Transaccion> getTransacciones() {
		return transacciones;
	}

	public Moneda getMoneda() {
		return saldo.getMoneda();
	}

	@Override
	public String toString() {
		return new StringBuilder().append("CBU: ").append(CBU).append("\n").append("Saldo: ").append(saldo.toString()).append("\n").toString();
	}

	@Override
	public int compareTo(Cuenta o) {
		if (CBU == o.CBU) {
			return 0;
		}

		return CBU < o.CBU ? -1 : 1;
	}
}

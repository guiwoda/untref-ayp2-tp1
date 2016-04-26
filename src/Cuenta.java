import java.util.Set;

abstract class Cuenta<T extends Moneda> {
	
	protected int CBU;
	protected Dinero<T> saldo;
	protected Set<Transaccion> transacciones;
	protected boolean estado;
	
	public Cuenta(int CBU, Dinero<T> saldo, boolean estado) throws Exception {
		if (saldo.isNegativo()) {
			throw new Exception(
				"Una cuenta no puede iniciar con saldo negativo."
			);
		}
		
		this.CBU = CBU;
		this.saldo = saldo;
		this.estado = estado;
	}
	
	public Cuenta(int CBU) throws Exception {
		this(CBU, new Dinero<T>(0, 0), true);
	}
	
	public Dinero<T> depositar(Dinero<T> dinero) {
		saldo = saldo.sumar(dinero);
		
		return saldo;
	}
	
	public Dinero<T> extraer(Dinero<T> dinero) {
		saldo = saldo.restar(dinero);
		
		return saldo;
	}

	public int getCBU() {
		return CBU;
	}

	public Dinero<T> getSaldo() {
		return saldo;
	}

	public Set<Transaccion> getTransacciones() {
		return transacciones;
	}
}

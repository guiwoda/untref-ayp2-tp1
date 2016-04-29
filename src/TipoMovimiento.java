public abstract class TipoMovimiento {
	public static final TipoMovimiento DEBITO = new Debito();
	public static final TipoMovimiento CREDITO = new Credito();
	
	private final String tipo;
	
	abstract public Dinero<Moneda> aplicar(Dinero<Moneda> saldo, Dinero<Moneda> monto) throws Exception;
	
	protected TipoMovimiento(String tipo){
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof TipoMovimiento && equals((TipoMovimiento) obj);
	}

	public boolean equals(TipoMovimiento obj) {
		return tipo.equals(obj.tipo);
	}
	
	@Override
	public int hashCode() {
		return tipo.hashCode();
	}
	
	private static class Credito extends TipoMovimiento {
		private Credito() {
			super("Crédito");
		}
		
		public Dinero<Moneda> aplicar(Dinero<Moneda> saldo, Dinero<Moneda> monto) throws Exception {
			return saldo.sumar(monto);
		}
	}
	
	private static class Debito extends TipoMovimiento {
		private Debito() {
			super("Débito");
		}
		
		public Dinero<Moneda> aplicar(Dinero<Moneda> saldo, Dinero<Moneda> monto) throws Exception {
			return saldo.restar(monto);
		}
	}
}

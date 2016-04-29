public abstract class TipoMovimiento {
	public static final TipoMovimiento DEBITO = new Debito();
	public static final TipoMovimiento CREDITO = new Credito();
	
	private final String tipo;
	
	abstract public Dinero aplicar(Dinero saldo, Dinero monto) throws Exception;
	
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
		
		public Dinero aplicar(Dinero saldo, Dinero monto) throws Exception {
			return saldo.sumar(monto);
		}
	}
	
	private static class Debito extends TipoMovimiento {
		private Debito() {
			super("Débito");
		}
		
		public Dinero aplicar(Dinero saldo, Dinero monto) throws Exception {
			return saldo.restar(monto);
		}
	}
}

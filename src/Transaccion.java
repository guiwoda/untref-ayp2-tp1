
public class Transaccion<M extends Moneda> {
	private Dinero<M> monto;
	
	public Transaccion(Dinero<M> monto) {
		this.monto = monto;
	}
	
	public Dinero<M> getMonto() {
		return monto;
	}
}

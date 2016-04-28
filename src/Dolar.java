public class Dolar implements Moneda {
	private static final Dinero<Moneda> mantenimientoCajaDeAhorro = new Dinero<Moneda>(new Dolar(), 250);

	@Override
	public String getSimbolo() {
		return "USD";
	}

	@Override
	public Dinero<Moneda> getMantenimientoCajaDeAhorro() {
		return mantenimientoCajaDeAhorro;
	}
}

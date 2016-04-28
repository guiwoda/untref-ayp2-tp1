
public class Peso implements Moneda {
	private static final Dinero<Moneda> mantenimientoCajaDeAhorro = new Dinero<Moneda>(new Peso(), 1500);
	
	@Override
	public String getSimbolo() {
		return "$";
	}
	
	@Override
	public Dinero<Moneda> getMantenimientoCajaDeAhorro() {
		return mantenimientoCajaDeAhorro;
	}
}

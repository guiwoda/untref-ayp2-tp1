
public class Peso implements Moneda {
	private static final int MANTENIMIENTO_CAJA_DE_AHORRO = 1500;
	
	@Override
	public String getSimbolo() {
		return "$";
	}
	
	@Override
	public Dinero<Moneda> getMantenimientoCajaDeAhorro() {
		try {
			return new Dinero<Moneda>(Moneda.PESO, MANTENIMIENTO_CAJA_DE_AHORRO);
		} catch (Exception e) {
			throw new Error();
		}
	}
	
	@Override
	public int hashCode() {
		return 1;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Peso;
	}
}

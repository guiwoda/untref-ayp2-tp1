public class Dolar implements Moneda {
	private static final int	MANTENIMIENTO_CAJA_DE_AHORRO	= 250;
	private static final String	SIMBOLO							= "USD";
	private static final String	DESCRIPCION						= "Dólares estadounidenses";

	@Override
	public String getSimbolo() {
		return SIMBOLO;
	}

	@Override
	public Dinero getMantenimientoCajaDeAhorro() {
		try {
			return new Dinero(this, MANTENIMIENTO_CAJA_DE_AHORRO);
		} catch (Exception e) {
			throw new Error();
		}
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Dolar;
	}
	
	@Override
	public String toString() {
		return DESCRIPCION;
	}
}

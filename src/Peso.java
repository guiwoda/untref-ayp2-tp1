public class Peso implements Moneda {
	private static final int	MANTENIMIENTO_CAJA_DE_AHORRO	= 1500;
	private static final String	SIMBOLO							= "$";
	private static final String	DESCRIPCION						= "Pesos Argentinos";

	@Override
	public final String getSimbolo() {
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
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Peso;
	}

	@Override
	public String toString() {
		return DESCRIPCION;
	}
}

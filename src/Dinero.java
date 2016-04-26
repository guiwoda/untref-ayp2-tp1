public class Dinero<T extends Moneda> implements Comparable<Dinero<T>> {
	private T moneda;
	private int cantidad;
	private int centavos;

	public Dinero(T moneda, int cantidad, int centavos) {
		this.moneda   = moneda;
		this.cantidad = cantidad;
		this.centavos = centavos;
	}

	public Dinero(T moneda, int cantidad) {
		this(moneda, cantidad, 0);
	}

	public boolean isNegativo() {
		return cantidad < 0 || (cantidad == 0 && centavos < 0);
	}

	public Dinero<T> sumar(Dinero<T> dinero) {
		return new Dinero<T>(moneda, cantidad + dinero.cantidad, centavos
				+ dinero.centavos);
	}

	public Dinero<T> restar(Dinero<T> dinero) {
		return new Dinero<T>(moneda, cantidad - dinero.cantidad, centavos
				- dinero.centavos);
	}

	@Override
	public int compareTo(Dinero<T> o) {
		if (cantidad > o.cantidad) {
			return 1;
		}

		if (cantidad < o.cantidad) {
			return -1;
		}

		if (centavos > o.centavos) {
			return 1;
		}

		if (centavos < o.centavos) {
			return -1;
		}

		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;

		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + centavos;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Dinero)) {
			return false;
		}
		
		return equals((Dinero) obj);
	}
	
	public boolean equals(Dinero other) {
		if (this == other) {
			return true;
		}
		
		if (! moneda.equals(other.moneda)) {
			return false;
		}
		
		if (cantidad != other.cantidad) {
			return false;
		}
		
		if (centavos != other.centavos) {
			return false;
		}

		return true;
	}
}

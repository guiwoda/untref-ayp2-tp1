import java.math.BigDecimal;

public class Dinero<T extends Moneda> implements Comparable<Dinero<T>> {
	private T	moneda;
	private int centavos;

	public Dinero(T moneda, int centavos) {
		this.moneda = moneda;
		this.centavos = centavos;
	}
	
	public Dinero(T moneda, float monto) {
		this(moneda, Math.round(monto * 100));
	}

	public Dinero<T> sumar(Dinero<T> dinero) {
		return sumar(dinero.centavos);
	}

	public Dinero<T> restar(Dinero<T> dinero) {
		return restar(dinero.centavos);
	}
	
	public Dinero<T> sumar(int centavos) {
		return new Dinero<T>(moneda, this.centavos + centavos);
	}

	public Dinero<T> restar(int centavos) {
		return new Dinero<T>(moneda, this.centavos - centavos);
	}
	
	public Dinero<T> sumar(float monto) {
		return new Dinero<T>(moneda, (float) this.centavos + monto);
	}

	public Dinero<T> restar(float monto) {
		return new Dinero<T>(moneda, (float) this.centavos - monto);
	}
	
	public Dinero<T> invertir() {
		return new Dinero<T>(moneda, 0).restar(this);
	}
	
	public boolean isNegativo() {
		return centavos < 0;
	}

	public boolean isPositivo() {
		return !isNegativo();
	}
	
	public T getMoneda() {
		return moneda;
	}
	
	public int getCentavos() {
		return centavos;
	}
	
	@Override
	public int compareTo(Dinero<T> o) {
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
		result = prime * result + centavos;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Dinero && equals((Dinero) obj);
	}

	public boolean equals(Dinero other) {
		return moneda.equals(other.moneda) && centavos == other.centavos;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(moneda.getSimbolo())
			.append(" ")
			.append(BigDecimal.valueOf(centavos, 2))
			.toString();
	}
}

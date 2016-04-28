import java.math.BigDecimal;

public class Dinero<T extends Moneda> implements Comparable<Dinero<T>> {
	private T	moneda;
	private int centavos;

	public Dinero(T moneda, int centavos) throws Exception {
		if (moneda == null) {
			throw new Exception("El dinero necesita ser de una moneda específica.");
		}
		
		this.moneda = moneda;
		this.centavos = centavos;
	}
	
	public Dinero(T moneda, float monto) throws Exception {
		this(moneda, Math.round(monto * 100));
	}

	public Dinero<T> sumar(Dinero<T> dinero) throws Exception {
		return sumar(dinero.centavos);
	}

	public Dinero<T> restar(Dinero<T> dinero) throws Exception {
		return restar(dinero.centavos);
	}
	
	public Dinero<T> sumar(int centavos) throws Exception {
		return new Dinero<T>(moneda, this.centavos + centavos);
	}

	public Dinero<T> restar(int centavos) throws Exception {
		return new Dinero<T>(moneda, this.centavos - centavos);
	}
	
	public Dinero<T> sumar(float monto) throws Exception {
		return new Dinero<T>(moneda, (float) this.centavos + monto);
	}

	public Dinero<T> restar(float monto) throws Exception {
		return new Dinero<T>(moneda, (float) this.centavos - monto);
	}
	
	public Dinero<T> invertir() throws Exception {
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
	public int compareTo(Dinero<T> other) {
		if (centavos > other.centavos) {
			return 1;
		}

		if (centavos < other.centavos) {
			return -1;
		}

		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		
		int result = 1;
		result = prime * result + centavos;
		result = prime * result + moneda.hashCode();
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Dinero && equals((Dinero) obj);
	}

	public boolean equals(Dinero other) {
		return 
			moneda.equals(other.moneda) && 
			centavos == other.centavos;
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

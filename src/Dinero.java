import java.math.BigDecimal;

public class Dinero<T extends Moneda> implements Comparable<Dinero<T>> {
	private static final int	FACTOR_DECIMAL	= 100;
	private T					moneda;
	private int					centavos;

	public Dinero(T moneda) throws Exception {
		if (moneda == null) {
			throw new Exception("El dinero necesita ser de una moneda específica.");
		}

		this.moneda = moneda;
	}

	public Dinero(T moneda, int monto) throws Exception {
		this(moneda);

		this.centavos = monto * FACTOR_DECIMAL;
	}

	public Dinero(T moneda, float monto) throws Exception {
		this(moneda);

		this.centavos = Math.round(monto * FACTOR_DECIMAL);
	}

	public Dinero<T> sumar(Dinero<T> dinero) throws Exception {
		return crearConCentavos(centavos + dinero.centavos);
	}

	public Dinero<T> restar(Dinero<T> dinero) throws Exception {
		return crearConCentavos(centavos - dinero.centavos);
	}

	public Dinero<T> sumar(int monto) throws Exception {
		return crearConCentavos(this.centavos + monto * FACTOR_DECIMAL);
	}

	public Dinero<T> restar(int monto) throws Exception {
		return crearConCentavos(this.centavos - monto * FACTOR_DECIMAL);
	}

	public Dinero<T> sumar(float monto) throws Exception {
		return new Dinero<T>(moneda, monto).sumar(this.centavos);
	}

	public Dinero<T> restar(float monto) throws Exception {
		return new Dinero<T>(moneda, -monto).sumar(this.centavos);
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

	public Dinero<T>[] distribuir(int[] partes) throws Exception {
		long total = 0;
		for (int parte : partes) {
			total += parte;
		}

		Dinero<T>[] resultado = new Dinero[partes.length];

		// distribuyo truncando decimales (fracciones de centavos)
		long resto = centavos;
		for (int i = 0; i < resultado.length; i++) {
			resultado[i] = crearConCentavos(centavos * partes[i] / total);
			resto -= resultado[i].centavos;
		}

		// distribuyo equitativamente los centavos que sobraron
		for (int i = 0; i < resto; i++) {
			resultado[i].centavos++;
		}

		return resultado;
	}

	private Dinero<T> crearConCentavos(long centavos) throws Exception {
		Dinero<T> dinero = new Dinero<T>(moneda);
		dinero.centavos = (int) centavos;

		return dinero;
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
		return moneda.equals(other.moneda) && centavos == other.centavos;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(moneda.getSimbolo()).append(" ").append(BigDecimal.valueOf(centavos, 2)).toString();
	}
}

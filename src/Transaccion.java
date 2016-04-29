import java.util.Date;

public class Transaccion implements Comparable<Transaccion> {
	private final Date				fecha;
	private final TipoMovimiento	tipo;
	private final Dinero<Moneda>	monto;
	private final String			motivo;
	private String					observaciones;

	private Transaccion(Date fecha, TipoMovimiento tipo, Dinero<Moneda> monto, String motivo) {
		this.fecha = fecha;
		this.tipo = tipo;
		this.monto = monto;
		this.motivo = motivo;
	}

	public static Transaccion debito(Date fecha, Dinero<Moneda> monto, String motivo) {
		return new Transaccion(fecha, TipoMovimiento.DEBITO, monto, motivo);
	}

	public static Transaccion debito(Date fecha, Dinero<Moneda> monto, String motivo, String observaciones) {
		Transaccion result = Transaccion.debito(fecha, monto, motivo);
		result.observaciones = observaciones;

		return result;
	}

	public static Transaccion credito(Date fecha, Dinero<Moneda> monto, String motivo) {
		return new Transaccion(fecha, TipoMovimiento.CREDITO, monto, motivo);
	}

	public static Transaccion credito(Date fecha, Dinero<Moneda> monto, String motivo, String observaciones) {
		Transaccion result = Transaccion.debito(fecha, monto, motivo);
		result.observaciones = observaciones;

		return result;
	}

	public Dinero<Moneda> getMonto() {
		return monto;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public Date getFecha() {
		return fecha;
	}

	public TipoMovimiento getTipoDeMovimiento() {
		return tipo;
	}

	public String getMotivo() {
		return motivo;
	}
	
	public Dinero<Moneda> aplicar(Dinero<Moneda> saldo) throws Exception {
		return tipo.aplicar(saldo, monto);
	}

	@Override
	public int compareTo(Transaccion o) {
		return fecha.compareTo(o.fecha);
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Transaccion del dia ").append(fecha.toString()).append("\n")
			.append("Tipo: ").append(tipo.toString()).append("\n")
			.append("Monto: ").append(monto.toString()).append("\n")
			.append("Motivo: ").append(motivo).append("\n")
			.append("Observaciones: ").append(observaciones != null ? observaciones : "ninguna").append("\n")
			.toString();
	}
}

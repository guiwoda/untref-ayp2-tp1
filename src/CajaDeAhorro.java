import java.util.Set;

public class CajaDeAhorro<T extends Moneda> extends Cuenta<T> implements Comparable<CajaDeAhorro> {

	private final Set<PersonaFisica>	titulares;
	private final Dinero<T>				mantenimiento;
	private final Dinero<T>				interes;

	@SuppressWarnings("unchecked")
	public CajaDeAhorro(int CBU, Dinero<T> depositoInicial, Set<PersonaFisica> titulares, Dinero<T> interes) throws Exception {
		super(CBU, depositoInicial);

		if (titulares.isEmpty()) {
			throw new Exception("Una caja de ahorro requiere al menos un cliente asociado.");
		}

		this.titulares = titulares;
		this.mantenimiento = (Dinero<T>) getDenominacion().getMantenimientoCajaDeAhorro();
		this.interes = interes;
	}

	public String toString() {
		return "";
	}

	@Override
	public int compareTo(CajaDeAhorro other) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Set<PersonaFisica> getTitulares() {
		return titulares;
	}

	@Override
	public Dinero<T> extraer(Dinero<T> dinero) throws Exception {
		Dinero<T> resultado = saldo.restar(dinero);

		if (resultado.isNegativo()) {
			throw new Exception("Las cajas de ahorro no pueden tener saldo negativo.");
		}

		return super.extraer(dinero);
	}

	public Dinero<T> getCostoDeMantenimiento() {
		return mantenimiento;
	}

	public T getDenominacion() {
		return saldo.getMoneda();
	}

	public void transferir(Cuenta<T> otra, Dinero<T> monto) {
		saldo = saldo.restar(monto);
		otra.saldo = otra.saldo.sumar(monto);

	}

	public Dinero<T> getInteres() {
		return interes;
	}
}

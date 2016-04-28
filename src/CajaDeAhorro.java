import java.util.Set;

public class CajaDeAhorro<M extends Moneda> extends CuentaDeCliente<M, PersonaFisica> implements Comparable<CajaDeAhorro<M>> {

	private final Dinero<M> interes;

	@SuppressWarnings("unchecked")
	public CajaDeAhorro(int CBU, Dinero<M> deposito, Set<PersonaFisica> titulares, Dinero<M> interes) throws Exception {
		super(
			CBU, 
			deposito, 
			(Dinero<M>) deposito.getMoneda().getMantenimientoCajaDeAhorro(),
			titulares
		);
		
		this.interes = interes;
	}

	public String toString() {
		return "";
	}

	@Override
	public int compareTo(CajaDeAhorro<M> other) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Set<PersonaFisica> getTitulares() {
		return titulares;
	}

	@Override
	public Dinero<M> extraer(Dinero<M> dinero) throws Exception {
		Dinero<M> resultado = saldo.restar(dinero);

		if (resultado.isNegativo()) {
			throw new Exception("Las cajas de ahorro no pueden tener saldo negativo.");
		}

		return super.extraer(dinero);
	}

	public Dinero<M> getCostoDeMantenimiento() {
		return mantenimiento;
	}

	public Dinero<M> getInteres() {
		return interes;
	}
}

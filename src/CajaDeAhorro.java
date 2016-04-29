import java.util.Set;

public class CajaDeAhorro extends CuentaDeCliente<PersonaFisica> {

	private final Dinero interes;

	public CajaDeAhorro(int CBU, Dinero deposito, Set<PersonaFisica> titulares, Dinero interes) throws Exception {
		super(
			CBU, 
			deposito, 
			(Dinero) deposito.getMoneda().getMantenimientoCajaDeAhorro(),
			titulares
		);
		
		this.interes = interes;
	}

	public String toString() {
		return new StringBuilder()
			.append("Caja de ahorro")
			.append("\n")
			.append(super.toString())
			.toString();
	}

	public Set<PersonaFisica> getTitulares() {
		return titulares;
	}

	@Override
	public Dinero extraer(Dinero dinero) throws Exception {
		Dinero resultado = saldo.restar(dinero);

		if (resultado.isNegativo()) {
			throw new Exception("Las cajas de ahorro no pueden tener saldo negativo.");
		}

		return super.extraer(dinero);
	}

	public Dinero getCostoDeMantenimiento() {
		return mantenimiento;
	}

	public Dinero getInteres() {
		return interes;
	}
}

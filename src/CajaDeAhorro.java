import java.util.Set;

public class CajaDeAhorro<T extends Moneda> extends Cuenta<T> implements Comparable<CajaDeAhorro> {
	
	private final Set<PersonaFisica> titulares;
	
	public CajaDeAhorro(int CBU, Set<PersonaFisica> titulares) throws Exception {
		super(CBU);
		
		if (titulares.isEmpty()) {
			throw new Exception("Una caja de ahorro requiere al menos un cliente asociado.");
		}
		
		this.titulares = titulares;
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
}

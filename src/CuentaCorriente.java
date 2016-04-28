import java.util.Set;

public class CuentaCorriente extends Cuenta<Peso, Cliente> implements Comparable<CuentaCorriente> {
	public CuentaCorriente(int CBU, Dinero<Peso> deposito, Set<Cliente> titulares) throws Exception {
		super(
			CBU, 
			deposito, 
			new Dinero<Peso>(deposito.getMoneda(), 0), 
			titulares
		);
	}

	@Override
	public int compareTo(CuentaCorriente o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("CBU: ")
			.append(CBU)
			.append("\n")
			.append("Saldo: ")
			.append(this.saldo.toString())
			.toString();
	}
}

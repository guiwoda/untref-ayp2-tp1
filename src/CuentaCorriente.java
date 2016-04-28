import java.util.Set;

public class CuentaCorriente extends CuentaDeCliente<Peso, Cliente> implements Comparable<CuentaCorriente> {
	
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
			.append("Cuenta Corriente. ")
			.append(super.toString())
			.toString();
	}
	
	@Override
	public Dinero<Peso> depositar(Dinero<Peso> dinero) throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
		
		// Planteo los porcentajes como un array de partes (ej: { 3, 97 }
		int[] partes = { porcentajeComision, 100 - porcentajeComision };
		
		// Distribuyo el dinero a depositar entre la retención y el depósito final
		Dinero<Peso>[] depositos = dinero.distribuir(partes);
		
		// Deposito en la cuenta de retenciones la parte retenida
		Banco.instance().getRetenciones().depositar(depositos[0]);
		
		// Deposito en la cuenta corriente la parte restante
		return super.depositar(depositos[1]);
	}
}

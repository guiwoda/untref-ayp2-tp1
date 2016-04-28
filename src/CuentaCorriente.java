import java.util.Set;

public class CuentaCorriente extends CuentaDeCliente<Peso, Cliente> implements Comparable<CuentaCorriente> {
	
	private Dinero<Peso>	sobregiro;

	public CuentaCorriente(int CBU, Dinero<Peso> deposito, Set<Cliente> titulares, Dinero<Peso> sobregiro) throws Exception {
		super(
			CBU, 
			deposito, 
			new Dinero<Peso>(deposito.getMoneda(), 0), 
			titulares
		);
		
		this.sobregiro = sobregiro;
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
	
	@Override
	public Dinero<Peso> extraer(Dinero<Peso> dinero) throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
		
		// Planteo los porcentajes como un array de partes (ej: { 3, 97 }
		int[] partes = { porcentajeComision, 100 - porcentajeComision };
		
		// Distribuyo el dinero a depositar entre la retención y el depósito final
		Dinero<Peso>[] depositos = dinero.distribuir(partes);
		
		if (saldo.restar(dinero).restar(depositos[0]).compareTo(sobregiro.invertir()) < 0) {
			throw new Exception("No se puede extraer el monto ya que supera el sobregiro habilitado a la cuenta.");
		}
		
		// Deposito en la cuenta de retenciones la parte retenida
		Banco.instance().getRetenciones().depositar(depositos[0]);
		
		return super.extraer(dinero);
	}

	public Dinero<Peso> getSobregiro() {
		return sobregiro;
	}
}

import java.util.Set;

public class CuentaCorriente extends CuentaDeCliente<Cliente> {
	
	private Dinero sobregiro;

	public CuentaCorriente(int CBU, Dinero deposito, Set<Cliente> titulares, Dinero sobregiro) throws Exception {
		super(
			CBU, 
			deposito, 
			new Dinero(deposito.getMoneda(), 0), 
			titulares
		);
		
		if (! (deposito.getMoneda() instanceof Peso)) {
			throw new Exception("Solo se puede abrir una cuenta corriente en pesos.");
		}
		
		this.sobregiro = sobregiro;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("Cuenta Corriente.")
			.append("\n")
			.append(super.toString())
			.toString();
	}
	
	@Override
	public Dinero depositar(Dinero dinero) throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
		
		// Planteo los porcentajes como un array de partes (ej: { 3, 97 }
		int[] partes = { porcentajeComision, 100 - porcentajeComision };
		
		// Distribuyo el dinero a depositar entre la retención y el depósito final
		Dinero[] depositos = dinero.distribuir(partes);
		
		// Deposito en la cuenta de retenciones la parte retenida
		Banco.instance().getRetenciones().depositar(depositos[0]);
		
		// Deposito en la cuenta corriente la parte restante
		return super.depositar(depositos[1]);
	}
	
	@Override
	public Dinero extraer(Dinero dinero, String observaciones) throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
		
		// Planteo los porcentajes como un array de partes (ej: { 3, 97 }
		int[] partes = { porcentajeComision, 100 - porcentajeComision };
		
		// Distribuyo el dinero a depositar entre la retención y el depósito final
		Dinero[] depositos = dinero.distribuir(partes);
		
		Dinero extraccion = dinero.sumar(depositos[0]);
		
		if (operacionSuperaSobregiroPermitido(extraccion)) {
			throw new Exception("No se puede extraer el monto ya que supera el sobregiro habilitado a la cuenta.");
		}
		
		// Deposito en la cuenta de retenciones la parte retenida
		Banco.instance().getRetenciones().depositar(depositos[0]);
		
		return super.extraer(extraccion, observaciones);
	}

	private boolean operacionSuperaSobregiroPermitido(Dinero montoAExtraer) throws Exception {
		return saldo.restar(montoAExtraer).compareTo(sobregiro.invertir()) < 0;
	}

	public Dinero getSobregiro() {
		return sobregiro;
	}
}

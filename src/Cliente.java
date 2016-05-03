import java.util.HashSet;
import java.util.Set;

abstract class Cliente implements Comparable<Cliente> {
	protected final String			razonSocial;
	protected final String			cuit;
	protected final Domicilio		domicilio;
	protected final String			telefono;
	protected boolean				activo;
	protected Set<CuentaDeCliente>	cuentas;

	public Cliente(String razonSocial, String cuit, Domicilio domicilio, String telefono) {
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.cuentas = new HashSet<>();
		activo = true;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Cliente && equals((Cliente) obj);
	}

	public boolean equals(Cliente other) {
		return cuit == other.cuit;
	}

	@Override
	public int hashCode() {
		return cuit.hashCode();
	}

	@Override
	public int compareTo(Cliente o) {
		if (this.equals(o)) {
			return 0;
		}

		return cuit.compareTo(o.cuit);
	}

	@Override
	public String toString() {
		return new StringBuilder().append("CUIT: ").append(cuit).append("\n").append("Nombre o razon social: ").append(razonSocial).append("\n").append("Domicilio: ").append(domicilio.toString()).append("\n").append("Telefono: ").append(telefono)
			.append("\n").toString();
	}

	public void desactivar() {
		activo = false;
	}

	public void reactivar() {
		activo = true;
	}

	public boolean isActivo() {
		return activo;
	}

	public void addCuenta(CuentaDeCliente cuenta) throws Exception {
		if (! activo) {
			throw new Exception("El cliente no está activo.");
		}
		
		this.cuentas.add(cuenta);
	}

	public boolean hasCuentas() {
		return !this.cuentas.isEmpty();
	}
}

public final class EstadoCivil {
	public static final EstadoCivil	SOLTERO		= new EstadoCivil("Soltero");
	public static final EstadoCivil	CASADO		= new EstadoCivil("Casado");
	public static final EstadoCivil	DIVORCIADO	= new EstadoCivil("Divorciado");
	public static final EstadoCivil	VIUDO		= new EstadoCivil("Viudo");

	private final String estado;

	private EstadoCivil(String estado) {
		this.estado = estado;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof EstadoCivil && equals((EstadoCivil) obj);
	}

	public boolean equals(EstadoCivil other) {
		return estado == other.estado;
	}

	@Override
	public int hashCode() {
		return estado.hashCode();
	}
}

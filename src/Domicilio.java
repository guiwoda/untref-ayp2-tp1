public class Domicilio {
	private final String	direccion;
	private final int		codigoPostal;
	private final String	localidad;
	private final String	provincia;

	public Domicilio(String direccion, int codigoPostal, String localidad, String provincia) {
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.provincia = provincia;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getProvincia() {
		return provincia;
	}

}

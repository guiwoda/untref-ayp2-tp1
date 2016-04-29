
public class PersonaFisica extends Cliente {
	private final Documento		documento;
	private final EstadoCivil	estadoCivil;
	private final String		profesion;
	private final String		conyuge;

	public PersonaFisica(
		String nombre, 
		String cuit, 
		Domicilio domicilio, 
		String telefono, 
		Documento documento, 
		EstadoCivil estadoCivil, 
		String profesion, 
		String conyuge)
	{
		super(nombre, cuit, domicilio, telefono);
		
		this.documento = documento;
		this.estadoCivil = estadoCivil;
		this.profesion = profesion;
		this.conyuge = conyuge;
	}

	public Documento getDocumento() {
		return documento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public String getProfesion() {
		return profesion;
	}

	public String getConyuge() {
		return conyuge;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("Persona Fisica.").append("\n")
			.append(super.toString())
			.append("Documento: ").append(documento.toString()).append("\n")
			.append("Estado civil: ").append(estadoCivil).append("\n")
			.append("Profesion: ").append(profesion).append("\n")
			.append("Conyuge: ").append(conyuge).append("\n")
			.toString();
	}
}

import java.util.Date;

public class PersonaJuridica extends Cliente {
	private final Date fechaContratoSocial;

	public PersonaJuridica(String razonSocial, String cuit, Domicilio domicilio, String telefono, Date fechaContratoSocial) {
		super(razonSocial, cuit, domicilio, telefono);
		
		this.fechaContratoSocial = fechaContratoSocial;
	}
	
	public Date getFechaContratoSocial() {
		return fechaContratoSocial;
	}
}

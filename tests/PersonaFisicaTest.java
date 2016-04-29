import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonaFisicaTest extends ClienteTest<PersonaFisica> {

	private Documento	documento;
	private EstadoCivil	estadoCivil;
	private String	profesion;
	private String	conyuge;
	
	@Override
	protected PersonaFisica getCliente() {
		return new PersonaFisica(
			razonSocial, 
			cuit, 
			domicilio, 
			telefono,
			documento,
			estadoCivil,
			profesion,
			conyuge
		);
	}

	@Test
	public void tieneUnDocumento() {
		assertEquals(documento, cliente.getDocumento());
	}

	@Test
	public void tieneUnEstadoCivil() {
		assertEquals(estadoCivil, cliente.getEstadoCivil());
	}

	@Test
	public void tieneUnaProfesion() {
		assertEquals(profesion, cliente.getProfesion());
	}

	@Test
	public void tieneDatosDelConyuge() {
		assertEquals(conyuge, cliente.getConyuge());
	}
	
	@Override
	public void setUp() throws Exception {
		documento = Documento.dni(32000555);
		estadoCivil = EstadoCivil.SOLTERO;
		profesion = "Programador";
		conyuge = "Clementina Josefa Pueyrredón Alvear";
		
		super.setUp();
	}
}

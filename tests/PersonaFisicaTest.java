import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonaFisicaTest extends ClienteTest {

	private PersonaFisica personaFisica;
	
	@Before
	@Override
	protected Cliente getCliente() {
		personaFisica = new PersonaFisica();
		
		return personaFisica;
	}
	
	@Test
	public void necesitaMasDetallesAlRegistrar() {
		fail("Not yet implemented");
	}

	@Test
	public void tieneUnDocumento() {
		fail("Not yet implemented");
	}

	@Test
	public void tieneUnEstadoCivil() {
		fail("Not yet implemented");
	}

	@Test
	public void tieneUnaProfesion() {
		fail("Not yet implemented");
	}

	@Test
	public void tieneDatosDelConyuge() {
		fail("Not yet implemented");
	}
}

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonaJuridicaTest extends ClienteTest {

	private PersonaJuridica personaJuridica;
	
	@Before
	@Override
	protected Cliente getCliente() {
		personaJuridica = new PersonaJuridica();
		
		return personaJuridica;
	}

	@Test
	public void necesitaLaFechaDelContratoSocialAlRegistrar() {
		fail("Not yet implemented");
	}

	@Test
	public void tieneUnaFechaDeContratoSocial() {
		fail("Not yet implemented");
	}
}

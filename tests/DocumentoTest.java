import static org.junit.Assert.*;

import org.hamcrest.core.StringContains;
import org.junit.Test;

public class DocumentoTest {

	@Test
	public void puedeSerUnDNI() throws Exception {
		Documento dni = Documento.dni(40555000);

		assertTrue(dni.isDNI());
		assertFalse(dni.isPasaporte());
	}

	@Test
	public void puedeSerUnPasaporte() throws Exception {
		Documento dni = Documento.pasaporte("ABC8765432N");

		assertTrue(dni.isPasaporte());
		assertFalse(dni.isDNI());
	}

	@Test(expected = Exception.class)
	public void necesitaUnNumeroDePasaporte() throws Exception {
		Documento.pasaporte("");
	}

	@Test(expected = Exception.class)
	public void necesitaUnNumeroDePasaporteNoNull() throws Exception {
		Documento.pasaporte(null);
	}

	@Test
	public void implementaToString() throws Exception {
		Documento pasaporte = Documento.pasaporte("ABC12345");
		
		assertThat(pasaporte.toString(), new StringContains("Pasaporte"));
		assertThat(pasaporte.toString(), new StringContains("ABC12345"));
	}
}

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class PersonaJuridicaTest extends ClienteTest<PersonaJuridica> {
	private Date fechaContratoSocial = new Date(1445385600);
	@Override
	protected PersonaJuridica getCliente() {
		return new PersonaJuridica(razonSocial, cuit, domicilio, telefono, fechaContratoSocial);
	}

	@Test
	public void tieneUnaFechaDeContratoSocial() {
		assertEquals(fechaContratoSocial, cliente.getFechaContratoSocial());
	}
}

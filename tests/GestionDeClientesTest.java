import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestionDeClientesTest {
	Cliente cliente;
	Domicilio casa;
	PersonaFisica persona;
	PersonaJuridica empresa;
	GestionDeClientes gestion;

	@Before
	public void setUp() throws Exception {
		gestion = getObject();
		casa = new Domicilio("La julia 338", 8877, "Saenz Peña", "Buenos Aires");
		cliente = gestion.darDeAltaPersonaFisica("Ivan", "8-97928549-40", casa,
				"4840-8843", Documento.dni(49398370), EstadoCivil.SOLTERO,
				"Desempleado", "Familia");
		persona = gestion.darDeAltaPersonaFisica("Ivan", "8-97923459-40", casa,
				"4840-8843", Documento.dni(49398370), EstadoCivil.SOLTERO,
				"Desempleado", "Familia");
		empresa = gestion.darDeAltaPersonaJuridica("Patagonica",
				"20-3194850-90", casa, "4848-8839");
	}

	protected GestionDeClientes getObject() {
		return new GestionDeClientes();
	}

	@Test
	public void puedeDarDeAltaClientesNuevos() throws Exception {
		assertEquals(true, cliente.isActivo());
	}

	@Test(expected = Exception.class)
	public void noPuedeDarDeAltaNuevamenteClientesYaExistentes()
			throws Exception {
		gestion.darDeAltaPersonaFisica("Ivan", "8-97928549-40", casa,
				"4840-8843", Documento.dni(49398370), EstadoCivil.SOLTERO,
				"Desempleado", "Familia");
	}

	@Test
	public void puedeDarDeBajaClientesSinCuentasAsociadas() throws Exception {
		gestion.darDeBaja("8-97928549-40");
		assertEquals(false, cliente.isActivo());
	}

	@Test(expected = Exception.class)
	public void noPuedeDarDeBajaClientesConCuentasAsociadas() throws Exception {
		CuentasFixture.cajaDeAhorroPesos((PersonaFisica) cliente);
		gestion.darDeBaja("8-97928549-40");
	}

	@Test
	public void puedeBuscarClientesPorCUIT() throws Exception {
		Cliente otro;
		otro = gestion.buscarClientesPorCuit("8-97928549-40");
		assertEquals(cliente, otro);
	}

	@Test
	public void puedeBuscarClientesPorRazonSocial() throws Exception {
		PersonaJuridica otro;
		otro = gestion.buscarClientesPorRazonSocial("Patagonica");
		assertEquals(empresa, otro);
	}

	@Test
	public void puedeBuscarClientesPorNumeroDeDocumento() throws Exception {
		PersonaFisica otro;
		Documento doc = Documento.dni(49398370);
		otro = gestion.buscarClientesPorNumeroDeDocumento(doc);
		assertEquals(persona, otro);

	}

	@Test
	public void puedeReactivarUnClienteQueEsteDadoDeBaja() throws Exception {
		cliente.desactivar();
		cliente.reactivar();
		assertEquals(true, cliente.isActivo());
	}
}

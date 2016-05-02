import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestionDeClientesTest extends TrabajoPracticoTest<GestionDeClientes> {
	
	@Override
	protected GestionDeClientes getObject() {
		return new GestionDeClientes();
	}
	
	@Test
	public void puedeDarDeAltaClientesNuevos() throws Exception{
		Domicilio casa = new Domicilio("La julia 338", 8877, "Saenz Peña", "Buenos Aires");
		Cliente cliente = getObject().darDeAltaPersonaFisica("Ivan", "8-97928549-40", casa, "4840-8843", Documento.dni(49398370), EstadoCivil.SOLTERO, "Desempleado", "Familia");
		assertEquals(true, cliente.isActivo());
	}
	
	@Test
	public void noPuedeDarDeAltaNuevamenteClientesYaExistentes() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeDarDeBajaClientesSinCuentasAsociadas() {
		fail("Not yet implemented");
	}
	
	@Test
	public void noPuedeDarDeBajaClientesConCuentasAsociadas() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeBuscarClientesPorCUIT() {
		fail("Not yet implemented");
	}
	@Test
	public void puedeBuscarClientesPorRazonSocial() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeBuscarClientesPorNumeroDeDocumento() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeReactivarUnClienteQueEsteDadoDeBaja() {
		fail("Not yet implemented");
	}
}

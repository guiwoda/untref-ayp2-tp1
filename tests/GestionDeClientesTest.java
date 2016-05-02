import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestionDeClientesTest extends TrabajoPracticoTest<GestionDeClientes> {
	Cliente cliente;
	Domicilio casa;
	@Before
	
	public void setUp() throws Exception{
		casa = new Domicilio("La julia 338", 8877, "Saenz Peña", "Buenos Aires");
		cliente = getObject().darDeAltaPersonaFisica("Ivan", "8-97928549-40", casa, "4840-8843", Documento.dni(49398370), EstadoCivil.SOLTERO, "Desempleado", "Familia");
	}
	@Override
	protected GestionDeClientes getObject() {
		return new GestionDeClientes();
	}
	
	@Test
	public void puedeDarDeAltaClientesNuevos() throws Exception{
		assertEquals(true, cliente.isActivo());
	}
	
	@Test(expected = Exception.class)
	public void noPuedeDarDeAltaNuevamenteClientesYaExistentes() throws Exception{
		
		getObject().darDeAltaPersonaFisica("Ivan", "8-97928549-40", casa, "4840-8843", Documento.dni(49398370), EstadoCivil.SOLTERO, "Desempleado", "Familia");
	}
	
	@Test
	public void puedeDarDeBajaClientesSinCuentasAsociadas() throws Exception{
		getObject().darDeAltaPersonaFisica("Ivan", "8-97928549-40", casa, "4840-8843", Documento.dni(49398370), EstadoCivil.SOLTERO, "Desempleado", "Familia");
	}
	
	@Test(expected = Exception.class)
	public void noPuedeDarDeBajaClientesConCuentasAsociadas() {
		CuentasFixture.cajaDeAhorroPesos(cliente);
		cliente.desactivar();
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

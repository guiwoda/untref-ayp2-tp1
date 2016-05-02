import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestionDeClientesTest extends TrabajoPracticoTest<GestionDeClientes> {
	Cliente cliente;
	Domicilio casa;
	PersonaFisica persona;
	PersonaJuridica empresa;
	@Before
	
	public void setUp() throws Exception{
		casa = new Domicilio("La julia 338", 8877, "Saenz Peña", "Buenos Aires");
		cliente = getObject().darDeAltaPersonaFisica("Ivan", "8-97928549-40", casa, "4840-8843", Documento.dni(49398370), EstadoCivil.SOLTERO, "Desempleado", "Familia");
		persona = getObject().darDeAltaPersonaFisica("Ivan", "8-97923459-40", casa, "4840-8843", Documento.dni(49398370), EstadoCivil.SOLTERO, "Desempleado", "Familia");
		empresa = getObject().darDeAltaPersonaJuridica("Patagonica", "20-3194850-90", casa, "4848-8839" );
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
		getObject().darDeBaja("8-97928549-40");
		assertEquals(false, cliente.isActivo());
	}
	
	@Test(expected = Exception.class)
	public void noPuedeDarDeBajaClientesConCuentasAsociadas() throws Exception {
		CuentasFixture.cajaDeAhorroPesos ((PersonaFisica) cliente);
		getObject().darDeBaja("8-97928549-40");
	}
	
	@Test
	public void puedeBuscarClientesPorCUIT() {
		Cliente otro;
		otro = getObject().buscarClientesPorCuit("8-97928549-40");
		assertEquals(cliente, otro);
	}
	@Test
	public void puedeBuscarClientesPorRazonSocial() {
		PersonaJuridica otro;
		otro = getObject().buscarClientesPorRazonSocial("Patagonica");
		assertEquals(empresa, otro);
	}
	
	@Test
	public void puedeBuscarClientesPorNumeroDeDocumento() throws Exception{
		PersonaFisica otro;
		otro = getObject().buscarClientesPorNumeroDeDocumento(Documento.dni(49398370));
		assertEquals(persona, otro);
		
	}	
	
	
	@Test
	public void puedeReactivarUnClienteQueEsteDadoDeBaja() {
		cliente.desactivar();
		cliente.reactivar();
		assertEquals(true, cliente.isActivo());
	}
}

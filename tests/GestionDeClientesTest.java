import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GestionDeClientesTest extends TrabajoPracticoTest<GestionDeClientes> {
	
	@Override
	protected GestionDeClientes getObject() {
		return new GestionDeClientes();
	}
	
	@Test
	public void puedeDarDeAltaClientesNuevos() {
		fail("Not yet implemented");
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

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CajaDeAhorroTest extends CuentaTest<CajaDeAhorro, Dolar> {
	
	@Override
	public <Dolar> CajaDeAhorro createCuenta(int CBU) {
		try {
			return new CajaDeAhorro<>(CBU, createClientes());
		} catch (Exception e) {
			fail("Something went wrong: " + e.getMessage());
			
			return null;
		}
	}
	
	public Dolar createMoneda() {
		return new Dolar();
	}

	private Set<PersonaFisica> createClientes() {
		Set<PersonaFisica> clientes = new HashSet<PersonaFisica>();
		clientes.add(new PersonaFisica());
		return clientes;
	}
	
	@Test
	public void tieneAlMenosUnaPersonaFisicaAsociadaComoTitular() {
		assertFalse(createCuenta(1337).getTitulares().isEmpty());
	}
	
	@Test
	public void puedeTenerMasDeUnaPersonaFisicaAsociadaComoTitular() throws Exception {
		Set<PersonaFisica> clientes = createClientes();
		clientes.add(new PersonaFisica());
		
		CajaDeAhorro<Dolar> laCuenta = new CajaDeAhorro<>(1337, clientes);
		
		assertEquals(2, laCuenta.getTitulares().size());
	}
	
	@Test(expected=Exception.class)
	public void noPuedeNoTenerAlMenosUnaPersonaFisicaAsociadaComoTitular() throws Exception {
		Set<PersonaFisica> clientes = new HashSet<PersonaFisica>();
		
		CajaDeAhorro<Dolar> laCuenta = new CajaDeAhorro<>(1337, clientes);
	}

	@Test
	public void noPuedeTenerSaldoNegativo() {
		CajaDeAhorro<Dolar> laCuenta = createCuenta(1337);
		
		
	}
	
	@Test
	public void puedeEstarNominadaEnPesos() {
		fail("Not yet implemented");
	}
	
	@Test
	public void puedeEstarNominadaEnDolares() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnCostoDeMantenimientoFijoEnLaMonedaEnQueEstaNominada() {
		fail("Not yet implemented");
	}
	
	@Test
	public void seDebeDepositarUnMontoAlAbrir() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sePuedeDepositarDinero() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sePuedeExtraerDinero() {
		fail("Not yet implemented");
	}
	
	@Test
	public void sePuedeTransferirDineroAOtraCuenta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tieneUnaTasaDeInteresPactadaAlAbrir() {
		fail("Not yet implemented");
	}
}

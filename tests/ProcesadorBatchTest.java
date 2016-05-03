import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProcesadorBatchTest {

	private List<CajaDeAhorro> cuentas;
	private ProcesadorBatch procesador;
	private CajaDeAhorro laQueFalla;
	
	@Before
	public void setUp() throws Exception {
		// Agrego una cuenta sin saldo para que no pueda debitarle el mantenimiento
		laQueFalla = CuentasFixture.cajaDeAhorroPesos();
		laQueFalla.extraer(laQueFalla.getSaldo());
		
		cuentas = new ArrayList<CajaDeAhorro>();
		cuentas.add(CuentasFixture.cajaDeAhorroPesos());
		cuentas.add(CuentasFixture.cajaDeAhorroDolares());
		cuentas.add(CuentasFixture.cajaDeAhorroPesos());
		cuentas.add(CuentasFixture.cajaDeAhorroDolares());
		cuentas.add(laQueFalla);
		cuentas.add(CuentasFixture.cajaDeAhorroDolares());
		
		procesador = new ProcesadorBatch(cuentas);
	}
	
	@After
	public void tearDown() throws Exception {
		Date hoy = new Date();
			
		Files.deleteIfExists(Paths.get(procesador.getNombreArchivoCobroExitoso(hoy)));
		Files.deleteIfExists(Paths.get(procesador.getNombreArchivoErrores(hoy)));
	}
	
	@Test
	public void puedeCobrarMantenimientos() throws Exception {
		Map<CajaDeAhorro, Dinero> saldos = new HashMap<>();
		
		for (CajaDeAhorro cuenta : cuentas) {
			saldos.put(cuenta, cuenta.getSaldo());
		}
		
		procesador.cobrarMantenimientos();
		
		for (CajaDeAhorro cuenta : cuentas) {
			if (cuenta.equals(laQueFalla)){
				continue;
			}
			
			assertTrue(
				saldos.get(cuenta).toString() + " deberia ser mayor que " + cuenta.getSaldo().toString(),
				saldos.get(cuenta).compareTo(cuenta.getSaldo()) > 0
			);
		}
	}

	@Test
	public void debeGenerarUnListadoDeMantenimientosCobradosConLaFechaDelDia() throws Exception {
		Date hoy = new Date();
		
		procesador.cobrarMantenimientos();
		
		Path path = Paths.get(procesador.getNombreArchivoCobroExitoso(hoy));
		
		assertTrue(Files.exists(path));
		
		String content = "";
		for (String line : Files.readAllLines(path)) {
			content = content + line + "\n";
		}
		
		for (CajaDeAhorro cuenta : cuentas) {
			if (cuenta.equals(laQueFalla)){
				continue;
			}
			
			assertThat(content, new StringContains(String.valueOf(cuenta.getCBU())));
			assertThat(content, new StringContains(cuenta.getCostoDeMantenimiento().toString()));
		}
	}
	
	@Test
	public void debeGenerarUnListadoDeErroresDeMantenimientoConLaFechaDelDia() throws Exception  {
		Date hoy = new Date();
		
		procesador.cobrarMantenimientos();
		
		Path path = Paths.get(procesador.getNombreArchivoErrores(hoy));
		
		assertTrue(Files.exists(path));
		
		String content = "";
		for (String line : Files.readAllLines(path)) {
			content = content + line + "\n";
		}
		System.out.println(content);
		assertThat(content, new StringContains(String.valueOf(laQueFalla.getCBU())));
		assertThat(content, new StringContains("Saldo insuficiente."));
	}
}

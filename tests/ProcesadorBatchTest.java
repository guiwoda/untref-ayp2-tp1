import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProcesadorBatchTest extends TrabajoPracticoTest {

	private ProcesadorBatch batch;
	
	@Before
	@Override
	protected Object getObject() {
		batch = new ProcesadorBatch();
		
		return batch;
	}
	
	@Test
	public void puedeCobrarMantenimientos() {
		fail("Not yet implemented");
	}

	@Test
	public void debeGenerarUnListadoDeMantenimientosCobradosConLaFechaDelDia() {
		fail("Not yet implemented");
	}
	
	@Test
	public void debeGenerarUnListadoDeErroresDeMantenimientoConLaFechaDelDia() {
		fail("Not yet implemented");
	}
}

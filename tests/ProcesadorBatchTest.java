import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProcesadorBatchTest extends TrabajoPracticoTest<ProcesadorBatch> {

	@Override
	protected ProcesadorBatch getObject() {
		return new ProcesadorBatch();
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

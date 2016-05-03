import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

public class BancoTest {

	@Test
	public void esUnSingleton() throws Exception {
		assertEquals(Banco.instance(), Banco.instance());
	}

	@Test
	public void tieneUnaCuentaEspecialDeRetenciones() throws Exception {
		assertThat(Banco.instance().getRetenciones(), new IsInstanceOf(CuentaEspecial.class));
	}
	
	@Test
	public void tieneUnaCuentaEspecialDeMantenimientos() throws Exception {
		assertThat(Banco.instance().getMantenimientos(), new IsInstanceOf(CuentaEspecial.class));
	}
	
	@Test
	public void puedeDecirmeElPorcentajeDeComision() throws Exception {
		assertEquals(3, Banco.instance().getPorcentajeComision());
	}
	
	@Test
	public void puedeDecirmeElCambioVigente() throws Exception {
		assertEquals(14.5, Banco.instance().getCambioVigente(), 0.01);
	}
}

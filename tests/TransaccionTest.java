import static org.junit.Assert.*;

import java.util.Date;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;

public class TransaccionTest extends TrabajoPracticoTest<Transaccion> {
	private Date	fecha;
	private Dinero	monto;
	private String	motivo;
	private String	observaciones;

	@Before
	public void setUp() throws Exception {
		fecha = new Date();
		monto = new Dinero(Moneda.PESO, 100);
		motivo = "JUnit tests";
		observaciones = "Alguna Observacion";
	}

	@Override
	protected Transaccion getObject() throws Exception {
		return Transaccion.credito(fecha, monto, motivo);
	}

	@Test
	public void puedeSerRegistradaAdemasConObservaciones() throws Exception {
		assertThat(Transaccion.credito(fecha, monto, motivo, observaciones), new IsInstanceOf(Transaccion.class));
	}

	@Test
	public void tieneUnaFechaYHora() throws Exception {
		assertEquals(fecha, getObject().getFecha());
	}

	@Test
	public void puedeTenerCreditoComoTipoDeMovimiento() throws Exception {
		assertEquals(TipoMovimiento.CREDITO, getObject().getTipoDeMovimiento());
	}

	@Test
	public void puedeTenerDebitoComoTipoDeMovimiento() throws Exception {
		Transaccion transaccion = Transaccion.debito(fecha, monto, motivo);

		assertEquals(TipoMovimiento.DEBITO, transaccion.getTipoDeMovimiento());
	}

	@Test
	public void tieneUnMonto() throws Exception {
		assertEquals(monto, getObject().getMonto());
	}

	@Test
	public void tieneUnMotivo() throws Exception {
		assertEquals(motivo, getObject().getMotivo());
	}

	@Test
	public void puedeTenerObservaciones() throws Exception {
		assertEquals(observaciones, Transaccion.credito(fecha, monto, motivo, observaciones).getObservaciones());
	}

	@Test
	public void puedeNoTenerObservaciones() throws Exception {
		assertNull(getObject().getObservaciones());
	}
}

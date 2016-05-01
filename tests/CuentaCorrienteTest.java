import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class CuentaCorrienteTest extends CuentaDeClienteTest<CuentaCorriente, Cliente> {
	private Dinero sobregiro;
	
	@Test
	public void tieneUnAcuerdoDeSobregiroPactadoAlAbrir() throws Exception {
		assertEquals(sobregiro, cuenta.getSobregiro());
	}

	@Test
	public void puedeTenerSaldoNegativo() throws Exception {
		cuenta.extraer(cuenta.getSaldo());
		cuenta.extraer(sobregiro.restar(100));
		
		assertTrue(cuenta.getSaldo().isNegativo());
	}
	
	@Test(expected=Exception.class)
	public void noPuedeTenerMenorSaldoQueElMontoDeSobregiro() throws Exception {
		cuenta.extraer(cuenta.getSaldo().sumar(sobregiro).sumar(100));
	}

	@Test(expected=Exception.class)
	public void soloPuedeEstarNominadaEnPesos() throws Exception {
		new CuentaCorriente(CBU, new Dinero(Moneda.DOLAR, 10), clientes, sobregiro);
	}

	@Test
	public void seCobraUnaComisionPorDepositoQueDebeSerDepositadaEnUnaCuentaEspecial() throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
		
		Dinero monto = new Dinero(Moneda.PESO, 40000);
		Dinero expected = cuenta.getSaldo().sumar(40000 - 400 * porcentajeComision);
		
		assertEquals(expected, cuenta.depositar(monto));
	}
	
	@Test
	public void seCobraUnaComisionPorExtraccionQueDebeSerDepositadaEnUnaCuentaEspecial() throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
		
		Dinero monto = new Dinero(Moneda.PESO, 400);
		Dinero expected = cuenta.getSaldo().restar(400 + 4 * porcentajeComision);
		
		assertEquals(expected, cuenta.extraer(monto));
	}
	
	@Test
	public void seCobraUnaComisionPorTransferenciaQueDebeSerDepositadaEnUnaCuentaEspecial() throws Exception {
		int porcentajeComision = Banco.instance().getPorcentajeComision();
		
		Dinero monto = new Dinero(Moneda.PESO, 400);
		Dinero resultadoDebito = cuenta.getSaldo().restar(400 + 4 * porcentajeComision);
		CuentaCorriente otra = createCuenta(1234);
		Dinero resultadoCredito = otra.getSaldo().sumar(400 - 4 * porcentajeComision);
		
		cuenta.transferir(otra, monto);
		
		assertEquals(resultadoDebito, cuenta.getSaldo());
		assertEquals(resultadoCredito, otra.getSaldo());
	}

	@Test
	public void noTieneCostoDeMantenimiento() throws Exception {
		assertEquals(new Dinero(denominacion, 0), cuenta.getCostoDeMantenimiento());
	}

	@Override
	public CuentaCorriente createCuenta() throws Exception {
		return createCuenta(CBU);
	}

	@Override
	public CuentaCorriente createCuenta(int CBU) throws Exception {
		clientes.add(createCliente("20-23455432-1"));
		
		return createCuenta(CBU, new Dinero(denominacion, 10), clientes, sobregiro);
	}

	public CuentaCorriente createCuenta(int CBU, Dinero saldo, Set<Cliente> clientes, Dinero sobregiro) throws Exception {
		return new CuentaCorriente(CBU, saldo, clientes, sobregiro);
	}

	@Override
	public CuentaCorriente createCuenta(Set<Cliente> clientes) throws Exception {
		return createCuenta(CBU, new Dinero(Moneda.PESO, 10), clientes, sobregiro);
	}

	@Override
	public Set<Cliente> createClientes() throws Exception {
		return new HashSet<Cliente>();
	}

	@Override
	public Cliente createCliente(String cuit) throws Exception {
		return new PersonaJuridica("Acme Co.", cuit, new Domicilio("La julia 338", 8877, "Saenz Peña", "Buenos Aires"), "5555-4444", new Date(1445385600));
	}

	@Override
	protected Peso createDenominacion() throws Exception {
		return Moneda.PESO;
	}
	
	@Override
	public void setUp() throws Exception {
		sobregiro = new Dinero(Moneda.PESO, 500);
		
		super.setUp();
	}
}

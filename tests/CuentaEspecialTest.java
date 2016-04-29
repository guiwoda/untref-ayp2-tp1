import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class CuentaEspecialTest extends CuentaTest<CuentaEspecial> {
	
	@Test
	public void puedenCobrarComisionesACuentasDeClientes() throws Exception {
		Set<Cliente> clientes = new HashSet<Cliente>();
		clientes.add(new PersonaJuridica("Acme Co.", "20-12345678-9", new Domicilio(), "5555-4444", new Date(1445385600)));
		
		CuentaCorriente cuentaDeCliente = new CuentaCorriente(123, new Dinero(Moneda.PESO, 10), clientes, new Dinero(Moneda.PESO, 1000));
		
		cuentaDeCliente.transferir(cuenta, new Dinero(Moneda.PESO, 20));
		
		assertTrue(cuenta.getSaldo().compareTo(saldo) > 0);
	}

	@Override
	public CuentaEspecial createCuenta(int CBU) throws Exception {
		return new CuentaEspecial(CBU);
	}

	@Override
	protected Peso createDenominacion() throws Exception {
		return Moneda.PESO;
	}
}

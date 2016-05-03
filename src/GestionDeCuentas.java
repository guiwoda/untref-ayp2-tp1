import java.util.Map;
import java.util.Set;

public class GestionDeCuentas {
	private Map<Integer, CuentaDeCliente<?>> cuentas;

	public GestionDeCuentas(Map<Integer, CuentaDeCliente<?>> cuentas) {
		this.cuentas = cuentas;
	}

	public CajaDeAhorro abrirCajaDeAhorro(Dinero deposito, Set<PersonaFisica> titulares, Dinero interes) throws Exception {
		CajaDeAhorro caja = new CajaDeAhorro(getProximoCBU(), deposito, titulares, interes);
		cuentas.put(caja.getCBU(), caja);

		return caja;
	}

	private int getProximoCBU() {
		int candidato = cuentas.size();

		while (cuentas.containsKey(candidato)) {
			candidato++;
		}

		return candidato;
	}

	public CuentaCorriente abrirCuentaCorriente(Dinero deposito, Set<Cliente> titulares, Dinero sobregiro) throws Exception {
		CuentaCorriente cc = new CuentaCorriente(getProximoCBU(), deposito, titulares, sobregiro);
		cuentas.put(cc.getCBU(), cc);

		return cc;
	}

	public void inhabilitar(int cbu) {
		cuentas.get(cbu).inactivar();
	}

	public void habilitar(int cbu) {
		cuentas.get(cbu).activar();
	}
}

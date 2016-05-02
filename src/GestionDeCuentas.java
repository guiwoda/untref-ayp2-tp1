import java.util.HashMap;
import java.util.Map;

public class GestionDeCuentas {
	Map<String, Cliente> clientes = new HashMap<>();
	Map<Documento, PersonaFisica> personasFisicas = new HashMap<>();
	Map<String, PersonaJuridica> personasJuridicas = new HashMap<>();
    
	public double depositoInicial;
	
public void aperturaDeCajaDeAhorro(PersonaFisica cliente, double depositoInicial, double costoDeMantenimiento)throws Exception { 
	this.depositoInicial = depositoInicial;
	
}

public void aperturaDeCuentaCorriente( double depositoInicial)throws Exception {
	
	
}

public void inhabilitarCuenta() {
	
	
}

public void habilitarCuenta() {
	
	SOLO PARA VER SI CAMBIA Y HACE EL COMMIT
}

}
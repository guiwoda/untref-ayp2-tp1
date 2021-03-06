import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class GestionDeClientes {
	Map<String, Cliente> clientes = new HashMap<>();
	Map<Documento, PersonaFisica> personasFisicas = new HashMap<>();
	Map<String, PersonaJuridica> personasJuridicas = new HashMap<>();
	
	public PersonaFisica darDeAltaPersonaFisica (String nombre, String cuit, Domicilio domicilio, String telefono,Documento documento, EstadoCivil estadoCivil, String profesion, String conyuge) throws Exception{
		if (clientes.containsKey(cuit)){
			throw new Exception ("ya existe");
		}
		PersonaFisica cliente = new PersonaFisica(nombre, cuit, domicilio, telefono, documento, estadoCivil, profesion, conyuge);
		clientes.put(cuit, cliente);
		personasFisicas.put(documento, cliente);
		return cliente;
	}
	public PersonaJuridica darDeAltaPersonaJuridica (String razonSocial, String cuit, Domicilio domicilio, String telefono)throws Exception{
		if (clientes.containsKey(cuit)){
			throw new Exception ("ya existe");
		}
		Date today = new Date();
		PersonaJuridica cliente = new PersonaJuridica(razonSocial, cuit, domicilio, telefono,today );
		clientes.put(cuit, cliente);
		personasJuridicas.put(razonSocial, cliente);
		return cliente;
	}
	public void darDeBaja (String cuit) throws Exception{
		Cliente cliente = clientes.get(cuit);
		if (clientes.get(cuit) == null){
			throw new Exception ("El cliente no existe");
		}
		if (cliente.hasCuentas()){
			throw new Exception ("Cliente posee cuentas activas, imposible dar de baja");
		}
		cliente.desactivar();
	}
	
	public Cliente buscarClientesPorCuit(String cuit){
		Cliente cliente = clientes.get(cuit);
		return cliente;
	}
	public PersonaJuridica buscarClientesPorRazonSocial(String razonSocial){
		PersonaJuridica cliente = personasJuridicas.get(razonSocial);
		return cliente;
	}
	public PersonaFisica buscarClientesPorNumeroDeDocumento(Documento documento){
		PersonaFisica cliente = personasFisicas.get(documento);
		return cliente;
	}
	public void reactivarCliente(String cuit){
		Cliente client = clientes.get(cuit);
		client.reactivar();
	}
	
}

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


class ClientesFixture {
	static PersonaFisica personaFisica() throws Exception {
		return new PersonaFisica(
			"ACME Co.",
			"20-12345678-9",
			new Domicilio("La julia 338", 8877, "Saenz Peña", "Buenos Aires"),
			"5555-4444",
			Documento.dni((int) (Math.random() * 100000000)),
			EstadoCivil.SOLTERO,
			"Programador",
			"Clementina Josefa Pueyrredón Alvear"
		);
	}
	
	static PersonaJuridica personaJuridica() throws Exception {
		return new PersonaJuridica(
			"ACME Co.",
			"20-12345678-9",
			new Domicilio("La julia 338", 8877, "Saenz Peña", "Buenos Aires"),
			"5555-4444",
			new Date(1445385600 + ((int) Math.random() * 100))
		);
	}
	
	static Set<Cliente> clientes(int fisicos, int juridicos) throws Exception {
		Set<Cliente> clientes = new HashSet<>();
		
		for (int i = 0; i < fisicos; i++)
			clientes.add(personaFisica());
		
		for (int j = 0; j < juridicos; j++)
			clientes.add(personaJuridica());
		
		return clientes;
	}
	
	static Set<Cliente> clientes() throws Exception {
		return clientes(1, 1);
	}
	
	static Set<PersonaFisica> personasFisicas(int count) throws Exception {
		Set<PersonaFisica> clientes = new HashSet<>();
		
		for (int i = 0; i < count; i++)
			clientes.add(personaFisica());
		
		return clientes;
	}
	
	static Set<PersonaFisica> personasFisicas() throws Exception {
		return personasFisicas(1);
	}
}

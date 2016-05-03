
public final class Documento {
	private static final String DNI = "DNI";
	private static final String PASAPORTE = "Pasaporte";
	
	private final String tipo;
	private final String numero;
	
	private Documento(String tipo, String numero) throws Exception {
		if (numero == null || numero.length() == 0) {
			throw new Exception("El numero de documento es requerido.");
		}
		
		this.tipo = tipo;
		this.numero = numero;
	}
	
	public static Documento dni(int numero) throws Exception {
		return new Documento(DNI, String.valueOf(numero));
	}
	
	public static Documento pasaporte(String numero) throws Exception {
		return new Documento(PASAPORTE, numero);
	}
	
	public boolean isDNI() {
		return tipo == DNI;
	}
	
	public boolean isPasaporte() {
		return tipo == PASAPORTE;
	}
	
	@Override
	public String toString() {
		return tipo + ": " + numero;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Documento && equals((Documento) obj);
	}
	public boolean equals(Documento other) {
		return numero.equals( other.numero);
	}


	@Override
	public int hashCode() {
		return numero.hashCode();
	}

}

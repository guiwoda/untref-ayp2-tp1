
public interface Moneda {
	public static final Peso PESO = new Peso();
	public static final Dolar DOLAR = new Dolar();
	
	public String getSimbolo();

	public Dinero getMantenimientoCajaDeAhorro();
}

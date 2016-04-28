
public class Banco {
	private static final Dinero<Dolar> mantenimientoCajaDeAhorroDolares = new Dinero<Dolar>(new Dolar(), 10);
	
	private static final Dinero<Peso> mantenimientoCajaDeAhorroPesos = new Dinero<Peso>(new Peso(), 10);
	
	public static <M extends Moneda> Dinero<M> getMantenimiento(CajaDeAhorro cajaDeAhorro) {
		if (cajaDeAhorro.getDenominacion().getClass() == Dolar.class) {
			return (Dinero<M>) mantenimientoCajaDeAhorroDolares;
		}
		
		return (Dinero<M>) mantenimientoCajaDeAhorroPesos;
	}
}


class CuentasFixture {
	static CajaDeAhorro cajaDeAhorroPesos() throws Exception {
		return new CajaDeAhorro(
			(int) (Math.random() * 100), 
			new Dinero(Moneda.PESO, (float) Math.random() * 100), 
			ClientesFixture.personasFisicas(), 
			new Dinero(Moneda.PESO, (float) Math.random() * 10)
		);
	}
	
	static CajaDeAhorro cajaDeAhorroDolares() throws Exception {
		return new CajaDeAhorro(
			(int) (Math.random() * 100), 
			new Dinero(Moneda.DOLAR, (float) Math.random() * 100), 
			ClientesFixture.personasFisicas(), 
			new Dinero(Moneda.DOLAR, (float) Math.random() * 10)
		);
	}
	
	static CuentaCorriente cuentaCorriente() throws Exception {
		return new CuentaCorriente(
			(int) (Math.random() * 100), 
			new Dinero(Moneda.PESO, (float) Math.random() * 100), 
			ClientesFixture.clientes(), 
			new Dinero(Moneda.PESO, (float) Math.random() * 10)
		);
	}

	public static CuentaDeCliente<?> random() throws Exception {
		double chance = Math.random();
		
		if (chance > 0.66) {
			return (CuentaDeCliente<?>) cajaDeAhorroPesos();
		} else if (chance > 0.33) {
			return (CuentaDeCliente<?>) cajaDeAhorroDolares();
		}
		
		return (CuentaDeCliente<?>) cuentaCorriente();
	}
}

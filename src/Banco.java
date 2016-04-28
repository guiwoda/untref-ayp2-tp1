public final class Banco {
	private static final int		CBU_RETENCIONES		= -1;
	private static final int		CBU_MANTENIMIENTOS	= -2;
	private static final int		PORCENTAJE_COMISION	= 3;
	private static final Banco		INSTANCE			= new Banco();

	private final CuentaEspecial	retenciones;
	private final CuentaEspecial	mantenimientos;

	public static Banco instance() {
		return INSTANCE;
	}

	private Banco() {
		try {
			retenciones = new CuentaEspecial(CBU_RETENCIONES);
			mantenimientos = new CuentaEspecial(CBU_MANTENIMIENTOS);
		} catch (Exception e) {
			throw new Error();
		}
	}

	public CuentaEspecial getRetenciones() {
		return retenciones;
	}

	public CuentaEspecial getMantenimientos() {
		return mantenimientos;
	}

	public int getPorcentajeComision() {
		return PORCENTAJE_COMISION;
	}
}

public final class Banco {
	private static final int		CBU_RETENCIONES		= -1;
	private static final int		CBU_MANTENIMIENTOS	= -2;
	private static final int		PORCENTAJE_COMISION	= 3;
	private static final double		CAMBIO_VIGENTE		= 14.5;
	private static Banco			INSTANCE;

	private final CuentaEspecial	retenciones;
	private final CuentaEspecial	mantenimientos;

	public static Banco instance() throws Exception {
		if (INSTANCE == null) {
			INSTANCE = new Banco(new CuentaEspecial(CBU_RETENCIONES), new CuentaEspecial(CBU_MANTENIMIENTOS));
		}

		return INSTANCE;
	}

	private Banco(CuentaEspecial retenciones, CuentaEspecial mantenimientos) {
		this.retenciones = retenciones;
		this.mantenimientos = mantenimientos;
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

	public double getCambioVigente() {
		return CAMBIO_VIGENTE;
	}
}

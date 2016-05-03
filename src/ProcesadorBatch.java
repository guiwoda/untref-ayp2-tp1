import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProcesadorBatch {
	private List<CajaDeAhorro>	cuentas;
	private final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 

	public ProcesadorBatch(List<CajaDeAhorro> cuentas) {
		this.cuentas = cuentas;
	}

	public void cobrarMantenimientos() throws IOException {
		CuentaEspecial mantenimientos = Banco.instance().getMantenimientos();
		Date hoy = new Date();

		FileWriter archivoCobroExitoso = new FileWriter(new File(getNombreArchivoCobroExitoso(hoy)));
		FileWriter archivoErrores = new FileWriter(new File(getNombreArchivoErrores(hoy)));

		for (CajaDeAhorro cuenta : cuentas) {
			try {
				cobrarMantenimiento(cuenta, mantenimientos);
				registrarCobroExitoso(archivoCobroExitoso, cuenta);
			} catch (Exception e) {
				registrarErrorDeMantenimiento(archivoErrores, cuenta, e);
			}
		}
		
		archivoCobroExitoso.close();
		archivoErrores.close();
	}

	private void cobrarMantenimiento(CajaDeAhorro cuenta, CuentaEspecial mantenimientos) throws Exception {
		cuenta.transferir(mantenimientos, cuenta.getCostoDeMantenimiento());
	}
	
	private void registrarCobroExitoso(FileWriter archivo, CajaDeAhorro cuenta) throws IOException {
		archivo.write(
			new StringBuilder()
				.append(cuenta.getCBU()).append(", ")
				.append("Tipo de cuenta: CA, ")
				.append(cuenta.getCostoDeMantenimiento()).append(", ")
				.append(cuenta.getMoneda()).append(", ")
				.append(Banco.instance().getCambioVigente())
				.append("\n")
				.toString()
		);
	}
	
	private void registrarErrorDeMantenimiento(FileWriter archivo, CajaDeAhorro cuenta, Exception e) throws IOException {
		archivo.write(
			new StringBuilder()
				.append(cuenta.getCBU()).append(",")
				.append("Tipo de cuenta: CA,")
				.append(cuenta.getCostoDeMantenimiento()).append(",")
				.append("motivo: ")
				.append(e.getMessage())
				.append("\n")
				.toString()
		);
	}
	
	public String getNombreArchivoCobroExitoso(Date hoy) {
		return "MantenimientosCobrados" + formato.format(hoy);
	}
	
	public String getNombreArchivoErrores(Date hoy) {
		return "ErroresMantenimiento" + formato.format(hoy);
	}
}
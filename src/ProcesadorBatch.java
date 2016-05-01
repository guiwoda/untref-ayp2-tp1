import java.io.*;
public class ProcesadorBatch {
	
	public void cobroDeMantenimientos(){
		
	
		
	}

	public static void main (String [] args) throws Exception{
		
		CuentaEspecial mantenimientos = Banco.instance().getMantenimientos();
		
		PrintWriter writer1 = new PrintWriter("MantenimientosCobrados.txt", "UTF-8");
		PrintWriter writer2 = new PrintWriter("ErroresManteniemiento.txt", "UTF-8");
		writer1.println("");
		writer1.println("");
		writer2.println("");
		writer2.println("");
		writer1.close();
		writer2.close();
		
    }
}
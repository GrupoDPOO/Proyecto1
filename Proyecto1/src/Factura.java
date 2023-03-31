import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Factura {
	
	private int idFactura;
	private int idReserva;
	private String mensaje;
	
	
	public Factura(int idFactura, int idReserva, String mensaje) {
		
		this.idFactura = idFactura;
		this.idReserva = idReserva;
		this.mensaje = mensaje;
	}
	
	public void imprimirFacturaEnPantalla() {
		System.out.println(mensaje);
	}
	
	public void guardarFactura() throws IOException {
		
		BufferedWriter factura = new BufferedWriter(new FileWriter("./facturas/Factura_" + idFactura + ".txt"));
		factura.write(mensaje);
		factura.close();
	}
	
	
	
}


public class Servicio {
	private String codigo;
	private String producto;
	private String horaInicial;
	private String horaFinal;
	private double precio;
	
	public Servicio(String codigo,String producto, String horaInicial, String horaFinal, double precio) {
		
		this.codigo=codigo;
		this.producto = producto;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.precio = precio;
	}

	public String getProducto() {
		return producto;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public double getPrecio() {
		return precio;
	}
	public String getCodigo() {
		return codigo;
	}
	
	
	
	
}

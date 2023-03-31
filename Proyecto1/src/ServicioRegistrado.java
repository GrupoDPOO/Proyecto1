
public class ServicioRegistrado {
	
	private int idReserva;
	private String servicio;
	private double precio;
	
	public ServicioRegistrado(int idReserva, String servicio, double precio) {
		
		this.idReserva = idReserva;
		this.servicio = servicio;
		this.precio = precio;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public String getServicio() {
		return servicio;
	}

	public double getPrecio() {
		return precio;
	}
	
	
	
	
}

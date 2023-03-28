
public class Reserva {
	
	private int idHabitacion;
	private String fechaInicio;
	private String fechaFin;
	
	
	public Reserva(int idHabitacion, String fechaInicio, String fechaFin) {
		this.idHabitacion = idHabitacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}


	public int getIdHabitacion() {
		return idHabitacion;
	}


	public String getFechaInicio() {
		return fechaInicio;
	}


	public String getFechaFin() {
		return fechaFin;
	}
	
	

}

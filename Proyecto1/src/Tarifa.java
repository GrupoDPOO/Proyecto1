import java.util.Date;

public class Tarifa {
	
	private String tipoHabitacion;
	private Date fechaInicial;
	private Date fechaFinal;
	private String[] dias;
	private double precio;
	
	
	public Tarifa(String tipoHabitacion, Date fechaInicial, Date fechaFinal, String[] dias, double precio) {
		
		this.tipoHabitacion = tipoHabitacion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.dias = dias;
		this.precio=precio;
	}


	public String getTipoHabitacion() {
		return tipoHabitacion;
	}


	public Date getFechaInicial() {
		return fechaInicial;
	}


	public Date getFechaFinal() {
		return fechaFinal;
	}


	public String[] getDias() {
		return dias;
	}


	public double getPrecio() {
		return precio;
	}
	
	
	
	
}

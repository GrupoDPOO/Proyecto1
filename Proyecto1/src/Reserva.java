import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Reserva {
	
	private int idReserva;
	private int idHabitacion;
	private Date fechaInicio;
	private Date fechaFin;
	private int idCliente;
	private String nombreCliente;
	private double precioReserva;
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");


	public Reserva(int idReserva, int idHabitacion, String fechaInicio, String fechaFin, int idCliente,
			String nombreCliente, double precioReserva) throws ParseException {
		
		
		Date fechaIni = formato.parse(fechaInicio);  
		Date fechaFinal = formato.parse(fechaFin);  
		this.idReserva = idReserva;
		this.idHabitacion = idHabitacion;
		this.fechaInicio = fechaIni;
		this.fechaFin = fechaFinal;
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.precioReserva = precioReserva;
	}


	public int getIdHabitacion() {
		return idHabitacion;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}
	
	public String getFechaInicioString() {
		return formato.format(fechaInicio);
	}


	public String getFechaFinString() {
		return formato.format(fechaFin);
	}
	
	public int getIdReserva() {
		return idReserva;
	}
	
	public String getNombreCliente() {
		return nombreCliente;
	}
	
	public double getPrecio() {
		return precioReserva;
	}
	
	

}

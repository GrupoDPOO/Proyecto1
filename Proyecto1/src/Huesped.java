
public class Huesped {
	
	private int idReserva;
	private int id;
	private String nombre;
	private String documento;
	private String correo;
	private String telefono;
	private int cantidadPersonas;
	
	
	public Huesped(int idReserva, int id, String nombre, String documento, String correo, String telefono, int cantidadPersonas) {
		this.idReserva= idReserva;
		this.id = id;
		this.nombre = nombre;
		this.documento = documento;
		this.correo = correo;
		this.telefono = telefono;
		this.cantidadPersonas = cantidadPersonas;
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getId() {
		return id;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public String getDocumento() {
		return documento;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public int getCantidadPersonas() {
		return cantidadPersonas;
	}
	
	
}

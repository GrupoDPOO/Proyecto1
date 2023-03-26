
public class Usuario {
	
	//Atributos
	
	private String rol;
	private String nombre;
	private String documento;
	private String correo;
	private String telefono;
	private String user;
	private String clave;
	
	//Constructor
	
	public Usuario(String rol, String nombre, String documento, String correo, String telefono, String user,
			String clave) {
		super();
		this.rol = rol;
		this.nombre = nombre;
		this.documento = documento;
		this.correo = correo;
		this.telefono = telefono;
		this.user = user;
		this.clave = clave;
	}
	
	public String getUser() {
		return user;
	}

	public String getClave() {
		return clave;
	}
	
	public String getRol() {
		return rol;
	}
	
	public String getNombre() {
		return nombre;
	}
}

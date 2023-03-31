
public class Acompañante {
	
	private int idReserva;
	private int idHuesped;
	private String nombre;
	private String documento;
	
	
	public Acompañante(int idReserva, int idHuesped, String nombre, String documento) {
		this.idReserva=idReserva;
		this.idHuesped = idHuesped;
		this.nombre = nombre;
		this.documento = documento;
	}


	public String getNombre() {
		return nombre;
	}


	public String getDocumento() {
		return documento;
	}
	
	public int getIdReserva() {
		return idReserva;
	}
	
	
	

}

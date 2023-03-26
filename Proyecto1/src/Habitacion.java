import java.util.ArrayList;

public class Habitacion {
	
	//Atributos
	
	private int identificador;
	private String ubicacion;
	private boolean balcon;
	private boolean vista;
	private boolean cocina;
	private ArrayList<Cama> camas;
	private String tipo;
	
	//Constructor
	
	public Habitacion(int identificador, String ubicacion, boolean balcon,
			boolean vista, boolean cocina, ArrayList<Cama> camas, String tipo) {
		super();
		this.identificador = identificador;
		this.ubicacion = ubicacion;
		this.balcon = balcon;
		this.vista = vista;
		this.cocina = cocina;
		this.camas = camas;
		this.tipo=tipo;
	}
	
	public int getIdentificador() {
		return identificador;
	}

	@Override
	public String toString() {
		
		return identificador + ";" + ubicacion + ";" + balcon
				+ ";" + vista + ";" + cocina + ";" + "mediana" + ";" + tipo;
	}
	
	
	
	
	
	

}

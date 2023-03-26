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
	private boolean disponible;
	
	//Constructor
	
	public Habitacion(int identificador, String ubicacion, boolean balcon,
			boolean vista, boolean cocina, ArrayList<Cama> camas, String tipo, boolean disponible) {
		super();
		this.identificador = identificador;
		this.ubicacion = ubicacion;
		this.balcon = balcon;
		this.vista = vista;
		this.cocina = cocina;
		this.camas = camas;
		this.tipo=tipo;
		this.disponible= disponible;

	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setIdentificador(boolean disponible) {
        this.disponible = disponible;
    }
	
	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isBalcon() {
        return balcon;
    }

    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }

    public boolean isVista() {
        return vista;
    }

    public void setVista(boolean vista) {
        this.vista = vista;
    }

    public boolean isCocina() {
        return cocina;
    }

    public void setCocina(boolean cocina) {
        this.cocina = cocina;
    }

    public ArrayList<Cama> getCamas() {
        return camas;
    }

    public void setCamas(ArrayList<Cama> camas) {
        this.camas = camas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	@Override
	public String toString() {
		
		return identificador + ";" + ubicacion + ";" + balcon
				+ ";" + vista + ";" + cocina + ";" + "mediana" + ";" + tipo;
	}
	
	
	
	
	
	

}

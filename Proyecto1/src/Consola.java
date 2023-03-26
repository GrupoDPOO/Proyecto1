import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Consola {
	
	private static Hotel hotel = new Hotel();
	
	public static void main(String[] args) throws IOException {
		
		hotel.cargarUsuarios();
		Consola consola = new Consola();
		consola.login();
	}
	
	private void login() throws IOException {
		
		System.out.println("\n----------- Bienvenido -----------\n");
		String user = input("Digite su usuario");
		String clave = input("Digite su clave");
		
		
		Usuario usuario = hotel.validarUsuario(user, clave);
		
		if(usuario==null) {
			System.out.println("\n----------- Usuario o Contraseña incorrectos -----------");
			System.out.println("----------- Intente de nuevo -----------");
			this.login();
		}else if(usuario.getRol().equals("administrador")) {
			System.out.println("\n----------- Bienvenido " + usuario.getNombre()
			+" -----------");
			this.menuAdministrador();
		}else if (usuario.getRol().equals("recepcionista")){
			System.out.println("\n----------- Bienvenido " + usuario.getNombre()
			+" -----------");
			this.menuRecepcionista();
		}else if (usuario.getRol().equals("empleado")){
			System.out.println("\n----------- Bienvenido " + usuario.getNombre()
			+" -----------");
			this.menuEmpleado();
		}
		
	}

	private void menuEmpleado(){

		System.out.println("\n1. Registrar consumo de un huésped");
		String opcion = input("Digite una opción");

		if (opcion.equals("1")){
			int idCliente= input(mensaje:"Digite el id del cliente");
			ArrayList<String> servicios= new ArrayList<String>();
			System.out.println("\n -----------SERVICIOS-----------");
			System.out.println("1. Servicio de SPA");
			System.out.println("2. Servicio de guía turístico");
			System.out.println("2. Servicio de restaurante");
			String opcion2= input(mensaje:"Digite una opción");

			if (opcion2.equals("1")){

			}
			if (opcion2.equals("2")){
				
			}
			if (opcion2.equals("3")){
				
			}

		}
	}
	
	private void menuRecepcionista() throws IOException{

		System.out.println("\n1. Acceder al inventario de una habitación");
		System.out.println("2. Consultar disponibilidad de una habitación");
		System.out.println("3. Realizar una reserva");
		String opcion = input("Digite una opción");

		if (opcion.equals("1")){
			int id= input("Digite el id de la habitación");
			ArrayList<Habitacion> lista= new ArrayList<Habitacion>();
			lista.add(Hotel.getInventario());
			for (Habitacion hab: lista){
				if (hab.getIdentificador().equals(id)){
					System.out.println("El identificador de la habitación es:" + " " + hab.getIdentificador() + ", tiene cocina:" + " " + hab.isCocina()
					+ ",tiene balcon:" + " " + hab.isBalcon() + ", tiene vista:" + " " + hab.isVista() + "y cuenta con las siguientes camas:" + " "
					 +hab.getCamas());
				}
			}
		}

		if (opcion.equals("2")){
			int id= input(mensaje:"Digite el id de la habitación");
			ArrayList<Habitacion> lista= new ArrayList<Habitacion>();
			lista.add(Hotel.getInventario());

			System.out.println("-----------OPCIONES-----------");
			System.out.println("1. Ver disponibilidad actual");
			System.out.println("2. Ver disponibilidad para una fecha");

			String opcion2= input(mensaje:"Digite una opción");
			if (opcion2.equals("1")){
			for (Habitacion hab: lista){
				if (hab.getIdentificador().equals(id) && hab.isDisponible().equals(false)){
					System.out.println("La habitación no se encuentra disponible, se encuentra ocupada por el huesped" + " "  );
			}
		
				if (hab.getIdentificador().equals(id) && hab.isDisponible().equals(true)){
					System.out.println("La habitación se encuentra disponible");
			}
		}
	}
		if (opcion.equals("3")){
		
			
		}
		
	}
}

	private void menuAdministrador() throws IOException {
		System.out.println("\n1. Crear una nueva habitación");
		System.out.println("2. Cargar archivo de habitaciones");
		
		String opcion = input("Digite una opción");
		
		if(opcion.equals("1")) {
			hotel.cargarArchivos();
			this.crearHabitacion();
		}
	}
	
	private void crearHabitacion() throws IOException {
		
		int id = Integer.parseInt(input("Digite el identificado de la habitacion (SOLO NUMEROS)"));
		String ubicacion = input("Escriba la ubicacion de la habitacion");
		boolean balcon = Boolean.parseBoolean(input("Cuenta con balcon (true/false)"));
		boolean vista = Boolean.parseBoolean(input("Cuenta con vista (true/false)"));
		boolean cocina = Boolean.parseBoolean(input("Cuenta con cocina (true/false)"));
		String camas = input("Escriba el tipo de camas con las que cuenta la habitación separadas por comas");
		String tipo= input("Escriba el tipo de habitación (suite/suite doble/estandar)");
		boolean disponible= true;
		
		hotel.guardarHabitacion(id, ubicacion, balcon, vista, cocina, camas, tipo,disponible);
		System.out.println("\nHabitación creada y registrada en el sistema con exito");
	}
	
	/*
	 * Método para ingresar datos por el usuario (se obtuve del taller 1)
	 */

	public static String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

}

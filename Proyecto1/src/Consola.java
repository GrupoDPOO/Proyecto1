import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		
		hotel.guardarHabitacion(id, ubicacion, balcon, vista, cocina, camas, tipo);
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

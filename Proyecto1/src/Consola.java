import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Consola {
	
	private static Hotel hotel = new Hotel();
	
	public static void main(String[] args) throws IOException, ParseException {
		
		hotel.cargarUsuarios();
		hotel.cargarArchivos();
		hotel.cargarReservas();
		Consola consola = new Consola();
		consola.login();
	}
	
	private void login() throws IOException, ParseException {
		
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

		Scanner scaner = new Scanner (System.in);
		System.out.println("\n1. Registrar consumo de un huésped");
		String opcion = scaner.nextLine();

		if (opcion.equals("1")){

			System.out.println("Digite el id del cliente");
			int idCliente= scaner.nextInt();
			ArrayList<String> servicios= new ArrayList<String>();
			System.out.println("\n -----------SERVICIOS-----------");
			System.out.println("1. Servicio de SPA");
			System.out.println("2. Servicio de guía turístico");
			System.out.println("2. Servicio de restaurante");
			String opcion2= scaner.nextLine();

			if (opcion2.equals("1")){

			}
			if (opcion2.equals("2")){
				
			}
			if (opcion2.equals("3")){
				
			}

			scaner.close();

		}
	}
	
	private void menuRecepcionista() throws IOException, ParseException{

		System.out.println("\n1.Consultar inventario de habitaciones");
		System.out.println("2. Consultar todas las reservas");
		System.out.println("3. Consultar las reservaciones de una habitación en especifico");
		System.out.println("4. Consultar disponibilidad de habitaciones por fecha");
		System.out.println("5. Realizar una reserva");
		String opcion = input("Digite una opción");

		if(opcion.equals("1")) {
			
			ArrayList<Habitacion> inventario = hotel.getInventario();
			
			
			for(int i=0;i<inventario.size();i++) {
				System.out.println("\n------------------------------------------\n");
				System.out.println("Habitación " + inventario.get(i).getIdentificador()+":");
				System.out.println("     Ubicacion: " + inventario.get(i).getUbicacion());
				
				if(inventario.get(i).isBalcon()) System.out.println("     Cuenta con balcón");
				if(inventario.get(i).isVista()) System.out.println("     Cuenta con Vista");
				if(inventario.get(i).isCocina()) System.out.println("     Cuenta con Cocina Integral");
				
				System.out.println("     Capacidad para Niños: " + inventario.get(i).capacidadNinos());
				System.out.println("     Capacidad para Adultos: " + inventario.get(i).capacidadAdultos());
				System.out.println("     Tipo: " + inventario.get(i).getTipo());
			}
			System.out.println("\n------------------------------------------\n");
			this.menuRecepcionista();
		}else if(opcion.equals("2")){
			
			ArrayList<Reserva> reservas = hotel.getReservas();
			
			for(int i=0;i<reservas.size();i++) {
				System.out.println("\n------------------------------------------\n");
				System.out.println("Reserva N°: " + reservas.get(i).getIdReserva());
				System.out.println("Reservador por: " + reservas.get(i).getNombreCliente());
				System.out.println("  Habitación: " + reservas.get(i).getIdHabitacion());
				System.out.println("  Fecha de reservación: " + reservas.get(i).getFechaInicioString() + " - " + reservas.get(i).getFechaFinString());
			}	
			System.out.println("\n------------------------------------------\n");
			this.menuRecepcionista();
		}else if(opcion.equals("3")) {
			
			ArrayList<Reserva> reservas = hotel.getReservas();
			String idHabitacion = input("Digite el número de la habitación");
			
			for(int i=0;i<reservas.size();i++) {
				
				if(String.valueOf(reservas.get(i).getIdHabitacion()).equals(idHabitacion)) {
					System.out.println("\n------------------------------------------\n");
					System.out.println("Reserva N°: " + reservas.get(i).getIdReserva());
					System.out.println("Reservador por: " + reservas.get(i).getNombreCliente());
					System.out.println("  Habitación: " + reservas.get(i).getIdHabitacion());
					System.out.println("  Fecha de reservación: " + reservas.get(i).getFechaInicioString() + " - " + reservas.get(i).getFechaFinString());
				}
				
			}	
			System.out.println("\n------------------------------------------\n");
			this.menuRecepcionista();
			
			
		}else if(opcion.equals("4")) {
			System.out.println("\n");
			String fechaInicio=input("Digite la fecha inicial de la reserva dd/MM/YYYY");
			String fechaFinal = input("Digite la fecha final de la reserva dd/MM/YYYY");
			
			System.out.println("Dentro de ese rango de fechas tenemos disponibles las siguientes habitaciones: ");
			
			ArrayList<Habitacion> habitacionesDisponibles = hotel.habitacionesPorFecha(fechaInicio, fechaFinal);
			
			for(int i=0;i<habitacionesDisponibles.size();i++) {
				System.out.println("\n------------------------------------------\n");
				System.out.println("Habitación N°: " + habitacionesDisponibles.get(i).getIdentificador());
				System.out.println("     Ubicacion: " + habitacionesDisponibles.get(i).getUbicacion());
				
				if(habitacionesDisponibles.get(i).isBalcon()) System.out.println("     Cuenta con balcón");
				if(habitacionesDisponibles.get(i).isVista()) System.out.println("     Cuenta con Vista");
				if(habitacionesDisponibles.get(i).isCocina()) System.out.println("     Cuenta con Cocina Integral");
				
				System.out.println("     Capacidad para Niños: " + habitacionesDisponibles.get(i).capacidadNinos());
				System.out.println("     Capacidad para Adultos: " + habitacionesDisponibles.get(i).capacidadAdultos());
				System.out.println("     Tipo: " + habitacionesDisponibles.get(i).getTipo());	
			}
			System.out.println("\n------------------------------------------\n");
			this.menuRecepcionista();
			
			
		}
	
	
}

	private void menuAdministrador() throws IOException {
		System.out.println("\n1. Crear una nueva habitación");
		System.out.println("2. Cargar archivo de habitaciones");
		
		String opcion = input("Digite una opción");
		
		if(opcion.equals("1")) {
			hotel.cargarArchivos();
			this.crearHabitacion();
		}else if(opcion.equals("2")){
			this.cargarArchivoHabitaciones();
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
		this.menuAdministrador();
	}
	
	/*
	 * Metodo para cargar las habitaciones desde un archivo
	 */
	
	private void cargarArchivoHabitaciones() {
		System.out.println("El archivo de las habitacion se encuentra en data dentro del proyecto, "
				+ " para efectos de eficacia se hara la carga de este archivo independientemente del tipo de usuario ingresado"
				+ "es decir que no es necesario que siempre que tenga que ingresar el administrador para hacer la carga de datos");
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

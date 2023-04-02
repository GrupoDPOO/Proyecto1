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

	private void menuEmpleado() throws IOException{

		System.out.println("\n -----------SERVICIOS-----------");
		System.out.println("1. Consultar menu y servicios");
		System.out.println("2. Registrar servicio");
		String op = input("Digite una opcion");
		if(op.equals("1")) {
			this.consultarServicios();
			this.menuEmpleado();
		}else if(op.equals("2")) {
			this.consultarServicios();
			String codigoServicio = input("Digite el codigo del producto o servicio que desea adquirir");
			String documento = input("Digite el documento de la persona que desea adquirir el servicio");
			hotel.registrarServicio(codigoServicio, documento);
			System.out.println("Servicio registrado correctamente");
			this.menuEmpleado();
		}
	}
	
	private void consultarServicios() {
		ArrayList<Servicio> servicios = hotel.getServicios();
		
		for(int i=0;i<servicios.size();i++) {
			System.out.println("\n--------------------");
			System.out.println("Código producto: " + servicios.get(i).getCodigo());
			System.out.println("Producto: " + servicios.get(i).getProducto());
			System.out.println("Horario: " +servicios.get(i).getHoraInicial()+ " - " + servicios.get(i).getHoraFinal());
			System.out.println("Precio: "+servicios.get(i).getPrecio());
		}
		System.out.println("\n--------------------");
	}
	
	private void menuRecepcionista() throws IOException, ParseException{

		System.out.println("\n1.Consultar inventario de habitaciones");
		System.out.println("2. Consultar todas las reservas");
		System.out.println("3. Consultar las reservaciones de una habitación en especifico");
		System.out.println("4. Consultar disponibilidad de habitaciones por fecha");
		System.out.println("5. Realizar una reserva");
		System.out.println("6. Generar Factura");
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
			
			this.consultaHabitaciones(fechaInicio, fechaFinal);
			this.menuRecepcionista();
			
			
		}else if(opcion.equals("5")) {
			this.realizarReserva();
			this.menuRecepcionista();
		}else if(opcion.equals("6")) {
			this.generarFactura();
			System.out.println("Factura generado y guardada correctamente");
			this.menuRecepcionista();
		}
	
	
}
	
	private void generarFactura() throws IOException, ParseException {
		
		String documento = input("\nDigite el numero de documento de la persona para generar la factura");
		hotel.generarFactura(documento);
	}
	
	
	private void consultaHabitaciones(String fechaInicio, String fechaFinal) throws ParseException {
		
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
	}
	
	private void realizarReserva() throws ParseException, IOException  {
		
		String fInicial = input("Fecha de ingreso de la reserva dd/MM/yyyy");
		String fFinal = input("Fecha de salidad de la reserva dd/MM/yyyy");
		this.consultaHabitaciones(fInicial, fFinal);
		
		ArrayList<Integer> idHabitacionesReservadas = hotel.idHabDisponibles(fInicial,fFinal);
		
		String idHabitacion = "-1";
		
		ArrayList<Integer> idHabitacionesPorReservar = new ArrayList<>();
		
		while(!idHabitacion.equals("0")) {
			idHabitacion = input("\nDigite los numeros de las habitaciones que desea reservar uno por uno, Marque 0 para finalizar");
				if(!idHabitacion.equals("0")){
					idHabitacionesPorReservar.add(Integer.valueOf(idHabitacion));
				}
				
			
		}
		
		ArrayList<Acompañante> huespedes = new ArrayList<>();
		
		int id = (int) (Math.random()*1000+1);
		int idReserva = (int) (Math.random()*1000+1);
		System.out.println("\n ---Datos de la persona a cargo de la reserva---");
		String nombre = input("Nombre del huesped");
		String documento = input("Documento de identidad");
		String correo = input("Correo electronico");
		String telefono = input("Numero celular");
		int cantidad = Integer.parseInt(input("Numero de personas de la reserva"));
		
		Huesped huesped = new Huesped(idReserva,id,nombre,documento,correo,telefono,cantidad);
		Acompañante principal = new Acompañante(idReserva,id,nombre,documento);
		huespedes.add(principal);
		System.out.println("\nDatos de los acompañantes");
		for(int i=0;i<cantidad;i++) {
			System.out.println("Datos acompañante " + i+1);
			nombre = input("\nNombre");
			documento = input("Documento");
			Acompañante acompañante = new Acompañante(idReserva, id,nombre,documento);
			huespedes.add(acompañante);
		}
		double precioReserva = hotel.tarifaPorReserva(idHabitacionesPorReservar, fInicial, fFinal);
		hotel.registrarReserva(idReserva,idHabitacionesPorReservar, fInicial, fFinal, id, huesped, huespedes,precioReserva);
		
		System.out.println("Registro exitoso el total de su reserva es: " + precioReserva);
		this.menuRecepcionista();

	}

	private void menuAdministrador() throws IOException, ParseException {
		System.out.println("\n1. Crear una nueva habitación");
		System.out.println("2. Cargar archivo de habitaciones");
		
		String opcion = input("Digite una opción");
		
		if(opcion.equals("1")) {
			hotel.cargarArchivos();
			this.crearHabitacion();
			this.menuAdministrador();
		}else if(opcion.equals("2")){
			this.cargarArchivoHabitaciones();
			this.menuAdministrador();
		}
	}
	
	private void crearHabitacion() throws IOException, ParseException {
		
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
	 * Método para ingresar datos por el usuario (se obtuvo del taller 1)
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

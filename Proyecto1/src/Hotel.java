import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Hotel {
	
	private ArrayList<Cama> camas= new ArrayList<>();
	private ArrayList<Habitacion> inventario= new ArrayList<>();
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Reserva> reservas = new ArrayList<>();
	private ArrayList<Tarifa> tarifas=new ArrayList<>();
	private ArrayList<Servicio> servicios = new ArrayList<>();
	private ArrayList<Acompañante> acompañantes = new ArrayList<>();
	private ArrayList<ServicioRegistrado> serviciosRegistrados = new ArrayList<>();
	
	


	public void cargarArchivos() throws IOException, ParseException {
		
		this.cargarCamas();
		this.cargarHabitaciones();
		this.cargarTarifas();
		this.cargarServicios();
		
	}
	
	public void cargarAcompañantes() throws IOException {
		
		FileReader file = new FileReader("./data/acompañantes.txt");
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine(); //Para omitir los titulos

		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			int idReserva = Integer.parseInt(partes[0]);
			int idHuesped = Integer.parseInt(partes[1]);
			String nombre = partes[2];
			String documento = partes[3];
			
			Acompañante acompañante = new Acompañante(idReserva,idHuesped, nombre, documento);
			
			acompañantes.add(acompañante);
			
			linea = br.readLine(); //Salto de linea

		}

		br.close();
	}
	
	public ArrayList<Servicio> getServicios(){
		return servicios;
	}
	private void cargarServicios() throws IOException {
		
		FileReader file = new FileReader("./data/menu.txt");
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine(); //Para omitir los titulos

		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			String codigo = partes[0];
			String producto = partes[1];
			String horaInicial = partes[2];
			String horaFinal = partes[3];
			double precio = Double.parseDouble(partes[4]);
			
			Servicio servicio = new Servicio(codigo,producto,horaInicial, horaFinal, precio);
			
			servicios.add(servicio);
			
			linea = br.readLine(); //Salto de linea

		}

		br.close();
	}
	
	private void cargarCamas() throws IOException {
		
		
		FileReader file = new FileReader("./data/camas.txt");
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine(); //Para omitir los titulos

		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			String tipo = partes[0];
			int ninos = Integer.parseInt(partes[1]);
			int adultos = Integer.parseInt(partes[2]);
			
			Cama cama = new Cama(tipo,ninos,adultos);
			camas.add(cama);
			
			linea = br.readLine(); //Salto de linea

		}

		br.close();
	}
	
private void cargarHabitaciones() throws IOException {
		
		ArrayList<Cama> camasHab=new ArrayList<>();
	
		FileReader file = new FileReader("./data/habitaciones.txt");
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine(); 
		

		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			int identificador = Integer.parseInt(partes[0]);
			String ubicacion = partes[1];
			boolean balcon = Boolean.parseBoolean(partes[2]);
			boolean vista = Boolean.parseBoolean(partes[3]);
			boolean cocina = Boolean.parseBoolean(partes[4]);
			String camas = partes[5];
			
			
			String[] partesCamas = camas.split(",");
			
			for(int i=0;i<partesCamas.length;i++) {
				Cama _cama = consultarCama(partesCamas[i]);
				camasHab.add(_cama);
			}
			
			String tipo = partes[6];
			
			
			Habitacion habitacion = new Habitacion(identificador,ubicacion,balcon,
					vista,cocina,camasHab, tipo);
			
			inventario.add(habitacion);
			
			linea = br.readLine(); //Salto de linea

		}

		br.close();
	}

	private Cama consultarCama(String tipo) {
		
		Cama _cama = null;
		
		for(int i=0;i<camas.size();i++) {
			
			if(camas.get(i).getTipo().equals(tipo)) {
				_cama=camas.get(i);
			}
		}
		
		return _cama;
	}
	
	public void cargarUsuarios() throws IOException {
		
		FileReader file = new FileReader("./data/usuarios.txt");
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine();

		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			String rol = partes[0];
			String nombre = partes[1];
			String documento = partes[2];
			String correo = partes[3];
			String telefono = partes[4];
			String user = partes[5];
			String clave = partes[6];
			
			Usuario usuario = new Usuario(rol,nombre,documento,correo,
					telefono,user,clave);
			
			usuarios.add(usuario);
			
			linea = br.readLine(); //Salto de linea

		}

		br.close();
		
	}
	
	public Usuario validarUsuario(String user, String clave) {
		
		Usuario usuario=null;
		
		for(int i=0;i<usuarios.size();i++) {
			
			if(usuarios.get(i).getUser().equals(user)&&usuarios.get(i).getClave().equals(clave)) {
				usuario=usuarios.get(i);
			}
		}
		
		return usuario;
		
	}
	
	public void guardarHabitacion(int identificador,String ubicacion,boolean balcon,
			boolean vista,boolean cocina,String camas,String tipo) throws IOException {
		
		ArrayList<Cama> camas2 = new ArrayList<>();
		
		int a=0;
		
		String[] partesCamas = camas.split(",");
		
		for(int i=0;i<partesCamas.length;i++) {
			Cama cama = consultarCama(partesCamas[i]);
			camas2.add(cama);
		}
		
		Habitacion hab = new Habitacion(identificador,ubicacion,balcon,vista,cocina,camas2,tipo);
		
		for(int i=0;i<inventario.size();i++) {
			if(inventario.get(i).getIdentificador()==identificador) {
				inventario.remove(i);
				inventario.add(hab);
				a=1;
			}
		}
		if(a==0) {
			inventario.add(hab);
		}
		this.actualizarHabitacionesTXT();
	}
	
	public void actualizarHabitacionesTXT() throws IOException {
		
		File archivoHabitaciones = new File("./data/habitaciones.txt");
		
		if(!archivoHabitaciones.exists()) {
			archivoHabitaciones.createNewFile();
		}else {
			
		}
		
		FileWriter escribir = new FileWriter(archivoHabitaciones);
		PrintWriter linea = new PrintWriter(escribir);
		
		for(int i=0;i<inventario.size();i++) {
			linea.println(inventario.get(i).toString());
		}
		
		linea.close();
		escribir.close();
	}

	public ArrayList<Habitacion> getInventario(){
		return inventario;
	}
	
	public void cargarReservas() throws IOException, ParseException {
		
		FileReader file = new FileReader("./data/reservas.txt");
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine();

		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			int idReserva = Integer.parseInt(partes[0]);
			int idHabitacion = Integer.parseInt(partes[1]);
			String fechaInicio = partes[2];
			String fechaFin = partes[3];
			int idCliente = Integer.parseInt(partes[4]);
			String nombreCliente = partes[5];
			double precio = Double.parseDouble(partes[6]);
			
			Reserva reserva = new Reserva(idReserva, idHabitacion,fechaInicio,fechaFin,idCliente,nombreCliente,precio);
			
			reservas.add(reserva);
			
			
			linea = br.readLine(); //Salto de linea

		}

		br.close();
		
	}
	
	public ArrayList<Reserva> getReservas(){
		return reservas;
	}
	
	/*
	 * Metodo para saber las habitaciones dispobibles en un rango de fechas
	 */
	
	public ArrayList<Habitacion> habitacionesPorFecha(String fechaInicial, String fechaFinal) throws ParseException {
		
		
		
		ArrayList<Habitacion> habitacionesDisponibles = inventario;
		
		ArrayList<Integer> idHabitacionesReservadas = idHabDisponibles(fechaInicial, fechaFinal);
		for(int i=0;i<inventario.size();i++) {
			for(int a=0;a<idHabitacionesReservadas.size();a++) {
				if(inventario.get(i).getIdentificador()==idHabitacionesReservadas.get(a)) {
					
					habitacionesDisponibles.remove(inventario.get(i));
				}
			}
		}
		
		return habitacionesDisponibles;
	}
	
	public ArrayList<Integer> idHabDisponibles(String fechaInicial, String fechaFinal) throws ParseException {
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fInicial = formato.parse(fechaInicial);  
		Date fFinal = formato.parse(fechaFinal); 
		
		ArrayList<Integer> idHabitacionesReservadas = new ArrayList<Integer>();
		
		for(int i=0;i<reservas.size();i++) {
			if(!compararFechas(reservas.get(i).getFechaInicio(), reservas.get(i).getFechaFin(),fInicial,fFinal)) {
				idHabitacionesReservadas.add(reservas.get(i).getIdHabitacion());
				
			}
		}
		
		return idHabitacionesReservadas;
	}
	
	/*
	 * Compara 4 fechas, retorna true si esta disponible o false si no esta disponible en ese rango de fechas
	 */
	private boolean compararFechas(Date fechaReservaInicial, Date fechaReservaFinal, Date fechaConsultaInicial, Date fechaConsultaFinal) {
		
		boolean disponible = false;
		
		if(fechaConsultaInicial.after(fechaReservaFinal) && fechaConsultaFinal.after(fechaReservaFinal)) {
			disponible= true;
		}
		if(fechaConsultaInicial.before(fechaReservaInicial) && fechaConsultaFinal.before(fechaReservaInicial)) {
			disponible =true;
		}
		
		return disponible;
	}
	
	private void cargarTarifas() throws IOException, ParseException {
		
		
		FileReader file = new FileReader("./data/tarifas.txt");
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine(); 
		

		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			String tipoCuarto=partes[0];
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaInicial = formato.parse(partes[1]);  
			Date fechaFinal = formato.parse(partes[2]);
			String[] dias = partes[3].split(",");
			double precio = Double.parseDouble(partes[4]);
			
			Tarifa tarifa = new Tarifa(tipoCuarto,fechaInicial,fechaFinal,dias,precio);
			
			tarifas.add(tarifa);
			
			
			linea = br.readLine(); //Salto de linea

		}

		br.close();
		
	}
	
	public void registrarReserva(int idReserva,ArrayList<Integer> habitaciones, String fInicial,String fFinal,int id,Huesped huesped,ArrayList<Acompañante> huespedes,double precio) throws IOException {
	
		BufferedWriter bw = null;
		File archivo = new File("./data/reservas.txt");
		
		bw = new BufferedWriter(new FileWriter(archivo,true));
		
		for(int i =0;i<habitaciones.size();i++) {
			String mensaje = idReserva +";"+habitaciones.get(i)+";"+fInicial+";"+fFinal+";"+id+";"+huesped.getNombre()+";"+precio+"\n";
			bw.write(mensaje);
		
		}
		
		bw.close();
		
		this.registrarHuesped(idReserva, huesped);
		this.registrarAcompañantes(idReserva, huesped, huespedes);
	}
	
	private void registrarHuesped(int idReserva, Huesped huesped) throws IOException {
		
		BufferedWriter bw = null;
		File archivo = new File("./data/huesped.txt");
		
		bw = new BufferedWriter(new FileWriter(archivo,true));
		
		String mensaje = idReserva + ";"+huesped.getId()+";"+ huesped.getNombre()+";"+huesped.getDocumento()+";"
				+ huesped.getCorreo()+";"+huesped.getTelefono()+";"+huesped.getCantidadPersonas()+"\n";
		bw.write(mensaje);
		bw.close();
		
	}
	
	private void registrarAcompañantes(int idReserva,Huesped huesped,ArrayList<Acompañante> acompañantes) throws IOException {
		
		BufferedWriter bw = null;
		File archivo = new File("./data/acompañantes.txt");
		
		bw = new BufferedWriter(new FileWriter(archivo,true));
		
		for(int i=0;i<huesped.getCantidadPersonas();i++) {
			String mensaje = idReserva + ";"+huesped.getId()+";"+acompañantes.get(i).getNombre()+";"+acompañantes.get(i).getDocumento()+"\n";
			
			bw.write(mensaje);
		
		}
		bw.close();
	}
	
	
	public double tarifaPorReserva(ArrayList<Integer> idHabitaciones, String fInicial, String fFinal ) throws ParseException {
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicial = formato.parse(fInicial);  
		Date fechaFinal = formato.parse(fFinal); 
		
		double precioFinal=0;
		ArrayList<Habitacion> habitacionesPorReservar = new ArrayList<>();
		ArrayList<Double> tarifasPrecio = new ArrayList<>();
		
		for(int i=0;i<idHabitaciones.size();i++) {
			for(int a=0;a<inventario.size();a++) {
				if(inventario.get(a).getIdentificador()==idHabitaciones.get(i)) {
					habitacionesPorReservar.add(inventario.get(a));
				}
			}
		}
		
		for(int i=0;i<habitacionesPorReservar.size();i++) {
			for(int a=0;a<tarifas.size();a++) {
				if(tarifas.get(a).getTipoHabitacion().equals(habitacionesPorReservar.get(i).getTipo())) {
					
						tarifasPrecio.add(tarifas.get(a).getPrecio());
						
				}
			}
		}
		
		for(int i=0;i<tarifasPrecio.size();i++) {
			precioFinal+=tarifasPrecio.get(i); // precio por dia de cada habitacion
		}
		//Calcular los días de la reserva
		long diff =  fechaFinal.getTime()-fechaInicial.getTime();
		TimeUnit time = TimeUnit.DAYS; 
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
		
		return precioFinal*diffrence;
		
	}
	
	public void registrarServicio(String codigo, String documento) throws IOException {
		
		Servicio servicio=null;

		for(int i=0;i<servicios.size();i++) {
			if(servicios.get(i).getCodigo().equals(codigo)) {
				 servicio= servicios.get(i);
			}
		}
		Acompañante acompañante = null;
		this.cargarAcompañantes();
		
		for(int i=0;i<acompañantes.size();i++) {
			if(acompañantes.get(i).getDocumento().equals(documento)) {
				acompañante = acompañantes.get(i);
			}
		}
		String mensaje = acompañante.getIdReserva() +";"+servicio.getProducto() +";" + servicio.getPrecio()+"\n";
		
		BufferedWriter bw = null;
		File archivo = new File("./data/serviciosRegistrados.txt");
		
		bw = new BufferedWriter(new FileWriter(archivo,true));
		
		bw.write(mensaje);
		bw.close();
	}
	
	public void generarFactura(String documento) throws IOException, ParseException {
		Acompañante acompañante = null;
		this.cargarAcompañantes();
		
		for(int i=0; i<acompañantes.size();i++) {
			if(acompañantes.get(i).getDocumento().equals(documento)) {
				acompañante = acompañantes.get(i);
			}
		}
		
		int idFactura = (int) (Math.random()*100+1);
		
		String mensaje = this.generarTextoFactura(acompañante.getIdReserva());
		
		Factura factura = new Factura(idFactura,acompañante.getIdReserva(),mensaje);
		factura.guardarFactura();
		factura.imprimirFacturaEnPantalla();
	}
	
	private void cargarServiciosRegistrados() throws IOException {
		
		FileReader file = new FileReader("./data/serviciosRegistrados.txt");
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine(); //Para omitir los titulos

		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			int idReserva = Integer.parseInt(partes[0]);
			String servicio = partes[1];
			double precio= Double.parseDouble(partes[2]);
			
			ServicioRegistrado servi = new ServicioRegistrado(idReserva,servicio,precio);
			
			serviciosRegistrados.add(servi);

			linea = br.readLine(); //Salto de linea

		}

		br.close();
	}
	private String generarTextoFactura(int idReserva) throws IOException, ParseException {
		
		
		String mensaje="\n    Servicios     |    Precio    \n";
		this.cargarReservas();
		double precio = 0;
		double precioTotal=0;
		for(int i=0;i<reservas.size();i++) {
			if(reservas.get(i).getIdReserva()==idReserva) {
				precio = reservas.get(i).getPrecio();
			}
		}
		
		mensaje +="   Alojamiento ............ "+precio+"\n";
		precioTotal +=precio;
		this.cargarServiciosRegistrados();
		for(int i=0;i<serviciosRegistrados.size();i++) {
			if(serviciosRegistrados.get(i).getIdReserva()==idReserva) {
				mensaje +="    " + serviciosRegistrados.get(i).getServicio()+" ..........." + serviciosRegistrados.get(i).getPrecio()+"\n";
				precioTotal +=serviciosRegistrados.get(i).getPrecio();
			}
		}
		
		mensaje +="----------------------\n";
		mensaje +="       TOTAL: " + precioTotal;
		
		return mensaje;
	}
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Hotel {
	
	private ArrayList<Cama> camas= new ArrayList<>();
	private ArrayList<Habitacion> inventario= new ArrayList<>();
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	
	


	public void cargarArchivos() throws IOException {
		
		this.cargarCamas();
		this.cargarHabitaciones();
		
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
			boolean disponible = Boolean.parseBoolean(partes[6]);
			
			String[] partesCamas = camas.split(",");
			
			for(int i=0;i<partesCamas.length;i++) {
				Cama _cama = consultarCama(partesCamas[i]);
				camasHab.add(_cama);
			}
			
			String tipo = partes[6];
			
			
			Habitacion habitacion = new Habitacion(identificador,ubicacion,balcon,
					vista,cocina,camasHab, tipo, disponible);
			
			inventario.add(habitacion);
			
			linea = br.readLine(); //Salto de linea

		}

		br.close();
	}

	private Cama consultarCama(String tipo) {
		
		Cama _cama = null;
		
		for(int i=0;i<camas.size();i++) {
			
			if(camas.get(i).equals(tipo)) {
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
			boolean vista,boolean cocina,String camas,String tipo, boolean disponible) throws IOException {
		
		ArrayList<Cama> camas2 = new ArrayList<>();
		
		int a=0;
		
		String[] partesCamas = camas.split(",");
		
		for(int i=0;i<partesCamas.length;i++) {
			Cama cama = consultarCama(partesCamas[i]);
			camas2.add(cama);
		}
		
		Habitacion hab = new Habitacion(identificador,ubicacion,balcon,vista,cocina,camas2,tipo,disponible);
		
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

}

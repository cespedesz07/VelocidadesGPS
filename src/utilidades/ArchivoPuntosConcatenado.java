package utilidades;

import interfaz.PanelConcatenadoPuntos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import clasesVelocidad.Punto;
import clasesVelocidad.RedVial;

public class ArchivoPuntosConcatenado extends Thread{
	
	
	
	public static final String[] COLUMNAS_PUNTOS = { "FID_2", "DATE", "TIME", "X", "Y", "HEIGHT" };
	
	
	
	//Atributos
	private String rutaCarpetaOrigen;			// Ruta de la carpeta donde se encuentran los archivos .csv
	private String rutaCarpetaDestino;			// Ruta de la carpeta donde se va a almacenar el archivo con los datos concatenados
	private String nombreArchivoResultado;		// Nombre del archivo csv con los datos concatenados
	private String contenidoTemp;				// Variable temporal que contiene las lineas a concatenar en el archivo final
	private HashMap<String, String> archivosErroneos; //HashMap que contiene el nombre de los archivos vacios o archivos sin formato .csv
	
	private String rutaOrigen;					// Ruta de Origen (este atributo se usa para cuando se tiene el 
												//  archivo con los puntos concatenados, para postereiormente calcular la velocidad
												//  promedio de c/arco)
	private String delimitador;
	private ArrayList<String[]> contenidoPuntos;
	
	private PanelConcatenadoPuntos panelConcatenadoPuntos;
	
	
	
	//Métodos
	public ArchivoPuntosConcatenado( String carpetaOrigen, String rutaOrigen, PanelConcatenadoPuntos panelConcatenadoPuntos ){
		this.rutaCarpetaOrigen = carpetaOrigen;
		this.rutaCarpetaDestino = carpetaOrigen + "//resultado";
		this.nombreArchivoResultado = "concatenado.csv";		
		this.contenidoTemp = "";
		this.rutaOrigen = rutaOrigen;
		this.contenidoPuntos = new ArrayList<String[]>();
		this.archivosErroneos = new HashMap<String, String>();
		this.panelConcatenadoPuntos = panelConcatenadoPuntos;
	} 
	
	
	public HashMap<String, String> getArchivosErroneos(){
		return this.archivosErroneos;
	}
	
	
	
	/**
	 * Método que lee los archivos .csv de la carpeta definida en el parametro rutaCarpetaOrigen
	 * y concatena estos archivos para generar un .csv final con el nombre definido en el parámetro
	 * nombreArchivoResultado, el cual se almacena en la carpeta definida en el archivo rutaCarpetaDestino
	 * @throws Exception
	 */
	public void generarArchivoConcatenado() throws Exception{
		int indiceTime = -1;
		String delimSeleccionado = this.panelConcatenadoPuntos.getDelimSeleccionado();
		File carpeta = new File( this.rutaCarpetaOrigen );			
		if ( carpeta.exists() ){														//--Se verifica si la carpeta existe		
			if ( carpeta.isDirectory() ){												//--Se verifica si la carpeta realmente es un folder
				File[] listaArchivosCSV = carpeta.listFiles();
				if ( listaArchivosCSV.length != 0 ){												//Se verifica si la carpeta está vacia
					this.archivosErroneos = verificarCarpeta(listaArchivosCSV);	//Se verifica si la carpeta tiene archivos .csv y que no estén vacios
					if ( archivosErroneos.isEmpty() ){												
					
						//Se realiza la lectura linea x linea de cada archivo CSV
						double total = obtenerTamañoTotal( listaArchivosCSV );   //Retorna el total de lineas de cada archivo CSV
						int contadorLinea = 0;
						for ( int i=0; i<listaArchivosCSV.length; i++ ){					
							File archivoCSV = listaArchivosCSV[i];						
							this.panelConcatenadoPuntos.setAreaLog( this.panelConcatenadoPuntos.getAreaLog() + 
									String.format( "%d - Leyendo: %s \n", i+1, archivoCSV.getName() ) );
									
							
							Scanner entrada = new Scanner( archivoCSV );																											//Se verifica que el archivo .csv no esté vacio
							if ( i == 0 ){															//Si el archivo a leer es diferente al primero de la lista...
								String primeraLinea = entrada.nextLine();
								indiceTime = buscarIndice( COLUMNAS_PUNTOS[2], primeraLinea.split(",") );
								this.contenidoTemp += primeraLinea.replace(",", delimSeleccionado) + "\n";
							}
							else{
								entrada.nextLine();														//...se hace caso omiso a la lectura de la primer linea (la cabecera)
							}
							
													
							while ( entrada.hasNext() ){								
								String linea = entrada.nextLine();
								contadorLinea += 1;
								String[] lineaPartida = linea.split( "," );
								lineaPartida[ indiceTime ] = String.format( "=\"%s\"", lineaPartida[ indiceTime ] );
								linea = Arrays.toString( lineaPartida ).replace(", ", delimSeleccionado);
								linea = linea.replace("[", "").replace("]", "");
								this.contenidoTemp += linea + "\n";
								
								double progreso = ( ((double)contadorLinea ) / total ) * 100;									
								this.panelConcatenadoPuntos.setProgreso( (int)progreso );
							}
							this.panelConcatenadoPuntos.setPosicionAreaLog( this.contenidoTemp.length() );
						}
						System.out.println( this.contenidoTemp );
					}
					else{
						throw new Exception( "La carpeta contiene archivos que no son .csv o Contiene archivos vacios "
								+ "\nPor favor revisar el Log para ver archivos inválidos." );
					}
					
					
					//Al finalizar la lectura de los archivos .csv, se crea la carpeta con el archivo concatenado
					File carpetaDestino = new File( this.rutaCarpetaDestino );
					carpetaDestino.mkdir();
					
					PrintWriter archivoConcatenado = new PrintWriter( this.rutaCarpetaDestino + "//" +  this.nombreArchivoResultado );
					archivoConcatenado.write( this.contenidoTemp );
					archivoConcatenado.close();
				}
				else{
					throw new Exception( "La carpeta " + this.rutaCarpetaOrigen + " no contiene ningun archivo" );					
				}
			}
			else{				
				throw new Exception ( "La ruta " + this.rutaCarpetaOrigen + " no corresponde a un directorio." );
			}
		}
		else{
			throw new Exception ( "No existe el archivo/ruta seleccionada." );
		}		
	}
	
	
	/**
	 * Método para obtener el numero de lineas totales de cada archivo CSV. 
	 * Ideal para ajustar la barra de progreso. 
	 * @param listaArchivos
	 * @return
	 */
	private int obtenerTamañoTotal( File[] listaArchivos ){
		int total = 0;
		for ( File archivo : listaArchivos ){
			try{
				total += new Scanner( archivo ).useDelimiter("\\A").next().split("\n").length;
			}
			catch ( Exception error ){
				error.printStackTrace();
			}
		}
		return total;
	}
	
	
	
	public int buscarIndice( String columna, String[] arreglo ){
		for ( int i=0; i<arreglo.length; i++ ){
			if ( arreglo[i].equals( columna ) ){
				return i;
			}
		}
		return -1;
	}
	
	
	
	/**
	 * Método para capturar la extension de un archivo (objeto File).
	 */
	private String obtenerExtension( File archivo ){
		String[] partido = archivo.getAbsolutePath().toLowerCase().split("\\.");
		return partido[ partido.length - 1 ];
	}
	
	
	
	
	/**
	 * Método para verificar que la carpeta contenga archivos .csv y no estén vacios
	 * Para aquellos archivos que no sean csv o estén vacios, los agrega en un HashMap
	 * Retorna este HashMap con el nombre de los archivos erróneos como clave y
	 * la descripción del respectivo error como valor.  	
	 */
	private HashMap<String, String> verificarCarpeta( File[] listaArchivos ){
		HashMap<String, String> archivosErroneos = new HashMap<String, String>();
		for ( File archivo : listaArchivos ){
			if ( archivo.isDirectory() ){
				archivosErroneos.put( archivo.getName(), "Carpeta" );
			}
			if (  !obtenerExtension( archivo ).equals( "csv" ) ){					//Se verifica si todos los archivos dentro de la carpeta son .csv
				archivosErroneos.put( archivo.getName(), "Archivo no csv" );
			}
			if ( archivo.length() == 0 ){											
				archivosErroneos.put( archivo.getName(), "Archivo vacío" );
			}
			
		}
		return archivosErroneos;
	}
	
	
	
	
	
	/**
	 * Método que carga el archivo de los puntos del GPS concatenado y los agrega al arreglo temporal
	 * 
	 */
	public void cargarPuntosConcatenados() throws Exception{
		File puntosGPS = new File( this.rutaOrigen );
		if ( puntosGPS.exists() ){
			if ( puntosGPS.isFile() ){
				if ( obtenerExtension( puntosGPS ).equals( "csv" ) ){
					Scanner entrada = new Scanner( puntosGPS );
					String primerLinea = entrada.nextLine();
					if ( primerLinea.contains(";") ){
						this.delimitador = ";";
					}
					else if ( primerLinea.contains(",") ){
						this.delimitador = ",";
					}
					this.contenidoPuntos.add( primerLinea.split(this.delimitador) );
					while ( entrada.hasNext() ){
						String[] linea = entrada.nextLine().split( this.delimitador );						
						this.contenidoPuntos.add( linea );
					}					
				}
				else{
					throw new Exception( "El archivo seleccionado no tiene extensión .csv" );
				}
			}
			else{
				throw new Exception( "El objeto seleccionado no es un archivo." );
			}
		}
		else{
			throw new Exception( "El archivo no existe" );
		}
	}
	
	
	
	public void inicializarPuntos( RedVial redVial ) throws Exception{
		//Primero se obtiene los indices de las columnas
		List<String> columnasArchivoGPS = Arrays.asList( this.contenidoPuntos.get(0) );
		ArrayList<Integer> indicesColumnas = new ArrayList<Integer>();
		ArrayList<String> columnasNoEncontradas = new ArrayList<String>();
		int indiceColumna = 0;
		for ( String columna : COLUMNAS_PUNTOS ){
			indiceColumna = columnasArchivoGPS.indexOf( columna );
			if ( indiceColumna != -1 ){
				indicesColumnas.add( indiceColumna );
			}
			else{
				columnasNoEncontradas.add( columna );				
			}
		}
		if ( !columnasNoEncontradas.isEmpty() ){
			throw new Exception( "Las siguientes columnas no ha sido encontrada en el archivo GPS: "
					+ "\n" + Arrays.toString( columnasNoEncontradas.toArray() ) );
		}
		else{		
			//Luego se inicializan los puntos y se agregan a los arcos de la red vial
			for ( int i=1; i<this.contenidoPuntos.size(); i++ ){
				String[] fila = this.contenidoPuntos.get(i);				
				long index = Long.parseLong( fila[ indicesColumnas.get(0) ].trim() );
				String fecha = fila[ indicesColumnas.get(1) ];
				String hora = String.format( "%06d", Integer.valueOf(fila[ indicesColumnas.get(2) ]) );
				double x = obtenerCoordenada( fila[ indicesColumnas.get(3) ] );
				double y = obtenerCoordenada( fila[ indicesColumnas.get(4) ] );
				int altitud = Integer.valueOf( fila[ indicesColumnas.get(5) ] );
				//int fid_2 = Integer.valueOf( fila[ indicesColumnas.get(6) ] );
				Punto punto = new Punto(index, fecha, hora, x, y, altitud);				
				redVial.ubicarPunto( punto );
			}
			this.contenidoTemp = null;
			System.gc();
		}
				
	}
	
	
	private Double obtenerCoordenada( String numero ){
		if ( numero.contains( "," ) ){
			numero = numero.replace( ",", "." );
		}
		return Double.parseDouble( numero );
	}
	
	
	

}
package utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import clasesVelocidad.Arco;
import clasesVelocidad.Punto;

public class ArchivoPuntosConcatenado{
	
	
	
	private static final String[] COLUMNAS_PUNTOS = { "INDEX", "DATE", "TIME", "LATITUDE N/S", "LONGITUDE E/W", "HEIGTH" };
	
	
	
	//Atributos
	private String rutaCarpetaOrigen;			// Ruta de la carpeta donde se encuentran los archivos .csv
	private String rutaCarpetaDestino;			// Ruta de la carpeta donde se va a almacenar el archivo con los datos concatenados
	private String nombreArchivoResultado;		// Nombre del archivo csv con los datos concatenados
	private String contenidoTemp;				// Variable temporal que contiene las lineas a concatenar en el archivo final
	
	private String rutaOrigen;					// Ruta de Origen (este atributo se usa para cuando se tiene el 
												//  archivo con los puntos concatenados, para postereiormente calcular la velocidad
												//  promedio de c/arco)
	private String delimitador;
	private ArrayList<String[]> contenidoPuntos;
	
	
	
	//Métodos
	public ArchivoPuntosConcatenado( String carpetaOrigen ){
		this.rutaCarpetaOrigen = carpetaOrigen;
		this.rutaCarpetaDestino = carpetaOrigen + "//resultado";
		this.nombreArchivoResultado = "concatenado.csv";		
		this.contenidoTemp = "";
		this.rutaOrigen = "";
		this.contenidoPuntos = new ArrayList<String[]>();
	} 
	
	
	
	/**
	 * Método que lee los archivos .csv de la carpeta definida en el parametro rutaCarpetaOrigen
	 * y concatena estos archivos para generar un .csv final con el nombre definido en el parámetro
	 * nombreArchivoResultado, el cual se almacena en la carpeta definida en el archivo rutaCarpetaDestino
	 * @throws Exception
	 */
	public void generarArchivoConcatenado() throws Exception{
		File carpeta = new File( this.rutaCarpetaOrigen );			
		if ( carpeta.exists() ){														//--Se verifica si la carpeta existe		
			if ( carpeta.isDirectory() ){												//--Se verifica si la carpeta realmente es un folder
				File[] listaArchivosCSV = carpeta.listFiles();
				if ( listaArchivosCSV.length != 0 ){
					
					//Se realiza la lectura linea x linea de cada archivo CSV
					for ( int i=0; i<listaArchivosCSV.length; i++ ){					
						File archivoCSV = listaArchivosCSV[i];
						//System.out.printf( "%d Leyendo: %s \n", i, archivoCSV.getName() );
						if (  obtenerExtension( archivoCSV ).equals( "csv" )  ){					//--Si todos los archivos dentro de la carpeta son .csv
							Scanner entrada = new Scanner( archivoCSV );
							
							if ( i != 0 ){															//Si el archivo a leer es diferente al primero de la lista...
								System.out.println( "Exceptuada: " + entrada.nextLine() );			//...se hace caso omiso a la lectura de la primer linea (la cabecera)
							}							
							while ( entrada.hasNext() ){								
								String linea = entrada.nextLine(); 
								this.contenidoTemp += linea + "\n";
							}
							
						}
						else{
							throw new Exception( "La carpeta " + this.rutaCarpetaOrigen + "contiene archivos/carpetas con formato diferente a .csv" );
						}
						
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
	 * Método para capturar la extension de un archivo (objeto File).
	 */
	private String obtenerExtension( File archivo ){
		String[] partido = archivo.getAbsolutePath().toLowerCase().split("\\.");
		return partido[ partido.length - 1 ];
	}
	
	
	
	public void cargarPuntosConcatenados( String ruta ) throws FileNotFoundException{		
		this.rutaOrigen = ruta;
		File puntosGPS = new File( this.rutaOrigen );
		if ( puntosGPS.exists() ){
			if ( puntosGPS.isFile() ){
				Scanner entrada = new Scanner( puntosGPS );
				
				if ( entrada.hasNext(";") ){
					this.delimitador = ";";
				}
				else if ( entrada.hasNext(",") ){
					this.delimitador = ",";
				}
				while ( entrada.hasNext() ){
					String[] linea = entrada.nextLine().split( this.delimitador );
					this.contenidoPuntos.add( linea );
				}
			}
		}
	}
	
	
	
	public void inicializarPuntos(){
		//Primero se obtiene los indices de las columnas
		List<String> columnasRedVial = Arrays.asList( this.contenidoPuntos.get(0) );
		ArrayList<Integer> indicesColumnas = new ArrayList<Integer>();
		int indiceColumna = 0;
		for ( String columna : COLUMNAS_PUNTOS ){
			indiceColumna = columnasRedVial.indexOf( columna );
			indicesColumnas.add( indiceColumna );
		}
		
		//Luego se inicializan los puntos y se agregan a los arcos de la red vial
		for ( int i=1; i<this.contenidoPuntos.size(); i++ ){
			String[] fila = this.contenidoPuntos.get(i);
			int index = Integer.valueOf( fila[0] );
			String fecha = fila[1];
			String hora = fila[2];
			double latitud = Double.parseDouble( fila[3] );
			double longitud = Double.parseDouble( fila[4] );
			int altitud = Integer.valueOf( fila[5] );
			Punto punto = new Punto(index, fecha, hora, latitud, longitud, altitud);
			this.redVial.
		}
				
	}
	
	
	

}
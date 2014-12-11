package utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

public class ArchivoPuntosConcatenado{
	
	
	
	//Atributos
	private String rutaCarpetaOrigen;			//Ruta de la carpeta donde se encuentran los archivos .csv
	private String rutaCarpetaDestino;			//Ruta de la carpeta donde se va a almacenar el archivo con los datos concatenados
	private String nombreArchivoResultado;		//Nombre del archivo csv con los datos concatenados
	private String contenidoTemp;				//Arreglo temporal que contiene las lineas a concatenar en el archivo final
	
	
	
	//Métodos
	public ArchivoPuntosConcatenado( String carpetaOrigen ){
		this.rutaCarpetaOrigen = carpetaOrigen;
		this.rutaCarpetaDestino = carpetaOrigen + "//resultado";
		this.nombreArchivoResultado = "concatenado.csv";
		this.contenidoTemp = "";
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
						System.out.printf( "%d Leyendo: %s \n", i, archivoCSV.getName() );
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
	
	
	

}
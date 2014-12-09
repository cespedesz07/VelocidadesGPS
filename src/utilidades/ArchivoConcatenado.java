package utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class ArchivoConcatenado{
	
	
	
	//Atributos
	private String rutaCarpetaOrigen;			//Ruta de la carpeta donde se encuentran los archivos .csv
	private String rutaCarpetaDestino;			//Ruta de la carpeta donde se va a almacenar el archivo con los datos concatenados
	private String nombreArchivoResultado;		//Nombre del archivo csv con los datos concatenados
	private String contenidoTemp;				//Arreglo temporal que contiene las lineas a concatenar en el archivo final
	
	
	
	//M�todos
	public ArchivoConcatenado( String carpetaOrigen ){
		this.rutaCarpetaOrigen = carpetaOrigen;
		this.rutaCarpetaDestino = carpetaOrigen + "//resultado";
		this.nombreArchivoResultado = "concatenado.csv";
		this.contenidoTemp = "";
	} 
	
	
	
	/**
	 * M�todo que lee los archivos .csv de la carpeta definida en el parametro rutaCarpetaOrigen
	 * y concatena estos archivos para generar un .csv final con el nombre definido en el par�metro
	 * nombreArchivoResultado, el cual se almacena en la carpeta definida en el archivo rutaCarpetaDestino
	 * 
	 */
	public boolean generarArchivoConcatenado() throws Exception{
		boolean generoArchivo = false;
		File carpeta = new File( this.rutaCarpetaOrigen );			
		if ( carpeta.exists() ){			
			if ( carpeta.isDirectory() ){			
				File[] listaArchivosCSV = carpeta.listFiles();
				if ( listaArchivosCSV.length != 0 ){
					
					//Se realiza la lectura linea x linea de cada archivo CSV
					for ( int i=0; i<listaArchivosCSV.length; i++ ){					
						File archivoCSV = listaArchivosCSV[i];
						if (  obtenerExtension( archivoCSV ).equals( "csv" )  ){
							Scanner entrada = new Scanner( archivoCSV );
							
							while ( entrada.hasNext() ){
								if ( i != 0 ){					//Si el archivo a leer es diferente al primero de la lista...
									entrada.next();				//...se hace caso omiso a la lectura de la primer linea (la cabecera)
								}
								String linea = entrada.nextLine(); 
								this.contenidoTemp += linea + "\n";
							}
						}
						else{
							throw new Exception( "La carpeta " + this.rutaCarpetaOrigen + " no contiene archivos .csv" );
						}
						
					}
					
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
	
	
	private String obtenerExtension( File archivo ){
		String[] partido = archivo.getAbsolutePath().toLowerCase().split("\\.");
		return partido[ partido.length - 1 ];
	}
	

}
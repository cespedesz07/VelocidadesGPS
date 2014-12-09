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
	
	
	
	//Métodos
	public ArchivoConcatenado( String carpetaOrigen ){
		this.rutaCarpetaOrigen = carpetaOrigen;
		this.rutaCarpetaDestino = carpetaOrigen + "//resultado";
		this.nombreArchivoResultado = "concatenado.csv";
		this.contenidoTemp = "";
	} 
	
	
	
	/**
	 * Método que lee los archivos .csv de la carpeta definida en el parametro rutaCarpetaOrigen
	 * y concatena estos archivos para generar un .csv final con el nombre definido en el parámetro
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
						Scanner entrada = new Scanner( archivoCSV );
						
						while ( entrada.hasNext() ){
							if ( i == 0 ){
								entrada.next();
							}
							String linea = entrada.nextLine(); 
							this.contenidoTemp += linea + "\n";
						}
					}
					
				}
				else{
					
				}
			}
			else{
				throw new Exception ( "La ruta seleccionada no corresponde a un directorio." );
			}
		}
		else{
			throw new Exception ( "No existe el archivo/ruta seleccionada." );
		}
		try{
			
			Scanner entrada = new Scanner( archivo );			
			while ( entrada.hasNext() ){
				String[] linea = entrada.nextLine().split(",");
				System.out.println( Arrays.toString( linea ) );
			}
		}
		catch ( Exception error ){
			error.printStackTrace();			
		}
		System.out.println( "Archivo Leido Correctamente" );
	}
	

}
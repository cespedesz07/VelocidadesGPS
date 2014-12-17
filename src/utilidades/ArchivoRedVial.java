package utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import clasesVelocidad.Arco;
import clasesVelocidad.RedVial;

public class ArchivoRedVial {
	
	
	private static final String[] COLUMNAS_RED_VIAL = { "Layer", "ID_Via", "Direccion", "Latitud1", "Longitud1", "Latitud2", "Longitud2" };		
	private String rutaOrigen;
	private ArrayList<String[]> contenidoRedVial;	
	private String delimitador;		
	private RedVial redVial;
	
	
	
	
	public ArchivoRedVial( String rutaOrigen ){
		this.rutaOrigen = rutaOrigen;
		this.contenidoRedVial = new ArrayList<String[]>();		
		this.delimitador = "";			
		this.redVial = new RedVial();			
	}
	
	
	
	
	public RedVial getRedVial(){
		return this.redVial;
	}	
	
	
	
	
	/**
	 * Método que lee el archivo de la red vial y carga el contenido de la red vial
	 * a un arreglo temporal (contenidoRedVial)	 
	 */
	public void cargarRedVial() throws Exception{
		File redVial = new File( this.rutaOrigen );
		if ( redVial.exists() ){
			if ( redVial.isFile() ){
				if ( obtenerExtension( redVial ).equals( "csv" ) ){
					Scanner entrada = new Scanner( redVial );				
					if ( entrada.hasNext(";") ){
						this.delimitador = "\\;";
					}
					else if ( entrada.hasNext(",") ){
						this.delimitador = "\\,";
					}				
					System.out.println( this.delimitador );
					while ( entrada.hasNext() ){
						String linea = entrada.nextLine();
						String[] lineaPartida = linea.split( this.delimitador );
						this.contenidoRedVial.add( lineaPartida );
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
	
	
	
	
	/**
	 * Método que recorre el contenido de la red vial (previamente cargado con el método cargarRedVial())
	 * e inicializa las instancias de los Arcos, además de agregarlos al objeto RedVial
	 */
	public void inicializarArcos(){
		//Primero se obtiene los indices de las columnas
		List<String> columnasRedVial = Arrays.asList( this.contenidoRedVial.get(0) );
		ArrayList<Integer> indicesColumnas = new ArrayList<Integer>();
		int indiceColumna = 0;
		for ( String columna : COLUMNAS_RED_VIAL ){
			indiceColumna = columnasRedVial.indexOf( columna );
			indicesColumnas.add( indiceColumna );
		}
		
		int idVia = 0;
		String layer = "", direccion = "";
		double latitud1 = 0.0, longitud1 = 0.0, latitud2 = 0.0, longitud2 = 0.0; 
		//Luego de Tener los indices, se utilizan para consultar las columnas de interes
		for ( int i=1; i<this.contenidoRedVial.size(); i++ ){
			String[] fila = this.contenidoRedVial.get(i);
			System.out.println( Arrays.toString(fila) );
			layer = fila[ indicesColumnas.get(0) ];
			idVia = Integer.valueOf( fila[ indicesColumnas.get(1) ] );
			direccion = fila[ indicesColumnas.get(2) ];
			latitud1 = Double.parseDouble( fila[ indicesColumnas.get(3) ] );
			longitud1 = Double.parseDouble( fila[ indicesColumnas.get(4) ] );
			latitud2 = Double.parseDouble( fila[ indicesColumnas.get(5) ] );
			longitud2 = Double.parseDouble( fila[ indicesColumnas.get(6) ] );			
			Arco arco = new Arco( idVia, layer, direccion, latitud1, longitud1, latitud1, longitud1 );
			this.redVial.agregarArco(arco);
		}
	}
	
	
	
	/**
	 * Método para capturar la extension de un archivo (objeto File).
	 */
	private String obtenerExtension( File archivo ){
		String[] partido = archivo.getAbsolutePath().toLowerCase().split("\\.");
		String otro = partido[ partido.length - 1 ];
		return otro;
	}
	
	

}

package utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import clasesVelocidad.Arco;
import clasesVelocidad.RedVial;

public class ArchivoRedVial {
	
	
	public static final String[] COLUMNAS_RED_VIAL = { "ID_VIA", "DIRECC", "X1", "Y1", "X2", "Y2" };		
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
					String primerLinea = entrada.nextLine();
					if ( primerLinea.contains(";") ){
						this.delimitador = ";";
					}
					else if ( primerLinea.contains(",") ){
						this.delimitador = ",";
					}
					this.contenidoRedVial.add( primerLinea.split(this.delimitador) );
					while ( entrada.hasNext() ){
						String[] linea = entrada.nextLine().split( this.delimitador );
						this.contenidoRedVial.add( linea );
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
	public void inicializarArcos() throws Exception{
		//Primero se obtiene los indices de las columnas
		List<String> columnasRedVial = Arrays.asList( this.contenidoRedVial.get(0) );
		ArrayList<Integer> indicesColumnas = new ArrayList<Integer>();
		ArrayList<String> columnasNoEncontradas = new ArrayList<String>();
		int indiceColumna = 0;
		for ( String columna : COLUMNAS_RED_VIAL ){
			indiceColumna = columnasRedVial.indexOf( columna );
			if ( indiceColumna != -1 ){
				indicesColumnas.add( indiceColumna );
			}
			else{
				columnasNoEncontradas.add( columna );				
			}
			
		}		
		if ( !columnasNoEncontradas.isEmpty() ){
			throw new Exception( "Las siguientes columnas no ha sido encontrada en la Red Vial: "
					+ "\n" + Arrays.toString( columnasNoEncontradas.toArray() ) );
		}
		else{		
			int idVia = 0;
			String direccion = "";
			double x1 = 0.0, y1 = 0.0, x2 = 0.0, y2 = 0.0; 
			//Luego de Tener los indices, se utilizan para consultar las columnas de interes
			for ( int i=1; i<this.contenidoRedVial.size(); i++ ){
				String[] fila = this.contenidoRedVial.get(i);
				idVia = Integer.valueOf( fila[ indicesColumnas.get(0) ] );
				direccion = fila[ indicesColumnas.get(1) ];
				x1 = obtenerCoordenada( fila[ indicesColumnas.get(2) ] );
				y1 = obtenerCoordenada( fila[ indicesColumnas.get(3) ] );	
				x2 = obtenerCoordenada( fila[ indicesColumnas.get(4) ] );
				y2 = obtenerCoordenada( fila[ indicesColumnas.get(5) ] );							
				Arco arco = new Arco( idVia, direccion, x1, y1, x2, y2 );				
				this.redVial.agregarArco(arco);				
			}
			this.contenidoRedVial = null;
			System.gc();
		}
	}
	
	
	
	private Double obtenerCoordenada( String numero ){
		if ( numero.contains( "," ) ){
			numero = numero.replace( ",", "." );
		}
		return Double.parseDouble( numero );
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

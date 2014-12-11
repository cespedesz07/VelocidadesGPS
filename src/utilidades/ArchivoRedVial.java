package utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import clasesVelocidad.Arco;

public class ArchivoRedVial {
	
	
	private static final String[] COLUMNAS = { "Layer", 	"longitud", "ID_Via", 	 "Direccion", 
											   "Longitud1", "Latitud1", "Longitud2", "Latitud2" };		
	private String rutaOrigen;
	private ArrayList<String[]> contenidoRedVial;	
	private String delimitador;	
	
	private ArrayList<Arco> arregloArcos;
	
	
	
	
	public ArchivoRedVial( String rutaOrigen ){
		this.rutaOrigen = rutaOrigen;
		this.contenidoRedVial = new ArrayList<String[]>();		
		this.delimitador = "";		
		
		this.arregloArcos = new ArrayList<Arco>();			
	}
	
	
	
	public void cargarRedVial() throws Exception{
		File redVial = new File( this.rutaOrigen );
		if ( redVial.exists() ){
			if ( redVial.isFile() ){
				Scanner entrada = new Scanner( redVial );
				
				if ( entrada.hasNext(";") ){
					this.delimitador = ";";
				}
				else if ( entrada.hasNext(",") ){
					this.delimitador = ",";
				}				
				
				while ( entrada.hasNext() ){
					String[] lineaPartida = entrada.nextLine().split( this.delimitador );
					this.contenidoRedVial.add( lineaPartida );
				}
			}
		}
	}
	
	
	
	private void obtenerValoresColumnas(){
		//Primero se obtiene los indices de las columnas
		List<String> columnasRedVial = Arrays.asList( this.contenidoRedVial.get(0) );
		ArrayList<Integer> indicesColumnas = new ArrayList<Integer>();
		for ( String columna : COLUMNAS ){
			indicesColumnas.in
		}
		for ( int i=1; i<this.contenidoRedVial.size(); i++ ){
			
		}
	}

}

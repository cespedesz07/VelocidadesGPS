package utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import clasesVelocidad.Arco;

public class ArchivoRedVial {
	
	
	private static final String[] COLUMNAS = { "Layer",     "ID_Via", 	"Direccion", 
											   "Latitud1", "Longitud1", "Latitud2", "Longitud2" };		
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
		int indiceColumna = 0;
		for ( String columna : COLUMNAS ){
			indiceColumna = columnasRedVial.indexOf( columna );
			indicesColumnas.add( indiceColumna );
		}
		
		int idVia = 0;
		String layer = "", direccion = "";
		double latitud1 = 0.0, longitud1 = 0.0, latitud2 = 0.0, longitud2 = 0.0; 
		//Luego de Tener los indices, se utilizan para consultar las columnas de interes
		for ( int i=1; i<this.contenidoRedVial.size(); i++ ){
			String[] fila = this.contenidoRedVial.get(i);
			layer = fila[ indicesColumnas.get(0) ];
			idVia = Integer.valueOf( fila[ indicesColumnas.get(1) ] );
			direccion = fila[ indicesColumnas.get(2) ];
			latitud1 = Double.parseDouble( fila[ indicesColumnas.get(3) ] );
			longitud1 = Double.parseDouble( fila[ indicesColumnas.get(4) ] );
			latitud2 = Double.parseDouble( fila[ indicesColumnas.get(5) ] );
			longitud2 = Double.parseDouble( fila[ indicesColumnas.get(6) ] );			
			Arco arco = new Arco( idVia, layer, direccion, latitud1, longitud1, latitud1, longitud1 );
			this.arregloArcos.add( arco );
		}
	}

}

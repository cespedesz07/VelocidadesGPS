package clasesVelocidad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.ws.util.StringUtils;

import utilidades.ArchivoPuntosConcatenado;
import utilidades.ArchivoRedVial;



public class RedVial {
	
	
	//Atributos
	private ArrayList<Arco> arregloArcos;
	
	
	//Métodos
	public RedVial(){
		this.arregloArcos = new ArrayList<Arco>();
	}


	public ArrayList<Arco> getArregloArcos() {
		return arregloArcos;
	}


	public void setArregloArcos(ArrayList<Arco> arregloArcos) {
		this.arregloArcos = arregloArcos;
	}
	
	public void agregarArco( Arco arco ){
		this.arregloArcos.add( arco );
	}
	
	
	/**
	 * Método para Ubicar un punto en un arco 
	 * de acuerdo a la latitud y longitud del punto respecto a las 
	 * latitudes y longitudes 1 y 2 del arco.
	 */
	public void ubicarPunto( Punto punto ){
		for ( Arco arco : this.arregloArcos ){			
			
			if ( arco.getX1() <= punto.getX()  &&  punto.getX() <= arco.getX2()   &&   
					arco.getY1() <= punto.getY()  &&  punto.getY() <= arco.getY2()	){
				arco.agregarPunto( punto );
			}
		}
	}
	
	
	public void guardarArcosPuntos( File archivo ) throws FileNotFoundException{			
		String temp = Arrays.toString( ArchivoRedVial.COLUMNAS_RED_VIAL ).replace(",", ";") + "; VEL_PROM; " + 
				      Arrays.toString( ArchivoPuntosConcatenado.COLUMNAS_PUNTOS ).replace(",", ";") + "\n";
		
		for ( Arco arco : arregloArcos ){
			for ( Punto punto : arco.getArregloPuntos() ){
				temp += arco.toString() + "; " + punto.toString() + "\n";
			}
		}
		PrintWriter archivoArcoPuntos = new PrintWriter( archivo );		
		archivoArcoPuntos.write( temp );
		archivoArcoPuntos.close();
	}
	
	
	public String getInfoArco( int idVia ){
		String infoArco = ""; 
		for ( Arco arco : arregloArcos ){
			if ( arco.getIdVia() == idVia ){
				infoArco = "Id Via: \t" + arco.getIdVia() + 
						"\nDireccion: \t" + arco.getDireccion() + 
						"\nX1: \t" + arco.getX1() +
						"\nY1: \t" + arco.getY1() +
						"\nX2: \t" + arco.getX2() +
						"\nY2: \t" + arco.getY2() +
						"\nVel. Promedio: \t" + arco.getVelocidadPromedio();
				return infoArco;
			}
		}
		return infoArco;
	}
	
	
	public String getInfoPunto( int idVia, int indexPunto ){
		String infoPunto = "";
		for ( Arco arco : arregloArcos ){
			if ( arco.getIdVia() == idVia ){
				for ( Punto punto : arco.getArregloPuntos() ){
					if ( punto.getIndex() == indexPunto ){
						infoPunto = "Index: \t" + punto.getIndex() + 
								"\nDate: \t" + punto.getDate() + 
								"\nTime: \t" + punto.getTime() + 
								"\nX: \t" + punto.getX() + 
								"\nY: \t" + punto.getY() + 
								"\nHeight: \t" + punto.getHeigth();
					}
				}
			}
		}
		
		return infoPunto;
		
	}
	
	
	

}

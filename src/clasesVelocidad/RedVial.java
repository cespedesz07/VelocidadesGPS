package clasesVelocidad;

import java.util.ArrayList;



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
	 * latitudes y longiotudes 1 y 2 del arco.
	 */
	public void ubicarPunto( Punto punto ){
		for ( Arco arco : this.arregloArcos ){
			if ( arco.getLatitud1() <= punto.getLatitude()  &&  punto.getLatitude() <= arco.getLatitud2()   &&   
					arco.getLongitud1() <= punto.getLongitude()  &&  punto.getLongitude() <= arco.getLongitud1()	){
				arco.agregarPunto( punto );
			}
		}
	}
	
	

}

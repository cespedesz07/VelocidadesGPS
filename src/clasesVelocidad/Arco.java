package clasesVelocidad;

import java.util.ArrayList;
import java.util.Date;




public class Arco {
	
	
	
	// Atributos
	private int idVia;
	private String direccion;
	private double longitud1;
	private double latitud1;
	private double longitud2;
	private double latitud2;
	private double velocidadPromedio;
	
	private ArrayList<Punto> listaPuntos;
	
	
	
	//Métodos
	public Arco( int idVia, String direccion, double latitud1, double longitud1, double latitud2, double longitud2 ){
		this.idVia = idVia;
		this.direccion = direccion;
		this.latitud1 = latitud1;
		this.longitud1 = longitud1;
		this.latitud2 = latitud2;
		this.longitud2 = longitud1;
		this.listaPuntos = new ArrayList<Punto>();
		this.velocidadPromedio = 0.0;
	}
	
	
	
	public double calcularVelocidadProm(){
		return 0.0;
	}
	

}

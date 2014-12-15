package clasesVelocidad;

import java.util.ArrayList;
import java.util.Date;




public class Arco {
	
	
	
	// Atributos
	private int idVia;
	private String layer;
	private String direccion;
	private double longitud1;
	private double latitud1;
	private double longitud2;
	private double latitud2;
	private double velocidadPromedio;
	
	private ArrayList<Punto> arregloPuntos;
	
	
	
	//Métodos
	public Arco( int idVia, String layer, String direccion, double latitud1, double longitud1, double latitud2, double longitud2 ){
		this.idVia = idVia;
		this.layer = layer;
		this.direccion = direccion;
		this.latitud1 = latitud1;
		this.longitud1 = longitud1;
		this.latitud2 = latitud2;
		this.longitud2 = longitud1;
		this.arregloPuntos = new ArrayList<Punto>();
		this.velocidadPromedio = 0.0;
	}	
	
	public double calcularVelocidadProm(){
		return 0.0;
	}	
	
	public double getVelocidadPromedio() {
		return velocidadPromedio;
	}	
	
	public void ubicarPunto( Punto punto ){
		
	}

	public int getIdVia() {
		return idVia;
	}

	public void setIdVia(int idVia) {
		this.idVia = idVia;
	}

	public String getLayer() {
		return layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getLongitud1() {
		return longitud1;
	}

	public void setLongitud1(double longitud1) {
		this.longitud1 = longitud1;
	}

	public double getLatitud1() {
		return latitud1;
	}

	public void setLatitud1(double latitud1) {
		this.latitud1 = latitud1;
	}

	public double getLongitud2() {
		return longitud2;
	}

	public void setLongitud2(double longitud2) {
		this.longitud2 = longitud2;
	}

	public double getLatitud2() {
		return latitud2;
	}

	public void setLatitud2(double latitud2) {
		this.latitud2 = latitud2;
	}

	public void agregarPunto( Punto punto ) {
		this.arregloPuntos.add( punto );
	}	
	

}

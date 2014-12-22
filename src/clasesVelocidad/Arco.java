package clasesVelocidad;

import java.util.ArrayList;
import java.util.Date;




public class Arco {
	
	
	
	// Atributos
	private int idVia;
	private String direccion;
	private double X1;					//Longitud1
	private double Y1;					//Latitud1
	private double X2;					//Longitud2
	private double Y2;					//Latitud2
	private double velocidadPromedio;
	
	private ArrayList<Punto> arregloPuntos;	
	

	//Métodos
	public Arco( int idVia, String direccion, double X1, double Y1, double X2, double Y2 ){
		this.idVia = idVia;
		this.direccion = direccion;
		this.X1 = X1;
		this.Y1 = Y1;
		this.X2 = X2;
		this.Y2 = Y2;
		this.arregloPuntos = new ArrayList<Punto>();
		this.velocidadPromedio = 0.0;
	}
	
	public double calcularVelocidadProm(){
		double velocidadPromedio = 0.0;
		return velocidadPromedio;
	}	
	
	public double getVelocidadPromedio() {
		return velocidadPromedio;
	}		

	public int getIdVia() {
		return idVia;
	}

	public void setIdVia(int idVia) {
		this.idVia = idVia;
	}	

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getX1() {
		return X1;
	}

	public void setX1(double x1) {
		X1 = x1;
	}

	public double getY1() {
		return Y1;
	}

	public void setY1(double y1) {
		Y1 = y1;
	}

	public double getX2() {
		return X2;
	}

	public void setX2(double x2) {
		X2 = x2;
	}

	public double getY2() {
		return Y2;
	}

	public void setY2(double y2) {
		Y2 = y2;
	}

	public void agregarPunto( Punto punto ) {
		this.arregloPuntos.add( punto );
	}
	
	public ArrayList<Punto> getArregloPuntos(){
		return this.arregloPuntos;
	}

	
	@Override
	public String toString() {
		return idVia + "; " + direccion	+ "; " + X1 + "; " + Y1 + "; " + X2 + "; " + Y2 + "; " + velocidadPromedio + "";
	}
	
	

		
	
	
	

}

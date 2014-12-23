package clasesVelocidad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Punto {
	
	
	
	
	//Atributos
	private long index;
	private String date;
	private String time;
	private double Y;
	private double X;
	private int heigth;
	private boolean recorrido;
	//private int fid_2;
	
	
	//Métodos
	public Punto( long index, String date, String time, double X, double Y, int heigth ){
		this.index = index;
		this.date = date;
		this.time = time;
		this.Y = Y;
		this.X = X;
		this.heigth = heigth;
		this.recorrido = false;
		//this.fid_2 = fid_2;
	}
	
	
	/**
	 * Método para convertir una latitud o longitud con etiqueta N,S,E,W 
	 * a valor numérico. Los valores con S o W los pasa a negativos, y los
	 * valores con N o E los pasa a positivos.
	 * Por ejemplo, convierte de 076.6572W a -76.6572
	 */
	public double convertirUnidad( String valor ){
		double factor = 0.0;
		if ( valor.contains("N")  ||  valor.contains("E") ){
			factor = 1.0;
		}
		else if ( valor.contains("S")  ||  valor.contains("W") ){
			factor = -1.0;
		}
		valor = valor.replaceAll("[^\\d.]", "");
		return Double.parseDouble( valor ) * factor;
	}

	
	public long getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}
	

	public double getY() {
		return Y;
	}


	public void setY(double y) {
		Y = y;
	}


	public double getX() {
		return X;
	}


	public void setX(double x) {
		X = x;
	}


	public int getHeigth() {
		return heigth;
	}


	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	
	public boolean getRecorrido(){
		return this.recorrido;
	}
	
	
	public void setRecorrido( boolean haSidoRecorrido ){
		this.recorrido = haSidoRecorrido;
	}
	
	
	public double calcularDistancia( Punto sgtePunto ){
		double distancia = -1.0;
		double temp = Math.pow( sgtePunto.getY() - this.Y, 2 ) + Math.pow( sgtePunto.getX() - this.X, 2 );
		distancia = Math.sqrt( temp );
		return distancia;
	}
	
	
	public long calcularIntervaloSegundos( Punto puntoSgte ){
		long intervaloSegundos = -1;
		try {
			SimpleDateFormat formatoHora = new SimpleDateFormat( "hhmmss" );
			Date horaPuntoActual = formatoHora.parse( this.time );
			Date horaPuntoSgte = formatoHora.parse( puntoSgte.getTime() );
			intervaloSegundos = ( horaPuntoSgte.getTime() - horaPuntoActual.getTime() ) / 1000;
			return intervaloSegundos;
			
		} 
		catch (ParseException e) {			
			e.printStackTrace();
		}
		return intervaloSegundos;
	}
	
	
	
	public boolean tieneHoraMenor( Punto puntoSgte ){
		try{
			SimpleDateFormat formatoHora = new SimpleDateFormat( "hhmmss" );
			Date horaPuntoActual = formatoHora.parse( this.time );
			Date horaPuntoSgte = formatoHora.parse( puntoSgte.getTime() );
			if ( horaPuntoActual.before(horaPuntoSgte) ){
				return true;
			}
			else{
				return false;
			}
		}
		catch ( ParseException error ){
			error.printStackTrace();
		}
		return false;
	}
	

	
	@Override
	public String toString() {
		return "" + index + "; " + date + "; " + time + "; " + Y + "; " + X + "; " + heigth + "; " + recorrido + "";
	}
	
	
	
	
	
	

}

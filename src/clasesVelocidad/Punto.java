package clasesVelocidad;

public class Punto {
	
	
	
	
	//Atributos
	private int index;
	private String date;
	private String time;
	private double latitude;
	private double longitude;
	private int heigth;
	
	
	//Métodos
	public Punto( int index, String date, String time, double latitude, double longitude, int heigth ){
		this.index = index;
		this.date = date;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.heigth = heigth;
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


	public int getIndex() {
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


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public int getHeigth() {
		return heigth;
	}


	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	
	

}

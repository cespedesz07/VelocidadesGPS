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

}

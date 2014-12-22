package clasesVelocidad;

public class Punto {
	
	
	
	
	//Atributos
	private int index;
	private String date;
	private String time;
	private double Y;
	private double X;
	private int heigth;
	//private int fid_2;
	
	
	//Métodos
	public Punto( int index, String date, String time, double X, double Y, int heigth ){
		this.index = index;
		this.date = date;
		this.time = time;
		this.Y = Y;
		this.X = X;
		this.heigth = heigth;
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

	
	@Override
	public String toString() {
		return "" + index + "; " + date + "; " + time + "; " + Y + "; " + X + "; " + heigth + "";
	}
	
	
	
	
	
	

}

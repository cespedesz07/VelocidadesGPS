package clasesVelocidad;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	
	
	public void calcularVelocidadProm() throws InterruptedException{
		double velocidadPromedio = 0.0;
		
		ArrayList<Double> velocidadesRutas = new ArrayList<Double>();
		int totalPuntosContados = 0;
		
		while ( totalPuntosContados < this.arregloPuntos.size() ){
			
			//Primero se busca el punto mas cercano al punto inicial (X1, Y1) del arco
			Punto puntoInicialMasCercano = buscarPuntoMasCercano( this.X1, this.Y1, null );
			
			//Segundo paso es encontrar el punto mas cercano al punto final (X2, Y2) del arco
			//CON LA MISMA FECHA DEL PUNTO INICIAL (anteriormente encontrado)
			Punto puntoFinalMasCercano = buscarPuntoMasCercano( this.X2, this.Y2, puntoInicialMasCercano.getDate() );			
			
			//Tercer paso es comparar los tiempos de ambos puntos para determinar la direccion de la ruta
			Punto puntoInicio = null;
			if ( puntoInicialMasCercano.tieneHoraMenor( puntoFinalMasCercano ) ){
				puntoInicio = puntoInicialMasCercano;
			}
			else{
				puntoInicio = puntoFinalMasCercano;
			}
			
			
			System.out.println( "-----------INICIO RUTA--------------------------- \n" );
			
			
			//Finalmente se recorren los puntos de la ruta (con igual fecha y tiempo consecutivo) siguiendo la direccion determinada de la ruta
			//y comenzando por el puntoInicio
			double velocidadOperacionRuta = 0.0;
			double numPuntosRuta = 0.0;
			puntoInicio.setRecorrido( true );
			for ( Punto puntoSgte : this.arregloPuntos ){
				if ( !puntoSgte.getRecorrido() ){
					if ( puntoInicio.getDate().equals( puntoSgte.getDate() ) ){		//Se verifica que los puntos sean de la misma fecha
						if ( puntoInicio.tieneHoraMenor(puntoSgte) ){				//Se verifica que el punto actual tenga una hora Menor a la hora del punto
							long diferenciaTiempo = puntoInicio.calcularIntervaloSegundos(puntoSgte);
							if ( diferenciaTiempo < 10 ){
								velocidadOperacionRuta += ( 3.6 / diferenciaTiempo ) * puntoInicio.calcularDistancia(puntoSgte);
								numPuntosRuta += 1;
								puntoSgte.setRecorrido(true);
								System.out.println( "    Punto Inicial: \t" + puntoInicio.toString() );
								System.out.println( "    Punto Sgte: \t" + puntoSgte.toString() );
								System.out.println( "    Velocidad Operacion Ruta: " + velocidadOperacionRuta );
								System.out.println( "    Intervalo de Segundos: " + diferenciaTiempo + "\n" );
								puntoInicio = puntoSgte;								
							}
							else{
								System.out.println( "Diferencia Tiempo Mayor a 10: " + diferenciaTiempo );
								break;
							}
						}														 
					}
				}
			}
			
			velocidadOperacionRuta = velocidadOperacionRuta / ( numPuntosRuta + 1 );
			velocidadesRutas.add( velocidadOperacionRuta );
			System.out.println( "Velocidades de Cada Ruta: " + Arrays.toString( velocidadesRutas.toArray() ) );
			totalPuntosContados += (numPuntosRuta + 1);
			System.out.println( "-----------FINALIZA RUTA--------------------------- \n" );
			System.out.println( "Total Puntos Contados: " + totalPuntosContados );
			System.out.println( "--------------------------------------------------- \n\n" );
			Thread.sleep( 1000 );
		}	//Se repide esto para cada ruta en el arco
		
		
		for ( Double velocidadRuta : velocidadesRutas ){
			this.velocidadPromedio += velocidadRuta;
		}
		this.velocidadPromedio = this.velocidadPromedio / velocidadesRutas.size();	
		
		System.out.println( "..........VELOCIDAD PROMEDIO DEL ARCO: " + this.velocidadPromedio );
	}	
	
	
	
	public Punto buscarPuntoMasCercano( double coord1, double coord2, String fecha ){
		Punto puntoMasCercano = null;
		double distanciaMasCorta = 1000;		
		Punto puntoInicialArco = new Punto(0, "", "", coord1, coord2, 0);
		for ( Punto punto : this.arregloPuntos ){
			if ( !punto.getRecorrido() ){
				if ( fecha != null ){
					if ( punto.getDate().equals( fecha ) ){
						if ( puntoInicialArco.calcularDistancia( punto ) < distanciaMasCorta ){
							distanciaMasCorta = puntoInicialArco.calcularDistancia( punto );
							puntoMasCercano = punto;
						}
					}
				}
				else{
					if ( puntoInicialArco.calcularDistancia( punto ) < distanciaMasCorta ){
						distanciaMasCorta = puntoInicialArco.calcularDistancia( punto );
						puntoMasCercano = punto;
					}
				}
			}
			
		}
		return puntoMasCercano;
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

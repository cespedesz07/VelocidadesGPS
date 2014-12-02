package utilidades;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class ConexionExcel{
	
	public static void main( String[] args ){
		File archivo = new File( "D://Google Drive//Movilidad//GPS//14081501.csv" );
		try{
			
			Scanner entrada = new Scanner( archivo );			
			while ( entrada.hasNext() ){
				String[] linea = entrada.nextLine().split(",");
				System.out.println( Arrays.toString( linea ) );
			}
		}
		catch ( Exception error ){
			error.printStackTrace();
		}
	}

}
package interfaz;

import javax.swing.*;

public class MainGUI extends JFrame {
	
	
	private PanelSeleccionCarpeta panelSeleccionCarpeta;
	
	
	
	public MainGUI(){
		setSize( 450, 600 );
		setTitle( "Pantalla Inicial" );	
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		this.panelSeleccionCarpeta = new PanelSeleccionCarpeta();
		add( this.panelSeleccionCarpeta );	
	}
	
	
	
	
	
	
	
	
	//***********************************************************************************
	public static void main( String[] args ){ 		
		try {
	        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );	        
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (UnsupportedLookAndFeelException e) {
	        e.printStackTrace();
	    }
		MainGUI interfaz = new MainGUI();
		interfaz.setVisible(true);
	}

}

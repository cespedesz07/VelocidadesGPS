package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainGUI extends JFrame {
	
	
	private PanelSeleccionCarpeta panelSeleccionCarpeta;
	
	
	
	public MainGUI(){
		setSize( 300, 200 );
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

package interfaz;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class MainGUI extends JFrame implements WindowListener {
	
	
	private PanelSeleccionCarpeta panelSeleccionCarpeta;
	
	
	
	public MainGUI(){
		setSize( 450, 600 );
		setTitle( "Pantalla Inicial" );	
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		addWindowListener( this );
		
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

	@Override
	public void windowActivated(WindowEvent e) {}


	@Override
	public void windowClosed(WindowEvent e) {}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub		
		dispose();
	}


	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e){}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}
}

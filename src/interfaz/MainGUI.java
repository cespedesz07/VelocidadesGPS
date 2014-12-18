package interfaz;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class MainGUI extends JFrame implements WindowListener {
	
	
	private PanelConcatenadoPuntos panelSeleccionCarpeta;
	private JCalcular panelCalculoVelocidades;
	
	
	
	public MainGUI(){
		setSize( 500, 620 );
		setTitle( "Pantalla Inicial" );	
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		getContentPane().setLayout( new GridLayout(2,1)  );
		addWindowListener( this );
		
		this.panelSeleccionCarpeta = new PanelConcatenadoPuntos();
		getContentPane().add( this.panelSeleccionCarpeta );
		
		this.panelCalculoVelocidades = new JCalcular();
		getContentPane().add( this.panelCalculoVelocidades );
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

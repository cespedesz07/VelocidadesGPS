package interfaz;

import javax.swing.JFrame;

import clasesVelocidad.RedVial;

public class FrameArbolArcosPuntos extends JFrame {

	
	private PanelArbolArcosPuntos panelArbolArcosPuntos;


	/**
	 * Create the frame.
	 */
	public FrameArbolArcosPuntos( RedVial redVial ) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 480);
		panelArbolArcosPuntos = new PanelArbolArcosPuntos( redVial );
		
		getContentPane().add( panelArbolArcosPuntos );
		
	}

}

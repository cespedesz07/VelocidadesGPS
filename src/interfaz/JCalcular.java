package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class JCalcular extends JPanel {

	/**
	 * Create the panel.
	 */
	public JCalcular() {
		setLayout(null);
		
		JLabel lblSeleecionarLaRed = new JLabel("Seleccionar la Red Vial");
		lblSeleecionarLaRed.setBounds(22, 33, 158, 16);
		add(lblSeleecionarLaRed);
		
		JLabel lblSeleccionarElArchivo = new JLabel("Seleccionar el archivo GPS");
		lblSeleccionarElArchivo.setBounds(22, 69, 200, 16);
		add(lblSeleccionarElArchivo);
		
		JLabel lblCalcularVelocidad = new JLabel("Calcular Velocidad");
		lblCalcularVelocidad.setBounds(22, 107, 158, 16);
		add(lblCalcularVelocidad);
		
		JButton btnBuscarRedeVial = new JButton("Buscar");
		btnBuscarRedeVial.setBounds(206, 28, 117, 29);
		add(btnBuscarRedeVial);
		
		JButton btnBuscarGPS = new JButton("Buscar");
		btnBuscarGPS.setBounds(206, 64, 117, 29);
		add(btnBuscarGPS);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(206, 102, 117, 29);
		add(btnCalcular);

	}

}

package interfaz;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelGoogleMaps extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelGoogleMaps() {
		setLayout(null);
		
		JLabel lblSeleccionarElArchivo = new JLabel("Seleccionar el archivo para graficar");
		lblSeleccionarElArchivo.setBounds(21, 17, 239, 16);
		add(lblSeleccionarElArchivo);
		
		JLabel lblSeleccionarArchivo = new JLabel("Seleccionar Archivo");
		lblSeleccionarArchivo.setBounds(29, 60, 134, 16);
		add(lblSeleccionarArchivo);
		
		JButton btnGraficar = new JButton("Graficar");
		btnGraficar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGraficar.setBounds(195, 55, 117, 29);
		add(btnGraficar);

	}
}

package interfaz;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCalculoVelocidades extends JPanel implements ActionListener {
	
	
	private static final String CARGAR_RED_VIAL = "Cargar Red Vial";
	private static final String CARGAR_PUNTOS_GPS = "Cargar Red Vial";

	/**
	 * Create the panel.
	 */
	public PanelCalculoVelocidades() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(49, 190, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(296, 190, 89, 23);
		add(btnNewButton_1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
				
	}
}

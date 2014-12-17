package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import utilidades.ArchivoPuntosConcatenado;
import utilidades.ArchivoRedVial;

import java.io.File;



public class JCalcular extends JPanel implements ActionListener {
	
	
	private static final String BUSCAR_RED_VIAL = "buscarRedVial";
	private static final String BUSCAR_PUNTOS_CONCATENADOS_GPS = "buscarPuntosGPS";
	private static final String CALCULAR_VELOCIDADES = "calcularVelocidades";
	
	private static final String NINGUNA_RED_VIAL_CARGADA = "(Ninguna Red Vial Cargada)";
	private static final String NINGUN_ARCHIVO_GPS_CARGADO = "(Ningún Archivo GPS Cargado)";
	
	private static final ImageIcon ICONO_OK = new ImageIcon( "img/emblem-ok.png" );
	private static final ImageIcon ICONO_NO_OK = new ImageIcon( "img/gtk-no.png" );
	
	private JFileChooser selectorRedVial;
	private ArchivoRedVial archivoRedVial;
	private JFileChooser selectorPuntosGPS;
	private ArchivoPuntosConcatenado archivoPuntosGPS;
	

	/**
	 * Create the panel.
	 */
	public JCalcular() {
		setLayout(null);
		setBackground( new Color(255, 255, 255) );
		setBorder( new TitledBorder("Cálculo Velocidades") );
		
		JLabel lblSeleecionarLaRed = new JLabel("Seleccionar la Red Vial");
		lblSeleecionarLaRed.setBounds(22, 39, 122, 16);
		add(lblSeleecionarLaRed);
		
		JLabel lblSeleccionarElArchivo = new JLabel("Seleccionar el archivo GPS");
		lblSeleccionarElArchivo.setBounds(22, 73, 134, 16);
		add(lblSeleccionarElArchivo);
		
		JLabel lblCalcularVelocidad = new JLabel("Calcular Velocidad");
		lblCalcularVelocidad.setBounds(22, 120, 134, 16);
		add(lblCalcularVelocidad);
		
		JButton btnBuscarRedVial = new JButton("Buscar");
		btnBuscarRedVial.setActionCommand( BUSCAR_RED_VIAL );
		btnBuscarRedVial.addActionListener( this );
		btnBuscarRedVial.setBounds(161, 33, 72, 29);
		add(btnBuscarRedVial);
		
		JButton btnBuscarGPS = new JButton("Buscar");
		btnBuscarGPS.setActionCommand( BUSCAR_PUNTOS_CONCATENADOS_GPS );
		btnBuscarGPS.addActionListener( this );
		btnBuscarGPS.setBounds(161, 69, 72, 29);
		add(btnBuscarGPS);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setActionCommand( CALCULAR_VELOCIDADES );
		btnCalcular.addActionListener( this );
		btnCalcular.setBounds(161, 114, 72, 29);
		add(btnCalcular);
		
		JLabel iconoRedVial = new JLabel("");
		iconoRedVial.setIcon( ICONO_NO_OK );
		iconoRedVial.setBounds(417, 41, 36, 14);
		add(iconoRedVial);
		
		JLabel iconoArchivoGPS = new JLabel("");
		iconoArchivoGPS.setIcon( ICONO_NO_OK );
		iconoArchivoGPS.setBounds(417, 75, 36, 14);
		add(iconoArchivoGPS);
		
		JLabel lblNombreRedVial = new JLabel(NINGUNA_RED_VIAL_CARGADA);
		lblNombreRedVial.setBounds(243, 40, 141, 14);
		add(lblNombreRedVial);
		
		JLabel lblningnArchivoGps = new JLabel(NINGUN_ARCHIVO_GPS_CARGADO);
		lblningnArchivoGps.setBounds(243, 74, 157, 14);
		add(lblningnArchivoGps);

	}


	@Override
	public void actionPerformed( ActionEvent evento) {
		String boton = evento.getActionCommand();
		if ( boton.equals( BUSCAR_RED_VIAL ) ){
			this.selectorRedVial = new JFileChooser();
			this.selectorRedVial.setFileSelectionMode( JFileChooser.FILES_ONLY );
			int opcion = this.selectorRedVial.showOpenDialog(this);
			if ( opcion == JFileChooser.APPROVE_OPTION ){
				try{
					File redVialCargada = this.selectorRedVial.getSelectedFile();
					this.archivoRedVial = new ArchivoRedVial( redVialCargada.getAbsolutePath() );
					this.archivoRedVial.cargarRedVial();
					this.archivoRedVial.inicializarArcos();
					JOptionPane.showMessageDialog(new JPanel(), "Arcos cargados exitosamente", "Exito!", JOptionPane.INFORMATION_MESSAGE );
				}
				catch ( Exception error ){					
					JOptionPane.showMessageDialog(new JPanel() , error.getMessage(), "Error al cargar Red Vial.", JOptionPane.ERROR_MESSAGE );			
					error.printStackTrace();
				}
			}
		}
		else if ( boton.equals( BUSCAR_PUNTOS_CONCATENADOS_GPS ) ){
			this.selectorPuntosGPS = new JFileChooser();
			this.selectorPuntosGPS.setFileSelectionMode( JFileChooser.FILES_ONLY );
			int opcion = this.selectorPuntosGPS.showOpenDialog(this);
			if ( opcion == JFileChooser.APPROVE_OPTION ){
				try{
					File archivoPuntosGPS = this.selectorPuntosGPS.getSelectedFile();
					this.archivoPuntosGPS = new ArchivoPuntosConcatenado( archivoPuntosGPS.getAbsolutePath(), null );
					this.archivoPuntosGPS.cargarPuntosConcatenados();
					this.archivoPuntosGPS.inicializarPuntos( this.archivoRedVial.getRedVial() );
					JOptionPane.showMessageDialog(new JPanel(), "Arcos cargados exitosamente", "Exito!", JOptionPane.INFORMATION_MESSAGE );
				}
				catch ( Exception error ){					
					JOptionPane.showMessageDialog(new JPanel() , error.getMessage(), "Error al cargar Red Vial.", JOptionPane.ERROR_MESSAGE );			
					error.printStackTrace();
				}
			}
		}
		else if ( evento.equals( CALCULAR_VELOCIDADES ) ){
			//TODO Completar
		}
	}
}

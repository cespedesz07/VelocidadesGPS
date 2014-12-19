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

import clasesVelocidad.RedVial;
import utilidades.ArchivoPuntosConcatenado;
import utilidades.ArchivoRedVial;

import java.io.File;
import java.io.FileNotFoundException;



public class PanelCalculoVelocidades extends JPanel implements ActionListener {
	
	
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
	private JButton btnBuscarRedVial;
	private JButton btnBuscarGPS;
	private JButton btnCalcular;
	private JLabel lblNombreRedVial;
	private JLabel lblNombreArchivoGps;
	private JLabel iconoRedVial;
	private JLabel iconoArchivoGPS;
	
	private boolean redVialCargada;
	private boolean archivoGPSCargado;
	

	/**
	 * Create the panel.
	 */
	public PanelCalculoVelocidades() {
		setLayout(null);
		setBackground( new Color(255, 255, 255) );
		setBorder( new TitledBorder("Cálculo Velocidades") );
		
		this.redVialCargada = false;
		this.archivoGPSCargado = false;
		
		JLabel lblSeleecionarLaRed = new JLabel("Seleccionar la Red Vial");
		lblSeleecionarLaRed.setBounds(22, 39, 122, 16);
		add(lblSeleecionarLaRed);
		
		
		JLabel lblSeleccionarElArchivo = new JLabel("Seleccionar el archivo GPS");
		lblSeleccionarElArchivo.setBounds(22, 73, 134, 16);
		add(lblSeleccionarElArchivo);
		
		JLabel lblCalcularVelocidad = new JLabel("Calcular Velocidad");
		lblCalcularVelocidad.setBounds(22, 120, 134, 16);
		add(lblCalcularVelocidad);
		
		btnBuscarRedVial = new JButton("Buscar");
		btnBuscarRedVial.setActionCommand( BUSCAR_RED_VIAL );
		btnBuscarRedVial.addActionListener( this );
		btnBuscarRedVial.setBounds(161, 33, 72, 29);
		add(btnBuscarRedVial);
		
		btnBuscarGPS = new JButton("Buscar");
		btnBuscarGPS.setActionCommand( BUSCAR_PUNTOS_CONCATENADOS_GPS );
		btnBuscarGPS.addActionListener( this );
		btnBuscarGPS.setBounds(161, 69, 72, 29);
		add(btnBuscarGPS);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.setActionCommand( CALCULAR_VELOCIDADES );
		btnCalcular.addActionListener( this );
		btnCalcular.setBounds(161, 114, 72, 29);
		add(btnCalcular);
		
		iconoRedVial = new JLabel("");
		iconoRedVial.setIcon( ICONO_NO_OK );
		iconoRedVial.setBounds(442, 26, 36, 36);
		add(iconoRedVial);
		
		iconoArchivoGPS = new JLabel("");
		iconoArchivoGPS.setIcon( ICONO_NO_OK );
		iconoArchivoGPS.setBounds(442, 73, 36, 36);
		add(iconoArchivoGPS);
		
		lblNombreRedVial = new JLabel(NINGUNA_RED_VIAL_CARGADA);
		lblNombreRedVial.setBounds(243, 40, 198, 14);
		add(lblNombreRedVial);
		
		lblNombreArchivoGps = new JLabel(NINGUN_ARCHIVO_GPS_CARGADO);
		lblNombreArchivoGps.setBounds(243, 74, 198, 14);
		add(lblNombreArchivoGps);

	}


	@Override
	public void actionPerformed( ActionEvent evento) {
		String boton = evento.getActionCommand();
		if ( boton.equals( BUSCAR_RED_VIAL ) ){
			//this.selectorRedVial = new JFileChooser();
			//this.selectorRedVial.setFileSelectionMode( JFileChooser.FILES_ONLY );
			//int opcion = this.selectorRedVial.showOpenDialog(this);
			//if ( opcion == JFileChooser.APPROVE_OPTION ){
				try{
					//File redVialCargada = this.selectorRedVial.getSelectedFile();
					//this.archivoRedVial = new ArchivoRedVial( redVialCargada.getAbsolutePath() );
					this.archivoRedVial = new ArchivoRedVial( "C://Users//unalman//Desktop//Quibdo_Red_vial_visum_link_Lat_Long.csv" );
					this.archivoRedVial.cargarRedVial();
					this.archivoRedVial.inicializarArcos();
					this.iconoRedVial.setIcon( ICONO_OK );
					//this.lblNombreRedVial.setText( redVialCargada.getName() );
					this.redVialCargada = true;
					JOptionPane.showMessageDialog(new JPanel(), "Arcos cargados exitosamente.", "Exito!", JOptionPane.INFORMATION_MESSAGE );
					
				}
				catch ( Exception error ){
					this.iconoRedVial.setIcon( ICONO_NO_OK );
					this.lblNombreRedVial.setText( NINGUNA_RED_VIAL_CARGADA );
					this.redVialCargada = false;
					JOptionPane.showMessageDialog(new JPanel() , error.getMessage(), "Error al cargar Red Vial.", JOptionPane.ERROR_MESSAGE );			
					//error.printStackTrace();
				}
			//}
		}
		else if ( boton.equals( BUSCAR_PUNTOS_CONCATENADOS_GPS ) ){			
			if ( this.redVialCargada == true ){
				//this.selectorPuntosGPS = new JFileChooser();
				//this.selectorPuntosGPS.setFileSelectionMode( JFileChooser.FILES_ONLY );
				//int opcion = this.selectorPuntosGPS.showOpenDialog(this);
				//if ( opcion == JFileChooser.APPROVE_OPTION ){
					try{
						//File archivoPuntosGPS = this.selectorPuntosGPS.getSelectedFile();
						//this.archivoPuntosGPS = new ArchivoPuntosConcatenado( null, archivoPuntosGPS.getAbsolutePath(), null );
						this.archivoPuntosGPS = new ArchivoPuntosConcatenado( null, "C://Users//unalman//Desktop//GPS_PLANASQUIB.csv", null );
						this.archivoPuntosGPS.cargarPuntosConcatenados();
						this.archivoPuntosGPS.inicializarPuntos( this.archivoRedVial.getRedVial() );
						this.iconoArchivoGPS.setIcon( ICONO_OK );
						//this.lblNombreArchivoGps.setText( archivoPuntosGPS.getName() );
						this.archivoGPSCargado = true;
						JOptionPane.showMessageDialog(this, "Puntos GPS cargados exitosamente.", "Exito!", JOptionPane.INFORMATION_MESSAGE );
					}
					catch ( Exception error ){	
						this.iconoArchivoGPS.setIcon( ICONO_NO_OK );
						this.lblNombreArchivoGps.setText( NINGUN_ARCHIVO_GPS_CARGADO );
						this.archivoGPSCargado = false;
						JOptionPane.showMessageDialog(this, error.getMessage(), "Error al cargar Puntos GPS.", JOptionPane.ERROR_MESSAGE );			
						//error.printStackTrace();
					}
				//}
			}
			else{
				JOptionPane.showMessageDialog(this, "Por favor cargue primero la red vial", "Error al cargar puntos GPS", JOptionPane.ERROR_MESSAGE );
			}
		}
		else if ( boton.equals( CALCULAR_VELOCIDADES ) ){
			if ( this.redVialCargada == true  &&  this.archivoGPSCargado == true ){
				try {
					JFileChooser selector = new JFileChooser( "C://Users//unalman//Desktop" );					
					int opcion = selector.showSaveDialog(this);
					if ( opcion == JFileChooser.APPROVE_OPTION ){
						File archivo = selector.getSelectedFile();
						this.archivoRedVial.getRedVial().guardarArcosPuntos(archivo);
						JOptionPane.showMessageDialog( this, "Archivo Arco Puntos generado exitosamente", "Éxito!", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} 
				catch (FileNotFoundException e) {					
					e.printStackTrace();
				}			
			}			
			else{
				JOptionPane.showMessageDialog(this, "Por favor cargue la red vial y el archivo con los puntos GPS.", "Error al calcular velocidades", JOptionPane.ERROR_MESSAGE );
			}
			
		}
	}
}

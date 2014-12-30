package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utilidades.ArchivoPuntosConcatenado;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JProgressBar;
import javax.swing.JComboBox;

public class PanelConcatenadoPuntos extends JPanel implements ActionListener {
	
		
	public static final String[] ITEMS_DELIM = { "1. Punto y Coma (;)", "2. Coma (,)" };
	public static final String[] DELIMS = { ";", "," };
	
	
	private JFileChooser selectorCarpeta;	
	private JProgressBar barraProgreso;
	private JLabel lblSeleccionCarpeta;
	private JButton btnBuscar;
	private TextArea areaLog;
	private ArchivoPuntosConcatenado archivoConcatenado;
	private JComboBox listaDelimitador;

	/**
	 * Create the panel.
	 */
	public PanelConcatenadoPuntos() {
		setLayout(null);
		setBorder( new TitledBorder("Concatenar Puntos GPS") );
		setBackground( new Color(255, 255, 255) );
		
		lblSeleccionCarpeta = new JLabel("Seleccionar Carpeta con Archivos .csv");
		lblSeleccionCarpeta.setBounds(42, 23, 209, 14);
		add(lblSeleccionCarpeta);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener( this );
		btnBuscar.setBounds(261, 19, 120, 23);
		add(btnBuscar);
		
		areaLog = new TextArea();
		areaLog.setEditable( false );
		areaLog.setFont( new Font( "Consolas", Font.PLAIN, 12 ) );
		areaLog.setBounds(42, 105, 422, 160);
		add(areaLog);
		
	
		barraProgreso = new JProgressBar();
		barraProgreso.setStringPainted( true );
		barraProgreso.setBounds(318, 85, 146, 14);
		add(barraProgreso);		
		
		
		JLabel lblLog = new JLabel("Log:");
		lblLog.setBounds(30, 85, 46, 14);
		add(lblLog);
		
		JLabel lblDelimitadorDeColumnas = new JLabel("Delimitador de Columnas:");
		lblDelimitadorDeColumnas.setBounds(42, 60, 128, 14);
		add(lblDelimitadorDeColumnas);
		
		listaDelimitador = new JComboBox( ITEMS_DELIM );
		listaDelimitador.setBounds(261, 57, 120, 20);
		add(listaDelimitador);
	}
	
	public void setPosicionAreaLog( int posicion ){
		this.areaLog.setCaretPosition( posicion );
	}

	
	public String getAreaLog(){
		return this.areaLog.getText();
	}
	
	
	public void setAreaLog( String texto ){
		this.areaLog.setText( texto );
	}
	
	//http://www.javamex.com/tutorials/threads/invokelater.shtml
	public void setProgreso( int valor ){		
		barraProgreso.setValue( valor );	
	}
	
	public String getDelimSeleccionado(){
		return DELIMS[ this.listaDelimitador.getSelectedIndex() ];
	}
	
	
	@Override
	public void actionPerformed( ActionEvent evento ){
		selectorCarpeta = new JFileChooser();
		selectorCarpeta.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		int opcion = selectorCarpeta.showOpenDialog( this );
		if ( opcion == JFileChooser.APPROVE_OPTION ){
			
			File carpetaSeleccionada = selectorCarpeta.getSelectedFile();
			this.archivoConcatenado = new ArchivoPuntosConcatenado( carpetaSeleccionada.getAbsolutePath(), null, this );
			
			Thread hilo = new Thread(){
				public void run(){
					try{
						Date fechaHoraInicio = new java.util.Date();
						areaLog.setText( fechaHoraInicio.toString() );
						areaLog.setText( areaLog.getText() + "\n\nConcatenando... \n" );				
						archivoConcatenado.generarArchivoConcatenado();	
						btnBuscar.setEnabled( false );
						listaDelimitador.setEnabled( false );
						Date fechaHoraFin = new java.util.Date();
						areaLog.setText( areaLog.getText() + "\n" + fechaHoraFin.toString() + "\n" );
						barraProgreso.setValue( 100 );
						JOptionPane.showMessageDialog(new JPanel(), "Archivo Concatenado Exitosamente", "Exito!", JOptionPane.INFORMATION_MESSAGE );
						btnBuscar.setEnabled(true);	
						listaDelimitador.setEnabled( true );
					}
					catch ( Exception error ){
						barraProgreso.setValue(0);
						areaLog.setText( archivoConcatenado.getArchivosErroneos().toString() );
						JOptionPane.showMessageDialog(new JPanel() , error.getMessage(), "Error al concatenar archivo", JOptionPane.ERROR_MESSAGE );			
						error.printStackTrace();
					}
					
				}
			};
			hilo.start();
		}//Cierre If
	}
}

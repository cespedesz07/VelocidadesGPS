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

public class PanelSeleccionCarpeta extends JPanel implements ActionListener {
	
	
	private JFileChooser selectorCarpeta;
	private ArchivoPuntosConcatenado archivoConcatenado;
	private JProgressBar barraProgreso;
	private JLabel lblSeleccionCarpeta;
	private JButton btnBuscar;
	private TextArea areaLog;

	/**
	 * Create the panel.
	 */
	public PanelSeleccionCarpeta() {
		setLayout(null);
		setBorder( new TitledBorder("Seleccionar Carpeta") );
		setBackground( new Color(255, 255, 255) );
		
		lblSeleccionCarpeta = new JLabel("Seleccionar Carpeta con Archivos .csv");
		lblSeleccionCarpeta.setBounds(42, 23, 209, 14);
		add(lblSeleccionCarpeta);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener( this );
		btnBuscar.setBounds(261, 19, 89, 23);
		add(btnBuscar);
		
		areaLog = new TextArea();
		areaLog.setEditable( false );
		areaLog.setFont( new Font( "Consolas", Font.PLAIN, 12 ) );
		areaLog.setBounds(28, 90, 370, 160);
		add(areaLog);
		
		barraProgreso = new JProgressBar();
		barraProgreso.setStringPainted( true );
		barraProgreso.setBounds(232, 70, 146, 14);
		add(barraProgreso);
		
		
		JLabel lblLog = new JLabel("Log:");
		lblLog.setBounds(28, 70, 46, 14);
		add(lblLog);
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
	
	
	@Override
	public void actionPerformed( ActionEvent evento ){
		selectorCarpeta = new JFileChooser();
		selectorCarpeta.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		int opcion = selectorCarpeta.showOpenDialog( this );
		if ( opcion == JFileChooser.APPROVE_OPTION ){
			
			File carpetaSeleccionada = selectorCarpeta.getSelectedFile();
			this.archivoConcatenado = new ArchivoPuntosConcatenado( carpetaSeleccionada.getAbsolutePath(), this );
			this.btnBuscar.setEnabled(false);
			Thread hilo = new Thread(){
				public void run(){
					try{
						Date fechaHoraInicio = new java.util.Date();
						barraProgreso.setValue(1);
						areaLog.setText( fechaHoraInicio.toString() );
						areaLog.setText( areaLog.getText() + "\n\nConcatenando... \n" );				
						archivoConcatenado.generarArchivoConcatenado();										
						JOptionPane.showMessageDialog(new JPanel(), "Archivo Concatenado Exitosamente", "Exito!", JOptionPane.INFORMATION_MESSAGE );
						btnBuscar.setEnabled(true);
						Date fechaHoraFin = new java.util.Date();
						areaLog.setText( areaLog.getText() + "\n" + fechaHoraFin.toString() + "\n" );	
					}
					catch ( Exception error ){
						areaLog.setText( archivoConcatenado.getArchivosErroneos().toString() );
						JOptionPane.showMessageDialog(new JPanel() , error.getMessage(), "Error al concatenar archivo", JOptionPane.ERROR_MESSAGE );			
						//error.printStackTrace();
					}
					
				}
			};
			hilo.start();
		}//Cierre If
	}
	
	
	
}

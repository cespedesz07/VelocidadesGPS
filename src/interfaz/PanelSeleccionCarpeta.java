package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import utilidades.ArchivoConcatenado;

public class PanelSeleccionCarpeta extends JPanel implements ActionListener {
	
	
	private JLabel lblSeleccionarCarpeta;
	private JButton btnSeleccionarCarpeta;
	private JFileChooser selectorCarpeta;
	private ArchivoConcatenado archivoConcatenado;
	
	
	public PanelSeleccionCarpeta(){
		setBorder( new TitledBorder("Seleccionar Carpeta con Archivos") );
		setBackground( new Color(255, 255, 255) );
		
		this.lblSeleccionarCarpeta = new JLabel( "Selecionar carpeta: " );
		
		this.btnSeleccionarCarpeta = new JButton( "Examinar..." );
		this.btnSeleccionarCarpeta.addActionListener( this );
		
		add( this.lblSeleccionarCarpeta );
		add( this.btnSeleccionarCarpeta );
	}


	
	@Override
	public void actionPerformed( ActionEvent evento ){
		selectorCarpeta = new JFileChooser();
		selectorCarpeta.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		int opcion = selectorCarpeta.showOpenDialog( this );
		if ( opcion == JFileChooser.APPROVE_OPTION ){
			File carpetaSeleccionada = selectorCarpeta.getSelectedFile();
			this.archivoConcatenado = new ArchivoConcatenado( carpetaSeleccionada.getAbsolutePath() );
			try{
				this.archivoConcatenado.generarArchivoConcatenado();
				JOptionPane.showMessageDialog(this, "Archivo Concatenado Exitosamente", "Exito!", JOptionPane.INFORMATION_MESSAGE );
			}
			catch ( Exception error ){
				JOptionPane.showMessageDialog(this, error.getMessage(), "Error al concatenar archivo", JOptionPane.ERROR_MESSAGE );
				error.printStackTrace();
			}
		}
	}

}

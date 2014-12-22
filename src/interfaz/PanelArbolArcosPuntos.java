package interfaz;

import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import clasesVelocidad.Arco;
import clasesVelocidad.Punto;
import clasesVelocidad.RedVial;

public class PanelArbolArcosPuntos extends JPanel implements TreeSelectionListener {
	
	private static final String LABEL_RED_VIAL = "Red Vial";
	private static final String LABEL_ARCO = "Arco";
	private static final String LABEL_PUNTO = "Punto";
	
	private JTree arbolArcosPuntos;
	private JTextArea txtPropiedades;
	private JLabel lblPropiedades;
	private JScrollPane scroll;
	
	
	private PanelCalculoVelocidades panelCalculoVelocidades;
	private RedVial redVial;

	
	public PanelArbolArcosPuntos( RedVial redVial ) {
		setBorder( new TitledBorder("Visualizar Arbol Arcos y Puntos") );
		setLayout( new BorderLayout() );		//OJO!!! NO USAR EL setLayout(null) PORQUE NO APARECE EL JTREE
		
		arbolArcosPuntos = new JTree();		
		arbolArcosPuntos.addTreeSelectionListener( this );
		arbolArcosPuntos.setBounds(10, 23, 243, 443);				
		scroll = new JScrollPane( this.arbolArcosPuntos );
		add(scroll, BorderLayout.WEST);
		
		
		txtPropiedades = new JTextArea();
		txtPropiedades.setEnabled( false );
		txtPropiedades.setText( "Propiedades..." );
		txtPropiedades.setBounds(263, 41, 227, 144);
		add(txtPropiedades, BorderLayout.CENTER);
		
		
		this.redVial = redVial;
		generarArbol();
	}
	
	
	
	public void generarArbol(){		
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode( "Red Vial" );
		raiz.setAllowsChildren( true );
		
		for ( int i=0; i<redVial.getArregloArcos().size(); i++ ){
			Arco arco = redVial.getArregloArcos().get( i );			
			DefaultMutableTreeNode nodoArco = new DefaultMutableTreeNode( LABEL_ARCO + " " + arco.getIdVia() );
			nodoArco.setAllowsChildren( true );			
			raiz.add( nodoArco );
			
			for ( int j=0; j<arco.getArregloPuntos().size(); j++ ){
				Punto punto = arco.getArregloPuntos().get( j );
				DefaultMutableTreeNode nodoPunto = new DefaultMutableTreeNode( LABEL_PUNTO + " " + punto.getIndex() );
				nodoPunto.setAllowsChildren( false );
				nodoArco.add( nodoPunto );
			}
			
		}		
		
		DefaultTreeModel nuevoModelo = new DefaultTreeModel( raiz );
		arbolArcosPuntos.setModel( nuevoModelo );	
	}
	


	
	@Override
	public void valueChanged(TreeSelectionEvent evento) {
		DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbolArcosPuntos.getLastSelectedPathComponent();	
		if ( nodoSeleccionado != null ){	
			//Object infoNodo = nodoSeleccionado.getUserObject();
			TreeNode[] rutaNodo = nodoSeleccionado.getPath();
			String[] infoNodoPartida = rutaNodo[ rutaNodo.length - 1 ].toString().split(" ");
			
			if ( infoNodoPartida[0].equals( LABEL_ARCO ) ){
				int idVia = Integer.valueOf( infoNodoPartida[1] );
				this.txtPropiedades.setText( this.redVial.getInfoArco( idVia ) );
			}
			else if ( infoNodoPartida[0].equals( LABEL_PUNTO ) ){
				int idVia = Integer.valueOf( rutaNodo[ rutaNodo.length - 2 ].toString().split(" ")[1] );
				int indexPunto = Integer.valueOf( infoNodoPartida[1] );
				this.txtPropiedades.setText( this.redVial.getInfoPunto(idVia, indexPunto) );
			}
			
		}
	}
}

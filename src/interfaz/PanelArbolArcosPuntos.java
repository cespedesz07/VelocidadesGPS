package interfaz;

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
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import clasesVelocidad.Arco;
import clasesVelocidad.Punto;
import clasesVelocidad.RedVial;

public class PanelArbolArcosPuntos extends JPanel implements TreeSelectionListener {
	private JTree arbolArcosPuntos;
	private JTextArea txtPropiedades;
	private JLabel lblPropiedades;
	
	
	private PanelCalculoVelocidades panelCalculoVelocidades;

	
	public PanelArbolArcosPuntos( RedVial redVial ) {
		setBorder( new TitledBorder("Visualizar Arbol Arcos y Puntos") );
		setLayout(null);
		
		arbolArcosPuntos = new JTree();
		arbolArcosPuntos.addTreeSelectionListener( this );
		arbolArcosPuntos.setBounds(10, 23, 243, 443);
		add(arbolArcosPuntos);
		
		txtPropiedades = new JTextArea();
		txtPropiedades.setBounds(263, 41, 227, 144);
		add(txtPropiedades);
		
		lblPropiedades = new JLabel("Propiedades de Selección: ");
		lblPropiedades.setBounds(263, 24, 136, 14);
		add(lblPropiedades);
		
		generarArbol( redVial );
	}
	
	
	
	/*
	private void crearArbol(){
		//Raiz del arbol
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode( "Red Vial" );
		
		//Modelo de Arbol donde se agregarán los nodos
		DefaultTreeModel modelo = new DefaultTreeModel( raiz );
		
		//Se agrega el modelo al componente JTree
		arbolArcosPuntos.setModel( modelo );
		
		//Se agrega el evento
		arbolArcosPuntos.addTreeSelectionListener( this );
		
		//Se agregan mas nodos al arbol
		DefaultMutableTreeNode arco1 = new DefaultMutableTreeNode( "Arco 1" );
		DefaultMutableTreeNode punto1 = new DefaultMutableTreeNode( "punto 1" );
		DefaultMutableTreeNode punto2 = new DefaultMutableTreeNode( "punto 2" );
		
		//Se define dónde se inserta e nodo, dentro de qué rama y en cual posición
		modelo.insertNodeInto(arco1, raiz, 0);
		
		modelo.insertNodeInto(punto1, arco1, 0);
		modelo.insertNodeInto(punto2, arco1, 1);
	}	
	*/
	
	
	
	public void generarArbol( RedVial redVial ){
		
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode( "Red Vial" );
		DefaultTreeModel modelo = new DefaultTreeModel( raiz );
		
		arbolArcosPuntos.setModel( modelo );
		
		for ( int i=0; i<redVial.getArregloArcos().size(); i++ ){
			Arco arco = redVial.getArregloArcos().get( i );
			if ( !arco.getArregloPuntos().isEmpty() ){
				DefaultMutableTreeNode nodoArco = new DefaultMutableTreeNode( "Arco " + arco.getIdVia() );
				modelo.insertNodeInto( nodoArco, raiz, i );
				for ( int j=0; j<arco.getArregloPuntos().size(); j++ ){
					Punto punto = arco.getArregloPuntos().get( j );
					DefaultMutableTreeNode nodoPunto = new DefaultMutableTreeNode( "Punto " + punto.getIndex() );
					modelo.insertNodeInto( nodoPunto, nodoArco, j );
				}		
			}
		}	
	}


	
	@Override
	public void valueChanged(TreeSelectionEvent evento) {
		DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbolArcosPuntos.getLastSelectedPathComponent();	
		if ( nodoSeleccionado != null ){			
			Object infoNodo = nodoSeleccionado.getUserObject();
			this.txtPropiedades.setText( infoNodo.toString() );
		}
	}
}

package Grafico;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import PostgreSQL.Conexion;
import PostgreSQL.Productos;

import com.mxrck.autocompleter.TextAutoCompleter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.Window.Type;


public class InterfazPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textCant;
	private JTextField textProducto;
	private JTextField textPrecio;
	static InterfazPrincipal frame=new InterfazPrincipal();
	public static InterfazPrincipal principal=new InterfazPrincipal();
	static InterfazPedido pedido=new InterfazPedido();
	Conexion conexion=new Conexion();
	PostgreSQL.Productos prod = new Productos();
	
	String codigo,cantidad,producto,precio,linea;
	ArrayList<Object> lista=new ArrayList<Object>();
	String fecha,Nocliente,Cliente;
	
	String acryl="com.jtattoo.plaf.acryl.AcrylLookAndFeel";
	String hifi="com.jtattoo.plaf.hifi.HiFiLookAndFeel";
	String noire="com.jtattoo.plaf.noire.NoireLookAndFeel";
	String nimbus= "javax.swing.plaf.nimbus.NimbusLookAndFeel";
	String luna="com.jtattoo.plaf.luna.LunaLookAndFeel";
	String aero="com.jtattoo.plaf.aero.AeroLookAndFeel";
	String aluminium="com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
	String bernstein="com.jtattoo.plaf.bernstein.BernsteinLookAndFeel";
	String fast="com.jtattoo.plaf.fast.FastLookAndFeel";
	String graphite="com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
	String mcwin="com.jtattoo.plaf.mcwin.McWinLookAndFeel";
	String mint="com.jtattoo.plaf.mint.MintLookAndFeel";
	String smart="com.jtattoo.plaf.smart.SmartLookAndFeel";
	String texture="com.jtattoo.plaf.texture.TextureLookAndFeel";
	String nimrod="com.nilo.plaf.nimrod.NimRODLookAndFeel";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					pedido.setLocationRelativeTo(null);
					
					pedido.setVisible(true);
					 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	public InterfazPrincipal() {
		setType(Type.UTILITY);
		
		
		try
		
		{
		    JFrame.setDefaultLookAndFeelDecorated(true);
		    JDialog.setDefaultLookAndFeelDecorated(true);
		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

		}

		catch (Exception e)

		{
		    e.printStackTrace();
		}
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazPrincipal.class.getResource("/Grafico/MDpng.png")));
		setResizable(false);
		setTitle("Sistema de Pedidos");
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String Espacios1="                                "/*35 espacios*/,Espacio2="                    "/*20 espacios*/,Espacio3="                     ";
				cantidad=textCant.getText().toString();
				producto=textProducto.getText().toString().trim();
				precio=textPrecio.getText().toString();
				
				System.out.println(producto);
				
				prod.setNombre_producto(producto);
				prod.buscarProducto(conexion.conectar());
				codigo=Integer.toString(prod.getCodigo());
				
				switch(codigo.length()){
				
				case 1:Espacios1="           ";//38
					break;
				case 2:Espacios1="         ";//36
					break;
				case 3:Espacios1="       ";//34
					break;
				case 4:Espacios1="     ";//32
					break;
				case 5:Espacios1="   ";//30
					break;
				default:
					break;
				}
				
				switch (cantidad.length()) {
				case 1:Espacio2="               ";//20
					break;
				case 2:Espacio2="             ";//18
					break;
				case 3:Espacio2="           ";//16
					break;
				case 4:Espacio2="         ";//14
					break;
				case 5:Espacio2="      ";//12
					break;
				case 6:Espacio2="    ";//12
				break;
				case 7:Espacio2="  ";//12
				break;
				default:Espacio2=" ";
					break;
				}
				
				switch(producto.length()){
				case 1:Espacio3="                                                                                                           ";//21
				break;
				case 2:Espacio3="                                                                                                         ";//21
				break;
				case 3:Espacio3="                                                                                                       ";//21
				break;
				case 4:Espacio3="                                                                                                     ";//21
				break;                                                                               
				case 5:Espacio3="                                                                                                   ";//21
				break;
				case 6:Espacio3="                                                                                                 ";//21
				break;
				case 7:Espacio3="                                                                                               ";//21
				break;
				case 8:Espacio3="                                                                                             ";//21
				break;
				case 9:Espacio3="                                                                                           ";//21
				break;
				case 10:Espacio3="                                                                                        ";//21
				break;
				case 11:Espacio3="                                                                                      ";//21
				break;
				case 12:Espacio3="                                                                                    ";//21
				break;
				case 13:Espacio3="                                                                                   ";//21
				break;
				case 14:Espacio3="                                                                                 ";//21
				break;
				case 15:Espacio3="                                                                               ";//21
				break;
				case 16:Espacio3="                                                                             ";//21
				break;
				case 17:Espacio3="                                                                           ";//21
				break;
				case 18:Espacio3="                                                                         ";//21
				break;
				case 19:Espacio3="                                                                       ";//21
				break;
				case 20:Espacio3="                                                                     ";//21
				break;
				case 21:Espacio3="                                                                   ";//21
				break;
				case 22:Espacio3="                                                                 ";//21
				break;
				case 23:Espacio3="                                                               ";//21
				break;
				case 24:Espacio3="                                                             ";//21
				break;
				case 25:Espacio3="                                                           ";//21
				break;
				case 26 :Espacio3="                                                        ";//21
				break;
				case 27:Espacio3="                                                       ";//21
				break;
				case 28:Espacio3="                                                     ";//21
				break;
				case 29:Espacio3="                                                   ";//21
				break;
				case 30:Espacio3="                                                 ";//21
				break;
				case 31:Espacio3="                                               ";//21
				break;
				case 32:Espacio3="                                             ";//21
				break;
				case 33:Espacio3="                                           ";//21
				break;
				case 34:Espacio3="                                         ";//21
				break;
				case 35:Espacio3="                                       ";//21
				break;
				case 36:Espacio3="                                     ";//21
				break;
				case 37:Espacio3="                                   ";//21
				break;
				case 38:Espacio3="                                 ";//21
				break;
				case 39:Espacio3="                               ";//21
				break;
				case 40:Espacio3="                             ";//21
				break;
				case 41:Espacio3="                           ";//21
				break;
				case 42:Espacio3="                         ";//21
				break;
				case 43:Espacio3="                       ";//21
				break;
				case 44:Espacio3="                     ";//21
				break;
				}
				
				
				linea=codigo+Espacios1+cantidad+Espacio2+producto+Espacio3+"$"+precio+"\n";
				
				pedido.textArea.setText(pedido.textArea.getText()+linea);
				
				textCant.setText("");
				textPrecio.setText("");
				textProducto.setText("");
				btnAgregar.transferFocus();
				
			}
		});
		btnAgregar.setBounds(325, 194, 89, 23);
		panel.add(btnAgregar);
		
		
		textPrecio = new JTextField();
		textPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//crea una variable y guarda un caracter presionad
				char TeclaPresionada=arg0.getKeyChar();
				//si es enter, da click en el boton aceptar
				if(TeclaPresionada==KeyEvent.VK_ENTER){
					btnAgregar.doClick();
				}
				
			}
		});
		textPrecio.setBounds(355, 125, 59, 29);
		panel.add(textPrecio);
		textPrecio.setColumns(10);
		
		textProducto = new JTextField();
		textProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//crea una variable y guarda un caracter presionad
				char TeclaPresionada=arg0.getKeyChar();
				//si es enter, da click en el boton aceptar
				if(TeclaPresionada==KeyEvent.VK_ENTER){
					textProducto.transferFocus();
				}
				
			}
		});
		textProducto.setBounds(65, 125, 280, 29);
		panel.add(textProducto);
		textProducto.setColumns(10);
		
		textCant = new JTextField();
		textCant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//crea una variable y guarda un caracter presionad
				char TeclaPresionada=arg0.getKeyChar();
				//si es enter, da click en el boton aceptar
				if(TeclaPresionada==KeyEvent.VK_ENTER){
					textCant.transferFocus();
				}
				
			}
		});
		textCant.setBounds(10, 125, 45, 29);
		panel.add(textCant);
		textCant.setColumns(10);
		
		
		
		JLabel lblCant = new JLabel("Cant.");
		lblCant.setBounds(10, 93, 46, 14);
		panel.add(lblCant);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(65, 93, 280, 14);
		panel.add(lblProducto);
		
		
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(355, 93, 59, 14);
		panel.add(lblPrecio);
		
		JLabel lblMartinezDistribuciones = new JLabel("Martinez Distribuciones");
		lblMartinezDistribuciones.setFont(new Font("Garamond", Font.BOLD, 39));
		lblMartinezDistribuciones.setBounds(10, 11, 404, 39);
		panel.add(lblMartinezDistribuciones);
		
		
		
		TextAutoCompleter AutoCompletar=new TextAutoCompleter(textProducto);
		prod.AgregarSugerenciaProductos(conexion.conectar());
		AutoCompletar.addItems(prod.listaProductos);
		
	}
	
	
	public void LeerArchivo(){
		
		int banderabuffer=0;
		int banderaCompare=0;
		String contenido=null,ruta=System.getProperty("user.dir")+"\\Librerias\\Todos los Servicios.txt";
		try{
            // Abrimos el archivo
            FileInputStream fstreamaux = new FileInputStream(ruta);
            // Creamos el objeto de entrada
            DataInputStream entradaaux = new DataInputStream(fstreamaux);
            // Creamos el Buffer de Lectura
            BufferedReader bufferaux = new BufferedReader(new InputStreamReader(entradaaux));
            String strLineaaux;
            
            // Leer el archivo linea por linea
            while ((strLineaaux = bufferaux.readLine()) != null&&banderabuffer==0)   {

                StringTokenizer tokensaux=new StringTokenizer(strLineaaux,"|");
                
                while(tokensaux.hasMoreTokens()){
                	
                	contenido=tokensaux.nextToken();
                	//System.out.println(contenido);
                	lista.add(contenido);

                	
                }
                
            }
            // Cerramos el archivo
            entradaaux.close();
            
        }catch (Exception e){ //Catch de excepciones
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
	}
	
	public String buscarEnProductos(String productoABuscar){
		
		
		String codigoEncontrado=null,otroAux=null;
		int banderabuffer=0;
		int banderaCompare=0;
		String contenido=null,ruta=System.getProperty("user.dir")+"\\Librerias\\Todos los Servicios.txt";
		
		try{
            // Abrimos el archivo
            FileInputStream fstreamaux = new FileInputStream(ruta);
            // Creamos el objeto de entrada
            DataInputStream entradaaux = new DataInputStream(fstreamaux);
            // Creamos el Buffer de Lectura
            BufferedReader bufferaux = new BufferedReader(new InputStreamReader(entradaaux));
            String strLineaaux;
            
            // Leer el archivo linea por linea
            while ((strLineaaux = bufferaux.readLine()) != null&&banderabuffer==0)   {

                StringTokenizer tokensaux=new StringTokenizer(strLineaaux,"|");
                
                while(tokensaux.hasMoreTokens()){
                	
                	otroAux=tokensaux.nextToken();
                	//System.out.println("codigoEncontrado"+codigoEncontrado);
                	contenido=tokensaux.nextToken().trim();
                	//System.out.println("///////////////////"+contenido);
                	
                	if(contenido.compareTo(productoABuscar)==0){
                		//System.out.println("************************** contenido "+contenido+" codigoencontrado "+otroAux);
                		codigoEncontrado=otroAux;
                		
                		banderabuffer=1;
                	}

                	
                }
                
            }
            // Cerramos el archivo
            entradaaux.close();
            
        }catch (Exception e){ //Catch de excepciones
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
		
		return codigoEncontrado;
	}
}

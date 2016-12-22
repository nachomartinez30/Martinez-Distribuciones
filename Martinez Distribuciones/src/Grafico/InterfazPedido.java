package Grafico;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.naming.ldap.Rdn;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Panel;
import java.awt.TextArea;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 

import java.io.InputStreamReader;
import java.security.CryptoPrimitive;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import PostgreSQL.*;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mxrck.autocompleter.TextAutoCompleter;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.awt.ComponentOrientation;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.Window.Type;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class InterfazPedido extends JFrame {
	
	JTextArea textArea = new JTextArea();
	InterfazPrincipal principal=new InterfazPrincipal();
	public static InterfazPedido frame=new InterfazPedido();
	private JPanel contentPane;
	String titulo;
	private JTextField textNombre;
	private JTextField textNumero;
	ArrayList<Object> lista=new ArrayList<Object>();
	JButton btnExportar;
	AgregarCliente cliente;
	AgregarProducto producto;
	PostgreSQL.ClienteFactura cliFac=new ClienteFactura();
	PostgreSQL.ClienteRemision cliRemi=new ClienteRemision();
	Conexion conexion=new Conexion();
	private JRadioButton rdbtnFacturacion;
	private JRadioButton rdbtnRemisin;
	private JButton btnBorrar;
	private JButton btnAgregar;
	

	
	public InterfazPedido() {
		
		
		
		setResizable(false);
		
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
		try
		
		{
		    JFrame.setDefaultLookAndFeelDecorated(true);
		    JDialog.setDefaultLookAndFeelDecorated(true);
		    UIManager.setLookAndFeel(noire);

		}

		catch (Exception e)

		{
		    e.printStackTrace();
		}
		
		cliente=new AgregarCliente();
		producto=new AgregarProducto();
		
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setTitle("Martinez Distribuciones v 2.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazPedido.class.getResource("/Grafico/MDpng.png")));
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(5, 5, 772, 611);
		contentPane.add(panel);
		panel.setLayout(null);
		
		rdbtnRemisin = new JRadioButton("Remisi\u00F3n");
		rdbtnRemisin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnFacturacion.setSelected(false);
			}
		});
		rdbtnRemisin.setBounds(132, 35, 109, 23);
		panel.add(rdbtnRemisin);
		
		rdbtnFacturacion = new JRadioButton("Facturacion");
		rdbtnFacturacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnRemisin.setSelected(false);
			}
		});
		rdbtnFacturacion.setBounds(243, 35, 109, 23);
		panel.add(rdbtnFacturacion);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(InterfazPedido.class.getResource("/Grafico/task-2x.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				principal.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 181, 719, 345);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(textArea);
		btnAgregar.setEnabled(false);
		btnAgregar.setBounds(530, 566, 103, 23);
		panel.add(btnAgregar);
		
		textNumero = new JTextField();
		textNumero.setEnabled(false);
		textNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//crea una variable y guarda un caracter presionad
				char TeclaPresionada=arg0.getKeyChar();
				//si es enter, da click en el boton aceptar
				if(TeclaPresionada==KeyEvent.VK_ENTER){
					String cliente,numeroCliente,estado=null,fecha=null;
					
					cliente=textNombre.getText().toString().trim();
					numeroCliente=textNumero.getText().toString().trim();
					
					if(rdbtnFacturacion.isSelected()){
						estado="FACTURA";
						}
					if(rdbtnRemisin.isSelected()){
						estado="REMISIÓN";
					}
					
					fecha=FechaParaImprimir();
					
					textNombre.setEnabled(false);
					textNumero.setEnabled(false);
					textArea.setText(fecha+"\n                                "+numeroCliente+"             "+cliente+"    "+"\n"+"                                                                                                                       "+estado+"\n\n\n"
							+"\nCOD.    CANT.\n\n");
					
					
					principal.setVisible(true);
					btnAgregar.setEnabled(true);
					btnExportar.setEnabled(true);
				}
				
			}
		});
		textNumero.setBounds(463, 100, 40, 27);
		panel.add(textNumero);
		textNumero.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setEnabled(false);
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//crea una variable y guarda un caracter presionad
				char TeclaPresionada=arg0.getKeyChar();
				//si es enter, da click en el boton aceptar
				if(TeclaPresionada==KeyEvent.VK_ENTER){
					if(rdbtnFacturacion.isSelected()){
						String cliente=textNombre.getText().toString();
						String numero;
						
						cliFac.setRazon_social(cliente);
						
						cliFac.buscarCliente(conexion.conectar());
						
						numero=Integer.toString(cliFac.getCodigo());
						textNumero.setText(numero);
						textNombre.transferFocus();
						
						
					}
					if(rdbtnRemisin.isSelected()){
						String cliente=textNombre.getText().toString();
						String numero;
						
						cliRemi.setRazon_social(cliente);
						
						cliRemi.buscarCliente(conexion.conectar());
						
						numero=Integer.toString(cliRemi.getCodigo());
						textNumero.setText(numero);
						textNombre.transferFocus();
					}
					
				}
				
			}
		});
		textNombre.setBounds(40, 100, 386, 27);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		TextAutoCompleter AutoCompletar=new TextAutoCompleter(textNombre);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.setHorizontalAlignment(SwingConstants.TRAILING);
		btnVerificar.setIcon(new ImageIcon(InterfazPedido.class.getResource("/Grafico/person-2x.png")));
		btnVerificar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
						
				
				
					if(rdbtnFacturacion.isSelected()&&rdbtnRemisin.isSelected()){
					JOptionPane.showMessageDialog(null, "Porfavor Elija 1 sola Opcion", "Error", JOptionPane.ERROR_MESSAGE);
					rdbtnRemisin.setSelected(false);
					rdbtnFacturacion.setSelected(false);
					}
				
				if(rdbtnFacturacion.isSelected()){
					
					rdbtnRemisin.setEnabled(false);
					rdbtnRemisin.setSelected(false);
					btnVerificar.setEnabled(false);
					textNombre.setEnabled(true);
					textNumero.setEnabled(true);
					
					
					
					//cliFac.listaClientes.clear();
					
					
					cliFac.AgregarSugerenciaClientes(conexion.conectar());
					
					
					AutoCompletar.addItems(cliFac.listaClientes);
					
					
					btnVerificar.transferFocus();
					
				}
				
				if(rdbtnRemisin.isSelected()){
					
					rdbtnFacturacion.setEnabled(false);
					rdbtnFacturacion.setSelected(false);
					btnVerificar.setEnabled(false);
					textNombre.setEnabled(true);
					textNumero.setEnabled(true);
					btnExportar.setEnabled(true);
					
					/*lista.clear();
					LeerArchivoRemisiones();
					AutoCompletar.removeAllItems();
					AutoCompletar.addItems(lista);
					
					btnVerificar.transferFocus();*/
					
					
					cliRemi.AgregarSugerenciaClientes(conexion.conectar());
					
					
					AutoCompletar.addItems(cliRemi.listaClientes);
					
					
					btnVerificar.transferFocus();
					
				}
				
				
		
			}
		});
		btnVerificar.setBounds(449, 35, 103, 27);
		panel.add(btnVerificar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setIcon(new ImageIcon(InterfazPedido.class.getResource("/Grafico/trash-2x.png")));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				textNombre.setEnabled(false);
				textNumero.setEnabled(false);
				textNombre.setText("");
				textNumero.setText("");
				btnVerificar.setEnabled(true);
				rdbtnFacturacion.setEnabled(true);
				rdbtnRemisin.setEnabled(true);
				rdbtnFacturacion.setSelected(false);
				rdbtnRemisin.setSelected(false);
				lista.clear();
				AutoCompletar.removeAllItems();
				btnAgregar.setEnabled(false);
				btnExportar.setEnabled(false);
				AutoCompletar.removeAllItems();
				cliFac.listaClientes.clear();
				cliRemi.listaClientes.clear();
			}
		});
		btnBorrar.setBounds(401, 566, 89, 23);
		panel.add(btnBorrar);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(40, 75, 117, 14);
		panel.add(lblCliente);
		
		
		
		JLabel lblNocliente = new JLabel("No.Cliente");
		lblNocliente.setBounds(453, 75, 62, 14);
		panel.add(lblNocliente);
		
		btnExportar = new JButton("Exportar");
		btnExportar.setIcon(new ImageIcon(InterfazPedido.class.getResource("/Grafico/share-boxed-2x.png")));
		btnExportar.setEnabled(false);
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
					String linea=textArea.getText().toString();
					titulo=textNombre.getText().toString().trim()+textNumero.getText().toString().trim();
					System.out.println(titulo);
					GenerarPDF(linea,titulo);
					textArea.setText("");
					textNombre.setText("");
					textNumero.setText("");
					textNombre.setEnabled(false);
					textNumero.setEnabled(false);
					rdbtnFacturacion.setEnabled(true);
					rdbtnRemisin.setEnabled(true);
					rdbtnFacturacion.setSelected(false);
					rdbtnRemisin.setSelected(false);
					btnVerificar.setEnabled(true);
					btnAgregar.setEnabled(false);
					btnExportar.setEnabled(false);
				
				
				
				lista.clear();
				AutoCompletar.removeAllItems();
				cliFac.listaClientes.clear();
				cliRemi.listaClientes.clear();
				
			}
		});
		btnExportar.setBounds(638, 566, 103, 23);
		panel.add(btnExportar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InterfazPedido.class.getResource("/Grafico/MDCHICO.png")));
		lblNewLabel.setBounds(591, 32, 137, 124);
		panel.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 772, 21);
		panel.add(menuBar);
		
		JMenu mnClientes = new JMenu("Archivos");
		menuBar.add(mnClientes);
		
		JMenuItem mntmCliente = new JMenuItem("Agregar Cliente");
		mnClientes.add(mntmCliente);
		
		JMenuItem mntmProducto = new JMenuItem("Agregar Producto");
		mntmProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			producto.setVisible(true);
			
			}
		});
		mnClientes.add(mntmProducto);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(InterfazPedido.class.getResource("/Grafico/fondo.png")));
		label.setBounds(-11, -15, 783, 643);
		panel.add(label);
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cliente.setVisible(true);
			}
		});
		
		
		
	}
	
	public void GenerarPDF(String areaTexto,String titulo)
	{
		String fecha=Fecha();
		System.out.println(fecha+"FEach");
		String tituloAux=fecha+titulo,ruta=System.getProperty("user.home")+"\\Desktop\\"+tituloAux+".pdf";
		Document documento = new Document();
		FileOutputStream ficheroPdf;
		try {
			ficheroPdf = new FileOutputStream(ruta);
			
			try {
				PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Su Documento se ha creado con exito");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Se ha provocado un error "+e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		documento.open();
		
		try {
			documento.add(new Paragraph(areaTexto));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			
		}
		documento.close();
	   
	}
	
public void LeerArchivoFacturas(){
		
		int banderabuffer=0;
		int banderaCompare=0;
		String contenido=null,ruta=System.getProperty("user.dir")+"\\Librerias\\Todos los Clientes Facturas.txt";
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

public void LeerArchivoRemisiones(){
	
	int banderabuffer=0;
	int banderaCompare=0;
	String contenido=null,ruta=System.getProperty("user.dir")+"\\Librerias\\Todos los Clientes Remisiones.txt";
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

public String buscarEnClientesRemisiones(String ClienteABuscar){
	
	
	String codigoEncontrado=null,otroAux=null;
	int banderabuffer=0;
	int banderaCompare=0;
	String contenido=null,ruta=System.getProperty("user.dir")+"\\Librerias\\Todos los Clientes Remisiones.txt";
	
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
            	
            	if(contenido.compareTo(ClienteABuscar)==0){
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


public String buscarEnClientesFacturas(String ClienteABuscar){
	
	
	String codigoEncontrado=null,otroAux=null;
	int banderabuffer=0;
	int banderaCompare=0;
	String contenido=null,ruta=System.getProperty("user.dir")+"\\Librerias\\Todos los Clientes Facturas.txt";
	
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
            	
            	if(contenido.compareTo(ClienteABuscar)==0){
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

public String Fecha(){
	
	String fecha=null;
	Calendar Fecha = Calendar.getInstance();
	String dia = Integer.toString(Fecha.get(Calendar.DATE));
	String mes = Integer.toString(Fecha.get(Calendar.MONTH)+1);
	String año = Integer.toString(Fecha.get(Calendar.YEAR));
	
	if(dia.length()==1){
		dia="0"+dia;
		
	}
	if(mes.length()==1){
		mes="0"+mes;
	}
	
	año=año.substring(2, año.length());
	
	
	fecha=dia+mes+año;
	return fecha;
}
public String FechaParaImprimir(){
	
	String fecha=null;
	Calendar Fecha = Calendar.getInstance();
	String dia = Integer.toString(Fecha.get(Calendar.DATE));
	String mes = Integer.toString(Fecha.get(Calendar.MONTH)+1);
	String año = Integer.toString(Fecha.get(Calendar.YEAR));
	
	if(dia.length()==1){
		dia="0"+dia;
		
	}
	if(mes.length()==1){
		mes="0"+mes;
	}
	
	año=año.substring(2, año.length());
	
	
	fecha=dia+"-"+mes+"-"+año;
	return fecha;
}
}

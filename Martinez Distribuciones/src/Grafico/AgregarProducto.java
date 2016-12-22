package Grafico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Toolkit;

import javax.swing.JCheckBox;

import PostgreSQL.Conexion;
import PostgreSQL.Productos;

public class AgregarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textNombreProducto;
	private JTextField textCodigo;
	
	PostgreSQL.Productos prod=new Productos();
	Conexion conexion=new Conexion();
	
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
	
	private JCheckBox chckbxIva;

	public AgregarProducto() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarProducto.class.getResource("/Grafico/MDpng.png")));
		setResizable(false);
		
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
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//String linea=textField_1.getText().toString()+"|"+textField.getText().toString().toUpperCase();
				//EscribirArchivo(linea, "producto");
				int codigo;
				double impuesto=0;
				String nombre_producto;
				
				nombre_producto=textNombreProducto.getText().toUpperCase();
				codigo=Integer.parseInt(textCodigo.getText());
				
				if(chckbxIva.isSelected()){
					impuesto=16;
				}
				
				prod.setCodigo(codigo);
				prod.setImpuesto(impuesto);
				prod.setNombre_producto(nombre_producto);
				
				prod.insertarProducto(conexion.conectar());
				
				textNombreProducto.setText("");
				textCodigo.setText(Integer.toString(prod.ultimoProducto(conexion.conectar())));
				chckbxIva.setSelected(false);
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10, 42, 69, 14);
		contentPane.add(lblProducto);
		
		textNombreProducto = new JTextField();
		textNombreProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				//crea una variable y guarda un caracter presionad
				char TeclaPresionada=e.getKeyChar();
				//si es enter, da click en el boton aceptar
				if(TeclaPresionada==KeyEvent.VK_ENTER){
					textNombreProducto.transferFocus();
				}
			}
		});
		textNombreProducto.setBounds(10, 67, 285, 27);
		contentPane.add(textNombreProducto);
		textNombreProducto.setColumns(10);
		
		
		textCodigo = new JTextField();
		textCodigo.setEnabled(false);
		textCodigo.setBounds(315, 67, 86, 27);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		int ultimo=prod.ultimoProducto(conexion.conectar());
		textCodigo.setText(Integer.toString(ultimo));
		
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(315, 42, 69, 14);
		contentPane.add(lblCdigo);
		
		
		btnAgregar.setBounds(335, 143, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textNombreProducto.setText("");
				textCodigo.setText("");
			}
			
		});
		btnLimpiar.setBounds(236, 143, 89, 23);
		contentPane.add(btnLimpiar);
		
		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(AgregarProducto.class.getResource("/Grafico/task-8x.png")));
		label.setBounds(70, 108, 86, 79);
		contentPane.add(label);
		
		chckbxIva = new JCheckBox("IVA");
		chckbxIva.setBounds(315, 97, 86, 23);
		contentPane.add(chckbxIva);
	}
	
	
	
	/////METODOS
	public static void EscribirArchivo(String linea,String tipo){
		
		int Tipo=0;//0-FACTuras 1-REMISIONES 2-Producto
		String nombre="";
		File f;
	
		if(tipo.equals("factura")){
			nombre = System.getProperty("user.dir")+"\\Librerias\\Todos los Clientes Facturas.txt";
		}
		if(tipo.equals("remision")){
			nombre = System.getProperty("user.dir")+"\\Librerias\\Todos los Clientes Remisiones.txt";
		}
		if(tipo.equals("producto")){
			nombre = System.getProperty("user.dir")+"\\Librerias\\Todos los Servicios.txt";
		}
		System.out.println(nombre);
		f = new File(nombre);

		 

		//Escritura

		try{

		FileWriter w = new FileWriter(f,true);

		BufferedWriter bw = new BufferedWriter(w);
	
		
		bw.write(linea);
		bw.newLine();        //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
	
		        //de no hacerlo no se escribirá nada en el archivo
	
		JOptionPane.showMessageDialog(null, "Se ha Agregado Exitosamente");
	
		bw.close();

		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Se ha provocado un error "+e, "Error", JOptionPane.ERROR_MESSAGE);
		};
	
		 

	}
}

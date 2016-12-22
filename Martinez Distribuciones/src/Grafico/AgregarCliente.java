package Grafico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import PostgreSQL.ClienteFactura;
import PostgreSQL.ClienteRemision;
import PostgreSQL.Conexion;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AgregarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textRazonSocial;
	private JTextField textCodigo;
	
	Conexion conexion=new Conexion();
	ClienteFactura cliFact= new ClienteFactura();
	ClienteRemision cliRemi=new ClienteRemision();
	
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
	String nimrod="com.nilo.plaf.nimrod.NimRODLookAndFeel";
	String texture="com.jtattoo.plaf.texture.TextureLookAndFeel";
	
	private JTextField textRFC;
	private JRadioButton rdbtnFacturacion;
	private JRadioButton rdbtnRemision;
	private JButton btnBorrar;
	private JButton btnAgregar;

	/**
	 * Create the frame.
	 */
	public AgregarCliente() {
		
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
		
		rdbtnFacturacion = new JRadioButton("Facturacion");
		rdbtnFacturacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnRemision.setSelected(false);
				
				int codigo;
				rdbtnFacturacion.setSelected(true);
				codigo=cliFact.ultimoCliente(conexion.conectar());
				textCodigo.setText(Integer.toString(codigo));
			}
		});
		rdbtnRemision = new JRadioButton("Remisión");
		rdbtnRemision.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnFacturacion.setSelected(false);
				
				int codigo;
				rdbtnRemision.setSelected(true);
				codigo=cliRemi.ultimoCliente(conexion.conectar());
				textCodigo.setText(Integer.toString(codigo));
				
				
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarCliente.class.getResource("/Grafico/MDpng.png")));
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Razon Social");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(10, 70, 306, 14);
		contentPane.add(lblNombre);
		
		textRazonSocial = new JTextField();
		textRazonSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				//crea una variable y guarda un caracter presionad
				char TeclaPresionada=arg0.getKeyChar();
				//si es enter, da click en el boton aceptar
				if(TeclaPresionada==KeyEvent.VK_ENTER){
					textRazonSocial.transferFocus();
				}
			}
		});
		textRazonSocial.setBounds(10, 95, 306, 27);
		contentPane.add(textRazonSocial);
		textRazonSocial.setColumns(10);
		
		textCodigo = new JTextField();
		textCodigo.setEnabled(false);
		textCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//crea una variable y guarda un caracter presionad
				char TeclaPresionada=e.getKeyChar();
				//si es enter, da click en el boton aceptar
				if(TeclaPresionada==KeyEvent.VK_ENTER){
					textCodigo.transferFocus();
				}
			}
		});
		textCodigo.setBounds(348, 95, 86, 27);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(348, 70, 86, 14);
		contentPane.add(lblCdigo);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estado="null",linea=null;
				
				
				linea=textCodigo.getText().toString()+"|"+textRazonSocial.getText().toString().toUpperCase();
				
				if(rdbtnFacturacion.isSelected()&&rdbtnRemision.isSelected()){
					JOptionPane.showMessageDialog(null, "Porfavor Elija 1 sola Opcion", "Error", JOptionPane.ERROR_MESSAGE);
					rdbtnRemision.setSelected(false);
					rdbtnFacturacion.setSelected(false);
				}else{
					
					if(rdbtnFacturacion.isSelected()){
						String razon_social,rfc;
						int codigo;
						
						rfc=textRFC.getText().toUpperCase();
						codigo=Integer.parseInt(textCodigo.getText());
						razon_social=textRazonSocial.getText().toUpperCase();
						
						if(rfc.equals("")){
							JOptionPane.showMessageDialog(null, "Porfavor ingrese el RFC", "Error", JOptionPane.ERROR_MESSAGE);
							
						}else{
						
						cliFact.setCodigo(codigo);
						cliFact.setRazon_social(razon_social);
						cliFact.setRfc(rfc);
						
						cliFact.insertarCliente(conexion.conectar());
						
						textRazonSocial.setText("");
						textCodigo.setText("");
						textRFC.setText("");
						rdbtnFacturacion.setSelected(false);
						rdbtnRemision.setSelected(false);
						}
						
					}
					if(rdbtnRemision.isSelected()){
						estado="remision";
						
						String razon_social,rfc;
						int codigo;
						
						rfc=textRFC.getText().toUpperCase();
						codigo=Integer.parseInt(textCodigo.getText());
						razon_social=textRazonSocial.getText().toUpperCase();
						
						
						
						cliRemi.setCodigo(codigo);
						cliRemi.setRazon_social(razon_social);
						cliRemi.setRfc(rfc);
						
						cliRemi.insertarCliente(conexion.conectar());
						
						
						textRazonSocial.setText("");
						textCodigo.setText("");
						rdbtnFacturacion.setSelected(false);
						rdbtnRemision.setSelected(false);
					}
					
					
				}
				
			}
		});
		btnAgregar.setBounds(345, 185, 89, 23);
		contentPane.add(btnAgregar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textRazonSocial.setText("");
				textCodigo.setText("");
				rdbtnFacturacion.setSelected(false);
				rdbtnRemision.setSelected(false);
			}
		});
		btnBorrar.setBounds(240, 185, 89, 23);
		contentPane.add(btnBorrar);
		
		
		rdbtnFacturacion.setBounds(23, 33, 109, 23);
		contentPane.add(rdbtnFacturacion);
		
		
		rdbtnRemision.setBounds(134, 33, 109, 23);
		contentPane.add(rdbtnRemision);
		
		textRFC = new JTextField();
		textRFC.setBounds(39, 152, 154, 27);
		contentPane.add(textRFC);
		textRFC.setColumns(10);
		
		JLabel lblRfc = new JLabel("RFC");
		lblRfc.setHorizontalAlignment(SwingConstants.CENTER);
		lblRfc.setBounds(39, 127, 154, 14);
		contentPane.add(lblRfc);
	}
}

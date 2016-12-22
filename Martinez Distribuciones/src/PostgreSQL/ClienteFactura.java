package PostgreSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClienteFactura {

	public ArrayList<Object> listaClientes = new ArrayList<Object>();
	ResultSet rst;
	String razon_social, rfc;
	int codigo, max;

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void insertarCliente(Connection conexion) {
		try {
			Statement stmtl = conexion.createStatement();
			stmtl.executeUpdate("insert into cliente_facturas values('"
					+ this.codigo + "','" + this.razon_social + "','"
					+ this.rfc + "')");
			JOptionPane.showMessageDialog(null, "Su Cliente ha sido insertado");
			///conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,
					"Se ha provocado un error INSERTAR CLIENTE FACTURAS " + e, "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void buscarCliente(Connection conexion) {

		try {
			try (Statement estatuto = conexion.createStatement()) {

				// con executeQuery REALIZA LAS CONSULTAS ESPECIFICAS
				

				rst = estatuto.executeQuery("SELECT * FROM cliente_facturas WHERE razon_social='"
						+ this.razon_social + "'");

				while (rst.next()) {
					setCodigo(rst.getInt("codigo"));
					setRazon_social(rst.getString("razon_social"));
					setRfc(rst.getString("rfc"));
				}
				rst.close();  
	             estatuto.close();  
	             ///conexion.close();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Se ha provocado un error Buscando al Cliente FACTURAS " + e,
					"Error", JOptionPane.ERROR_MESSAGE);
			;
		}

	}

	public void AgregarSugerenciaClientes(Connection conexion) {
		try {
			try (Statement estatuto = conexion.createStatement()) {
				String razon_social2;
				int i = 0;
				// con executeQuery REALIZA LAS CONSULTAS ESPECIFICAS

				
				rst = estatuto.executeQuery("SELECT * FROM cliente_facturas;");
				JOptionPane.showMessageDialog(null,
						"Clientes de Facturas Activados");
				
				while (rst.next()) {
					i++;
					razon_social2 = rst.getString("razon_social");
					listaClientes.add(razon_social2);
				}
				rst.close();  
	             estatuto.close();  
	             ///conexion.close();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Se ha provocado un error Sugerencias Cliente FACTURAS" + e,
					"Error", JOptionPane.ERROR_MESSAGE);
			;
		}
	}

	public int ultimoCliente(Connection conexion) {
		try {
			try (Statement estatuto = conexion.createStatement()) {

				// con executeQuery REALIZA LAS CONSULTAS ESPECIFICAS
				

				rst = estatuto.executeQuery("SELECT max(codigo) FROM cliente_facturas");
				int i = 0;

				while (rst.next()) {

					max = rst.getInt(1);
					System.out.println(max);
				}
				rst.close();  
	             estatuto.close();  
	             /////conexion.close();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return max + 1;
	}

}

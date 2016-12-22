package PostgreSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Productos {

	public ArrayList<Object> listaProductos = new ArrayList<Object>();
	ResultSet rst;
	String nombre_producto;
	int codigo, max;
	double impuesto;

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	public void insertarProducto(Connection conexion) {
		try {
			Statement stmtl = conexion.createStatement();
			stmtl.executeUpdate("insert into producto values('" + this.codigo
					+ "','" + this.nombre_producto + "','" + this.impuesto
					+ "')");
			JOptionPane
					.showMessageDialog(null, "Su Producto ha sido insertado");
			// rst.close();
			//conexion.close();

		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,
					"Se ha provocado un error INSERTAR PRODUCTOS" + e, "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void buscarProducto(Connection conexion) {

		try {
			try (Statement estatuto = conexion.createStatement()) {

				// con executeQuery REALIZA LAS CONSULTAS ESPECIFICAS
				

				rst = estatuto.executeQuery("SELECT * FROM producto WHERE nombre_producto='"
						+ this.nombre_producto + "'");

				while (rst.next()) {
					setCodigo(rst.getInt("codigo"));
					setNombre_producto(rst.getString("nombre_producto"));
					setImpuesto(rst.getDouble("impuesto"));
				}
				rst.close();
				estatuto.close();
				//conexion.close();

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Se ha provocado un error BUSCAR PRODUCTOS" + e, "Error",
					JOptionPane.ERROR_MESSAGE);
			;
		}

	}

	public void AgregarSugerenciaProductos(Connection conexion) {
		try {
			Statement estatuto = conexion.createStatement();
				String razon_social2;
				int i = 0;

				rst = estatuto.executeQuery("SELECT * FROM producto;");;
				
				while (rst.next()) {
					i++;
					razon_social2 = rst.getString("nombre_producto");
					listaProductos.add(razon_social2);
				}
				 rst.close();  
	             estatuto.close();  
	            // conexion.close();
			

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Se ha provocado un error SUGERENCIA PRODUCTOS" + e, "Error",
					JOptionPane.ERROR_MESSAGE);
			;
			e.printStackTrace();
		}
		 
	}

	public int ultimoProducto(Connection conexion) {
		try {
			try (Statement estatuto = conexion.createStatement()) {

				// con executeQuery REALIZA LAS CONSULTAS ESPECIFICAS
				

				rst = estatuto.executeQuery("SELECT max(codigo) FROM producto");
				int i = 0;

				while (rst.next()) {

					max = rst.getInt(1);
					System.out.println(max);
				}
				rst.close();
				estatuto.close();
				//conexion.close();
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return max + 1;
	}

}
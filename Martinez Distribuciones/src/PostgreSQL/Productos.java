package PostgreSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Productos {
	
	public ArrayList<Object> listaProductos=new ArrayList<Object>();
	ResultSet rst;
	String nombre_producto;
	int codigo,max;
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
	
	

	public void insertarProducto(Connection conexion){
		try {
			Statement stmtl=conexion.createStatement();
			stmtl.executeUpdate("insert into producto values('"+this.codigo+"','"+this.nombre_producto+"','"+this.impuesto+"')");
			JOptionPane.showMessageDialog(null, "Su Producto ha sido insertado");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Se ha provocado un error "+e, "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
public void buscarProducto(Connection conexion){
		
		try {
			try(Statement estatuto= conexion.createStatement()){
				
				//con executeQuery REALIZA LAS CONSULTAS ESPECIFICAS
				estatuto.executeQuery("SELECT * FROM producto WHERE nombre_producto='"+this.nombre_producto+"'");
				
				
				rst=estatuto.getResultSet();
				
			
					
				while(rst.next()){
					setCodigo(rst.getInt("codigo"));
					setNombre_producto(rst.getString("nombre_producto"));
					setImpuesto(rst.getDouble("impuesto"));
				}
				
				
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha provocado un error "+e, "Error", JOptionPane.ERROR_MESSAGE);;
		}
		
	}



public void AgregarSugerenciaProductos(Connection conexion){
	try {
		try(Statement estatuto= conexion.createStatement()){
			String razon_social2;
			int i=0;
			//con executeQuery REALIZA LAS CONSULTAS ESPECIFICAS
			
			estatuto.executeQuery("SELECT * FROM producto;");
			
			
			//JOptionPane.showMessageDialog(null, "Clientes de Remisiones Activados");
			rst=estatuto.getResultSet();
			while(rst.next()){
				i++;
				razon_social2=rst.getString("nombre_producto");
				listaProductos.add(razon_social2);
			}
		}
		
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Se ha provocado un error "+e, "Error", JOptionPane.ERROR_MESSAGE);;
	}
}

public int ultimoProducto(Connection conexion){
	try {
		try(Statement estatuto= conexion.createStatement()){
			
			//con executeQuery REALIZA LAS CONSULTAS ESPECIFICAS
			estatuto.executeQuery("SELECT max(codigo) FROM producto");

			rst=estatuto.getResultSet();
			int i=0;
			
			while(rst.next()){
				
				max=rst.getInt(1);
				System.out.println(max);
			}
		}
		
	} catch (Exception e) {
		System.out.println(e);
	}
	return max+1;
}

	

}
package PostgreSQL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {

	/*
	 * public Connection con; public String
	 * Driver="jdbc:postgresql://localhost:5432/Pedidos"; public String
	 * administrador="postgres"; public String pass="lamisma";
	 * 
	 * public Connection conectar(){ try{ final String
	 * CONTROLADOR="org.sqlite.JDBC"; Connection
	 * conexion=DriverManager.getConnection(Driver,administrador,pass);
	 * this.con=conexion; } catch (SQLException ex){ System.out.println(ex); }
	 * return this.con; }
	 */

	//private static String driver = "jdbc:sqlite:D:\\testdb.db";
	private static Connection connect;

	public static Connection getConnection() {
		try {

			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection(getURL());

			// System.out.println(getURL());
			connect.setAutoCommit(false);
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null,
					"Error, No hay base de datos local disponible \n" + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return connect;
	}

	public static void closeConnection() {
		try {
			connect.close();
			//JOptionPane.showConfirmDialog(null, "Desconectado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error, No hay base de datos local disponible" + e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static String getURL() {
		Path currentPath = Paths.get("");
		String path = currentPath.toAbsolutePath().toString();
		// System.err.println("jdbc:sqlite:" + path + "/Pedidos.db");
		// return "jdbc:sqlite:" + path + "/src/db/MuestreoINF_2015.db";

		return "jdbc:sqlite:" + path + "/Pedidos.db"; // Para distribuir

		// return "jdbc:sqlite:" + path + "/src/db/MuestreoINF_2015.db"; //En
		// producci�n.
	}
}

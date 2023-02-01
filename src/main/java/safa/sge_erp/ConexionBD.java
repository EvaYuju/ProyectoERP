package safa.sge_erp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConexionBD {

        public Connection conexion;
        public Statement sentencia;
        public ResultSet resultado;

        public void ConectarBasedeDatos() {
            try {
                final String Controlador = "com.mysql.jdbc.Driver";
                Class.forName(Controlador);
                final String url_bd = "jdbc:mysql://localhost:3307/usuarios_erp";
                conexion = DriverManager.getConnection(url_bd, "root", "root");
                sentencia = conexion.createStatement();
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        public void DesconectarBasedeDatos() {
            try {
                if (conexion != null) {
                    if (sentencia != null) {
                        sentencia.close();
                    }
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.exit(1);
            }
        }

        public Connection getConnection() {
            return conexion;
        }
    }


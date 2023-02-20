package safa.sge_erp;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;



import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import static safa.sge_erp.ConexionBD.conexionBD;
import static safa.sge_erp.ConexionBD.conexionMySQL;


public class ControladorPrincipal implements Initializable {

    @FXML
    private Button btnAcceder;

    @FXML
    private Button btnAceptarRegistro;

    @FXML
    private Button btnCancelarRegistro;

    @FXML
    private Button btnCompraBuscar;

    @FXML
    private Button btnCompras;

    @FXML
    private Button btnComprasBorrar;

    @FXML
    private Button btnComprasModificar;

    @FXML
    private Button btnComprasVolver;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnCrearCompra;

    @FXML
    private Button btnCrearProducto;

    @FXML
    private Button btnCrearVenta;

    @FXML
    private Button btnFacturaBorrar;

    @FXML
    private Button btnFacturaBuscar;

    @FXML
    private Button btnFacturaPDF;

    @FXML
    private Button btnFacturas;

    @FXML
    private Button btnFormCompraCrear;

    @FXML
    private Button btnAceptarProducto;

    @FXML
    private Button btnFormVentaCrear;

    @FXML
    private Button btnInventario;

    @FXML
    private Button btnProductoModificar;

    @FXML
    private Button btnProductoVolver;

    @FXML
    private Button btnProductosBuscar;

    @FXML
    private Button btnRegistro;

    @FXML
    private Button btnVentaBuscar;

    @FXML
    private Button btnVentas;

    @FXML
    private Button btnVentasBorrar;

    @FXML
    private Button btnVentasBorrar1;

    @FXML
    private Button btnVentasModificar;

    @FXML
    private Button btnVentasVolver;

    @FXML
    private Button btnVentasVolver11;

    @FXML
    private Button btnVolverCompra;

    @FXML
    private Button btnVolverProducto;

    @FXML
    private Button btnVolverVenta;

    @FXML
    private TableColumn<Compra, Integer> colCantidad;

    @FXML
    private TableColumn<Venta, Integer> colCantidadV;

    @FXML
    private TableColumn<Venta, String> colClienteV;

    @FXML
    private TableColumn<Compra, String> colDetalle;

    @FXML
    private TableColumn<Venta, String> colDetalleV;

    @FXML
    private TableColumn<Compra, String> colNombre;

    @FXML
    private TableColumn<Venta, String> colNombreV;

    @FXML
    private TableColumn<Compra, Float> colPrecioTotal;

    @FXML
    private TableColumn<Venta, Float> colPrecioUnitV;

    @FXML
    private TableColumn<Compra, Float> colPrecioUnitario;

    @FXML
    private TableColumn<Compra, String> colProveedor;

    @FXML
    private TableColumn<Compra, Integer> colRef;

    @FXML
    private TableColumn<Venta, Integer> colRefV;

    @FXML
    private TableColumn<Venta, Float> colTotalV;

    @FXML
    private GridPane gpBasesDeDatos;

    @FXML
    private Label labelBienvenido;

    @FXML
    private AnchorPane panelBD;

    @FXML
    private AnchorPane panelCompra;

    @FXML
    private AnchorPane panelConectado;

    @FXML
    private AnchorPane panelFacturas;

    @FXML
    private AnchorPane panelFormularioCompra;

    @FXML
    private AnchorPane panelFormularioProducto;

    @FXML
    private AnchorPane panelFormularioVenta;

    @FXML
    private AnchorPane panelLogin;

    @FXML
    private AnchorPane panelMenu;

    @FXML
    private AnchorPane panelProductos;

    @FXML
    private AnchorPane panelRegistro;

    @FXML
    private AnchorPane panelUsuario;

    @FXML
    private AnchorPane panelVentas;

    @FXML
    private TextField tfBuscarCompra;

    @FXML
    private TextField tfBuscarFactura;

    @FXML
    private TextField tfBuscarProducto;

    @FXML
    private TextField tfBuscarVenta;

    @FXML
    private TextField tfFormCompraPrecioUnit;

    @FXML
    private TextField tfFormCompraCantidad;

    @FXML
    private TextField tfFormCompraDetalle;

    @FXML
    private TextField tfFormCompraNombre;

    @FXML
    private TextField tfFormCompraProveedor;

    @FXML
    private TextField tfFormCompraReferencia;

    @FXML
    private TextField tfFormVentaCantidad;

    @FXML
    private TextField tfFormVentaCliente;

    @FXML
    private TextField tfFormVentaDetalle;

    @FXML
    private TextField tfFormVentaNombre;

    @FXML
    private TextField tfFormVentaPrecioUnit;

    @FXML
    private TextField tfFormVentaReferencia;

    @FXML
    private TextField tfNombreProducto;

    @FXML
    private TextField tfCantidadProducto;

    @FXML
    private TextField tfPrecioVentaProducto;

    @FXML
    private TextField tfPrecioCompraProducto;

    @FXML
    private TextField tfLoginClave;

    @FXML
    private TextField tfLoginUsuario;

    @FXML
    private TextField tfRegistroClave;

    @FXML
    private TextField tfRegistroConfirmaClave;

    @FXML
    private TextField tfRegistroEmail;

    @FXML
    private TextField tfRegistroUsuario;

    @FXML
    private TableView<Compra> tvCompras;

    @FXML
    private TableView<Factura> tvFacturas;

    @FXML
    private TableView<Producto> tvProductos;

    @FXML
    private TableView<Venta> tvVentas;




    // Atributos
    Usuario usuario;
    // Compra compra;
    int contadorFilas = 0;
    Connection bdSeleccionada;
    Gson gson = new Gson();
    int counter = 0;
    Boolean editaCompra;
    Boolean editaVenta;
    Boolean editaProducto;



    // Cambio de paneles

    @FXML
    void entrarCompras(ActionEvent event) {
        panelMenu.setVisible(false);
        panelCompra.setVisible(true);
    }

    @FXML
    void entrarFacturas(ActionEvent event) {
        panelMenu.setVisible(false);
        panelFacturas.setVisible(true);
    }

    @FXML
    void entrarInventario(ActionEvent event) {
        panelMenu.setVisible(false);
        panelProductos.setVisible(true);
    }

    @FXML
    void entrarVentas(ActionEvent event) {
        panelMenu.setVisible(false);
        panelVentas.setVisible(true);
    }
    @FXML
    void volverMenu(ActionEvent event) {
        panelCompra.setVisible(false);
        panelVentas.setVisible(false);
        panelFacturas.setVisible(false);
        panelProductos.setVisible(false);
        panelMenu.setVisible(true);
    }

    @FXML
    void volverCompras() {
        panelFormularioCompra.setVisible(false);
        panelCompra.setVisible(true);
        panelVentas.setVisible(false);
        //panelFacturas.setVisible(false);
        //panelInventario.setVisible(false);

        tfFormCompraReferencia.setText("");
        tfFormCompraReferencia.setId("tfNormal");
        tfFormCompraNombre.setText("");
        tfFormCompraNombre.setId("tfNormal");
        tfFormCompraPrecioUnit.setText("");
        tfFormCompraPrecioUnit.setId("tfNormal");
        tfFormCompraCantidad.setText("");
        tfFormCompraCantidad.setId("tfNormal");
        tfFormCompraProveedor.setText("");
        tfFormCompraProveedor.setId("tfNormal");
        tfFormCompraDetalle.setText("");
        tfFormCompraDetalle.setId("tfNormal");

    }
    @FXML
    void volverVentas() {
        panelFormularioVenta.setVisible(false);
        panelCompra.setVisible(false);
        panelVentas.setVisible(true);
        //panelFacturas.setVisible(false);
        //panelInventario.setVisible(false);

        tfFormVentaReferencia.setText("");
        tfFormVentaReferencia.setId("tfNormal");
        tfFormVentaNombre.setText("");
        tfFormVentaNombre.setId("tfNormal");
        tfFormVentaCliente.setText("");
        tfFormVentaCliente.setId("tfNormal");
        tfFormVentaPrecioUnit.setText("");
        tfFormVentaPrecioUnit.setId("tfNormal");
        tfFormVentaCantidad.setText("");
        tfFormVentaCantidad.setId("tfNormal");
        tfFormCompraDetalle.setText("");
        tfFormCompraDetalle.setId("tfNormal");

    }


    // Métodos

    /* MÉTODOS COMUNES */
    private static void ventanaDialogo(String titulo, String mensaje) {
        // Ventana de error
        Dialog<String> ventana = new Dialog<>();
        ventana.setTitle(titulo);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ventana.setContentText(mensaje);
        ventana.getDialogPane().getButtonTypes().add(type);
        ventana.showAndWait();
    }

    public static Alert ventanaConfirmacion(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);

        ButtonType buttonTypeYes = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        return alert;
    }

    String leerCampo(String nombrecampo, String texto, String criterioValidacion) {
        return (texto == null || !texto.matches(criterioValidacion)) ? null : texto;
    }


    /* PANEL USUARIOS */
    @FXML
    public void acceder(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = conexionBD("usuarios_erp");
        String sql = "SELECT * FROM usuarios WHERE nombre=? AND clave = ?";
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, tfLoginUsuario.getText());
            statement.setString(2, tfLoginClave.getText());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Creamos el usuario
                usuario = new Usuario(rs.getString("nombre"), rs.getString("clave"),
                        rs.getString("email"));
                labelBienvenido.setText(labelBienvenido.getText()+usuario.getNombre());
                cargaBasesDatos(rs);

                // Cerramos la conexión con la base de datos de usuarios
                connection.close();

                // Cargar usuario
                // Carga el panel BD
                panelLogin.setVisible(false);
                panelBD.setVisible(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Mensaje de información");
                alert.setContentText("Usuario/Clave incorrectos");

                alert.showAndWait();

            }

        }catch (Exception e) {e.printStackTrace();}
    }

    private void cargaBasesDatos(ResultSet rs) throws SQLException {
        // Cargamos su tabla de bases de datos
        String json = rs.getString("basesDatos");
        if (json != null){ // Comprobamos que tenga alguna base de datos creada
            List<String> basesDeDatos = gson.fromJson(json, new TypeToken<List<String>>() {}.getType());
            usuario.setBasesDeDatos(basesDeDatos);
            for (String bd : basesDeDatos){
                nuevaFila(bd);
            }
        }
    }

    @FXML
    void aceptaRegistro(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Comprobar si los campos de clave y confirmación de clave son iguales
        TextField tfConfirmaClave = tfRegistroConfirmaClave;
        if (!tfRegistroClave.getText().equals(tfConfirmaClave.getText())) {
            // Mostrar un mensaje de error si no coinciden
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Mensaje de error");
            alert.setContentText("Las claves no coinciden");
            alert.showAndWait();
            return;
        }

        Connection connection = conexionBD("usuarios_erp");
        String sql = "INSERT INTO usuarios (nombre, clave, email) VALUES (?,?,?)";
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tfRegistroUsuario.getText());
            statement.setString(2, tfRegistroClave.getText());
            statement.setString(3, tfRegistroEmail.getText());
            System.out.println(statement);
            statement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Mensaje de información");
            alert.setContentText("USUARIO INSERTADO");

            connection.close();

            panelRegistro.setVisible(false);
            panelLogin.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void nuevoRegistro(ActionEvent event) {
        panelLogin.setVisible(false);
        panelRegistro.setVisible(true);

    }

    @FXML
    void cancelaRegistro(ActionEvent event) {
        tfRegistroEmail.setText("");
        tfRegistroClave.setText("");
        tfRegistroUsuario.setText("");
        tfRegistroConfirmaClave.setText("");
        panelRegistro.setVisible(false);
        panelBD.setVisible(true);
    }

    /* PANEL BASES DE DATOS */

    /**
     * Inserta nueva base de datos tras pulsar 'CREAR'
     */
    @FXML
    void crearBD(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Ventana de diálogo para introducir el nombre
        TextInputDialog nombreBD = new TextInputDialog();
        nombreBD.setTitle("INTRODUZCA NOMBRE");
        nombreBD.setHeaderText("Introduzca el nombre de la nueva base de datos");
        Optional<String> resultado = nombreBD.showAndWait();
        // Si se recibe resultado, crea la bbdd y el panel
        if(resultado.isPresent()){
            String nombre = resultado.get().replace(" ", "_");
            nuevaBD(nombre);
            nuevaFila(nombre);
            actualizaBD(nombre, false);
        }
    }

    /**
     * Actualiza la base de datos de usuario con la nueva base de datos
     */
    private void actualizaBD(String nombre, boolean borrar) throws SQLException, ClassNotFoundException {

        // Añadimos la nueva bd a la lista y la pasamos a json
        List<String> listaActualizada = usuario.getBasesDeDatos();
        if (!borrar){
            listaActualizada.add(nombre);
        } else {
            listaActualizada.remove(nombre);
        }
        usuario.setBasesDeDatos(listaActualizada);

        String json = gson.toJson(listaActualizada);

        // Nos conectamos a la bd de usuarios_erp para actualizarla
        Connection connection = conexionBD("usuarios_erp");
        String query = "UPDATE usuarios SET basesDatos = ? WHERE nombre = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, json);
        statement.setString(2, usuario.nombre);
        System.out.println(statement);
        statement.executeUpdate();

        // Cerramos la conexión
        statement.close();
        connection.close();
    }

    /**
     * Crea la base de datos con el nombre proporcionado
     */
    private void nuevaBD(String nombre) {
        try {
            String dbName = formatoNombre(nombre);
            // Conexión con MySQL
            Connection connection = conexionMySQL();
            Statement statement = connection.createStatement();

            // Creación de la base de datos
            statement.executeUpdate("CREATE DATABASE " + dbName);
            statement.close();
            connection.close();
            crearTablas(dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatoNombre(String nombre) {
        return "erp_" + usuario.getNombre() + "_" + nombre; // Nombre de la BD en la memoria
    }

    /**
     * Añade las tablas necesarias para el ERP a la base de datos dada
     */
    private void crearTablas(String nombreBD) {
        try {
            // Conexión con la base de datos
            Connection connection = conexionBD(nombreBD);
            Statement statement = connection.createStatement();

            // Creación de las tablas
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS productos (idProducto INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50), cantidad INT, precioCompra FLOAT, precioVenta FLOAT);");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS compras (referencia INT PRIMARY KEY, nombre VARCHAR(50), precio FLOAT, cantidad INT, total FLOAT, proveedor VARCHAR(50), detalle VARCHAR(100), idProducto INT, "
                    + "FOREIGN KEY (idProducto) REFERENCES productos(idProducto) ON DELETE SET NULL);");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ventas (referencia INT PRIMARY KEY, nombre VARCHAR(50), cliente VARCHAR(50), precio FLOAT, cantidad INT, total FLOAT, detalle VARCHAR(100), idProducto INT, "
                    + "FOREIGN KEY (idProducto) REFERENCES productos(idProducto) ON DELETE SET NULL);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS facturas (idFactura INT AUTO_INCREMENT PRIMARY KEY, total DOUBLE, idCompra INT, idVenta INT, "
                    + "FOREIGN KEY (idCompra) REFERENCES compras(referencia) ON DELETE SET NULL, "
                    + "FOREIGN KEY (idVenta) REFERENCES ventas(referencia) ON DELETE SET NULL);");
        // Cerramos las conexiones
            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Añade una nueva fila al GridPane que muestra las bases de datos del usuario
     */
    void nuevaFila(String nombre) {
        Label lNombre = new Label(nombre);
        lNombre.setId("labelConectar");
        lNombre.setMinWidth(750);
        lNombre.setMaxWidth(750);
        Button btnConectar = new Button("CONECTAR >");
        btnConectar.setId("boton");
        Button btnEliminar = new Button("ELIMINAR");
        btnEliminar.setId("botonEliminar");

        btnConectar.setOnAction(accesoBD(nombre));
        btnEliminar.setOnAction(borrarBD(nombre));

        HBox hbox = new HBox(lNombre, btnConectar, btnEliminar);
        hbox.setSpacing(50);

        StackPane sp = new StackPane(hbox);
        sp.setId("panelConectar");

        gpBasesDeDatos.addRow(contadorFilas + 1, sp);
        GridPane.setHalignment(sp, HPos.CENTER);
        GridPane.setFillWidth(sp, true);
        contadorFilas++;
    }

    /*
    Configura un botón para dar acceso a la base de datos seleccionada
     */
    private EventHandler<ActionEvent> accesoBD(String nombre) {
        return event -> {
            try {
                bdSeleccionada = conexionBD(formatoNombre(nombre));
                Dialog<String> ventana = new Dialog<>();
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                panelBD.setVisible(false);
                panelConectado.setVisible(true);
                panelMenu.setVisible(true);


            } catch (Exception e){
                e.printStackTrace();
            }
        };
    }

    /*
    Configura un botón para borrar la base de datos seleccionada
     */
    private EventHandler<ActionEvent> borrarBD(String nombre) {
        return event -> {
            try {
                Connection con = conexionMySQL();
                Statement stmt = con.createStatement();

                String sql = "DROP DATABASE " + formatoNombre(nombre);
                stmt.executeUpdate(sql);

                ventanaDialogo("ELIMINAR", "Se ha borrado la base de datos: " + nombre);

                actualizaBD(nombre, true);
                contadorFilas = 0;
                clearGridPane(gpBasesDeDatos);
                for (String bd : usuario.getBasesDeDatos()){
                    nuevaFila(bd);
                }


            } catch (Exception e){
                e.printStackTrace();
            }
        };
    }
    public static void clearGridPane(GridPane gridPane) {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                int finalI = i;
                int finalJ = j;
                gridPane.getChildren().removeIf(node ->
                        GridPane.getRowIndex(node) == finalI && GridPane.getColumnIndex(node) == finalJ);
            }
        }
    }


    /* PANEL COMPRAS */

    @FXML
    void aceptarCompra(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (editaCompra) {
            actualizarCompra();
        } else {
            insertarCompra();
        }
    }

    // Botón borrar
    @FXML
    void borrarCompra(ActionEvent event) throws SQLException {
        try {
            String nombre = tvCompras.getSelectionModel().getSelectedItem().getNombre();
            Alert confirmar = ventanaConfirmacion("ELIMINAR PEDIDO", "Eliminar pedido", "¿Está seguro de que desea eliminar este pedido?");
            Optional<ButtonType> resultado = confirmar.showAndWait();
            if (resultado.get() == confirmar.getButtonTypes().get(0)) {
                eliminarCompra(nombre);
                cargarTablaCompra();
            }
        } catch (NullPointerException | ClassNotFoundException e) {
            ventanaDialogo("ERROR", "No hay ningún pedido seleccionado");
        }
    }

    // Botón modificar
    @FXML
    void modificarCompra(ActionEvent event) throws SQLException {
        try {
            cargarFormCompras(tvCompras.getSelectionModel().getSelectedItem().getReferencia());
            panelFormularioCompra.setVisible(true);
            editaCompra = true;
        } catch (NullPointerException | ClassNotFoundException npe) {
            ventanaDialogo("ERROR", "No hay ningún usuario seleccionado");
        }
    }


    private void cargarFormCompras(Integer referencia) throws SQLException, ClassNotFoundException {

        Compra compra = null;
        Statement statement = bdSeleccionada.createStatement();
        String query = "SELECT * FROM compras WHERE referencia = '" + referencia + "'";
        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            compra = new Compra(datos.getInt("referencia"), datos.getString("nombre"), datos.getFloat("precio"), datos.getInt("cantidad"), datos.getFloat("total"), datos.getString("proveedor"), datos.getString("detalle"));
        }

        tfFormCompraReferencia.setText(String.valueOf(compra.getReferencia()));
        tfFormCompraNombre.setText(compra.getNombre());
        tfFormCompraPrecioUnit.setText(String.valueOf(compra.getPrecio()));
        tfFormCompraCantidad.setText(String.valueOf(compra.getCantidad()));
        tfFormCompraProveedor.setText(compra.getProveedor());
        tfFormCompraDetalle.setText(compra.getDetalle());
    }

    // Botón crear de panel compra
    @FXML
    void crearPedidoCompra(ActionEvent event) {
        panelCompra.setVisible(false);
        panelFormularioCompra.setVisible(true);

        editaCompra = false;
        tfFormCompraReferencia.setEditable(true);
    }


    // Cargar en la tabla los pedidos
    @FXML
    void cargarTablaCompra() throws SQLException, ClassNotFoundException {
        ObservableList<Compra> listaCompras = FXCollections.observableArrayList();

        // Conexión con la base de datos
        Statement statement = bdSeleccionada.createStatement();

        String query = "SELECT * FROM compras";
        String filtro = tfBuscarCompra.getText();
        if (!filtro.equals("")) {
            query += " WHERE " + "referencia LIKE '%" + filtro + "%' OR " + "nombre LIKE '%" + filtro + "%' OR " + "proveedor LIKE '%" + filtro + "%' OR " + "detalle LIKE '%" + filtro + "%';";
        }

        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            listaCompras.add(new Compra(datos.getInt("referencia"), datos.getString("nombre"), datos.getFloat("precio"), datos.getInt("cantidad"), datos.getFloat("total"), datos.getString("proveedor"), datos.getString("detalle")));

        }
        tvCompras.setItems(listaCompras);

        colRef.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecioTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        colDetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
    }


    Compra leerValoresCompra(){
        Integer referencia = Integer.valueOf(leerCampo("referencia",tfFormCompraReferencia.getText(),"^[0-9]\\d*(\\.\\d+)?$"));
        String nombre = leerCampo("nombre", tfFormCompraNombre.getText(), ".{1,50}");
        Float precio = Float.valueOf(leerCampo("precio", tfFormCompraPrecioUnit.getText(), "^[1-9]\\d*(\\.\\d+)?$"));
        Integer cantidad = Integer.valueOf(leerCampo("cantidad", tfFormCompraCantidad.getText(), "^[0-9]\\d*(\\.\\d+)?$"));
        Float total = precio * cantidad;
        String proveedor = leerCampo("proveedor", tfFormCompraProveedor.getText(), ".{1,50}");
        String detalle = leerCampo("detalle", tfFormCompraDetalle.getText(), ".{1,50}");
        return new Compra(referencia, nombre, precio, cantidad, total, proveedor, detalle);
    }

    // métodos comprueba error

    private boolean compruebaReferencia(String referencia, StringBuilder mensajeError) {
        if (referencia == null) {
            mensajeError.append("Referencia (Escriba la referencia correcta)\n");
            tfFormCompraReferencia.setId("tfError");
            return true;
        } else {
            tfFormCompraReferencia.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaNombre(String nombre, StringBuilder mensajeError) {
        if (nombre == null) {
            mensajeError.append("Nombre (No puede estar vacío. Máximo 50 caracteres)\n");
            tfFormCompraNombre.setId("tfError");
            return true;
        } else {
            tfFormCompraNombre.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaPrecio(Float precio, StringBuilder mensajeError) {
        if (precio == null) {
            mensajeError.append("Precio (No puede estar vacío. Ingrese una cantidad)\n");
            tfFormCompraPrecioUnit.setId("tfError");
            return true;
        } else {
            tfFormCompraPrecioUnit.setId("tfNormal");
        }
        return false;
    }
    private boolean compruebaCantidad(Integer cantidad, StringBuilder mensajeError) {
        if (cantidad == null) {
            mensajeError.append("Precio (No puede estar vacío. Ingrese una cantidad)\n");
            tfFormCompraCantidad.setId("tfError");
            return true;
        } else {
            tfFormCompraCantidad.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaProveedor(String proveedor, StringBuilder mensajeError) {
        if (proveedor == null) {
            mensajeError.append("Proveedor (No puede estar vacío.)\n");
            tfFormCompraProveedor.setId("tfError");
            return true;
        } else {
            tfFormCompraProveedor.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaDetalle(String detalle, StringBuilder mensajeError) {
        if (detalle == null) {
            mensajeError.append("Detalle (No puede estar vacío.)\n");
            tfFormCompraDetalle.setId("tfError");
            return true;
        } else {
            tfFormCompraDetalle.setId("tfNormal");
        }
        return false;
    }


    boolean mensajeErrorCompra(Compra compra) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaReferencia(compra.getNombre(), mensajeError) ? true : hayError;
        hayError = compruebaNombre(compra.getNombre(), mensajeError) ? true : hayError;
        hayError = compruebaPrecio(compra.getPrecio(), mensajeError) ? true : hayError;
        hayError = compruebaCantidad(compra.getCantidad(), mensajeError) ? true : hayError;
        hayError = compruebaProveedor(compra.getProveedor(), mensajeError) ? true : hayError;
        hayError = compruebaDetalle(compra.getDetalle(), mensajeError) ? true : hayError;


        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError);
            error.showAndWait();
        }
        return hayError;


    }

    // INSERT
    private void insertarCompra() throws SQLException, ClassNotFoundException {
        Compra compra = leerValoresCompra();
        if (!mensajeErrorCompra(compra)) {
            consultaInsertarPedidoCompra(compra);
            cargarTablaCompra();
            volverCompras();
        }
    }


    private void consultaInsertarPedidoCompra(Compra compra) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos
        String sql = "INSERT INTO compras (referencia, nombre, precio, cantidad, total, proveedor, detalle) " + "VALUES (?, ?, ?, ?, ?, ?, ?)"; // Consulta para insertar pedido producto en la base de datos
        try (PreparedStatement statement = bdSeleccionada.prepareStatement(sql))
        {
            int i = 1;
            statement.setInt(1, compra.getReferencia());
            statement.setString(2, compra.getNombre());
            statement.setFloat(3, compra.getPrecio());
            statement.setInt(4, compra.getCantidad());
            statement.setFloat(5, compra.getTotal());
            statement.setString(6, compra.getProveedor());
            statement.setString(7, compra.getDetalle());


            statement.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR PEDIDO", "Pedido insertado con éxito");
        }
    }

    // DELETE
    void eliminarCompra(String referencia) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos

        String sql = "DELETE FROM compras WHERE nombre = ?";

        try (PreparedStatement statement = bdSeleccionada.prepareStatement(sql)) {

            statement.setString(1, String.valueOf(referencia));
            statement.executeUpdate();
        }
    }

    // UPDATE
    private void actualizarCompra() throws SQLException, ClassNotFoundException {
        Compra compra = leerValoresCompra();
        if (!mensajeErrorCompra(compra)) {
            consultaActualizarCompra(compra);
            cargarTablaCompra();
            volverCompras();
        }
    }

    private void consultaActualizarCompra(Compra compra) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos
        String sql = "UPDATE compras SET nombre=?, precio=?, cantidad=?, total=?, proveedor=?, detalle=? WHERE referencia=?";
        try (PreparedStatement statement = bdSeleccionada.prepareStatement(sql)) {
            int i = 1;
            statement.setString(1, compra.getNombre());
            statement.setFloat(2, compra.getPrecio());
            statement.setInt(3, compra.getCantidad());
            statement.setFloat(4, compra.getTotal());
            statement.setString(5, compra.getProveedor());
            statement.setString(6, compra.getDetalle());
            statement.setInt(7, compra.getReferencia());


            statement.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ACTUALIZAR PEDIDO", "Pedido actualizado con éxito");
        }
    }




    /* PANEL VENTAS */

    // En formulario pedido -> clic botón crear pedido
    @FXML
    void aceptarVenta(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (editaVenta) {
            actualizarVenta();
        } else {
            insertarVenta();
        }
    }

    // Botón borrar
    @FXML
    void borrarVenta(ActionEvent event) throws SQLException {
        try {
            String nombre = tvVentas.getSelectionModel().getSelectedItem().getNombre();
            Alert confirmar = ventanaConfirmacion("ELIMINAR PEDIDO", "Eliminar pedido", "¿Está seguro de que desea eliminar este pedido?");
            Optional<ButtonType> resultado = confirmar.showAndWait();
            if (resultado.get() == confirmar.getButtonTypes().get(0)) {
                eliminarVenta(nombre);
                cargarTablaVenta();
            }
        } catch (NullPointerException | ClassNotFoundException e) {
            ventanaDialogo("ERROR", "No hay ningún pedido seleccionado");
        }
    }

    // Botón modificar
    @FXML
    void modificarVenta(ActionEvent event) throws SQLException {
        try {
            cargarFormVentas(tvVentas.getSelectionModel().getSelectedItem().getReferencia());
            panelFormularioVenta.setVisible(true);
            editaVenta = true;
        } catch (NullPointerException | ClassNotFoundException npe) {
            ventanaDialogo("ERROR", "No hay ningún usuario seleccionado");
        }
    }

    private void cargarFormVentas(Integer referencia) throws SQLException, ClassNotFoundException {
        Venta venta = null;
        Statement statement = bdSeleccionada.createStatement();
        String query = "SELECT * FROM ventas WHERE referencia = '" + referencia + "'";
        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            venta = new Venta(datos.getInt("referencia"), datos.getString("nombre"), datos.getString("cliente"), datos.getFloat("precio"), datos.getInt("cantidad"), datos.getFloat("total"), datos.getString("detalle"));
        }
        tfFormVentaReferencia.setEditable(false);
        tfFormVentaReferencia.setText(String.valueOf(venta.getReferencia()));
        tfFormVentaNombre.setText(venta.getNombre());
        tfFormVentaCliente.setText(venta.getCliente());
        tfFormVentaPrecioUnit.setText(String.valueOf(venta.getPrecio()));
        tfFormVentaCantidad.setText(String.valueOf(venta.getCantidad()));
        tfFormVentaDetalle.setText(venta.getDetalle());
    }

    // Botón crear de panel compra
    @FXML
    void crearPedidoVenta(ActionEvent event) {
        panelVentas.setVisible(false);
        panelFormularioVenta.setVisible(true);

        editaVenta = false;
        tfFormVentaReferencia.setEditable(true);
    }

    // Cargar en la tabla los pedidos
    @FXML
    void cargarTablaVenta() throws SQLException, ClassNotFoundException {
        ObservableList<Venta> listaVentas = FXCollections.observableArrayList();

        // Conexión con la base de datos

        Statement statement = bdSeleccionada.createStatement();

        String query = "SELECT * FROM ventas";
        String filtro = tfBuscarVenta.getText();
        if (!filtro.equals("")) {
            query += " WHERE " + "referencia LIKE '%" + filtro + "%' OR " + "nombre LIKE '%" + filtro + "%' OR " + "cliente LIKE '%" + filtro + "%' OR " + "detalle LIKE '%" + filtro + "%';";
        }

        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            listaVentas.add(new Venta(datos.getInt("referencia"), datos.getString("nombre"), datos.getString("cliente"), datos.getFloat("precio"), datos.getInt("cantidad"), datos.getFloat("total"), datos.getString("detalle")));

        }
        tvVentas.setItems(listaVentas);

        colRefV.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        colNombreV.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colClienteV.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colPrecioUnitV.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidadV.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colTotalV.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDetalleV.setCellValueFactory(new PropertyValueFactory<>("detalle"));
    }

    Venta leerValoresVenta(){
        Integer referencia = Integer.valueOf(leerCampo("referencia",tfFormVentaReferencia.getText(),"^[0-9]\\d*(\\.\\d+)?$"));
        String nombre = leerCampo("nombre", tfFormVentaNombre.getText(), ".{1,50}");
        String cliente = leerCampo("cliente", tfFormVentaCliente.getText(), ".{1,50}");
        Float precio = Float.valueOf(leerCampo("precio", tfFormVentaPrecioUnit.getText(), "^[1-9]\\d*(\\.\\d+)?$"));
        Integer cantidad = Integer.valueOf(leerCampo("cantidad", tfFormVentaCantidad.getText(), "^[0-9]\\d*(\\.\\d+)?$"));
        Float total = precio * cantidad;
        String detalle = leerCampo("detalle", tfFormVentaDetalle.getText(), ".{1,50}");
        return new Venta(referencia, nombre, cliente, precio, cantidad, total, detalle);
    }


    // métodos comprueba error
    private boolean compruebaReferenciaV(Integer referencia, StringBuilder mensajeError) {
        if (referencia == null) {
            mensajeError.append("Referencia (Escriba la referencia correcta)\n");
            tfFormVentaReferencia.setId("tfError");
            return true;
        } else {
            tfFormVentaReferencia.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaNombreV(String nombre, StringBuilder mensajeError) {
        if (nombre == null) {
            mensajeError.append("Nombre (No puede estar vacío. Máximo 50 caracteres)\n");
            tfFormVentaNombre.setId("tfError");
            return true;
        } else {
            tfFormVentaNombre.setId("tfError");
        }
        return false;
    }

    private boolean compruebaProveedorV(String cliente, StringBuilder mensajeError) {
        if (cliente == null) {
            mensajeError.append("Cliente (No puede estar vacío.)\n");
            tfFormVentaCliente.setId("tfError");
            return true;
        } else {
            tfFormVentaCliente.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaPrecioV(Float precio, StringBuilder mensajeError) {
        if (precio == null) {
            mensajeError.append("Precio (No puede estar vacío. Ingrese una cantidad)\n");
            tfFormVentaPrecioUnit.setId("tfError");
            return true;
        } else {
            tfFormVentaPrecioUnit.setId("tfNormal");
        }
        return false;
    }
    private boolean compruebaCantidadV(Integer cantidad, StringBuilder mensajeError) {
        if (cantidad == null) {
            mensajeError.append("Precio (No puede estar vacío. Ingrese una cantidad)\n");
            tfFormVentaCantidad.setId("tfError");
            return true;
        } else {
            tfFormVentaCantidad.setId("tfNormal");
        }
        return false;
    }


    private boolean compruebaDetalleV(String detalle, StringBuilder mensajeError) {
        if (detalle == null) {
            mensajeError.append("Detalle (No puede estar vacío.)\n");
            tfFormVentaDetalle.setId("tfError");
            return true;
        } else {
            tfFormVentaDetalle.setId("tfNormal");
        }
        return false;
    }


    boolean mensajeErrorVenta(Venta venta) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaReferenciaV(venta.getReferencia(), mensajeError) ? true : hayError;
        hayError = compruebaNombreV(venta.getNombre(), mensajeError) ? true : hayError;
        hayError = compruebaProveedorV(venta.getCliente(), mensajeError) ? true : hayError;
        hayError = compruebaPrecioV(venta.getPrecio(), mensajeError) ? true : hayError;
        hayError = compruebaCantidadV(venta.getCantidad(), mensajeError) ? true : hayError;
        hayError = compruebaDetalleV(venta.getDetalle(), mensajeError) ? true : hayError;


        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError);
            error.showAndWait();
        }
        return hayError;
        
    }

    // INSERT
    private void insertarVenta() throws SQLException, ClassNotFoundException {
        Venta venta = leerValoresVenta();
        if (!mensajeErrorVenta(venta)) {
            consultaInsertarPedidoVenta(venta);
            cargarTablaVenta();
            volverVentas();
        }
    }

    private void consultaInsertarPedidoVenta(Venta venta) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos



        String sql = "INSERT INTO ventas (referencia, nombre, cliente, precio, cantidad, total, detalle) " + "VALUES (?, ?, ?, ?, ?, ?, ?)"; // Consulta para insertar pedido producto en la base de datos
        try (PreparedStatement statement = bdSeleccionada.prepareStatement(sql))
        {
            int i = 1;
            statement.setInt(1, venta.getReferencia());
            statement.setString(2, venta.getNombre());
            statement.setString(3, venta.getCliente());
            statement.setFloat(4, venta.getPrecio());
            statement.setInt(5, venta.getCantidad());
            statement.setFloat(6, venta.getTotal());
            statement.setString(7, venta.getDetalle());


            statement.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR PEDIDO", "Pedido insertado con éxito");
        }
    }
    // DELETE
    void eliminarVenta(String referencia) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos

        String sql = "DELETE FROM ventas WHERE referencia = ?";

        try (PreparedStatement statement = bdSeleccionada.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(referencia));
            statement.executeUpdate();
        }
    }
    // UPDATE
    private void actualizarVenta() throws SQLException, ClassNotFoundException {
        Venta venta = leerValoresVenta();
        if (!mensajeErrorVenta(venta)) {
            consultaActualizarVenta(venta);
            cargarTablaVenta();
            volverVentas();
        }
    }

    private void consultaActualizarVenta(Venta venta) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos

        String sql = "UPDATE ventas SET nombre=?, cliente=?, precio=?, cantidad=?, total=?, detalle=? WHERE referencia=?";
        try (PreparedStatement statement = bdSeleccionada.prepareStatement(sql)) {
            int i = 1;
            statement.setString(1, venta.getNombre());
            statement.setString(2, venta.getCliente());
            statement.setFloat(3, venta.getPrecio());
            statement.setInt(4, venta.getCantidad());
            statement.setFloat(5, venta.getTotal());
            statement.setString(6, venta.getDetalle());
            statement.setInt(7, venta.getReferencia());

            statement.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ACTUALIZAR PEDIDO", "Pedido actualizado con éxito");
        }
    }

    /* PANEL PRODUCTO */

    @FXML
    void cargarTablaProducto() throws SQLException {
        ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

        Statement stmt = bdSeleccionada.createStatement();

        String query = "SELECT * FROM productos";
        String filtro = tfBuscarProducto.getText();
        if (!filtro.equals("")) {
            query += " WHERE " + "nombre LIKE '%" + filtro +
                    "%' OR " + "cantidad LIKE '%" + filtro +
                    "%' OR " + "precioCompra LIKE '%" + filtro +
                    "%' OR " + "precioVenta LIKE '%" + filtro + "%';";
        }

        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            listaProductos.add(new Producto(datos.getInt("idProducto"), datos.getString("nombre"),
                    datos.getInt("cantidad"), datos.getFloat("precioCompra"),
                    datos.getFloat("precioVenta")));
        }

        tvProductos.setItems(listaProductos);


    }

    @FXML
    void creaProducto(){
        panelProductos.setVisible(false);
        panelFormularioProducto.setVisible(true);

        editaProducto = false;
    }

    @FXML
    void modificarProducto(){

    }

    @FXML
    void borrarProducto(){

    }

    @FXML
    void aceptarProducto() throws SQLException {
        if (editaProducto){
            //actualizaProducto();
        } else {
            insertarProducto();
        }
    }

    private void insertarProducto() throws SQLException {
        Producto producto = leerValoresProducto();
        if (mensajeErrorProducto(producto)){
            consultaInsertarProducto(producto);
            cargarTablaProducto();
        }
    }

    private void consultaInsertarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, cantidad, precioCompra, precioVenta) " + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql)) {
            int i = 1;

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, String.valueOf(producto.getCantidad()));
            stmt.setString(2, String.valueOf(producto.getPrecioCompra()));
            stmt.setString(2, String.valueOf(producto.getPrecioVenta()));

            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR PRODUCTO", "Producto insertado con éxito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Producto leerValoresProducto() {
        String nombre = leerCampo("Nombre", tfNombreProducto.getText(), ".{1,50}");
        Integer cantidad = Integer.valueOf(leerCampo("Cantidad", tfCantidadProducto.getText(), "^[0-9]\\d*(\\.\\d+)?$"));
        Float precioCompra = Float.valueOf(leerCampo("Precio de compra", tfPrecioCompraProducto.getText(), "^[0-9]+(\\.[0-9]+)?$"));
        Float precioVenta = Float.valueOf(leerCampo("Precio de venta", tfPrecioVentaProducto.getText(), "^[0-9]+(\\.[0-9]+)?$"));
        return new Producto(nombre, cantidad, precioCompra, precioVenta);
    }

    // Comprobación de errores en los campos
    private boolean mensajeErrorProducto(Producto producto) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaNombre(producto.getNombre(), mensajeError) ? true : hayError;
        hayError = compruebaCantidad(producto.getCantidad(), mensajeError) ? true : hayError;
        hayError = compruebaPrecio(producto.getPrecioCompra(), mensajeError) ? true : hayError;
        hayError = compruebaPrecio(producto.getPrecioVenta(), mensajeError) ? true : hayError;

        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;
    }

    @FXML
    void volverProducto(){
        panelFormularioProducto.setVisible(false);
        tfNombreProducto.setText("");
        tfCantidadProducto.setText("");
        tfPrecioVentaProducto.setText("");
        tfPrecioCompraProducto.setText("");
        panelProductos.setVisible(true);
    }



    /* PANEL FACTURACIÓN */

    @FXML
    void borrarFactura(){

    }

    @FXML
    void cargarTablaFactura(){

    }

    /* INICIALIZACIÓN */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}

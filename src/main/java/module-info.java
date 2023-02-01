module safa.sge_erp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens safa.sge_erp to javafx.fxml;
    exports safa.sge_erp;
}
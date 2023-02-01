module safa.sge_erp {
    requires javafx.controls;
    requires javafx.fxml;


    opens safa.sge_erp to javafx.fxml;
    exports safa.sge_erp;
}
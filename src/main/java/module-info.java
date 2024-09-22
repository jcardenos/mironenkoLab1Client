module com.example.lab1cryptoprotocolsclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab1cryptoprotocolsclient to javafx.fxml;
    exports com.example.lab1cryptoprotocolsclient;
}
package lk.ijse.deb.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomPageController {

    @FXML
    private AnchorPane anchorPaneAdmin;

    @FXML
    private AnchorPane anchorPaneBuyer;

    @FXML
    private AnchorPane root;

    @FXML
    void AncapaneAdminOnAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/loginpage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");

    }

    @FXML
    void AnchorBuyerMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/loginBuyerPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("login Form");

    }

    @FXML
    void adminOnAction(MouseEvent event) {

    }

}

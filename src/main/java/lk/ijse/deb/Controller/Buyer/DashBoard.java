package lk.ijse.deb.Controller.Buyer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoard {

    @FXML
    private AnchorPane AnchorAboutFactory;

    @FXML
    private AnchorPane ancapaneshirt;

    @FXML
    private AnchorPane ancarPaneTrouser;

    @FXML
    private AnchorPane ancharPaneFrock;

    @FXML
    private AnchorPane ancharPanePlaceOrder;

    @FXML
    private AnchorPane anchorRegisterBuyer;

    @FXML
    private AnchorPane anchornTShirt;

    @FXML
    private Button btnLogOut;

    @FXML
    private Label lblRawMatirialCount;

    @FXML
    private AnchorPane root;

    @FXML
    private Text txtAboutFactory;

    @FXML
    private Text txtFrock;

    @FXML
    private Text txtPlaceOrder;

    @FXML
    private Text txtProduct;

    @FXML
    private Text txtShirt;

    @FXML
    private Text txtTshirt;

    @FXML
    private Text txttrouser;

    @FXML
    void AnchorAboutFactoryOnMouseClicked(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/aboutFactort.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("About Factory Form");
    }

    @FXML
    void AnchorRegisterBuyerMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/buyerPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("buyer");
    }

    @FXML
    void ancapaneShirtOnMouseClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/shirt_.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("shirt");

    }

    @FXML//
    void ancharPaneTshirtMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/t_shirt.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("T shirt");

    }

    @FXML
    void anchonPlaceOrderMouseProductOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/placeOrderPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Plase Order");

    }

    @FXML
    void anchorPaneFrockMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/frock.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("frock");

    }

    @FXML
    void anchorPaneTrouserMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/trouser.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("trouser");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/loginBuyerPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("login");
    }

    @FXML
    void txtAboutFactoryMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/aboutFactort.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("About Factory Form");
    }

    @FXML
    void txtFrockOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/frock.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("frock");
    }

    @FXML
    void txtPlaceOrderMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/placeOrderPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Plase Order");
    }

    @FXML
    void txtRegisterBuyerOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/buyerPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("buyer");
    }

    @FXML
    void txtShirtMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/shirt_.fxml"));


        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("shirt");
    }

    @FXML
    void txtTrouserMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/trouser.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("trouser");

    }

    @FXML
    void txtTshirtOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/t_shirt.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("T shirt");
    }

    public void btnBackMouseOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/loginBuyerPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("login");
    }
}

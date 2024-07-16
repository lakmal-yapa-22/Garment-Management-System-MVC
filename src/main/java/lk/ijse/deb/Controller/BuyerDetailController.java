package lk.ijse.deb.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.Buyer;
import lk.ijse.deb.model.tm.BuyerTm;
import lk.ijse.deb.repository.BuyerRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyerDetailController {

    @FXML
    private Button btnLogOut1;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colContacNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField searchId;

    @FXML
    private TableView<BuyerTm> tblBuyer;

    @FXML
    private Text text;

    @FXML
    private Text txtAddress;

    @FXML
    private Text txtBuyerID;

    @FXML
    private Text txtBuyerTel;

    @FXML
    private Text txtCompanyName;

    @FXML
    private Text txtEmail;

    @FXML
    void DashBoardDashBoardOnClick(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    @FXML
    void btnDashBoardEmployeeOnAction(ActionEvent event) throws IOException {


            AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("eEmployee Form");
        }

    @FXML
    void btnDashBoardOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/orderPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");
    }
    @FXML
    void btnDashBoardPaymentOrderOnAction(ActionEvent event) throws IOException {

        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }

    @FXML
    void btnDashBoardProductOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/productPagee.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("product Form");
    }
    @FXML
    void btnDashBoardRowMatirialOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/rowMatirialPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("row matirial Form");
    }

    @FXML
    void btnDashBoardSupplierOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }


    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = searchId.getText().trim();  // Always trim input to remove unwanted spaces

        try {
            if (id.isEmpty()) {
                clearFields();
                return; // No need for break here, return to exit the method
            }

            Buyer buyer = BuyerRepo.searchById(id);
            if (buyer != null) {

                txtBuyerID.setText(buyer.getId());
                txtBuyerTel.setText(buyer.getTel());
                txtAddress.setText(buyer.getAddress());
                txtEmail.setText(buyer.getEmail());
                txtCompanyName.setText(buyer.getCompany());



            } else {
                // No employee found, show an alert
                new Alert(Alert.AlertType.INFORMATION,"  "+searchId.getText()+"  :  " + "Buyer not found!  "+ "  Enter the correct Buyer ID").show();
                searchId.setText("");
                clearFields();
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions here
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
            searchId.setText("");
            clearFields();
        } catch (Exception e) {
            // Handle any other exceptions here
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            searchId.setText("");
            clearFields();
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtBuyerID.setText("");
        txtBuyerTel.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtCompanyName.setText("");
        searchId.setText("");

    }

    public void initialize() {
        setCellValueFactory();
        loadAllBuyer();
    }

    private void loadAllBuyer() {
        ObservableList<BuyerTm> obList = FXCollections.observableArrayList();

        try {
            List<Buyer> buyerList = BuyerRepo.getAll();
            for (Buyer buyer :buyerList) {
                BuyerTm tm = new BuyerTm(
                        buyer.getId(),
                        buyer.getTel(),
                        buyer.getAddress(),
                        buyer.getEmail(),
                        buyer.getCompany()
                );

                obList.add(tm);
            }

            tblBuyer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colContacNumber.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("company"));
    }


    public void reportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/AllBuyers.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();



        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void btnNewBuyerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerPageAdmin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("buyer Form");
    }
}

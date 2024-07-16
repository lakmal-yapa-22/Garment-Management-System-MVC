package lk.ijse.deb.Controller.Buyer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.Product;
import lk.ijse.deb.model.tm.ProductTm;
import lk.ijse.deb.repository.ProductRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shirt {

    @FXML
    private AnchorPane AnchorPaneEmployee;

    @FXML
    private AnchorPane anchorPaneRowMatirial;

    @FXML
    private AnchorPane anchorPaneRowMatirial1;

    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ProductTm> tblT_shirt;

    @FXML
    private TableColumn<?, ?> colType;


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/dashBoardBuyer.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("dashBoard Form");
    }

    @FXML
    void nextPageOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/trouser.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("trouser Form");


    }
    public void initialize() {
        setCellValueFactory();
        loadAllProductShirt_longShirt();
    }

    private void  loadAllProductShirt_longShirt() {
        ObservableList<ProductTm> obList = FXCollections.observableArrayList();

        try {
            List<Product> productList = ProductRepo.getAllProdctShirtLongShirt();
            for (Product product : productList) {
                ProductTm tm = new ProductTm(
                    product.getId(),
                        product.getQty(),
                        product.getPrice(),
                        product.getType(),
                        product.getColor(),
                        product.getSize()
                );

                obList.add(tm);
            }

            tblT_shirt.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));


    }

    public void BacknextPageOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/t_shirt.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/placeOrderPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("plase Order");
    }

    public void ReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/Allshirt.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();



        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}

   /* */




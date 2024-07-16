package lk.ijse.deb.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.model.tm.OrderDetailTm;
import lk.ijse.deb.repository.OrderDetailRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderController {

    @FXML
    private TableColumn<?, ?> colBuyerCompany;

    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colDiscountRate;

    @FXML
    private TableColumn<?, ?> colDress;

    @FXML
    private TableColumn<?, ?> colFinalAmount;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colPaymentStatus;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField searchId;

    @FXML
    private TableView<OrderDetailTm> tblOrderDetail;

    @FXML
    private Text text;

    @FXML
    private Text txtBuyerName;

    @FXML
    private Text txtDiscountRate;

    @FXML
    private Text txtFinalAmount;

    @FXML
    private Text txtOrderId;

    @FXML
    private Text txtPaymentStatus;

    @FXML
    private Text txtProductType;

    @FXML
    private Text txtQty;

    @FXML
    private Text txtSize;

    @FXML
    private Text txtty;

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
    void btnClearOnAction(ActionEvent event) {
        clearAll();

    }

    private void clearAll() {
        searchId.setText("");
        txtOrderId.setText("");
        txtBuyerName.setText("");
        txtProductType.setText("");
        txtQty.setText("");
        txtty.setText("");
        txtSize.setText("");
        txtDiscountRate.setText("");
        txtFinalAmount.setText("");
        txtPaymentStatus.setText("");

    }

    @FXML
    void btnDashBoardBuyerOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerDetailPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");
    }

    @FXML
    void btnDashBoardEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
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
        Stage stage = (Stage) this.root.getScene().getWindow();
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
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = searchId.getText();

        OrderDetailTm orderDetailTm = OrderDetailRepo.searchByOrder(id);
        if (orderDetailTm != null) {
            txtOrderId.setText(orderDetailTm.getOrderID());
            txtBuyerName.setText(orderDetailTm.getCompanyName());
            txtProductType.setText(orderDetailTm.getProductType());
            txtQty.setText(String.valueOf(orderDetailTm.getQty()));
           txtty.setText(orderDetailTm.getColor());
           txtSize.setText(orderDetailTm.getSize());
          txtDiscountRate.setText(orderDetailTm.getDiscountRate());
          txtFinalAmount.setText(String.valueOf(orderDetailTm.getFinalAmount()));
         txtPaymentStatus.setText(orderDetailTm.getPaymentStatus());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    public void initialize() {
        setCellValueFactory();
        loadAllOrderDetail();
    }

    private void loadAllOrderDetail() {
        ObservableList<OrderDetailTm> obList = FXCollections.observableArrayList();

        try {
            List<OrderDetailTm> orderDetailTmList = OrderDetailRepo.getAllOrderDetail();
            for (OrderDetailTm orderDetailTm : orderDetailTmList) {
                OrderDetailTm tm = new OrderDetailTm(
                       orderDetailTm.getOrderID(),
                orderDetailTm.getCompanyName(),
                orderDetailTm.getProductType(), orderDetailTm.getQty(),
                orderDetailTm.getColor(),
                orderDetailTm.getSize(),
                orderDetailTm.getDiscountRate(),
                orderDetailTm.getFinalAmount(),
                orderDetailTm.getPaymentStatus()




                );

                obList.add(tm);
            }

            tblOrderDetail.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
colBuyerCompany.setCellValueFactory(new PropertyValueFactory<>("companyName"));
     colDress.setCellValueFactory(new PropertyValueFactory<>("productType"));
     colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
       colColor.setCellValueFactory(new  PropertyValueFactory<>("color"));
       colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
       colDiscountRate.setCellValueFactory(new PropertyValueFactory<>("discountRate"));
        colFinalAmount.setCellValueFactory(new  PropertyValueFactory<>("finalAmount"));
      colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

    }


    public void btnNewOrderOnClick(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/adminPlaseOrder.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("placeOrder Form");
    }
}

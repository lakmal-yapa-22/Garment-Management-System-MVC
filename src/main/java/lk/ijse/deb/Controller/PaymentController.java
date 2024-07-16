package lk.ijse.deb.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.OrderDetail;
import lk.ijse.deb.model.Payment;
import lk.ijse.deb.repository.OrderDetailRepo;
import lk.ijse.deb.repository.PaymentRepo;
import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PaymentController {

    @FXML
    private JFXComboBox<String> cmbOrderId;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colCashTender;

    @FXML
    private TableColumn<?, ?> colChesaerName;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colPaymentID;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private TableColumn<?, ?> colPaymentStatus;

    @FXML
    private Label lblFinalAmount;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderTime;

    @FXML
    private Label lblPaymentId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Payment> tblPayment;

    @FXML
    private TextField txtCashTender;

    @FXML
    private TextField txtCasherName;

    @FXML
    private TextField txtPaymentMethod;
    @FXML
    private TextField searchId2;



    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("place Order Form");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
clearFields();
    }

    @FXML
    void btnDashBoardEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("buyer Form");

    }


    @FXML
    void btnDashBoardOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/orderPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");
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
    void cmbOrderOnAction(ActionEvent event) {
        String id = cmbOrderId.getValue();

        try {
            OrderDetail orderDetail = OrderDetailRepo.searchById(id);
            if (orderDetail == null) {
              //  System.out.println("Order detail is null for ID: " + id);
                return;
            }

            String payment = PaymentRepo.searchByOrderId(id);

            if (payment == null) {
                lblFinalAmount.setText(String.valueOf(orderDetail.getFinalAmount()));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Payment Status");
                alert.setHeaderText(null);

                lblFinalAmount.setText("");
                alert.setContentText(cmbOrderId.getValue() + " payment is successful");
                cmbOrderId.setValue("");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Log the exception for diagnosis
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    @FXML
    void savePaymentOnClick(ActionEvent event) {
        try {
            String paymentId = lblPaymentId.getText();
            String orderId = cmbOrderId.getValue();
            String cashier = txtCasherName.getText();
            String paymentMethod = txtPaymentMethod.getText();

            double cashTendered = Double.parseDouble(txtCashTender.getText());
            double finalAmount = Double.parseDouble(lblFinalAmount.getText());
            double balance = cashTendered - finalAmount;

            if (balance >= 0) {
                String paymentStatus = "Pay";
                Payment payment = new Payment(paymentId, orderId, cashTendered, balance, cashier, paymentMethod, paymentStatus);
                boolean isSaved = PaymentRepo.save(payment);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();
                    loadAllPayment();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save payment.").show();
                }
            } else {
                double absBalance = Math.abs(balance);
                String message = String.format("Insufficient funds! Your balance is %.2f", absBalance,"\nplese enter the full payment");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
                alert.setHeaderText(null);
                alert.showAndWait();
                txtCashTender.setText("");
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter valid numbers.").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving payment.").show();
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtCashTender.clear();
        lblFinalAmount.setText("");
     //   lblPaymentId.setText("");
        cmbOrderId.getSelectionModel().clearSelection();
        txtCasherName.clear();
        txtPaymentMethod.clear();
    }





/*  @FXML
    void savePaymentOnClick(ActionEvent event) {




        double x= Double.parseDouble(txtCashTender.getText());
        double y = Double.parseDouble(lblFinalAmount.getText());
        System.out.println(x);
       String payment_id=lblPaymentId.getText();

        String order_id=cmbOrderId.getValue();
        double cashTenderd= Double.parseDouble(txtCashTender.getText());
        double balance=x-y;
         String cashier=txtCasherName.toString();
        String paymentMethod=txtPaymentMethod.getText();
        String paymentStatus="not Pay";

        if(balance>=0){
            paymentStatus="Pay";
        }
        System.out.println(payment_id+order_id+cashTenderd+cashier+paymentMethod+paymentStatus);



        Payment payment = new Payment(payment_id,order_id,cashTenderd,cashier,paymentMethod,paymentStatus);

        try {
            boolean isSaved = PaymentRepo.save(payment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                System.out.println(payment);
          //      clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


    public void initialize() {
        setDate();
        setTime();
       getCurrentPayId();
       getOrdersIds();
       // getProductCodes();
        setCellValueFactory();
        loadAllPayment();
    }

    private void loadAllPayment() {
        ObservableList<Payment> obList = FXCollections.observableArrayList();

        try {
            List<Payment> paymentList = PaymentRepo.getAll();
            for (Payment payment: paymentList) {
                Payment tm = new Payment(
                   payment.getPayment_id(),
                        payment.getOrder_id(),
                        payment.getCashTenderd(),
                        payment.getBalance(),
                        payment.getCashier(),
                        payment.getPaymentMethod(),
                        payment.getPaymentStatus()
                );

                obList.add(tm);
            }

            tblPayment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
colPaymentID.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
colOrderID.setCellValueFactory(new PropertyValueFactory<>("order_id"));
colCashTender.setCellValueFactory(new PropertyValueFactory<>("cashTenderd"));
colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
    colChesaerName.setCellValueFactory(new PropertyValueFactory<>("cashier"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
    }

    private void getOrdersIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = OrderDetailRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbOrderId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentPayId() {
        try {
            String currentId = PaymentRepo.getCurrentId();

            String nextPayId = generateNextPayId(currentId);
            lblPaymentId.setText(nextPayId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextPayId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("Pay\\s+"); // Corrected split function
            int idNum = Integer.parseInt(split[1]);
            return "Pay " + ++idNum; // Corrected return statement
        }
        return "Pay 1"; // Corrected return statement
    }


    private void setTime() {
        LocalTime now = LocalTime.now();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(formatter);


        lblOrderTime.setText(formattedTime);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    public void btnDashBoardBuyerOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerDetailPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");
    }
    public void DashBoardDashBoardOnClick(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void CashTenderOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SALARY,txtCashTender);
    }

    public void PaymentMethodOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.Pmethod,txtPaymentMethod);
    }

    public void CasherNameOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtCasherName);
    }



    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.SALARY,txtCashTender)) return false;
        if (!Regex.setTextColor(TextFields.Pmethod,txtPaymentMethod)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtCasherName)) return false;


        return true;

    }



}

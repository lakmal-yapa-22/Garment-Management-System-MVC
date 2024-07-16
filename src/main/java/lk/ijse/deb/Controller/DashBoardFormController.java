package lk.ijse.deb.Controller;

import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.db.DbConnection;
import net.sf.jasperreports.engine.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardFormController {

    @FXML
    private AnchorPane AnchorPaneEmployee;

    @FXML
    private AnchorPane ancapaneBuyer;

    @FXML
    private AnchorPane ancarPaneOrder;

    @FXML
    private AnchorPane anchorPaneProduct;

    @FXML
    private AnchorPane anchorPaneRowMatirial;

    @FXML
    private AnchorPane anchorPaneSupplier;

    @FXML
    private Button btnLogOut;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane root;

    @FXML
    private Text txtBuyer;

    @FXML
    private Text txtEmployee;

    @FXML
    private Text txtOrder;

    @FXML
    private Text txtProduct;

    @FXML
    private Text txtRowMatirial;

    @FXML
    private Text txtSupplier;

    @FXML
    private Text txtUserName;

    @FXML
    private Text txtWelcome;


    @FXML
    private Label lblBuyerCount;

    @FXML
    private Label lblEmployeeCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblProductCount;

    @FXML
    private Label lblRawMatirialCount;

    @FXML
    private Label lblSupplierCount;
    private int employeeCount;
    private int supplierCount;
    private int buyerCount;

    private int orderCount;
    private int productCount;
    private  int rawMatirialCount;

    public void initialize() {
        buyerInitialize();
        supplierInitialize();
        employeeInitializer();
      orderInitializer();
       productInitializer();
       rowmatirialInitializer();

    }

    private void rowmatirialInitializer() {
        try {
            rawMatirialCount = getRowMatirialCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setRawMatirialCount(rawMatirialCount);
    }

    private void setRawMatirialCount(int rawMatirialCount) {
        lblRawMatirialCount.setText(String.valueOf(rawMatirialCount));
    }
    private int getRowMatirialCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS row_count FROM RawMaterial ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("row_count");
        }
        return 0;


    }

    private void productInitializer() {
        try {
            productCount = getProductCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setProductCount(productCount);
    }

    private void setProductCount(int productCount) {
        lblProductCount.setText(String.valueOf(productCount));
    }
    private int getProductCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS product_count FROM  Product  ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("product_count");
        }
        return 0;

    }

    private void orderInitializer() {
        try {
            orderCount = getOrderCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setOrderCount(orderCount);
    }

    private void setOrderCount(int orderCount) {
        lblOrderCount.setText(String.valueOf(orderCount));
    }
    private int getOrderCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS order_count FROM Orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("order_count");
        }
        return 0;

    }

    private void employeeInitializer() {
        try {
            employeeCount = getEmployeeCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setEmployeeCount(employeeCount);
    }

    private void setEmployeeCount(int employeeCount) {
        lblEmployeeCount.setText(String.valueOf(employeeCount));
    }
    private int getEmployeeCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS employee_count FROM Employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("employee_count");
        }
        return 0;
    }



    public void buyerInitialize() {
        try {
            buyerCount = getBuyerCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setBuyerCount(buyerCount);
    }

    private void setBuyerCount(int buyerCount) {
        lblBuyerCount.setText(String.valueOf(buyerCount));
    }
    private int getBuyerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS buyer_count FROM Buyer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("buyer_count");
        }
        return 0;
    }


    public void supplierInitialize() {
        try {
            supplierCount = getSupplierCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setSupplierCount(supplierCount);
    }


    private void setSupplierCount(int supplierCount) {
        lblSupplierCount.setText(String.valueOf(supplierCount));
    }

    private int getSupplierCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS supplier_count FROM Supplier";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("supplier_count");
        }
        return 0;
    }
    @FXML
    void AnchorPaneEmployeeOnMouseClicked(MouseEvent event) throws IOException {
      employeeNavigare();

    }

    private void employeeNavigare() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("employee Form");
        new SlideInLeft(root).play();
    }

    @FXML
    void txtEmployeeMouseOnClick(MouseEvent event) throws IOException {
        employeeNavigare();

    }








    @FXML
    void ancapaneBuyerMouseOnClick(MouseEvent event) throws IOException {

        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerDetailPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");

    }



    @FXML
    void ancarPaneOrderMouseOnClick(MouseEvent event) throws IOException {
        OrderNavigare();

    }
    @FXML
    void txtOrderMouseOnClick(MouseEvent event) throws IOException {
        OrderNavigare();

    }

    @FXML
    void ancharPaneMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/rowMatirialPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Row Matirial Form");

    }


    @FXML
    void anchorPaneMouseProductOnClick(MouseEvent event) throws IOException {
        ProductNavigare();


    }


    @FXML
    void txtBuyerMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerDetailPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");

    }








    @FXML
    void txtProductOnClick(MouseEvent event) throws IOException {

        ProductNavigare();
    }


    private void ProductNavigare() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/productPagee.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("product Form");
    }


    @FXML
    void txtRowMatirialMouseOnClick(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/rowMatirialPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Row Matirial Form");

    }

    @FXML
    void txtSupplierMouseOnClick(MouseEvent event) throws IOException {
        SupplierNavigare();


    }
    private void SupplierNavigare() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }

    public void AnchorSupplierPaneMouseOnClick(MouseEvent mouseEvent) throws IOException {
        SupplierNavigare();
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/loginpage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");

    }

    public void btnDashBoardEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        employeeNavigare();
    }

    public void btnDashBoadSupplierOnAction(ActionEvent actionEvent) throws IOException {
        SupplierNavigare();
    }

    public void btnDashBoardProductOnAction(ActionEvent actionEvent) throws IOException {
        ProductNavigare();
    }

    public void btnDashBoardRowMatirialOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/rowMatirialPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Row matirial Form");
    }

    public void btnDashBoardBuyerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerDetailPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");

    }

    public void btnDashBoardOrderOnAction(ActionEvent actionEvent) throws IOException {
        OrderNavigare();
    }
    private void OrderNavigare() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/orderPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");
    }






    public void anchorPaneMousePaymentOnClick(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }

    public void txtPaymentOnClick(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }

    public void dashBoardPaymentOnMouseClick(MouseEvent mouseEvent) throws IOException {

    }

    public void btnDashBoardPaymentOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }
}

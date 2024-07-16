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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.model.tm.ProductTm;
import lk.ijse.deb.repository.ProductRepo;
import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductFormController {

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductPrice;

    @FXML
    private TableColumn<?, ?> colProductQty;

    @FXML
    private TableColumn<?, ?> colProductType;
    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colSize;
    @FXML
    private TextField txtProductSize;

    @FXML
    private TextField txtProductColor;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ProductTm> tblProduct;

    @FXML
    private TextField txtProducPrice;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductQty;

    @FXML
    private TextField txtProductType;

    @FXML
    private Text txtWelcome;

    @FXML
    private TextField searchId;

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
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtProductId.setText("");
        txtProductQty.setText("");
        txtProducPrice.setText("");
        txtProductType.setText("");
        txtProductColor.setText("");
searchId.setText("");
        txtProductSize.setText("");
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
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/orderPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");

    }










    public void btnDashBoardSupplierOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }


    public void btnDashBoardRowMatirialOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/rowMatirialPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("row matirial Form");
    }

    public void btnDashBoardBuyerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerDetailPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtProductId.getText();

        try {
            boolean isDeleted = ProductRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "product deleted!").show();
                clearFields();
                loadAllProduct();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtProductId.getText();
        int qty= Integer.parseInt(txtProductQty.getText());
     double price= Double.parseDouble(txtProducPrice.getText());

         String type=txtProductType.getText();
         String color=txtProductColor.getText();
         String size =txtProductSize.getText();


        if (isValied()) {
            boolean isSaved = false;
            try {
                isSaved = ProductRepo.save( new ProductTm(id, qty,price,type,color,size));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();

            }

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                clearFields();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
        loadAllProduct();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtProductId.getText().trim();  // Retrieves the product ID and trims whitespace
        int qty = 0;  // Initialize quantity
        double price = 0.0;  // Initialize price
        String type = txtProductType.getText().trim();  // Retrieves the product type and trims whitespace
        String color=txtProductColor.getText().trim();
        String size =txtProductSize.getText().trim();
        // Check for empty strings in critical fields
        if (id.isEmpty() || type.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Product ID and Type cannot be empty.").show();
            return;  // Exit the method if any critical field is empty
        }

        // Try to parse quantity and price
        try {
            qty = Integer.parseInt(txtProductQty.getText().trim());
            price = Double.parseDouble(txtProducPrice.getText().trim());
        } catch (NumberFormatException e) {
            // Log the parsing error and continue with default values of 0, since they are acceptable
            System.out.println("Error parsing number: " + e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Invalid numeric values entered. Please enter valid numbers.").show();
            return;  // Exit if there's a parsing error to avoid further complications
        }

        // Create the product model with the obtained values
        ProductTm product = new ProductTm(id, qty, price, type,color,size);

        // Attempt to update the product in the database
        try {
            boolean isUpdated = ProductRepo.update(product);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Product successfully updated!").show();
                clearFields();
                loadAllProduct();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update product. No changes detected or connection issue.").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
            e.printStackTrace();  // Print stack trace for debugging purposes
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = searchId.getText().trim();  // Always trim input to remove unwanted spaces

        try {
            if (id.isEmpty()) {
                clearFields();
                return; // No need for break here, return to exit the method
            }

            ProductTm product = ProductRepo.searchById(id);
            if (product != null) {

                // Successfully found the employee, set values in text fields
                txtProductId.setText(product.getId());
                txtProductQty.setText(String.valueOf(product.getQty()));

                txtProducPrice.setText(String.valueOf(product.getPrice()));

                txtProductType.setText(product.getType());
                txtProductColor.setText(product.getColor());
                txtProductSize.setText(product.getSize());



            } else {
                // No employee found, show an alert
                new Alert(Alert.AlertType.INFORMATION,"  "+searchId.getText()+"  :  " + "Product not found!  "+ "  Enter the correct product ID").show();
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


/*
    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = searchId.getText();

        ProductTm product =ProductRepo.searchById(id);
        if (product != null) {

            txtProductId.setText(product.getId());
            txtProductQty.setText(String.valueOf(product.getQty()));

            txtProducPrice.setText(String.valueOf(product.getPrice()));

            txtProductType.setText(product.getType());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "product not found!").show();
        }
    }

    */


    public void initialize() {
        setCellValueFactory();
        loadAllProduct();
    }

    private void loadAllProduct() {

        ObservableList<ProductTm> obList = FXCollections.observableArrayList();

        try {
            List<ProductTm> productList = ProductRepo.getAll();
            for (ProductTm product : productList) {
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

            tblProduct.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));


    }


    public void ProductIdOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IDP,txtProductId);
    }

    public void ProductTypeOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PType,txtProductType);
    }

    public void ProductPriceOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtProducPrice);
    }

    public void ProductQtyOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SALARY,txtProductQty);
    }
    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.IDP,txtProductId)) return false;
        if (!Regex.setTextColor(TextFields.PType,txtProductType)) return false;
        if (!Regex.setTextColor(TextFields.DOUBLE,txtProducPrice)) return false;
        if (!Regex.setTextColor(TextFields.SALARY,txtProductQty)) return false;
        if (!Regex.setTextColor(TextFields.PColor,txtProductColor)) return false;
        if (!Regex.setTextColor(TextFields.PSize,txtProductSize)) return false;




        return true;


    }

    public void btnDashBoardPaymentOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }

    public void DashBoardDashBoardOnClick(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void ProductColorOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PColor,txtProductColor);
    }

    public void ProductSizeOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PSize,txtProductSize);
    }
}


/*













    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void btnDashBoardEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnDashBoardRowMatirialOnAction(ActionEvent actionEvent) {
    }

    public void btnDashBoardBuyerOnAction(ActionEvent actionEvent) {
    }

    public void btnDashBoardOrderOnAction(ActionEvent actionEvent) {
    }
}
*/
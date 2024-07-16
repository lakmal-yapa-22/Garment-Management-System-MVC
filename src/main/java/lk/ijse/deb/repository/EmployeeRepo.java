package lk.ijse.deb.repository;


import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.Employee;
import lk.ijse.deb.model.tm.EmployeeTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class EmployeeRepo {


    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Employee> empList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String salary =resultSet.getString(4);
            String address= resultSet.getString(5);
            String birthday =resultSet.getString(6);
            String productId= resultSet.getString(7);


         Employee  employee = new Employee(id, name,tel,salary,address,birthday,productId);
            empList.add(employee);
        }
        return empList;
    }

    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?, ?, ? ,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getContactNumber());
        pstm.setObject(4 ,employee.getSalary());
        pstm.setObject(5,employee.getAddress() );
        pstm.setObject(6,employee.getBirthday());
        pstm.setObject(7, employee.getProductId());


        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE employee_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET employee_name  = ?, contact_number = ?, salary    = ? ,address = ?,birthday = ?, product_id = ? WHERE employee_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getContactNumber());
        pstm.setObject(3, employee.getSalary());
        pstm.setObject(4, employee.getAddress());
        pstm.setObject(5,employee.getBirthday());
        pstm.setObject(6,employee.getProductId());

        pstm.setObject(7,employee.getId());

        return pstm.executeUpdate() > 0;
    }

    public static EmployeeTm searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE employee_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String empid = resultSet.getString(1); // Highlighted: employee_id
            String name = resultSet.getString(2);
            String contactNumber = resultSet.getString(3); // Highlighted: contactNumber
            String salary = resultSet.getString(4); // Highlighted: salary
            String address = resultSet.getString(5);
            String d_o_b = resultSet.getString(6); // Highlighted: date of birth
            String ProductId = resultSet.getString(7); // Highlighted: ProductId

            EmployeeTm employee = new EmployeeTm(empid, name, contactNumber, salary, address, d_o_b, ProductId);

            return employee;
        }

        return null;
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT employee_id  FROM Employee ";
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection()
                .prepareStatement(sql)
                .executeQuery();

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }




    /*

@FXML
void txtSearchOnAction(ActionEvent event) {
    String id = searchId.getText().trim();  // Always trim input to remove unwanted spaces

    try {
        if (id.isEmpty()) {
            clearFields();
            return; // No need for break here, return to exit the method
        }

        EmployeeTm employee = EmployeeRepo.searchById(id);
        if (employee != null) {
            // Successfully found the employee, set values in text fields
            txtId.setText(employee.getId());
            txtName.setText(employee.getName());
            txtContactNumber.setText(employee.getContactNumber());
            txtSalary.setText(String.valueOf(employee.getSalary()));  // Convert int to String here
            txtAddress.setText(employee.getAddress());
            txtD_O_B.setText(employee.getBirthday());


            highlightText(txtName, id);
            highlightText(txtContactNumber, id);
            highlightText(txtSalary, id);
            highlightText(txtAddress, id);
            highlightText(txtD_O_B, id);

            cmbProductIdFeeId.setValue(employee.getProductId());
        } else {
            // No employee found, show an alert
            new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
            clearFields();
        }
    } catch (SQLException e) {
        // Handle any SQL exceptions here
        new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
    } catch (Exception e) {
        // Handle any other exceptions here
        new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
    }
}

void highlightText(TextField textField, String searchText) {
    String text = textField.getText();
    int index = text.toLowerCase().indexOf(searchText.toLowerCase());
    if (index >= 0) {
        // Highlight matching text
        textField.clear();
        textField.setStyle(""); // Clear previous style
        textField.setText(text.substring(0, index));
        textField.appendText(text.substring(index, index + searchText.length()));
        textField.setStyle("-fx-text-fill: red;"); // Example color, you can adjust as needed
        textField.appendText(text.substring(index + searchText.length()));
    }
}

void clearFields() {
    txtId.clear();
    txtName.clear();
    txtContactNumber.clear();
    txtSalary.clear();
    txtAddress.clear();
    txtD_O_B.clear();
    cmbProductIdFeeId.setValue(null);
}
*/
}

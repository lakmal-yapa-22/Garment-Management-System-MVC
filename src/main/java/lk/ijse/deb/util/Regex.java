package lk.ijse.deb.util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regex {
    public static boolean isTextFieldValid(TextFields textField, String text){
        String filed = "";

        switch (textField){
            case IDE:
                filed = "^E\\d{3,10}$";
                break;
            case NAME:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case CONTACT:
                filed="^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case SALARY:
                filed="^\\d+$";
                break ;
            case DATE:
                filed = "^\\d{4}-\\d{2}-\\d{2}$";
                break;
            case ADDRESS:
                filed = "^[A-Za-z0-9\\s\\-_.,'\"/&@!?():;%+=#]*$";
                break;
            case IDS:
                filed = "^S\\d{3,10}$";
                break;

            case IDP:
                filed = "^P\\d{3,10}$";
                break;
            case DOUBLE:
                filed = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case IDB:
                filed = "^B\\d{3,10}$";
                break;
            case IDR:
                filed = "^([0-9]){1,}$";
                break;
            case PSize:
                filed = "^(S|M|L|XL|XXL)$";
                break;
            case PColor :
                filed = "^(white|black|blue|red|green|yelow)$";
                break;
            case PType :
                filed = "^(shirt|t-shirt|long_sleve_t_shirt|long_sleve_shirt|pant|trouser|frock)$";
                break;
            case Pmethod:
                filed = "^(cash|card)$";
                break;
            case PASSWORD:
                filed = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$";
                break;






        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextFields location, TextField textField) {
        if (Regex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-text-fill: Green;");

            return true;
        } else {
            textField.setStyle("-fx-text-fill: Red;");
            return false;
        }
    }




}

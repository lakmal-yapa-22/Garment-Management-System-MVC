package lk.ijse.deb.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class Employee {
    private String id;
    private String name;

    private String contactNumber;
    private  String salary;



    private String address;
    private String birthday;

    private String ProductId;




    public Employee(String id, String qty, String price, String type) {
    }
}
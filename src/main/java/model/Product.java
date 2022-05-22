package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Product {
    private String productID;
    private String productName;
    private String productCategory;
    private String unit;
    private double productPrice;
    private int productQuantity;
}

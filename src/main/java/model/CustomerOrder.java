package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerOrder {
    private Customer customer;
    private Product product;
    private Integer productQuantity;
}

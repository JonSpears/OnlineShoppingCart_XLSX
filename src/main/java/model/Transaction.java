package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor@AllArgsConstructor@Data
public class Transaction {
    private String customerName;
    private LocalDateTime dateOfPurchase;
    double totalPrice;
}

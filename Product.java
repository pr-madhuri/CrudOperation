package com.ProductSimulation.product.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productId")
    private int productId;
    @Column(name = "productName")
    private String productName;
   @Column(name="productPerUnitPrice")
    private double productPerUnitPrice;
   @Column(name="productQty")
    private int productQty;

   @Column(name = "user_qty")
   private int userQty;
}

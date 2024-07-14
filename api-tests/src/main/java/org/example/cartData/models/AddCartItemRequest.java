package org.example.cartData.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCartItemRequest {

    private String productId;
    private int countOfProducts;

}
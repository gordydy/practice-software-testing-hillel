package org.example.cartData.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDetails {

    private String id;
    private Double additionalDiscountPercentage;
    private Double lat;
    private Double lng;
    private List<CartItem> cartItems;
}
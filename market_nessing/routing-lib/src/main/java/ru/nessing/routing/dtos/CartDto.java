package ru.nessing.routing.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CartDto {
    private List<ru.nessing.routing.dtos.CartItemDto> items;
    private int totalPrice;
}

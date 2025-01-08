package io.conduktor.kfk.service.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
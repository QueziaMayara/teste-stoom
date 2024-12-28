package br.com.stoom.store.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String description;
    private boolean active;
    private Integer categoryId;
    private Integer brandId;
    private BigDecimal price;

}

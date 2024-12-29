package br.com.stoom.store.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandDTO {

    private Long id;
    private String description;
    private boolean active;
}

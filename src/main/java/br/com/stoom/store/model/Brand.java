package br.com.stoom.store.model;

import br.com.stoom.store.model.dto.BrandDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    @SequenceGenerator(name = "brand_sequence", sequenceName = "BRAND_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    public Brand() {
    }

    public Brand(BrandDTO dto) {
        this.id = dto.getId();
        this.description = dto.getDescription();
        this.active = dto.isActive();
    }
}
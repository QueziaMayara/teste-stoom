package br.com.stoom.store.model;

import br.com.stoom.store.model.dto.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    @SequenceGenerator(name = "category_sequence", sequenceName = "CATEGORY_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    public Category() {
    }

    public Category(CategoryDTO categoryDTO) {
        this.id = categoryDTO.getId();
        this.active = categoryDTO.isActive();
        this.description = categoryDTO.getDescription();
    }
}
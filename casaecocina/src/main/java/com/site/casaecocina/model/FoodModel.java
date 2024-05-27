package com.site.casaecocina.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_FOOD")
@Getter
@Setter
public class FoodModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFood;
    private String title;
    private String image;
    private Integer price;
}

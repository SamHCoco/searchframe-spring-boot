package com.samhcoco.project.searchframe.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "product_line_id")
    private int productLineId;

    @Column(name = "manufacturer")
    private String manufacturer;
}

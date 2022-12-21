package com.samhcoco.project.searchframe.model;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity class for testing search.
 */
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class VehicleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "vehicle_line_id")
    private int vehicleLineId;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;
}

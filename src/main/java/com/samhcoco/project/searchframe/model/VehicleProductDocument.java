package com.samhcoco.project.searchframe.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class VehicleProductDocument {
    @Id
    private int id;
    private String name;
    private int vehicleLineId;
    private String manufacturer;
    private BigDecimal price;
    private String currency;
}

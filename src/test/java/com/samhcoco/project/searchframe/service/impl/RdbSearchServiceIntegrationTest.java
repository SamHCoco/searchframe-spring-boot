package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.model.SearchCriteria;
import com.samhcoco.project.searchframe.model.VehicleProduct;
import com.samhcoco.project.searchframe.repository.VehicleProductRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.samhcoco.project.searchframe.util.ImportJsonUtil.importJson;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class RdbSearchServiceIntegrationTest {

    private static final String MANUFACTURER = "manufacturer";
    private static final String PRICE = "price";

    @Autowired
    private RdbSearchService searchService;

    @Autowired
    private VehicleProductRepository vehicleProductRepository;

    public void setupData() throws IOException {
        vehicleProductRepository.deleteAll();
        List<VehicleProduct> vehicles = importJson(new ClassPathResource("data/products.json"), VehicleProduct[].class);
        vehicleProductRepository.saveAll(vehicles);
    }

    @Test
    public void testQuery_noPagination_equalAndInRange() throws IOException {
        setupData();
        val query = Query.builder()
                         .searchCriteria(List.of(
                                 SearchCriteria.builder()
                                         .field(MANUFACTURER)
                                         .value("Mercedes-Benz")
                                         .operation("==")
                                         .build(),
                                SearchCriteria.builder()
                                              .field(PRICE)
                                              .value("30000")
                                              .operation(">=")
                                              .build(),
                                SearchCriteria.builder()
                                              .field(PRICE)
                                              .value("45000")
                                              .operation("<=")
                                              .build()
                         )).strict(true)
                           .build();

        val result = (List<VehicleProduct>) searchService.query(query, VehicleProduct.class);
        val expected = listAllTestData().values().stream()
                                                 .filter(v -> List.of(1, 2, 3, 5).contains(v.getId()))
                                                 .collect(Collectors.toList());

        result.forEach(r -> {
            val expectedVehicle = expected.stream()
                                    .filter(e -> e.getId() == r.getId())
                                    .findFirst()
                                    .orElse(null);

            assertThat(expectedVehicle).isNotNull();
            assertThat(r).isEqualTo(expectedVehicle);
        });

    }

    /**
     * Returns all test data in the database for assertion comparison.
     * @return All test data.
     */
    private Map<Integer, VehicleProduct> listAllTestData() {
        val data = new HashMap<Integer, VehicleProduct>();
        data.put(1, VehicleProduct.builder()
                .id(1)
                .name("Mercedes-Benz GLC")
                .vehicleLineId(1)
                .manufacturer("Mercedes-Benz")
                .price(new BigDecimal("45000.00"))
                .currency("GBP")
                .build());

        data.put(2, VehicleProduct.builder()
                .id(2)
                .name("Mercedes-Benz A-Class")
                .vehicleLineId(1)
                .manufacturer("Mercedes-Benz")
                .price(new BigDecimal("30800.00"))
                .currency("GBP")
                .build());

        data.put(3, VehicleProduct.builder()
                .id(3)
                .name("Mercedes-Benz C-Class")
                .vehicleLineId(1)
                .manufacturer("Mercedes-Benz")
                .price(new BigDecimal("43060.00"))
                .currency("GBP")
                .build());

        data.put(4, VehicleProduct.builder()
                .id(4)
                .name("Mercedes-Benz CLS")
                .vehicleLineId(1)
                .manufacturer("Mercedes-Benz")
                .price(new BigDecimal("70000.00"))
                .currency("EUR")
                .build());

        data.put(5, VehicleProduct.builder()
                .id(5)
                .name("Mercedes-Benz GLB")
                .vehicleLineId(1)
                .manufacturer("Mercedes-Benz")
                .price(new BigDecimal("40000.00"))
                .currency("EUR")
                .build());

        data.put(6, VehicleProduct.builder()
                .id(6)
                .name("BMW X5")
                .vehicleLineId(1)
                .manufacturer("BMW")
                .price(new BigDecimal("61000.00"))
                .currency("USD")
                .build());

        data.put(7, VehicleProduct.builder()
                .id(7)
                .name("BMW iX3")
                .vehicleLineId(1)
                .manufacturer("BMW")
                .price(new BigDecimal("70000.00"))
                .currency("EUR")
                .build());

        data.put(8, VehicleProduct.builder()
                .id(8)
                .name("Cessna 120")
                .vehicleLineId(3)
                .manufacturer("Cessna")
                .price(new BigDecimal("30000.00"))
                .currency("GBP")
                .build());

        data.put(9, VehicleProduct.builder()
                .id(9)
                .name("Cessna 170")
                .vehicleLineId(3)
                .manufacturer("Cessna")
                .price(new BigDecimal("82000.00"))
                .currency("EUR")
                .build());

        data.put(10, VehicleProduct.builder()
                .id(10)
                .name("Cessna 162 Skycatcher")
                .vehicleLineId(3)
                .manufacturer("Cessna")
                .price(new BigDecimal("5000000.00"))
                .currency("USD")
                .build());

        data.put(11, VehicleProduct.builder()
                .id(11)
                .name("Cessna Citation M2")
                .vehicleLineId(3)
                .manufacturer("Cessna")
                .price(new BigDecimal("1200000.00"))
                .currency("USD")
                .build());

        data.put(12, VehicleProduct.builder()
                .id(12)
                .name("Boeing 787")
                .vehicleLineId(3)
                .manufacturer("Boeing")
                .price(new BigDecimal("300500000.00"))
                .currency("USD")
                .build());

        data.put(13, VehicleProduct.builder()
                .id(13)
                .name("Airbus A380")
                .vehicleLineId(3)
                .manufacturer("Airbus")
                .price(new BigDecimal("300500000.00"))
                .currency("USD")
                .build());

        return data;
    }

}

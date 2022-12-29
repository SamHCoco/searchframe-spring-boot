package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.VehicleProduct;
import com.samhcoco.project.searchframe.repository.VehicleProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static com.samhcoco.project.searchframe.util.ImportJsonUtil.importJson;

@SpringBootTest
@ActiveProfiles("test")
public class RdbSearchServiceIntegrationTest {

    private static final String MANUFACTURER = "manufacturer";
    private static final String PRICE = "price";

    @Autowired
    private RdbSearchService searchService;

    @Autowired
    private VehicleProductRepository vehicleProductRepository;

    @Test
    public void setup() throws IOException {
        vehicleProductRepository.deleteAll();
        List<VehicleProduct> vehicles = importJson(new ClassPathResource("data/products.json"), VehicleProduct[].class);
        vehicleProductRepository.saveAll(vehicles);
    }

    @Test
    public void testQuery_noPagination_equalAndInRange(){
//        val query = Query.builder()
//                         .searchCriteria(List.of(
//                                SearchCriteria.builder()
//                                              .field(MANUFACTURER)
//                                              .value("Mercedes-Benz")
//                                              .operation("==")
//                                              .build(),
//                                SearchCriteria.builder()
//                                              .field(PRICE)
//                                              .value("30000")
//                                              .operation(">=")
//                                              .build(),
//                                SearchCriteria.builder()
//                                              .field(PRICE)
//                                              .value("45000")
//                                              .operation("<=")
//                                              .build()
//                         )).strict(true)
//                           .build();
//
//        val result = (List<VehicleProduct>) searchService.query(query, VehicleProduct.class);
//
//        val MERCEDES = "Mercedes-Benz";
//        val GBP = "GBP";
//
//        val expectedList = new ArrayList<VehicleProduct>(List.of(
//                                VehicleProduct.builder()
//                                        .id(1)
//                                        .name("Mercedes-Benz GLC")
//                                        .vehicleLineId(1)
//                                        .manufacturer(MERCEDES)
//                                        .price(new BigDecimal("45000.00"))
//                                        .currency(GBP)
//                                        .build(),
//                                VehicleProduct.builder()
//                                        .id(2)
//                                        .name("Mercedes-Benz A-Class")
//                                        .vehicleLineId(1)
//                                        .manufacturer(MERCEDES)
//                                        .price(new BigDecimal("30800.00"))
//                                        .currency(GBP)
//                                        .build(),
//                                VehicleProduct.builder()
//                                        .id(3)
//                                        .name("Mercedes-Benz C-Class")
//                                        .vehicleLineId(1)
//                                        .manufacturer(MERCEDES)
//                                        .price(new BigDecimal("43060.00"))
//                                        .currency(GBP)
//                                        .build()
//        ));
//
//        result.forEach(r -> {
//            val expected = expectedList.stream()
//                                  .filter(e -> e.getId() == r.getId())
//                                  .findFirst()
//                                  .orElse(null);
//            assertThat(r).isEqualTo(null);
//        });
    }

}

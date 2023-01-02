package com.samhcoco.project.searchframe.service.impl;

import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.model.SearchCriteria;
import com.samhcoco.project.searchframe.model.VehicleProduct;
import com.samhcoco.project.searchframe.repository.VehicleProductRepository;
import lombok.NonNull;
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
import java.util.Set;
import java.util.stream.Collectors;

import static com.samhcoco.project.searchframe.util.ImportJsonUtil.importJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("test")
public class RdbSearchServiceIntegrationTest {

    private static final String MANUFACTURER = "manufacturer";
    private static final String PRICE = "price";
    private static final String NAME = "name";

    @Autowired
    private RdbSearchService searchService;

    @Autowired
    private VehicleProductRepository vehicleProductRepository;

    public void setupData() {
        if (vehicleProductRepository.count() == 0) {
            try {
                List<VehicleProduct> vehicles = importJson(new ClassPathResource("data/products.json"), VehicleProduct[].class);
                vehicleProductRepository.saveAll(vehicles);
            } catch (IOException e) {
                fail(e.getMessage());
            }
        }
    }

    @Test
    public void testQuery_strict_equal() {
        setupData();
        val criteria = SearchCriteria.builder()
                                    .field(NAME)
                                    .value("Cessna 170")
                                    .operation("==")
                                    .build();

        val query = Query.builder()
                        .searchCriteria(List.of(criteria))
                        .strict(true)
                        .build();

        val expected = listExpectedByIds(Set.of(9));
        val result = (List<VehicleProduct>) searchService.query(query, VehicleProduct.class);

        assertThat(result).hasSize(1);
        assertResult(result, expected);
    }

    @Test
    public void testQuery_strict_lessThan() {
        setupData();
        val criteria = SearchCriteria.builder()
                                    .field(PRICE)
                                    .value("35000")
                                    .operation("<")
                                    .build();

        val query = Query.builder()
                        .searchCriteria(List.of(criteria))
                        .strict(true)
                        .build();

        val result = (List<VehicleProduct>) searchService.query(query, VehicleProduct.class);
        val expected = listExpectedByIds(Set.of(8, 2));

        assertThat(result).hasSize(2);
        assertResult(result, expected);
    }

    @Test
    public void testQuery_strict_lessThanOrEqual() {
        setupData();
        val criteria = SearchCriteria.builder()
                                    .field(PRICE)
                                    .value("30000")
                                    .operation("<=")
                                    .build();

        val query = Query.builder()
                        .searchCriteria(List.of(criteria))
                        .strict(true)
                        .build();

        val result = (List<VehicleProduct>) searchService.query(query, VehicleProduct.class);
        val expected = listExpectedByIds(Set.of(8));

        assertThat(result).hasSize(1);
        assertResult(result, expected);
    }

    @Test
    public void testQuery_strict_greaterThan() {
        setupData();
        val criteria = SearchCriteria.builder()
                                    .field(PRICE)
                                    .value("70000")
                                    .operation(">")
                                    .build();

        val query = Query.builder()
                .searchCriteria(List.of(criteria))
                .strict(true)
                .build();

        val result = (List<VehicleProduct>) searchService.query(query, VehicleProduct.class);
        val expected = listExpectedByIds(Set.of(9 ,10, 11, 12, 13));

        assertThat(result).hasSize(5);
        assertResult(result, expected);
    }

    @Test
    public void testQuery_strict_greaterThanOrEqual() {
        setupData();
        val criteria = SearchCriteria.builder()
                .field(PRICE)
                .value("70000")
                .operation(">")
                .build();

        val query = Query.builder()
                        .searchCriteria(List.of(criteria))
                        .strict(true)
                        .build();

        val result = (List<VehicleProduct>) searchService.query(query, VehicleProduct.class);
        val expected = listExpectedByIds(Set.of(9 ,10, 11, 12, 13));

        assertThat(result).hasSize(5);
        assertResult(result, expected);
    }

    @Test
    public void testQuery_strict_equalAndInRange() throws IOException {
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
        val expected = listExpectedByIds(Set.of(1, 2, 3, 5));

        assertThat(result).hasSize(4);
        assertResult(result, expected);
    }

    @Test
    public void testQuery_strict_like() {
        setupData();
        val query = Query.builder()
                .searchCriteria(List.of(
                        SearchCriteria.builder()
                                .field(NAME)
                                .value("x")
                                .operation("like")
                                .build()
                )).strict(true)
                .build();

        val result = (List<VehicleProduct>) searchService.query(query, VehicleProduct.class);
        val expected = listExpectedByIds(Set.of(6, 7));

        assertThat(result).hasSize(2);
        assertResult(result, expected);
    }

    /**
     * Performs test assertion for query search result.
     * @param result {@link VehicleProduct} query result produced by a given test.
     * @param expectedList Expected {@link VehicleProduct} for a given test.
     */
    private void assertResult(@NonNull List<VehicleProduct> result, @NonNull List<VehicleProduct> expectedList) {
        result.forEach(r -> {
            val expectedVehicle = expectedList.stream()
                    .filter(e -> e.getId() == r.getId())
                    .findFirst()
                    .orElse(null);

            assertThat(expectedVehicle).isNotNull();
            assertThat(r).isEqualTo(expectedVehicle);
        });
    }

    /**
     * Returns the {@link VehicleProduct} specified by id.
     * @param ids {@link VehicleProduct} ids.
     * @return List of {@link VehicleProduct}s.
     */
    private List<VehicleProduct> listExpectedByIds(@NonNull Set<Integer> ids) {
        return listAllTestData().values().stream()
                .filter(v -> ids.contains(v.getId()))
                .collect(Collectors.toList());
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

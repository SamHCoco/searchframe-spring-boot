package com.samhcoco.project.searchframe;

import com.samhcoco.project.searchframe.model.Queries;
import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.model.VehicleProduct;
import com.samhcoco.project.searchframe.service.impl.RdbSearchService;
import com.samhcoco.project.searchframe.service.impl.VehicleProductService;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VehicleProductServiceTest {

    private VehicleProductService underTest;
    private VehicleProduct product = new VehicleProduct();
    private Collection<VehicleProduct> result = List.of(product);
    private Query query = new Query();
    private Queries queries = new Queries();

    @Mock
    private RdbSearchService searchService;

    @Before
    public void setup() {
        underTest = new VehicleProductService(searchService);
    }

    @Test
    public void testQuery() {
        when(searchService.query(any(Query.class), (Class) any())).thenReturn(result);

        val queryResult = (List<VehicleProduct>) underTest.query(query);

        assertThat(queryResult.get(0)).isEqualTo(product);
        assertThat(queryResult.size()).isEqualTo(1);

        verify(searchService).query(any(Query.class), (Class) any());
    }

    @Test
    public void testQuery_queries() {
        when(searchService.query(any(Queries.class), (Class) any())).thenReturn(result);

        val queryResult = (List<VehicleProduct>) underTest.query(queries);

        assertThat(queryResult.get(0)).isEqualTo(product);
        assertThat(queryResult.size()).isEqualTo(1);

        verify(searchService).query(any(Queries.class), (Class) any());
    }

    @Test(expected = NullPointerException.class)
    public void testQuery_nullArg() {
        underTest.query((Query) null);
    }

    @Test(expected = NullPointerException.class)
    public void testQuery_queries_nullArg() {
        underTest.query((Queries) null);
    }

}

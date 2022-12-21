package com.samhcoco.project.searchframe;

import com.samhcoco.project.searchframe.model.Query;
import com.samhcoco.project.searchframe.service.impl.RdbSearchService;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RdbSearchServiceTest {

    private RdbSearchService underTest;

    @Mock
    private JpaSpecificationExecutor executor;

    @Before
    public void setup() {
        underTest = new RdbSearchService<>(executor);
    }

    @Test
    public void testQuery() {
        val query = new Query();
        val object = new Object();

        when(executor.findAll(any())).thenReturn(List.of(object));

        val queryResult = (List<Object>) underTest.query(query);

        assertThat(queryResult.get(0)).isEqualTo(object);
        assertThat(queryResult.size()).isEqualTo(1);

        verify(executor).findAll(any());
    }

    @Test(expected = NullPointerException.class)
    public void testQuery_nullArg() {
        underTest.query((Query) null);
    }

}

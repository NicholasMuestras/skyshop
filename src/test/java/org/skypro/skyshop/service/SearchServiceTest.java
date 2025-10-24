package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SearchServiceTest {

    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        this.storageService = mock(StorageService.class);
        this.searchService = new SearchService(this.storageService);
    }

    @Test
    void searchItemInEmptyStorage() {
        Mockito.when(this.storageService.getAll())
                .thenReturn(new HashSet<Searchable>().stream().collect(Collectors.toSet()));
        assertThat(searchService.search("someString"))
                .isNotNull()
                .isInstanceOf(Collection.class)
                .isEmpty();
    }

    @Test
    void searchItemWhichNotExists() {
        Collection<Searchable> result = new HashSet<>();
        result.add(new SimpleProduct(UUID.randomUUID(), "Test product", 100));

        Mockito.when(this.storageService.getAll())
                .thenReturn(result.stream().collect(Collectors.toSet()));

        assertThat(searchService.search("Another product"))
                .isNotNull()
                .isInstanceOf(Collection.class)
                .isEmpty();
    }

    @Test
    void searchItemWhichExists() {
        Searchable testProduct = new SimpleProduct(UUID.randomUUID(), "Test product", 100);
        Collection<Searchable> result = new HashSet<Searchable>();
        result.add(testProduct);

        Mockito.when(this.storageService.getAll())
                .thenReturn(result.stream().collect(Collectors.toSet()));

        assertThat(searchService.search("Test product"))
                .isNotNull()
                .isInstanceOf(Collection.class)
                .allSatisfy(o -> assertThat(o).isSameAs(testProduct));
    }
}

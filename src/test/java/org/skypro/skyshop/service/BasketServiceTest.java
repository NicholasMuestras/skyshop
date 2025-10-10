package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.view.basket.UserBasket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class BasketServiceTest {

    private StorageService storageService;
    private ProductBasket productBasket;
    private BasketService basketService;

    @BeforeEach
    void setUp() {
        this.storageService = mock(StorageService.class);
        this.productBasket = mock(ProductBasket.class);
        this.basketService = new BasketService(this.productBasket, this.storageService);
    }

    @Test
    void addItemWhichNotExists() {
        Mockito.when(this.storageService.getProductById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchProductException.class, () -> this.basketService.addProduct(randomUUID()));
    }

    @Test
    void addItemWillCallAnAddProductMethod() {
        Product testProduct = new SimpleProduct(UUID.randomUUID(), "Test product", 100);

        Mockito.when(this.storageService.getProductById(testProduct.getId()))
                .thenReturn(Optional.of(testProduct));

        this.basketService.addProduct(testProduct.getId());

        Mockito.verify(this.productBasket, Mockito.times(1))
                .addProduct(testProduct.getId());
    }

    @Test
    void getEmptyBasket() {
        assertThat(this.basketService.getUserBasket())
                .isNotNull()
                .isInstanceOf(UserBasket.class)
                .satisfies(o -> assertThat(o.getItems()).isEmpty());
    }

    @Test
    void getNotEmptyBasket() {
        Product testProduct = new SimpleProduct(UUID.randomUUID(), "Test product", 100);

        Mockito.when(this.storageService.getProductById(testProduct.getId()))
                .thenReturn(Optional.of(testProduct));

        Map<UUID, Integer> basketGetProductsResultWithOneItem = new HashMap<>();
        basketGetProductsResultWithOneItem.put(testProduct.getId(), 1);

        Mockito.when(this.productBasket.getProducts())
                .thenReturn(basketGetProductsResultWithOneItem);

        assertThat(this.basketService.getUserBasket())
                .isNotNull()
                .isInstanceOf(UserBasket.class)
                .satisfies(o -> assertThat(o.getItems()).isNotEmpty());
    }
}

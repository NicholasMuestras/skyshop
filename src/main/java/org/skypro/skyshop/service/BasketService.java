package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.view.basket.BasketItem;
import org.skypro.skyshop.view.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storage;

    public BasketService(ProductBasket basket, StorageService storage) {
        this.basket = basket;
        this.storage = storage;
    }

    public void addProduct(UUID id) {
        if (this.storage.getProductById(id).isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.basket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        return new UserBasket(this.basket.getProducts()
                .entrySet()
                .stream()
                .map(
                        item -> {
                            Optional<Product> product = this.storage.getProductById(item.getKey());

                            if (product.isEmpty()) {
                                throw new RuntimeException(
                                        "Basket has a Product which not exist in a Storage. ProductId: " + item.getKey()
                                );
                            }

                            return new BasketItem(product.get(), item.getValue());
                        }
                )
                .toList()
        );
    }
}

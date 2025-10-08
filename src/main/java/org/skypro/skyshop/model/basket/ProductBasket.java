package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basket = new HashMap<>();

    public void addProduct(UUID id) {
        if (this.basket.containsKey(id)) {
            basket.put(id, basket.get(id) + 1);
        } else {
            basket.put(id, 1);
        }
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(this.basket);
    }
}

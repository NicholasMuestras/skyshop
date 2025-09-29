package org.skypro.skyshop.view.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> items;
    private int total;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.calculateTotal();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    private void calculateTotal() {
        this.items.forEach(o -> this.total += o.getProduct().getPrice() * o.getCount());
    }
}

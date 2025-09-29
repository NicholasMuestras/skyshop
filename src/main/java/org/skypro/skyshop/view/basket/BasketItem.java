package org.skypro.skyshop.view.basket;

import org.skypro.skyshop.model.product.Product;

public final class BasketItem {
    private final Product product;
    private final int count;

    public BasketItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }
}

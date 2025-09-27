package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class DiscountedProduct extends Product {

    private final int price;
    private final int discount;

    public DiscountedProduct(UUID id, String name, int price, int discount) {
        super(id, name);

        if (price < 1) {
            throw new IllegalArgumentException("Field 'price' must be equal 1 or more");
        }

        this.price = price;

        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Field 'discount' must be value from 0 to 100.");
        }

        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return price - (price / 100 * this.discount);
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.getPrice() + " (" + this.getDiscount() + "%)";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Searchable)) {
            return false;
        }

        if (!(object instanceof DiscountedProduct o)) {
            return false;
        }

        return Objects.equals(this.getName(), o.getName()) && this.getPrice() == o.getPrice() && this.getDiscount() == o.getDiscount();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName() + getPrice() + getDiscount());
    }
}

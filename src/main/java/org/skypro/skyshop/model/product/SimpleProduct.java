package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;

public class SimpleProduct extends Product {

    private final int price;

    public SimpleProduct(String name, int price) throws IllegalArgumentException {
        super(name);

        if (price < 1) {
            throw new IllegalArgumentException("Field 'price' must be equal 1 or more");
        }

        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.getPrice();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Searchable)) {
            return false;
        }

        if (!(object instanceof SimpleProduct)) {
            return false;
        }

        return Objects.equals(this.getName(), ((Searchable) object).getName()) && this.getPrice() == ((SimpleProduct) object).getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName() + getPrice());
    }
}

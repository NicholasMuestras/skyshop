package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;

abstract public class Product implements Searchable {
    private final String name;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Field 'name' must be not empty.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public int getPrice();

    abstract public boolean isSpecial();

    @Override
    public String getSearchTerm() {
        return this.getName();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (this.getClass() != object.getClass()) {
            return false;
        }

        return Objects.equals(this.getName(), ((Searchable) object).getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}

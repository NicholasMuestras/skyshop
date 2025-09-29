package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

abstract public class Product implements Searchable {
    private final UUID id;
    private final String name;

    public Product(UUID id, String name) {
        this.id = id;

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Field 'name' must be not empty.");
        }

        this.name = name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    abstract public int getPrice();

    abstract public boolean isSpecial();

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return this.getName();
    }

    @Override
    @JsonIgnore
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

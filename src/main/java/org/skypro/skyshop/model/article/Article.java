package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String body;

    public Article(UUID id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return this.getTitle() + System.lineSeparator() + this.getBody();
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return this.getTitle() + " " + this.getBody();
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return this.getTitle();
    }

    @Override
    public boolean equals(Object article) {
        if (article == this) {
            return true;
        }

        if (!(article instanceof Searchable)) {
            return false;
        }

        if (!(article instanceof Article o)) {
            return false;
        }

        return Objects.equals(this.getName(), o.getName()) && Objects.equals(this.getBody(), o.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getName() + this.getBody());
    }
}

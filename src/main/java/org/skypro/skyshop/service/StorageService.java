package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();

    public StorageService() {
        fillTestProducts();
        fillTestArticles();
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    public Collection<Searchable> getAll() {
        return Stream.concat(getProducts().values().stream(), getArticles().values().stream()).collect(Collectors.toSet());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(this.products.get(id));
    }

    private void fillTestProducts() {
        Product testProduct;
        UUID id;

        id = UUID.randomUUID();
        testProduct = new SimpleProduct(id, "Product A", 2);
        products.put(id, testProduct);

        id = UUID.randomUUID();
        testProduct = new SimpleProduct(id, "Lamp", 10);
        products.put(id, testProduct);

        id = UUID.randomUUID();
        testProduct = new FixPriceProduct(id, "Special Product BBB");
        products.put(id, testProduct);

        id = UUID.randomUUID();
        testProduct = new DiscountedProduct(id, "Special Product AA", 100, 10);
        products.put(id, testProduct);
    }


    private void fillTestArticles() {
        Article testArticle;
        UUID id;

        id = UUID.randomUUID();
        testArticle = new Article(id, "Article about Product Tree", "So many symbols about trees.");
        articles.put(id, testArticle);

        id = UUID.randomUUID();
        testArticle = new Article(id, "Best Product", "So many symbols about the Best Product.");
        articles.put(id, testArticle);

        id = UUID.randomUUID();
        testArticle = new Article(id, "Article with same length title about a Product Banana", "some content");
        articles.put(id, testArticle);

        id = UUID.randomUUID();
        testArticle = new Article(id, "Article with same length title about a Product Apple_", "some content");
        articles.put(id, testArticle);
    }
}

package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage = new HashMap<>();
    private final Map<UUID, Article> articleStorage = new HashMap<>();

    public StorageService() {
        fillTestProducts();
        fillTestArticles();
    }

    public Map<UUID, Product> getProducts() {
        return productStorage;
    }

    public Map<UUID, Article> getArticles() {
        return articleStorage;
    }

    public Collection<Searchable> getAll() {
        return Stream.concat(getProducts().values().stream(), getArticles().values().stream()).collect(Collectors.toSet());
    }

    private void fillTestProducts() {
        Product testProduct;
        UUID id;

        id = UUID.randomUUID();
        testProduct = new SimpleProduct(id, "Product A", 2);
        productStorage.put(id, testProduct);

        id = UUID.randomUUID();
        testProduct = new SimpleProduct(id, "Lamp", 10);
        productStorage.put(id, testProduct);

        id = UUID.randomUUID();
        testProduct = new FixPriceProduct(id, "Special Product BBB");
        productStorage.put(id, testProduct);

        id = UUID.randomUUID();
        testProduct = new DiscountedProduct(id, "Special Product AA", 100, 10);
        productStorage.put(id, testProduct);
    }


    private void fillTestArticles() {
        Article testArticle;
        UUID id;

        id = UUID.randomUUID();
        testArticle = new Article(id, "Article about Product Tree", "So many symbols about trees.");
        articleStorage.put(id, testArticle);

        id = UUID.randomUUID();
        testArticle = new Article(id, "Best Product", "So many symbols about the Best Product.");
        articleStorage.put(id, testArticle);

        id = UUID.randomUUID();
        testArticle = new Article(id, "Article with same length title about a Product Banana", "some content");
        articleStorage.put(id, testArticle);

        id = UUID.randomUUID();
        testArticle = new Article(id, "Article with same length title about a Product Apple_", "some content");
        articleStorage.put(id, testArticle);
    }
}

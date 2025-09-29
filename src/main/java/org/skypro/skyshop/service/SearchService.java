package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<Searchable> search(String pattern) {
        return storageService.getAll().stream().filter(o -> o.getSearchTerm().contains(pattern)).collect(Collectors.toSet());
    }
}

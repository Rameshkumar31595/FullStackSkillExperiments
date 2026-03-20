package com.klu.controller;

import com.klu.model.Product;
import com.klu.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/category/{category}")
    public List<Product> byCategory(@PathVariable String category) {
        return repo.findByCategory(category);
    }

    @GetMapping("/category/{category}/price/{price}")
    public List<Product> byCategoryAndPrice(@PathVariable String category,
                                             @PathVariable double price) {
        return repo.findByCategoryAndPriceGreaterThan(category, price);
    }

    @GetMapping("/or")
    public List<Product> byCategoryOrName(@RequestParam String category,
                                           @RequestParam String name) {
        return repo.findByCategoryOrName(category, name);
    }

    @GetMapping("/filter")
    public List<Product> between(@RequestParam double min,
                                  @RequestParam double max) {
        return repo.findByPriceBetween(min, max);
    }

    @GetMapping("/search")
    public List<Product> like(@RequestParam String keyword) {
        return repo.findByNameLike("%" + keyword + "%");
    }

    @GetMapping("/expensive/{price}")
    public List<Product> greaterThan(@PathVariable double price) {
        return repo.findByPriceGreaterThan(price);
    }

    @GetMapping("/count/{category}")
    public long count(@PathVariable String category) {
        return repo.countByCategory(category);
    }

    @GetMapping("/exists/{name}")
    public boolean exists(@PathVariable String name) {
        return repo.existsByName(name);
    }

    @DeleteMapping("/delete/{name}")
    public String delete(@PathVariable String name) {
        repo.deleteByName(name);
        return "Product deleted successfully";
    }

    @GetMapping("/sorted")
    public List<Product> sorted() {
        return repo.sortByPrice();
    }
}
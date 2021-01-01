package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.NotFoundException;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.util.List;

@RequestMapping("/api/v1/product")
@RestController
public class ProductResource {

    private final ProductRepository repository;

    @Autowired
    public ProductResource(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/all")
    public List<Product> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "{id}/id")
    public Product findById(@PathVariable("id") long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        repository.save(product);
        return product;
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        repository.save(product);
        return product;
    }

    @DeleteMapping(path = "{id}/id")
    public void deleteProduct(@PathVariable("id") long id) {
        repository.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException ex) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }
}

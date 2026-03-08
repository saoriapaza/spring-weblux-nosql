package ap1._1.saori.apaza.rest;

import ap1._1.saori.apaza.model.Product;
import ap1._1.saori.apaza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRest {

    private final ProductService service;

    @GetMapping
    public Flux<Product> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<Product> create(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/{id}")
    public Mono<Product> update(@PathVariable String id,
                                @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

}
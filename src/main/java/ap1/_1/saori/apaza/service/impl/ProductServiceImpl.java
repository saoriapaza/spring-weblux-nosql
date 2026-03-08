package ap1._1.saori.apaza.service.impl;

import ap1._1.saori.apaza.model.Product;
import ap1._1.saori.apaza.repository.ProductRepository;
import ap1._1.saori.apaza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Flux<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        return repository.save(product);
    }

    @Override
    public Mono<Product> update(String id, Product product) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setName(product.getName());
                    existing.setDescription(product.getDescription());
                    existing.setCategory(product.getCategory());
                    existing.setPrice(product.getPrice());
                    existing.setStock(product.getStock());
                    existing.setBrand(product.getBrand());
                    existing.setAvailable(product.getAvailable());
                    existing.setCreatedAt(product.getCreatedAt());

                    return repository.save(existing);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
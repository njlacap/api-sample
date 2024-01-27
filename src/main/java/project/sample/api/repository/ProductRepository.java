package project.sample.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.sample.api.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

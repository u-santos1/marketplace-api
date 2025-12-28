package com.uerles.marketplace_api.Repository;

import com.uerles.marketplace_api.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

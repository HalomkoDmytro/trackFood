package org.hdo.trackFood.repository;

import org.hdo.trackFood.model.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Integer> {
}

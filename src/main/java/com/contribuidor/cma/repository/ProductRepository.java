package com.contribuidor.cma.repository;

import com.contribuidor.cma.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

     List<Product> findAllByActive(Boolean active);

}

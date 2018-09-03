package com.contribuidor.cma.repository;

import com.contribuidor.cma.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    List<Category> findAllByActive(Boolean active);

    Category findOneByName(String name);

    Category findOneByIdAndActive(Long id, Boolean active);

    Category findOneByNameAndActive(String name, Boolean active);

}

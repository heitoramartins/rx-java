package com.contribuidor.cma.repository.budget;

import com.contribuidor.cma.entities.Budget;
import com.contribuidor.cma.entities.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends PagingAndSortingRepository<Budget, Long> {

}

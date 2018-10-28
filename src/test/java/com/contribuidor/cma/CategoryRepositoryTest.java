package com.contribuidor.cma;

import com.contribuidor.cma.entities.Category;
import com.contribuidor.cma.repository.category.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		// given
		Category category = new Category();
		category.setActive(true);
		category.setName("eletronico");

		entityManager.persist(category);
		entityManager.flush();

		// when
		Category found = categoryRepository.findOneByName(category.getName());

		// then
		assertThat(found.getName()).isEqualTo(category.getName());
	}

}

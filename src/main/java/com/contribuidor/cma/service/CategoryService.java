package com.contribuidor.cma.service;

import static rx.Observable.defer;
import static rx.Observable.empty;
import static rx.Observable.just;

import java.util.List;

import com.contribuidor.cma.entities.Category;
import com.contribuidor.cma.repository.CategoryRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rx.Observable;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public Observable<List<Category>> findAllActives() {
		return defer(() -> {
			return just(categoryRepository.findAllByActive(true));
		});
	}

	public Observable<Category> findOne(Long id) {
		return defer(() -> {
			Category category = categoryRepository.findById(id).orElse(null);
			if (category == null)
				return empty();

			return just(category);
		});
	}

	public Observable<Category> findOneActive(Long id) {
		return defer(() -> {
			Category category = categoryRepository.findOneByIdAndActive(id, true);
			if (category == null)
				return empty();

			return just(category);
		});
	}

	public Observable<Category> findOneActive(String name) {
		return defer(() -> {
			Category category = categoryRepository.findOneByNameAndActive(name, true);
			if (category == null)
				return empty();

			return just(category);
		});
	}

	public Observable<Category> findOne(String name) {
		return defer(() -> {
			Category category = categoryRepository.findOneByName(name);
			if (category == null)
				return empty();

			return just(category);
		});
	}

	public Observable<Category> create(Category category) {
		return defer(() -> {
			return just(categoryRepository.save(category));
		});
	}

	public Observable<Category> findOrCreate(Long id, String name) {
		Observable<Category> findCategoryById = just(Pair.of(id, name)).flatMap(pair -> {
			if (pair.getLeft() == null)
				return empty();

			return findOneActive(pair.getLeft());
		});
		
		if (name == null)
			return findCategoryById;
		
		return findCategoryById
			.switchIfEmpty(findOneActive(name))
			.switchIfEmpty(create(new Category(name)));
	}

	public Observable<Category> delete(Long id) {
		return Observable.defer(() -> {
			Category category = categoryRepository.findById(id).orElse(null);
			category.inactive();
			return Observable.just(categoryRepository.save(category));
		});
	}

}

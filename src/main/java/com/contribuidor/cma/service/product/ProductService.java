package com.contribuidor.cma.service.product;

import com.contribuidor.cma.contract.ProductRegistration;
import com.contribuidor.cma.entities.Product;
import com.contribuidor.cma.exception.entityerror.CategoryNotFound;
import com.contribuidor.cma.repository.product.ProductRepository;
import com.contribuidor.cma.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

import static rx.Observable.*;

@Service
public class ProductService {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductRepository productRepository;

	public Observable<List<Product>> findAllActives() {
		return defer(() -> {
			return just(productRepository.findAllByActive(true));
		});
	}

	public Observable<Product> findOne(Long id) {
		return defer(() -> {
			Product product = productRepository.findById(id).orElse(null);
			if (product == null)
				return empty();

			return just(productRepository.findById(id).orElse(null));
		});
	}

	public Observable<Product> create(ProductRegistration productRegistration) {
			return just(productRegistration).flatMap(registration -> {
					ProductRegistration.CategoryRegistration category = registration.getCategory();
					return categoryService.findOrCreate(category.getId(),category.getName())
							.map(cat -> {
								return Pair.of(cat,registration);
					});
		   }).switchIfEmpty(Observable.error(new CategoryNotFound())).flatMap(pair -> {
			ProductRegistration registration = pair.getSecond();
			Product product = new Product(registration.getName(),
					registration.getDescription(),
					registration.getPrice(), pair.getFirst());
			return create(product);
		});
	}

	private Observable<Product> create(Product product) {
		return defer(() -> {
			return just(productRepository.save(product));
		});
	}

}

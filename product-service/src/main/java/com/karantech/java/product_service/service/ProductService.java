package com.karantech.java.product_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.karantech.java.product_service.dto.ProductDto;
import com.karantech.java.product_service.entity.Product;
import com.karantech.java.product_service.entity.UserInfo;
import com.karantech.java.product_service.repository.ProductRepository;
import com.karantech.java.product_service.repository.UserRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository pRepository;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<ProductDto> getAllProducts() {
		List<Product> products = pRepository.findAll();
		List<ProductDto> prods = products.stream()
				.map((prod) -> new ProductDto(prod.getpName(), prod.getpDesc(), prod.getpExpiry()))
				.collect(Collectors.toList());
		return prods;

	}

	public ProductDto getProduct(int id) {
		Optional<Product> byId = pRepository.findById(id);
		Optional<ProductDto> map = byId
				.map(prod -> new ProductDto(prod.getpName(), prod.getpDesc(), prod.getpExpiry()));
		return map.get();
	}

	public ProductDto saveProduct(ProductDto prod) {
		Optional<ProductDto> ofNullable = Optional.ofNullable(prod);
		Optional<Product> map = ofNullable.map(pro -> new Product(pro.getpName(), pro.getpDesc(), pro.getpExpiry()));
		Product save = pRepository.save(map.get());
		Optional<Product> ofNullable2 = Optional.ofNullable(save);
		Optional<ProductDto> map2 = ofNullable2.map(p -> new ProductDto(p.getpName(), p.getpDesc(), p.getpExpiry()));
		return map2.get();
	}

	public String addUser(UserInfo user) {
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		UserInfo save = userRepo.save(user);
		return "user saved";
	}

}

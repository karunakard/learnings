package com.karantech.java.product_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karantech.java.product_service.dto.ProductDto;
import com.karantech.java.product_service.entity.UserInfo;
import com.karantech.java.product_service.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService pService;

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome all";
	}

	@PostMapping("/new")
	public ResponseEntity<String> saveProduct(@RequestBody ProductDto prod) {
		ProductDto saveProduct = pService.saveProduct(prod);
		if (saveProduct != null) {
			return new ResponseEntity<String>("valued saved", HttpStatus.OK);
		}
		return new ResponseEntity<String>("valued not saved", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/all")
	//@PreAuthorize("hasAuthority(ROLE_ADMIN)")
	public ResponseEntity<List<ProductDto>> getAllProducts() {

		Optional<List<ProductDto>> ofNullable = Optional.ofNullable(pService.getAllProducts());
		if (ofNullable.isPresent() && !ofNullable.get().isEmpty()) {
			return new ResponseEntity<List<ProductDto>>(ofNullable.get(), HttpStatus.OK);
		}

		return new ResponseEntity<List<ProductDto>>(ofNullable.get(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping("/byid/{id}")
	//@PreAuthorize("hasAuthority(ROLE_USER)")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
		ProductDto product = pService.getProduct(id);
		if (product != null) {
			return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
		}
		return new ResponseEntity<ProductDto>(product, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody UserInfo user) {
		return pService.addUser(user);
	}

}

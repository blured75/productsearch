package com.blured.ecomerce.product.service;

import com.blured.ecomerce.product.dao.ProductRepository;
import com.blured.ecomerce.product.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductService {

    @Autowired
    ProductRepository productRepo;

    @RequestMapping("/${env}product/{id}")
    Product getProduct(@PathVariable("id") int id) {
        return productRepo.findById(id).get();
    }

    @RequestMapping("/${env}products") // For a categoryId
    List<Product> getProductsForCategory(@RequestParam("id") int id) {
        return productRepo.findByCatId(id);
    }

    @PutMapping("/${env}product/{id}")
    public void insertUpdateProduct(@RequestBody Product product) {
        productRepo.save(product);
    }

    @DeleteMapping("/${env}product/{id}")
    public void deleteProduct(@RequestBody Product product) {
        productRepo.delete(product);
    }
}

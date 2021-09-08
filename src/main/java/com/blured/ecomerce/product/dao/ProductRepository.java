package com.blured.ecomerce.product.dao;

import com.blured.ecomerce.product.domain.entity.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, Integer> {

    @Cacheable("productsByCategoryCache")
    List<Product> findByCatId(int catId);
}

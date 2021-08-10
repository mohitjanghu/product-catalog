package com.ominrio.category.service;

import com.ominrio.category.domain.Category;
import com.ominrio.category.domain.Product;
import com.ominrio.category.models.response.ProductResponse;

import java.util.UUID;

public interface ProductService {
    Product create(Product product) throws Exception;

    ProductResponse getById(String id) throws Exception;

    ProductResponse addCategory(Category category, String id) throws Exception;

}

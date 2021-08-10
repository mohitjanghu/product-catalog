package com.ominrio.category.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ominrio.category.domain.Category;
import com.ominrio.category.domain.Product;
import com.ominrio.category.models.response.ProductResponse;
import com.ominrio.category.repository.CategoryRepository;
import com.ominrio.category.repository.ProductRepository;
import com.ominrio.category.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Gson gson;


    @Override
    public Product create(Product product) throws Exception {
        if (product != null) {

            if (product.getProductId() != null)
                throw new Exception("Product id is auto generated and can not be specified explicitly");

            if (product.getProductName() == null)
                throw new Exception("Product name can not be null");


            return repository.save(product);
        } else
            throw new Exception("Product is required!");

    }

    @Override
    public ProductResponse getById(String id) throws Exception {
        if (id != null) {

            Optional<Product> product = repository.findById(id);

            if (product.isPresent()) {
                Product existingProduct = product.get();
                ProductResponse response = new ProductResponse();
                response.setProductID(existingProduct.getProductId());
                response.setProductName(existingProduct.getProductName());
                if (existingProduct.getCategory() != null) {
                    response.setCategoryId(existingProduct.getCategory().getCategoryId());
                    response.setCategryName(existingProduct.getCategory().getCategoryName());
                    response.setProductAttributes(existingProduct.getCategory().getProductAttributes());
                }
                return response;
            } else
                throw new Exception("Product with id " + id + " not found");
        } else
            throw new Exception("Product id is required!");
    }

    @Override
    public ProductResponse addCategory(Category category, String id) throws Exception {
        if (category != null && category.getCategoryId() != null) {
            Optional<Product> product = repository.findById(id);

            if (product.isPresent()) {
                product.get().setCategory(category);
                Product updatedProduct = repository.save(product.get());
                ProductResponse response = new ProductResponse();
                response.setProductID(updatedProduct.getProductId());
                response.setProductName(updatedProduct.getProductName());
                if (updatedProduct.getCategory() != null) {

                    Optional<Category> existingCategory = categoryRepository.findById(updatedProduct.getCategory().getCategoryId());
                    if (existingCategory.isPresent()) {
                        response.setCategoryId(existingCategory.get().getCategoryId());
                        response.setCategryName(existingCategory.get().getCategoryName());
                        response.setProductAttributes(existingCategory.get().getProductAttributes());
                    }
                }
                return response;
            } else
                throw new Exception("Product with id " + id + " not found");

        } else
            throw new Exception("Category required!");


    }
}

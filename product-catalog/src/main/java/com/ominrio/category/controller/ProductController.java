package com.ominrio.category.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ominrio.category.domain.Attribute;
import com.ominrio.category.domain.Category;
import com.ominrio.category.domain.Product;
import com.ominrio.category.service.CategoryService;
import com.ominrio.category.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {


    /**
     * Logger for the class
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Gson gson;


    @RequestMapping( name = "/",method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@RequestBody String request) throws JsonProcessingException {

        try {
            LOGGER.info("Product Create Request Body ::::: " + request);
            Product product = gson.fromJson(request, Product.class);

            return ResponseEntity.ok(mapper.writeValueAsString(productService.create(product)));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> get(@PathVariable(name = "id", required = true) String  id) throws JsonProcessingException {
        try {
            LOGGER.info("Product get request with  ::::: " + id);

            return ResponseEntity.ok(mapper.writeValueAsString(productService.getById(id)));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/category",method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> addAttributes(@PathVariable(name = "id",required = true)String id
            , @RequestBody String request) throws Exception {
        try {
            LOGGER.info("Product add category Request Body ::::: "+request);

            Category category = gson.fromJson(request, Category.class);

            return ResponseEntity.ok(mapper.writeValueAsString(productService.addCategory(category,id)));
        }
        catch (Exception ex){
            return  ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}

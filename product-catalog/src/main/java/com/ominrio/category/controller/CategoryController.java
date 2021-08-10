package com.ominrio.category.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ominrio.category.domain.Attribute;
import com.ominrio.category.domain.Category;
import com.ominrio.category.service.AttributeService;
import com.ominrio.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    /**
     * Logger for the class
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    private CategoryService categoryService;


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Gson gson;


    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> create(@RequestBody String request) throws Exception {
        try {
        LOGGER.info("Category Create Request Body ::::: "+request);

            Category category = gson.fromJson(request, Category.class);

            return ResponseEntity.ok(mapper.writeValueAsString(categoryService.createCategory(category)));
        }
        catch (Exception ex){
            return  ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> get(@PathVariable(name = "id", required = true) int id) throws Exception {
        try{
            LOGGER.info("Category get request with  ::::: "+id);

            return ResponseEntity.ok(mapper.writeValueAsString(categoryService.getById(id)));
        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }


    @RequestMapping(value = "/{id}/attribute",method = RequestMethod.PUT)
    public ResponseEntity<?> addAttributes(@PathVariable(name = "id",required = true) int id
            , @RequestBody String request) throws Exception {
        try {
            LOGGER.info("Category Create Request Body ::::: "+request);

            List<Attribute> attributeList = gson.fromJson(request, ArrayList.class);

            return ResponseEntity.ok(mapper.writeValueAsString(categoryService.addAttributes(attributeList,id)));
        }
        catch (Exception ex){
            return  ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}

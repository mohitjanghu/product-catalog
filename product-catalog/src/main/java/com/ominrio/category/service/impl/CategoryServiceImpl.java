package com.ominrio.category.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ominrio.category.domain.Attribute;
import com.ominrio.category.domain.Category;
import com.ominrio.category.repository.AttributeRepository;
import com.ominrio.category.repository.CategoryRepository;
import com.ominrio.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Gson gson;



    @Override
    public Category createCategory(Category category) throws Exception {

        if(category!=null) {

            if (category.getCategoryId() != null)
                throw new Exception("Category id is auto generated and can not be specified explicitly");

            if (category.getCategoryName() == null)
                throw new Exception("Category name can not be null");

            return repository.save(category);
        }
        else
            throw new Exception("Category is required!");

    }

    @Override
    public Category getById(Integer id) throws Exception {
        if(id!=null) {
            Optional<Category> category = repository.findById(id);

            if(category.isPresent())
            {
                return  category.get();
            }
            else
                 throw new Exception("Category with id "+id+" not found");
        }
        else
            throw new Exception("Category id is required!");
    }

    @Override
    public Category addAttributes(List<Attribute> attributeList,Integer id) throws Exception {

        if(attributeList!=null && !attributeList.isEmpty()) {
            Optional<Category> category = repository.findById(id);

            if(category.isPresent())
            {

                category.get().setProductAttributes(attributeList);
                return  repository.save(category.get());
            }
            else
                throw new Exception("Category with id "+id+" not found");

        }
        else
            throw new Exception("Attributes required!");
    }
}

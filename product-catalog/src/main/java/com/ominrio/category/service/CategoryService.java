package com.ominrio.category.service;

import com.ominrio.category.domain.Attribute;
import com.ominrio.category.domain.Category;

import java.util.List;

public interface CategoryService {


    Category createCategory(Category category) throws Exception;

    Category getById(Integer id) throws Exception;

    Category addAttributes(List<Attribute> attributeList,Integer id) throws Exception;

}

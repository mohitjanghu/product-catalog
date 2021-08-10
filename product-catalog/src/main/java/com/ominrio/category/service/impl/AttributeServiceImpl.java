package com.ominrio.category.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ominrio.category.domain.Attribute;
import com.ominrio.category.repository.AttributeRepository;
import com.ominrio.category.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttributeServiceImpl implements AttributeService {


    @Autowired
    private AttributeRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Gson gson;

    @Override
    public Attribute createAttribute(Attribute attribute) throws Exception {

        if(attribute.getId()!=null)
            throw new Exception("Attribute id is auto generated and can not be specified explicitly");

        if(attribute.getAttributeName() ==  null)
            throw new Exception("Attribute name can not be null");

        if(attribute.getAttributeValue() ==  null)
            throw new Exception("Attribute value can not be null");

        attribute = repository.save(attribute);

        return attribute;
    }
}

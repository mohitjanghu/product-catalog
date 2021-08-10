package com.ominrio.category.service;

import com.ominrio.category.domain.Attribute;
import org.springframework.stereotype.Service;


public interface AttributeService {


    Attribute createAttribute(Attribute attribute) throws Exception;

}

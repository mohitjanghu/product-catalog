package com.ominrio.category.repository;

import com.ominrio.category.domain.Attribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends CrudRepository<Attribute,Integer> {
}

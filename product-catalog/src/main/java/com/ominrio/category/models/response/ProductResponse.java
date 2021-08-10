package com.ominrio.category.models.response;


import com.ominrio.category.domain.Attribute;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class ProductResponse {

    private String productID;
    private String productName;
    private Integer categoryId;
    private String categryName;
    private List<Attribute> productAttributes;

    public ProductResponse() {
    }

    public ProductResponse(String productID, String productName, Integer categoryId, String categryName, List<Attribute> productAttributes) {
        this.productID = productID;
        this.productName = productName;
        this.categoryId = categoryId;
        this.categryName = categryName;
        this.productAttributes = productAttributes;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategryName() {
        return categryName;
    }

    public void setCategryName(String categryName) {
        this.categryName = categryName;
    }

    public List<Attribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<Attribute> productAttributes) {
        this.productAttributes = productAttributes;
    }
}

package com.ominrio.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Indexed
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer categoryId;
    @NotNull
    private String  categoryName;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Attribute> productAttributes;

    @JsonIgnore
    @OneToOne(mappedBy = "category")
    private Product product;

    public Category() {

    }

    public Category(Integer categoryId, @NotNull String categoryName, List<Attribute> productAttributes, Product product) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productAttributes = productAttributes;
        this.product = product;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Attribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<Attribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

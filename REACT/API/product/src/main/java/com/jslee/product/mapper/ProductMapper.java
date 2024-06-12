package com.jslee.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jslee.product.dto.Product;

@Mapper
public interface ProductMapper {
    public List<Product> list();
    public Product select(int no);
    public int update(Product product);
    public int insert(Product product);
    public int delete(int no);
}

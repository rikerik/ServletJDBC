package com.example.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.DTO.ProductCreateDTO;
import com.example.Model.Product;

@Mapper
public interface ProductCreateMapper {
    ProductCreateMapper INSTANCE = Mappers.getMapper(ProductCreateMapper.class);

    @Mapping(target = "productId", ignore = true)
    Product ProductCreateDTOToProduct(ProductCreateDTO productCreateDTO);
}

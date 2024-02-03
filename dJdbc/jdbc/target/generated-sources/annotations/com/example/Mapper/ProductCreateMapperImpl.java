package com.example.Mapper;

import com.example.DTO.ProductCreateDTO;
import com.example.Model.Product;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T15:45:03+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
*/
public class ProductCreateMapperImpl implements ProductCreateMapper {

    @Override
    public Product ProductCreateDTOToProduct(ProductCreateDTO productCreateDTO) {
        if ( productCreateDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductName( productCreateDTO.getProductName() );
        product.setSupplier( productCreateDTO.getSupplier() );
        product.setPrice( productCreateDTO.getPrice() );
        product.setQuantity( productCreateDTO.getQuantity() );
        product.setCreatedByUserId( productCreateDTO.getCreatedByUserId() );
        product.setCreatedOn( productCreateDTO.getCreatedOn() );
        product.setModifiedByUserId( productCreateDTO.getModifiedByUserId() );
        product.setModifiedOn( productCreateDTO.getModifiedOn() );

        return product;
    }
}

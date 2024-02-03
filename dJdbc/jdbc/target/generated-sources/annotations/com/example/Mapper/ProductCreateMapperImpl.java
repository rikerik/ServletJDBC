package com.example.Mapper;

import com.example.DTO.ProductCreateDTO;
import com.example.Model.Product;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-03T14:02:03+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
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

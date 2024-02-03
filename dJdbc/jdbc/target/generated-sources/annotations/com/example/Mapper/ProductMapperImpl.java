package com.example.Mapper;

import com.example.DTO.ProductDTO;
import com.example.Model.Product;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T15:45:03+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
*/
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId( product.getProductId() );
        productDTO.setProductName( product.getProductName() );
        productDTO.setSupplier( product.getSupplier() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setQuantity( product.getQuantity() );
        productDTO.setCreatedByUserId( product.getCreatedByUserId() );
        productDTO.setCreatedOn( product.getCreatedOn() );
        productDTO.setModifiedByUserId( product.getModifiedByUserId() );
        productDTO.setModifiedOn( product.getModifiedOn() );

        return productDTO;
    }
}

package com.example.Mapper;

import com.example.DTO.ProductDTO;
import com.example.Model.Product;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-03T14:02:03+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
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

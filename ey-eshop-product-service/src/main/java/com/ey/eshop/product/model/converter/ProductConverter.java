package com.ey.eshop.product.model.converter;

import com.ey.eshop.product.model.dto.ProductDto;
import com.ey.eshop.product.model.entity.ProductDo;
import com.ey.eshop.product.model.vo.ProductListVo;
import com.ey.eshop.product.model.vo.ProductVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductConverter {

    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    ProductDto productDoToDto(ProductDo productDo);

    List<ProductDto> productDosToProductDtos(List<ProductDo> productDo);

    ProductVo productDtoToVo(ProductDto productDto);

    List<ProductListVo.Response> productWithUserDtosToVos(List<ProductDto> productDto);
}

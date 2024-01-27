package project.sample.api.service;

import project.sample.api.dto.ProductDto;
import project.sample.api.exception.ProductException;

import java.util.List;

public interface ProductService {
    ProductDto getProductDtoById(Long id) throws ProductException;
    List<ProductDto> getAllProduct() throws ProductException;
}

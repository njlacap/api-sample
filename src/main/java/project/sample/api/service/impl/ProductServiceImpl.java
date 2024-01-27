package project.sample.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sample.api.domain.Product;
import project.sample.api.dto.ProductDto;
import project.sample.api.exception.ProductException;
import project.sample.api.repository.ProductRepository;
import project.sample.api.service.ProductService;
import project.sample.api.util.UtilDto;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final UtilDto utilDto = UtilDto.getInstance();

    @Autowired
    public ProductServiceImpl(ProductRepository productRepositoryInject) {
        this.productRepository = productRepositoryInject;
    }

    public Product findProductById(Long id) throws ProductException {
        log.info("ProductService - findProductById - finding product by id or else throw product exception");
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            log.info("ProductService - findProductById - No product found");
            throw new ProductException("Product not found!");
        }
        log.info("ProductService - findProductById - done finding product");
        return productRepository.findById(id).orElseThrow(() -> new ProductException("Product not found!"));
    }

    public List<Product> findAllProduct() throws ProductException {
        log.info("ProductService - findAllProduct - finding all or else throw product exception");
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()) {
            log.info("ProductService - findAllProduct - No products found");
            throw new ProductException("No products available!");
        }
        log.info("ProductService - findAllProduct - done finding products");
        return productList;
    }

    public ProductDto getProductDtoById(Long id) throws ProductException {
        log.info("ProductService - getProductDtoById - getting product by id ");
        ProductDto productDto = utilDto.convertToDto(this.findProductById(id), ProductDto.class);
        log.info("ProductService - getProductDtoById - done getting product ");
        return productDto;
    }

    public List<ProductDto> getAllProduct() throws ProductException {
        log.info("ProductService - getAllProduct - getting all products");
        List<ProductDto> productDtoList = utilDto.convertToDtoList(this.findAllProduct());
        log.info("ProductService - getAllProduct - done getting products");
        return productDtoList;
    }

}

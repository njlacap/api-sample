package project.sample.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import project.sample.api.domain.Product;
import project.sample.api.dto.ProductDto;
import project.sample.api.exception.ProductException;
import project.sample.api.repository.ProductRepository;
import project.sample.api.service.impl.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void givenProductList_whenGetAllProducts_thenReturnProductDtoList() throws ProductException {

        List<Product> list = new ArrayList<>();
        list.add(new Product("hotdog"));
        list.add(new Product("cheesedog"));
        list.add(new Product("chickendog"));

        when(productRepository.findAll()).thenReturn(list);

        List<ProductDto> productList = productService.getAllProduct();

        // Should match values
        assertThat(productList.get(0).getProductName()).isEqualTo(list.get(0).getProductName());
        assertThat(productList.get(1).getProductName()).isEqualTo(list.get(1).getProductName());
        assertThat(productList.get(2).getProductName()).isEqualTo(list.get(2).getProductName());

        // Should NOT match values
        assertThat(productList.get(0).getProductName()).isNotEqualTo(list.get(1).getProductName());
        assertThat(productList.get(0).getProductName()).isNotEqualTo(list.get(2).getProductName());

        // Verify repository - find all is only called once
        verify(productRepository, times(1)).findAll();

        // Verify repository - find id is not called
        verify(productRepository, times(0)).findById(any());

    }

}

package project.sample.api.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.sample.api.domain.Product;
import project.sample.api.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UtilDto {

    private final ModelMapper modelMapper;
    private static UtilDto utilDto = null;

    public UtilDto(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static UtilDto getInstance() {
        if(utilDto == null) {
            utilDto = new UtilDto(new ModelMapper());
        }
        return utilDto;
    }

    public ProductDto convertToDto(Product fClass, Class<ProductDto> tClass) {
        return modelMapper.map(fClass, tClass);
    }

    public List<ProductDto> convertToDtoList(List<Product> fList) {
        return fList
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }
}

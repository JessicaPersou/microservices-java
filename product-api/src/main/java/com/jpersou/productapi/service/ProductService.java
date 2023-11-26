package com.jpersou.productapi.service;

import com.jpersou.productapi.converter.DTOconverter;
import com.jpersou.shoppingclient.exception.CategoryNotFoundException;
import com.jpersou.shoppingclient.exception.ProductNotFoundException;
import com.jpersou.productapi.model.Product;
import com.jpersou.productapi.repository.CategoryRepository;
import com.jpersou.productapi.repository.ProductRepository;
import com.jpersou.shoppingclient.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId){
        List<Product> productList = productRepository.getProductsByCategory(categoryId);
        return productList
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null){
            return DTOconverter.convert(product);
        }
        throw new ProductNotFoundException();
    }

    public ProductDTO save(ProductDTO productDTO){
        Boolean existsCategory = categoryRepository.existsById(productDTO.getCategoryDTO().getId());
        if(!existsCategory){
            throw new CategoryNotFoundException();
        }
        Product product = productRepository.save(Product.convert(productDTO));
        return DTOconverter.convert(product);
    }

    public void delete(long productId) throws ProductNotFoundException{
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            productRepository.delete(product.get());
        }
        throw new ProductNotFoundException();
    }

    public ProductDTO editProduct(long id, ProductDTO productDTO){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));

        if(productDTO.getName() != null || !productDTO.getName().isEmpty()){
            product.setName(productDTO.getName());
        }

        if(productDTO.getPrice() != null){
            product.setPrice(productDTO.getPrice());
        }
        return DTOconverter.convert(productRepository.save(product));
    }

    public Page<ProductDTO> getAllPage(Pageable page){
        Page<Product> products = productRepository.findAll(page);
        return products
                .map(DTOconverter::convert);
    }

}

package com.dawar.vipul.service;

import com.dawar.vipul.domain.Product;
import com.dawar.vipul.domain.ShoppingCart;
import com.dawar.vipul.dto.ShoppingCartDTO;
import com.dawar.vipul.repository.ProductRepository;
import com.dawar.vipul.repository.ShoppingCartRepository;
import com.dawar.vipul.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart saveProducts(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = productRepository.findOne(shoppingCartDTO.getProductId());
        shoppingCart.setProduct(product);
        shoppingCart.setUser(userRepository.findOne(1L));
        shoppingCart.setStatus(shoppingCartDTO.getStatus());
        shoppingCart.setDate(new Date());
        shoppingCart.setStock(shoppingCartDTO.getStock());
        shoppingCart.setAmount(product.getUnitPrice() * shoppingCartDTO.getStock());

        return shoppingCartRepository.save(shoppingCart);
    }


    public List<ShoppingCart> findAll() {
        return shoppingCartRepository.findByStatus("NOT_PURCHASED");
    }

    public ShoppingCart updateProduct(ShoppingCartDTO shoppingCartDTO, Long id) {
        ShoppingCart updateItem = shoppingCartRepository.findOne(id);
        updateItem.setStock(shoppingCartDTO.getStock());
        updateItem.setAmount(updateItem.getProduct().getUnitPrice() * shoppingCartDTO.getStock());
        return shoppingCartRepository.save(updateItem);
    }

    public void deleteProduct(Long id) {
        shoppingCartRepository.delete(id);
    }

    public void clearShoppingCart(Object object) {
        shoppingCartRepository.delete(findAll());
    }


    public List<ShoppingCart> findByPurchased() {
        return shoppingCartRepository.findByStatus("PURCHASED");
    }


    public void purchaseProducts(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findOne(id);
        shoppingCart.setStatus("PURCHASED");
        shoppingCartRepository.save(shoppingCart);
    }
}

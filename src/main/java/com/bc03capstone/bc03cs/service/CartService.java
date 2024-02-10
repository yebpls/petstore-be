package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.CartDTO;
import com.bc03capstone.bc03cs.entity.Cart;
import com.bc03capstone.bc03cs.entity.User;
import com.bc03capstone.bc03cs.mapper.CartMapper;
import com.bc03capstone.bc03cs.repository.CartRepository;
import com.bc03capstone.bc03cs.repository.UserRepository;
import com.bc03capstone.bc03cs.service.imp.CartItemServiceImp;
import com.bc03capstone.bc03cs.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartItemServiceImp cartItemServiceImp;

    @Override
    public CartDTO findByUser(Integer userId) {
        User user = userRepository.findByIdAndStatus(userId,true);
        return cartMapper.convertToDTO(cartRepository.findByUserAndStatus(user,true));
    }

    @Override
    public CartDTO findById(Integer id) {
        return cartMapper.convertToDTO(cartRepository.findByIdAndStatus(id,true));
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void add(User user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setStatus(true);
        try {
            cartRepository.save(newCart);
        } catch (Exception e) {
            throw new RuntimeException("Error add cart " + e.getMessage());
        }
    }

    @Override
    public void hide(Integer id) {
        Cart cart = cartRepository.findByIdAndStatus(id,true);
        cart.getCartItemList().forEach(cartItem -> cartItemServiceImp.hide(cartItem.getId()));
        cart.setStatus(false);
        cartRepository.save(cart);
    }

    @Override
    public void show(Integer id) {
        Cart cart = cartRepository.findByIdAndStatus(id,false);
        cart.getCartItemList().forEach(cartItem -> cartItemServiceImp.show(cartItem.getId()));
        cart.setStatus(true);
        cartRepository.save(cart);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.getCartItemList().forEach(cartItem -> cartItemServiceImp.delete(cartItem.getId()));
        cartRepository.deleteById(id);
    }
}

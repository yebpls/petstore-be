package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.CartDTO;
import com.bc03capstone.bc03cs.entity.Cart;
import com.bc03capstone.bc03cs.entity.User;
import com.bc03capstone.bc03cs.mapper.CartMapper;
import com.bc03capstone.bc03cs.repository.CartRepository;
import com.bc03capstone.bc03cs.repository.UserRepository;
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
    public void add(CartDTO cartDTO) {
        Cart newCart = cartMapper.revertToEntity(cartDTO);
        try {
            cartRepository.save(newCart);
        } catch (Exception e) {
            throw new RuntimeException("Error add cart " + e.getMessage());
        }
    }

    @Override
    public void hide(Integer id) {
        Cart cart = cartRepository.findByIdAndStatus(id,true);
        cart.setStatus(false);
        cartRepository.save(cart);
    }

    @Override
    public void show(Integer id) {
        Cart cart = cartRepository.findByIdAndStatus(id,false);
        cart.setStatus(true);
        cartRepository.save(cart);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cartRepository.deleteById(id);
    }
}

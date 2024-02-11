package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.CartItemDTO;
import com.bc03capstone.bc03cs.entity.Cart;
import com.bc03capstone.bc03cs.entity.CartItem;
import com.bc03capstone.bc03cs.mapper.CartItemMapper;
import com.bc03capstone.bc03cs.repository.CartItemRepository;
import com.bc03capstone.bc03cs.repository.CartRepository;
import com.bc03capstone.bc03cs.service.imp.CartItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService implements CartItemServiceImp {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Cacheable("cartItemList")
    @Override
    public List<CartItemDTO> findAllByCart(Integer cartId) {
        Cart cart = cartRepository.findByIdAndStatus(cartId,true);
//        long time = 1000L;       //test cache
//        Thread.sleep(time);
        return cartItemRepository.findAllByCartAndStatus(cart,true)
                .stream().map(cartItemMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CartItemDTO findById(Integer id) {
        CartItem cartItem = cartItemRepository.findByIdAndStatus(id,true);
        return cartItemMapper.convertToDTO(cartItem);
    }

    @CacheEvict("cartItemList")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer add(CartItemDTO cartItemDTO) {
        CartItem newCartItem = cartItemMapper.revertToEntity(cartItemDTO);
        try {
            cartItemRepository.save(newCartItem);
            return newCartItem.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error add cartItem " + e.getMessage());
        }
    }

    @CacheEvict("cartItemList")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void update(CartItemDTO cartItemDTO) {
        CartItem cartItem = cartItemMapper.revertToEntity(cartItemDTO);
        try {
            cartItemRepository.save(cartItem);
        } catch (Exception e) {
            throw new RuntimeException("Error update cartItem " + e.getMessage());
        }
    }

    @CacheEvict("cartItemList")
    @Override
    public void hide(Integer id) {
        CartItem cartItem = cartItemRepository.findByIdAndStatus(id,true);
        cartItem.setStatus(false);
        cartItemRepository.save(cartItem);
    }

    @CacheEvict("cartItemList")
    @Override
    public void show(Integer id) {
        CartItem cartItem = cartItemRepository.findByIdAndStatus(id,false);
        cartItem.setStatus(true);
        cartItemRepository.save(cartItem);
    }

    @CacheEvict("cartItemList")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        cartItemRepository.deleteById(id);
    }
}

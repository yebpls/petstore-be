package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.OrderItemDTO;
import com.bc03capstone.bc03cs.DTO.OrdersDTO;
import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import com.bc03capstone.bc03cs.entity.OrderItem;
import com.bc03capstone.bc03cs.entity.Orders;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.mapper.OrderItemMapper;
import com.bc03capstone.bc03cs.repository.OrderItemRepository;
import com.bc03capstone.bc03cs.repository.OrdersRepository;
import com.bc03capstone.bc03cs.service.imp.OrderItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService implements OrderItemServiceImp {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemDTO> findAllByOrders(Integer orderId) {
        Orders orders = ordersRepository.findByIdAndStatus(orderId,true);
        return orderItemRepository.findAllByOrdersAndStatus(orders,true)
                .stream().map(orderItemMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderItemDTO findById(Integer id) {
        OrderItem orderItem = orderItemRepository.findByIdAndStatus(id,true);
        return orderItemMapper.convertToDTO(orderItem);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void add(OrderItemDTO orderItemDTO) {
        OrderItem newOrderItem = orderItemMapper.revertToEntity(orderItemDTO);
        try {
            orderItemRepository.save(newOrderItem);
        } catch (Exception e) {
            throw new RuntimeException("Error add orderItem " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void update(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemMapper.revertToEntity(orderItemDTO);
        try {
            orderItemRepository.save(orderItem);
        } catch (Exception e) {
            throw new RuntimeException("Error update orderItem " + e.getMessage());
        }
    }

    @Override
    public void hide(Integer id) {
        OrderItem orderItem = orderItemRepository.findByIdAndStatus(id,true);
        orderItem.setStatus(false);
        orderItemRepository.save(orderItem);
    }

    @Override
    public void show(Integer id) {
        OrderItem orderItem = orderItemRepository.findByIdAndStatus(id,false);
        orderItem.setStatus(true);
        orderItemRepository.save(orderItem);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow();
        orderItemRepository.deleteById(id);
    }
}

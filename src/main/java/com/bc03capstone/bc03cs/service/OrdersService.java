package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.OrdersDTO;
import com.bc03capstone.bc03cs.entity.Orders;
import com.bc03capstone.bc03cs.entity.User;
import com.bc03capstone.bc03cs.mapper.OrdersMapper;
import com.bc03capstone.bc03cs.repository.OrdersRepository;
import com.bc03capstone.bc03cs.repository.UserRepository;
import com.bc03capstone.bc03cs.service.imp.OrderItemServiceImp;
import com.bc03capstone.bc03cs.service.imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService implements OrdersServiceImp {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemServiceImp orderItemServiceImp;

    @Override
    public List<OrdersDTO> findAllByUserAndIsCompleted(Integer userId, Boolean isCompleted) {
        User user = userRepository.findByIdAndStatus(userId,true);
        return ordersRepository.findAllByUserAndIsCompletedAndStatus(user, isCompleted, true)
                .stream().map(ordersMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrdersDTO findById(Integer id) {
        Orders orders = ordersRepository.findByIdAndStatus(id,true);
        return ordersMapper.convertToDTO(orders);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer add(String jsonString) {
        Orders newOrders = ordersMapper.revertToEntity(jsonString);
        newOrders.setOrderTime(LocalDateTime.now());
        try {
            ordersRepository.save(newOrders);
            return newOrders.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error add Orders " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer update(String jsonString) {
        Orders orders = ordersMapper.revertToEntity(jsonString);
        try {
            ordersRepository.save(orders);
            return orders.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error update Orders " + e.getMessage());
        }
    }

    @Override
    public void hide(Integer id) {
        Orders orders = ordersRepository.findByIdAndStatus(id,true);
        orders.getOrderItemList().forEach(orderItem -> orderItemServiceImp.hide(orderItem.getId()));
        orders.setStatus(false);
        ordersRepository.save(orders);
    }

    @Override
    public void show(Integer id) {
        Orders orders = ordersRepository.findByIdAndStatus(id,false);
        orders.getOrderItemList().forEach(orderItem -> orderItemServiceImp.show(orderItem.getId()));
        orders.setStatus(true);
        ordersRepository.save(orders);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        Orders orders = ordersRepository.findById(id).orElseThrow();
        orders.getOrderItemList().forEach(orderItem -> orderItemServiceImp.delete(orderItem.getId()));
        ordersRepository.deleteById(id);
    }
}

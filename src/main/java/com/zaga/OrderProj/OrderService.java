package com.zaga.OrderProj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    @Autowired
    OrderRepo repo;

    public OrderEntity createOrderDetails(OrderEntity order) {
        try {
            OrderEntity details = repo.save(order);
            logger.info("Order details created successfully: {}", details);
            return details;
        } catch (Exception e) {
            logger.error("Error while creating order details", e);
            throw e;
        }
    }

    public OrderEntity createErrorDetails(OrderEntity order) {
        try {
            OrderEntity details = repo.save(order);
            logger.info("Order details created successfully: {}", details);
            return details;
        } catch (Exception e) {
            logger.error("Error while creating order details", e);
            throw e;
        }
    }

    public OrderEntity getOrderDetails(Long id) {
        try {
            OrderEntity getDetails = repo.findById(id).orElse(null);
            if (getDetails != null) {
                logger.info("Order details retrieved successfully: {}", getDetails);
            } else {
                logger.info("No order details found for ID: {}", id);
            }
            return getDetails;
        } catch (Exception e) {
            logger.error("Error while fetching order details", e);
            throw e;
        }
    }

    public List<OrderEntity> getAllOrders() {
        try {
            List<OrderEntity> allOrders = repo.findAll();
            if (!allOrders.isEmpty()) {
                logger.info("Found {} orders.", allOrders.size());
            } else {
                logger.info("No orders found.");
            }
            return allOrders;
        } catch (Exception e) {
            logger.error("Error while fetching all orders", e);
            throw e;
        }
    }

}
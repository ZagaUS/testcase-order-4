package com.zaga.OrderProj;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
        private static final Logger logger = LogManager.getLogger(OrderController.class);

        @Autowired
        OrderService orderService;

        @PostMapping("/createOrderDetails")
        public OrderEntity createOrders(@RequestBody OrderEntity orderEntity) {
                try {
                        logger.info("Received request to create order: {}", orderEntity);
                        OrderEntity details = orderService.createOrderDetails(orderEntity);
                        logger.info("Order created successfully: {}", details);
                        return details;
                } catch (Exception e) {
                        logger.error("Error while creating order: {}", e.getMessage());
                        throw new RuntimeException("Internal Server Error");
                }
        }

        @PostMapping("/createErrorOrders")
        public OrderEntity createErrorOrder(@RequestBody OrderEntity orderEntity) {
                logger.info("Received request to create order: {}", orderEntity);

                // Validate the OrderEntity object for missing fields
                if (orderEntity == null || orderEntity.getProductId() == null || orderEntity.getProductName() == null
                                || orderEntity.getOrderId() == null/* add more checks for other fields */) {
                        logger.error("Invalid order data. One or more fields are missing.");
                        throw new OrderCreationException("Invalid order data. One or more fields are missing.");

                }

                try {
                        OrderEntity details = orderService.createErrorDetails(orderEntity);
                        logger.info("Order created successfully: {}", details);
                        return details;
                } catch (OrderCreationException e) {
                        logger.error("Error while creating order: {}", e.getMessage());
                        throw e;
                } catch (Exception e) {
                        logger.error("Error while creating order: {}", e.getMessage());
                        throw new RuntimeException("Internal Server Error!!");
                }
        }

        @GetMapping("/getOrderDetails")
        public ResponseEntity<OrderEntity> getOrdersByid(Long id) {
                logger.info("Received request to get order by ID: {}", id);
                OrderEntity getDetails = orderService.getOrderDetails(id);
                if (getDetails != null) {
                        logger.info("Found order with ID {}: {}", id, getDetails);
                        return ResponseEntity.ok(getDetails);
                } else {
                        logger.error("No order found with ID: {}", id);
                        return ResponseEntity.notFound().build();
                }
        }

        @GetMapping("/getAllOrders")
        public ResponseEntity<List<OrderEntity>> getOrders() {
                List<OrderEntity> allOrders = orderService.getAllOrders();
                if (!allOrders.isEmpty()) {
                        logger.info("Found {} orders.", allOrders.size());
                        return ResponseEntity.ok(allOrders);
                } else {
                        logger.error("No orders found.");
                        return ResponseEntity.notFound().build();
                }
        }
}

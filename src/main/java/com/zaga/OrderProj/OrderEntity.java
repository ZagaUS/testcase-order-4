package com.zaga.OrderProj;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "vm_instrumented_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private String productName;
    private Long orderId;
    private Long customerId;
    private String customerName;
    private Long customerPhoneNumber;

    private String modelNo;
    private String productAddress;
    private int quantity;

}

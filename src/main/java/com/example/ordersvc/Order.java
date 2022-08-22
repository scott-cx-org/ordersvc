package com.example.ordersvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    private @Id @Column(name="id") String id;
    private @Column(name="customer_id") Long customerId;
    private @Column(name="ccard_id") Long ccardId;
    private @Column(name="created_date") Date createdDate;
    private @Column(name="order_total") BigDecimal orderTotal;
    private @Column(name="status") String orderStatus;
    private @Column(name="updated_date") Date updatedDate;

    public Order() {}


    public String getId() { return id; }
    public void setId(String id) { this.id = id;}

    public Long getCcardId() { return ccardId;}
    public void setCcardId(Long ccardId) { this.ccardId = ccardId; }

    public Long getCustomerId() { return customerId;}
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public BigDecimal getOrderTotal() { return orderTotal; }
    public void setOrderTotal(BigDecimal ordertotal) { this.orderTotal = ordertotal; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public Date getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }

    public void createOrder(OrderDto orderDto) {
        this.id = String.valueOf(UUID.randomUUID());
        this.customerId = orderDto.getCustomerId();
        this.ccardId = orderDto.getCcardId();
        this.createdDate = new Date();
        this.orderTotal = orderDto.getOrderTotal();
        this.orderStatus = "new";
        this.updatedDate = new Date();
    }

    public void updateOrder(OrderDto orderDto) {
        this.id = orderDto.getId();
        this.customerId = orderDto.getCustomerId();
        this.ccardId = orderDto.getCcardId();
        this.orderTotal = orderDto.getOrderTotal();
        this.orderStatus = "new";
        this.updatedDate = new Date();
    }

    public void deleteOrder(String id) {
        //TODO: add business logic to require pre-shipment
        this.orderStatus = "deleted";
        this.updatedDate = new Date();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId=" + customerId +
                ", ccardId=" + ccardId +
                ", createdDate=" + createdDate +
                ", orderTotal=" + orderTotal +
                ", orderStatus='" + orderStatus + '\'' +
                ", updatedDate=" + updatedDate +
                '}';
    }
}

